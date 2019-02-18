package com.udd.naucnacentrala.web.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udd.naucnacentrala.domain.User;
import com.udd.naucnacentrala.service.UserService;
import com.udd.naucnacentrala.web.dto.FormFieldsDto;
import com.udd.naucnacentrala.web.dto.TaskDto;

@RestController
@RequestMapping(value="/api/task")
public class TaskController {

	@Autowired
	private UserService userService;

	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	FormService formService;
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllTasks(Principal principal) {
		User user = userService.findByEmail(principal.getName());

		List<Task> tasks = taskService.createTaskQuery()
			.active()
			.taskAssignee(user.getId().toString())
			.list();

		List<TaskDto> result = new ArrayList<>();
		for(Task task : tasks) {
			result.add(new TaskDto(
					task.getId(),
					task.getName(),
					runtimeService.getVariable(task.getProcessInstanceId(), "magazineId").toString(),
					(String)runtimeService.getVariable(task.getProcessInstanceId(), "magazineTitle")));
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
    @GetMapping(path = "/{taskId}")
    public ResponseEntity<?> getTask(
			@PathVariable String taskId) {
    	System.out.println("TaskController.getTask...Getting the form data for the task with id: " + taskId);
        TaskFormData tfd = formService.getTaskFormData(taskId);

        List<FormField> properties = tfd.getFormFields();

        return new ResponseEntity<>(new FormFieldsDto(taskId, "publishScientificPaper", properties), HttpStatus.OK);
    }
    
    @PostMapping(path = "executeTask/{taskId}")
    public ResponseEntity<?> executeTask(@RequestBody Map<String, Object> form,
			@PathVariable String taskId) {
    	System.out.println("TaskController.executeTask...Executing the task with id: " + taskId);
    	System.out.println("TaskController.executeTask...Following form data received:");
        System.out.println(form);
        
        User author = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        
        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .active()
                .taskAssignee(author.getId().toString())
                .list().get(0);
            if(task == null) {
            	System.out.println("TaskController.executeTask...Task completed succesfully.");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            runtimeService.setVariables(task.getProcessInstanceId(), form);
            taskService.complete(taskId);

        	System.out.println("TaskController.executeTask...Task completed succesfully.");

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

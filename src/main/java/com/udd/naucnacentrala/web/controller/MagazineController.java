package com.udd.naucnacentrala.web.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.udd.naucnacentrala.domain.Magazine;
import com.udd.naucnacentrala.domain.User;
import com.udd.naucnacentrala.service.MagazineService;
import com.udd.naucnacentrala.service.UserService;

@RestController
@RequestMapping(value="/api/magazine")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class MagazineController {
	
	@Autowired
	private MagazineService magazineService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	FormService formService;
	
	@RequestMapping(value="/getAll", 
			method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createAuthenticationToken() throws AuthenticationException {
	
		return new ResponseEntity(magazineService.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{magazineId}")
	public ResponseEntity<?> startPublication(Principal principal,
			@PathVariable Long magazineId) {
		Magazine magazine = magazineService.findOne(magazineId);
		if(magazine == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		User author = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

		System.out.println("Trenutno ulogovan:");
		System.out.println(author.getEmail());
		
		Map<String, Object> variables = new HashMap<String,Object>();
		variables.put("magazineId", magazine.getId());
		variables.put("authorId", author.getId().toString());
		variables.put("mainEditorId", magazine.getMainEditor().getId().toString());
		variables.put("magazineTitle", magazine.getName());
		runtimeService.startProcessInstanceByKey("publishScientificPaper", variables);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}

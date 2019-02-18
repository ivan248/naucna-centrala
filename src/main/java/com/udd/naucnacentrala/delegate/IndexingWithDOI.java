package com.udd.naucnacentrala.delegate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.udd.naucnacentrala.domain.ScientificPaper;
import com.udd.naucnacentrala.domain.User;
import com.udd.naucnacentrala.elasticsearch.ScientificPaperIndexUnit;
import com.udd.naucnacentrala.elasticsearch.UserElasticSearchDTO;
import com.udd.naucnacentrala.repository.ScientificAreaRepository;
import com.udd.naucnacentrala.service.MagazineService;
import com.udd.naucnacentrala.service.ScientificPaperService;
import com.udd.naucnacentrala.service.UserService;

@Component
public class IndexingWithDOI implements JavaDelegate {
    
	@Autowired
	private UserService userService;
	
	@Autowired
	private ScientificAreaRepository scientificAreaRepository;
	
	@Autowired
	private MagazineService magazineService;
	
	@Autowired
	private ScientificPaperService scientificPaperService;
	
//	@Autowired
//	private ScientificPaperElasticSearchRepository elasticRepository;
 	
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("Starting task IndexingWithDOI...");
        
        ScientificPaperIndexUnit scientificPaperIndexingUnit = new ScientificPaperIndexUnit();
        ScientificPaper scientificPaper = new ScientificPaper();
        
        scientificPaper.setTitle(execution.getVariable("title").toString());
        scientificPaperIndexingUnit.setTitle(execution.getVariable("title").toString());
        
        scientificPaper.setAbstractDescription(execution.getVariable("abstractDescription").toString());
        scientificPaperIndexingUnit.setAbstractDescription(execution.getVariable("abstractDescription").toString());
        
        scientificPaper.setScientificArea(scientificAreaRepository.getOne(2l));
        scientificPaperIndexingUnit.setScientificArea(execution.getVariable("scientificArea").toString());
        
        scientificPaper.setKeywords(execution.getVariable("keywords").toString());
        scientificPaperIndexingUnit.setKeywords(execution.getVariable("keywords").toString());
        
        Set<User> coAuthors = new HashSet<User>();
		coAuthors.add(userService.findById(2l));
		scientificPaper.setCoAuthors(coAuthors);
		scientificPaperIndexingUnit.setCoAuthors(new ArrayList<UserElasticSearchDTO>());
		
		scientificPaper.setMagazine(magazineService.findOne(Long.parseLong(execution.getVariable("magazineId").toString())));
		scientificPaperIndexingUnit.setMagazine(execution.getVariable("magazineId").toString());
		
		scientificPaper.setAuthor(userService.findById(Long.parseLong(execution.getVariable("authorId").toString())));
		scientificPaperIndexingUnit.setAuthor(userService.findById(Long.parseLong(execution.getVariable("authorId").toString())).getEmail());
		
		scientificPaperService.save(scientificPaper);
		//elasticRepository.save(scientificPaperIndexingUnit);
		
        System.out.println("Ending task IndexingWithDOI...");	

	}
}

//package com.udd.naucnacentrala.web.dto;
//
//import java.util.Base64;
//import java.util.HashSet;
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.udd.naucnacentrala.domain.ScientificArea;
//import com.udd.naucnacentrala.domain.User;
//import com.udd.naucnacentrala.service.impl.ScientificAreaServiceImpl;
//
//
//public class ConverterDTO {
//
//	@Autowired
//	private ScientificAreaServiceImpl scientificAreaService;
//	
//	public User userDTOtoUser(UserDTO userDTO) {
//		
//		String originalInput = userDTO.getPassword();
//		String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
//		
//		Set<ScientificArea> scientificAreas = new HashSet<ScientificArea>();
//		
//		for(ScientificArea sa : scientificAreaService.getAll()) {
//			if(userDTO.getScientificAreas().contains(sa.getScientificAreaName()))
//				scientificAreas.add(sa);
//		}
//		
//		System.out.println("converter");
//		
//		return new User(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail(),
//				encodedString, userDTO.getUserRole(), userDTO.getCity(),
//				userDTO.getState(), userDTO.getTitle(), scientificAreas);
//	}
//	
//	public ConverterDTO() {
//		
//	}
//}

//package com.udd.naucnacentrala.web.controller;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.udd.naucnacentrala.domain.ScientificArea;
//import com.udd.naucnacentrala.domain.User;
//import com.udd.naucnacentrala.service.AuthenticationService;
//import com.udd.naucnacentrala.service.ScientificAreaService;
//import com.udd.naucnacentrala.web.dto.UserDTO;
//
//@RestController
//@RequestMapping("/api/authentication")
//public class AuthenticationController {
//
//	@Autowired
//	private AuthenticationService authenticationService;
//	
//	@Autowired
//	private ScientificAreaService scientificAreaService;
//	
////	@Autowired
////	private BCryptPasswordEncoder bCryptPasswordEncoder;
//	
//	@PostMapping("/signup")
//	public ResponseEntity<Boolean> signup(@RequestBody UserDTO userDTO){
//		try {
//			
////			String originalInput = userDTO.getPassword();
////			String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
//	
//			Set<ScientificArea> scientificAreas = new HashSet<ScientificArea>();
//			
//			for(ScientificArea sa : scientificAreaService.getAll()) {
//				if(userDTO.getScientificAreas().contains(sa.getScientificAreaName()))
//					scientificAreas.add(sa);
//			}
//			
////			User u = new User(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail(),
////					bCryptPasswordEncoder.encode(userDTO.getPassword()), userDTO.getUserRole(), userDTO.getCity(),
////					userDTO.getState(), userDTO.getTitle(), scientificAreas);
//			User u = null;
//			if(authenticationService.signUp(u))
//				return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
//			
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			return  new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
//		}
//		
//		return  new ResponseEntity<Boolean>(false, HttpStatus.NOT_ACCEPTABLE);
//	}
//	
//	@PostMapping("/signin")
//	public ResponseEntity<Boolean> login(@RequestBody UserDTO userDTO){
//		
//		System.out.println("dsadas");
//		return  new ResponseEntity<Boolean>(true, HttpStatus.OK);
//	}
//	
//}

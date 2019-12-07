package net.milkpoint.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.milkpoint.service.LoginService;

@RestController
@RequestMapping("/api")
public class LoginResource {
	
	@Autowired
	LoginService service;
	
	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody @Valid String email, String senha) {
		if(email != null && senha != null) {
			Object obj = service.login(email, senha);
			
			return new ResponseEntity<Object>(obj, HttpStatus.OK);
			
		}
		
		return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
	}
	
}

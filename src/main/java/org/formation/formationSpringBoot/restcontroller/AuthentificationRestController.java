package org.formation.formationSpringBoot.restcontroller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/rest/auth")
public class AuthentificationRestController {

	@GetMapping("")
	public boolean verif() {
		return true;
	}
	
}

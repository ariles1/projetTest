package org.formation.formationSpringBoot.restcontroller;

import org.formation.formationSpringBoot.entity.Role;
import org.formation.formationSpringBoot.entity.User;
import org.formation.formationSpringBoot.entity.UserRole;
import org.formation.formationSpringBoot.repository.UserRepository;
import org.formation.formationSpringBoot.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/user")
@CrossOrigin(origins = "*")
public class CreateUserRestController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("")
	public void createUser(@RequestBody User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(Role.ROLE_USER);
		userRoleRepository.save(userRole);
	}
}

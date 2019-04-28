package org.formation.formationSpringBoot.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.formation.formationSpringBoot.entity.Formateur;
import org.formation.formationSpringBoot.entity.Personne;
import org.formation.formationSpringBoot.entity.Stagiaire;
import org.formation.formationSpringBoot.entity.Titre;
import org.formation.formationSpringBoot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsoleApplicationService implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {

		System.out.println("console:"+passwordEncoder);
	}

}

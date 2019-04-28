package org.formation.formationSpringBoot.restcontroller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.formation.formationSpringBoot.entity.Formateur;
import org.formation.formationSpringBoot.entity.Personne;
import org.formation.formationSpringBoot.entity.Stagiaire;
import org.formation.formationSpringBoot.entity.view.JsonViews;
import org.formation.formationSpringBoot.repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/rest/personne")
public class PersonneRestController {

	@Autowired
	private PersonneRepository personneRepository;

	@JsonView(JsonViews.Common.class)
	@GetMapping(value = { "", "/" })
	public ResponseEntity<List<Personne>> findAll() {
		return new ResponseEntity<List<Personne>>(personneRepository.findAll(), HttpStatus.OK);
	}

	@JsonView(JsonViews.PersonneWithSalle.class)
	@GetMapping(value = { "/salle" })
	public ResponseEntity<List<Personne>> findAllWithSalle() {
		return new ResponseEntity<List<Personne>>(personneRepository.findAll(), HttpStatus.OK);
	}

	@JsonView(JsonViews.Common.class)
	@GetMapping("/{id}")
	public ResponseEntity<Personne> findById(@PathVariable(name = "id") Integer id) {
		Optional<Personne> opt = personneRepository.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Personne>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/formateur")
	public ResponseEntity<Void> insertFormateur(@Valid @RequestBody Formateur formateur, BindingResult br,
			UriComponentsBuilder uCB) {
		return insert(formateur, br, uCB);
	}

	@PostMapping("/stagiaire")
	public ResponseEntity<Void> insertStagiaire(@Valid @RequestBody Stagiaire stagiaire, BindingResult br,
			UriComponentsBuilder uCB) {
		return insert(stagiaire, br, uCB);
	}

	private ResponseEntity<Void> insert(Personne personne, BindingResult br, UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		personneRepository.save(personne);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uCB.path("/rest/personne/{id}").buildAndExpand(personne.getId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	@PutMapping("/formateur")
	public ResponseEntity<Void> updateFormateur(@Valid @RequestBody Formateur formateur, BindingResult br) {
		return update(formateur, br);
	}

	@PutMapping("/stagiaire")
	public ResponseEntity<Void> updateStagiaire(@Valid @RequestBody Stagiaire stagiaire, BindingResult br) {
		return update(stagiaire, br);
	}

	private ResponseEntity<Void> update(Personne personne, BindingResult br) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Optional<Personne> opt = personneRepository.findById(personne.getId());
		if (opt.isPresent()) {
			personne.setVersion(opt.get().getVersion());
			personneRepository.save(personne);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "id") Integer id) {
		personneRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}

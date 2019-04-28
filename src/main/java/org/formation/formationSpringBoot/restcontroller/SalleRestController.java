package org.formation.formationSpringBoot.restcontroller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.formation.formationSpringBoot.entity.Salle;
import org.formation.formationSpringBoot.entity.view.JsonViews;
import org.formation.formationSpringBoot.repository.SalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/rest/salle")
@CrossOrigin(origins="*")
public class SalleRestController {
	@Autowired
	SalleRepository salleRepository;

	@JsonView(JsonViews.SalleWithPersonne.class)
	@GetMapping("/personne")
	public List<Salle> findAllWithSalle() {
		return salleRepository.findAllSalleWithPersonne();
	}
	
	@JsonView(JsonViews.Common.class)
	@GetMapping("")
	public List<Salle> findAll(){
		return salleRepository.findAll();
	}
	
	@JsonView(JsonViews.Common.class)
	@GetMapping("/{id}")
	public ResponseEntity<Salle> findById(@PathVariable(name = "id") Integer id) {
		Optional<Salle> opt = salleRepository.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Salle>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("")
	public ResponseEntity<Void> insert(@Valid @RequestBody Salle salle, BindingResult br,
			UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		salleRepository.save(salle);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uCB.path("/rest/personne/{id}").buildAndExpand(salle.getId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	@PutMapping("")
	private ResponseEntity<Void> update(@RequestBody Salle salle, BindingResult br) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Optional<Salle> opt = salleRepository.findById(salle.getId());
		if (opt.isPresent()) {
			salle.setVersion(opt.get().getVersion());
			salleRepository.save(salle);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable(name = "id") Integer id) {
		salleRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}

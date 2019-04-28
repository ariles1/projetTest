package org.formation.formationSpringBoot.controller;

import java.util.Optional;

import org.formation.formationSpringBoot.entity.Formateur;
import org.formation.formationSpringBoot.entity.Personne;
import org.formation.formationSpringBoot.entity.Stagiaire;
import org.formation.formationSpringBoot.entity.Titre;
import org.formation.formationSpringBoot.repository.PersonneRepository;
import org.formation.formationSpringBoot.repository.SalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/personne")
public class PersonneController {

	@Autowired
	private PersonneRepository personneRepository;
	@Autowired
	private SalleRepository salleRepository;

	@GetMapping("/list")
	public ModelAndView list() {
		return new ModelAndView("personne/list", "personnes", personneRepository.findAll());
	}

	@GetMapping("/delete")
	public ModelAndView delete(Integer id) {
		personneRepository.deleteById(id);
		return new ModelAndView("redirect:/personne/list");
	}

	@GetMapping("/edit")
	public ModelAndView edit(Integer id) {
		Optional<Personne> opt = personneRepository.findById(id);
		if (opt.isPresent()) {
			return goEdit(opt.get());
		}
		return new ModelAndView("redirect:/personne/list");
	}

	private ModelAndView goEdit(Personne personne) {
		ModelAndView modelAndView = new ModelAndView("personne/edit");
		modelAndView.addObject("personne", personne);
		modelAndView.addObject("titres", Titre.values());
		modelAndView.addObject("salles", salleRepository.findAll());
		return modelAndView;
	}

	@GetMapping("/addFormateur")
	public ModelAndView addFormateur() {
		return goEdit(new Formateur());
	}

	@GetMapping("/addStagiaire")
	public ModelAndView addStagiaire() {
		return goEdit(new Stagiaire());
	}

	@GetMapping("/saveFormateur")
	public ModelAndView saveFormateur(@ModelAttribute("personne") Formateur formateur, BindingResult br) {
		return save(formateur, br);
	}

	@GetMapping("/saveStagiaire")
	public ModelAndView saveStagiaire(@ModelAttribute("personne") Stagiaire stagiaire, BindingResult br) {
		return save(stagiaire, br);
	}

	private ModelAndView save(Personne personne, BindingResult br) {
		if (br.hasErrors()) {
			return goEdit(personne);
		}
		if (personne.getSalle() != null && personne.getSalle().getId() == null) {
			personne.setSalle(null);
		}
		personneRepository.save(personne);
		return new ModelAndView("redirect:/personne/list");
	}

}

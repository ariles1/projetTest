package org.formation.formationSpringBoot.controller;

import java.util.Optional;

import org.formation.formationSpringBoot.entity.Salle;
import org.formation.formationSpringBoot.repository.SalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/salle")
public class SalleController {
	@Autowired
	private SalleRepository salleRepository;

	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("salles", salleRepository.findAll());
		return "salle/list";
	}

	@GetMapping("/edit")
	public String edit(Model model, @RequestParam(name = "id") Integer id) {
		Optional<Salle> opt = salleRepository.findById(id);
		if (opt.isPresent()) {
			return goEdit(opt.get(), model);
		}
		return "redirect:/salle/list";
	}

	private String goEdit(Salle salle, Model model) {
		model.addAttribute("salle", salle);
		return "salle/edit";
	}

	@GetMapping("/add")
	public String add(Model model) {
		return goEdit(new Salle(), model);
	}

	@GetMapping("/delete")
	public String delete(@RequestParam(name = "id") Integer id) {
		salleRepository.deleteById(id);
		return "redirect:/salle/list";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute("salle") Salle salle, BindingResult br, Model model) {
		if (br.hasErrors()) {
			return goEdit(salle, model);
		}
		salleRepository.save(salle);
		return "redirect:/salle/list";
	}
}

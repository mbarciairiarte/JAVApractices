package com.website.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.website.domain.Persona;
import com.website.service.PersonaService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class IndexController {
	
	@Autowired
	private PersonaService personaService;
	
	@GetMapping("/")
	public String inicio(Model model) {
		var personas = personaService.listarPersonas();
				
		log.info("se esta ejecutando index Controller");
//		model.addAttribute("mensaje", mensaje);
		model.addAttribute("personas", personas);
		
		return "index";
	};
	
	@GetMapping("/agregar")
	public String agregar(Persona persona) {
		return "modificar";
		
	}
	
	@PostMapping("/guardar")
	public String guardar(@Valid Persona persona, Errors errores) {
		
		if(errores.hasErrors()) {
			return "modificar";
		}
		
		
		personaService.guardar(persona);
		return "redirect:/";
	}
	
	@GetMapping("/editar/{idPersona}")
	public String editar(Persona persona, Model model) {
		personaService.encontrarPersona(persona);
		model.addAttribute("persona",persona);
		return "modificar";
	}
	
	@GetMapping("/eliminar")
	public String eliminar(Persona persona) {
		personaService.eliminar(persona);
		return"redirect:/";
	}
	
}
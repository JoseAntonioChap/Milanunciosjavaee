package com.example.milanuncios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.milanuncios.interfaces.IAnuncioService;
import com.example.milanuncios.interfaces.IUsuarioService;
import com.example.milanuncios.model.Anuncio;
import com.example.milanuncios.util.Anuncio_v;

@Controller
public class MiControllerjose {

	
	
	@Autowired
	IUsuarioService usuarioService;
	@Autowired
	IAnuncioService anuncioService;
	
	
	@GetMapping("/FEditar_anuncio/{id_anuncio}")
	public String editar_usuario(@PathVariable("id_anuncio") int id_anuncio, Model model) {
		Anuncio anuncio = anuncioService.find_by_id(id_anuncio);
		String id_anuncios = Integer.toString(anuncio.getId_anuncio());
		model.addAttribute("anuncio_v", new Anuncio_v(Integer.toString(anuncio.getId_anuncio()), Integer.toString(anuncio.getId_categoria()), anuncio.getTitulo(), anuncio.getDescripcion(),
				Double.toString(anuncio.getPrecio()), anuncio.getUser()));
		return "editar_usuario";
	}
	/*
	@PostMapping("/modificar_anuncio")
	public String modificar_alumno(Model model, Usuario_v usuario_v, BindingResult result) {
		usuario_v.validate(result);
		if (result.hasErrors()) {
			
			return "editar_usuario";
		} else {
			Usuario usuarios = new Usuario(usuario_v.getUser(), usuario_v.getPassword(), usuario_v.getEmail());

			usuarioservice.save(usuarios);
				
				model.addAttribute("mensaje", "alumno modificado");
				

			}
		
		return get_usuarios(model);
	}
	*/
}

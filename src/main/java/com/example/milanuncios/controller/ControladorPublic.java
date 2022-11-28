package com.example.milanuncios.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.milanuncios.dto.AnuncioDTO;
import com.example.milanuncios.interfaces.IAnuncioService;
import com.example.milanuncios.interfaces.ICategoriaService;
import com.example.milanuncios.interfaces.IUsuarioService;
import com.example.milanuncios.model.Anuncio;
import com.example.milanuncios.model.Usuario;
import com.example.milanuncios.util.findAnuncioForm;



@Controller
@RequestMapping("/public")
public class ControladorPublic {
	
	@Autowired
	ICategoriaService categoriaService;
	
	@Autowired
	IAnuncioService anuncioService;
	
	@Autowired
	IUsuarioService usuarioService;

	@GetMapping("/lista_anuncios/{id_categoria}")
	public String lista_anuncios_categoria(@PathVariable("id_categoria") int id_categoria, Model model) {
		List<Anuncio> anuncios = anuncioService.anuncios_by_categoria(id_categoria);
		System.out.println(id_categoria);
		List<AnuncioDTO> anuncios_dto = new ArrayList();
		for (Anuncio anuncio : anuncios) {
			AnuncioDTO anunciodto = new AnuncioDTO(anuncio.getId_anuncio(),anuncio.getId_categoria(),anuncio.getFecha()
					,anuncio.getTitulo(),anuncio.getDescripcion(),anuncio.getPrecio(),anuncio.getUser());
			anuncios_dto.add(anunciodto);
		}
		model.addAttribute("anuncios", anuncios_dto);
		return "listado_anuncios";

	}
	
	
	
	@GetMapping("/datos_anuncio/{id_anuncio}")
	public String datos_anuncio(@PathVariable("id_anuncio") int id_anuncio, Model model) {
		Anuncio anuncio = anuncioService.find_by_id(id_anuncio);
		Usuario usuario = usuarioService.find_by_user(anuncio.getUser());
			AnuncioDTO anunciodto = new AnuncioDTO(anuncio.getId_anuncio(),anuncio.getId_categoria(),anuncio.getFecha()
					,anuncio.getTitulo(),anuncio.getDescripcion(),anuncio.getPrecio(),anuncio.getUser());
		model.addAttribute("anuncio", anunciodto);
		model.addAttribute("usuario", usuario);
		return "datos_anuncio";
	}
	
	
	
	@PostMapping("buscar_anuncios")
	public String buscar_anuncios(Model model, findAnuncioForm findAnuncioForm) {
		List<Anuncio> anuncios = anuncioService.find_contains_titulo(findAnuncioForm.getTexto());
		
		List<AnuncioDTO> anuncios_dto = new ArrayList();
		for (Anuncio anuncio : anuncios) {
			AnuncioDTO anunciodto = new AnuncioDTO(anuncio.getId_anuncio(),anuncio.getId_categoria(),anuncio.getFecha()
					,anuncio.getTitulo(),anuncio.getDescripcion(),anuncio.getPrecio(),anuncio.getUser());
			anuncios_dto.add(anunciodto);
		}
		model.addAttribute("anuncios", anuncios_dto);
		return "listado_anuncios";
	}
	
	
	
	

}

package com.example.milanuncios.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.milanuncios.dto.CategoriaDTO;
import com.example.milanuncios.model.Categoria;
import com.example.milanuncios.interfaces.ICategoriaService;



@Controller
public class ControladorPublico {
	
	@Autowired
	ICategoriaService categoriaService;
	
	@GetMapping("/")
	public String listado_categorias(Model model) {
		List<Categoria> categorias = categoriaService.list_all_categorias();
		List<CategoriaDTO> categoriasdto = new ArrayList();
		for (Categoria categoria : categorias) {
			CategoriaDTO categoriadto = new CategoriaDTO(categoria.getId_categoria(), categoria.getDescripcion());
			categoriasdto.add(categoriadto);
		}
		model.addAttribute("categorias", categoriasdto);
		return "index";
	}
	

}

package com.example.milanuncios.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.milanuncios.dto.CategoriaDTO;
import com.example.milanuncios.model.Categoria;
import com.example.milanuncios.model.Role;
import com.example.milanuncios.model.Usuario;
import com.example.milanuncios.util.Usuario_v;
import com.example.milanuncios.interfaces.ICategoriaService;
import com.example.milanuncios.interfaces.IUsuarioService;



@Controller
public class Controlador {
	
	@Autowired
	ICategoriaService categoriaService;
	@Autowired
	IUsuarioService usuarioService;
	
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
	
	
	@GetMapping("/sign")
	public String alta_usuario(Model model) {
		Usuario_v usuario_v = new Usuario_v();
		model.addAttribute("usuario_v", usuario_v);
		return "sign";

	}
	
	@GetMapping("/login")
	public String login(Model model) {
		Usuario_v usuario_v = new Usuario_v();
		model.addAttribute("usuario_v", usuario_v);
		return "login";

	}
	
	@PostMapping("/grabar_user")
	public String grabar_usuario(Model model, Usuario_v usuario_v, BindingResult result) {
		usuario_v.validate(result);
		if (result.hasErrors()) {
			return "sign";
		} else {
			if (usuarioService.find_by_user(usuario_v.getUser()) == null) {
				Usuario usuario = new Usuario();
				usuario.setUser(usuario_v.getUser());
				usuario.setPassword(usuario_v.getPassword());
				usuario.setEmail(usuario_v.getEmail());
				
				Set<Role> roles = new HashSet();
				roles.add(new Role("userbasico"));
				usuario.setRoles(roles);
				usuarioService.save(usuario);

				return listado_categorias(model);

			} else {
				// result.rejectValue("id_alumno","alumno ya existe");
				result.rejectValue("user", "badFormat", "user ya existe");
				return "sign";
			}
		}
	}
	
	

	
	
}

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.milanuncios.dto.AnuncioDTO;
import com.example.milanuncios.interfaces.IAnuncioService;
import com.example.milanuncios.interfaces.IUsuarioService;
import com.example.milanuncios.model.Anuncio;
import com.example.milanuncios.model.Role;
import com.example.milanuncios.model.Usuario;
import com.example.milanuncios.util.Usuario_v;

@Controller
@RequestMapping("/private")
public class ControladorPrivate {

	@Autowired
	IUsuarioService usuarioService;
	@Autowired
	IAnuncioService anuncioService;
	
	@PostMapping("/check_user")
	public String grabar_usuario(Model model, Usuario_v usuario_v, HttpServletRequest request) {
		HttpSession session= request.getSession(true);
		Usuario usu = usuarioService.find_by_user(usuario_v.getUser());
		if (usu == null) {
			return "login";
			
		}		
		if(usu!=null && usu.getPassword().equals(usuario_v.getPassword()) && usu.getRoles().stream().filter(u->u.getRole()=="admin") != null  ) {
				session.setAttribute("user", usuario_v.getUser());
				session.setAttribute("role", usu.getRoles());
				System.out.println(usu.getRoles().stream().filter(u->u.getRole()=="admin").toString());
				return "menu_admin";
				
			}
		if(usu!=null && usu.getPassword().equals(usuario_v.getPassword()) && usu.getRoles().stream().filter(u->u.getRole()=="admin") == null  ) {
			session.setAttribute("user", usuario_v.getUser());
			session.setAttribute("role", usu.getRoles());
			return "menu_user";
			
		}else {
			
			return "login";
		}}
		
		@GetMapping("/administrar_anuncio")
		public String lista_anuncios(Model model) {
			List<Anuncio> anuncios = anuncioService.list_all();
			
			List<AnuncioDTO> anuncios_dto = new ArrayList();
			for (Anuncio anuncio : anuncios) {
				AnuncioDTO anunciodto = new AnuncioDTO(anuncio.getId_anuncio(),anuncio.getId_categoria(),anuncio.getFecha()
						,anuncio.getTitulo(),anuncio.getDescripcion(),anuncio.getPrecio(),anuncio.getUser());
				anuncios_dto.add(anunciodto);
			}
			model.addAttribute("anuncios", anuncios_dto);
			return "administrar_anuncio";

		}
		
		@GetMapping("/eliminar_anuncio/{id_anuncio}/{id_categoria}")
		public String eliminar_anuncio(@PathVariable("id_anuncio") int id_anuncio,
				@PathVariable("id_categoria") int id_categoria, Model model) {
			anuncioService.delete_anuncio(id_anuncio);
		
			return lista_anuncios(model);

		}
		
			
			
		}
			
			
		
	


package com.example.milanuncios.controller;

import java.sql.Date;
import java.time.LocalDate;
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
import com.example.milanuncios.dto.UsuarioDTO;
import com.example.milanuncios.interfaces.IAnuncioService;
import com.example.milanuncios.interfaces.ICategoriaService;
import com.example.milanuncios.interfaces.IUsuarioService;
import com.example.milanuncios.model.Anuncio;
import com.example.milanuncios.model.Categoria;
import com.example.milanuncios.model.Role;
import com.example.milanuncios.model.Usuario;
import com.example.milanuncios.util.Anuncio_v;
import com.example.milanuncios.util.Categoria_v;
import com.example.milanuncios.util.Usuario_v;

@Controller
@RequestMapping("/private")
public class ControladorPrivate {

	@Autowired
	IUsuarioService usuarioService;
	@Autowired
	IAnuncioService anuncioService;
	@Autowired
	ICategoriaService categoriaService;
	
	@PostMapping("/check_user")
	public String grabar_usuario(Model model, Usuario_v usuario_v, HttpServletRequest request, BindingResult result) {
		HttpSession session= request.getSession(true);
		usuario_v.validate_login(result);
		if (result.hasErrors()) {
			model.addAttribute("mensaje", "aquí");
			return "login";
			
		}else {
		Usuario usu = usuarioService.find_by_user(usuario_v.getUser());
		Set<Role> roles = usu.getRoles();
		List<Role> rol = new ArrayList(roles);
		Role aux = rol.get(0);
		System.out.println(aux.getRole());
		//usu.getPassword().equals(usuario_v.getPassword()) && aux.getRole().equals("admin")
		
		if (usu == null) {
			model.addAttribute("mensaje", "El usuario no existe");
			return "login";
			
		}else {	
				if(usu != null) {
					if(usu.getPassword().equals(usuario_v.getPassword()) && aux.getRole().equals("admin")) {
						session.setAttribute("usuario", usu.getUser());
						session.setAttribute("role", aux.getRole());
						return "menu_admin";
					}
					if(usu.getPassword().equals(usuario_v.getPassword()) && aux.getRole().equals("userbasico")) {
						session.setAttribute("usuario", usu.getUser());
						session.setAttribute("role", aux.getRole());
						return "menu_user";
					}
					
				}else {
					model.addAttribute("mensaje", "Error al inicial sesión, revise usuario y contraseña");
					return "login";
				}
				model.addAttribute("mensaje", "Error al inicial sesión, revise usuario y contraseña");
		return "login";
		}
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
		
		
		@GetMapping("/administrar_usuario")
		public String lista_usuarios(Model model) {
			List<Usuario> usuarios = usuarioService.List_all();
			List<UsuarioDTO> usuarios_dto = new ArrayList();
			
			for (Usuario usuario : usuarios) {
				UsuarioDTO usuariodto = new UsuarioDTO(usuario.getUser(), usuario.getEmail(), usuario.getPassword());
				usuarios_dto.add(usuariodto);
			}
			
		
			
			model.addAttribute("usuarios", usuarios_dto);
			return "administrar_usuario";

		}
	
		@GetMapping("/eliminar_usuario/{user}")
		public String eliminar_usuario(@PathVariable("user") String user, Model model) {
			
			usuarioService.delete_user(user);
			return lista_usuarios(model);
		}
		
		@GetMapping("/eliminar_anuncio/{id_anuncio}")
		public String eliminar_anuncio(@PathVariable("id_anuncio") int id_anuncio, Model model) {
			anuncioService.delete_anuncio(id_anuncio);
			
			return lista_anuncios(model);
		}
		
		
		
		@GetMapping("/FEditar_anuncio/{id_anuncio}")
		public String editar_usuario(@PathVariable("id_anuncio") int id_anuncio, Model model) {
			Anuncio anuncio = anuncioService.find_by_id(id_anuncio);
			
			model.addAttribute("anuncio_v", new Anuncio_v(Integer.toString(anuncio.getId_anuncio()), Integer.toString(anuncio.getId_categoria()), anuncio.getTitulo(), anuncio.getDescripcion(),
					Double.toString(anuncio.getPrecio()), anuncio.getUser()));
			return "FEditar_anuncio";
		}
	
		@GetMapping("/FEditar_usuario/{user}")
		public String editar_usuario(@PathVariable("user") String user, Model model) {
			Usuario usuario = usuarioService.find_by_user(user);
			Set<Role> roles = usuario.getRoles();
			List<Role> rol = new ArrayList(roles);
			Role aux = rol.get(0);
			System.out.println(aux.getRole());
			model.addAttribute("usuario_v", new Usuario_v(usuario.getUser(), usuario.getPassword(), usuario.getEmail(), aux.getRole()));
			
			return "FEditar_usuario";
		}
	
		@GetMapping("/alta_categoria")
        public String alta_categoria(Model model) {
            Categoria_v categoria_v = new Categoria_v();
            model.addAttribute("categoria_v", categoria_v);
            return "alta_categoria";



       }
        
        @PostMapping("/grabar_categoria")
        public String grabar_categoria(Model model, Categoria_v categoria_v, BindingResult result) {
            categoria_v.validate(result);
            if (result.hasErrors()) {
                return "alta_categoria";
            } else {
                if (categoriaService.find_by_id(Integer.parseInt(categoria_v.getId_categoria())) == null) {
                    Categoria categoria = new Categoria();
                    categoria.setId_categoria(Integer.parseInt(categoria_v.getId_categoria()));
                    categoria.setDescripcion(categoria_v.getDescripcion());
                    
                    categoriaService.save(categoria);
                    model.addAttribute("mensaje", "Categoria añadida correctamente");
                    return alta_categoria(model);



               } else {
                    result.rejectValue("id_categoria", "badFormat", "categoria ya existe");
                    return "alta_categoria";
                }
            }
        }
        
        
        @GetMapping("/alta_anuncio")
        public String alta_anuncio(Model model) {
        	List<Categoria> categorias = categoriaService.list_all_categorias();
            Anuncio_v anuncio_v = new Anuncio_v();
            model.addAttribute("anuncio_v", anuncio_v);
            model.addAttribute("categorias", categorias);
            return "alta_anuncio";



       }
        
        @PostMapping("/grabar_anuncio")
        public String grabar_anuncio(Model model, Anuncio_v anuncio_v, BindingResult result, HttpServletRequest request) {
            HttpSession session= request.getSession(true);
            
            String usuario = (String) session.getAttribute("usuario");
            Date fecha = Date.valueOf(LocalDate.now());
            
            anuncio_v.validate(result);
            if (result.hasErrors()) {
                return "alta_anuncio";
            } else {
                if(categoriaService.find_by_id(Integer.parseInt(anuncio_v.getId_categoria())) == null) {
                    model.addAttribute("mensaje", "No existe esa categoria");
                    return "alta_anuncio";
                    
                }else {
                    Anuncio anuncio = new Anuncio(Integer.parseInt(anuncio_v.getId_categoria()),fecha,anuncio_v.getTitulo(),anuncio_v.getDescripcion()
                            ,Double.parseDouble(anuncio_v.getPrecio()),usuario);
                    anuncioService.save(anuncio);
                    
                    model.addAttribute("mensaje", "Anuncio añadido correctamente");
                    return alta_anuncio(model);
                }
                
            }



       }
        
        @GetMapping("/menu_admin")
        public String volver_menu_admin(Model model) {
            return "menu_admin";



       }
        @GetMapping("/menu_user")
        public String volver_menu_user(Model model) {
            return "menu_user";



       }
        
        @PostMapping("/modificar_anuncio")
        public String modificar_anuncio(Model model, Anuncio_v anuncio_v, BindingResult result) {
    		anuncio_v.validate(result);
    		Anuncio anunciocheck = anuncioService.find_by_id(Integer.parseInt(anuncio_v.getId_anuncio()));
    		 
    		if (result.hasErrors()) {
    			
    			return "Feditar_anuncio";
    			
    		}else {
    		
    		if(categoriaService.find_by_id(Integer.parseInt(anuncio_v.getId_categoria())) == null) {
    			model.addAttribute("mensaje", "La categoria no existe");
			
    		}
    		else {
    			Anuncio anuncio = new Anuncio(Integer.parseInt(anuncio_v.getId_anuncio()), Integer.parseInt(anuncio_v.getId_categoria()), anunciocheck.getFecha(), anuncio_v.getTitulo(),anuncio_v.getDescripcion(), Double.parseDouble(anuncio_v.getPrecio()), anunciocheck.getUser());
    				
    				model.addAttribute("mensaje", "anuncio modificado");
    				anuncioService.save(anuncio);

    			}
    		
    		return "Feditar_anuncio";
    	}	 }
		
        
        
        @PostMapping("/modificar_usuario")
        public String modificar_anuncio(Model model, Usuario_v usuario_v, BindingResult result) {
    		usuario_v.validate(result);
    		
    		
    		if (result.hasErrors()) {
    			
    			return "Feditar_usuario";
    			
    		}else {
    		
    		Usuario usuario = new Usuario(usuario_v.getUser(), usuario_v.getPassword(), usuario_v.getEmail());
    	
    			
    				
    				model.addAttribute("mensaje", "usuario modificado");
    				usuarioService.save(usuario);

    		
    		return "Feditar_usuario";
    	}	 }
		
        
        @GetMapping("/mis_anuncios")
        public String lista_anuncios_user(Model model, HttpServletRequest request) {
        	HttpSession session = request.getSession(true);
        	String user = (String) session.getAttribute("usuario");
            List<Anuncio> anuncios = anuncioService.anuncios_by_usuario(user);
            List<AnuncioDTO> anuncios_dto = new ArrayList();
            for (Anuncio anuncio : anuncios) {
                AnuncioDTO anunciodto = new AnuncioDTO(anuncio.getId_anuncio(),anuncio.getId_categoria(),anuncio.getFecha()
                        ,anuncio.getTitulo(),anuncio.getDescripcion(),anuncio.getPrecio(),anuncio.getUser());
                anuncios_dto.add(anunciodto);
            }
            model.addAttribute("anuncios", anuncios_dto);
            return "mis_anuncios";



       }
        
        @GetMapping("/eliminarmi_anuncio/{id_anuncio}")
		public String eliminarmi_anuncio(@PathVariable("id_anuncio") int id_anuncio, Model model, HttpServletRequest request) {
			anuncioService.delete_anuncio(id_anuncio);
			
			return lista_anuncios_user(model, request);
		}
        
        
		}
			
			
		
	


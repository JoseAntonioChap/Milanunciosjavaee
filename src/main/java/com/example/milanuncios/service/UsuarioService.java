package com.example.milanuncios.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.milanuncios.interfaces.IUsuarioService;
import com.example.milanuncios.model.Categoria;
import com.example.milanuncios.model.Role;
import com.example.milanuncios.model.Usuario;
import com.example.milanuncios.repository.RoleRepository;
import com.example.milanuncios.repository.UsuarioRepository;
@Service
public class UsuarioService implements IUsuarioService {

	@Autowired
	RoleRepository rolerepository;
	
	@Autowired
	UsuarioRepository usuariorepository;
	
	
	
	@Override
	public List<Usuario> usuarios_by_role(String role) {
		List<Usuario> usuarios;
		Role roleaux = rolerepository.findById(role).orElse(null);
		if (roleaux == null) {
			return null;
		} else {
			usuarios = new ArrayList(roleaux.getUsuarios());
			return usuarios;
		}
	}

	@Override
	public void delete_user(String usuario) {
		usuariorepository.deleteById(usuario);

	}

	@Override
	public Usuario find_by_user(String usuario) {
		// TODO Auto-generated method stub
		return usuariorepository.findById(usuario).orElse(null);
	}

	@Override
	public void save(Usuario usuario) {
		usuariorepository.save(usuario);

	}

	@Override
	public List<Usuario> List_all() {
		
		return (List<Usuario>) usuariorepository.findAll();
	}

}

package com.example.milanuncios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.milanuncios.interfaces.IRoleService;
import com.example.milanuncios.model.Role;
import com.example.milanuncios.repository.RoleRepository;

@Service
public class RoleService implements IRoleService {

	@Autowired
	RoleRepository rolerepository;
	
	@Override
	public List<Role> list_all_roles() {
		
		return (List<Role>) rolerepository.findAll();
	}

}

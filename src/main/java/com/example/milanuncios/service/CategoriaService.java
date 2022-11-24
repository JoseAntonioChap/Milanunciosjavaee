package com.example.milanuncios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.milanuncios.interfaces.ICategoriaService;
import com.example.milanuncios.model.Categoria;
import com.example.milanuncios.repository.CategoriaRepository;

@Service
public class CategoriaService implements ICategoriaService {

	@Autowired
	CategoriaRepository categoriarepository;
	
	
	
	@Override
	public Categoria find_by_id(int id_categoria) {
		// TODO Auto-generated method stub
		return categoriarepository.findById(id_categoria).orElse(null);
	}

	@Override
	public List<Categoria> list_all_categorias() {
		// TODO Auto-generated method stub
		return (List<Categoria>) categoriarepository.findAll();
	}

}

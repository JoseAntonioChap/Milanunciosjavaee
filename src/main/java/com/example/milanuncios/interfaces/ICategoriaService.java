package com.example.milanuncios.interfaces;

import java.util.List;

import com.example.milanuncios.model.Categoria;



public interface ICategoriaService {
	public Categoria find_by_id(int id_categoria);
	public List<Categoria> list_all_categorias();
	public void save(Categoria categoria);
}

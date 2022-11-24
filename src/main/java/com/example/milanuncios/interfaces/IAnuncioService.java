package com.example.milanuncios.interfaces;

import java.util.List;

import com.example.milanuncios.model.Anuncio;
import com.example.milanuncios.model.Usuario;



public interface IAnuncioService {
	public List<Anuncio> anuncios_by_categoria(int id_categoria);
	public List<Anuncio> anuncios_by_usuario(String user);
	public void delete_anuncio(int id_anuncio);
	public List<Anuncio> list_all();
	public void save(Anuncio anuncio);
}
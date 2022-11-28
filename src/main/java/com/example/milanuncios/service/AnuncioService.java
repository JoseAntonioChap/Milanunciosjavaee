package com.example.milanuncios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.milanuncios.interfaces.IAnuncioService;
import com.example.milanuncios.model.Anuncio;
import com.example.milanuncios.model.Categoria;
import com.example.milanuncios.model.Usuario;
import com.example.milanuncios.repository.AnuncioRepository;
import com.example.milanuncios.repository.CategoriaRepository;
import com.example.milanuncios.repository.UsuarioRepository;
@Service
public class AnuncioService implements IAnuncioService {

	@Autowired
	AnuncioRepository anunciorepository;
	@Autowired
	CategoriaRepository categoriarepository;
	@Autowired
	UsuarioRepository usuariorepository;
	
	
	@Override
	public List<Anuncio> anuncios_by_categoria(int id_categoria) {
		Categoria categoria = categoriarepository.findById(id_categoria).orElse(null);
		if(categoria == null) {
			return null;
		}else {
		return categoria.getAnuncios();
		}}

	@Override
	public List<Anuncio> anuncios_by_usuario(String user) {
		Usuario usuario = usuariorepository.findById(user).orElse(null);
		if(usuario == null) {
			return null;
		}else {
		return usuario.getAnuncios();
		}
	}

	@Override
	public void delete_anuncio(int id_anuncio) {
		anunciorepository.deleteById(id_anuncio);

	}

	@Override
	public List<Anuncio> list_all() {
		return (List<Anuncio>) anunciorepository.findAll();
	}

	@Override
	public void save(Anuncio anuncio) {
		anunciorepository.save(anuncio);

	}

	@Override
	public Anuncio find_by_id(int id_anuncio) {
		return anunciorepository.findById(id_anuncio).orElse(null);
	}

	@Override
	public List<Anuncio> find_contains_titulo(String titulo) {
		// TODO Auto-generated method stub
		return anunciorepository.findByTituloContainingIgnoreCase(titulo);
	}

}

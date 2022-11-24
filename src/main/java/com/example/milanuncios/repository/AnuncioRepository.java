package com.example.milanuncios.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.milanuncios.model.Anuncio;

public interface AnuncioRepository extends CrudRepository<Anuncio, Integer>{

}

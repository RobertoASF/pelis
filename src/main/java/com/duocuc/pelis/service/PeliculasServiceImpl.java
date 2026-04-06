package com.duocuc.pelis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duocuc.pelis.model.Pelicula;
import com.duocuc.pelis.repository.PeliculasRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculasServiceImpl implements PeliculasService{
    @Autowired
    private PeliculasRepository peliculasRepository;

    @Override
    public List<Pelicula> getAllPeliculas(){
        return peliculasRepository.findAll();
    }

    @Override
    public Optional<Pelicula> getPeliculaById(Long id){
        return peliculasRepository.findById(id);
    }

    @Override
    public Pelicula createPelicula(Pelicula pelicula){
        return peliculasRepository.save(pelicula);
    }

    @Override
    public Pelicula updatePelicula(Long id, Pelicula pelicula){
        if(peliculasRepository.existsById(id)){
            pelicula.setId(id);
            return peliculasRepository.save(pelicula);
        } else {
            return null;
        }
    }
    @Override
    public void deletePelicula(Long id){
        peliculasRepository.deleteById(id);
    }

}

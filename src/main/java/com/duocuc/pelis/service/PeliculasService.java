package com.duocuc.pelis.service;
import com.duocuc.pelis.model.Pelicula;
import java.util.List;
import java.util.Optional;

public interface PeliculasService {
    List<Pelicula> getAllPeliculas();
    Optional<Pelicula> getPeliculaById(Long id);
    Pelicula createPelicula(Pelicula Pelicula);
    Pelicula updatePelicula(Long id,Pelicula pelicula);
    void deletePelicula(Long id);
}
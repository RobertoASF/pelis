package com.duocuc.pelis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.duocuc.pelis.model.Pelicula;

public interface PeliculasRepository extends JpaRepository<Pelicula, Long>{
}

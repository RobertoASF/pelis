package com.duocuc.pelis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.duocuc.pelis.model.Pelicula;
import com.duocuc.pelis.service.PeliculasService;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@RestController
    @RequestMapping("/peliculas")
@CrossOrigin(origins = "*")
public class PeliculaController {
    @Autowired
    private PeliculasService peliculasService;

    @GetMapping
    public List<Pelicula>  getAllPeliculas(){
        return peliculasService.getAllPeliculas();
    }

    @GetMapping("/{id}")
    public EntityModel<Pelicula> getPeliculaById(@PathVariable Long id) {
        Pelicula pelicula = peliculasService.getPeliculaById(id)
                .orElseThrow(() -> new RuntimeException("Pelicula no encontrada"));

        return EntityModel.of(pelicula,
                linkTo(methodOn(PeliculaController.class).getPeliculaById(id)).withSelfRel(),
                linkTo(methodOn(PeliculaController.class).getAllPeliculas()).withRel("lista-peliculas"));
    }

    @PostMapping
    public Pelicula createPelicula(@RequestBody Pelicula pelicula){
        return peliculasService.createPelicula(pelicula);
    }

    @PutMapping("/{id}")
    public Pelicula upodatepelicula(@PathVariable Long id,@RequestBody Pelicula pelicula){
        return peliculasService.updatePelicula(id, pelicula);
    }

    @DeleteMapping("/{id}")
    public void deletePelicula(@PathVariable Long id){
        peliculasService.deletePelicula(id);
    }
}
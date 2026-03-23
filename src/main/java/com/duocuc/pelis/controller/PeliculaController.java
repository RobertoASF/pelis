package com.duocuc.pelis.controller;

import com.duocuc.pelis.model.Pelicula;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PeliculaController {

    private List<Pelicula> peliculas = new ArrayList<>();

    public PeliculaController() {
        peliculas.add(new Pelicula(1, "One Piece: The Movie", 2000, "Junji Shimizu", "Animación/Aventura", "Los Sombrero de Paja se enfrentan al pirata El Drago para encontrar el tesoro del Gran Pirata Woonan."));
        peliculas.add(new Pelicula(2, "One Piece Film: Strong World", 2009, "Munehisa Sakai", "Animación/Aventura", "Shiki el León Dorado secuestra a Nami y los Sombrero de Paja deben rescatarla y salvar el East Blue."));
        peliculas.add(new Pelicula(3, "One Piece Film: Z", 2012, "Tatsuya Nagamine", "Animación/Aventura", "La tripulación se enfrenta al ex Almirante de la Marina Zephyr, quien planea destruir el Nuevo Mundo."));
        peliculas.add(new Pelicula(4, "One Piece Film: Gold", 2016, "Hiroaki Miyamoto", "Animación/Aventura", "Luffy y sus amigos llegan a Gran Tesoro, una ciudad de entretenimiento controlada por el ambicioso Gild Tesoro."));
        peliculas.add(new Pelicula(5, "One Piece: Stampede", 2019, "Takashi Otsuka", "Animación/Aventura", "Piratas de todo el mundo se reúnen en el Festival de los Piratas para encontrar un tesoro perdido de Gol D. Roger."));
        peliculas.add(new Pelicula(6, "One Piece Film: Red", 2022, "Goro Taniguchi", "Animación/Musical", "La tripulación asiste al concierto de Uta, la cantante más famosa del mundo y la hija de Shanks."));
    }

    @GetMapping("/peliculas")
    public List<Pelicula> getAllPeliculas() {
        return peliculas;
    }

    @GetMapping("/peliculas/{id}")
    public Pelicula getPeliculaById(@PathVariable int id) {
        for (Pelicula pelicula : peliculas) {
            if (pelicula.getId() == id) {
                return pelicula;
            }
        }
        return null;
    }
}
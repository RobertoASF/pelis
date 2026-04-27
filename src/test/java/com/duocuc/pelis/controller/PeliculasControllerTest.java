package com.duocuc.pelis.controller;

import com.duocuc.pelis.model.Pelicula;
import com.duocuc.pelis.service.PeliculasService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PeliculaController.class)
class PeliculaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PeliculasService peliculasService;

    @Autowired
    private ObjectMapper objectMapper;

    // 🔹 GET ALL
    @Test
    void getAllPeliculas_deberiaRetornarLista() throws Exception {
        Pelicula pelicula = new Pelicula();
        pelicula.setId(1L);
        pelicula.setTitle("Inception");

        when(peliculasService.getAllPeliculas())
                .thenReturn(Arrays.asList(pelicula));

        mockMvc.perform(get("/peliculas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].titulo").value("Inception"));
    }

    // 🔹 GET BY ID (OK)
    @Test
    void getPeliculaById_cuandoExiste() throws Exception {
        Pelicula pelicula = new Pelicula();
        pelicula.setId(1L);
        pelicula.setTitle("Inception");

        when(peliculasService.getPeliculaById(1L))
                .thenReturn(Optional.of(pelicula));

        mockMvc.perform(get("/peliculas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.titulo").value("Inception"))
                .andExpect(jsonPath("$._links.self").exists())
                .andExpect(jsonPath("$._links['lista-peliculas']").exists());
    }

    @Test
    void getPeliculaById_cuandoNoExiste() throws Exception {
        when(peliculasService.getPeliculaById(1L))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/peliculas/1"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void createPelicula_deberiaCrear() throws Exception {
        Pelicula pelicula = new Pelicula();
        pelicula.setId(1L);
        pelicula.setTitle("Inception");

        when(peliculasService.createPelicula(any(Pelicula.class)))
                .thenReturn(pelicula);

        mockMvc.perform(post("/peliculas")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(pelicula)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.titulo").value("Inception"));
    }

    @Test
    void updatePelicula_deberiaActualizar() throws Exception {
        Pelicula pelicula = new Pelicula();
        pelicula.setId(1L);
        pelicula.setTitle("Interstellar");

        when(peliculasService.updatePelicula(eq(1L), any(Pelicula.class)))
                .thenReturn(pelicula);

        mockMvc.perform(put("/peliculas/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(pelicula)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Interstellar"));
    }

    @Test
    void deletePelicula_deberiaEliminar() throws Exception {
        doNothing().when(peliculasService).deletePelicula(1L);

        mockMvc.perform(delete("/peliculas/1"))
                .andExpect(status().isOk());

        verify(peliculasService, times(1)).deletePelicula(1L);
    }
}

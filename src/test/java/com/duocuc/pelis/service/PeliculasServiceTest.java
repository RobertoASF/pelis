package com.duocuc.pelis.service;

import com.duocuc.pelis.model.Pelicula;
import com.duocuc.pelis.repository.PeliculasRepository;
import com.duocuc.pelis.service.PeliculasServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PeliculasServiceTest {

    @Mock
    private PeliculasRepository peliculaRepository;

    @InjectMocks
    private PeliculasServiceImpl peliculasService;

    private Pelicula pelicula;

    @BeforeEach
    void setUp() {
        pelicula = new Pelicula();
        pelicula.setId(1L);
        pelicula.setTitle("Inception");
        pelicula.setGender("Sci-Fi");
    }

    @Test
    void getAllPeliculas_deberiaRetornarLista() {
        when(peliculaRepository.findAll()).thenReturn(Arrays.asList(pelicula));

        List<Pelicula> resultado = peliculasService.getAllPeliculas();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(peliculaRepository, times(1)).findAll();
    }

    @Test
    void getPeliculaById_cuandoExiste() {
        when(peliculaRepository.findById(1L)).thenReturn(Optional.of(pelicula));

        Optional<Pelicula> resultado = peliculasService.getPeliculaById(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Inception", resultado.get().getTitle());
    }

    @Test
    void getPeliculaById_cuandoNoExiste() {
        when(peliculaRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Pelicula> resultado = peliculasService.getPeliculaById(1L);

        assertFalse(resultado.isPresent());
    }

    @Test
    void createPelicula_deberiaGuardar() {
        when(peliculaRepository.save(pelicula)).thenReturn(pelicula);

        Pelicula resultado = peliculasService.createPelicula(pelicula);

        assertNotNull(resultado);
        assertEquals("Inception", resultado.getTitle());
        verify(peliculaRepository).save(pelicula);
    }

    @Test
    void updatePelicula_cuandoExiste() {
        Pelicula nueva = new Pelicula();
        nueva.setTitle("Interstellar");
        nueva.setGender("Sci-Fi");

        when(peliculaRepository.findById(1L)).thenReturn(Optional.of(pelicula));
        when(peliculaRepository.save(any(Pelicula.class))).thenReturn(pelicula);

        Pelicula resultado = peliculasService.updatePelicula(1L, nueva);

        assertNotNull(resultado);
        verify(peliculaRepository).save(any(Pelicula.class));
    }

    @Test
    void updatePelicula_cuandoNoExiste() {
        when(peliculaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            peliculasService.updatePelicula(1L, pelicula);
        });
    }

    @Test
    void deletePelicula_deberiaEliminar() {
        doNothing().when(peliculaRepository).deleteById(1L);

        peliculasService.deletePelicula(1L);

        verify(peliculaRepository, times(1)).deleteById(1L);
    }
}

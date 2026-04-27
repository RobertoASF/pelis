package com.duocuc.pelis.repository;

import com.duocuc.pelis.model.Pelicula;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb;NON_KEYWORDS=YEAR;DB_CLOSE_DELAY=-1",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
        "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
public class PeliculasRepositoryTest {

    @Autowired
    private PeliculasRepository peliculasRepository;

    private Pelicula peliculaTest;

    @BeforeEach
    void setUp() {
        peliculaTest = new Pelicula();
        peliculaTest.setTitle("One Piece Film: Red");
        peliculaTest.setYear(2022);
        peliculaTest.setDirector("Goro Taniguchi");
        peliculaTest.setGender("Anime");
        peliculaTest.setSynopsis("Uta canta y es la villana.");
    }

    @Test
    public void testGuardarPelicula() {
        Pelicula guardada = peliculasRepository.save(peliculaTest);

        assertNotNull(guardada.getId(), "El ID no debería ser nulo después de guardar");
        assertEquals("One Piece Film: Red", guardada.getTitle());
    }

    @Test
    public void testBuscarPorId() {
        // Preparación: Guardamos primero una película
        Pelicula guardada = peliculasRepository.save(peliculaTest);

        // Ejecución: La buscamos por su ID autogenerado
        Optional<Pelicula> encontrada = peliculasRepository.findById(guardada.getId());

        // Verificación
        assertTrue(encontrada.isPresent(), "La película debería existir en la base de datos");
        assertEquals("Goro Taniguchi", encontrada.get().getDirector());
    }

    @Test
    public void testListarTodasLasPeliculas() {
        // Preparación: Guardamos dos películas
        peliculasRepository.save(peliculaTest);

        Pelicula otraPeli = new Pelicula();
        otraPeli.setTitle("The Matrix");
        otraPeli.setYear(1999);
        otraPeli.setDirector("Wachowskis");
        peliculasRepository.save(otraPeli);

        // Ejecución
        List<Pelicula> lista = peliculasRepository.findAll();

        // Verificación
        assertFalse(lista.isEmpty(), "La lista de películas no debería estar vacía");
        assertTrue(lista.size() >= 2, "Debería haber al menos 2 películas en la base de datos");
    }

    @Test
    public void testEliminarPelicula() {
        // Preparación: Guardar una película para luego borrarla
        Pelicula guardada = peliculasRepository.save(peliculaTest);
        Long id = guardada.getId();

        // Ejecución: Eliminarla
        peliculasRepository.deleteById(id);

        // Verificación: Intentar buscarla y asegurar que ya no existe
        Optional<Pelicula> buscada = peliculasRepository.findById(id);
        assertFalse(buscada.isPresent(), "La película no debería existir después de ser eliminada");
    }
}
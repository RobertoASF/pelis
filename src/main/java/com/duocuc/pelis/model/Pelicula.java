
package com.duocuc.pelis.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.hateoas.RepresentationModel;


@Entity
@Table(name = "pelicula")
@JsonPropertyOrder({ "id", "titulo", "año", "director", "genero", "sinopsis" })
public class Pelicula  extends RepresentationModel<Pelicula> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //Como costumbre y para v itar problemas los atributos los dejé con nombres en ingés pero la respuesta del json cumple con los nombres solicitadops en esta entrega

    @NotBlank(message = "No puede ingresar un titulo vacio")
    @Column(name = "title")
    @JsonProperty("titulo")
    private String title;

    @NotBlank(message = "Debe ingresar el año de la pelicula")
    @Column(name = "year")
    @JsonProperty("año")
    private int year;

    @NotBlank(message = "Debe ingresar el nombre del director")
    @Column(name = "director")
    @JsonProperty("director")
    private String director;

    @Column(name = "gender")
    @JsonProperty("genero")
    private String gender;

    @Column(name = "synopsis")
    @JsonProperty("sinopsis")
    private String synopsis;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public String getGender() {
        return gender;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
}
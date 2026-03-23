package com.duocuc.pelis.model;


public class Pelicula {
    private int id;
    private String titulo;
    private int ano;
    private String director;
    private String genero;
    private String sinopsis;

    public Pelicula(int id, String titulo, int ano, String director, String genero, String sinopsis) {
        this.id = id;
        this.titulo = titulo;
        this.ano = ano;
        this.director = director;
        this.genero = genero;
        this.sinopsis = sinopsis;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public int getAno() { return ano; }
    public String getDirector() { return director; }
    public String getGenero() { return genero; }
    public String getSinopsis() { return sinopsis; }
}
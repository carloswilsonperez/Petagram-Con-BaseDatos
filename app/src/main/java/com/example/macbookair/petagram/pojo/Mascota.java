package com.example.macbookair.petagram.pojo;

/**
 * Created by MacBookAir on 15/08/16.
 */
public class Mascota {
    private String Nombre;
    private int Id;
    private int Foto;
    private int Likes;


    public Mascota (int foto, String nombre, int likes) {
        this.Foto = foto;
        this.Nombre = nombre;
        this.Likes = likes;

    }

    public Mascota() {
        //Constructor vacio requerido para el llenado de datos desde la base de datos
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public int getFoto() {
        return Foto;
    }

    public void setFoto(int foto) {
        this.Foto = foto;
    }

    public int getLikes() {
        return Likes;
    }

    public void setLikes(int likes) {
        this.Likes = likes;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}

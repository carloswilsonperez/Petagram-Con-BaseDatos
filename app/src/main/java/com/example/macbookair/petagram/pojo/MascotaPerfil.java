package com.example.macbookair.petagram.pojo;

/**
 * Created by MacBookAir on 22/08/16.
 */
public class MascotaPerfil {
    private int foto;
    private int likes;


    public MascotaPerfil (int foto, int likes) {
        this.foto = foto;
        this.likes = likes;

    }


    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}

package com.miguelcr.a02_recyclerview;

/**
 * Created by miguelcampos on 30/11/16.
 */

public class FootballPlayer {
    private String nombre;
    private int edad;
    private String urlPhoto;

    public FootballPlayer(String nombre, int edad, String urlPhoto) {
        this.nombre = nombre;
        this.edad = edad;
        this.urlPhoto = urlPhoto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }
}

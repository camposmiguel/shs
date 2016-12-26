package com.miguelcr.a01_databasegreendao.model;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "ALUMNO_DB".
 */
@Entity
public class AlumnoDB {

    @Id(autoincrement = true)
    private Long id;

    @NotNull
    private String nombre;
    private Integer edad;
    private String apellidos;
    private Float notaMedia;
    private long claseId;

    @Generated(hash = 329380564)
    public AlumnoDB() {
    }

    public AlumnoDB(Long id) {
        this.id = id;
    }

    @Generated(hash = 1440132751)
    public AlumnoDB(Long id, @NotNull String nombre, Integer edad, String apellidos, Float notaMedia, long claseId) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.apellidos = apellidos;
        this.notaMedia = notaMedia;
        this.claseId = claseId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    public String getNombre() {
        return nombre;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setNombre(@NotNull String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Float getNotaMedia() {
        return notaMedia;
    }

    public void setNotaMedia(Float notaMedia) {
        this.notaMedia = notaMedia;
    }

    public long getClaseId() {
        return claseId;
    }

    public void setClaseId(long claseId) {
        this.claseId = claseId;
    }

    @Override
    public String toString() {
        return getNombre();
    }

}

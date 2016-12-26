
package com.miguelcr.a01_login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RespuestaNota {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("titulo")
    @Expose
    private String titulo;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("color")
    @Expose
    private String color;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RespuestaNota() {
    }

    /**
     * 
     * @param id
     * @param titulo
     * @param color
     * @param userId
     * @param descripcion
     */
    public RespuestaNota(String id, String titulo, String descripcion, String userId, String color) {
        super();
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.userId = userId;
        this.color = color;
    }

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * 
     * @param titulo
     *     The titulo
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * 
     * @return
     *     The descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * 
     * @param descripcion
     *     The descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * 
     * @return
     *     The userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 
     * @param userId
     *     The user_id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 
     * @return
     *     The color
     */
    public String getColor() {
        return color;
    }

    /**
     * 
     * @param color
     *     The color
     */
    public void setColor(String color) {
        this.color = color;
    }

}

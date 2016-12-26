
package com.miguelcr.shskeep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RespuestaRegistro {

    @SerializedName("mensaje")
    @Expose
    private String mensaje;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RespuestaRegistro() {
    }

    /**
     * 
     * @param mensaje
     */
    public RespuestaRegistro(String mensaje) {
        super();
        this.mensaje = mensaje;
    }

    /**
     * 
     * @return
     *     The mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * 
     * @param mensaje
     *     The mensaje
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}

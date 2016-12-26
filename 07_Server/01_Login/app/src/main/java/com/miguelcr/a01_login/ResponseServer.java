
package com.miguelcr.a01_login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseServer {

    @SerializedName("mensaje")
    @Expose
    private String mensaje;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ResponseServer() {
    }

    /**
     * 
     * @param mensaje
     */
    public ResponseServer(String mensaje) {
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


package com.miguelcr.shskeep;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RespuestaNotas {

    @SerializedName("notas")
    @Expose
    private List<Nota> notas = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RespuestaNotas() {
    }

    /**
     * 
     * @param notas
     */
    public RespuestaNotas(List<Nota> notas) {
        super();
        this.notas = notas;
    }

    /**
     * 
     * @return
     *     The notas
     */
    public List<Nota> getNotas() {
        return notas;
    }

    /**
     * 
     * @param notas
     *     The notas
     */
    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }

}

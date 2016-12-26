package com.miguelcr.shskeep;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by miguelcampos on 7/12/16.
 */

public interface ApiKeepInterface {

    @FormUrlEncoded
    @POST("register")
    Call<RespuestaRegistro> doRegister(@Field("nombre") String nombre,
                                       @Field("email") String emailUsuario,
                                       @Field("password") String passUsuario);
    @FormUrlEncoded
    @POST("login")
    Call<RespuestaRegistro> doLogin(@Field("email") String emailUsuario,
                                       @Field("password") String passUsuario);

    @FormUrlEncoded
    @POST("addnota")
    Call<RespuestaNotas> addNota(@Field("titulo") String tituloNota,
                                    @Field("descripcion") String descripcionNota,
                                    @Field("userid") String userNota);

    @GET("notas")
    Call<RespuestaNotas> listadoNotas(@Query("userid") String userNota);
}

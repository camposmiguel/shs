package com.miguelcr.a01_login;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by miguelcampos on 20/7/16.
 */
public interface InterfaceApi {

        @GET("domain")
        Call<ResponseServer> doActivationCode(@Query("appkey") String appkey);

        @FormUrlEncoded
        @POST("register")
        Call<ResponseServer> userRegistration(
                @Field("email") String email,
                @Field("nombre") String nombre,
                @Field("password") String password
        );

}

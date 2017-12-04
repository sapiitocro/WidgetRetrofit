package net.simplifiedlearning.retrofitexample.Services;

import net.simplifiedlearning.retrofitexample.Models.Incidencia;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Belal on 10/2/2017.
 */

public interface ApiService {

    String API_BASE_URL = " https://almacen-api-mrpapita.c9users.io/";

    @GET("api/v1/incidencia")
    Call<List<Incidencia>> getIncidencia();

    @FormUrlEncoded
    @POST("api/v1/incidencia")
    Call<ResponseMessage>
    createIncidencia(@Field("name")String name,
                   @Field("desc") String desc);

}

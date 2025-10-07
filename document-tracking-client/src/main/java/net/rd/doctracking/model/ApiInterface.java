package net.rd.doctracking.model;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

interface ApiInterface {

    @GET("/api/v1/team")
    Call<TeamModel> listAllTeams();

    @GET("/api/v1/team")
    Call<List<PersonModel>> listAllPersons();

    @POST("/api/v1/person")
    Call<PersonModel> createUser(@Body PersonModel personModel);

}


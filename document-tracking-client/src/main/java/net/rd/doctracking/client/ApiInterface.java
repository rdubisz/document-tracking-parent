package net.rd.doctracking.client;

import net.rd.doctracking.model.*;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.*;

import java.time.LocalDateTime;
import java.util.List;

interface ApiInterface {

    @GET("/api/v1/team")
    Call<List<TeamModel>> listAllTeams();

    @POST("/api/v1/team")
    Call<TeamModel> createTeam(@Body final TeamModel teamModel);

    @DELETE("/api/v1/team/{id}")
    Call<Void> deleteTeam(@Path("id") final Long id);

    @GET("/api/v1/person")
    Call<List<PersonModel>> listAllPersons();

    @GET("/api/v1/person/inactive")
    Call<InactivePersonsQueryModel> listInactivePersons(
            @Query("startTime")  final LocalDateTime startTime,
            @Query("endTime")  final LocalDateTime endTime);

    @POST("/api/v1/person")
    Call<PersonModel> createPerson(@Body final PersonModel personModel);

    @GET("/api/v1/document")
    Call<List<DocumentModel>> listAllDocuments();

    @GET("/api/v1/document/{id}/stats/words-frequency")
    Call<DocumentWordsFrequencyModel> documentWordsFrequency(@Path("id") long id);

}

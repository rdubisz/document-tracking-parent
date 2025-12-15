package net.rd.doctracking.client;

import net.rd.doctracking.model.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.time.LocalDateTime;
import java.util.List;

public interface ApiInterface {

    // -- Team --

    @GET("/api/v1/team")
    Call<List<TeamModel>> getAllTeams();

    @GET("/api/v1/team/{id}")
    Call<TeamModel> getOneTeam(@Path("id") final Long id);

    @POST("/api/v1/team")
    Call<TeamModel> createTeam(@Body final TeamModel teamModel);

    @PUT("/api/v1/team/{id}")
    Call<TeamModel> updateOrCreateTeam(
            @Body final TeamModel teamModel,
            @Path("id") final Long id);

    @DELETE("/api/v1/team/{id}")
    Call<Void> deleteTeam(@Path("id") final Long id);

    @GET("/api/v1/team/{teamId}/person")
    Call<List<PersonModel>> getTeamPersons(@Path("teamId") final Long teamId);

    // -- Person --

    @GET("/api/v1/person")
    Call<List<PersonModel>> getAllPersons();

    @GET("/api/v1/person/{id}")
    Call<PersonModel> getOnePerson(@Path("id") final Long id);

    @POST("/api/v1/person")
    Call<PersonModel> createPerson(@Body final PersonModel personModel);

    @PUT("/api/v1/person/{id}")
    Call<PersonModel> updateOrCreatePerson(
            @Body final PersonModel personModel,
            @Path("id") final Long id);

    @DELETE("/api/v1/person/{id}")
    Call<Void> deletePerson(@Path("id") final Long id);

    @GET("/api/v1/person/inactive")
    Call<InactivePersonsQueryModel> inactivePersonsQuery(
            @Query("startTime")  final LocalDateTime startTime,
            @Query("endTime")  final LocalDateTime endTime);

    @GET("/api/v1/person/{id}/document")
    Call<List<DocumentModel>> getPersonDocuments(final Long id);

    // -- Document --

    @GET("/api/v1/document")
    Call<List<DocumentModel>> getAllDocuments();

    @GET("/api/v1/document/{id}")
    Call<DocumentModel> getOneDocument(@Path("id") final Long id);

    @POST("/api/v1/document")
    Call<DocumentModel> createDocument(@Body final DocumentModel documentModel);

    @PUT("/api/v1/document/{id}")
    Call<DocumentModel> updateOrCreateDocument(
            @Body final DocumentModel documentModel,
            @Path("id") final Long id);

    @DELETE("/api/v1/document/{id}")
    Call<Void> deleteDocument(@Path("id") final Long id);

    @GET("/api/v1/document/{id}/stats/words-frequency")
    Call<DocumentWordsFrequencyModel> documentStatsWordsFrequency(@Path("id") final Long id);

    @GET("/api/v1/document/{id}/stats/synonyms")
    Call<DocumentLongestWordSynonymsModel> documentStatsLongestWordSynonyms(@Path("id") final Long id);

}

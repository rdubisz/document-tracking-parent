package net.rd.doctracking.model;

import retrofit2.Retrofit;

public class ApiClient {

    final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .build();

    final ApiInterface service = retrofit.create(ApiInterface.class);
}

package net.rd.doctracking.client;

import retrofit2.Retrofit;

public class ApiClient {

    final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .build();

    final ApiInterface apiInterface = retrofit.create(ApiInterface.class);
}

package net.rd.doctracking.client;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ApiClient {

    final Retrofit retrofit;
    final ApiInterface apiIf;

    public ApiClient(String url) {
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(converterFactory())
                .client(httpClient())
                .build();
        apiIf = retrofit.create(ApiInterface.class);
    }

    private static OkHttpClient httpClient() {
        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(Level.BASIC);

        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
    }

    protected Converter.Factory converterFactory() {
        //return GsonConverterFactory.create();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(JsonParser.Feature.INCLUDE_SOURCE_IN_LOCATION);
        objectMapper.registerModule(new JavaTimeModule());
        return JacksonConverterFactory.create(objectMapper);
    }
}

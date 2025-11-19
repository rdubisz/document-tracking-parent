package net.rd.doctracking.client;

import net.rd.doctracking.model.PersonModel;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Response;


import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;


class ApiClientTest {

    private final ApiClient tested = new ApiClient();

    @Test
    void testToString() throws IOException {
        Call<List<PersonModel>> allPersonsCall = tested.apiInterface.listAllPersons();
        Response<List<PersonModel>> allPersonsResponse = allPersonsCall.execute();
        List<PersonModel> allPersonsList = allPersonsResponse.body();
        assertNotNull(allPersonsList);
    }
}

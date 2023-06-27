package com.evelyn.chichande.apirest.IO;

import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ActorService {

    String API_ROUTE="/v1/actors";

    @GET(API_ROUTE)
    Call<List<Actor>> getList();

}

package com.example.beerapplication.Beerstuff;

import com.example.beerapplication.Beerstuff.Beer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface JsonPlaceHolderApi {
    @GET("beers")
    Call<List<Beer>> getListBeer();
}

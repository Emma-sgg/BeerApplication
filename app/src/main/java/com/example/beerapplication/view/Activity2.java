package com.example.beerapplication.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.beerapplication.Beerstuff.Beer;
import com.example.beerapplication.Beerstuff.JsonPlaceHolderApi;
import com.example.beerapplication.Beerstuff.MyAdapter;
import com.example.beerapplication.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Activity2 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        textViewResult = findViewById(R.id.my_text_view);

      Retrofit retrofit = new Retrofit.Builder() //création du retrofit
                .baseUrl("https://api.punkapi.com/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Beer>> call = jsonPlaceHolderApi.getListBeer();
        call.enqueue(new Callback<List<Beer>>() {
            @Override
           public void onResponse(Call<List<Beer>> call, Response<List<Beer>> response) {
                if (response.isSuccessful()) {
                    List<Beer> beers = response.body();
                    showList(beers);
                }
                else{
                    textViewResult.setText("Code" + response.code());
                    return;
                }
                List<Beer> beers = response.body();
            }

            @Override
            public void onFailure(Call<List<Beer>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });

    }


    public void showList(List<Beer> beerList){
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        mAdapter = new MyAdapter(beerList);//définit mon adapteur
        recyclerView.setAdapter(mAdapter);
    }

}

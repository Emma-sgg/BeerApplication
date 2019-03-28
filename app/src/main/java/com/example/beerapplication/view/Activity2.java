package com.example.beerapplication.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beerapplication.Beerstuff.Beer;
import com.example.beerapplication.Beerstuff.JsonPlaceHolderApi;
import com.example.beerapplication.Beerstuff.MyAdapter;
import com.example.beerapplication.R;
import com.google.gson.Gson;

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
    int cacheSize = 10 * 1024 *1024;

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

        //DONNEÉE DANS CACHE

        sharedPreferences = getBaseContext().getSharedPreferences(PREFS, MODE_PRIVATE);

            sharedPreferences
                    .edit()
                    .putInt(PREFS_AGE, 24)
                    .putString(PREFS_NAME, "beers")
                    .apply();

    }

       public void startThird(Beer currentBeer) {

        final Intent Intent3 = new Intent(this, DetailActivity.class);

        Gson gson = new Gson();
        String JsoncurrentBeer = gson.toJson(currentBeer);

        Intent3.putExtra("key",JsoncurrentBeer);

        recyclerView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(Intent3);
            }
        });
        startActivity(Intent3);
    }


    public void showList(List<Beer> beerList){
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        mAdapter = new MyAdapter(beerList);//définit mon adapteur
        recyclerView.setAdapter(mAdapter);
    }

    private static final String PREFS = "PREFS";
    private static final String PREFS_AGE = "PREFS_AGE";
    private static final String PREFS_NAME = "PREFS_NAME";
    SharedPreferences sharedPreferences;




}

package com.example.beerapplication.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import com.example.beerapplication.Beerstuff.MyAdapter;
import com.example.beerapplication.Beerstuff.Beer;

import com.example.beerapplication.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {


    private ImageView icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        icon = findViewById(R.id.detail_image_beer_view);

        String beerJson = getIntent().getStringExtra("CLE");
        Gson gson = new Gson();
        /*Beers beers = gson.fromJson(beerJson, Beers.class);

        Picasso.with(getBaseContext())
                .load(beers.getImage_url())
                .into(icon);*/
    }
}

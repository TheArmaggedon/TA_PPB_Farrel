package com.example.ta_pbb_farrel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class NewsDetail extends AppCompatActivity {
    TextView newsTitleView, newsSourceView, newsDescView;
    ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        newsTitleView = findViewById(R.id.newsTitle);
        newsSourceView = findViewById(R.id.newsSource);
        imageView = findViewById(R.id.imageView);
        newsDescView = findViewById(R.id.newsDesc);
        Intent intent = getIntent();
        String newsTitle = intent.getStringExtra("title");
        String newsSource = intent.getStringExtra("source");
        String newsContent = intent.getStringExtra("desc");
        String imageUrl = intent.getStringExtra("imageUrl");
        String url = intent.getStringExtra("url");



        newsTitleView.setText(newsTitle);
        newsSourceView.setText(newsSource);
        newsDescView.setText(newsContent);

        Picasso.get().load(imageUrl).resize(1280, 720).into(imageView);
    }
}
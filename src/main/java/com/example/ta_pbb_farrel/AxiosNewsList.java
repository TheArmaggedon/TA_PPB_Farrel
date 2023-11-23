package com.example.ta_pbb_farrel;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ta_pbb_farrel.model.Articles;
import com.example.ta_pbb_farrel.model.Headlines;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AxiosNewsList extends AppCompatActivity {
    RecyclerView newsRecyclerView;
    NewsAdapter adapter;
    BottomNavigationView bottomNavigationView;
    final String API_KEY = "0b96b0d8b6a746eb86f169757eb43781";

    List<Articles> articles = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.axios_news_list);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        newsRecyclerView = findViewById(R.id.newsRecyclerView);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NewsAdapter(AxiosNewsList.this, articles);
        newsRecyclerView.setAdapter(adapter);
        String sources = "axios";
        retrieveJSON(sources, API_KEY);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.nav_politico_list) {
                    startActivity(new Intent(AxiosNewsList.this, PoliticoNewsList.class));
                } else if (itemId == R.id.nav_axios_list) {
                    startActivity(new Intent(AxiosNewsList.this, AxiosNewsList.class));
                } else if (itemId == R.id.nav_about_author) {
                    startActivity(new Intent(AxiosNewsList.this, AboutAuthor.class));
                } else {
                    throw new IllegalStateException("Unexpected value: " + itemId);
                }
                return true;
            }
        });


    }

    public void retrieveJSON(String source, String apiKey) {
        Call<Headlines> call = ApiClient.getInstance().getApi().getHeadlines(source, apiKey);
        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                if (response.isSuccessful() && response.body().getArticles() != null) {
                    articles.clear();
                    articles.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();


                }
            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {
                Toast.makeText(AxiosNewsList.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
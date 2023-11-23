package com.example.ta_pbb_farrel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.ta_pbb_farrel.model.Articles;

import com.squareup.picasso.Picasso;

import java.util.List;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    Context context;
    List<Articles> articles;

    public NewsAdapter(Context context, List<Articles> articles) {
        this.context = context;
        this.articles = articles;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Articles articles1 =  articles.get(position);
        holder.newsTitle.setText(articles1.getTitle());
        holder.newsSource.setText(articles1.getSource().getName());
        holder.newsDate.setText(articles1.getPublishedAt());
        String imageUrl = articles1.getUrlToImage();

        Picasso.get().load(imageUrl).resize(1280,720).into(holder.imageView);

        holder.cardView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = articles1.getTitle();
                String source = articles1.getSource().getName();
                String imageUrl = articles1.getUrlToImage();
                String desc = articles1.getDescription();

                Intent intent = new Intent(context, NewsDetail.class);
                intent.putExtra("title", title);
                intent.putExtra("source", source);
                intent.putExtra("desc", desc);
                intent.putExtra("imageUrl", imageUrl);


                context.startActivity(intent);
            }

        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView newsTitle, newsSource, newsDate;
        ImageView imageView;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            newsTitle = itemView.findViewById(R.id.newsTitle);
            newsSource = itemView.findViewById(R.id.newsSource);
            newsDate = itemView.findViewById(R.id.newsDate);
            imageView = itemView.findViewById(R.id.imageView);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }
}

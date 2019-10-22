package com.sarker.samplemvvmwithroomproject.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sarker.samplemvvmwithroomproject.R;
import com.sarker.samplemvvmwithroomproject.requests.responses.NewsResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    Context context;
    ArrayList<NewsResponse.ArticlesBean> articles;

    public NewsAdapter(Context context, ArrayList<NewsResponse.ArticlesBean> articles) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_item, parent, false);
        return new  NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.NewsViewHolder holder, int position) {
        holder.tvName.setText(articles.get(position).getTitle().toString());
        holder.tvDesCription.setText(articles.get(position).toString());
        Picasso.get().load(articles.get(position).getUrlToImage()).into(holder.ivNews);
    }

    @Override
    public int getItemCount()
    {
        Log.i("", "getItemCount:................. "+articles.size());
        return articles.size();

    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{

        TextView tvName;
        TextView tvDesCription;
        ImageView ivNews;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvDesCription = itemView.findViewById(R.id.tvDesCription);
            ivNews = itemView.findViewById(R.id.ivNews);

        }
    }
}

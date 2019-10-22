package com.sarker.samplemvvmwithroomproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sarker.samplemvvmwithroomproject.R;
import com.sarker.samplemvvmwithroomproject.models.NicePlace;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerAdpter extends RecyclerView.Adapter<RecyclerAdpter.MyViewHolder> {

    private Context context;
    private List<NicePlace> nicePlaces;
   // private List<NewsArticle> newsArticles;

    public RecyclerAdpter(Context context, List<NicePlace> nicePlaces) {
        this.context = context;
        this.nicePlaces = nicePlaces;
    }


//    public RecyclerAdpter(Context context, List<NewsArticle> newsArticles) {
//        this.context = context;
//        this.newsArticles = newsArticles;
//    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Set the name of the 'NicePlace'
        holder.mName.setText(nicePlaces.get(position).getTitle());

         //Set the image
        RequestOptions defaultOptions = new RequestOptions()
                .error(R.drawable.ic_launcher_background);
        Glide.with(context)
                .setDefaultRequestOptions(defaultOptions)
                .load(nicePlaces.get(position).getImageUrl())
                .into(holder.mImage);

        //holder.mName.setText(newsArticles.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return nicePlaces.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView mImage;
        private TextView mName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.image);
            mName = itemView.findViewById(R.id.image_name);
        }
    }
}

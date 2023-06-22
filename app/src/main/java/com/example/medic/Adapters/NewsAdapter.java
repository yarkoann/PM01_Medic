package com.example.medic.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.medic.Models.News;
import com.example.medic.R;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    private Context newsContext;
    private List<News> newsData;

    public NewsAdapter(Context newsContext, List<News> newsData) {
        this.newsContext = newsContext;
        this.newsData = newsData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(newsContext);
        v = inflater.inflate(R.layout.news_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(newsData.get(position).getName());
        holder.description.setText(newsData.get(position).getDescription());
        holder.price.setText(newsData.get(position).getPrice());

        float density = newsContext.getResources().getDisplayMetrics().density;
        int widthDp = 150;   // значение в dp
        int heightDp = 152; // значение в dp

        int widthPx = (int) (widthDp * density + 0.5f);
        int heightPx = (int) (heightDp * density + 0.5f);

        Glide.with(newsContext)
                .load(newsData.get(position).getImage())
                .optionalCenterCrop()
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return newsData.size(); // Возвращаем количество элементов в списке новостей
    }

    // расширяем стандартный RecyclerView собстенный MyViewHolder
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView description;
        TextView price;
        ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.news_title);
            description = itemView.findViewById(R.id.news_desc);
            price = itemView.findViewById(R.id.news_price);
            img = itemView.findViewById(R.id.news_img);
        }
    }
}

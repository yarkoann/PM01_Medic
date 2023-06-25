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
import com.example.medic.Models.Catalog;
import com.example.medic.Models.News;
import com.example.medic.R;

import java.util.List;

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.MyViewHolder> {

    private Context catalogContext;
    private List<Catalog> catalogData;

    public CatalogAdapter(Context catalogContext, List<Catalog> newsData) {
        this.catalogContext = catalogContext;
        this.catalogData = newsData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(catalogContext);
        v = inflater.inflate(R.layout.catalog_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(catalogData.get(position).getName());
        holder.time.setText(catalogData.get(position).getTime());
        holder.price.setText(catalogData.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return catalogData.size(); // Возвращаем количество элементов в списке новостей
    }

    // расширяем стандартный RecyclerView собстенный MyViewHolder
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView time;
        TextView price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.catalog_title);
            time = itemView.findViewById(R.id.catalog_time);
            price = itemView.findViewById(R.id.catalog_price);
        }
    }
}

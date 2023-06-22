package com.example.medic.MainPage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import com.example.medic.Adapters.NewsAdapter;
import com.example.medic.MedicApi;
import com.example.medic.Models.News;
import com.example.medic.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Analys extends Fragment {

    RecyclerView viewNews;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_analys, container, false);
        viewNews = rootView.findViewById(R.id.viewNews);
        viewNews.setLayoutManager(new LinearLayoutManager(getContext()));
        setNews();
        return rootView;

    }

    private void loadAdapterNews(List<News> news) {
        NewsAdapter adapter = new NewsAdapter(getContext(), news);
        viewNews.setAdapter(adapter);

    }

    private void setNews() {
        MedicApi api = MedicApi.retrofit.create(MedicApi.class);
        Call<List<News>> call = api.getNews();
        call.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                List<News> news = response.body();
                if (response.isSuccessful()) {
                    assert news != null;
                    Log.d("JJJJ", "Проверка: "+news.get(1).getName());
                    loadAdapterNews(news);
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {

            }
        });
    }
}
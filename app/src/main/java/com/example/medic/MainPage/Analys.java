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

import com.example.medic.Adapters.CatalogAdapter;
import com.example.medic.Adapters.NewsAdapter;
import com.example.medic.AnalogApi;
import com.example.medic.MedicApi;
import com.example.medic.Models.Catalog;
import com.example.medic.Models.News;
import com.example.medic.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Analys extends Fragment {

    RecyclerView viewNews, viewCatalog;
    private static String CATALOG_URL = "https://run.mocky.io/v3/";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_analys, container, false);
        viewNews = rootView.findViewById(R.id.viewNews);
        viewCatalog = rootView.findViewById(R.id.viewCatalog);
        viewNews.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        viewCatalog.setLayoutManager(new LinearLayoutManager(getContext()));

        getNewsAPI();
        getCatalogAPI();

        return rootView;

    }

    private void loadAdapterNews(List<News> news) {
        NewsAdapter adapter = new NewsAdapter(getContext(), news);
        viewNews.setAdapter(adapter);
    }
    private void loadAdapterCatalog(List<Catalog> catalog) {
        Log.d("JJJJ", "Проверка2: "+catalog.get(1).getName());
        CatalogAdapter adapter = new CatalogAdapter(getContext(), catalog);
        Log.d("JJJJ", "Проверка3: "+catalog.get(1).getName());
        viewCatalog.setAdapter(adapter);
    }

    private void getNewsAPI() {
        AnalogApi api = AnalogApi.retrofit.create(AnalogApi.class);


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
    private void getCatalogAPI() {
        AnalogApi api = AnalogApi.retrofit.create(AnalogApi.class);
        Call<List<Catalog>> call = api.getCatalog();
        call.enqueue(new Callback<List<Catalog>>() {
            @Override
            public void onResponse(Call<List<Catalog>> call, Response<List<Catalog>> response) {
                List<Catalog> catalog = response.body();
                if (response.isSuccessful()) {
                    assert catalog != null;
                    Log.d("JJJJ", "Проверка: "+catalog.get(1).getName());
                    loadAdapterCatalog(catalog);
                }
            }

            @Override
            public void onFailure(Call<List<Catalog>> call, Throwable t) {

            }
        });
    }
}
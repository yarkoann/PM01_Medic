package com.example.medic.MainPage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.medic.MedicApi;
import com.example.medic.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Analys extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setNews();
        return inflater.inflate(R.layout.fragment_analys, container, false);
    }

    private void setNews() {
        MedicApi api = MedicApi.retrofit.create(MedicApi.class);
        Call<List<NewsModel>> call = api.getNews();
        call.enqueue(new Callback<List<NewsModel>>() {
            @Override
            public void onResponse(Call<List<NewsModel>> call, Response<List<NewsModel>> response) {
                List<NewsModel> news = response.body();
                if (response.isSuccessful()) {
                    assert news != null;
                    Log.d("JJJJ", "Проверка: "+news.get(1).getName());
                }
            }

            @Override
            public void onFailure(Call<List<NewsModel>> call, Throwable t) {

            }
        });
    }
}
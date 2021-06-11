package com.example.thread_practice;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyResponse {
    public void getPostResponse() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<Post>> call = apiService.getData();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                MainActivity.myList = new ArrayList<>(response.body());
                MainActivity.adapter = new MyAdapter(MainActivity.myList, MainActivity.context);
                MainActivity.recyclerView.setAdapter(MainActivity.adapter);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.d("aaaaaaaaaaaaaaaaaaaaaa", "bbbbbbbbbbbbbbbbbbb");

            }
        });
    }
}

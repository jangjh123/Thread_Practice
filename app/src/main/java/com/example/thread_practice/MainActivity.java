package com.example.thread_practice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Button button;
    public static RecyclerView recyclerView;
    private Handler handler;
    public static MyAdapter adapter;
    public static Context context;
    public static ArrayList<Post> myList = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    // 메인 스레드는 원래 루퍼 있음. 어지간하면 루퍼 신경 안 써도 될듯
    // 루퍼 = 메세지 큐에서 메세지 꺼내서 핸들러한테 줌
    // 헨들러는 메인스레드에 종속되어 있음.
    // 메시지 = 스레드 간의 신호. 큐이기 때문에 선입선출. 메시지 쌓아 두는 공간 = 메시지 큐


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        this.context = context;



        Thread thread = new Thread(new Runnable() { // 2021-06-11 todo
            @Override
            public void run() {


                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        MyResponse myResponse = new MyResponse();
                        myResponse.getPostResponse();

                    }
                });
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            thread.start();
            }
        });




    }
    public void initView() {
        handler = new Handler();
        button = findViewById(R.id.button);
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new MyAdapter(myList, MainActivity.this);
        linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

    }
}


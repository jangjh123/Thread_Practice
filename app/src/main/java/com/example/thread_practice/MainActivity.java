package com.example.thread_practice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button button1, button2, button3;
    private Context context;
    private Handler handler;
    private TextView textView;
    // 메인 스레드는 원래 루퍼 있음. 어지간하면 루퍼 신경 안 써도 될듯
    // 루퍼 = 메세지 큐에서 메세지 꺼내서 핸들러한테 줌
    // 헨들러는 메인스레드에 종속되어 있음.
    // 메시지 = 스레드 간의 신호. 큐이기 때문에 선입선출. 메시지 쌓아 두는 공간 = 메시지 큐


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.context =context;
        initView();

//        Thread thread = new Thread(new Runnable() { // 2021-06-11 todo
//            @Override
//            public void run() {
//                // JSON 파싱해서
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        // 띄워주기
//                    }
//                });
//            }
//        });
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            thread.start();
//            }
//        });

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("1");
                    }
                });
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("2");
                    }
                });
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("3");
                    }
                });
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thread1.start();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thread2.start();
            }
        });
        button3.setOnClickListener(v -> {
            thread3.start();
        });
// 재사용하면 앱 뻗음
    }
    public void initView() {
        handler = new Handler();
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        textView = findViewById(R.id.textView);
    }
}


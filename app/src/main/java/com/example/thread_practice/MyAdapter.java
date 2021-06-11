package com.example.thread_practice;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<Post> myList = new ArrayList<>();
    private Context context;

    public MyAdapter(ArrayList<Post> myList, Context context) {
        this.myList = myList;
        this.context = context;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull MyAdapter.ViewHolder holder, int position) {
        Post post = myList.get(position);
        holder.id.setText(myList.get(position).getId());
        holder.userId.setText(myList.get(position).getUserId());
        holder.title.setText(myList.get(position).getTitle());
        holder.body.setText(myList.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView id, userId, title, body;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            userId = itemView.findViewById(R.id.userId);
            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);
        }
    }
}

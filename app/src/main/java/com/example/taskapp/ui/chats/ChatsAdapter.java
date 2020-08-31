package com.example.taskapp.ui.chats;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskapp.R;
import com.example.taskapp.models.Chat;

import java.util.ArrayList;

public class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.ViewHolder> {
    private ArrayList<Chat> tasks;

    public ChatsAdapter(ArrayList<Chat> list) {
        tasks = list;
    }

    public int addNew(String s) {
        int position = tasks.size();
        Chat chat1 = new Chat();
        chat1.name = s;
        tasks.add(chat1);
        notifyDataSetChanged();
        return position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_chat_left, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(tasks.get(position));
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView right, left;
        String resultText = "";

        @SuppressLint("CutPasteId")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            right = itemView.findViewById(R.id.list_chat_right);
//            left = itemView.findViewById(R.id.list_chat_left);
        }

        public void bind(Chat task) {
                right.setText("");
                right.setText(task.getName());
        }


    }


}

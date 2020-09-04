package com.example.taskapp.ui.chats;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskapp.R;
import com.example.taskapp.interfaces.OnChatsClickListener;
import com.example.taskapp.models.Chat;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

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

        private TextView right, name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            right = itemView.findViewById(R.id.list_chat_right);
            name = itemView.findViewById(R.id.tv_Name);

        }

        public void bind(final Chat chat) {
            right.setText(chat.getName());
//            String usersId = FirebaseAuth.getInstance().getUid();
//            FirebaseFirestore.getInstance().collection("users")
//                    .document(usersId)
//                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
//                        @Override
//                        public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//                            if (value != null && value.exists()) {
//                                Map<String, Object> map = value.getData();
//                                name.setText(map.get("name").toString());
//                            }
//                        }
//                    });
        }


    }


}

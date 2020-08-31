package com.example.taskapp.ui.chats;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskapp.R;
import com.example.taskapp.models.Chat;
import com.example.taskapp.models.Task;

import java.util.ArrayList;

public class ChatsFragment extends Fragment {

    ArrayList<Chat> list = new ArrayList<>();
    RecyclerView recyclerView;
    AppBarConfiguration appBarConfiguration;
    ChatsAdapter adapter = new ChatsAdapter(list);
    Chat chat;
    TextView textView;
    EditText editText;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = view.findViewById(R.id.btn_submit);
        editText = view.findViewById(R.id.edit_text_sms);
        appBarConfiguration = new AppBarConfiguration.Builder(R.layout.fragment_profile_photos, R.layout.fragment_profile_videos, R.layout.fragment_pro_plans ).build();
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendFormBase();
            }
        });


        recyclerView = view.findViewById(R.id.recyclerview_description);
        recyclerView.setAdapter(adapter);
    }

    private void sendFormBase() {
        String s = editText.getText().toString().trim();
        int lastPosition =  adapter.addNew(s);
        recyclerView.scrollToPosition(lastPosition);
        editText.setText("");
    }
}
package com.example.taskapp.ui.chats;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskapp.App;
import com.example.taskapp.R;
import com.example.taskapp.interfaces.OnChatsClickListener;
import com.example.taskapp.models.Chat;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChatsFragment extends Fragment {

    ArrayList<Chat> list = new ArrayList<>();
    RecyclerView recyclerView;
    ChatsAdapter adapter = new ChatsAdapter(list);
    EditText editText;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendFormBase();
            }
        });
        editText = view.findViewById(R.id.edit_text_sms);
        recyclerView = view.findViewById(R.id.recyclerview_description);
        recyclerView.setAdapter(adapter);
        getChatDescription();
    }

    private void getChatDescription() {
        FirebaseFirestore.getInstance().collection("chat").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        if (document.get("chat") != null) {
                            String chat = document.get("chat") + "";
                            adapterName(chat);
                        }
                    }
                } else {
                    Log.e("TAG", "Ошибка при получении документов: ", task.getException());
                }
            }
        });
        editText.setText("");

    }


    private void sendFormBase() {
        String s = editText.getText().toString().trim();
        Map<String, Object> map = new HashMap<>();
        map.put("message", s);
        map.put("chatNew", s);
        adapterName(s);
        FirebaseFirestore.getInstance().collection("chat").add(map).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull com.google.android.gms.tasks.Task<DocumentReference> task) {
                Toast.makeText(requireContext(), "Result: " + task.isSuccessful(), Toast.LENGTH_SHORT).show();
            }
        });
        editText.setText("");
    }

    private void adapterName(String text) {
        int lastPosition = adapter.addNew(text);
        recyclerView.scrollToPosition(lastPosition);
        adapter.notifyDataSetChanged();
    }
}
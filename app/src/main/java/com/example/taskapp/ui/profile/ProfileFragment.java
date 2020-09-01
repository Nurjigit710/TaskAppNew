package com.example.taskapp.ui.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.taskapp.MainActivity;
import com.example.taskapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class ProfileFragment extends Fragment {
    ImageView imageView, imageBackG;
    TextView textView;
    EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navControllerProfile = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        imageBackG = view.findViewById(R.id.imageBackground);
        textView = view.findViewById(R.id.btn_Save_base);
        editText = view.findViewById(R.id.textName);

        view.findViewById(R.id.imageBackBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navControllerProfile.navigateUp();
            }
        });

        imageView = view.findViewById(R.id.imageAvatar);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imageIntent = new Intent(Intent.ACTION_PICK);
                imageIntent.setType("image/*");
                startActivityForResult(imageIntent, 42);
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveName();
            }
        });

        Glide.with(this).load("https://i.pinimg.com/originals/48/b3/95/48b395c55ecc2b8bc4fc0812dec3a30e.jpg").circleCrop().into(imageView);
        Glide.with(this).load("https://bipbap.ru/wp-content/uploads/2017/05/01101425.jpg").centerCrop().into(imageBackG);
        getNameFromFirestoreLive();
    }

    private void getNameFromFirestore() {
        String usersId = FirebaseAuth.getInstance().getUid();
        FirebaseFirestore.getInstance().collection("users")
                .document(usersId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            editText.setText(task.getResult().getString("name"));
                        }
                    }
                });

    }

    private void getNameFromFirestoreLive() {
        String usersId = FirebaseAuth.getInstance().getUid();
        FirebaseFirestore.getInstance().collection("users")
                .document(usersId)
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (value != null && value.exists()) {
                            Map<String, Object> map = value.getData();
                            editText.setText(map.get("name").toString());
                        }
                    }
                });
    }

    private void saveName() {
        String usersId = FirebaseAuth.getInstance().getUid();
        String name = editText.getText().toString().trim();
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        FirebaseFirestore.getInstance().collection("users")
                .document(usersId)
                .set(map)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(requireContext(), "Result: " + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 42 && resultCode == getActivity().RESULT_OK) {
            assert data != null;
            Glide.with(this).load(data.getData()).circleCrop().into(imageView);
        }
    }

}


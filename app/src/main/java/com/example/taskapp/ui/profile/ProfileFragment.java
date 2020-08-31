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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.taskapp.MainActivity;
import com.example.taskapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileFragment extends Fragment {
    ImageView imageView, imageBackG;
    TextView textView;

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
                Toast.makeText(requireContext(), "onClick: Save Base", Toast.LENGTH_SHORT).show();
            }
        });

        Glide.with(this).load("https://i.pinimg.com/originals/48/b3/95/48b395c55ecc2b8bc4fc0812dec3a30e.jpg").circleCrop().into(imageView);
        Glide.with(this).load("https://images.unsplash.com/photo-1538455687899-25c6244d8f9c?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1000&q=80").centerCrop().into(imageBackG);
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


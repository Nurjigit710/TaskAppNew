package com.example.taskapp.ui.auth;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taskapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;


public class PhoneFragment extends Fragment {
    EditText editText;
    TextView textError;
    boolean isError;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_phone, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText = view.findViewById(R.id.editPhone);
        textError = view.findViewById(R.id.textError);

        getParentFragmentManager().setFragmentResultListener("phone3", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                isError = result.getBoolean("phone4");
                if (isError){
                    textError.setText("Проверьте номер");
                }
            }
        });



        view.findViewById(R.id.btnContinue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = editText.getText().toString().trim();
                if (s.isEmpty() || s.length() < 10) {
                    editText.setError("Valid number is required");
                    editText.requestFocus();
                }
                else if (s != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("phone", s);
                    getParentFragmentManager().setFragmentResult("phone2", bundle);
                    NavController navController = Navigation.findNavController( requireActivity(),R.id.nav_host_fragment);
                    navController.navigate(R.id.action_phoneFragment_to_codeFragment);
                }
            }
        });
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finish();
            }
        });
//         mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//            @Override
//            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//                Log.e("Phone", "onVerificationCompleted");
//                signIn(phoneAuthCredential);
//            }
//
//            @Override
//            public void onVerificationFailed(@NonNull FirebaseException e) {
//                Log.e("Phone", "onVerificationFailed" + e.getMessage());
//            }
//
//            @Override
//            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                super.onCodeSent(s, forceResendingToken);
//                Log.e("Phone", "onCodeSent");
//            }
//
//            @Override
//            public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
//                super.onCodeAutoRetrievalTimeOut(s);
//                Log.e("Phone", "onCodeAutoRetrievalTimeOut");
//            }
//        };
//
//  }
//
//    private void signIn(PhoneAuthCredential phoneAuthCredential) {
//        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()) {
//                    Toast.makeText(requireContext(), "succes", Toast.LENGTH_SHORT).show();
//                } else
//                    Toast.makeText(requireContext(), "failed", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private void requestSms() {
//        String phone = editText.getText().toString().trim();
//        PhoneAuthProvider.getInstance().verifyPhoneNumber(phone, 60,
//                TimeUnit.SECONDS, requireActivity(), mCallbacks);
    }
}
package com.example.dungeonsanddragonstraven.menu_screens;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dungeonsanddragonstraven.CharacterSelectionFrag;
import com.example.dungeonsanddragonstraven.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class SignUpFrag extends Fragment {
    private FirebaseAuth mAuth;
    private DatabaseReference ref;

    private String userText;
    private String emailText;
    private String passwordText;


    public static SignUpFrag newInstance() {

        Bundle args = new Bundle();

        SignUpFrag fragment = new SignUpFrag();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase datebase = FirebaseDatabase.getInstance();
        ref = datebase.getReference("data");

        Button signUpBtn = getActivity().findViewById(R.id.signUpBtn);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewUser();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sign_up_screen, container, false);
    }

    private void createNewUser(){
        mAuth.createUserWithEmailAndPassword(emailText, passwordText)
                .addOnCompleteListener(Objects.requireNonNull(getActivity()), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.d("Created", "Creating user was successful");
                            FirebaseUser currentUser = mAuth.getCurrentUser();

                            DatabaseReference userRef = ref.child("users");

                            userRef.child(currentUser.getUid());

                            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.fragcontainer, CharacterSelectionFrag.newInstance()).commit();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Error", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}

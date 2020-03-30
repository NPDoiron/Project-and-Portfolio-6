package com.example.dungeonsanddragonstraven.character_creation_manual_screens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dungeonsanddragonstraven.Character;
import com.example.dungeonsanddragonstraven.CharacterSelectionFrag;
import com.example.dungeonsanddragonstraven.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ManualScreenFive extends Fragment {

    private FirebaseAuth mAuth;
    private DatabaseReference ref;

    public static ManualScreenFive newInstance(Character current, String type) {

        Bundle args = new Bundle();
        args.putSerializable("Character", current);
        args.putString("Type", type);

        ManualScreenFive fragment = new ManualScreenFive();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setHasOptionsMenu(true);

        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase datebase = FirebaseDatabase.getInstance();
        ref = datebase.getReference("data");

        final Character current =  (Character) getArguments().getSerializable("Character");
        ImageView backBtn = getActivity().findViewById(R.id.backBtnScreenFive);

        final EditText height = getActivity().findViewById(R.id.heightEdit);
        final EditText weight = getActivity().findViewById(R.id.weightEdit);
        final EditText age = getActivity().findViewById(R.id.ageEdit);
        final EditText gender = getActivity().findViewById(R.id.genderEdit);
        final EditText name = getActivity().findViewById(R.id.nameEdit);

        Button finishBtn = getActivity().findViewById(R.id.createCharacterFinalBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (height.getText().toString().isEmpty() || weight.getText().toString().isEmpty() || age.getText().toString().isEmpty() ||
                        gender.getText().toString().isEmpty() || name.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "One of the required fields are not filled out.", Toast.LENGTH_SHORT).show();

                } else {
                    current.setHeight(height.getText().toString());
                    current.setWeight(weight.getText().toString());
                    current.setAge(age.getText().toString());
                    current.setGender(gender.getText().toString());
                    current.setCharacterName(name.getText().toString());

                    FirebaseUser currentUser = mAuth.getCurrentUser();
                    DatabaseReference userRef = ref.child("users");
                    userRef.child(currentUser.getUid()).child(current.characterName).setValue(current);

                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragcontainer, CharacterSelectionFrag.newInstance()).commit();
                }
            }
        });

        if (getArguments().getString("Type") == "Randomization"){
            name.setText(current.characterName);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragcontainer, CharacterSelectionFrag.newInstance()).commit();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.manual_creation_menu_one, menu);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.manual_screen_five, container, false);
    }
}

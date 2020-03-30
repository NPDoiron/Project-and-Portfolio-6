package com.example.dungeonsanddragonstraven.character_display_screens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dungeonsanddragonstraven.Character;
import com.example.dungeonsanddragonstraven.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DisplayScreenFour extends Fragment {
    boolean edit = false;
    Character selected;

    public static DisplayScreenFour newInstance(Character selected) {

        Bundle args = new Bundle();
        args.putSerializable("Selected", selected);

        DisplayScreenFour fragment = new DisplayScreenFour();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.display_screen_four, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Spinner alignment = getActivity().findViewById(R.id.displayAlignmentSpin);
        Spinner lifestyle = getActivity().findViewById(R.id.displayLifeSpin);
        EditText faith = getActivity().findViewById(R.id.displayFaith);
        EditText name = getActivity().findViewById(R.id.displayName);
        EditText gender = getActivity().findViewById(R.id.displayGender);
        EditText weight = getActivity().findViewById(R.id.displayWeight);
        EditText height = getActivity().findViewById(R.id.displayHeight);
        EditText eyes = getActivity().findViewById(R.id.displayEyes);
        EditText hair = getActivity().findViewById(R.id.displayHair);
        EditText age = getActivity().findViewById(R.id.displayAge);
        EditText skin = getActivity().findViewById(R.id.displaySkin);
        ImageView backBtn = getActivity().findViewById(R.id.displayBackBtnFour);


        setHasOptionsMenu(true);

        selected = (Character) getArguments().getSerializable("Selected");

        for (int i = 0; i < alignment.getCount(); i++){
            alignment.setSelection(i);
            if (alignment.getSelectedItem().toString().equals(selected.race)){
                break;
            }
        }

        for (int i = 0; i < lifestyle.getCount(); i++){
            lifestyle.setSelection(i);
            if (lifestyle.getSelectedItem().toString().equals(selected.race)){
                break;
            }
        }

        faith.setText(selected.faith);
        name.setText(selected.characterName);
        gender.setText(selected.gender);
        weight.setText(selected.weight);
        height.setText(selected.height);
        eyes.setText(selected.eyes);
        hair.setText(selected.hair);
        age.setText(selected.age);
        skin.setText(selected.skin);

        faith.setEnabled(false);
        name.setEnabled(false);
        age.setEnabled(false);
        gender.setEnabled(false);
        weight.setEnabled(false);
        height.setEnabled(false);
        hair.setEnabled(false);
        skin.setEnabled(false);
        eyes.setEnabled(false);
        alignment.setEnabled(false);
        lifestyle.setEnabled(false);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragcontainer, DisplayBtnScreen.newInstance(selected)).commit();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.edit_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Spinner alignment = getActivity().findViewById(R.id.displayAlignmentSpin);
        Spinner lifestyle = getActivity().findViewById(R.id.displayLifeSpin);
        EditText faith = getActivity().findViewById(R.id.displayFaith);
        EditText name = getActivity().findViewById(R.id.displayName);
        EditText gender = getActivity().findViewById(R.id.displayGender);
        EditText weight = getActivity().findViewById(R.id.displayWeight);
        EditText height = getActivity().findViewById(R.id.displayHeight);
        EditText eyes = getActivity().findViewById(R.id.displayEyes);
        EditText hair = getActivity().findViewById(R.id.displayHair);
        EditText age = getActivity().findViewById(R.id.displayAge);
        EditText skin = getActivity().findViewById(R.id.displaySkin);

        if (edit == false) {
            edit = true;
            Toast.makeText(getActivity(), "Editing has been turned on", Toast.LENGTH_SHORT).show();

            faith.setEnabled(true);
            name.setEnabled(true);
            age.setEnabled(true);
            gender.setEnabled(true);
            weight.setEnabled(true);
            height.setEnabled(true);
            hair.setEnabled(true);
            skin.setEnabled(true);
            eyes.setEnabled(true);
            alignment.setEnabled(true);
            lifestyle.setEnabled(true);

        } else if (edit == true) {

            if (faith.getText().toString().equals("") || name.getText().toString().equals("") || height.getText().toString().equals("") ||
                    weight.getText().toString().equals("") || age.getText().toString().equals("") || eyes.getText().toString().equals("") ||
                    hair.getText().toString().equals("") || gender.getText().toString().equals("") || skin.getText().toString().equals("")){
                Toast.makeText(getActivity(), "Editing can't be turned off unless all fields have a value.", Toast.LENGTH_SHORT).show();
            } else {
                edit = false;
                Toast.makeText(getActivity(), "Editing has been turned off. Saving changes", Toast.LENGTH_SHORT).show();

                faith.setEnabled(false);
                name.setEnabled(false);
                age.setEnabled(false);
                gender.setEnabled(false);
                weight.setEnabled(false);
                height.setEnabled(false);
                hair.setEnabled(false);
                skin.setEnabled(false);
                eyes.setEnabled(false);
                alignment.setEnabled(false);
                lifestyle.setEnabled(false);

                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                FirebaseDatabase datebase = FirebaseDatabase.getInstance();

                DatabaseReference reference = datebase.getReference("data/users/" + mAuth.getCurrentUser().getUid() + "/" + selected.characterName);

                reference.child("alignment").setValue(alignment.getSelectedItem().toString());
                reference.child("lifeStyle").setValue(lifestyle.getSelectedItem().toString());
                reference.child("eyes").setValue(eyes.getText().toString());
                reference.child("faith").setValue(faith.getText().toString());
                reference.child("gender").setValue(gender.getText().toString());
                reference.child("height").setValue(height.getText().toString());
                reference.child("weight").setValue(weight.getText().toString());
                reference.child("hair").setValue(hair.getText().toString());
                reference.child("age").setValue(age.getText().toString());
                reference.child("skin").setValue(skin.getText().toString());
                reference.child("characterName").setValue(name.getText().toString());
            }
        }

        return super.onOptionsItemSelected(item);
    }
}

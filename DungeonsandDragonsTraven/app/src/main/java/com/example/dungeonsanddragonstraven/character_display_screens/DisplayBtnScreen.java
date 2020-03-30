package com.example.dungeonsanddragonstraven.character_display_screens;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dungeonsanddragonstraven.Character;
import com.example.dungeonsanddragonstraven.CharacterSelectionFrag;
import com.example.dungeonsanddragonstraven.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DisplayBtnScreen extends Fragment {

    Character selected;
    private FirebaseAuth mAuth;

    public static DisplayBtnScreen newInstance(Character selected) {

        Bundle args = new Bundle();
        args.putSerializable("Selected", selected);

        DisplayBtnScreen fragment = new DisplayBtnScreen();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.display_button, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        selected = (Character) getArguments().getSerializable("Selected");

        setHasOptionsMenu(true);

        ImageView backBtn = getActivity().findViewById(R.id.display_btn_back_btn);
        Button hpBtn = getActivity().findViewById(R.id.hpBtn);
        Button stats = getActivity().findViewById(R.id.statsBtn);
        Button background = getActivity().findViewById(R.id.backProBtn);
        Button details = getActivity().findViewById(R.id.characterDetailBtn);
        Button inventory = getActivity().findViewById(R.id.inventoryBtn);
        Button spell = getActivity().findViewById(R.id.spellListBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragcontainer, CharacterSelectionFrag.newInstance()).commit();
            }
        });

        hpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragcontainer, DisplayScreenOne.newInstance(selected)).commit();
            }
        });

        stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragcontainer, DisplayScreenTwo.newInstance(selected)).commit();
            }
        });

        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragcontainer, DisplayScreenThree.newInstance(selected)).commit();
            }
        });

        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragcontainer, DisplayScreenFour.newInstance(selected)).commit();
            }
        });

        inventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        spell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.deleteIcon){

            new AlertDialog.Builder(getContext())
                    .setTitle("Delete Character")
                    .setMessage("Are you sure you want to delete this character?")

                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            mAuth = FirebaseAuth.getInstance();
                            FirebaseDatabase datebase = FirebaseDatabase.getInstance();

                            DatabaseReference reference = datebase.getReference("data/users/" + mAuth.getCurrentUser().getUid() + "/" + selected.characterName);

                            reference.removeValue();

                            getActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.fragcontainer, CharacterSelectionFrag.newInstance()).commit();                        }
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.select_menu, menu);
    }

    @Override
    public void onResume() {
        super.onResume();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase datebase = FirebaseDatabase.getInstance();
        DatabaseReference reference = datebase.getReference("data/users/" + mAuth.getCurrentUser().getUid());

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Character character = ds.getValue(Character.class);
                    if (character.characterName.equals(selected.characterName)) {
                        selected = character;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        reference.addValueEventListener(eventListener);
    }
}

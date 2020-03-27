package com.example.dungeonsanddragonstraven;

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
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dungeonsanddragonstraven.character_display_screens.DisplayBtnScreen;
import com.example.dungeonsanddragonstraven.menu_screens.MainMenu;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CharacterSelectionFrag extends Fragment {
    private FirebaseAuth mAuth;
    ArrayList<Character> characters = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();

    public static CharacterSelectionFrag newInstance() {

        Bundle args = new Bundle();

        CharacterSelectionFrag fragment = new CharacterSelectionFrag();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setHasOptionsMenu(true);

        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase datebase = FirebaseDatabase.getInstance();

        DatabaseReference reference = datebase.getReference("data/users/" + mAuth.getCurrentUser().getUid());

        Button logOutBtn = getActivity().findViewById(R.id.logOutBtn);
        final ListView characterList = getActivity().findViewById(R.id.characterlist);

        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragcontainer, MainMenu.newInstance()).commit();
            }
        });

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    Character character = ds.getValue(Character.class);
                    if (!characters.contains(character)) {
                        characters.add(character);
                    }
                }

                if (characters.size() > 0){
                    for (int i = 0; i < characters.size(); i++){
                        names.add(characters.get(i).characterName);
                    }

                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, names );

                    characterList.setAdapter(arrayAdapter);

                    characterList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            getActivity().getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.fragcontainer, DisplayBtnScreen.newInstance(characters.get(position))).addToBackStack("Selected").commit();
                        }
                    });
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        reference.addValueEventListener(eventListener);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.character_selection_screen, container, false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragcontainer, CharacterCreateBtnFrag.newInstance()).addToBackStack("CreationBtn").commit();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.character_creation_menu, menu);
    }
}

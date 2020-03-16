package com.example.dungeonsanddragonstraven;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dungeonsanddragonstraven.menu_screens.MainMenu;
import com.google.firebase.auth.FirebaseAuth;

public class CharacterSelectionFrag extends Fragment {
    private FirebaseAuth mAuth;

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

        Button logOutBtn = getActivity().findViewById(R.id.logOutBtn);

        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragcontainer, MainMenu.newInstance()).commit();
            }
        });
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

package com.example.dungeonsanddragonstraven.character_display_screens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dungeonsanddragonstraven.Character;
import com.example.dungeonsanddragonstraven.CharacterSelectionFrag;
import com.example.dungeonsanddragonstraven.R;

public class DisplayBtnScreen extends Fragment {
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

        setHasOptionsMenu(true);

        ImageView backBtn = getActivity().findViewById(R.id.display_btn_back_btn);
        Button hpBtn = getActivity().findViewById(R.id.hpBtn);
        Button stats = getActivity().findViewById(R.id.statsBtn);
        Button background = getActivity().findViewById(R.id.backProBtn);
        Button alignment = getActivity().findViewById(R.id.alignmentFaithLifeBtn);
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

            }
        });

        stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        alignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.select_menu, menu);
    }
}

package com.example.dungeonsanddragonstraven.character_creation_manual_screens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dungeonsanddragonstraven.Character;
import com.example.dungeonsanddragonstraven.R;

public class ManualScreenTwo extends Fragment {

    Character character;

    public static ManualScreenTwo newInstance(Character currentCharacter) {

        Bundle args = new Bundle();
        args.putSerializable("Character", currentCharacter);

        ManualScreenTwo fragment = new ManualScreenTwo();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        character = (Character) getArguments().getSerializable("Character");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.manual_screen_two, container, false);
    }
}

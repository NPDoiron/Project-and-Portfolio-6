package com.example.dungeonsanddragonstraven;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CharacterCreateBtnFrag extends Fragment {
    public static CharacterCreateBtnFrag newInstance() {

        Bundle args = new Bundle();

        CharacterCreateBtnFrag fragment = new CharacterCreateBtnFrag();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.character_button_screen, container, false);
    }
}

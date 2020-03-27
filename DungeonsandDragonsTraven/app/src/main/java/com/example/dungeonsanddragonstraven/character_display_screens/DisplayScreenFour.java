package com.example.dungeonsanddragonstraven.character_display_screens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dungeonsanddragonstraven.Character;
import com.example.dungeonsanddragonstraven.R;

public class DisplayScreenFour extends Fragment {
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
    }
}

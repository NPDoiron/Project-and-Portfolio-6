package com.example.dungeonsanddragonstraven;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dungeonsanddragonstraven.character_creation_manual_screens.ManualScreenOne;

public class CharacterCreateBtnFrag extends Fragment {
    public static CharacterCreateBtnFrag newInstance() {

        Bundle args = new Bundle();

        CharacterCreateBtnFrag fragment = new CharacterCreateBtnFrag();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button manualBtn = getActivity().findViewById(R.id.manualBtn);
        ImageView backBtn = getActivity().findViewById(R.id.creationBtnScreenBackBtn);

        manualBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragcontainer, ManualScreenOne.newInstance(beginCharacterCreation())).commit();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.character_button_screen, container, false);
    }

    public Character beginCharacterCreation(){
       Character newCharacter = new Character(null, null, null, null,
               null, 0, 0, 0, 0, 0, 0, null,
               null, null, null, null,null, null,
               null, null, null, null, null, null, null, null, null,
               null);
       return newCharacter;
    }
}

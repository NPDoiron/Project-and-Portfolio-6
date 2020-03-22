package com.example.dungeonsanddragonstraven.randomization;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dungeonsanddragonstraven.Character;
import com.example.dungeonsanddragonstraven.R;
import com.example.dungeonsanddragonstraven.character_creation_manual_screens.ManualScreenOne;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomizerPrefFrag extends Fragment {
    public static RandomizerPrefFrag newInstance() {

        Bundle args = new Bundle();

        RandomizerPrefFrag fragment = new RandomizerPrefFrag();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.randomizer_pref, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final EditText name = getActivity().findViewById(R.id.characterNameRandom);
        final Spinner race = getActivity().findViewById(R.id.randomRaceSpinner);
        final Spinner characterClass = getActivity().findViewById(R.id.randomClassSpinner);
        final Spinner background = getActivity().findViewById(R.id.randomBackgroundSpinner);
        ImageView backBtn = getActivity().findViewById(R.id.randomBackBtn);
        Button generateBtn = getActivity().findViewById(R.id.generateBtn);

        generateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Character randomCharacter = beginCharacterCreation();

                randomCharacter.setCharacterName(name.getText().toString());

                if (characterClass.getSelectedItem().toString().equals("Select a class")){
                    characterClass.setSelection(randomInt(characterClass.getCount()));
                    randomCharacter.setClassString(characterClass.getSelectedItem().toString());
                }else{
                    randomCharacter.setClassString(characterClass.getSelectedItem().toString());
                }

                if (race.getSelectedItem().equals("Select a race")){
                    race.setSelection(randomInt(race.getCount()));
                    randomCharacter.setRace(race.getSelectedItem().toString());
                }else{
                    randomCharacter.setRace(race.getSelectedItem().toString());
                }

                if (background.getSelectedItem().equals("Choose a background")){
                    background.setSelection(randomInt(background.getCount()));
                    randomCharacter.setBackground(background.getSelectedItem().toString());
                }else{
                    randomCharacter.setBackground(background.getSelectedItem().toString());
                }

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragcontainer, ManualScreenOne.newInstance(randomCharacter, "Randomization")).commit();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

    public Character beginCharacterCreation() {
        Character newCharacter = new Character(null, null, null, null,
                null, 0, 0, 0, 0, 0, 0, null,
                null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null,
                null, null);
        return newCharacter;
    }

    public int randomInt(int max){
        final int min = 1;
        final int random = new Random().nextInt((max - min)) + min;

        return random;
    }

}

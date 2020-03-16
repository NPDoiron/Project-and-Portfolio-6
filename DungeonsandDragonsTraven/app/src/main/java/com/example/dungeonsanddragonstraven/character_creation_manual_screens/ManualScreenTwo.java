package com.example.dungeonsanddragonstraven.character_creation_manual_screens;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dungeonsanddragonstraven.Character;
import com.example.dungeonsanddragonstraven.CharacterSelectionFrag;
import com.example.dungeonsanddragonstraven.R;

import org.w3c.dom.Text;

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

        setHasOptionsMenu(true);

        character = (Character) getArguments().getSerializable("Character");

        TextView pointsRemaining = getActivity().findViewById(R.id.remainingDisplay);
        ImageView backBtn = getActivity().findViewById(R.id.backBtnScreenTwo);
        Button continueBtn = getActivity().findViewById(R.id.continueBtnScreenThree);
        final TextView strBase = getActivity().findViewById(R.id.strBase);
        final TextView wisBase = getActivity().findViewById(R.id.wisBase);
        final TextView conBase = getActivity().findViewById(R.id.conBase);
        final TextView charBase = getActivity().findViewById(R.id.charBase);
        final TextView intBase = getActivity().findViewById(R.id.intBase);
        final TextView dexBase = getActivity().findViewById(R.id.dexBase);
        TextView strMod = getActivity().findViewById(R.id.strMod);
        TextView dexMod = getActivity().findViewById(R.id.dexMod);
        TextView intMod = getActivity().findViewById(R.id.intMod);
        TextView charMod = getActivity().findViewById(R.id.charMod);
        TextView conMod = getActivity().findViewById(R.id.conMod);
        TextView wisMod = getActivity().findViewById(R.id.wisMod);
        final TextView strTotal = getActivity().findViewById(R.id.strTotal);
        final TextView dexTotal = getActivity().findViewById(R.id.dexTotal);
        final TextView intTotal = getActivity().findViewById(R.id.intTotal);
        final TextView charTotal = getActivity().findViewById(R.id.charTotal);
        final TextView conTotal = getActivity().findViewById(R.id.conTotal);
        final TextView wisTotal = getActivity().findViewById(R.id.wisTotal);
        final TextView strBonus = getActivity().findViewById(R.id.strRace);
        final TextView dexBonus = getActivity().findViewById(R.id.dexRace);
        final TextView conBonus = getActivity().findViewById(R.id.conRace);
        final TextView wisBonus = getActivity().findViewById(R.id.wisRace);
        final TextView charBonus = getActivity().findViewById(R.id.charRace);
        final TextView intBonus = getActivity().findViewById(R.id.intRace);
        final Spinner strSpinner = getActivity().findViewById(R.id.strSpinner);
        final Spinner dexSpinner = getActivity().findViewById(R.id.dexSpinner);
        final Spinner conSpinner = getActivity().findViewById(R.id.conSpinner);
        final Spinner intSpinner = getActivity().findViewById(R.id.intSpinner);
        final Spinner wisSpinner = getActivity().findViewById(R.id.wisSpinner);
        final Spinner charSpinner = getActivity().findViewById(R.id.charSpinner);



        pointsRemaining.setText("27/27");

        setRaceBonus(character.race);

        strBase.setText("8");
        wisBase.setText("8");
        dexBase.setText("8");
        intBase.setText("8");
        charBase.setText("8");
        conBase.setText("8");
        strMod.setText("-1");
        dexMod.setText("-1");
        intMod.setText("-1");
        charMod.setText("-1");
        conMod.setText("-1");
        wisMod.setText("-1");

        final int strBaseInt = Integer.parseInt(strBase.getText().toString());
        int strBonusInt = Integer.parseInt(strBonus.getText().toString());
        final int dexBaseInt = Integer.parseInt(dexBase.getText().toString());
        int dexBonusInt = Integer.parseInt(dexBonus.getText().toString());
        final int conBaseInt = Integer.parseInt(conBase.getText().toString());
        int conBonusInt = Integer.parseInt(conBonus.getText().toString());
        final int wisBaseInt = Integer.parseInt(wisBase.getText().toString());
        int wisBonusInt = Integer.parseInt(wisBonus.getText().toString());
        final int charBaseInt = Integer.parseInt(charBase.getText().toString());
        int charBonusInt = Integer.parseInt(charBonus.getText().toString());
        final int intBaseInt = Integer.parseInt(intBase.getText().toString());
        int intBonusInt = Integer.parseInt(intBonus.getText().toString());

        strTotal.setText(String.valueOf(strBaseInt + strBonusInt));
        dexTotal.setText(String.valueOf(dexBaseInt + dexBonusInt));
        intTotal.setText(String.valueOf(intBaseInt + intBonusInt));
        charTotal.setText(String.valueOf(charBaseInt + charBonusInt));
        conTotal.setText(String.valueOf(conBaseInt + conBonusInt));
        wisTotal.setText(String.valueOf(wisBaseInt + wisBonusInt));

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        strSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strBase.setText(strSpinner.getSelectedItem().toString());
                int strBaseInt = Integer.parseInt(strBase.getText().toString());
                int strBonusInt = Integer.parseInt(strBonus.getText().toString());
                strTotal.setText(String.valueOf(strBaseInt + strBonusInt));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        wisSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                wisBase.setText(wisSpinner.getSelectedItem().toString());
                int wisBaseInt = Integer.parseInt(wisBase.getText().toString());
                int wisBonusInt = Integer.parseInt(wisBonus.getText().toString());
                wisTotal.setText(String.valueOf(wisBaseInt + wisBonusInt));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dexSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dexBase.setText(dexSpinner.getSelectedItem().toString());
                int dexBaseInt = Integer.parseInt(dexBase.getText().toString());
                int dexBonusInt = Integer.parseInt(dexBonus.getText().toString());
                dexTotal.setText(String.valueOf(dexBaseInt + dexBonusInt));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        conSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                conBase.setText(conSpinner.getSelectedItem().toString());
                int conBaseInt = Integer.parseInt(conBase.getText().toString());
                int conBonusInt = Integer.parseInt(conBonus.getText().toString());
                conTotal.setText(String.valueOf(conBaseInt + conBonusInt));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        charSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                charBase.setText(charSpinner.getSelectedItem().toString());
                int charBaseInt = Integer.parseInt(charBase.getText().toString());
                int charBonusInt = Integer.parseInt(charBonus.getText().toString());
                charTotal.setText(String.valueOf(charBaseInt + charBonusInt));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        intSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                intBase.setText(intSpinner.getSelectedItem().toString());
                int intBaseInt = Integer.parseInt(intBase.getText().toString());
                int intBonusInt = Integer.parseInt(intBonus.getText().toString());
                intTotal.setText(String.valueOf(intBaseInt + intBonusInt));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                character.setCharInt(intBaseInt);
                character.setStr(strBaseInt);
                character.setDex(dexBaseInt);
                character.setCon(conBaseInt);
                character.setWis(wisBaseInt);
                character.setCharis(charBaseInt);

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragcontainer, ManualScreenThree.newInstance()).addToBackStack("Screen Three").commit();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.manual_creation_menu_one, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragcontainer, CharacterSelectionFrag.newInstance()).commit();

        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.manual_screen_two, container, false);
    }

    private void setRaceBonus(String selectedRace){

        TextView strBonus = getActivity().findViewById(R.id.strRace);
        TextView dexBonus = getActivity().findViewById(R.id.dexRace);
        TextView conBonus = getActivity().findViewById(R.id.conRace);
        TextView wisBonus = getActivity().findViewById(R.id.wisRace);
        TextView charBonus = getActivity().findViewById(R.id.charRace);
        TextView intBonus = getActivity().findViewById(R.id.intRace);


        switch (selectedRace){
            case "Aarakocra":
                dexBonus.setText("+2");
                wisBonus.setText("+1");
                strBonus.setText("0");
                conBonus.setText("0");
                charBonus.setText("0");
                intBonus.setText("0");
                break;

            case "Dragonborn":
                dexBonus.setText("0");
                wisBonus.setText("0");
                strBonus.setText("+2");
                conBonus.setText("0");
                charBonus.setText("+1");
                intBonus.setText("0");
                break;

            case "Hill Dwarf":
                dexBonus.setText("0");
                wisBonus.setText("0");
                strBonus.setText("0");
                conBonus.setText("+2");
                charBonus.setText("0");
                intBonus.setText("0");
                break;

            case "Mountain Dwarf":
                dexBonus.setText("0");
                wisBonus.setText("0");
                strBonus.setText("0");
                conBonus.setText("+2");
                charBonus.setText("0");
                intBonus.setText("0");
                break;

            case "Eladrin Elf":
                dexBonus.setText("+2");
                wisBonus.setText("0");
                strBonus.setText("0");
                conBonus.setText("0");
                charBonus.setText("0");
                intBonus.setText('0');
                break;

            case "Wood Elf":
                dexBonus.setText("0");
                wisBonus.setText("0");
                strBonus.setText("0");
                conBonus.setText("+2");
                charBonus.setText("0");
                intBonus.setText("0");
                break;

            case "High Elf":
                dexBonus.setText("0");
                wisBonus.setText("0");
                strBonus.setText("0");
                conBonus.setText("+2");
                charBonus.setText("0");
                intBonus.setText("0");
                break;

            case "Air Genasi":
                dexBonus.setText("0");
                wisBonus.setText("0");
                strBonus.setText("0");
                conBonus.setText("+2");
                charBonus.setText("0");
                intBonus.setText("0");
                break;

            case "Water Genasi":
                dexBonus.setText("0");
                wisBonus.setText("0");
                strBonus.setText("0");
                conBonus.setText("+2");
                charBonus.setText("0");
                intBonus.setText("0");
                break;

            case "Fire Genasi":
                dexBonus.setText("0");
                wisBonus.setText("0");
                strBonus.setText("0");
                conBonus.setText("+2");
                charBonus.setText("0");
                intBonus.setText("0");
                break;

            case "Earth Genasi":
                dexBonus.setText("0");
                wisBonus.setText("0");
                strBonus.setText("0");
                conBonus.setText("+2");
                charBonus.setText("0");
                intBonus.setText("0");
                break;

            case "Deep Gnome":
                dexBonus.setText("0");
                wisBonus.setText("0");
                strBonus.setText("0");
                conBonus.setText("0");
                charBonus.setText("0");
                intBonus.setText("+2");
                break;

            case "Rock Gnome":
                dexBonus.setText("0");
                wisBonus.setText("0");
                strBonus.setText("0");
                conBonus.setText("0");
                charBonus.setText("0");
                intBonus.setText("+2");
                break;

            case "Goliath":
                dexBonus.setText("0");
                wisBonus.setText("0");
                strBonus.setText("+2");
                conBonus.setText("+1");
                charBonus.setText("0");
                intBonus.setText("0");
                break;

            case "Half-Elf":
                dexBonus.setText("0");
                wisBonus.setText("+1");
                strBonus.setText("+1");
                conBonus.setText("0");
                charBonus.setText("+2");
                intBonus.setText("0");
                break;

            case "Half-Orc":
                dexBonus.setText("0");
                wisBonus.setText("0");
                strBonus.setText("+2");
                conBonus.setText("+1");
                charBonus.setText("0");
                intBonus.setText("0");
                break;

            case "Lightfoot Halfling":
                dexBonus.setText("0");
                wisBonus.setText("0");
                strBonus.setText("0");
                conBonus.setText("+2");
                charBonus.setText("0");
                intBonus.setText("0");
                break;

            case "Stout Halfling":
                dexBonus.setText("0");
                wisBonus.setText("0");
                strBonus.setText("0");
                conBonus.setText("+2");
                charBonus.setText("0");
                intBonus.setText("0");
                break;

            case "Human":
                dexBonus.setText("+1");
                wisBonus.setText("+1");
                strBonus.setText("+1");
                conBonus.setText("+1");
                charBonus.setText("+1");
                intBonus.setText("+1");
                break;

            case "Tiefling":
                dexBonus.setText("0");
                wisBonus.setText("0");
                strBonus.setText("0");
                conBonus.setText("0");
                charBonus.setText("+2");
                intBonus.setText("+1");
                break;

            case "Aasimar":
                dexBonus.setText("0");
                wisBonus.setText("0");
                strBonus.setText("0");
                conBonus.setText("0");
                charBonus.setText("+2");
                intBonus.setText("0");
                break;
        }
    }

}

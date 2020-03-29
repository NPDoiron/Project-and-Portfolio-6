package com.example.dungeonsanddragonstraven.character_display_screens;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dungeonsanddragonstraven.Character;
import com.example.dungeonsanddragonstraven.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DisplayScreenTwo extends Fragment {
    boolean edit = false;
    Character selected;

    int strBaseInt;
    int dexBaseInt;
    int conBaseInt;
    int wisBaseInt;
    int charBaseInt;
    int intBaseInt;

    public static DisplayScreenTwo newInstance(Character selected) {

        Bundle args = new Bundle();
        args.putSerializable("Selected", selected);

        DisplayScreenTwo fragment = new DisplayScreenTwo();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.display_screen_two, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setHasOptionsMenu(true);

        selected = (Character) getArguments().getSerializable("Selected");

        setRaceBonus(selected.race);

        final EditText str = getActivity().findViewById(R.id.displayStr);
        final EditText dex = getActivity().findViewById(R.id.displayDex);
        final EditText con = getActivity().findViewById(R.id.displayCon);
        final EditText wis = getActivity().findViewById(R.id.displayWis);
        final EditText intString = getActivity().findViewById(R.id.displayInt);
        final EditText charString = getActivity().findViewById(R.id.displayCharm);
        final TextView strBonus = getActivity().findViewById(R.id.displayStrRace);
        final TextView dexBonus = getActivity().findViewById(R.id.displayDexRace);
        final TextView wisBonus = getActivity().findViewById(R.id.displayWisRace);
        final TextView intBonus = getActivity().findViewById(R.id.displayIntRace);
        final TextView charBonus = getActivity().findViewById(R.id.displayCharRace);
        final TextView conBonus = getActivity().findViewById(R.id.displayConRace);
        final TextView strTotal = getActivity().findViewById(R.id.displayStrTotal);
        final TextView dexTotal = getActivity().findViewById(R.id.displayDexTotal);
        final TextView wisTotal = getActivity().findViewById(R.id.displayWisTotal);
        final TextView intTotal = getActivity().findViewById(R.id.displayIntTotal);
        final TextView conTotal = getActivity().findViewById(R.id.displayConTotal);
        final TextView charTotal = getActivity().findViewById(R.id.displayCharTotal);
        final TextView strBase = getActivity().findViewById(R.id.displayStrBase);
        final TextView dexBase = getActivity().findViewById(R.id.displayDexBase);
        final TextView wisBase = getActivity().findViewById(R.id.displayWisBase);
        final TextView charBase = getActivity().findViewById(R.id.displayCharBase);
        final TextView conBase = getActivity().findViewById(R.id.displayConBase);
        final TextView intBase = getActivity().findViewById(R.id.displayIntBase);
        final TextView strMod = getActivity().findViewById(R.id.displayStrMod);
        final TextView dexMod = getActivity().findViewById(R.id.displayDexMod);
        final TextView intMod = getActivity().findViewById(R.id.displayIntMod);
        final TextView wisMod = getActivity().findViewById(R.id.displayWisMod);
        final TextView conMod = getActivity().findViewById(R.id.displayConMod);
        final TextView charMod = getActivity().findViewById(R.id.displayCharMod);
        ImageView backBtn = getActivity().findViewById(R.id.displayBackBtnScreenTwo);


        str.setText(String.valueOf(selected.str));
        dex.setText(String.valueOf(selected.dex));
        wis.setText(String.valueOf(selected.wis));
        con.setText(String.valueOf(selected.con));
        intString.setText(String.valueOf(selected.charInt));
        charString.setText(String.valueOf(selected.charis));

        strBase.setText(str.getText().toString());
        dexBase.setText(dex.getText().toString());
        wisBase.setText(wis.getText().toString());
        charBase.setText(charString.getText().toString());
        intBase.setText(intString.getText().toString());
        conBase.setText(con.getText().toString());

        str.setEnabled(false);
        dex.setEnabled(false);
        wis.setEnabled(false);
        intString.setEnabled(false);
        charString.setEnabled(false);
        con.setEnabled(false);

        strBaseInt = Integer.parseInt(str.getText().toString());
        int strBonusInt = Integer.parseInt(strBonus.getText().toString());
        dexBaseInt = Integer.parseInt(dex.getText().toString());
        int dexBonusInt = Integer.parseInt(dexBonus.getText().toString());
        conBaseInt = Integer.parseInt(con.getText().toString());
        int conBonusInt = Integer.parseInt(conBonus.getText().toString());
        wisBaseInt = Integer.parseInt(wis.getText().toString());
        int wisBonusInt = Integer.parseInt(wisBonus.getText().toString());
        charBaseInt = Integer.parseInt(charString.getText().toString());
        int charBonusInt = Integer.parseInt(charBonus.getText().toString());
        intBaseInt = Integer.parseInt(intString.getText().toString());
        int intBonusInt = Integer.parseInt(intBonus.getText().toString());

        strTotal.setText(String.valueOf(strBaseInt + strBonusInt));
        dexTotal.setText(String.valueOf(dexBaseInt + dexBonusInt));
        intTotal.setText(String.valueOf(intBaseInt + intBonusInt));
        charTotal.setText(String.valueOf(charBaseInt + charBonusInt));
        conTotal.setText(String.valueOf(conBaseInt + conBonusInt));
        wisTotal.setText(String.valueOf(wisBaseInt + wisBonusInt));
        strMod.setText(String.valueOf((strBaseInt / 2) - 5));
        dexMod.setText(String.valueOf((dexBaseInt / 2) - 5));
        wisMod.setText(String.valueOf((wisBaseInt / 2) - 5));
        charMod.setText(String.valueOf((charBaseInt / 2) - 5));
        intMod.setText(String.valueOf((intBaseInt / 2) - 5));
        conMod.setText(String.valueOf((conBaseInt / 2) - 5));


        str.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!str.getText().toString().equals("")) {
                    strBase.setText(str.getText().toString());
                    strBaseInt = Integer.parseInt(str.getText().toString());
                    int strBonusInt = Integer.parseInt(strBonus.getText().toString());
                    strTotal.setText(String.valueOf(strBaseInt + strBonusInt));

                    strMod.setText(String.valueOf((strBaseInt / 2) - 5));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        dex.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!dex.getText().toString().equals("")) {
                    dexBase.setText(dex.getText().toString());
                    dexBaseInt = Integer.parseInt(dex.getText().toString());
                    int dexBonusInt = Integer.parseInt(dexBonus.getText().toString());
                    dexTotal.setText(String.valueOf(dexBaseInt + dexBonusInt));

                    dexMod.setText(String.valueOf((dexBaseInt / 2) - 5));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        intString.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!intString.getText().toString().equals("")) {
                    intBase.setText(str.getText().toString());
                    intBaseInt = Integer.parseInt(intString.getText().toString());
                    int intBonusInt = Integer.parseInt(intBonus.getText().toString());
                    intTotal.setText(String.valueOf(intBaseInt + intBonusInt));

                    intMod.setText(String.valueOf((intBaseInt / 2) - 5));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        con.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!con.getText().toString().equals("")) {
                    conBase.setText(con.getText().toString());
                    conBaseInt = Integer.parseInt(con.getText().toString());
                    int conBonusInt = Integer.parseInt(conBonus.getText().toString());
                    conTotal.setText(String.valueOf(conBaseInt + conBonusInt));

                    conMod.setText(String.valueOf((conBaseInt / 2) - 5));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        wis.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!wis.getText().toString().equals("")) {
                    wisBase.setText(wis.getText().toString());
                    wisBaseInt = Integer.parseInt(wis.getText().toString());
                    int wisBonusInt = Integer.parseInt(wisBonus.getText().toString());
                    wisTotal.setText(String.valueOf(wisBaseInt + wisBonusInt));

                    wisMod.setText(String.valueOf((wisBaseInt / 2) - 5));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        charString.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!charString.getText().toString().equals("")) {
                    charBase.setText(charString.getText().toString());
                    charBaseInt = Integer.parseInt(charString.getText().toString());
                    int charBonusInt = Integer.parseInt(charBonus.getText().toString());
                    charTotal.setText(String.valueOf(charBaseInt + charBonusInt));

                    charMod.setText(String.valueOf((charBaseInt / 2) - 5));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragcontainer, DisplayBtnScreen.newInstance(selected)).commit();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.edit_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        EditText str = getActivity().findViewById(R.id.displayStr);
        EditText dex = getActivity().findViewById(R.id.displayDex);
        EditText con = getActivity().findViewById(R.id.displayCon);
        EditText wis = getActivity().findViewById(R.id.displayWis);
        EditText intString = getActivity().findViewById(R.id.displayInt);
        EditText charString = getActivity().findViewById(R.id.displayCharm);

        if (edit == false) {
            edit = true;
            Toast.makeText(getActivity(), "Editing has been turned on", Toast.LENGTH_SHORT).show();

            str.setEnabled(true);
            dex.setEnabled(true);
            wis.setEnabled(true);
            intString.setEnabled(true);
            charString.setEnabled(true);
            con.setEnabled(true);

        } else if (edit == true) {
            if (str.getText().toString().equals("") || dex.getText().toString().equals("") || con.getText().toString().equals("") ||
                    intString.getText().toString().equals("") || charString.getText().toString().equals("") || wis.getText().toString().equals("")){
                Toast.makeText(getActivity(), "Editing can't be turned off unless all fields have a value.", Toast.LENGTH_SHORT).show();
            }else {
                edit = false;
                Toast.makeText(getActivity(), "Editing has been turned off. Saving changes", Toast.LENGTH_SHORT).show();

                str.setEnabled(false);
                dex.setEnabled(false);
                wis.setEnabled(false);
                intString.setEnabled(false);
                charString.setEnabled(false);
                con.setEnabled(false);

                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                FirebaseDatabase datebase = FirebaseDatabase.getInstance();

                DatabaseReference reference = datebase.getReference("data/users/" + mAuth.getCurrentUser().getUid() + "/" + selected.characterName);
                reference.child("str").setValue(Integer.valueOf(str.getText().toString()));
                reference.child("dex").setValue(Integer.valueOf(dex.getText().toString()));
                reference.child("charis").setValue(Integer.valueOf(charString.getText().toString()));
                reference.child("charInt").setValue(Integer.valueOf(intString.getText().toString()));
                reference.child("con").setValue(Integer.valueOf(con.getText().toString()));
                reference.child("wis").setValue(Integer.valueOf(wis.getText().toString()));
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void setRaceBonus(String selectedRace){

        TextView strBonus = getActivity().findViewById(R.id.displayStrRace);
        TextView dexBonus = getActivity().findViewById(R.id.displayDexRace);
        TextView conBonus = getActivity().findViewById(R.id.displayConRace);
        TextView wisBonus = getActivity().findViewById(R.id.displayWisRace);
        TextView charBonus = getActivity().findViewById(R.id.displayCharRace);
        TextView intBonus = getActivity().findViewById(R.id.displayIntRace);


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
                intBonus.setText("0");
                break;

            case "Wood Elf":
                dexBonus.setText("+2");
                wisBonus.setText("0");
                strBonus.setText("0");
                conBonus.setText("0");
                charBonus.setText("0");
                intBonus.setText("0");
                break;

            case "High Elf":
                dexBonus.setText("+2");
                wisBonus.setText("0");
                strBonus.setText("0");
                conBonus.setText("0");
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

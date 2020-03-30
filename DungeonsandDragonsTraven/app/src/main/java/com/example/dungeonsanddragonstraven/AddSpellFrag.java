package com.example.dungeonsanddragonstraven;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dungeonsanddragonstraven.character_display_screens.SpellListFrag;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddSpellFrag extends Fragment {
    Character selected;

    public static AddSpellFrag newInstance(Character selected) {

        Bundle args = new Bundle();
        args.putSerializable("Selected", selected);

        AddSpellFrag fragment = new AddSpellFrag();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_spell, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button cancelBtn = getActivity().findViewById(R.id.spellCancelBtn);
        Button confirmBtn = getActivity().findViewById(R.id.spellConfirmBtn);
        final EditText spellName = getActivity().findViewById(R.id.spellName);
        final EditText spellDesc = getActivity().findViewById(R.id.spellDescription);

        selected = (Character) getArguments().getSerializable("Selected");

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spellDesc.getText().toString().isEmpty() || spellName.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "One of the fields are not field out.", Toast.LENGTH_SHORT).show();
                } else {
                    if (selected.spells != null) {
                        selected.spells.add(new Spell(spellName.getText().toString(), spellDesc.getText().toString()));
                    } else {
                        selected.spells = new ArrayList<>();
                        selected.spells.add(new Spell(spellName.getText().toString(), spellDesc.getText().toString()));
                    }

                    FirebaseAuth mAuth = FirebaseAuth.getInstance();
                    FirebaseDatabase datebase = FirebaseDatabase.getInstance();

                    DatabaseReference reference = datebase.getReference("data/users/" + mAuth.getCurrentUser().getUid() + "/" + selected.characterName);

                    reference.child("spells").setValue(selected.spells);

                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragcontainer, SpellListFrag.newInstance(selected)).commit();
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragcontainer, SpellListFrag.newInstance(selected)).commit();
            }
        });
    }
}

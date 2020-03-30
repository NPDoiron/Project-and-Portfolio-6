package com.example.dungeonsanddragonstraven.character_display_screens;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dungeonsanddragonstraven.AddSpellFrag;
import com.example.dungeonsanddragonstraven.Character;
import com.example.dungeonsanddragonstraven.CharacterSelectionFrag;
import com.example.dungeonsanddragonstraven.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SpellListFrag extends Fragment {
    Character selected;
    ArrayList<String> spellArrayList = new ArrayList<String>();

    public static SpellListFrag newInstance(Character selected) {

        Bundle args = new Bundle();
        args.putSerializable("Selected", selected);

        SpellListFrag fragment = new SpellListFrag();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setHasOptionsMenu(true);

        ImageView backBtn = getActivity().findViewById(R.id.spellListBackBtn);
        final ListView spellList = getActivity().findViewById(R.id.spellListArea);

        selected = (Character) getArguments().getSerializable("Selected");

        if (selected.spells != null) {
            if (selected.spells.size() > 0){
                for (int i = 0; i < selected.spells.size(); i++){
                    spellArrayList.add(selected.spells.get(i).spellName + ": " + selected.spells.get(i).desc);
                }

                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, spellArrayList );
                spellList.setAdapter(arrayAdapter);
            }
        }

        spellList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Delete Spell")
                        .setMessage("Are you sure you want to delete this spell?")

                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                                FirebaseDatabase datebase = FirebaseDatabase.getInstance();

                                DatabaseReference reference = datebase.getReference("data/users/" + mAuth.getCurrentUser().getUid() + "/"
                                        + selected.characterName + "/" + "spells/" + position);

                                reference.removeValue();

                                spellList.invalidate();
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.spell_list_screen, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.add_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragcontainer, AddSpellFrag.newInstance(selected)).commit();

        return super.onOptionsItemSelected(item);
    }
}

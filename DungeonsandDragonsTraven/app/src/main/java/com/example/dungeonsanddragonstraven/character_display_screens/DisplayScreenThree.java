package com.example.dungeonsanddragonstraven.character_display_screens;

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
import android.widget.Spinner;
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

public class DisplayScreenThree extends Fragment {
    boolean edit = false;
    Character selected;

    public static DisplayScreenThree newInstance(Character selected) {

        Bundle args = new Bundle();
        args.putSerializable("Selected", selected);

        DisplayScreenThree fragment = new DisplayScreenThree();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.display_screen_three, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        selected = (Character) getArguments().getSerializable("Selected");
        setHasOptionsMenu(true);

        final Spinner background = getActivity().findViewById(R.id.displayBackgroundSpinner);
        Spinner spinnerOne = getActivity().findViewById(R.id.displayOtherSpinOne);
        Spinner spinnerTwo = getActivity().findViewById(R.id.displayOtherSpinTwo);
        ImageView backBtn = getActivity().findViewById(R.id.displayBackBtnThree);

        for (int i = 0; i < background.getCount(); i++){
            background.setSelection(i);
            if (background.getSelectedItem().toString().equals(selected.background)){
                break;
            }
        }

        getBackgroundDescription(selected.background);
        getBackgroundFeat(selected.background);

        for (int i = 0; i < spinnerOne.getCount(); i++){
            spinnerOne.setSelection(i);
            if (spinnerOne.getSelectedItem().toString().equals(selected.otherProcOne)){
                break;
            }
        }

        for (int i = 0; i < spinnerTwo.getCount(); i++){
            spinnerTwo.setSelection(i);
            if (spinnerTwo.getSelectedItem().toString().equals(selected.otherProcTwo)){
                break;
            }
        }

        background.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getBackgroundDescription(background.getSelectedItem().toString());
                getBackgroundFeat(background.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        background.setEnabled(false);
        spinnerOne.setEnabled(false);
        spinnerTwo.setEnabled(false);

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

        final Spinner background = getActivity().findViewById(R.id.displayBackgroundSpinner);
        Spinner spinnerOne = getActivity().findViewById(R.id.displayOtherSpinOne);
        Spinner spinnerTwo = getActivity().findViewById(R.id.displayOtherSpinTwo);
        TextView backgroundDesc = getActivity().findViewById(R.id.displayBackgroundDesc);
        TextView backgroundFeat = getActivity().findViewById(R.id.displayBackgroundFeat);
        TextView skillOne = getActivity().findViewById(R.id.displaySkillOne);
        TextView skillTwo = getActivity().findViewById(R.id.displaySkillTwo);


        if (edit == false) {
            edit = true;
            Toast.makeText(getActivity(), "Editing has been turned on", Toast.LENGTH_SHORT).show();

            background.setEnabled(true);
            spinnerOne.setEnabled(true);
            spinnerTwo.setEnabled(true);

        } else if (edit == true) {
            edit = false;
            Toast.makeText(getActivity(), "Editing has been turned off. Saving changes", Toast.LENGTH_SHORT).show();

            background.setEnabled(false);
            spinnerOne.setEnabled(false);
            spinnerTwo.setEnabled(false);

            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            FirebaseDatabase datebase = FirebaseDatabase.getInstance();

            DatabaseReference reference = datebase.getReference("data/users/" + mAuth.getCurrentUser().getUid() + "/" + selected.characterName);

            reference.child("background").setValue(background.getSelectedItem().toString());
            reference.child("backgroundDesc").setValue(backgroundDesc.getText().toString());
            reference.child("backgroundFeat").setValue(backgroundFeat.getText().toString());
            reference.child("otherProcOne").setValue(spinnerOne.getSelectedItem().toString());
            reference.child("otherProcTwo").setValue(spinnerTwo.getSelectedItem().toString());
            reference.child("skillProficOne").setValue(skillOne.getText().toString());
            reference.child("skillProficTwo").setValue(skillTwo.getText().toString());
        }

        return super.onOptionsItemSelected(item);
    }

    private void getBackgroundDescription(String background){

        TextView backgroundDesc = getActivity().findViewById(R.id.displayBackgroundDesc);
        TextView skillOne = getActivity().findViewById(R.id.displaySkillOne);
        TextView skillTwo = getActivity().findViewById(R.id.displaySkillTwo);
        Spinner spinnerOne = getActivity().findViewById(R.id.displayOtherSpinOne);
        Spinner spinnerTwo = getActivity().findViewById(R.id.displayOtherSpinTwo);

        ArrayAdapter<CharSequence> langAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.all_languages_edit, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> exticAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.exotic_languages_edit, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> gameAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.game_set_edit, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> artAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.artisan_tools_edit, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> thiefAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.thieve_tool, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> vehicleAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.vehicles, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> nullAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.nothing, android.R.layout.simple_spinner_item);

        switch (background){
            case "Acolyte":
                backgroundDesc.setText("You have spent your life in the service of a temple to a specific god or pantheon of gods. You act as an intermediary between the realm of the holy and the mortal world, performing sacred rites and offering sacrifices in order to conduct worshipers into the presence of the divine. You are not necessarily a cleric—performing sacred rites is not the same thing as channeling divine power.\n" +
                        "\n" +
                        "Choose a god, a pantheon of gods, or some other quasi-divine being, and work with your DM to detail the nature of your religious service. The Gods of the Multiverse section contains a sample pantheon, from the Forgotten Realms setting. Were you a lesser functionary in a temple, raised from childhood to assist the priests in the sacred rites? Or were you a high priest who suddenly experienced a call to serve your god in a different way? Perhaps you were the leader of a small cult outside of any established temple structure, or even an occult group that served a fiendish master that you now deny. ");

                skillOne.setText("Insight");
                skillTwo.setText("Religion");

                spinnerOne.setAdapter(langAdapter);
                spinnerOne.setClickable(true);
                spinnerTwo.setAdapter(langAdapter);
                spinnerTwo.setClickable(true);

                break;

            case "Criminal/Spy":
                backgroundDesc.setText("You are an experienced criminal with a history of breaking the law. You have spent a lot of time among other criminals and still have contacts within the criminal underworld. You’re far closer than most people to the world of murder, theft, and violence that pervades the underbelly of civilization, and you have survived up to this point by flouting the rules and regulations of society.");

                skillOne.setText("Deception");
                skillTwo.setText("Stealth");

                spinnerOne.setAdapter(thiefAdapter);
                spinnerOne.setClickable(false);
                spinnerTwo.setAdapter(gameAdapter);
                spinnerTwo.setClickable(true);

                break;

            case "Folk Hero":
                backgroundDesc.setText("You come from a humble social rank, but you are destined for so much more. Already the people of your home village regard you as their champion, and your destiny calls you to stand against the tyrants and monsters that threaten the common folk everywhere.");

                skillOne.setText("Animal Handling");
                skillTwo.setText("Survival");

                spinnerOne.setAdapter(vehicleAdapter);
                spinnerOne.setClickable(false);
                spinnerTwo.setAdapter(artAdapter);
                spinnerTwo.setClickable(true);

                break;

            case "Haunted One":
                backgroundDesc.setText("You are haunted by something so terrible that you dare not speak of it. You’ve tried to bury it and run away from it, to no avail. Whatever this thing is that haunts you can’t be slain with a sword or banished with a spell. It might come to you as a shadow on the wall, a bloodcurdling nightmare, a memory that refuses to die, or a demonic whisper in the dark. The burden has taken its toll, isolating you from most people and making you question your sanity. You must find a way to overcome it before it destroys you.");

                skillOne.setText("Arcana");
                skillTwo.setText("Investigation");


                spinnerOne.setAdapter(exticAdapter);
                spinnerOne.setClickable(true);
                spinnerTwo.setAdapter(nullAdapter);
                spinnerTwo.setClickable(false);

                break;

            case "Noble":
                backgroundDesc.setText("You understand wealth, power, and privilege. You carry a noble title, and your family owns land, collects taxes, and wields significant political influence. You might be a pampered aristocrat unfamiliar with work or discomfort, a former merchant just elevated to the nobility, or a disinherited scoundrel with a disproportionate sense of entitlement. Or you could be an honest, hard-working landowner who cares deeply about the people who live and work on your land, keenly aware of your responsibility to them.\n" +
                        "\n" +
                        "Work with your DM to come up with an appropriate title and determine how much authority that title carries. A noble title doesn’t stand on its own—it’s connected to an entire family, and whatever title you hold, you will pass it down to your own children. Not only do you need to determine your noble title, but you should also work with the DM to describe your family and their influence on you.\n" +
                        "\n" +
                        "Is your family old and established, or was your title only recently bestowed? How much influence do they wield, and over what area? What kind of reputation does your family have among the other aristocrats of the region? How do the common people regard them?\n" +
                        "\n" +
                        "What’s your position in the family? Are you the heir to the head of the family? Have you already inherited the title? How do you feel about that responsibility? Or are you so far down the line of inheritance that no one cares what you do, as long as you don’t embarrass the family? How does the head of your family feel about your adventuring career? Are you in your family’s good graces, or shunned by the rest of your family?\n" +
                        "\n" +
                        "Does your family have a coat of arms? An insignia you might wear on a signet ring? Particular colors you wear all the time? An animal you regard as a symbol of your line or even a spiritual member of the family?\n" +
                        "\n" +
                        "These details help establish your family and your title as features of the world of the campaign.");

                skillOne.setText("History");
                skillTwo.setText("Persuasion");

                spinnerOne.setAdapter(gameAdapter);
                spinnerOne.setClickable(true);
                spinnerTwo.setAdapter(langAdapter);
                spinnerTwo.setClickable(true);

                break;

            case "Sage":
                backgroundDesc.setText("You spent years learning the lore of the multiverse. You scoured manuscripts, studied scrolls, and listened to the greatest experts on the subjects that interest you. Your efforts have made you a master in your fields of study.");

                skillOne.setText("Arcana");
                skillTwo.setText("History");

                spinnerOne.setAdapter(langAdapter);
                spinnerOne.setClickable(true);
                spinnerTwo.setAdapter(langAdapter);
                spinnerTwo.setClickable(true);

                break;

            case "Soldier":
                backgroundDesc.setText("War has been your life for as long as you care to remember. You trained as a youth, studied the use of weapons and armor, learned basic survival techniques, including how to stay alive on the battlefield. You might have been part of a standing national army or a mercenary company, or perhaps a member of a local militia who rose to prominence during a recent war.\n" +
                        "\n" +
                        "When you choose this background, work with your DM to determine which military organization you were a part of, how far through its ranks you progressed, and what kind of experiences you had during your military career. Was it a standing army, a town guard, or a village militia? Or it might have been a noble’s or merchant’s private army, or a mercenary company.");

                skillOne.setText("Athletics");
                skillTwo.setText("Intimidation");

                spinnerOne.setAdapter(vehicleAdapter);
                spinnerOne.setClickable(false);
                spinnerTwo.setAdapter(gameAdapter);
                spinnerTwo.setClickable(true);

                break;
        }
    }

    private void getBackgroundFeat(String background){

        TextView backgroundFeat = getActivity().findViewById(R.id.displayBackgroundFeat);

        switch (background){
            case "Acolyte":
                backgroundFeat.setText("As an acolyte, you command the respect of those who share your faith, and you can perform the religious ceremonies of your deity. You and your adventuring companions can expect to receive free healing and care at a temple, shrine, or other established presence of your faith, though you must provide any material components needed for spells. Those who share your religion will support you (but only you) at a modest lifestyle.\n" +
                        "\n" +
                        "You might also have ties to a specific temple dedicated to your chosen deity or pantheon, and you have a residence there. This could be the temple where you used to serve, if you remain on good terms with it, or a temple where you have found a new home. While near your temple, you can call upon the priests for assistance, provided the assistance you ask for is not hazardous and you remain in good standing with your temple.");
                break;

            case "Criminal/Spy":
                backgroundFeat.setText("You have a reliable and trustworthy contact who acts as your liaison to a network of other criminals. You know how to get messages to and from your contact, even over great distances; specifically, you know the local messengers, corrupt caravan masters, and seedy sailors who can deliver messages for you.");
                break;

            case "Folk Hero":
                backgroundFeat.setText("Since you come from the ranks of the common folk, you fit in among them with ease. You can find a place to hide, rest, or recuperate among other commoners, unless you have shown yourself to be a danger to them. They will shield you from the law or anyone else searching for you, though they will not risk their lives for you.");
                break;

            case "Haunted One":
                backgroundFeat.setText("Those who look into your eyes can see that you have faced unimaginable horror and that you are no stranger to darkness. Though they might fear you, commoners will extend you every courtesy and do their utmost to help you. Unless you have shown yourself to be a danger to them, they will even take up arms to fight alongside you, should you find yourself facing an enemy alone.");
                break;

            case "Noble":
                backgroundFeat.setText("Thanks to your noble birth, people are inclined to think the best of you. You are welcome in high society, and people assume you have the right to be wherever you are. The common folk make every effort to accommodate you and avoid your displeasure, and other people of high birth treat you as a member of the same social sphere. You can secure an audience with a local noble if you need to.");
                break;

            case "Sage":
                backgroundFeat.setText("When you attempt to learn or recall a piece of lore, if you do not know that information, you often know where and from whom you can obtain it. Usually, this information comes from a library, scriptorium, university, or a sage or other learned person or creature. Your DM might rule that the knowledge you seek is secreted away in an almost inaccessible place, or that it simply cannot be found. Unearthing the deepest secrets of the multiverse can require an adventure or even a whole campaign.");
                break;

            case "Soldier":
                backgroundFeat.setText("You have a military rank from your career as a soldier. Soldiers loyal to your former military organization still recognize your authority and influence, and they defer to you if they are of a lower rank. You can invoke your rank to exert influence over other soldiers and requisition simple equipment or horses for temporary use. You can also usually gain access to friendly military encampments and fortresses where your rank is recognized.");
                break;
        }
    }

}

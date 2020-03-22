package com.example.dungeonsanddragonstraven.character_creation_manual_screens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dungeonsanddragonstraven.Character;
import com.example.dungeonsanddragonstraven.CharacterSelectionFrag;
import com.example.dungeonsanddragonstraven.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class ManualScreenThree extends Fragment {
    public static ManualScreenThree newInstance(Character current, String type) {

        Bundle args = new Bundle();
        args.putSerializable("Character", current);
        args.putString("Type", type);

        ManualScreenThree fragment = new ManualScreenThree();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setHasOptionsMenu(true);
        final Character currentCharacter = (Character) getArguments().getSerializable("Character");

        Button continueBtn = getActivity().findViewById(R.id.continueBtnScreenFour);
        ImageView backBtn = getActivity().findViewById(R.id.backBtnScreenThree);

        final Spinner backgroundSpinner = getActivity().findViewById(R.id.backgroundSpinner);

        backgroundSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getBackgroundDescription(backgroundSpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView backgroundDesc = getActivity().findViewById(R.id.backgroundDesc);
                TextView skillOne = getActivity().findViewById(R.id.skillOne);
                TextView skillTwo = getActivity().findViewById(R.id.skillTwo);
                Spinner spinnerOne = getActivity().findViewById(R.id.langSpinnerOne);
                Spinner spinnerTwo = getActivity().findViewById(R.id.langSpinnerTwo);

                currentCharacter.setBackground(backgroundSpinner.getSelectedItem().toString());
                currentCharacter.setBackgroundDesc(backgroundDesc.getText().toString());
                currentCharacter.setSkillProficOne(skillOne.getText().toString());
                currentCharacter.setSkillProficTwo(skillTwo.getText().toString());
                currentCharacter.setOtherProcOne(spinnerOne.getSelectedItem().toString());
                currentCharacter.setOtherProcTwo(spinnerTwo.getSelectedItem().toString());

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragcontainer, ManualScreenFour.newInstance(currentCharacter, getArguments().getString("Type"))).addToBackStack("Screen Four").commit();
            }
        });

        if (getArguments().getString("Type") == "Randomization"){
            Spinner spinnerOne = getActivity().findViewById(R.id.langSpinnerOne);
            Spinner spinnerTwo = getActivity().findViewById(R.id.langSpinnerTwo);

            for (int i = 0; i < backgroundSpinner.getCount(); i++){
                backgroundSpinner.setSelection(i);
                if (backgroundSpinner.getSelectedItem().toString().equals(String.valueOf(currentCharacter.background))){
                    getBackgroundDescription(backgroundSpinner.getSelectedItem().toString());
                    break;
                }
            }

            if (spinnerOne.getCount() > 1) {
                spinnerOne.setSelection(randomInt(spinnerOne.getCount()));
            }

            if (spinnerTwo.getCount() > 1) {
                spinnerTwo.setSelection(randomInt(spinnerTwo.getCount()));
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.manual_screen_three, container, false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragcontainer, CharacterSelectionFrag.newInstance()).commit();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.manual_creation_menu_one, menu);
    }

    private void getBackgroundDescription(String background){

        TextView backgroundDesc = getActivity().findViewById(R.id.backgroundDesc);
        TextView skillOne = getActivity().findViewById(R.id.skillOne);
        TextView skillTwo = getActivity().findViewById(R.id.skillTwo);
        Spinner spinnerOne = getActivity().findViewById(R.id.langSpinnerOne);
        Spinner spinnerTwo = getActivity().findViewById(R.id.langSpinnerTwo);

        ArrayAdapter<CharSequence> langAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.all_languages, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> exticAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.exotic_languages, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> gameAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.game_set, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> artAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.artisan_tools, android.R.layout.simple_spinner_item);
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

    public int randomInt(int max){
        final int min = 1;
        final int random = new Random().nextInt((max - min)) + min;

        return random;
    }
}

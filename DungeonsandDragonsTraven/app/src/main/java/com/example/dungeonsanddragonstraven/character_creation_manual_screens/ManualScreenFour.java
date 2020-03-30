package com.example.dungeonsanddragonstraven.character_creation_manual_screens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dungeonsanddragonstraven.Character;
import com.example.dungeonsanddragonstraven.CharacterSelectionFrag;
import com.example.dungeonsanddragonstraven.R;

import java.util.Random;

public class ManualScreenFour extends Fragment {
    public static ManualScreenFour newInstance(Character current, String type) {

        Bundle args = new Bundle();
        args.putSerializable("Character", current);
        args.putString("Type", type);

        ManualScreenFour fragment = new ManualScreenFour();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final Character current = (Character) getArguments().getSerializable("Character");
        setHasOptionsMenu(true);

        getBackgroundFeat(current.background);

        ImageView backBtn = getActivity().findViewById(R.id.backBtnScreenFour);
        Button continueBtn = getActivity().findViewById(R.id.continueBtnScreenFive);

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText hair = getActivity().findViewById(R.id.hairEdit);
                EditText eyes = getActivity().findViewById(R.id.eyesEdit);
                EditText skin = getActivity().findViewById(R.id.skinEdit);
                TextView backgroundFeat = getActivity().findViewById(R.id.backgroundFeatText);
                EditText faith = getActivity().findViewById(R.id.faithEdit);
                Spinner alignment = getActivity().findViewById(R.id.alignmentSpinner);
                Spinner lifeStyle = getActivity().findViewById(R.id.lifeStyleSpinner);

                if (hair.getText().toString().isEmpty() || eyes.getText().toString().isEmpty() || skin.getText().toString().isEmpty() ||
                    faith.getText().toString().isEmpty() || alignment.getSelectedItem().toString().equals("Select a alignment") || lifeStyle.getSelectedItem()
                        .toString().equals("Select a lifestyle")){
                    Toast.makeText(getActivity(), "One of the required fields are not filled out.", Toast.LENGTH_SHORT).show();

                } else {
                    current.setHair(hair.getText().toString());
                    current.setEyes(eyes.getText().toString());
                    current.setSkin(skin.getText().toString());
                    current.setFaith(faith.getText().toString());
                    current.setBackgroundFeat(backgroundFeat.getText().toString());
                    current.setAlignment(alignment.getSelectedItem().toString());
                    current.setLifeStyle(lifeStyle.getSelectedItem().toString());

                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragcontainer, ManualScreenFive.newInstance(current, getArguments().getString("Type"))).addToBackStack("Screen Five").commit();
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        if (getArguments().getString("Type") == "Randomization"){
            Spinner lifeStyle = getActivity().findViewById(R.id.lifeStyleSpinner);
            Spinner alignment = getActivity().findViewById(R.id.alignmentSpinner);

            lifeStyle.setSelection(randomInt(lifeStyle.getCount()));

            alignment.setSelection(randomInt(alignment.getCount()));
        }
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
        return inflater.inflate(R.layout.manual_screen_four, container, false);
    }

    private void getBackgroundFeat(String background){

        TextView backgroundFeat = getActivity().findViewById(R.id.backgroundFeatText);

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

    public int randomInt(int max){
        final int min = 1;
        final int random = new Random().nextInt((max - min)) + min;

        return random;
    }

}

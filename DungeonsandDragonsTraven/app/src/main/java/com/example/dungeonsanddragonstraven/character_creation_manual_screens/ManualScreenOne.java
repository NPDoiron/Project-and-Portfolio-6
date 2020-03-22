package com.example.dungeonsanddragonstraven.character_creation_manual_screens;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
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
import com.example.dungeonsanddragonstraven.util.BitmapConversions;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;

public class ManualScreenOne extends Fragment {
    private static final int PICK_IMAGE = 1;
    Character currentCharacter;

    public static ManualScreenOne newInstance(Character character, String type) {

        Bundle args = new Bundle();
        args.putSerializable("Creation", character);
        args.putString("Type", type);

        ManualScreenOne fragment = new ManualScreenOne();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setHasOptionsMenu(true);
        currentCharacter = (Character) getArguments().getSerializable("Creation");

        Button changeImageBtn = getActivity().findViewById(R.id.changeImageCreationBtn);
        final Spinner raceSpinner = getActivity().findViewById(R.id.raceSpinner);
        final Spinner classSpinner = getActivity().findViewById(R.id.classSpinner);
        Button nextScreen = getActivity().findViewById(R.id.nextScreenOneBtn);

        changeImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, PICK_IMAGE);
            }
        });

        raceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setRaceDesc(raceSpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        classSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setClassDesc(classSpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        nextScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (raceSpinner.getSelectedItem().toString().equals("Select a race") ||
                        classSpinner.getSelectedItem().toString().equals("Select a class")) {
                    Toast.makeText(getActivity(), "One of the required fields are not filled out.", Toast.LENGTH_SHORT).show();
                } else {
                    TextView classDesc = getActivity().findViewById(R.id.classDescText);
                    TextView raceDesc = getActivity().findViewById(R.id.raceDescText);

                    currentCharacter.setRace(raceSpinner.getSelectedItem().toString());
                    currentCharacter.setRaceDesc(raceDesc.getText().toString());
                    currentCharacter.setClassDesc(classDesc.getText().toString());
                    currentCharacter.setClassString(classSpinner.getSelectedItem().toString());

                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragcontainer, ManualScreenTwo.newInstance(currentCharacter, getArguments().getString("Type"))).addToBackStack("Screen Two").commit();
                }
            }
        });

        if (getArguments().getString("Type") == "Randomization"){
            for (int i = 0; i < raceSpinner.getCount(); i++){
                raceSpinner.setSelection(i);
                if (raceSpinner.getSelectedItem().toString() == currentCharacter.race){
                    setRaceDesc(raceSpinner.getSelectedItem().toString());
                    break;
                }
            }

            for (int i = 0; i < classSpinner.getCount(); i++){
                classSpinner.setSelection(i);
                if (classSpinner.getSelectedItem().toString() == currentCharacter.classString){
                    setClassDesc(classSpinner.getSelectedItem().toString());
                    break;
                }
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.manual_screen_one, container, false);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {

            ImageView characterImage = getActivity().findViewById(R.id.characterImage);
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getActivity().getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                characterImage.setImageBitmap(selectedImage);
                currentCharacter.setBitmapString(BitmapConversions.bitMapToString(selectedImage));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "Something went wrong.", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(getContext(), "No image selected.",Toast.LENGTH_LONG).show();
        }
    }

    private void setRaceDesc(String selectedRace){

        TextView raceDesc = getActivity().findViewById(R.id.raceDescText);

        switch (selectedRace){
            case "Aarakocra":
                raceDesc.setText("Sequestered in high mountains atop tall trees, the aarakocra, sometimes called birdfolk, evoke fear and wonder.\n" +
                        "\n" +
                        "Racial Traits\n" +
                        "+2 Dexterity, +1 Wisdom, Flight, Talons");
                break;

            case "Dragonborn":
                raceDesc.setText("Dragonborn look very much like dragons standing erect in humanoid form, though they lack wings or a tail.\n" +
                        "\n" +
                        "Racial Traits\n" +
                        "+2 Strength, +1 Charisma, Draconic Ancestry, Breath Weapon, Damage Resistance");
                break;

            case "Hill Dwarf":
                raceDesc.setText("Bold and hardy, dwarves are known as skilled warriors, miners, and workers of stone and metal.\n" +
                        "\n" +
                        "Racial Traits\n" +
                        "+2 Constitution, Darkvision, Dwarven Resilience, Dwarven Combat Training, Stonecunning");
                break;

            case "Mountain Dwarf":
                raceDesc.setText("Bold and hardy, dwarves are known as skilled warriors, miners, and workers of stone and metal.\n" +
                        "\n" +
                        "Racial Traits\n" +
                        "+2 Constitution, Darkvision, Dwarven Resilience, Dwarven Combat Training, Stonecunning");
                break;

            case "Eladrin Elf":
                raceDesc.setText("Elves are a magical people of otherworldly grace, living in the world but not entirely part of it.\n" +
                        "\n" +
                        "Racial Traits\n" +
                        "+2 Dexterity, Darkvision, Keen Senses, Fey Ancestry, Trance");
                break;

            case "Wood Elf":
                raceDesc.setText("Elves are a magical people of otherworldly grace, living in the world but not entirely part of it.\n" +
                        "\n" +
                        "Racial Traits\n" +
                        "+2 Dexterity, Darkvision, Keen Senses, Fey Ancestry, Trance\n" +
                        "\n" +
                        "As a wood elf, you have keen senses and intuition, and your fleet feet carry you quickly and stealthily through your native forests. This category includes the wild elves (grugach) of Greyhawk and the Kagonesti of Dragonlance, as well as the races called wood elves in Greyhawk and the Forgotten Realms. In Faerûn, wood elves (also called wild elves, green elves, or forest elves) are reclusive and distrusting of non-elves.\n" +
                        "\n" +
                        "Wood elves’ skin tends to be copperish in hue, sometimes with traces of green. Their hair tends toward browns and blacks, but it is occasionally blond or copper-colored. Their eyes are green, brown, or hazel.");
                break;

            case "High Elf":
                raceDesc.setText("Elves are a magical people of otherworldly grace, living in the world but not entirely part of it.\n" +
                        "\n" +
                        "Racial Traits\n" +
                        "+2 Dexterity, Darkvision, Keen Senses, Fey Ancestry, Trance\n" +
                        "\n" +
                        "As a high elf, you have a keen mind and a mastery of at least the basics of magic. In many of the worlds of D&D, there are two kinds of high elves. One type (which includes the gray elves and valley elves of Greyhawk, the Silvanesti of Dragonlance, and the sun elves of the Forgotten Realms) is haughty and reclusive, believing themselves to be superior to non-elves and even other elves. The other type (including the high elves of Greyhawk, the Qualinesti of Dragonlance, and the moon elves of the Forgotten Realms) are more common and more friendly, and often encountered among humans and other races.\n" +
                        "\n" +
                        "The sun elves of Faerûn (also called gold elves or sunrise elves) have bronze skin and hair of copper, black, or golden blond. Their eyes are golden, silver, or black. Moon elves (also called silver elves or gray elves) are much paler, with alabaster skin sometimes tinged with blue. They often have hair of silver-white, black, or blue, but various shades of blond, brown, and red are not uncommon. Their eyes are blue or green and flecked with gold.");
                break;

            case "Air Genasi":
                raceDesc.setText("Genasi carry the power of the elemental planes of air, earth, fire, and water in their blood.\n" +
                        "\n" +
                        "Racial Traits\n" +
                        "+2 Constitution, Air, Earth, Fire, Water Subraces\n" +
                        "\n" +
                        "As an air genasi, you are descended from the djinn. As changeable as the weather, your moods shift from calm to wild and violent with little warning, but these storms rarely last long.\n" +
                        "\n" +
                        "Air genasi typically have light blue skin, hair, and eyes. A faint but constant breeze accompanies them, tousling the hair and stirring the clothing. Some air genasi speak with breathy voices, marked by a faint echo. A few display odd patterns in their flesh or grow crystals from their scalps.");
                break;

            case "Water Genasi":
                raceDesc.setText("Genasi carry the power of the elemental planes of air, earth, fire, and water in their blood.\n" +
                        "\n" +
                        "Racial Traits\n" +
                        "+2 Constitution, Air, Earth, Fire, Water Subraces\n" +
                        "\n" +
                        "The lapping of waves, the spray of sea foam on the wind, the ocean depths—all of these things call to your heart. You wander freely and take pride in your independence, though others might consider you selfish.\n" +
                        "\n" +
                        "Most water genasi look as if they just finished bathing, with beads of moisture collecting on their skin and hair. They smell of fresh rain and clean water. Blue or green skin is common, and most have somewhat overlarge eyes, blue-black in color. A water genasi’s hair might float freely, swaying and waving as if underwater. Some have voices with undertones reminiscent of whale song or trickling streams.");
                break;

            case "Fire Genasi":
                raceDesc.setText("Genasi carry the power of the elemental planes of air, earth, fire, and water in their blood.\n" +
                        "\n" +
                        "Racial Traits\n" +
                        "+2 Constitution, Air, Earth, Fire, Water Subraces\n" +
                        "\n" +
                        "As a fire genasi, you have inherited the volatile mood and keen mind of the efreet. You tend toward impatience and making snap judgments. Rather than hide your distinctive appearance, you exult in it.\n" +
                        "\n" +
                        "Nearly all fire genasi are feverishly hot as if burning inside, an impression reinforced by flaming red, coal- black, or ash-gray skin tones. The more human-looking have fiery red hair that writhes under extreme emotion, while more exotic specimens sport actual flames dancing on their heads. Fire genasi voices might sound like crackling flames, and their eyes flare when angered. Some are accompanied by the faint scent of brimstone.");
                break;

            case "Earth Genasi":
                raceDesc.setText("Genasi carry the power of the elemental planes of air, earth, fire, and water in their blood.\n" +
                        "\n" +
                        "Racial Traits\n" +
                        "+2 Constitution, Air, Earth, Fire, Water Subraces\n" +
                        "\n" +
                        "As an earth genasi, you are descended from the cruel and greedy dao, though you aren’t necessarily evil. You have inherited some measure of control over earth, reveling in superior strength and solid power. You tend to avoid rash decisions, pausing long enough to consider your options before taking action.\n" +
                        "\n" +
                        "Elemental earth manifests differently from one individual to the next. Some earth genasi always have bits of dust falling from their bodies and mud clinging to their clothes, never getting clean no matter how often they bathe. Others are as shiny and polished as gemstones, with skin tones of deep brown or black, eyes sparkling like agates. Earth genasi can also have smooth metallic flesh, dull iron skin spotted with rust, a pebbled and rough hide, or even a coating of tiny embedded crystals. The most arresting have fissures in their flesh, from which faint light shines.");
                break;

            case "Deep Gnome":
                raceDesc.setText("A gnome’s energy and enthusiasm for living shines through every inch of his or her tiny body.\n" +
                        "\n" +
                        "Racial Traits\n" +
                        "+2 Intelligence, Darkvision, Gnome Cunning\n" +
                        "\n" +
                        "Forest gnomes and rock gnomes are the gnomes most commonly encountered in the lands of the surface world. There is another subrace of gnomes rarely seen by any surface-dweller: deep gnomes, also known as svirfneblin. Guarded, and suspicious of outsiders, svirfneblin are cunning and taciturn, but can be just as kind-hearted, loyal, and compassionate as their surface cousins.");
                break;

            case "Rock Gnome":
                raceDesc.setText("A gnome’s energy and enthusiasm for living shines through every inch of his or her tiny body.\n" +
                        "\n" +
                        "Racial Traits\n" +
                        "+2 Intelligence, Darkvision, Gnome Cunning\n" +
                        "\n" +
                        "As a rock gnome, you have a natural inventiveness and hardiness beyond that of other gnomes. Most gnomes in the worlds of D&D are rock gnomes, including the tinker gnomes of the Dragonlance setting.");
                break;

            case "Goliath":
                raceDesc.setText("Strong and reclusive, every day brings a new challenge to a goliath.\n" +
                        "\n" +
                        "Racial Traits\n" +
                        "+2 Strength, +1 Constitution, Natural Athlete, Stone's Endurance, Powerful Build, Mountain Born");
                break;

            case "Half-Elf":
                raceDesc.setText("Half-elves combine what some say are the best qualities of their elf and human parents.\n" +
                        "\n" +
                        "Racial Traits\n" +
                        "+2 Charisma, +1 to Two Other Ability Scores, Darkvision, Fey Ancestry, Skill Versatility");
                break;

            case "Half-Orc":
                raceDesc.setText("Half-orcs’ grayish pigmentation, sloping foreheads, jutting jaws, prominent teeth, and towering builds make their orcish heritage plain for all to see.\n" +
                        "\n" +
                        "Racial Traits\n" +
                        "+2 Strength, +1 Constitution, Darkvision, Menacing, Relentless Endurance, Savage Attacks");
                break;

            case "Lightfoot Halfling":
                raceDesc.setText("The diminutive halflings survive in a world full of larger creatures by avoiding notice or, barring that, avoiding offense.\n" +
                        "\n" +
                        "Racial Traits\n" +
                        "+2 Dexterity, Lucky, Brave, Halfling Nimbleness\n" +
                        "\n" +
                        "As a lightfoot halfling, you can easily hide from notice, even using other people as cover. You’re inclined to be affable and get along well with others. In the Forgotten Realms, lightfoot halflings have spread the farthest and thus are the most common variety.\n" +
                        "\n" +
                        "Lightfoots are more prone to wanderlust than other halflings, and often dwell alongside other races or take up a nomadic life. In the world of Greyhawk, these halflings are called hairfeet or tallfellows.");
                break;

            case "Stout Halfling":
                raceDesc.setText("The diminutive halflings survive in a world full of larger creatures by avoiding notice or, barring that, avoiding offense.\n" +
                        "\n" +
                        "Racial Traits\n" +
                        "+2 Dexterity, Lucky, Brave, Halfling Nimbleness\n" +
                        "\n" +
                        "As a stout halfling, you’re hardier than average and have some resistance to poison. Some say that stouts have dwarven blood. In the Forgotten Realms, these halflings are called stronghearts, and they’re most common in the south.");
                break;

            case "Human":
                raceDesc.setText("Humans are the most adaptable and ambitious people among the common races. Whatever drives them, humans are the innovators, the achievers, and the pioneers of the worlds.\n" +
                        "\n" +
                        "Racial Traits\n" +
                        "+1 to All Ability Scores, Extra Language");
                break;

            case "Tiefling":
                raceDesc.setText("To be greeted with stares and whispers, to suffer violence and insult on the street, to see mistrust and fear in every eye: this is the lot of the tiefling.\n" +
                        "\n" +
                        "Racial Traits\n" +
                        "+2 Charisma, +1 Intelligence, Darkvision, Hellish Resistance, Infernal Legacy");
                break;

            case "Aasimar":
                raceDesc.setText("Aasimar are placed in the world to serve as guardians of law and good. Their patrons expect them to strike at evil, lead by example, and further the cause of justice.\n" +
                        "\n" +
                        "Racial Traits\n" +
                        "+2 Charisma, Darkvision, Celestial Resistance, Healing Hands, Light Bearer\n" +
                        "\n" +
                        "This aasimar variant originally appeared in the Dungeon Master's Guide as an example for creating your own races.\n" +
                        "\n" +
                        "Whereas tieflings have fiendish blood in their veins, aasimar are the descendants of celestial beings. These folk generally appear as glorious humans with lustrous hair, flawless skin, and piercing eyes. Aasimar often attempt to pass as humans in order to right wrongs and defend goodness on the Material Plane without drawing undue attention to their celestial heritage. They strive to fit into society, although they usually rise to the top, becoming revered leaders and honorable heroes.");
                break;

            case "Select a race.":
                raceDesc.setText("");
                break;
        }
    }

    private void setClassDesc(String selectedClass){

        TextView classDesc = getActivity().findViewById(R.id.classDescText);

        switch (selectedClass){

            case "Barbarian":
             classDesc.setText("A fierce warrior of primitive background who can enter a battle rage\n" +
                     "\n" +
                     "Hit Die: d12\n" +
                     "Primary Ability: Strength\n" +
                     "Saves: Strength & Constitution ");
             break;

            case "Bard":
                classDesc.setText("An inspiring magician whose power echoes the music of creation\n" +
                        "\n" +
                        "Hit Die: d8\n" +
                        "Primary Ability: Charisma\n" +
                        "Saves: Dexterity & Charisma");
                break;

            case "Cleric":
                classDesc.setText("A priestly champion who wields divine magic in service of a higher power\n" +
                        "\n" +
                        "Hit Die: d8\n" +
                        "Primary Ability: Wisdom\n" +
                        "Saves: Wisdom & Charisma");
                break;

            case "Druid":
                classDesc.setText("A priest of the Old Faith, wielding the powers of nature and adopting animal forms\n" +
                        "\n" +
                        "Hit Die: d8\n" +
                        "Primary Ability: Wisdom\n" +
                        "Saves: Intelligence & Wisdom");
                break;

            case "Fighter":
                classDesc.setText("A master of martial combat, skilled with a variety of weapons and armor\n" +
                        "\n" +
                        "Hit Die: d10\n" +
                        "Primary Ability: Strength or Dexterity\n" +
                        "Saves: Strength & Constitution");
                break;

            case "Monk":
                classDesc.setText("A master of martial arts, harnessing the power of the body in pursuit of physical and spiritual perfection\n" +
                        "\n" +
                        "Hit Die: d8\n" +
                        "Primary Ability: Dexterity & Wisdom\n" +
                        "Saves: Strength & Dexterity");
                break;

            case "Paladin":
                classDesc.setText("A holy warrior bound to a sacred oath\n" +
                        "\n" +
                        "Hit Die: d10\n" +
                        "Primary Ability: Strength & Charisma\n" +
                        "Saves: Wisdom & Charisma");
                break;

            case "Ranger":
                classDesc.setText("A warrior who combats threats on the edges of civilization\n" +
                        "\n" +
                        "Hit Die: d10\n" +
                        "Primary Ability: Dexterity & Wisdom\n" +
                        "Saves: Strength & Dexterity");
                break;

            case "Rogue":
                classDesc.setText("A scoundrel who uses stealth and trickery to overcome obstacles and enemies\n" +
                        "\n" +
                        "Hit Die: d8\n" +
                        "Primary Ability: Dexterity\n" +
                        "Saves: Dexterity & Intelligence");
                break;

            case "Sorcerer":
                classDesc.setText("A spellcaster who draws on inherent magic from a gift or bloodline\n" +
                        "\n" +
                        "Hit Die: d6\n" +
                        "Primary Ability: Charisma\n" +
                        "Saves: Constitution & Charisma");
                break;

            case "Warlock":
                classDesc.setText("A wielder of magic that is derived from a bargain with an extraplanar entity\n" +
                        "\n" +
                        "Hit Die: d8\n" +
                        "Primary Ability: Charisma\n" +
                        "Saves: Wisdom & Charisma");
                break;

            case "Wizard":
                classDesc.setText("A scholarly magic-user capable of manipulating the structures of reality\n" +
                        "\n" +
                        "Hit Die: d6\n" +
                        "Primary Ability: Intelligence\n" +
                        "Saves: Intelligence & Wisdom");
                break;

            case "Select a class":
                classDesc.setText("");
                break;
        }
    }
}

package com.example.dungeonsanddragonstraven;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.dungeonsanddragonstraven.menu_screens.MainMenu;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragcontainer, MainMenu.newInstance()).commit();
    }
}

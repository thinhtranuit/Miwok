package com.thinhtranit1601.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        ArrayList<Word> colors = new ArrayList<Word>();
        colors.add(new Word("weṭeṭṭi","Red",R.drawable.color_red));
        colors.add(new Word("Chokokki","Green",R.drawable.color_green));
        colors.add(new Word("ṭakaakki","Brown",R.drawable.color_brown));
        colors.add(new Word("ṭopoppi","Gray",R.drawable.color_gray));
        colors.add(new Word("Kulilli","Black",R.drawable.color_black));
        colors.add(new Word("Kelelli","White",R.drawable.color_white));
        colors.add(new Word("ṭopiisә","Dusty yellow",R.drawable.color_dusty_yellow));
        colors.add(new Word("chiwiiṭә","Mustard yellow",R.drawable.color_mustard_yellow));

        WordAdapter wordAdapter = new WordAdapter(this, colors);
        ListView listView = (ListView) findViewById(R.id.rootView);
        listView.setAdapter(wordAdapter);
    }
}

package com.thinhtranit1601.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);

        ArrayList<Word> phrases = new ArrayList<>();
        phrases.add(new Word("minto wuksus","Where are you going?",R.drawable.sample));
        phrases.add(new Word("tinnә oyaase'nә","What is your name?",R.drawable.sample));
        phrases.add(new Word("oyaaset...","My name is...",R.drawable.sample));
        phrases.add(new Word("michәksәs?","How are you feeling?",R.drawable.sample));
        phrases.add(new Word("kuchi achit","I'm feeling good",R.drawable.sample));
        phrases.add(new Word("әәnәs'aa?","Are you coming?",R.drawable.sample));
        phrases.add(new Word("hәә’ әәnәm","Yes, i'm coming",R.drawable.sample));
        phrases.add(new Word("әәnәm","I'm coming",R.drawable.sample));
        phrases.add(new Word("yoowutis","Let's go",R.drawable.sample));
        phrases.add(new Word("әnni'nem","Come here",R.drawable.sample));

        WordAdapter wordAdapter = new WordAdapter(this, phrases);
        ListView listView = (ListView)findViewById(R.id.rootView);
        listView.setAdapter(wordAdapter);
    }
}

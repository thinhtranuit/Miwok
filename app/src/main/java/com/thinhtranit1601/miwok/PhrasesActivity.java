package com.thinhtranit1601.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);

        ArrayList<Word> phrases = new ArrayList<>();
        phrases.add(new Word("minto wuksus","Where are you going?",R.raw.phrase_where_are_you_going));
        phrases.add(new Word("tinnә oyaase'nә","What is your name?",R.raw.phrase_what_is_your_name));
        phrases.add(new Word("oyaaset...","My name is...",R.raw.phrase_my_name_is));
        phrases.add(new Word("michәksәs?","How are you feeling?",R.raw.phrase_how_are_you_feeling));
        phrases.add(new Word("kuchi achit","I'm feeling good",R.raw.phrase_im_feeling_good));
        phrases.add(new Word("әәnәs'aa?","Are you coming?",R.raw.phrase_are_you_coming));
        phrases.add(new Word("hәә’ әәnәm","Yes, i'm coming",R.raw.phrase_yes_im_coming));
        phrases.add(new Word("әәnәm","I'm coming",R.raw.phrase_im_coming));
        phrases.add(new Word("yoowutis","Let's go",R.raw.phrase_lets_go));
        phrases.add(new Word("әnni'nem","Come here",R.raw.phrase_come_here));

        WordAdapter wordAdapter = new WordAdapter(this, phrases,R.color.category_phrases);
        ListView listView = (ListView)findViewById(R.id.rootView);
        listView.setAdapter(wordAdapter);
    }
}

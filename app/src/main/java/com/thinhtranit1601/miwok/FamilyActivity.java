package com.thinhtranit1601.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);

        ArrayList<Word> familyMembers = new ArrayList<Word>();
        familyMembers.add(new Word("әpә","Father",R.raw.family_father,R.drawable.family_father));
        familyMembers.add(new Word("әṭa","Mother",R.raw.family_mother,R.drawable.family_mother));
        familyMembers.add(new Word("Angsi","Son",R.raw.family_son,R.drawable.family_son));
        familyMembers.add(new Word("Tune","Daughter",R.raw.family_daughter,R.drawable.family_daughter));
        familyMembers.add(new Word("Taachi","Older brother",R.raw.family_older_brother,R.drawable.family_older_brother));
        familyMembers.add(new Word("Chalitti","Younger brother",R.raw.family_younger_brother,R.drawable.family_younger_brother));
        familyMembers.add(new Word("teṭe","Older sister",R.raw.family_older_sister,R.drawable.family_older_sister));
        familyMembers.add(new Word("Kolliti","Younger sister",R.raw.family_younger_sister,R.drawable.family_younger_sister));
        familyMembers.add(new Word("Ama","Grandmother",R.raw.family_grandmother,R.drawable.family_grandmother));
        familyMembers.add(new Word("Paapa","Grandfather",R.raw.family_grandfather,R.drawable.family_grandfather));

        ListView view = (ListView)findViewById(R.id.rootView);
        WordAdapter wordAdapter = new WordAdapter(this, familyMembers,R.color.category_family);
        view.setAdapter(wordAdapter);
    }
}

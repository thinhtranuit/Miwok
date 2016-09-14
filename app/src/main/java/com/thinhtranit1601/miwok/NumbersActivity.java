package com.thinhtranit1601.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("Lutti","One",R.raw.number_one,R.drawable.number_one));
        words.add(new Word("Otiiko","Two",R.raw.number_two,R.drawable.number_two));
        words.add(new Word("Tolookosu","Three",R.raw.number_three,R.drawable.number_three));
        words.add(new Word("Oyyisa","Four",R.raw.number_four,R.drawable.number_four));
        words.add(new Word("Massokka","Five",R.raw.number_five,R.drawable.number_five));
        words.add(new Word("Temmokka","Six",R.raw.number_six,R.drawable.number_six));
        words.add(new Word("Kenekaku","Seven",R.raw.number_seven,R.drawable.number_seven));
        words.add(new Word("Kawinta","Eight",R.raw.number_eight,R.drawable.number_eight));
        words.add(new Word("Wo'e","Nine",R.raw.number_nine,R.drawable.number_nine));
        words.add(new Word("Na'aacha","Ten",R.raw.number_ten,R.drawable.number_ten));

        ListView rootView = (ListView) findViewById(R.id.rootView);
        WordAdapter numbersAdapter = new WordAdapter(this, words,R.color.category_numbers);
        rootView.setAdapter(numbersAdapter);
    }
}

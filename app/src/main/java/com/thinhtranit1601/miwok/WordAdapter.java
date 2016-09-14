package com.thinhtranit1601.miwok;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by thinh on 09/09/2016.
 */
public class WordAdapter extends ArrayAdapter<Word> {
    private int backgroundColor;

    public WordAdapter(Activity context, ArrayList<Word> words, int backgroundColor){
        super(context, R.layout.list_item , words);
        this.backgroundColor = backgroundColor;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            view = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        Word word = getItem(position);

        //make image for imageView to Describe the Word
        ImageView imageView = (ImageView) view.findViewById(R.id.logo_image_view);
        if (word.getImageResource() == 0){
            imageView.setVisibility(View.GONE);
        } else {
            imageView.setImageResource(word.getImageResource());
        }

        //set text with Miwok word
        TextView miwokTextView = (TextView) view.findViewById(R.id.miwok_text_view);
        miwokTextView.setText(word.getMiwokWord());

        //set text with English word
        TextView englishTextView = (TextView) view.findViewById(R.id.english_text_view);
        englishTextView.setText(word.getEnglishWord());

        //set sound for Word
        final MediaPlayer sound = MediaPlayer.create(getContext(), word.getRawResource());
        View root = view.findViewById(R.id.root);
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sound.start();
                Toast.makeText(getContext(), "Playing sound", Toast.LENGTH_SHORT).show();
            }
        });

        //set background color for the list_view
        LinearLayout rootTextView = (LinearLayout) view.findViewById(R.id.root_Text_View);
        int color = ContextCompat.getColor(getContext(),this.backgroundColor);
        rootTextView.setBackgroundColor(color);

        return view;
    }
}

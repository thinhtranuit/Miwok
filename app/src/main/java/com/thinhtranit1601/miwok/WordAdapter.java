package com.thinhtranit1601.miwok;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by thinh on 09/09/2016.
 */
public class WordAdapter extends ArrayAdapter<Word> {

    public WordAdapter(Activity context, ArrayList<Word> words){
        super(context, R.layout.list_item , words);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            view = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        Word word = getItem(position);

        ImageView imageView = (ImageView) view.findViewById(R.id.logo_image_view);
        imageView.setImageResource(word.getImageResource());

        TextView miwokTextView = (TextView) view.findViewById(R.id.miwok_text_view);
        miwokTextView.setText(word.getMiwokWord());

        TextView englishTextView = (TextView) view.findViewById(R.id.english_text_view);
        englishTextView.setText(word.getEnglishWord());

        return view;
    }
}

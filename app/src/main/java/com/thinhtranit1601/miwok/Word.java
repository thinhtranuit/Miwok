package com.thinhtranit1601.miwok;


/**
 * Created by thinh on 09/09/2016.
 */
public class Word {
    private String miwokWord;
    private String englishWord;
    private int imageResource;

    public Word(String miwok, String english, int image) {
        this.englishWord = english;

        this.miwokWord = miwok;
        this.imageResource = image;
    }

    @Override
    public String toString() {
        return (this.miwokWord + "\n" + this.englishWord);
    }

    public String getMiwokWord() {
        return miwokWord;
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public int getImageResource() {
        return imageResource;
    }
}

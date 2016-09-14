package com.thinhtranit1601.miwok;


/**
 * Created by thinh on 09/09/2016.
 */
public class Word {
    private String miwokWord;
    private String englishWord;
    private int imageResource;
    private int rawResource;

    public Word(String miwok, String english,int raw, int image) {
        this.englishWord = english;
        this.miwokWord = miwok;
        this.imageResource = image;
        this.rawResource = raw;
    }

    public Word(String miwokWord, String englishWord, int raw) {
        this.miwokWord = miwokWord;
        this.englishWord = englishWord;
        this.rawResource = raw;
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

    public int getRawResource() {
        return rawResource;
    }
}

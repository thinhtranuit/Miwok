package com.thinhtranit1601.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {
    /**
     * Reference of MediaPlayer used to play sound
     */
    private MediaPlayer mediaPlayer;

    /**
     * Reference of AudioManager used to focus Audio
     */
    private AudioManager audioManager;

    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we get a phone call).
     */
    private AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        mediaPlayer.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        releaseMediaPlayer();
                    }
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        final ArrayList<Word> colors = new ArrayList<Word>();
        colors.add(new Word("weṭeṭṭi","Red",R.raw.color_red,R.drawable.color_red));
        colors.add(new Word("Chokokki","Green",R.raw.color_green,R.drawable.color_green));
        colors.add(new Word("ṭakaakki","Brown",R.raw.color_brown,R.drawable.color_brown));
        colors.add(new Word("ṭopoppi","Gray",R.raw.color_gray,R.drawable.color_gray));
        colors.add(new Word("Kulilli","Black",R.raw.color_black,R.drawable.color_black));
        colors.add(new Word("Kelelli","White",R.raw.color_white,R.drawable.color_white));
        colors.add(new Word("ṭopiisә","Dusty yellow",R.raw.color_dusty_yellow,R.drawable.color_dusty_yellow));
        colors.add(new Word("chiwiiṭә","Mustard yellow",R.raw.color_mustard_yellow,R.drawable.color_mustard_yellow));

        WordAdapter wordAdapter = new WordAdapter(this, colors, R.color.category_colors);
        final ListView listView = (ListView) findViewById(R.id.rootView);
        listView.setAdapter(wordAdapter);

        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();

                Word word = colors.get(i);

                // Request audio focus for playback
                int result = audioManager.requestAudioFocus(onAudioFocusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(ColorsActivity.this, word.getRawResource());
                    mediaPlayer.start();
                }
            }
        });
    }

    /**
     * This method to release the MediaPlayer reference which aren't used anymore
     */
    private void releaseMediaPlayer(){
        if (this.mediaPlayer != null){
            this.mediaPlayer.release();
            this.mediaPlayer = null;
            this.audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.releaseMediaPlayer();
    }
}

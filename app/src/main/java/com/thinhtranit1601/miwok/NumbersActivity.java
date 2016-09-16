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

public class NumbersActivity extends AppCompatActivity {
    /**
     * Reference of MediaPlayer used to play sound
     */
    private MediaPlayer mediaPlayer;

    /**
     * Reference of AudioManager used to focus Audio
     */
    private AudioManager audioManager;

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
        setContentView(R.layout.activity_numbers);

        final ArrayList<Word> numbers = new ArrayList<Word>();
        numbers.add(new Word("Lutti","One",R.raw.number_one,R.drawable.number_one));
        numbers.add(new Word("Otiiko","Two",R.raw.number_two,R.drawable.number_two));
        numbers.add(new Word("Tolookosu","Three",R.raw.number_three,R.drawable.number_three));
        numbers.add(new Word("Oyyisa","Four",R.raw.number_four,R.drawable.number_four));
        numbers.add(new Word("Massokka","Five",R.raw.number_five,R.drawable.number_five));
        numbers.add(new Word("Temmokka","Six",R.raw.number_six,R.drawable.number_six));
        numbers.add(new Word("Kenekaku","Seven",R.raw.number_seven,R.drawable.number_seven));
        numbers.add(new Word("Kawinta","Eight",R.raw.number_eight,R.drawable.number_eight));
        numbers.add(new Word("Wo'e","Nine",R.raw.number_nine,R.drawable.number_nine));
        numbers.add(new Word("Na'aacha","Ten",R.raw.number_ten,R.drawable.number_ten));

        ListView listView = (ListView) findViewById(R.id.rootView);
        WordAdapter numbersAdapter = new WordAdapter(this, numbers,R.color.category_numbers);
        listView.setAdapter(numbersAdapter);

        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();

                Word word = numbers.get(i);

                // Request audio focus for playback
                int result = audioManager.requestAudioFocus(onAudioFocusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getRawResource());
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

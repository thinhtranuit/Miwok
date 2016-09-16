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

public class PhrasesActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_phrases);

        final ArrayList<Word> phrases = new ArrayList<>();
        phrases.add(new Word("minto wuksus", "Where are you going?", R.raw.phrase_where_are_you_going));
        phrases.add(new Word("tinnә oyaase'nә", "What is your name?", R.raw.phrase_what_is_your_name));
        phrases.add(new Word("oyaaset...", "My name is...", R.raw.phrase_my_name_is));
        phrases.add(new Word("michәksәs?", "How are you feeling?", R.raw.phrase_how_are_you_feeling));
        phrases.add(new Word("kuchi achit", "I'm feeling good", R.raw.phrase_im_feeling_good));
        phrases.add(new Word("әәnәs'aa?", "Are you coming?", R.raw.phrase_are_you_coming));
        phrases.add(new Word("hәә’ әәnәm", "Yes, i'm coming", R.raw.phrase_yes_im_coming));
        phrases.add(new Word("әәnәm", "I'm coming", R.raw.phrase_im_coming));
        phrases.add(new Word("yoowutis", "Let's go", R.raw.phrase_lets_go));
        phrases.add(new Word("әnni'nem", "Come here", R.raw.phrase_come_here));

        WordAdapter wordAdapter = new WordAdapter(this, phrases, R.color.category_phrases);
        ListView listView = (ListView) findViewById(R.id.rootView);
        listView.setAdapter(wordAdapter);

        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();

                Word word = phrases.get(i);

                // Request audio focus for playback
                int result = audioManager.requestAudioFocus(onAudioFocusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(PhrasesActivity.this, word.getRawResource());
                    mediaPlayer.start();
                }
            }
        });
    }

    /**
     * This method to release the MediaPlayer reference which aren't used anymore
     */
    private void releaseMediaPlayer() {
        if (this.mediaPlayer != null) {
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

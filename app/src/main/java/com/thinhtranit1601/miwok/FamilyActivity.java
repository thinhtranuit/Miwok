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

public class FamilyActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_family);

        final ArrayList<Word> familyMembers = new ArrayList<Word>();
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

        ListView listView = (ListView)findViewById(R.id.rootView);
        WordAdapter wordAdapter = new WordAdapter(this, familyMembers,R.color.category_family);
        listView.setAdapter(wordAdapter);

        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();

                Word word = familyMembers.get(i);

                // Request audio focus for playback
                int result = audioManager.requestAudioFocus(onAudioFocusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(FamilyActivity.this, word.getRawResource());
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

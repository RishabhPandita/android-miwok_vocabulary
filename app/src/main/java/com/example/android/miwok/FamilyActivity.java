package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {
    private MediaPlayer newMediaPlayer;
    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {

        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };
    private AudioManager mAudioManager;
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {

                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        newMediaPlayer.pause();
                        newMediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        releaseMediaPlayer();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        newMediaPlayer.start();
                    }
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.world_list);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("father", "minto wuksus", R.drawable.family_father, R.raw.family_father));
        words.add(new Word("mother", "tinnә oyaase'nә", R.drawable.family_mother, R.raw.family_mother));
        words.add(new Word("son.", "oyaaset..", R.drawable.family_son, R.raw.family_son));
        words.add(new Word("daughter", "michәksәs?", R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new Word("older brother.", "kuchi achit", R.drawable.family_older_brother, R.raw.family_older_brother));
        words.add(new Word("younger brother", "әәnәs'aa?", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        words.add(new Word("older sister", "hәә’ әәnәm", R.drawable.family_older_sister, R.raw.family_older_sister));
        words.add(new Word("younger sister", "әәnәm", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        words.add(new Word("grandmother", "yoowutis", R.drawable.family_grandmother, R.raw.family_grandmother));
        words.add(new Word("grandfather", "әnni'nem", R.drawable.family_grandfather, R.raw.family_grandfather));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_family);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    newMediaPlayer = MediaPlayer.create(FamilyActivity.this, words.get(i).getmAudioResourceID());
                    newMediaPlayer.start();
                    newMediaPlayer.setOnCompletionListener(onCompletionListener);
                }
            }
        }

            );

        }

        @Override
        protected void onStop () {
            super.onStop();
            releaseMediaPlayer();
        }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (newMediaPlayer != null) {
            newMediaPlayer.release();
            newMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}

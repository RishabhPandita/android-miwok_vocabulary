package com.example.android.miwok;

import android.media.Image;

import static android.R.string.no;

/**
 * Created by Rishabh on 14-05-2017.
 */

public class Word {

    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mImageResourceID = noImageResourceID;
    private static final int noImageResourceID = 0;

    private int mAudioResourceID = noAudioResourceID;

    public int getmAudioResourceID() {
        return mAudioResourceID;
    }

    public void setmAudioResourceID(int mAudioResourceID) {
        this.mAudioResourceID = mAudioResourceID;
    }

    private static final int noAudioResourceID = 0;


    public Word(String defaultTranslation, String miwokTranslation, int imgResorceId, int audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceID = imgResorceId;
        mAudioResourceID = audioResourceId;
    }

    public Word(String defaultTranslation, String miwokTranslation, int audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudioResourceID = audioResourceId;
    }

    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getmMiwokTranslation() {
        return mMiwokTranslation;
    }

    public void setmMiwokTranslation(String mMiwokTranslation) {
        this.mMiwokTranslation = mMiwokTranslation;
    }

    public void setmDefaultTranslation(String mDefaultTranslation) {
        this.mDefaultTranslation = mDefaultTranslation;
    }

    @Override
    public String toString() {
        return "Word{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", mImageResourceID=" + mImageResourceID +
                ", mAudioResourceID=" + mAudioResourceID +
                '}';
    }

    public int getmImageResourceID() {
        return mImageResourceID;
    }

    public void setmImageResourceID(int mIcon) {
        this.mImageResourceID = mIcon;
    }

    public boolean hasImageResourceId() {
        return !(this.mImageResourceID == noImageResourceID);
    }
}

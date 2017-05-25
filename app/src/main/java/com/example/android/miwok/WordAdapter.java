package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.attr.resource;
import static android.R.attr.text;

/**
 * Created by Rishabh on 14-05-2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {
    private int mcolorRsrId ;

    public WordAdapter(Context context, ArrayList<Word> words, int colorId) {
        super(context, 0, words);
        mcolorRsrId =  colorId;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Word word = getItem(position);

        TextView defaultWord = (TextView) listItemView.findViewById(R.id.english_word);
        defaultWord.setText(word.getmDefaultTranslation());

        TextView translatedWord = (TextView) listItemView.findViewById(R.id.mewok_word);
        translatedWord.setText(word.getmMiwokTranslation());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.imageThumb);

        if (word.hasImageResourceId()) {
            imageView.setImageResource(word.getmImageResourceID());
            imageView.setVisibility(View.VISIBLE);
        }
        else
            imageView.setVisibility(View.GONE);

        View textContainer = listItemView.findViewById(R.id.list_item1);
        int color= ContextCompat.getColor(getContext(),mcolorRsrId);
        textContainer.setBackgroundColor(color);
        return listItemView;
    }
}

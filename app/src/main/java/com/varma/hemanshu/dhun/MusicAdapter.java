package com.varma.hemanshu.dhun;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MusicAdapter extends ArrayAdapter<Music> {

    public MusicAdapter(Activity context, ArrayList<Music> music){
        super(context,0, music);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent, false);
        }

        Music currentMusic = getItem(position);

        //Fetching Thumbnail of the song and populating it into ListView
        ImageView imageResource = (ImageView) listItemView.findViewById(R.id.albumThumbnail);
        imageResource.setImageResource(currentMusic.getImageResourceID());

        //Fetching Song name and populating into ListView
        TextView musicName = (TextView) listItemView.findViewById(R.id.songName);
        musicName.setText(currentMusic.getMusicName());

        //Fetching Artist name and populating into ListView
        TextView artistName = (TextView) listItemView.findViewById(R.id.songArtist);
        artistName.setText(currentMusic.getArtistName());

        return listItemView;
    }
}

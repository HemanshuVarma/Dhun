package com.varma.hemanshu.dhun;

public class Music {

    //Image Resource of Music Thumbnail
    private int mImageResourceId;
    //Name of Song
    private String mMusicName;
    //Name of Album
    private String mArtistName;

    //Getter for Music Thumbnail
    public int getImageResourceID() {
        return mImageResourceId;
    }

    //Getters for Music Name
    public String getMusicName() {
        return mMusicName;
    }

    //Getters for Music Artist
    public String getArtistName() {
        return mArtistName;
    }

    //Setting values when Invoked from View
    public Music(int imageResourceId, String musicName, String artistName) {
        mImageResourceId = imageResourceId;
        mMusicName = musicName;
        mArtistName = artistName;
    }
}

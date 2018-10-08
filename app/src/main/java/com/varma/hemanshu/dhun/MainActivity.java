package com.varma.hemanshu.dhun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private SlidingUpPanelLayout mLayout;
    boolean isPlaying;
    boolean isFavourite;

    ListView musicList;
    TextView fetchedMusicName;
    ImageView fetchedImageView;
    ImageView shuffleButtonView;
    ImageView playPreviousButtonView;
    ImageView play_pauseNowPlayingView;
    ImageView play_pauseBottomSheetView;
    ImageView playNextButtonView;
    ImageView fav_unfavButtonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shuffleButtonView = findViewById(R.id.shuffle);
        playPreviousButtonView = findViewById(R.id.playPrevious);
        play_pauseNowPlayingView = findViewById(R.id.play_pause_now_playing);
        play_pauseBottomSheetView = findViewById(R.id.play_pause_state);
        playNextButtonView = findViewById(R.id.playNext);
        fav_unfavButtonView = findViewById(R.id.favourite);

        //ArrayList of Music
        final ArrayList<Music> music = new ArrayList<>();

        //Adding music which contains Thumbnail, Name and it's Artist
        music.add(new Music(R.drawable.alone_marshmellow_cover, "Alone", "Alan Walker"));
        music.add(new Music(R.drawable.backbone_hardysandhu_cover, "Backbone", "Hardy Sandhu"));
        music.add(new Music(R.drawable.burn_kshmr_cover, "BURN", "KSHMR & Dallask"));
        music.add(new Music(R.drawable.daru_badnam_cover, "Daru Badnam", "Param Singh & Kamal Kahlon"));
        music.add(new Music(R.drawable.friends_anne_marie_cover, "F.R.I.E.N.D.S", "Anne Marie & Marshmello"));
        music.add(new Music(R.drawable.girls_like_you_cover, "Girls Like You", "Maroon 5"));
        music.add(new Music(R.drawable.heartless_one_cover, "Heartless", "Badshah"));
        music.add(new Music(R.drawable.joker_hardysandhu_cover, "Joker", "Hardy Sandhu"));
        music.add(new Music(R.drawable.lag_ja_gale_cover, "Lag Ja Gale", "Lata Mangeshkar"));
        music.add(new Music(R.drawable.soch_na_sake_cover, "Soch Na Sake", "Amaal Mallik"));

        //Adapter for the list
        final MusicAdapter adapter = new MusicAdapter(this, music);

        //Inflating ListView
        musicList = findViewById(R.id.MusicList);
        //Setting the Adapter for Word Class
        musicList.setAdapter(adapter);

        //Linking Name TextView for setting in BottomSheet View
        fetchedMusicName = findViewById(R.id.name_of_song);
        //Linking Thumbnail ImageView for setting in Now Playing Activity
        fetchedImageView = findViewById(R.id.Thumbnail_UI);

        //onClickListener for for setting the Name and Thumbnail when any Item from ListView is clicked
        musicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Setting the Text
                fetchedMusicName.setText(music.get(position).getMusicName());
                //Setting the Image
                fetchedImageView.setImageResource((music.get(position).getImageResourceID()));
            }
        });


        //onClickListener for Shuffle Button
        shuffleButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, R.string.shuffle_toast, Toast.LENGTH_SHORT).show();
            }
        });

        //onClickListener for Play Previous Button
        playPreviousButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, R.string.play_previous_toast, Toast.LENGTH_SHORT).show();
            }
        });

        //onClickListener for Play/Pause Button in BottomSheet Activity
        play_pauseBottomSheetView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePlayPauseImage();
            }
        });

        //onClickListener for Play/Pause Button in Now Playing Activity
        play_pauseNowPlayingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePlayPauseImage();
            }
        });

        //onClickListener for Play Next Button
        playNextButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, R.string.play_next_toast, Toast.LENGTH_SHORT).show();
            }
        });

        //onClickListener for Favourite/Unfavourite Button
        fav_unfavButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFavourite) {
                    fav_unfavButtonView.setImageResource(R.drawable.round_favorite_border_black_36);
                    Toast.makeText(MainActivity.this, R.string.unfavourite_toast, Toast.LENGTH_SHORT).show();
                    isFavourite = false;
                } else {
                    fav_unfavButtonView.setImageResource(R.drawable.round_favorite_black_36);
                    Toast.makeText(MainActivity.this, R.string.favourite_toast, Toast.LENGTH_SHORT).show();
                    isFavourite = true;
                }
            }
        });

        mLayout = findViewById(R.id.sliding_layout);
        mLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Log.i(TAG, "onPanelSlide, offset " + slideOffset);
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                Log.i(TAG, "onPanelStateChanged " + newState);
            }
        });
        mLayout.setFadeOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        });
    }

    public void updatePlayPauseImage() {
        if (isPlaying) {
            play_pauseNowPlayingView.setImageResource(R.drawable.round_pause_black_48);
            play_pauseBottomSheetView.setImageResource(R.drawable.round_pause_black_48);
            Toast.makeText(MainActivity.this, R.string.play_toast, Toast.LENGTH_SHORT).show();
            isPlaying = false;
        } else {
            play_pauseNowPlayingView.setImageResource(R.drawable.round_play_arrow_black_48);
            play_pauseBottomSheetView.setImageResource(R.drawable.round_play_arrow_black_48);
            Toast.makeText(MainActivity.this, R.string.pause_toast, Toast.LENGTH_SHORT).show();
            isPlaying = true;
        }

    }

    @Override
    public void onBackPressed() {
        if (mLayout != null &&
                (mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED || mLayout.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED)) {
            mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        } else {
            super.onBackPressed();
        }
    }
}

package com.vivan.info.world.vivdevcomp.masterpackage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.vivan.info.world.vivdevcomp.R;

import java.util.concurrent.Callable;

public class YoutubeActivity extends AdsClass {

    YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);
        showBannerAds();
        loadInterastialAds();
        String myurl = getIntent().getStringExtra("url");

        youTubePlayerView = (YouTubePlayerView)findViewById(R.id.youtube_player_view);
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = myurl;
                youTubePlayer.loadVideo(videoId, 0);
            }
        });
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        youTubePlayerView.release();
    }


    @Override
    public void onBackPressed() {
        showInterstitialAds(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                finish();
                return null;
            }
        });
    }

}

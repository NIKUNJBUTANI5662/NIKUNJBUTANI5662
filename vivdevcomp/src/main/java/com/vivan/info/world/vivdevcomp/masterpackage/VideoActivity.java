package com.vivan.info.world.vivdevcomp.masterpackage;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.khizar1556.mkvideoplayer.MKPlayer;
import com.vivan.info.world.vivdevcomp.R;

import java.util.concurrent.Callable;

public class VideoActivity extends AdsClass{

    MKPlayer mkPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
         loadInterastialAds();
        String myurl = getIntent().getStringExtra("url");
        mkPlayer = new MKPlayer(VideoActivity.this);
        mkPlayer.play(myurl);
    }


    @Override
    public void onBackPressed() {
        mkPlayer.stop();
        showInterstitialAds(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                finish();
                return null;
            }
        });
    }


}

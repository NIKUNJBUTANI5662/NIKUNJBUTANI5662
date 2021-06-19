package com.vivan.info.world.cooldevcomp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.vivan.info.world.vivdevcomp.masterpackage.AdsClass;

import java.util.concurrent.Callable;

public class MainActivity  extends AdsClass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnInter = (Button)findViewById(R.id.showInter);
        btnInter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInterstitialAds(new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                     //   Toast.makeText(MainActivity.this, "Ads", Toast.LENGTH_SHORT).show();
                        return null;
                    }
                });
//                showAddapptrInterstitial(new Callable<Void>() {
//                    @Override
//                    public Void call() throws Exception {
//
//                        Toast.makeText(MainActivity.this, "Ads", Toast.LENGTH_SHORT).show();
//                        return null;
//                    }
//                });
            }
        });

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                  loadInterastialAds();

                 // showBannerAds();

            }
        }, 5000);

    }
}
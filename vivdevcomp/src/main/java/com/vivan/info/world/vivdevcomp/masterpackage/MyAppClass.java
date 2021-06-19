package com.vivan.info.world.vivdevcomp.masterpackage;

import android.app.Application;

import androidx.annotation.Nullable;

import com.google.android.gms.ads.RequestConfiguration;
import com.intentsoftware.addapptr.AATKit;
import com.intentsoftware.addapptr.AATKitConfiguration;
import com.intentsoftware.addapptr.AATKitReward;
import com.intentsoftware.addapptr.BannerPlacementLayout;
import com.intentsoftware.addapptr.PlacementSize;
import com.intentsoftware.addapptr.ad.VASTAdData;

import java.util.Arrays;

import static com.intentsoftware.addapptr.AATKit.enableTestMode;

public class MyAppClass extends Application implements AATKit.Delegate{

    private AATKitEventListner listener;
    public int fullScreenadplacement;



    public void onCreate() {
        super.onCreate();

//        AATKitConfiguration configuration = new AATKitConfiguration(this);
//       configuration.setTestModeAccountId(2525);
//        configuration.setDelegate(this);
//        configuration.setUseDebugShake(false);
//        AATKit.init(configuration);

//        listener = (AATKitEventListner) g;



        AATKitConfiguration configuration = new AATKitConfiguration((Application) getApplicationContext());
        configuration.setDelegate(this);
        //   configuration.setTestModeAccountId(2525);
        new RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList("100B8569157C8A3284CC9C1E544DCC66"));
        // configuration.setUseDebugShake(false);

        AATKit.init(configuration);
        AATKit.enableTestMode(2525);


    }

    public void setListener (AATKitEventListner listener)
    {
        this.listener = listener;
    }
    @Override
    public void aatkitHaveAd(int placementId) {
        listener.onHaveAd(placementId);
    }

    @Override
    public void aatkitNoAd(int placementId) {
        listener.onNoAd(placementId);
    }

    @Override
    public void aatkitPauseForAd(int placementId) {
//        listener.
    }

    @Override
    public void aatkitResumeAfterAd(int placementId) {
        listener.onResumeAfterAd(placementId);
    }

    @Override
    public void aatkitShowingEmpty(int placementId) {

    }

    @Override
    public void aatkitUserEarnedIncentive(int placementId, @Nullable AATKitReward aatKitReward) {

    }

    @Override
    public void aatkitObtainedAdRules(boolean fromTheServer) {

    }

    @Override
    public void aatkitUnknownBundleId() {

    }

    @Override
    public void aatkitHaveAdForPlacementWithBannerView(int placementId, BannerPlacementLayout bannerView) {

    }

    @Override
    public void aatkitHaveVASTAd(int placementId, VASTAdData data) {

    }
}

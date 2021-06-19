package com.vivan.info.world.vivdevcomp.masterpackage;

import com.intentsoftware.addapptr.ad.VASTAdData;

import java.util.concurrent.Callable;

public interface AATKitEventListner {

    void onNoAd(int placementId);

    void onHaveAd(int placementId);

    void onUserEarnedIncentive(int placementId);

    public void onResumeAfterAd(int placementId);

    void onHaveVASTAd(int placementId, VASTAdData data);
}

package com.vivan.info.world.vivdevcomp.masterpackage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.adcolony.sdk.AdColony;
import com.adcolony.sdk.AdColonyAdOptions;
import com.adcolony.sdk.AdColonyAdSize;
import com.adcolony.sdk.AdColonyAdView;
import com.adcolony.sdk.AdColonyAdViewListener;
import com.adcolony.sdk.AdColonyAppOptions;
import com.adcolony.sdk.AdColonyInterstitial;
import com.adcolony.sdk.AdColonyInterstitialListener;
import com.adcolony.sdk.AdColonyUserMetadata;
import com.adcolony.sdk.AdColonyZone;
import com.bumptech.glide.Glide;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.intentsoftware.addapptr.AATKit;
import com.intentsoftware.addapptr.AATKitConfiguration;
import com.intentsoftware.addapptr.BannerPlacementLayout;
import com.intentsoftware.addapptr.PlacementSize;
import com.intentsoftware.addapptr.ad.VASTAdData;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.startapp.sdk.ads.banner.Banner;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.StartAppSDK;
import com.startapp.sdk.adsbase.VideoListener;

import com.vivan.info.world.vivdevcomp.R;
import com.vungle.warren.AdConfig;
import com.vungle.warren.Banners;
import com.vungle.warren.InitCallback;
import com.vungle.warren.LoadAdCallback;
import com.vungle.warren.PlayAdCallback;
import com.vungle.warren.Vungle;
import com.vungle.warren.VungleBanner;
import com.vungle.warren.error.VungleException;
//import com.vivan.info.world.vivdevcomp.R;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import cz.msebera.android.httpclient.Header;

import static com.adcolony.sdk.AdColony.requestInterstitial;
import static com.intentsoftware.addapptr.AATKit.createPlacement;
import static com.intentsoftware.addapptr.AATKit.enableTestMode;
import static com.intentsoftware.addapptr.AATKit.hasAdForPlacement;
import static com.intentsoftware.addapptr.AATKit.showPlacement;


public class AdsClass extends AppCompatActivity  {


    public static Boolean fst = true;
    public MasterListAdapter masterAdapter;
    GsonUtils gsonUtils;
    ProgressDialog pDialog;
    public static ArrayList<DialogDetail> dialogList = new ArrayList<>();
    public ArrayList<AdsDetail> adsList = new ArrayList<>();
    public static String currentadnetwork = "";
    private AdColonyAdView bannerAdColony;

    // for vungle
    private static String Vungle_APP_ID = "";
    private static String Vungle_Int_ID = "";
    private static String Vungle_Banner_ID = "";
//
//    private static String Vungle_APP_ID = "5fffc0d9d15708acb64b1789";
//    private String Vungle_Int_ID="TEACHER_INT-1125834";
//    private String Vungle_Banner_ID="TEACHER_BANNER-2499102";

    // for Adscolony
    private static String AdColony_APP_ID = "";
    private static String Adcolony_ZONE_ID = "";
    private static String Adcolony_banner_Zone_ID = "";

    //    private String AdColony_APP_ID = "appfd999accf4f54a65b2";
//    private String Adcolony_ZONE_ID = "vzea12dd5bdb1242da87";
//    private String Adcolony_banner_Zone_ID = "vz04cdc7f61c2d4aedb8";
    public final String[] AD_UNIT_Zone_Ids = new String[2];
    // final private String AdColony_TAG = "AdColonyDemo";

    private AdColonyInterstitial ad;
    private AdColonyInterstitialListener listener;
    private AdColonyAdOptions adOptions;
    public com.google.android.gms.ads.InterstitialAd googleInterstitialAd;
    private com.facebook.ads.InterstitialAd facebookInterstitialAd;
    private com.facebook.ads.AdView adView;

    // for startApp
    private String StartAppId = "";
//    private String StartAppId = "200045219";

    // for google
//    private String google_appid="ca-app-pub-3940256099942544~3347511713";
//    private String googleInterastialAdsId="ca-app-pub-3940256099942544/1033173712";
//    private String googleBannerAdsId="ca-app-pub-3940256099942544/6300978111";
//

    private static String google_appid = "";
    private static String googleInterastialAdsId = "";
    private static String googleBannerAdsId = "";


    //for facebook
    private static String facebookInterstitialAdid = "";
    private static String facebookBannerAdid = "";

    public static boolean nodata = false;

    public static String AppKey = "";

    private int fullscreenPlacementId = -1;
    private int bannerPlacementId = -1;

    private MyAppClass showcaseApplication;
//
//    private String facebookInterstitialAdid="373097850233939_373098686900522";
//    private String facebookBannerAdid="373097850233939_750608699149517";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gsonUtils = GsonUtils.getInstance();
        showcaseApplication = ((MyAppClass) getApplication());
        if (isConnected(this)) {

            if (fst) {

                ApplicationInfo ai = null;
                try {
                    ai = this.getPackageManager().getApplicationInfo( this.getPackageName(), PackageManager.GET_META_DATA );
                    AppKey = String.valueOf(ai.metaData.get("my_app_id"));

                    Toast.makeText(this, AppKey, Toast.LENGTH_SHORT).show();
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }


                Webservice();
                fst = false;
            }


        }
    }

    public void setAppKey(String appKEY) {
        this.AppKey = appKEY;
    }

    public void Webservice() {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading Message... Please wait");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params1 = new RequestParams();
        params1.put("app_key", AppKey);
        try {
            client.setConnectTimeout(50000);

            client.post("http://vivaninfoworld.com/api/get_appdata.php", params1, new BaseJsonHttpResponseHandler<AdsData>() {

                @Override
                public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, AdsData response) {

                    //  Log.d("response",response.toString());
                    if (response.getSuccess() == 1) {
                        currentadnetwork = response.getCurrentad();
                        if (response.getDialogDetail().size() > 0) {
                            dialogList = new ArrayList<>();
                            dialogList.addAll(response.getDialogDetail());



                            if(!currentadnetwork.equals("addapptr"))
                            {
                                adsList.addAll(response.getAdsDetail());
                            }

                            for (int i = 0; i < adsList.size(); i++) {
                                if (currentadnetwork.equals("google")) {
                                    if (adsList.get(i).getAdNetwork().equals("google") && (adsList.get(i).getAdsType().equals("inter"))) {
                                        google_appid = adsList.get(i).getNetworkAppId();
                                        googleInterastialAdsId = adsList.get(i).getPlacementId();
                                    } else if (adsList.get(i).getAdNetwork().equals("google") && (adsList.get(i).getAdsType().equals("banner"))) {
                                        google_appid = adsList.get(i).getNetworkAppId();
                                        googleBannerAdsId = adsList.get(i).getPlacementId();
                                    }
                                    //loadInterastialAds();
                                } else if (currentadnetwork.equals("facebook")) {
                                    if (adsList.get(i).getAdNetwork().equals("facebook") && (adsList.get(i).getAdsType().equals("inter"))) {
                                        facebookInterstitialAdid = adsList.get(i).getPlacementId();
                                    } else if (adsList.get(i).getAdNetwork().equals("facebook") && (adsList.get(i).getAdsType().equals("banner"))) {
                                        facebookBannerAdid = adsList.get(i).getPlacementId();
                                    }
                                    //  loadInterastialAds();
                                } else if (currentadnetwork.equals("vungle")) {
                                    if (adsList.get(i).getAdNetwork().equals("vungle") && (adsList.get(i).getAdsType().equals("inter"))) {
                                        Vungle_APP_ID = adsList.get(i).getNetworkAppId();
                                        Vungle_Int_ID = adsList.get(i).getPlacementId();
                                    } else if (adsList.get(i).getAdNetwork().equals("vungle") && (adsList.get(i).getAdsType().equals("banner"))) {
                                        Vungle_APP_ID = adsList.get(i).getNetworkAppId();
                                        Vungle_Banner_ID = adsList.get(i).getPlacementId();
                                    }
                                    //  loadInterastialAds();

                                } else if (currentadnetwork.equals("adcolony")) {
                                    if (adsList.get(i).getAdNetwork().equals("adcolony") && (adsList.get(i).getAdsType().equals("inter"))) {

                                        AdColony_APP_ID = adsList.get(i).getNetworkAppId();
                                        Adcolony_ZONE_ID = adsList.get(i).getPlacementId();
                                        AD_UNIT_Zone_Ids[0] = Adcolony_ZONE_ID;
                                    } else if (adsList.get(i).getAdNetwork().equals("adcolony") && (adsList.get(i).getAdsType().equals("banner"))) {
                                        AdColony_APP_ID = adsList.get(i).getNetworkAppId();
                                        Adcolony_banner_Zone_ID = adsList.get(i).getPlacementId();
                                        AD_UNIT_Zone_Ids[1] = Adcolony_ZONE_ID;
                                    }
                                    //  loadInterastialAds();

                                } else if (currentadnetwork.equals("startapp")) {
                                    if (adsList.get(i).getAdNetwork().equals("startapp") && (adsList.get(i).getAdsType().equals("inter"))) {
                                        StartAppId = adsList.get(i).getNetworkAppId();
                                        // googleInterastialAdsId = adsList.get(i).getPlacementId();
                                    }
                                    //  loadInterastialAds();
                                }
                                else if (currentadnetwork.equals("addapptr")) {



//                                    if (adsList.get(i).getAdNetwork().equals("addapptr") && (adsList.get(i).getAdsType().equals("inter"))) {
//
//                                        AdColony_APP_ID = adsList.get(i).getNetworkAppId();
//                                        Adcolony_ZONE_ID = adsList.get(i).getPlacementId();
//                                        AD_UNIT_Zone_Ids[0] = Adcolony_ZONE_ID;
//                                    } else if (adsList.get(i).getAdNetwork().equals("addapptr") && (adsList.get(i).getAdsType().equals("banner"))) {
//                                        AdColony_APP_ID = adsList.get(i).getNetworkAppId();
//                                        Adcolony_banner_Zone_ID = adsList.get(i).getPlacementId();
//                                        AD_UNIT_Zone_Ids[1] = Adcolony_ZONE_ID;
//                                    }
                                    //  loadInterastialAds();
                                }


                            }


                            // showDialog();
                            initAdNetwork();

                            // Log.d("response--",response.toString());
                            // setDialogData();
//                           dialogList.get(0).getTitleText()
                            //  Toast.makeText(com.yami.videourlplayer.masterpackage.AdsClass.this, "Success", Toast.LENGTH_SHORT).show();
                            pDialog.dismiss();
                        } else {

                            pDialog.dismiss();
                            Toast.makeText(AdsClass.this, "No record Found", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        nodata = true;
                        pDialog.dismiss();
                        Toast.makeText(AdsClass.this, "internal server error", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, AdsData errorResponse) {
                    pDialog.dismiss();
                    Toast.makeText(AdsClass.this, "Server Fail", Toast.LENGTH_SHORT).show();
                }


                @Override
                protected AdsData parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
                    pDialog.dismiss();
                    try {
                        if (!isFailure && !rawJsonData.isEmpty()) {
                            return gsonUtils.getGson().fromJson(rawJsonData, AdsData.class);
                        }
                    } catch (Exception e) {
                        Log.d("response--", e.getMessage().toString());
                    }
                    return null;
                }

                @Override
                public void onStart() {
                    super.onStart();
                }

                @Override
                public void onFinish() {
                    super.onFinish();

                }
            });

        } catch (Exception e) {

        }
    }


    private void initAdcolony() {
        AdColonyAppOptions appOptions = new AdColonyAppOptions()
                .setUserID("xx")
                .setKeepScreenOn(true);

        AdColony.configure(this, appOptions, AdColony_APP_ID, AD_UNIT_Zone_Ids);


        AdColonyUserMetadata metadata = new AdColonyUserMetadata()
                .setUserAge(26)
                .setUserEducation(AdColonyUserMetadata.USER_EDUCATION_BACHELORS_DEGREE)
                .setUserGender(AdColonyUserMetadata.USER_MALE);

    }

    public void showAdcolonyInterastial(Callable<Void> callable) {
        listener = new AdColonyInterstitialListener() {
            @Override
            public void onRequestFilled(AdColonyInterstitial ad) {
                AdsClass.this.ad = ad;
                ad.show();

            }

            @Override
            public void onRequestNotFilled(AdColonyZone zone) {
                try {
                    callable.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                requestInterstitial(Adcolony_ZONE_ID, this, adOptions);
            }

            @Override
            public void onOpened(AdColonyInterstitial ad) {

            }

            @Override
            public void onExpiring(AdColonyInterstitial ad) {
//                try {
//                    callable.call();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                requestInterstitial(Adcolony_ZONE_ID, this, adOptions);

            }

            @Override
            public void onClosed(AdColonyInterstitial ad) {
                try {
                    callable.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //  requestInterstitial(Adcolony_ZONE_ID, this, adOptions);
            }
        };

        AdColony.requestInterstitial(Adcolony_ZONE_ID, listener, adOptions);

    }


    public void showAdcolonyBanner() {
        AdColonyAdViewListener listener = new AdColonyAdViewListener() {
            @Override
            public void onRequestFilled(AdColonyAdView ad) {
                RelativeLayout bannerContainer = (RelativeLayout) findViewById(R.id.layout_banner);
                bannerContainer.addView(ad);
                bannerAdColony = ad;

            }
        };
        AdColony.requestAdView(Adcolony_banner_Zone_ID, listener, AdColonyAdSize.BANNER);
    }

    private void initVungleInterastailAd() {
        Vungle.init(Vungle_APP_ID, AdsClass.this.getApplicationContext(), new InitCallback() {
            @Override
            public void onSuccess() {
                loadVungleBanner();
            }

            @Override
            public void onError(VungleException e) {

            }

            @Override
            public void onAutoCacheAdAvailable(String pid) {

            }
        });
    }

    private void loadVungleInterastialAd() {

        Vungle.loadAd(Vungle_Int_ID, new LoadAdCallback() {
            @Override
            public void onAdLoad(String id) {

            }

            @Override
            public void onError(String id, VungleException e) {

            }
        });
    }

    private void loadVungleBanner() {
        Banners.loadBanner(Vungle_Banner_ID, AdConfig.AdSize.BANNER, vungleLoadAdCallback);
    }

    private void playVungleBanner() {
        // vungle banner
        RelativeLayout bannerContainer = findViewById(R.id.layout_banner);
        if (Banners.canPlayAd(Vungle_Banner_ID, AdConfig.AdSize.BANNER)) {
            VungleBanner vungleBanner = Banners.getBanner(Vungle_Banner_ID, AdConfig.AdSize.BANNER, VunglePlayAdBannerCallback);
            bannerContainer.addView(vungleBanner);
        }
    }

    private void playVungleInterastialAd(Callable<Void> callable) {

        Vungle.playAd(Vungle_Int_ID, new AdConfig(), new PlayAdCallback() {
            @Override
            public void onAdStart(String placementReferenceID) {
                //    showToastMessage("Ad Start");
            }

            @Override
            public void onAdViewed(String placementReferenceID) {
                //    showToastMessage("Ad Viewed");
            }


            @Override
            public void onAdEnd(String id, boolean completed, boolean isCTAClicked) {
                try {
                    callable.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                loadfacebookInterastialAds();
            }

            @Override
            public void onAdEnd(String placementReferenceID) {

                //showToastMessage("Ad End");
            }

            @Override
            public void onAdClick(String placementReferenceID) {
                showToastMessage("Ad Clicked");
            }

            @Override
            public void onAdRewarded(String placementReferenceID) {
                // showToastMessage("User Rewarded");
            }

            @Override
            public void onAdLeftApplication(String placementReferenceID) {
                //   showToastMessage("User Left Application");
            }

            @Override
            public void onError(String id, VungleException e) {
                //  setButtonState(false, true, false);
                try {
                    callable.call();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                loadVungleInterastialAd();
//                showToastMessage("Ad Play Error : " + e.getLocalizedMessage());
            }
        });
    }

    private void showToastMessage(String message) {
        Toast.makeText(AdsClass.this, message, Toast.LENGTH_SHORT).show();
    }

    private final PlayAdCallback VunglePlayAdBannerCallback = new PlayAdCallback() {
        @Override
        public void onAdStart(String id) {
            // Ad experience started
        }

        @Override
        public void onAdEnd(String id, boolean completed, boolean isCTAClicked) {

        }

        @Override
        public void onAdViewed(String id) {
            // Ad has rendered
        }

        @Override
        public void onAdEnd(String id) {
            // Ad experience ended
        }

        @Override
        public void onAdClick(String id) {
            // User clicked on ad
        }

        @Override
        public void onAdRewarded(String id) {

        }

        @Override
        public void onAdLeftApplication(String id) {
            // User has left app during an ad experience
        }

        @Override
        public void onError(String id, VungleException exception) {
            // Ad failed to play
        }
    };

    private final LoadAdCallback vungleLoadAdCallback = new LoadAdCallback() {
        @Override
        public void onAdLoad(String id) {
            // Ad has been successfully loaded for the placement
            playVungleBanner();
        }

        @Override
        public void onError(String id, VungleException exception) {
            // Ad has failed to load for the placement
        }
    };

    private void initStartApp() {
        StartAppSDK.init(this, StartAppId, false);
    }

    private void initStartAppRewardVideo() {
        StartAppAd ads = new StartAppAd(this);
        ads.loadAd(StartAppAd.AdMode.REWARDED_VIDEO);
        ads.setVideoListener(new VideoListener() {
            @Override
            public void onVideoCompleted() {

            }
        });
    }


    @SuppressLint("MissingPermission")
    public void initGoogleAds() {
        MobileAds.initialize(AdsClass.this, google_appid);
    }

    @SuppressLint("MissingPermission")
    public void loadGoogleInterastialAds() {
        googleInterstitialAd = new com.google.android.gms.ads.InterstitialAd(this);
        googleInterstitialAd.setAdUnitId(googleInterastialAdsId);
        googleInterstitialAd.loadAd(new AdRequest.Builder().build());


    }

    public void showGoogleInterastialAds(Callable<Void> callable) {

        if (googleInterstitialAd.isLoaded()) {
            googleInterstitialAd.show();
            googleInterstitialAd.setAdListener(new com.google.android.gms.ads.AdListener() {

                @Override
                public void onAdClosed() {
                    try {
                        callable.call();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    loadGoogleInterastialAds();
                }

                @Override
                public void onAdFailedToLoad(LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    try {
                        callable.call();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            try {
                callable.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void showGoogleBannerAds() {
        //google banner
        AdView adView = new AdView(this);

        RelativeLayout bannerContainer = (RelativeLayout) findViewById(R.id.layout_banner);
        bannerContainer.addView(adView);
        adView.setAdUnitId(googleBannerAdsId);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.setAdSize(AdSize.LARGE_BANNER);
        adView.loadAd(adRequest);

    }

    public void initfacebookAds() {
        AudienceNetworkAds.initialize(this);
    }

    public void loadfacebookInterastialAds() {

        facebookInterstitialAd = new com.facebook.ads.InterstitialAd(AdsClass.this, facebookInterstitialAdid);
        facebookInterstitialAd.loadAd();

    }

    public void showFacebookAds(Callable<Void> callable) {
        if (facebookInterstitialAd.isAdLoaded()) {
            facebookInterstitialAd.show();
            InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
                @Override
                public void onInterstitialDisplayed(Ad ad) {

                }

                @Override
                public void onInterstitialDismissed(Ad ad) {
                    try {
                        callable.call();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    loadfacebookInterastialAds();
                }

                @Override
                public void onError(Ad ad, AdError adError) {
                    try {
                        callable.call();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    loadfacebookInterastialAds();

                }

                @Override
                public void onAdLoaded(Ad ad) {


                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            };

            facebookInterstitialAd.buildLoadAdConfig().withAdListener(interstitialAdListener);
        } else {
            try {
                callable.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void showfacebookBanner() {
        // facebook banner
        adView = new com.facebook.ads.AdView(this, facebookBannerAdid, com.facebook.ads.AdSize.BANNER_HEIGHT_50);
        RelativeLayout adContainer = (RelativeLayout) findViewById(R.id.layout_banner);
        adContainer.addView(adView);
        adView.loadAd();
    }

    public void showStartAppBanner() {
        RelativeLayout adContainer = (RelativeLayout) findViewById(R.id.layout_banner);
        Banner startAppBanner = new Banner(this);
        RelativeLayout.LayoutParams bannerParameters =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        bannerParameters.addRule(RelativeLayout.CENTER_HORIZONTAL);
        bannerParameters.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
// Add to main Layout
        adContainer.addView(startAppBanner);
    }

    public final static boolean isConnected(Context context) {
        final ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        @SuppressLint("MissingPermission") final NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public class ViewDialog {

        public void showDialog(Activity activity, String msg, String title, String name, Boolean btn_update_show, String btn_update_txt, Boolean btn_cancel_show, String btn_cancel_txt, Boolean btn_start_show, String btn_start_txt, Boolean cancelable, String appurl, String iconurl) {
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.update_dialog);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            if (cancelable) {
                dialog.setCancelable(true);
            } else {
                dialog.setCancelable(false);
            }

            TextView txtMessage = (TextView) dialog.findViewById(R.id.txtMessage);
            TextView txtTitle = (TextView) dialog.findViewById(R.id.txtTitle);
            TextView txtName = (TextView) dialog.findViewById(R.id.txtAppName);
            ImageView imgIcon = (ImageView) dialog.findViewById(R.id.imgIcon);
            Glide.with(AdsClass.this)
                    .load(Uri.parse(iconurl))
                    .into(imgIcon);


            txtMessage.setText(msg);
            txtTitle.setText(title);
            txtName.setText(name);

            Button btn_update = (Button) dialog.findViewById(R.id.btn_update);
            if (!(btn_update_txt.equals(null) || btn_update_txt.equals(""))) {
                btn_update.setText(btn_update_txt);
            }

            if (btn_update_show) {
                btn_update.setVisibility(View.VISIBLE);
            } else {
                btn_update.setVisibility(View.GONE);
            }

            btn_update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(appurl));
                    startActivity(browserIntent);
                }
            });

            Button btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);
            if (!(btn_cancel_txt.equals(null) || btn_cancel_txt.equals(""))) {
                btn_cancel.setText(btn_cancel_txt);
            }
            if (btn_cancel_show) {
                btn_cancel.setVisibility(View.VISIBLE);
            } else {
                btn_cancel.setVisibility(View.GONE);
            }


            btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(getApplicationContext(),"Okay" ,Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }
            });

            Button btn_start = (Button) dialog.findViewById(R.id.btn_start);
            if (!(btn_start_txt.equals(null) || btn_start_txt.equals(""))) {
                btn_start.setText(btn_start_txt);
            }
            if (btn_start_show) {
                btn_start.setVisibility(View.VISIBLE);
            } else {
                btn_start.setVisibility(View.GONE);
            }


            btn_start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(AdsClass.this, MasterActivity.class);
                    startActivity(in);

                }
            });

            dialog.show();
        }
    }

    public void showDialog() {


        if (isConnected(this)) {

            if (!nodata) {
                ViewDialog alert = new ViewDialog();

                Boolean btnCancelShow = Integer.parseInt(dialogList.get(0).getBtnCancelShow()) == 1 ? true : false;
                Boolean btnStartShow = Integer.parseInt(dialogList.get(0).getBtnStartShow()) == 1 ? true : false;
                Boolean btnUpdateShow = Integer.parseInt(dialogList.get(0).getBtnUpdateShow()) == 1 ? true : false;
                Boolean dialogcancelable = Integer.parseInt(dialogList.get(0).getDialogCancelable()) == 1 ? true : false;
                Boolean imgDialogShow = Integer.parseInt(dialogList.get(0).getDialogShow()) == 1 ? true : false;
                Boolean imgBannerShow = Integer.parseInt(dialogList.get(0).getImgBannerShow()) == 1 ? true : false;
                if (imgDialogShow) {
                    alert.showDialog(AdsClass.this, dialogList.get(0).getTxtMessage(), dialogList.get(0).getDialogType(), dialogList.get(0).getTxtName(), btnUpdateShow, dialogList.get(0).getBtnUpdateTxt(), btnCancelShow, dialogList.get(0).getBtnCancelTxt(), btnStartShow, dialogList.get(0).getBtnStartTxt(), dialogcancelable, dialogList.get(0).getAppUrl(), dialogList.get(0).getAppIconUrl());
                }
            }
        }

    }

    public void initAdNetwork() {

        if (currentadnetwork.equals("google")) {
            initGoogleAds();

        } else if (currentadnetwork.equals("facebook")) {
            initfacebookAds();

        } else if (currentadnetwork.equals("vungle")) {
            loadVungleInterastialAd();

        } else if (currentadnetwork.equals("startapp")) {
            initStartApp();
        } else if (currentadnetwork.equals("adcolony")) {
            initAdcolony();
        }
        else if(currentadnetwork.equals("addapptr"))
        {
            Toast.makeText(this, "add app tr", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "No ads", Toast.LENGTH_SHORT).show();
        }

    }

    public void loadInterastialAds() {
        //   Log.d("adnetwork--",currentadnetwork);
        if (currentadnetwork.equals("google")) {
            loadGoogleInterastialAds();
        } else if (currentadnetwork.equals("facebook")) {
            loadfacebookInterastialAds();
        } else if (currentadnetwork.equals("vungle")) {
            initVungleInterastailAd();
        } else if (currentadnetwork.equals("startapp")) {
            initStartApp();
        } else if (currentadnetwork.equals("adcolony")) {
            initAdcolony();
        }
        else if (currentadnetwork.equals("addapptr")) {
            loadAddapptrInterstitial();
        }
        else {
            Toast.makeText(this, "No ads", Toast.LENGTH_SHORT).show();
        }
    }


    public void showInterstitialAds(Callable<Void> callable) {
        if (currentadnetwork.equals("google")) {
            showGoogleInterastialAds(callable);
        } else if (currentadnetwork.equals("facebook")) {
            showFacebookAds(callable);
        } else if (currentadnetwork.equals("vungle")) {
            playVungleInterastialAd(callable);
        } else if (currentadnetwork.equals("startapp")) {
            StartAppAd.showAd(this);

        } else if (currentadnetwork.equals("adcolony")) {
            showAdcolonyInterastial(callable);
        }
        else if (currentadnetwork.equals("addapptr")) {
            showAddapptrInterstitial(callable);
        }
        else {
            try {
                callable.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Toast.makeText(this, "No ads", Toast.LENGTH_SHORT).show();
        }
    }


    public void showBannerAds() {
        if (currentadnetwork.equals("google")) {
            initGoogleAds();
            showGoogleBannerAds();
        } else if (currentadnetwork.equals("facebook")) {
            initfacebookAds();
            showfacebookBanner();
        } else if (currentadnetwork.equals("vungle")) {
            playVungleBanner();
        } else if (currentadnetwork.equals("startapp")) {
            showStartAppBanner();

        } else if (currentadnetwork.equals("adcolony")) {
            initAdcolony();
            showAdcolonyBanner();
        }
        else if (currentadnetwork.equals("addapptr")) {
            showAddapptrBanner();
        }
        else {
            //   Toast.makeText(this, "No ads", Toast.LENGTH_SHORT).show();
        }
    }





    //addapptr
    public void showAddapptrBanner(){
        AATKit.onActivityResume(this);
        // RelativeLayout adContainer = (RelativeLayout) findViewById(R.id.layout_banner);
        bannerPlacementId = createPlacement("Banner",
                PlacementSize.Banner320x53);
        addPlacementView(bannerPlacementId);
        AATKit.startPlacementAutoReload(bannerPlacementId);
    }

    public void loadAddapptrInterstitial(){
        AATKit.onActivityResume(this);

        fullscreenPlacementId = AATKit.createPlacement("Fullscreen", PlacementSize.Fullscreen);
        AATKit.startPlacementAutoReload(fullscreenPlacementId);
        showcaseApplication.setListener(createOnAATKitEventListener(null));
        resumeonce = 0;
        AdsShown = false;

    }

    public  Callable clb=null;
    public boolean AdsShown = false;
    public void showAddapptrInterstitial(Callable<Void> callable){

        //  aatKitEventListner = createOnAATKitEventListener(callable);




        // AATKit.onActivityResume(this);
        //   AATKitConfiguration configuration = new AATKitConfiguration(getApplication());
//       // configuration.setDelegate();
//        DelegateClass delegate = new DelegateClass();
//        AATKit.Delegate d = delegate.p;
//        configuration.setDelegate(d);
////
//        fullscreenPlacementId = AATKit.createPlacement("Fullscreen", PlacementSize.Fullscreen);
//
        //  AATKit.reconfigure(configuration);



        if(!AdsShown)
        {
            if(hasAdForPlacement(fullscreenPlacementId))
            {
                clb = callable;
                AATKit.showPlacement(getFullscreenPlacementId());
                //  aatKitEventListner.onResumeAfterAd(fullscreenPlacementId);
                AdsShown =true;

            }
        }
        else
        {
            try {
                callable.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }





    }

    public AATKitEventListner createOnAATKitEventListener(Callable<Void> callable) {
        return new AATKitEventListner() {
            @Override
            public void onNoAd(int placementId) {
                if(placementId == fullscreenPlacementId)
                {
                    try {

                        //clb.call();
                        clb.call();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onHaveAd(int placementId) {


                // toast("havead");
//                showPlacement(fullscreenPlacementId);
//                if(placementId == fullscreenPlacementId)


//                {
//                    try {
//
//                        callable.call();
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }

            }

            @Override
            public void onUserEarnedIncentive(int placementId) {
//                showPlacement(fullscreenPlacementId);
//                try {
//                    callable.call();
//                } catch (Exception e) {
//                    e.printStackTrace();

//                }
                // toast("onUserEarnedIncentive");
            }

            @Override
            public void onResumeAfterAd(int placementId) {
//                Toast.makeText(showcaseApplication, "1", Toast.LENGTH_SHORT).show();
                if(placementId == fullscreenPlacementId)
                {
                    Toast.makeText(showcaseApplication, "2", Toast.LENGTH_SHORT).show();
                    try {

                        clb.call();
                        // callable.call();
                        Toast.makeText(showcaseApplication, "3", Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(showcaseApplication, "4", Toast.LENGTH_SHORT).show();

                    }
                }

                // toast("onResumeAfterAd");
            }

            @Override
            public void onHaveVASTAd(int placementId, VASTAdData data) {
//                showPlacement(fullscreenPlacementId);
//                try {
//                    callable.call();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }




        };
    }

    private void addPlacementView(int placementId) {
        RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.layout_banner);

        mainLayout.removeAllViews();
        View placementView = AATKit.getPlacementView(placementId);
        if (placementView.getParent() != null){
            ((ViewGroup)placementView.getParent()).removeView(placementView);
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
        mainLayout.setVisibility(View.VISIBLE);

        mainLayout.addView(placementView, layoutParams);
    }

    private void removePlacementView(int placementId) {
        View placementView = AATKit.getPlacementView(placementId);

        if (placementView.getParent() != null) {
            ViewGroup parent = (ViewGroup) placementView.getParent();
            parent.removeView(placementView);
        }
    }

    public int getBannerPlacementId() {
        return bannerPlacementId;
    }

    public int getFullscreenPlacementId() {
        return fullscreenPlacementId;
    }

    public int resumeonce = 0;

    @Override
    public void onResume() {
        super.onResume();
        if (currentadnetwork.equals("addapptr")) {
            if(AdsShown) {
                if(resumeonce == 0) {

                    resumeonce = 1;
                    try {
                        clb.call();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

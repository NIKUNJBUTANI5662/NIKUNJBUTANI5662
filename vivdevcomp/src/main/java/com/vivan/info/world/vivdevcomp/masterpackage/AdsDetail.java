package com.vivan.info.world.vivdevcomp.masterpackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdsDetail {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("app_key")
    @Expose
    private String appKey;
    @SerializedName("ad_network")
    @Expose
    private String adNetwork;
    @SerializedName("network_app_id")
    @Expose
    private String networkAppId;
    @SerializedName("ads_type")
    @Expose
    private String adsType;
    @SerializedName("placement_id")
    @Expose
    private String placementId;
    @SerializedName("show_load_dialog")
    @Expose
    private String showLoadDialog;
    @SerializedName("ad_show")
    @Expose
    private String adShow;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAdNetwork() {
        return adNetwork;
    }

    public void setAdNetwork(String adNetwork) {
        this.adNetwork = adNetwork;
    }

    public String getNetworkAppId() {
        return networkAppId;
    }

    public void setNetworkAppId(String networkAppId) {
        this.networkAppId = networkAppId;
    }

    public String getAdsType() {
        return adsType;
    }

    public void setAdsType(String adsType) {
        this.adsType = adsType;
    }

    public String getPlacementId() {
        return placementId;
    }

    public void setPlacementId(String placementId) {
        this.placementId = placementId;
    }

    public String getShowLoadDialog() {
        return showLoadDialog;
    }

    public void setShowLoadDialog(String showLoadDialog) {
        this.showLoadDialog = showLoadDialog;
    }

    public String getAdShow() {
        return adShow;
    }

    public void setAdShow(String adShow) {
        this.adShow = adShow;
    }
}

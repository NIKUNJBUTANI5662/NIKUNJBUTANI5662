package com.vivan.info.world.vivdevcomp.masterpackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AdsData {
    @SerializedName("DialogDetail")
    @Expose
    private List<DialogDetail> dialogDetail = null;
    @SerializedName("AdsDetail")
    @Expose
    private List<AdsDetail> adsDetail = null;
    @SerializedName("currentad")
    @Expose
    private String currentad;
    @SerializedName("success")
    @Expose
    private Integer success;

    public List<DialogDetail> getDialogDetail() {
        return dialogDetail;
    }

    public void setDialogDetail(List<DialogDetail> dialogDetail) {
        this.dialogDetail = dialogDetail;
    }

    public List<AdsDetail> getAdsDetail() {
        return adsDetail;
    }

    public void setAdsDetail(List<AdsDetail> adsDetail) {
        this.adsDetail = adsDetail;
    }

    public String getCurrentad() {
        return currentad;
    }

    public void setCurrentad(String currentad) {
        this.currentad = currentad;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

}

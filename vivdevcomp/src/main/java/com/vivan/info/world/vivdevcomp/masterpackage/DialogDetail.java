package com.vivan.info.world.vivdevcomp.masterpackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DialogDetail {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("app_key")
    @Expose
    private String appKey;
    @SerializedName("dialog_type")
    @Expose
    private String dialogType;
    @SerializedName("dialog_show")
    @Expose
    private String dialogShow;
    @SerializedName("app_icon_url")
    @Expose
    private String appIconUrl;
    @SerializedName("txt_name")
    @Expose
    private String txtName;
    @SerializedName("txt_message")
    @Expose
    private String txtMessage;
    @SerializedName("btn_update_txt")
    @Expose
    private String btnUpdateTxt;
    @SerializedName("btn_update_show")
    @Expose
    private String btnUpdateShow;
    @SerializedName("btn_cancel_txt")
    @Expose
    private String btnCancelTxt;
    @SerializedName("btn_cancel_show")
    @Expose
    private String btnCancelShow;
    @SerializedName("btn_start_txt")
    @Expose
    private String btnStartTxt;
    @SerializedName("btn_start_show")
    @Expose
    private String btnStartShow;
    @SerializedName("dialog_cancelable")
    @Expose
    private String dialogCancelable;
    @SerializedName("img_banner_txt")
    @Expose
    private String imgBannerTxt;
    @SerializedName("img_banner_show")
    @Expose
    private String imgBannerShow;
    @SerializedName("app_url")
    @Expose
    private String appUrl;

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

    public String getDialogType() {
        return dialogType;
    }

    public void setDialogType(String dialogType) {
        this.dialogType = dialogType;
    }

    public String getDialogShow() {
        return dialogShow;
    }

    public void setDialogShow(String dialogShow) {
        this.dialogShow = dialogShow;
    }

    public String getAppIconUrl() {
        return appIconUrl;
    }

    public void setAppIconUrl(String appIconUrl) {
        this.appIconUrl = appIconUrl;
    }

    public String getTxtName() {
        return txtName;
    }

    public void setTxtName(String txtName) {
        this.txtName = txtName;
    }

    public String getTxtMessage() {
        return txtMessage;
    }

    public void setTxtMessage(String txtMessage) {
        this.txtMessage = txtMessage;
    }

    public String getBtnUpdateTxt() {
        return btnUpdateTxt;
    }

    public void setBtnUpdateTxt(String btnUpdateTxt) {
        this.btnUpdateTxt = btnUpdateTxt;
    }

    public String getBtnUpdateShow() {
        return btnUpdateShow;
    }

    public void setBtnUpdateShow(String btnUpdateShow) {
        this.btnUpdateShow = btnUpdateShow;
    }

    public String getBtnCancelTxt() {
        return btnCancelTxt;
    }

    public void setBtnCancelTxt(String btnCancelTxt) {
        this.btnCancelTxt = btnCancelTxt;
    }

    public String getBtnCancelShow() {
        return btnCancelShow;
    }

    public void setBtnCancelShow(String btnCancelShow) {
        this.btnCancelShow = btnCancelShow;
    }

    public String getBtnStartTxt() {
        return btnStartTxt;
    }

    public void setBtnStartTxt(String btnStartTxt) {
        this.btnStartTxt = btnStartTxt;
    }

    public String getBtnStartShow() {
        return btnStartShow;
    }

    public void setBtnStartShow(String btnStartShow) {
        this.btnStartShow = btnStartShow;
    }

    public String getDialogCancelable() {
        return dialogCancelable;
    }

    public void setDialogCancelable(String dialogCancelable) {
        this.dialogCancelable = dialogCancelable;
    }

    public String getImgBannerTxt() {
        return imgBannerTxt;
    }

    public void setImgBannerTxt(String imgBannerTxt) {
        this.imgBannerTxt = imgBannerTxt;
    }

    public String getImgBannerShow() {
        return imgBannerShow;
    }

    public void setImgBannerShow(String imgBannerShow) {
        this.imgBannerShow = imgBannerShow;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }
}

package com.vivan.info.world.vivdevcomp.masterpackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MasterDetail {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("app_key")
    @Expose
    private String appKey;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("image_show")
    @Expose
    private String imageShow;
    @SerializedName("title_text")
    @Expose
    private String titleText;
    @SerializedName("title_show")
    @Expose
    private String titleShow;
    @SerializedName("desc_text")
    @Expose
    private String descText;
    @SerializedName("desc_show")
    @Expose
    private String descShow;
    @SerializedName("open_in")
    @Expose
    private String openIn;
    @SerializedName("bg_color")
    @Expose
    private String bgColor;
    @SerializedName("text_color")
    @Expose
    private String textColor;
    @SerializedName("url")
    @Expose
    private String url;

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageShow() {
        return imageShow;
    }

    public void setImageShow(String imageShow) {
        this.imageShow = imageShow;
    }

    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public String getTitleShow() {
        return titleShow;
    }

    public void setTitleShow(String titleShow) {
        this.titleShow = titleShow;
    }

    public String getDescText() {
        return descText;
    }

    public void setDescText(String descText) {
        this.descText = descText;
    }

    public String getDescShow() {
        return descShow;
    }

    public void setDescShow(String descShow) {
        this.descShow = descShow;
    }

    public String getOpenIn() {
        return openIn;
    }

    public void setOpenIn(String openIn) {
        this.openIn = openIn;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}

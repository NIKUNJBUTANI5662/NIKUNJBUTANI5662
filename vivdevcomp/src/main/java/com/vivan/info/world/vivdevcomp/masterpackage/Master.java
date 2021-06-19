package com.vivan.info.world.vivdevcomp.masterpackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Master {
    @SerializedName("MasterDetail")
    @Expose
    private List<MasterDetail> masterDetail = null;
    @SerializedName("success")
    @Expose
    private Integer success;

    public List<MasterDetail> getMasterDetail() {
        return masterDetail;
    }

    public void setMasterDetail(List<MasterDetail> masterDetail) {
        this.masterDetail = masterDetail;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }


}

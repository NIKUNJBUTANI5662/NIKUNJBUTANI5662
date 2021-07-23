package com.vivan.info.world.vivdevcomp.masterpackage;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.vivan.info.world.vivdevcomp.R;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MasterActivity extends AdsClass{

    ListView lstMaster;

    public static ArrayList<MasterDetail> masterList = new ArrayList<MasterDetail>();
    public MasterListAdapter masterAdapter;

    GsonUtils gsonUtils;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);

        showBannerAds();

        lstMaster = (ListView)findViewById(R.id.lstMaster);
        gsonUtils = GsonUtils.getInstance();
        if(isConnected(this))
        {
            Webservice();
        }
        else
        {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }


    }

    public void Webservice() {

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading Message... Please wait");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        //pDialog.show();
        AsyncHttpClient client = new AsyncHttpClient();


        RequestParams params1 = new RequestParams();
        params1.put("app_key", AppKey);

        try {
            client.setConnectTimeout(50000);

            client.post("http://vivaninfoworld.com/api/get_masterdata.php", params1, new BaseJsonHttpResponseHandler<Master>() {

                @Override
                public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, Master response) {
                     Log.d("response--",response.toString());
                    if(response.getSuccess()==1)
                    {
                        if(response.getMasterDetail().size()>0)
                        {
                            masterList.clear();
                            masterList.addAll(response.getMasterDetail());
                            masterAdapter = new MasterListAdapter(MasterActivity.this,masterList);
                            lstMaster.setAdapter(masterAdapter);
                            masterAdapter.notifyDataSetChanged();
                            pDialog.dismiss();
                        }
                        else
                        {
                            pDialog.dismiss();
                            Toast.makeText(MasterActivity.this, "No record Found", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        pDialog.dismiss();
                        Toast.makeText(MasterActivity.this, "internal server error", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, Master errorResponse) {
                    pDialog.dismiss();
                    Toast.makeText(MasterActivity.this, "Server Fail", Toast.LENGTH_SHORT).show();
                }


                @Override
                protected Master parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
                    pDialog.dismiss();
                    try {
                        if (!isFailure && !rawJsonData.isEmpty()) {
                            return gsonUtils.getGson().fromJson(rawJsonData, Master.class);
                        }
                    } catch (Exception e) {

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
    @Override
    public void onBackPressed() {
       super.onBackPressed();
       finish();
    }


}

package com.vivan.info.world.vivdevcomp.masterpackage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.vivan.info.world.vivdevcomp.R;

import java.util.ArrayList;

public class MasterListAdapter extends BaseAdapter {

    Context context;
    ArrayList<MasterDetail> arrayList;
    private LayoutInflater mInflater;

    public MasterListAdapter(Context context, ArrayList<MasterDetail> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    private static class ViewHolder {
        public final LinearLayout rootView;
        public final ImageView imgImage;
        public final TextView txtTitle;
        public final TextView txtDesc;

        private ViewHolder(LinearLayout rootView, ImageView imgImage, TextView txtTitle, TextView txtDesc) {
            this.rootView = rootView;
            this.imgImage = imgImage;
            this.txtTitle = txtTitle;
            this.txtDesc = txtDesc;
        }

        public static ViewHolder create(LinearLayout rootView) {
            ImageView imgImage = (ImageView)rootView. findViewById(R.id.imgImage);
            TextView txtTitle = (TextView)rootView. findViewById(R.id.txtTitle);
            TextView txtDesc = (TextView)rootView. findViewById(R.id.txtDesc);
            return new ViewHolder( rootView, imgImage, txtTitle, txtDesc );
        }
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if ( convertView == null ) {
            mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = mInflater.inflate( R.layout.layout_dataitem, parent, false );
            vh = ViewHolder.create( (LinearLayout)view );
            view.setTag( vh );
        } else {
            vh = (ViewHolder)convertView.getTag();
        }

        if(Integer.parseInt(arrayList.get(position).getImageShow())==1?true:false)
        {
            vh.imgImage.setVisibility(View.VISIBLE);
            Glide.with(context).load(arrayList.get(position).getImageUrl()).into(vh.imgImage);
        }
        else
        {
            vh.imgImage.setVisibility(View.GONE);
        }

        if(Integer.parseInt(arrayList.get(position).getTitleShow())==1?true:false) {
            vh.txtTitle.setText(arrayList.get(position).getTitleText());
        }
        else
        {
            vh.txtTitle.setVisibility(View.GONE);
        }


        if(Integer.parseInt(arrayList.get(position).getDescShow())==1?true:false) {
            vh.txtDesc.setText(arrayList.get(position).getDescText());
        }
        else
        {
            vh.txtDesc.setVisibility(View.GONE);
        }

        vh.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arrayList.get(position).getOpenIn().equals("web"))
                {
                    Intent in = new Intent(context,WebActivity.class);
                    in.putExtra("url",arrayList.get(position).getUrl());
                    context.startActivity(in);
                }
                else if(arrayList.get(position).getOpenIn().equals("video"))
                {
                    Intent in = new Intent(context,VideoActivity.class);
                    in.putExtra("url",arrayList.get(position).getUrl());
                    context.startActivity(in);
                }
                else if(arrayList.get(position).getOpenIn().equals("youtube"))
                {
                    Intent in = new Intent(context,YoutubeActivity.class);
                    in.putExtra("url",arrayList.get(position).getUrl());
                    context.startActivity(in);
                }

            }
        });

        return vh.rootView;
    }
}





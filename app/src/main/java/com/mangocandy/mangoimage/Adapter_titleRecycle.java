package com.mangocandy.mangoimage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;


import com.bumptech.glide.Glide;
import com.mangocandy.beans.Title;
import com.mangocandy.utils.BitmapCache;


import java.util.List;
import java.util.Random;

/**
 * Created by MangoCandy on 2015/12/6.
 */
public class Adapter_titleRecycle extends RecyclerView.Adapter<Adapter_titleRecycle.mViewHolder> {
    List<Title> titles;
    Context context;

    int height;


    public Adapter_titleRecycle(List<Title> titles,Context context){
        this.titles=titles;
        this.context=context;

        DisplayMetrics metrics=new DisplayMetrics();
        ((MainActivity)context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        height=metrics.widthPixels/2+100;
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new mViewHolder(LayoutInflater.from(context).inflate(R.layout.single_title,null));
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, int position) {
        Title title=titles.get(position);
        FrameLayout.LayoutParams params= (FrameLayout.LayoutParams) holder.smallimg.getLayoutParams();
        if(title.getHeight()!=null){
            params.height=Integer.parseInt(title.getHeight());
        }
        holder.smallimg.setLayoutParams(params);
        Glide.with(context).load(title.getS_img()).into(holder.smallimg);
        holder.titletext.setText(title.getTitle());
    }


    @Override
    public int getItemCount() {
        return titles.size();
    }

    public static class mViewHolder extends RecyclerView.ViewHolder{
        public mViewHolder(View view){
            super(view);
            smallimg=(ImageView)view.findViewById(R.id.smallimg);
            titletext=(TextView)view.findViewById(R.id.titletext);
        }

        public ImageView smallimg;
        public TextView titletext;
    }


}

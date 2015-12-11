package com.mangocandy.mangoimage;


import android.animation.Animator;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.mangocandy.beans.Title;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/12/4.
 */
public class Fragment_showTitle extends Fragment {
    String type=null;
    View view;
    Context context;
    RecyclerView recyclerView;
    Adapter_titleRecycle adapter_titleRecycle;

    int start=1;

    List<Title> titles=new ArrayList<Title>();
    public static Fragment_showTitle newInstance(String type){
        Fragment_showTitle fragmentShowTitle=new Fragment_showTitle();
        Bundle bundle=new Bundle();
        bundle.putString("type",type);
        fragmentShowTitle.setArguments(bundle);
        return fragmentShowTitle;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try {
            this.type= URLEncoder.encode(getArguments().getString("type"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        context=getActivity();
        view=inflater.inflate(R.layout.fragment_showtitle, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        getJson();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(titles.size()<=1){
            getJson();
        }
    }

    public void initView(){
        recyclerView=(RecyclerView)view.findViewById(R.id.recycleview);
        StaggeredGridLayoutManager manager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(false);
        recyclerView.setItemViewCacheSize(40);
        adapter_titleRecycle=new Adapter_titleRecycle(titles, context);
        recyclerView.setAdapter(adapter_titleRecycle);

    }

    public void getJson(){
        Thread thread=new Thread(runnable);
        thread.start();
    }

    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            RequestQueue requestQueue= Volley.newRequestQueue(context);
            JsonObjectRequest stringRequest=new JsonObjectRequest(Request.Method.POST, "http://pic.sogou.com/pics/channel/getAllRecomPicByTag.jsp?category=%E5%A3%81%E7%BA%B8&width=1920&height=1200&tag="+type+"&start="+start+"&len=200"
                    , new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.i("asd",response+"");
                    try {
                        JSONArray jsonArray=response.getJSONArray("all_items");
                        for(int i=0;i<jsonArray.length();i++){
                            Title title=new Title();
                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                            title.setS_img(jsonObject.getString("sthumbUrl"));
                            title.setTitle(jsonObject.getString("title"));
                            title.setHeight(jsonObject.getString("sthumb_height"));
                            titles.add(title);
                        }
                        start+=20;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(context,"OK",Toast.LENGTH_SHORT).show();
                    adapter_titleRecycle.notifyDataSetChanged();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i("asd", error + "");
                }
            });
            requestQueue.add(stringRequest);
            requestQueue.start();
        }
    };

}

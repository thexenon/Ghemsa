package com.thexenon.ghemsaapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.squareup.picasso.Picasso;
import com.thexenon.ghemsaapp.R;
import com.thexenon.ghemsaapp.models.Model;

import java.util.List;

public class MyAdapter extends PagerAdapter {

    Context context;
    List<Model> imgList;
    LayoutInflater inflater;

    public MyAdapter(Context context, List<Model> imgList) {
        this.context = context;
        this.imgList = imgList;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return imgList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        ((ViewPager)container).removeView((View)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //Inflate View
        View view = inflater.inflate(R.layout.view_pager_item, container, false);

        //View
        ImageView bike_Img = (ImageView) view.findViewById(R.id.bikeImg);
        /*TextView bike_Txt = (TextView)view.findViewById(R.id.bikeTxt);*/


        //set Data

        //image
        Picasso.get().load(imgList.get(position).getImg()).into(bike_Img);

        //text//
       /* bike_Txt.setText(imgList.get(position).getTitle());*/

        container.addView(view);
        return view;
    }
}

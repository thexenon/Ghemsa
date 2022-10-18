package com.thexenon.ghemsaapp.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thexenon.ghemsaapp.Interface.IFirebaseLoadDone;
import com.thexenon.ghemsaapp.R;
import com.thexenon.ghemsaapp.Transformer.DepthPageTransformer;
import com.thexenon.ghemsaapp.adapters.MyAdapter;
import com.thexenon.ghemsaapp.models.Model;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class AboutActivity extends AppCompatActivity implements IFirebaseLoadDone {

    ViewPager viewPager;
    MyAdapter adapter;

    DatabaseReference bikes;

    IFirebaseLoadDone iFirebaseLoadDone;

    ProgressDialog progressDialog;

    Button preBtn, nxtBtn;
    List<Model> imgList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        bikes = FirebaseDatabase.getInstance().getReference("Executives Images");


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Images...");
        iFirebaseLoadDone = this;

        loadBikes();

        viewPager = (ViewPager) findViewById(R.id.view_pagermain);
        //for transformation when image slided
        viewPager.setPageTransformer(true, new DepthPageTransformer());
        viewPager.setCurrentItem(0);


        preBtn = findViewById(R.id.preBtn);
        nxtBtn = findViewById(R.id.nxtBtn);


        progressDialog.show();


    }

    private void loadBikes() {
        bikes.addListenerForSingleValueEvent(new ValueEventListener() {



            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot bikeSnapShot : dataSnapshot.getChildren())
                    imgList.add(bikeSnapShot.getValue(Model.class));
                iFirebaseLoadDone.onFirebaseLoadSuccess(imgList);
                Toast.makeText(AboutActivity.this, "Total number of image "+imgList.size(), Toast.LENGTH_SHORT).show();

                progressDialog.dismiss();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(AboutActivity.this, "Something wrong ....", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public void onFirebaseLoadSuccess(List<Model> imgList) {

        adapter = new MyAdapter(this, imgList);
        viewPager.setAdapter(adapter);

        //for circle indicator
        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);


    }

    @Override
    public void onFirebaseLoadFailed(String message) {
        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
    }

    //for next prev button
    public void pre(View view) {


        if (viewPager.getCurrentItem() == 1 ){
            preBtn.setVisibility(View.GONE);
            nxtBtn.setVisibility(View.VISIBLE);

        }else {
            preBtn.setVisibility(View.VISIBLE);
            nxtBtn.setVisibility(View.VISIBLE);


        }
        if (viewPager.getCurrentItem() != 0){
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }



    }

    public void nxt(View view) {

        if (viewPager.getCurrentItem() == imgList.size()-2){
            nxtBtn.setVisibility(View.GONE);
            preBtn.setVisibility(View.VISIBLE);
        }else {
            preBtn.setVisibility(View.VISIBLE);
            nxtBtn.setVisibility(View.VISIBLE);


        }
        if (viewPager.getCurrentItem() != imgList.size()-1){
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
        }
    }

}

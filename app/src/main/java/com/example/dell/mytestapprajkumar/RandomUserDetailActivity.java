package com.example.dell.mytestapprajkumar;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dell on 21/06/2017.
 */

public class RandomUserDetailActivity extends AppCompatActivity implements Config {

    @BindView(R.id.textName) TextView textName;
    @BindView(R.id.textLocation) TextView textLocation;
    @BindView(R.id.imgPic) ImageView imgPic;
    @BindView(R.id.textPoneNumber) TextView textPoneNumber;
    @BindView(R.id.textEmail) TextView textEmail;
    String Pic, Email, Location, Phone, Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randomuser_detail);
        ButterKnife.bind(this);
        try {
            Email = getIntent().getStringExtra("email");
            Pic = getIntent().getStringExtra("pic");
            Location = getIntent().getStringExtra("location");
            Phone = getIntent().getStringExtra("phone");
            Name = getIntent().getStringExtra("name");
        } catch (Exception e) {
            e.printStackTrace();
        }

        textName.setText(Name);
        textPoneNumber.setText(Phone);
        textEmail.setText(Email);
        textLocation.setText(Location);


        Glide.with(getApplicationContext()).load(Pic).asBitmap().placeholder(R.drawable.img_marico_logo).centerCrop().into(new BitmapImageViewTarget(imgPic) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(getApplicationContext().getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                imgPic.setImageDrawable(circularBitmapDrawable);
            }
        });
    }
}

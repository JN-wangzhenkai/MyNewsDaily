package com.example.mynewsdaily.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.mynewsdaily.R;

public class LogoActivity extends AppCompatActivity {
    private ImageView mImageView;
    private Animation animation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        mImageView= (ImageView) findViewById(R.id.iv_logo);
        animation = AnimationUtils.loadAnimation(this, R.anim.alpha_logo);
        mImageView.startAnimation(animation);

        Handler handler=new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent=new Intent(LogoActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        },2000);
    }
}

package com.cooltechworks.scratchview.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cooltechworks.views.ScratchImageView;

public class CaptchaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captcha);

        ScratchImageView siv = (ScratchImageView) findViewById(R.id.sample_image);
        siv.setEnabled(false);

    }

}

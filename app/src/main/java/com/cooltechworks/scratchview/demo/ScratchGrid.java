package com.cooltechworks.scratchview.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import com.cooltechworks.views.ScratchImageView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ScratchGrid extends AppCompatActivity {

    GridLayout mScratchGridLayout;
    List<ScratchImageView> mScratchImageViewList;
    boolean mScratchStarted = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scratch_grid);
        mScratchImageViewList = new ArrayList<>();
        mScratchGridLayout = (GridLayout)findViewById(R.id.scratch_grid_layout);

        int winRow = randInt(0, 2);
        int winCol = randInt(0, 2);

        for (int iRow = 0; iRow < 3; iRow++){
           for (int iCol = 0; iCol < 3; iCol++){

               boolean winCell = ((iRow == winRow) &&  (iCol == winCol));
               GenerateScratchView(iRow, iCol, winCell);
           }
        }
    }


    public void GenerateScratchView(int iRow, int iCol, final boolean winCell){
        ScratchImageView scratchImageView = new ScratchImageView(this);
        scratchImageView.setBackgroundResource(R.color.white);
        if (winCell){
            scratchImageView.setImageDrawable(getResources().getDrawable(R.drawable.img_sample3));
        }else{
            scratchImageView.setImageDrawable(getResources().getDrawable(R.drawable.img_sample2));
        }
        GridLayout.LayoutParams sivLayout = new GridLayout.LayoutParams();
        sivLayout.width = GetDPValue(100f);
        sivLayout.height = GetDPValue(100f);
        sivLayout.setGravity(Gravity.CENTER);
        sivLayout.rowSpec = GridLayout.spec(iRow, GridLayout.CENTER);
        sivLayout.columnSpec = GridLayout.spec(iCol, GridLayout.CENTER);

        int margins = GetDPValue(10f);
        sivLayout.setMargins(margins, margins, margins, margins);

        scratchImageView.setLayoutParams(sivLayout);
        scratchImageView.setRevealListener(new ScratchImageView.IRevealListener() {
            @Override
            public void onRevealed(ScratchImageView tv) {
                Log.d("Scratch Grid", "Revealed!!!");
                if (winCell){
                    Log.d("Scratch Grid", "WIN!!!");

                    Toast.makeText(getApplicationContext(), "WIN!", Toast.LENGTH_LONG).show();
                }else{
                    Log.d("Scratch Grid", "LOSE!!!");
                    Toast.makeText(getApplicationContext(), "LOSE!", Toast.LENGTH_LONG).show();
                }
            }
        });
        scratchImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mScratchStarted){
                    return false;
                }
                mScratchStarted = true;

                for (int i = 0; i < mScratchImageViewList.size(); i++){
                    if (mScratchImageViewList.get(i) != v){
                       //mScratchImageViewList.get(i).setEnabled(false);
                    }
                }
                return false;
            }
        });
        mScratchImageViewList.add(scratchImageView);
        mScratchGridLayout.addView(scratchImageView);
    }

    private int GetDPValue(float value){
        DisplayMetrics metrics = getApplicationContext().getResources().getDisplayMetrics();
        return (int) (metrics.density * value);
    }


    public static int randInt(int min, int max) {

        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}

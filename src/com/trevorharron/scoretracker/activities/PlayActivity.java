package com.trevorharron.scoretracker.activities;

import android.app.Activity;

import android.os.Bundle;
import android.view.View;

import com.trevorharron.scoretracker.R;


public class PlayActivity extends Activity implements View.OnClickListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_ui);
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    @Override
    public void onClick(View view){

    }
}

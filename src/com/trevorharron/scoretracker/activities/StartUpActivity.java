package com.trevorharron.scoretracker.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.content.Intent;

import com.trevorharron.scoretracker.R;

/**
 * Created by Trevor Harron on 1/7/14.
 */
public class StartUpActivity extends Activity implements OnClickListener {
    Button helpButton, makeButton,infoButton, leaveButton;
    //First call for the Activity
    private static String START_TAG = "Score Tracker Startup";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up_ui);
        helpButton = (Button) findViewById(R.id.button_help);
        if (helpButton != null)
            helpButton.setOnClickListener(this);

        infoButton = (Button) findViewById(R.id.button_info);
        if (infoButton != null)
            infoButton.setOnClickListener(this);

        makeButton = (Button) findViewById(R.id.button_make);
        if (makeButton != null)
            makeButton.setOnClickListener(this);


        leaveButton = (Button) findViewById(R.id.button_leave);
        if (leaveButton != null)
            leaveButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        if (view.getId() == R.id.button_help){
            //Help Button actions
            Log.v(START_TAG,  "Help has been pressed.");
            new AlertDialog.Builder(this)
                    .setCancelable(true)
                    .setMessage(R.string.content_help)
                    .setTitle(R.string.button_help)
                    .show();
        }
        if (view.getId() == R.id.button_info){
            //Info Button actions
            Log.v(START_TAG, "Info has been pressed");
            new AlertDialog.Builder(this)
                .setCancelable(true)
                .setMessage(R.string.content_info)
                .setTitle(R.string.title_about)
                .show();
        }
        if (view.getId() == R.id.button_make){
            //Make Button actions
            Log.v(START_TAG, "Make has been pressed");
            Intent makeActivityIntent = new Intent(StartUpActivity.this, MakeActivity.class);
            startActivity(makeActivityIntent);
        }
        if (view.getId() == R.id.button_leave){
            //Leave Button actions
            Log.v(START_TAG, "Leave has been pressed");
            Intent leaveIntent = new Intent(Intent.ACTION_MAIN);

            leaveIntent.addCategory(Intent.CATEGORY_HOME);
            leaveIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(leaveIntent);

            leavePressed();
        }
    }

    private void leavePressed() {

            int pid = android.os.Process.myPid();
            android.os.Process.killProcess(pid);
    }

}
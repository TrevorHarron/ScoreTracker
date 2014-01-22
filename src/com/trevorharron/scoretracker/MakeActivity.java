package com.trevorharron.scoretracker;

import android.app.Activity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * Created by trevorharron on 1/12/14.
 */
public class MakeActivity extends Activity implements OnClickListener {

    private static String MAKE_TAG = "Score Tracker Make";

    private Button infoButton;
    private Button makeGameButton;
    private Button clearButton;

    private EditText textGameName;
    private EditText textScoreLimit;
    private EditText textTurnLimit;
    private EditText textIncrement;

    private CheckBox checkHasTurnLimit;
    private CheckBox checkHasScoreLimit;

    private String gameName;
    private boolean hasScoreLimit;
    private boolean hasTurnLimit;
    private int turnLimit = -1;
    private int scoreLimit = -1;
    private int increment;


    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_game_ui);

        //View load
        infoButton = (Button) findViewById(R.id.button_info);
        if(infoButton != null){
            infoButton.setOnClickListener(this);
            Log.i(MAKE_TAG, "Info button created");
        }

        makeGameButton = (Button) findViewById(R.id.button_make);
        if(makeGameButton != null){
            makeGameButton.setOnClickListener(this);
            Log.i(MAKE_TAG, "Make button created");
        }

        clearButton = (Button) findViewById(R.id.button_clear);
        if(clearButton != null){
            clearButton.setOnClickListener(this);
            Log.i(MAKE_TAG, "Clear button created");
        }

        checkHasScoreLimit = (CheckBox) findViewById(R.id.is_score_limit);
        if(checkHasScoreLimit != null)
            Log.i(MAKE_TAG, "Score Limit box created");

        checkHasTurnLimit = (CheckBox) findViewById(R.id.is_turn_limit);
        if(checkHasTurnLimit != null)
            Log.i(MAKE_TAG, "Turn Limit box created");

        textGameName = (EditText) findViewById(R.id.game_name);
        if(textGameName != null)
            Log.i(MAKE_TAG, "Text Game Name created");

        textScoreLimit = (EditText) findViewById(R.id.score_limit);
        if(textScoreLimit != null)
            Log.i(MAKE_TAG, "Text Game Score Limit created");

        textTurnLimit = (EditText) findViewById(R.id.turn_limit);
        if(textTurnLimit != null)
            Log.i(MAKE_TAG, "Text Turn Limit created");

        textIncrement = (EditText) findViewById(R.id.score_increment);
        if(textIncrement != null)
            Log.i(MAKE_TAG, "Text Increment created");
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

    public void onClick(View view){
        if(view.getId() == R.id.button_clear){
            Log.v(MAKE_TAG,"Clear has been pressed.");
            checkHasTurnLimit.setChecked(false);
            hasTurnLimit = false;
            checkHasScoreLimit.setChecked(false);
            hasScoreLimit =false;
            textGameName.setText(R.string.game_name);
            gameName = "";
            textTurnLimit.setText(R.string.turn_limit);
            turnLimit = -1;
            textScoreLimit.setText(R.string.score_limit);
            scoreLimit = -1;
            textIncrement.setText(R.string.score_increment);
            increment = -1;
        } else if(view.getId() == R.id.button_info){
            Log.v(MAKE_TAG, "Info has been pressed.");
            new AlertDialog.Builder(this)
                    .setCancelable(true)
                    .setMessage(R.string.make_info)
                    .setTitle(R.string.button_info)
                    .show();
        } else if(view.getId() == R.id.button_make){
            Log.v(MAKE_TAG, "Make has been pressed");

            try{
                if(blankOrDefault(textGameName, R.string.game_name)){
                    showError(R.string.error_null_name);
                    return;
                } else {
                    gameName = textGameName.getText().toString();
                }

                increment = Integer.valueOf(textIncrement.getText().toString());

                hasTurnLimit = checkHasTurnLimit.isChecked();
                hasScoreLimit = checkHasScoreLimit.isChecked();

                if(hasTurnLimit && !(blankOrDefault(textTurnLimit, R.string.turn_limit))){
                    turnLimit = Integer.valueOf(textTurnLimit.getText().toString());
                    if(turnLimit < 0){
                        showError(R.string.turn_error);
                        return;
                    }
                } else if (hasTurnLimit && blankOrDefault(textTurnLimit, R.string.turn_limit)){
                    showError(R.string.field_error);
                    return;
                }

                if(hasScoreLimit && !blankOrDefault(textScoreLimit, R.string.score_limit)){
                    scoreLimit = Integer.valueOf(textScoreLimit.getText().toString());
                } else if(hasScoreLimit && blankOrDefault(textScoreLimit, R.string.score_limit)){
                    showError(R.string.field_error);
                    return;
                }

            } catch (NullPointerException e){
                Log.e(MAKE_TAG, "Error gathering from the fields: Null pointer", e);
                showError(R.string.field_error);
                return;
            } catch(NumberFormatException e){
                Log.e(MAKE_TAG, "Error gathering from the fields: Number Format", e);
                showError(R.string.number_error);
                return;
            } catch (Exception e){
                Log.e(MAKE_TAG, "Unforeseen Error: ", e);
                showError(R.string.unknown_error);
                return;
            }
        }
    }

    private void showError(final int errorMsgId){
        new AlertDialog.Builder(this)
                .setCancelable(true)
                .setMessage(errorMsgId)
                .setTitle(R.string.title_error)
                .show();
    }

    private boolean blankOrDefault(final EditText edit, final int defaultId){
        return edit.getText().toString().isEmpty() ||
                edit.getText().toString().equals(getString(defaultId));
    }
}

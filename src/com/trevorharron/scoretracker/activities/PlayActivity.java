package com.trevorharron.scoretracker.activities;

import android.app.AlertDialog;
import android.app.ListActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.ListView;
import android.widget.Button;
import android.widget.TextView;

import com.trevorharron.scoretracker.R;
import com.trevorharron.scoretracker.engine.ScoreTracker;
import com.trevorharron.scoretracker.engine.ScoreTrackerImpl;
import com.trevorharron.scoretracker.game.Game;
import com.trevorharron.scoretracker.game.GameBasicImpl;

import java.util.ArrayList;


public class PlayActivity extends ListActivity implements View.OnClickListener{

    private static String PLAY_TAG = "Score Tracker Play";

    private Intent cIntent;

    private String gameName;
    private int increment;
    private int startScore;

    private int turnLimit;
    private boolean hasTurnLimit;

    private int scoreLimit;
    private boolean hasScoreLimit;

    private ListView playerScores;

    private Game game;
    private ScoreTracker tracker;

    private ArrayList<String> players;
    private Button newButton;
    private Button nextButton;
    private TextView currentPlayer;
    private TextView turnNum;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_ui);

        newButton = (Button) findViewById(R.id.new_button);
        if(newButton != null){
            newButton.setOnClickListener(this);
            Log.i(PLAY_TAG, "New Button created");
        }

        nextButton = (Button) findViewById(R.id.button_next);
        if(nextButton != null){
            nextButton.setOnClickListener(this);
            Log.i(PLAY_TAG, "Next Button created");
        }

        cIntent = getIntent();

        gameName = cIntent.getStringExtra("game_name");
        players = cIntent.getStringArrayListExtra("players");
        increment = Integer.parseInt(cIntent.getStringExtra("increment"));
        startScore = Integer.parseInt(cIntent.getStringExtra("start_score"));

        hasTurnLimit = Boolean.getBoolean(cIntent.getStringExtra("has_turn_limit"));
        turnLimit =Integer.parseInt(cIntent.getStringExtra("turn_limit"));

        hasScoreLimit = Boolean.getBoolean(cIntent.getStringExtra("has_score_limit"));
        scoreLimit =Integer.parseInt(cIntent.getStringExtra("score_limit"));

        game = new GameBasicImpl(gameName,startScore,increment, hasScoreLimit,scoreLimit,
                hasTurnLimit,turnLimit,players);
        tracker = new ScoreTrackerImpl(game);
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

    @Override
    protected void onListItemClick(ListView lView, View view, int position, long id) {
        super.onListItemClick(lView, view, position, id);
    }

    private void showError(final int errorMsgId){
        new AlertDialog.Builder(this)
                .setCancelable(true)
                .setMessage(errorMsgId)
                .setTitle(R.string.title_error)
                .show();
    }

}

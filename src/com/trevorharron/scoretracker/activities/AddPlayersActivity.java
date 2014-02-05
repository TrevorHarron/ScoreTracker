package com.trevorharron.scoretracker.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.trevorharron.scoretracker.R;

import java.util.ArrayList;

/**
 * Created by trevorharron on 1/22/14.
 */
public class AddPlayersActivity extends Activity implements View.OnClickListener{

    private static String ADD_TAG = "Score Tracker Add";

    private Intent cIntent;

    private TextView gameNameView;
    private EditText textPlayerName;

    private Button addButton;
    private Button removeButton;
    private Button playButton;

    private ListView playerList;

    private String gameName;
    private boolean hasScoreLimit;
    private boolean hasTurnLimit;
    private int turnLimit = -1;
    private int scoreLimit = -1;
    private int increment;
    private int startScore;


    private ArrayList<String> players;
    private ArrayAdapter<String> playersAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_players_layout_ui);

        cIntent = getIntent();

        players = new ArrayList<String>();

        gameNameView = (TextView) findViewById(R.id.game_name);
        if( gameNameView != null){
            gameName = cIntent.getStringExtra("game_name");
            gameNameView.setText(gameName);
        }
        textPlayerName = (EditText) findViewById(R.id.text_player_name);
        if(textPlayerName != null)
            Log.i(ADD_TAG, "Player name field created");

        addButton = (Button) findViewById(R.id.button_add);
        if(addButton != null){
            addButton.setOnClickListener(this);
            Log.i(ADD_TAG, "Add button created");
        }

        removeButton = (Button) findViewById(R.id.button_remove);
        if(removeButton != null){
            removeButton.setOnClickListener(this);
            Log.i(ADD_TAG, "Remove button created");
        }

        playButton = (Button) findViewById(R.id.button_play);
        if(playButton != null){
            playButton.setOnClickListener(this);
            Log.i(ADD_TAG, "Play button created");
        }

        textPlayerName = (EditText) findViewById(R.id.text_player_name);
        if(textPlayerName != null){
            Log.i(ADD_TAG, "Player name created");
        }

        playerList = (ListView) findViewById(android.R.id.list);
        if(playerList != null){
            Log.i(ADD_TAG, "Player List View created");
            playersAdapter = new ArrayAdapter<String>(this, R.layout.simple_list, R.id.name_view,players);
            playerList.setAdapter(playersAdapter);
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        //get data
    }

    public void onClick(View view){
        try{
            if(view.getId() == R.id.button_add){
                Log.i(ADD_TAG, "Add press");
                if(!blankOrDefault(textPlayerName,R.id.text_player_name)
                        && !players.contains(textPlayerName.getText().toString())){
                    players.add(textPlayerName.getText().toString());
                    textPlayerName.setText(R.string.default_name);
                    playersAdapter.notifyDataSetChanged();
                }
            }  else if(view.getId() == R.id.button_remove){
                Log.i(ADD_TAG, "Remove Pressed");
                if(players.contains(textPlayerName.getText().toString())){
                    players.remove(textPlayerName.getText().toString());
                    playersAdapter.notifyDataSetChanged();
                }
            } else if(view.getId() == R.id.button_play){
                Log.i(ADD_TAG, "Play pressed");
                if(!players.isEmpty()){

                    Intent nextIntent = new Intent(AddPlayersActivity.this, PlayActivity.class);
                    nextIntent.putExtra("game_name", cIntent.getStringExtra("game_name"));
                    nextIntent.putExtra("start_score",cIntent.getStringExtra("start_score"));
                    nextIntent.putExtra("increment",cIntent.getStringExtra("increment"));

                    nextIntent.putExtra("has_score_limit",cIntent.getStringExtra("has_score_limit"));
                    nextIntent.putExtra("score_limit", cIntent.getStringExtra("score_limit"));

                    nextIntent.putExtra("has_turn_limit", cIntent.getStringExtra("has_turn_limit"));
                    nextIntent.putExtra("turn_limit", cIntent.getStringExtra("turn_limit"));

                    nextIntent.putStringArrayListExtra("players",players);
                    startActivity(nextIntent);
                } else {
                    showError(R.string.empty_error);
                }
            }
        } catch(NullPointerException e){
            Log.e(ADD_TAG,"Null Pointer Exception",e);
            showError(R.string.field_error);
        } catch (Exception e){
            Log.e(ADD_TAG, "Exception",e);
            showError(R.string.unknown_error);
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


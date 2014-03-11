package com.trevorharron.scoretracker.game;

import java.util.ArrayList;

import android.util.Log;

import com.trevorharron.scoretracker.exceptions.NotUsedException;

public class GameBasicImpl implements Game {

    private String name;

    private ArrayList<String> players;

    private int startingScore;
    private int increment;
    private int scoreLimit;
    private boolean useScoreLimit;

    private int round;
    private int roundLimit;
    private boolean useRoundLimit;

    public GameBasicImpl(final String name, final int startingScore, final int increment,
                         final boolean useScoreLimit, final int scoreLimit,
                         final boolean useRoundLimit,final int roundLimit,
                         final ArrayList<String> players){

        this.name = name;
        this.useRoundLimit = useRoundLimit;
        this.players = players;
        this.startingScore = startingScore;
        this.increment = increment;
        if(useRoundLimit)
            this.roundLimit = roundLimit;
        else
            this.roundLimit = -1; //A value we will not look at but still needs to be initialized
        this.useScoreLimit = useScoreLimit;
        if(useScoreLimit)
            this.scoreLimit = scoreLimit;
        else
            this.scoreLimit = -1; //A value we will not look at but still needs to be initialized
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getCurrentRound() {
        return this.round;
    }

    @Override
    public Integer getStartValue() {
        return this.startingScore;
    }

    @Override
    public ArrayList<String> getPlayerList() {
        return this.players;
    }

    @Override
    public void nextRound() { this.round += 1; }

    @Override
    public boolean useRoundLimit() { return this.useRoundLimit; }

    @Override
    public int getMaxRounds() throws NotUsedException {
        if(this.useRoundLimit)
            return this.roundLimit;
        else
            throw new NotUsedException("Round Limit Not Used");
    }

    @Override
    public int getScoreLimit() throws NotUsedException {
        if(this.useScoreLimit)
            return this.scoreLimit;
        else
            throw new NotUsedException("Score Limit Not Used");
    }

    @Override
    public int getRoundLimit() throws NotUsedException {
        if(this.useRoundLimit)
            return this.roundLimit;
        else
            throw new NotUsedException("Round Limit Not Used");
    }

    @Override
    public int getIncrement(){
        return increment;
    }

    @Override
    public void removePlayer(String player) {
        try{
            if (!this.players.remove(player))
                Log.d("Remove", player + " not found in the list");
        } catch (UnsupportedOperationException e){
            Log.w("Remove", "operation not supported");
        } catch (Exception e){
            Log.w("Remove", "Unknown Error occured ");
            e.printStackTrace();
        }
    }

    @Override
    public void addPlayer(String player) {
        players.add(player);
    }

}

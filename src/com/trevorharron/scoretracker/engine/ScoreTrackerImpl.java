package com.trevorharron.scoretracker.engine;


import java.util.ArrayList;
import java.util.HashMap;

import com.trevorharron.scoretracker.exceptions.NoPlayerException;
import com.trevorharron.scoretracker.game.Game;

public class ScoreTrackerImpl implements ScoreTracker {
	
	private Game game;
	private HashMap<String, ArrayList<Integer>> scores;
    private HashMap<String, Integer> totals;

	public ScoreTrackerImpl(final Game game){
		this.game = game;
		this.scores = new HashMap<String, ArrayList<Integer>>(game.getPlayerList().size());
        this.totals = new HashMap<String, Integer>(game.getPlayerList().size());
		for(int playerIndex = 0; playerIndex < game.getPlayerList().size(); playerIndex++){
			ArrayList<Integer> playerScoreList =  new ArrayList<Integer>();
			playerScoreList.add(game.getStartValue());
			scores.put(game.getPlayerList().get(playerIndex), playerScoreList);
            totals.put(game.getPlayerList().get(playerIndex), 0);
		}
	}

	@Override
	public HashMap<String, Integer> getTotals() {
		return totals;
	}

	@Override
    //TODO Check this
	public HashMap<String, ArrayList<Integer>> scoresAndTotals() {
		ArrayList<String> playerList = game.getPlayerList();
		HashMap<String, ArrayList<Integer>> scoresAndTotals = new HashMap<String, ArrayList<Integer>>(playerList.size());
		
		for(int playerIndex = 0; playerIndex < playerList.size(); playerIndex++){
			String player = playerList.get(playerIndex);
			ArrayList<Integer> scoresList = scores.get(player);
			ArrayList<Integer> playerScoreList = new ArrayList<Integer>(scoresList.size() + 1);
			for(int index = 0; index < scoresList.size(); index++)
				playerScoreList.add(scoresList.get(index));
			scoresAndTotals.put(player, playerScoreList);
			scoresAndTotals.get(player).add(totals.get(player));
		}
		return scoresAndTotals;
	}
	
	@Override
	public void addScore(String player, int points) throws NoPlayerException {
		if(game.getPlayerList().contains(player)){
			scores.get(player).add(points);
            totals.put(player, totals.get(player) + points);
		} else {
			throw new NoPlayerException(player + " not in players list");
		}
	}

	@Override
	public String getGame() {
		return this.game.getName();
	}

	@Override
	public void setGame(Game game) {
		this.game = game;
	}

}

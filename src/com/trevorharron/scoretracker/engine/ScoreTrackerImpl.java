package com.trevorharron.scoretracker.engine;


import java.util.ArrayList;
import java.util.HashMap;

import com.trevorharron.scoretracker.exceptions.NoPlayerException;
import com.trevorharron.scoretracker.game.Game;

public class ScoreTrackerImpl implements ScoreTracker {
	
	private Game game;
	private HashMap<String, ArrayList<Integer>> scores;

	public ScoreTrackerImpl(final Game game){
		this.game = game;
		this.scores = new HashMap<String, ArrayList<Integer>>(game.getPlayerList().size());
		for(int playerIndex = 0; playerIndex < game.getPlayerList().size(); playerIndex++){
			ArrayList<Integer> playerScoreList =  new ArrayList<Integer>();
			playerScoreList.add(game.getStartValue());
			scores.put(game.getPlayerList().get(playerIndex), playerScoreList);
		}
	}

	@Override
	public HashMap<String, Integer> totals() {
		ArrayList<String> playerList = game.getPlayerList();
		HashMap<String, Integer> totalsHash = new HashMap<String, Integer>(playerList.size());
		for(int playerIndex = 0; playerIndex < playerList.size(); playerIndex++){
			String player = playerList.get(playerIndex);
			int total = calculateTotals(scores.get(player));
			totalsHash.put(player, total);
		}
		return totalsHash;
	}

	@Override
	public HashMap<String, ArrayList<Integer>> scoresAndTotals() {
		ArrayList<String> playerList = game.getPlayerList();
		HashMap<String, ArrayList<Integer>> scoresAndTotals = new HashMap<String, ArrayList<Integer>>(playerList.size());
		
		for(int playerIndex = 0; playerIndex < playerList.size(); playerIndex++){
			String player = playerList.get(playerIndex);
			ArrayList<Integer> scoresList = scores.get(player);
			ArrayList<Integer> playerScoreList = new ArrayList<Integer>(scoresList.size() + 1);
			for(int index = 0; index < scoresList.size(); index++){
				playerScoreList.add(scoresList.get(index));
			}
			scoresAndTotals.put(player, playerScoreList);
			scoresAndTotals.get(player).add(calculateTotals(playerScoreList));
		}
		return scoresAndTotals;
	}
	
	private int calculateTotals(final ArrayList<Integer> playerScoreList){
		int score = 0;
		for(int index = 0; index < playerScoreList.size(); index++){
			score += playerScoreList.get(index);
		}
		return score;
	}
	
	@Override
	public void addScore(String player, int points) throws NoPlayerException {
		if(game.getPlayerList().contains(player)){
			this.scores.get(player).add(points);
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

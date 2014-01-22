package com.trevorharron.scoretracker.engine;

import java.util.ArrayList;
import java.util.HashMap;

import com.trevorharron.scoretracker.exceptions.NoPlayerException;
import com.trevorharron.scoretracker.game.Game;

public interface ScoreTracker {
	
	/**
	 * @returns The game for this engine
	 */
	String getGame();
	
	/**
	 * This method takes each player and totals 
	 * their score for display purposes
	 * @return A map of the players and their totaled scores
	 */
	HashMap<String, Integer> totals();
	
	/**
	 * @return A map of players there scores and totals by round
	 */
	HashMap<String, ArrayList<Integer>> scoresAndTotals();
	
	/**
	 * Adds the points for a player's turn to their score and updates it
	 * @param player
	 * @param points
	 * @throws com.trevorharron.scoretracker.exceptions.NoPlayerException
	 */
	void addScore(String player, int points) throws NoPlayerException;
	
	/**
	 * @param game
	 */
	void setGame(Game game);
	
}

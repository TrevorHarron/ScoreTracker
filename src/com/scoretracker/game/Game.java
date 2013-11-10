package com.scoretracker.game;

import java.util.ArrayList;

import com.scoretracker.exceptions.NotUsedException;

public interface Game {

	/**
	 * @param name is the name of the game if it needs to be changed
	 */
	void setName(String name);
	
	/**
	 * @return the name of the game
	 */
	String getName();

	/**
	 * Get the start value initialized with the game
	 * @return
	 */
	Integer getStartValue();

	/**
	 * Get the list of players from the game
	 * @return
	 */
	ArrayList<String> getPlayerList();

	/**
	 * Return if a roundLimit for the game is used
	 * @return
	 */
	boolean useRoundLimit(); 
	
	/**
	 * Get the roundLimit for the game
	 * @return
	 * @throws NotUsedException 
	 */
	int getRoundLimit() throws NotUsedException;
	
	/**
	 * Get the current round
	 * @return the current round number
	 */
	int getCurrentRound();
	
	/**
	 * Increment the round
	 */
	void nextRound();
	

	/**
	 * Ask for the maximum number of rounds we can go
	 * @return limit of rounds
	 * @throws NotUsedException
	 */
	int getMaxRounds() throws NotUsedException;
	
	/**
	 * Give the score limit
	 * @return score limit
	 * @throws NotUsedException
	 */
	int getScoreLimit() throws NotUsedException;

	/**
	 * Remove a player from a game
	 * @param player
	 */
	void removePlayer(String player);
	
	/**
	 * Add a player to a game
	 * @param palyer
	 */
	void addPlayer(String player);
}

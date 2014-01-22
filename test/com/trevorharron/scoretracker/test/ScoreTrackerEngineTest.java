/**
 * 
 */
package com.trevorharron.scoretracker.test;


import java.util.ArrayList;
import java.util.HashMap;

import junit.framework.TestCase;

import com.trevorharron.scoretracker.engine.ScoreTrackerImpl;
import com.trevorharron.scoretracker.game.Game;
import com.trevorharron.scoretracker.game.GameBasicImpl;

/**
 * @author gurutre
 *
 */
public class ScoreTrackerEngineTest extends TestCase{
	
	private ArrayList<String> testPlayerList;
	ScoreTrackerImpl testEngine;
	Game testGame;
	/**
	 * @throws java.lang.Exception
	 */
	public void setUp() throws Exception {
		testPlayerList = new ArrayList<String>();
		testPlayerList.add("Bob");
		testPlayerList.add("Susan");
		//, testPlayerList, true, 10, true, 10
		testGame = new GameBasicImpl("Foo Game", 0, true, 0, true, 0);
		testGame.addPlayer("Bob");
		testGame.addPlayer("Susan");
		testEngine = new ScoreTrackerImpl(testGame);
		System.out.println(testGame.getPlayerList());
		testEngine.addScore("Bob", 10);
		testEngine.addScore("Susan", 15);
	}

	public void testGetName() {
		String testName = testGame.getName();
		assertEquals("The name return should be Foo Game","Foo Game", testName);
	}
	
	public void testTotals(){
		HashMap<String, Integer> totals = new HashMap<String, Integer>();
		totals = testEngine.totals();
		HashMap<String, Integer> desired = new HashMap<String, Integer>();
		desired.put("Bob", 10);
		desired.put("Susan", 15);
		assertEquals("The totals HashMaps should be equal", desired, totals);
	}
	
	public void testScoreAndTotals(){
		HashMap<String, ArrayList<Integer>> scoreAndTotals = new HashMap<String, ArrayList<Integer>>();
		scoreAndTotals = testEngine.scoresAndTotals();
		HashMap<String, ArrayList<Integer>> desired = new HashMap<String, ArrayList<Integer>>();
		ArrayList<Integer> bobScore = new ArrayList<Integer>();
		bobScore.add(0);
		bobScore.add(10);
		bobScore.add(10);
		ArrayList<Integer> susanScore = new ArrayList<Integer>();
		susanScore.add(0);
		susanScore.add(15);
		susanScore.add(15);
		desired.put("Bob", bobScore);
		desired.put("Susan", susanScore);
		assertEquals("The totals HashMaps should be equal", desired, scoreAndTotals);
	}

}

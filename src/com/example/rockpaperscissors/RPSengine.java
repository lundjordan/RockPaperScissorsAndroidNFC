package com.example.rockpaperscissors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RPSengine {
	
	static List<String> options = new ArrayList<String>();
	static Map<String, String> stats = new HashMap<String, String>();
    static Random randomGenerator = new Random();

	
	public static void setup() {
		options.add("rock");
		options.add("paper");
		options.add("scissors");
	}
	
	public static Map<String, String> play(String userChoice) {
		setup();
		stats.put("userChoice", userChoice);
		stats.put("opponentChoice", options.get(randomGenerator.nextInt(3)));
		stats.put("result", getResult(stats.get("userChoice"), stats.get("opponentChoice")));
		
		return stats;
	}
	
	public static String getResult(String user, String computer) {
		if (user.equals("rock")) {
			if (user.equals(computer)) {
				return "draw";
			}
			else if (computer.equals("paper")) {
				return "lose";
			}
			else {
				return "win";
			}
		}
		else if (user.equals("paper")){
			if (user.equals(computer)) {
				return "draw";
			}
			else if (computer.equals("scissors")) {
				return "lose";
			}
			else {
				return "win";
			}
		}
		else {
			if (user.equals(computer)) {
				return "draw";
			}
			else if (computer.equals("rock")) {
				return "lose";
			}
			else {
				return "win";
			}
		}
		
	}
	
	
	
	
	

}

package mastermind.io;

import mastermind.ColorPicker;

import java.util.Scanner;

public class UserIO implements ColorPicker {

	public static String UserInput() {
		boolean loop = true;
		Scanner input = new Scanner(System.in);
		String givenAnswer = "";
		do {
			try {
				String answer = input.next();
				givenAnswer = answer;
				if (answer.toLowerCase().startsWith("q") | answer.toLowerCase().startsWith("g")) { //controleert of het antwoord begint met Q of G.
					loop = false;
					break;
				}
				if (answer.length() != 4) throw new Exception(); //gooit exceptions als antwoord niet 4 letters lang is.
				loop = false;
			} catch (Exception e) {
				System.out.println(ANSI_RED + "Daar ging wat mis, heb je wel een 4-letterige code ingevoerd?");
			}
		} while (loop);
		return givenAnswer;
	}
	
	String UserInput(String question) {
		return question;
		
	}
	
	private static void quitGame() { //Quits the game.
		System.out.println(ANSI_GREEN + "\nWas leuk om met je/jullie te spelen!");
		System.exit(0);
	}
}

package mastermind.game;

import mastermind.io.UserIO;

import java.util.Arrays;
import java.util.Random;

public class Game {
	final char[] colours = {'a','b','c','d','e','f'};
	private boolean gameLoop = true;
	private char[] code = {'d','a','e','c'};
	
	public void start() {
		printCode(); //print de hardcoded code uit
		while (gameLoop) {
			System.out.println("Geef invoer, q om te stoppen:");
			String answer = UserIO.UserInput();
			checkCode(answer);
		}
	}
	protected void checkCode(String answer) { 
		//Controleert voor nu of de eerste character van het gegeven antwoord overeenkomt met de eerste character van de code.
		if(answer.charAt(0) == code[0]) {
			System.out.println("Goed gedaan.");
		} else {
			System.out.println("Helaas.");
		}
	}
	
	protected void printCode() {
		//Print de code uit.
		System.out.println("De code is: " + Arrays.toString(code));
	}
	
	protected String getCode() {
		//Haalt de code op en geeft deze terug als String
		String codeString = Arrays.toString(code);
		return codeString;
	}
}

package mastermind.game;

import mastermind.io.UserIO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Game {
	final char[] colours = {'a','b','c','d','e','f'};
	private boolean gameLoop = true;
	private char[] code = {'d','a','e','c'};
	
	public void start() {
		generateCode();
		printCode(); //print de gegenereerde code uit
		System.out.println("De code kan bestaan uit de volgende letters:");
		System.out.println(Arrays.toString(colours));
		while (gameLoop) {
			System.out.println("Voer je antwoord in: (Q om te stoppen / G om een nieuw code te genereren.)");
			String answer = UserIO.UserInput().toLowerCase();
			switch(answer) {
			case "q":
				quitGame();
			case "g":
				System.out.println("\nEr word een nieuwe code gegenereerd:");
				generateCode();
				printCode();
				break;
			default:
				checkCode(answer);
			}
		}
	}
	private void checkCode(String answer) { 
		//Controleert voor nu of de eerste character van het gegeven antwoord overeenkomt met de eerste character van de code.
		if(answer.charAt(0) == code[0]) {
			System.out.println("Goed gedaan.");
		} else {
			System.out.println("Helaas.");
		}
	}
	
	private void printCode() {
		//Print de code uit.
		System.out.println("De code is: " + Arrays.toString(code));
	}
	
	private String getCode() {
		//Haalt de code op en geeft deze terug als String
		String codeString = Arrays.toString(code);
		return codeString;
	}
	
	private void generateCode() {
		ArrayList<Character> options = new ArrayList<Character>();
		for (char x : colours) options.add(x);
		ArrayList<Character> newCode = new ArrayList<Character>();
		while (newCode.size() < 4) {
			Collections.shuffle(options);
			Collections.shuffle(options);
			newCode.add(options.get(0));
		}
		for (int x = 0; x < code.length; x++) {
			code[x] = newCode.get(x);
		}
	}
	
	private static void quitGame() { //Quits the game.
		System.out.println("\nWas leuk om met je/jullie te spelen!");
		System.exit(0);
	}
}

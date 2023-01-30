package mastermind.game;

import mastermind.ColorPicker;
import mastermind.io.UserIO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Game implements ColorPicker {
	final char[] colours = {'a','b','c','d','e','f'};
	private boolean gameLoop = true;
	private char[] code = {'d','a','e','c'};
	private int attempts = 0;
	
	public void start() {
		generateCode();
		printCode(); //print de gegenereerde code uit
		System.out.println("De code kan bestaan uit de volgende letters:");
		System.out.println(Arrays.toString(colours));
		while (gameLoop) {
			System.out.println(ANSI_PURPLE + "Voer je antwoord in: (Q om te stoppen / G om een nieuw code te genereren.)");
			String answer = UserIO.UserInput().toLowerCase();
			switch(answer) {
			case "q":
				quitGame();
			case "g":
				System.out.println("\nEr word een nieuwe code gegenereerd:");
				generateCode();
				break;
			default:
				checkCode(answer);
			}
		}
	}
	private void checkCode(String answer) { 
		System.out.println("\n'?' betekend wel aanwezig maar niet juiste plek.\n'+' betekend juiste plek.");
		char[] answerArray = answer.toCharArray(); //maakt een nieuw char array van het antwoord
		for (int X = 0; X <answerArray.length; X++) {
			if (answerArray[X] == code[X]) answerArray[X] = '+'; //vervangt de char met een + als deze gelijk is aan de char op dezelfde positie in de code
		}
		System.out.println(Arrays.toString(answerArray));
		answer = Arrays.toString(answerArray);
		if(answer.equals("[+, +, +, +]")) {
			attempts++;
			System.out.println(ANSI_CYAN + "\nGefeliciteerd!");
			System.out.println("Je hebt het geraden in " + attempts + (attempts <= 1 ? " poging." : " pogingen."));
			gameLoop = false;
		} else {
			System.out.println(ANSI_RED + "\nHelaas.");
			attempts++; //verhoogd de aantal pogingen counter.
			if (attempts == 12) {
				System.out.println("Je hebt het niet kunnen raden binnen 12 keer.");
				quitGame();
			} else {
				System.out.println("Je hebt nog " + (12 - attempts) + " pogingen over.");
			}
		}
	}
	
	private void printCode() {
		//Print de code uit.
		System.out.println(ANSI_BLUE + "De code is: " + Arrays.toString(code));
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
		System.out.println("\nHet was leuk om met je/jullie te spelen!");
		System.exit(0);
	}
}

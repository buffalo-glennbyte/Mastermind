package mastermind.game;

import mastermind.ColorPicker;
import mastermind.io.UserIO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Game implements ColorPicker {
	final char[] colours = {'a','b','c','d','e','f'};
	private boolean gameLoop = true;
	private char[] code = new char[4];
	ArrayList<Player> players = new ArrayList<Player>();
	private int everyoneGuessedRight = 0;
	
	public void start() {
		getAmountPlayers();
		generateCode();
		printCode(); //print de gegenereerde code uit
		System.out.println(ANSI_YELLOW + "\nDe code is gegenereerd van de volgende letters:");
		System.out.println(Arrays.toString(colours) + ANSI_RESET);
		while (gameLoop) {
			if (everyoneGuessedRight == players.size()) {
				gameLoop = false;
				continue;
			}
			for (Player p : players) {
				if(p.hasGuessedCode()) continue;
				System.out.println("Het is de beurt van " + p.getName());
				System.out.println("Voer je antwoord in: " + ANSI_YELLOW + "(Q om te stoppen / G om een nieuw code te genereren.)" + ANSI_RESET);
				String answer = UserIO.codeInput();
				switch(answer) {
				case "q":
					quitGame();
				case "g":
					System.out.println(ANSI_YELLOW + "\nEr word een nieuwe code gegenereerd." + ANSI_RESET);
					generateCode();
					printCode();
					break;
				default:
					checkCode(p, answer);
				}
			}
		}
	}
	private void getAmountPlayers() {
		Scanner input = new Scanner(System.in);
		System.out.println("\nHoeveel spelers gaan er mee doen?");
		int amount = input.nextInt();
		for (int a = 0; a < amount; a++) {
			System.out.println("\nSpeler " + (a + 1));
			players.add(new Player());
		}
		System.out.println("\nDit zijn alle spelers:");
		for (Player p : players) System.out.println(p.getName());
		UserIO.enterPrompt();
	}
	
	private void checkCode(Player player, String answer) { 
		char[] answerArray = answer.toCharArray(); //maakt een nieuw char array van het antwoord
		System.out.println("\nJe hebt het volgende ingevoerd:\n" + (Arrays.toString(answerArray)));
		char[] tempCode = code.clone(); //maakt clone van code array.
		for (int X = 0; X <answerArray.length; X++) {
			if (answerArray[X] == code[X]) answerArray[X] = '+'; //vervangt de char met een + als deze gelijk is aan de char op dezelfde positie in de code
		}
		for (int Z = 0; Z < answerArray.length; Z++) { //Zet de + in de tempCode array
			if (answerArray[Z] == '+') tempCode[Z] = answerArray[Z];
		}
		answer = Arrays.toString(tempCode);
		for (int Y = 0; Y < answerArray.length; Y++) {
			if (answerArray[Y] == '+') continue;
			if (answer.contains(Character.toString(answerArray[Y]))) {
				answerArray[Y] = '?';
			}
		}
		answer = Arrays.toString(answerArray);
		isCodeCorrect(player, answer);
	}
	
	private void isCodeCorrect(Player player, String answer) {
		if(answer.equals("[+, +, +, +]")) {
			player.incrementAttempts(); //Verhoogd aantal pogingen van speler
			System.out.println(ANSI_GREEN + "\nGefeliciteerd " + player.getName() + "!");
			System.out.println("Je hebt het geraden in " + player.getAttempts() + (player.getAttempts() <= 1 ? " poging." : " pogingen." + ANSI_RESET));
			UserIO.enterPrompt();
			player.didGuessCode();
			everyoneGuessedRight++;
		} else {
			System.out.println(answer);
			System.out.println(ANSI_CYAN + "\n'?', aanwezig maar niet op de juiste plek.\n'+', op de juiste plek." + ANSI_RESET);
			System.out.println(ANSI_RED + "\nHelaas " + player.getName() + ".");
			player.incrementAttempts(); //Verhoogd de aantal pogingen van speler
			if (player.getAttempts() == 12) {
				System.out.println("Je hebt het niet kunnen raden binnen 12 keer." + ANSI_RESET);
				UserIO.enterPrompt();
				quitGame();
			} else {
				System.out.println("Je hebt nog " + (12 - player.getAttempts()) + " pogingen over.\n" + ANSI_RESET);
			}
		}
	}
	
	private void printCode() {
		//Print de code uit.
		System.out.println(ANSI_BLACK + "De code is: " + Arrays.toString(code) + ANSI_RESET);
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
		System.out.println(ANSI_GREEN + "\nWas leuk om met je/jullie te spelen!");
		System.exit(0);
	}
}

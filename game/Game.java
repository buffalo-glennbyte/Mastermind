package mastermind.game;

import mastermind.ColorPicker;
import mastermind.Menu;
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
	private int playersUsedAllAttempts = 0;
	
	public void start() {
		getAmountPlayers();
		for (Player p : players) p.setCode(generateCode()); //geeft elke speler zijn eigen unieke code
		Menu.printTextToColor(ANSI_YELLOW, "\nDe code is gegenereerd van de volgende letters:");
		Menu.printTextToColor(ANSI_YELLOW, Arrays.toString(colours));
		while (gameLoop) {
			if (playersUsedAllAttempts == players.size()) {
				gameLoop = false;
				continue;
			}
			for (Player p : players) {
				if(p.hasGuessedCode() || p.getAttempts() == 12) continue; //controleert of speler zijn code al heeft geraden of alle pogingen al heeft gehad.
				Menu.printTextToColor(ANSI_WHITE, "Het is de beurt van: " + p.getName() );
				if(p.getInputtedCodes().size() != 0) {
					Menu.printTextToColor(ANSI_BLUE, "Dit waren je vorige antwoorden:");
					for (int x = 0; x < p.getInputtedCodes().size(); x += 2) {
						System.out.print(Arrays.toString(p.getInputtedCodes().get(x)));
						System.out.print(" - " + Arrays.toString(p.getInputtedCodes().get(x + 1)));
						System.out.println();
					}
					System.out.println();
				}
				System.out.print("Voer je antwoord in: ");
				Menu.printTextToColor(ANSI_YELLOW, "(Q om te stoppen / G om een nieuw code te genereren.)");
				
				String answer = UserIO.codeInput();
				switch(answer) {
				case "q":
					quitGame();
				case "g":
					Menu.printTextToColor(ANSI_YELLOW, "\nEr word een nieuwe code gegenereerd voor je.");					
					p.setCode(generateCode());
					break;
				case "p":
					printCode(p);
					break;
				default:
					p.addInputtedCode(answer.toCharArray());
					checkCode(p, answer);
				}
			}
		}
		if (players.size() > 1) printWinner(); //Print de winnaar uit als er meer dan 1 speler is.
	}
	
	private void printWinner() {
		Collections.sort(players, new SortAttempts());
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getAttempts() < 12) {
				if (i == 0) {
					Menu.printTextToColor(ANSI_GREEN, players.get(i).getName() + " heeft gewonnen met " + players.get(i).getAttempts() + " pogingen.");
				} else {
					Menu.printTextToColor(ANSI_CYAN, players.get(i).getName() + " heeft het geraden met " + players.get(i).getAttempts() + " pogingen.");
				}
			} else {
				Menu.printTextToColor(ANSI_RED, players.get(i).getName() + " heeft het helaas niet kunnen raden binnen 12 pogingen.");
			}
		}
		UserIO.enterPrompt();
	}
	
	private void getAmountPlayers() {
		Scanner input = new Scanner(System.in);
		System.out.println("\nHoeveel spelers gaan er mee doen?");
		int amount = input.nextInt();
		for (int a = 0; a < amount; a++) {
			Menu.printTextToColor(ANSI_BLUE, "\nSpeler " + (a + 1));
			players.add(new Player());
		}
		if (players.size() > 1) {
			Menu.printTextToColor(ANSI_CYAN, "\nDit zijn alle spelers:");
			for (Player p : players) System.out.println(p.getName());
		}
		UserIO.enterPrompt();
	}
	
	private void checkCode(Player player, String answer) { 
		char[] answerArray = answer.toCharArray(); //maakt een nieuw char array van het antwoord
		System.out.println("\nJe hebt het volgende ingevoerd:\n" + (Arrays.toString(answerArray)));
		char[] tempCode = player.getCode().clone(); //maakt clone van correcte code array van de ingevoerde speler
		for (int X = 0; X <answerArray.length; X++) {
			if (answerArray[X] == player.getCode()[X]) answerArray[X] = '+'; //vervangt de char met een + als deze gelijk is aan de char op dezelfde positie in de code
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
		String checkedCode = Arrays.toString(answerArray);
		player.addInputtedCode(answerArray);
		isCodeCorrect(player, checkedCode);
	}
	
	private void isCodeCorrect(Player player, String checkedCode) {
		if(checkedCode.equals("[+, +, +, +]")) {
			player.incrementAttempts(); //Verhoogd aantal pogingen van speler
			Menu.printTextToColor(ANSI_GREEN, "\nGefeliciteerd " + player.getName() + "!");   
			Menu.printTextToColor(ANSI_GREEN, "Je hebt het geraden in " + player.getAttempts() + (player.getAttempts() <= 1 ? " poging." : " pogingen."));   
			UserIO.enterPrompt();
			player.didGuessCode();
			playersUsedAllAttempts++;
		} else {
			System.out.println(checkedCode);
			Menu.printTextToColor(ANSI_CYAN, "\n'?', aanwezig maar niet op de juiste plek.\n'+', op de juiste plek.");
			Menu.printTextToColor(ANSI_RED, "\nHelaas " + player.getName() + ".");
			player.incrementAttempts(); //Verhoogd de aantal pogingen van speler
			if (player.getAttempts() == 12) {
				Menu.printTextToColor(ANSI_RED, "Je hebt het niet kunnen raden binnen 12 keer.");
				UserIO.enterPrompt();
				playersUsedAllAttempts++;
			} else {
				Menu.printTextToColor(ANSI_RED, "Je hebt nog " + (12 - player.getAttempts()) + (player.getAttempts() == 11 ? " poging over." : " pogingen over."));
				UserIO.enterPrompt();
			}
		}
	}
	
	private void printCode(Player p) {
		//Print de code uit.
		Menu.printTextToColor(ANSI_PURPLE, p.getName() + ", je code is: " + Arrays.toString(p.getCode()));
		System.out.println();
	}
	
	private char[] generateCode() {
		ArrayList<Character> options = new ArrayList<Character>();
		for (char x : colours) options.add(x);
		ArrayList<Character> newCode = new ArrayList<Character>();
		while (newCode.size() < 4) {
			Collections.shuffle(options);
			Collections.shuffle(options);
			newCode.add(options.get(0));
		}
		char[] returnedCode = new char[4];
		for (int x = 0; x < code.length; x++) {
			returnedCode[x] = newCode.get(x);
		}
		return returnedCode;
	}
	
	private static void quitGame() { //Quits the game.
		Menu.printTextToColor(ANSI_GREEN, "\nWas leuk om met je/jullie te spelen!");
		System.exit(0);
	}
	
	private void saveGame() {
		if(players.size() == 1) {
			
		}
	}
}

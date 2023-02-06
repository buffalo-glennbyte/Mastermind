package mastermind;

import java.io.IOException;

import mastermind.game.Game;
import mastermind.io.*;

public class Menu implements ColorPicker {

	private static boolean menuLoop = true;
	final static String[] options = {"1. Start een nieuw spel","2. Laat uitslag van vorige spel zien","3. Geef uitleg over spel","4. Stop programma"};
	
	public static void main(String[] args) {
		printTextToColor(ANSI_GREEN, "Welkom bij Mastermind!\n");
		printMenu();
		do {
			int answer = UserIO.menuInput();
			switch(answer) {
			case 1:
				Game g1 = new Game();
				g1.start();
				printMenu(1);
				break;
			case 2:
				printLastGame();
				UserIO.enterPrompt();
				printMenu(1);
				break;
			case 3:
				printExplanation();
				UserIO.enterPrompt();
				printMenu(1);
				break;
			case 4:
				printTextToColor(ANSI_GREEN, "\nFijne dag nog!");
				menuLoop = false;
				break;
				
			default:
				printTextToColor(ANSI_RED, "Ongeldige keuze, probeer opnieuw.");
			}
		} while (menuLoop);

	}
	
	static void printMenu() {
		for (String x : options) printTextToColor(ANSI_RESET, x);
		System.out.println();
	}
	
	static void printMenu(int unused) {
		printTextToColor(ANSI_YELLOW, "\nWelkom terug bij het menu:");
		printMenu();
	}
	
	static void printExplanation() {
		printTextToColor(ANSI_YELLOW, "\nUitleg:");
		try {
			FileIO.pExplanation();
		} catch (IOException e) {
			Menu.printTextToColor(ANSI_RED, "Kan het bestand niet vinden.");
		}
	}
	
	static void printLastGame() {
		try {
			FileIO.pLastGame();
		} catch (IOException e) {
			Menu.printTextToColor(ANSI_RED, "Kan het bestand niet vinden of er is nog geen spel gespeeld.");
		}
	}
	
	public static void printTextToColor(String color, String text) {
		System.out.println(color + text + ANSI_RESET);
		// print alle text in kLeUrTjEssss
		// reset? niet meer nodig!
		// Groeten van Timo ;)
	}
}

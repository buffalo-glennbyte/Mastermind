package mastermind;

import mastermind.game.Game;
import mastermind.io.UserIO;

public class Menu implements ColorPicker {

	private static boolean menuLoop = true;
	final static String[] options = {"1. Start een nieuw spel","2. Laat uitslag van vorige spel zien","3. Geef uitleg over spel","4. Stop programma"};
	
	public static void main(String[] args) {
		System.out.println(ANSI_GREEN + "Welkom bij Mastermind!\n" + ANSI_RESET);
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
				printMenu(1);
				break;
			case 3:
				printExplanation();
				printMenu(1);
				break;
			case 4:
				System.out.println(ANSI_GREEN + "\nFijne dag nog!");
				menuLoop = false;
				break;
				
			default:
				System.out.println(ANSI_RED + "Ongeldige keuze, probeer opnieuw." + ANSI_RESET);
			}
		} while (menuLoop);

	}
	
	static void printMenu() {
		for (String x : options) System.out.println(ANSI_RESET + x);
		System.out.println();
	}
	
	static void printMenu(int unused) {
		System.out.println(ANSI_GREEN + "\nWelkom terug bij het menu:" + ANSI_RESET);
		printMenu();
	}
	
	static void printExplanation() {
		System.out.println("Placeholder.");
	}
	
	static void printLastGame() {
		System.out.println("Placeholder.");
	}
}

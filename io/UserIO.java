package mastermind.io;

import mastermind.ColorPicker;
import mastermind.Menu;

import java.util.Scanner;

public class UserIO implements ColorPicker {

	public static String codeInput() {
		boolean loop = true;
		Scanner input = new Scanner(System.in);
		String givenAnswer = null;
		do {
			try {
				String answer = input.next().toLowerCase();
				givenAnswer = answer;
				if (answer.startsWith("q") | answer.startsWith("g") | answer.startsWith("p")) { //controleert of het antwoord begint met Q of G.
					loop = false;
					break;
				}
				if (answer.length() != 4) throw new Exception(); //gooit exceptions als antwoord niet 4 letters lang is.
				loop = false;
			} catch (Exception e) {
				Menu.printTextToColor(ANSI_RED, "Daar ging wat mis, heb je wel een 4-letterige code ingevoerd?");
			}
		} while (loop);
		return givenAnswer;
	}
	
	public static int menuInput() {
		Scanner input = new Scanner(System.in);
		int answer = 0;
		try {
			int tempAnswer = input.nextInt();
			answer = tempAnswer;
		} catch (Exception e) {
			Menu.printTextToColor(ANSI_RED, "Daar ging wat mis, heb je wel een cijfer ingevuld?");
		}
		return answer;
	}
	
	public static void enterPrompt() { //Maakt een nieuwe scanner aan en vraagt om input waar niets mee gebeurd.
		Scanner enter = new Scanner(System.in);
		Menu.printTextToColor(ANSI_PURPLE, "\nDruk op enter om door te gaan.");
		enter.nextLine();
	}
}

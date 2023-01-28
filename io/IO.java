package mastermind.io;

import java.util.Scanner;

public class IO {
	
	public String UserInput() {
		boolean loop = true;
		Scanner input = new Scanner(System.in);
		String givenAnswer = "";
		do {
			try {
				String answer = input.next();
				givenAnswer = answer;
				loop = false;
			} catch (Exception e) {
				System.out.println("Daar ging wat mis");
			}
		} while (loop);
		if (givenAnswer.toLowerCase().equals("q")) { //Calls the method to quit the game
			quitGame();
		}
		return givenAnswer;
	}
	
	String UserInput(String question) {
		return question;
		
	}
	
	protected void quitGame() { //Quits the game.
		System.out.println("\nWas leuk om met je/jullie te spelen!");
		System.exit(0);
	}
}

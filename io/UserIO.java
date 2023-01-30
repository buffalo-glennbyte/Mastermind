package mastermind.io;

import java.util.Scanner;

public class UserIO {
	
	public static String UserInput() {
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
		return givenAnswer;
	}
	
	String UserInput(String question) {
		return question;
		
	}
}

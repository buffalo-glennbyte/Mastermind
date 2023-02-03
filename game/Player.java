package mastermind.game;

import java.util.Scanner;

//import java.util.ArrayList;

public class Player {
	private String name;
	private int attempts;
	private boolean guessedCode = false;
//	private ArrayList<char[]> inputtedCodes = new ArrayList<char[]>();
	
	Player(){
		setName();
	}
	
	public void setName() {
		Scanner input = new Scanner(System.in);
		//Vraagt speler om naam en vraagt of het klopt
		 //Slaat deze vervolgens op in String naam. 
		 boolean naamCorrect = false;
		 while(!naamCorrect) {
			 boolean invoerCorrect = false;
			 System.out.println("Wat is je naam?");
			 this.name = input.nextLine();
			 while(!invoerCorrect) {
				 System.out.println("\nJe hebt " + this.name + " ingevoerd, klopt dit? (ja/nee)");
				 switch(input.next().toLowerCase()) {
				 case "j": case "ja":
					 naamCorrect = true;
					 invoerCorrect = true;
					 input.nextLine();
					 break;
				 case "n": case "nee":
					 System.out.println();
					 invoerCorrect = true;
					 input.nextLine();
					 break;
				 default:
					System.out.println("\nOngeldige invoer. Vul a.u.b. ja of nee in.");
				 }
			 }
		 }
	}
	public String getName() {
		return this.name;
	}
	
	void incrementAttempts() {
		this.attempts++;
	}
	
	int getAttempts() {
		return this.attempts;
	}
	
	void didGuessCode() {
		this.guessedCode = true;
	}
	
	boolean hasGuessedCode() {
		return this.guessedCode;
	}
	
//	void addInputtedCode(char[] inputtedCode) {
//		this.inputtedCodes.add(inputtedCode);
//	}
//	
//	ArrayList getInputtedCodes() {
//		return this.inputtedCodes;
//	}
}

package mastermind.game;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Player {
	private String name;
	private int attempts;
	private boolean guessedCode = false;
	private ArrayList<char[]> inputtedCodes = new ArrayList<char[]>();
	private char[] code = new char[4];
	
	Player(){
		setName();
	}
	
	public ArrayList<char[]> getInputtedCodes() {
		return inputtedCodes;
	}

	public void addInputtedCode(char[] IC) {
		inputtedCodes.add(IC);
	}
	
	public char[] getCode() {
		return code;
	}

	protected void setCode(char[] code) {
		this.code = code;
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
		return name;
	}
	
	protected void incrementAttempts() {
		attempts++;
	}
	
	public int getAttempts() {
		return attempts;
	}
	
	protected void didGuessCode() { //
		guessedCode = true;
	}
	
	public boolean hasGuessedCode() {
		return guessedCode;
	}
}

class SortAttempts implements Comparator<Player> { //Voor het sorteren van spelers op aantal pogingen 
	public int compare(Player a, Player b)  {  
		return a.getAttempts() - b.getAttempts();
	    }  
	}  
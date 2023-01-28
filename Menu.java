package mastermind;

import mastermind.game.Game;

public class Menu {

	public static void main(String[] args) {
		System.out.println("Hier wordt het menu geprint waar je keuze's in kan maken.");
		Game g1 = new Game();
		g1.start();
	}
}

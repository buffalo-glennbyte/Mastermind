package mastermind.io;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Arrays;

import mastermind.ColorPicker;
import mastermind.Menu;
import mastermind.game.Player;

/*
 * saveGame method herschrijven zodat laatste game bovenaan staat:
 * - Data die in scores.txt staat wegschrijven naar tijdelijk bestand.
 * - scores.txt leegmaken.
 * - Huidige game data schrijven naar scores.txt.
 * - Data uit tijdelijk bestand toevoegen aan scores.txt  
 */


public class FileIO implements ColorPicker {
	
	final static Path explanationPath = Paths.get("src/mastermind/spelUitleg.txt");
	final static Path scoresPath = Paths.get("src/mastermind/scores.txt");
	final static Path tempScores = Paths.get("src/mastermind/tempScores.txt");
	final static DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT, FormatStyle.SHORT);
	
	public static void pExplanation() throws IOException {
		Files.lines(explanationPath).forEach(line -> {System.out.println(line);});
	}
	
	public static void pLastGame() throws IOException {
		System.out.println();
		Files.lines(scoresPath).forEach(line -> {System.out.println(line);});
	}
	
	public static void saveGame(ArrayList<Player> players) throws IOException {
		if(!(scoresPath.toFile().exists())) 
			{scoresPath.toFile().createNewFile();}
		try {
			LocalDateTime writeTime = LocalDateTime.now();
			StringBuilder timestamp = new StringBuilder();
			timestamp.append("Dit potje heeft plaatsgenomen op: ");
			timestamp.append(writeTime.format(formatter) + "\n");
			Files.writeString(scoresPath, timestamp, StandardOpenOption.APPEND);
			for(Player p : players) {
				StringBuilder temp = new StringBuilder();
				temp.append(p.getName() + " had als code " + Arrays.toString(p.getCode()) + " en heeft de code ");
				if (p.hasGuessedCode()) {
					temp.append("geraden in " + p.getAttempts() + (p.getAttempts() == 1 ? " poging." : " pogingen."));
				} else {
					temp.append("niet geraden.");
				}
				temp.append("\n");
				Files.writeString(scoresPath, temp, StandardOpenOption.APPEND);
			}
			Files.writeString(scoresPath, "\n", StandardOpenOption.APPEND);
		} catch (Exception e) {
			e.printStackTrace();
			Menu.printTextToColor(ANSI_RED, "Er ging iets mis tijdens het wegschrijven van de game stats.");
		}
	}
	
	public static void deleteScores() {
		if(scoresPath.toFile().exists()) {
			scoresPath.toFile().delete();
			Menu.printTextToColor(ANSI_YELLOW, "Scorebestand is verwijderd.");
		}
		else {
			Menu.printTextToColor(ANSI_RED, "Er is geen scorebestand.");
		}
	}
}

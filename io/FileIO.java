package mastermind.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;

import mastermind.ColorPicker;
import mastermind.Menu;

/*
 * Check for separate scores folder, otherwise create one.
 * Create new file per session, based on time stamp?
 * - Write to file method
 * - Read from file method
 * - Print session files list?
 * 
 */


public class FileIO implements ColorPicker {
	
	final static Path explanationPath = Paths.get("src/mastermind/spelUitleg.txt");
	final static Path scoresPath = Paths.get("src/masterming/scores.txt");
	final static Path tempScores = Paths.get("src/mastermind/tempScores.txt");
	
	public static void pExplanation() throws IOException {
		Files.lines(explanationPath).forEach(line -> {System.out.println(line);});
	}
	
	public static void pLastGame() throws IOException {
		System.out.println();
		Files.lines(scoresPath).forEach(line -> {System.out.println(line);});
	}
	
	public static void saveGame() throws IOException {
		if(!scoresPath.toFile().exists()) 
			{scoresPath.toFile().createNewFile();}
		try {
			Path path = Files.copy(scoresPath, tempScores,StandardCopyOption.REPLACE_EXISTING);//copy with REPLACE_EXISTING option
		} catch (Exception e) {
			e.printStackTrace();
			Menu.printTextToColor(ANSI_RED, "Er ging iets mis met tijdelijk opslaan van de oude scores.");
		}
//		Files.write(scoresPath, String.format("%s%n", line).getBytes(), StandardOpenOption.APPEND)
	}
}

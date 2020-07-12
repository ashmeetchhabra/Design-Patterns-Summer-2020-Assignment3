package studentskills.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import studentskills.util.FileDisplayInterface;
import studentskills.util.StdoutDisplayInterface;
import studentskills.util.MyLogger.DebugLevel;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {

	BufferedWriter write;

	/**
	 * Constructor to open the file in append mode
	 * 
	 * @param filename The file in which data is to be written
	 * @throws IOException
	 */

	public Results(String filename) throws IOException {
		write = new BufferedWriter(new FileWriter(filename));

	}

	/**
	 * Writes to a file in append mode
	 * 
	 * @param str The string to be written in file
	 * @throws IOException
	 */

	@Override
	public void writeToFile(String str) throws IOException {
		MyLogger.writeMessage("Data is written to a file", DebugLevel.RESULTS);
		write.write(str);
	}

	/**
	 * Prints different number of arguments to the console
	 * 
	 * @param obj Varargs used to print on console
	 * @throws IOException 
	 */

	@Override
	public void printToConsole(Object... obj) throws IOException {
		MyLogger.writeMessage("Printing to console", DebugLevel.RESULTS);
		for (int i = 0; i < obj.length; i++) {
			System.out.print(obj[i]);
		}
	}

	/**
	 * Close the file
	 * 
	 * @throws IOException
	 */

	public void closeFile() throws IOException {
		MyLogger.writeMessage("Closing of the file", DebugLevel.RESULTS);
		write.close();

	}

}

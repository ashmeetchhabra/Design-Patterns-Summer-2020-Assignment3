package studentskills.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MyLogger {

	static BufferedWriter errorWriter;

	// FIXME: Add more enum values as needed for the assignment
	public static enum DebugLevel {
		CONSTRUCTOR, FILE_PROCESSOR, NONE, ALL, TREEHELPER, STUDENTRECORD, STUDENTRECORDS, DRIVER, LINEHANDLER, ERROR,
	};

	private static DebugLevel debugLevel;

	// FIXME: Add switch cases for all the levels
	public static void setDebugValue(int levelIn) {
		switch (levelIn) {
		case 10:
			debugLevel = DebugLevel.ERROR;
			break;
		case 9:
			debugLevel = DebugLevel.LINEHANDLER;
			break;
		case 8:
			debugLevel = DebugLevel.DRIVER;
			break;
		case 7:
			debugLevel = DebugLevel.STUDENTRECORDS;
			break;
		case 6:
			debugLevel = DebugLevel.STUDENTRECORD;
			break;
		case 5:
			debugLevel = DebugLevel.TREEHELPER;
			break;
		case 4:
			debugLevel = DebugLevel.ALL;
			break;
		case 3:
			debugLevel = DebugLevel.CONSTRUCTOR;
			break;
		case 2:
			debugLevel = DebugLevel.CONSTRUCTOR;
			break;
		case 1:
			debugLevel = DebugLevel.FILE_PROCESSOR;
			break;
		default:
			debugLevel = DebugLevel.NONE;
			break;
		}
	}

	public static void setDebugValue(DebugLevel levelIn) {
		debugLevel = levelIn;
	}

	public static void writeMessage(String message, DebugLevel levelIn) throws IOException {

		if (levelIn == DebugLevel.ERROR) {
			System.out.println(message);
			errorWriter.write(message);
		} else if (levelIn == debugLevel)
			System.out.println(message);
	}

	public static void errorFileHandler(String filename) throws IOException {
		errorWriter = new BufferedWriter(new FileWriter(filename));
	}

	public static void closeErrorFile() throws IOException {
		errorWriter.close();

	}

	public String toString() {
		return "The debug level has been set to the following " + debugLevel;
	}
}
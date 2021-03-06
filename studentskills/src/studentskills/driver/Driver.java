package studentskills.driver;

import java.io.IOException;
import java.util.HashMap;

import studentskills.mytree.TreeHelper;
import studentskills.util.FileProcessor;
import studentskills.util.LineHandler;
import studentskills.util.MyLogger;
import studentskills.util.MyLogger.DebugLevel;
import studentskills.util.Results;

/**
 * @author ashmeet
 * 
 * Code starts implementing from here
 *
 */
public class Driver {
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 7;
	private static final int NUMBER_OF_TREES = 3;

	public static void main(String[] args) {

		if ((args.length != 7) || (args[0].equals("${input}")) || (args[1].equals("${out1}"))
				|| (args[2].equals("${out2}")) || (args[3].equals("${out3}")) || (args[4].equals("${modify}"))
				|| (args[5].equals("${error}")) || (args[6].equals("${debug}"))) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.",
					REQUIRED_NUMBER_OF_CMDLINE_ARGS);
			System.exit(0);
		}
		try {
			MyLogger.setDebugValue(Integer.parseInt(args[6]));
			MyLogger.writeMessage("In Main", DebugLevel.DRIVER);
			MyLogger.errorFileHandler(args[5]);

			// Initializations
			FileProcessor fp;
			TreeHelper th = new TreeHelper(NUMBER_OF_TREES);

			LineHandler lh = new LineHandler();
			String line;

//			Processing input.txt file
			fp = new FileProcessor(args[0]);
			while ((line = fp.poll()) != null) {
				MyLogger.writeMessage("Reading of input file", DebugLevel.DRIVER);
				HashMap<String, Object> hm = (HashMap<String, Object>) lh.lineInputProcessor(line);
				th.insert(hm);
			}

//			Processing modify.txt file
			fp = new FileProcessor(args[4]);
			while ((line = fp.poll()) != null) {
				MyLogger.writeMessage("Reading of modify file", DebugLevel.DRIVER);
				HashMap<String, Object> hm = (HashMap<String, Object>) lh.lineModifyProcessor(line);
				th.modify(hm);
			}
			
			MyLogger.writeMessage("Creating instances of Results", DebugLevel.DRIVER);
			for (int i = 0; i < NUMBER_OF_TREES; i++) {
				Results res11 = new Results(args[i + 1]);
				th.printTree(res11, i);
				res11.closeFile();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Error e) {
			try {
				MyLogger.errorFileHandler(e.getMessage());
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		} finally {
			try {
				MyLogger.closeErrorFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}

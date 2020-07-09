package studentskills.driver;

import java.io.IOException;
import java.util.HashMap;
import mytree.TreeHelper;
import studentskills.util.FileProcessor;
import studentskills.util.LineHandler;
import studentskills.util.Results;

public class Driver {
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 2;

	public static void main(String[] args) {

		if ((args.length != 2) || (args[0].equals("${input}")) || (args[1].equals("${output}"))) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.",
					REQUIRED_NUMBER_OF_CMDLINE_ARGS);
			System.exit(0);
		}

		// Initializations
		FileProcessor fp;
		TreeHelper th = new TreeHelper();
		try {
			LineHandler lh = new LineHandler();
			String line;
			fp = new FileProcessor(args[0]);
			Results res = null;

			while ((line = fp.poll()) != null) {
				HashMap<String, Object> hm = (HashMap<String, Object>) lh.lineProcessor(line);
				th.insert(hm);
			}
			th.printTree();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

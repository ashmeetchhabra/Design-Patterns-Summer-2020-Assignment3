package studentskills.driver;

import java.io.IOException;
import java.util.HashMap;
import studentskills.mytree.TreeHelper;
import studentskills.util.FileProcessor;
import studentskills.util.LineHandler;
import studentskills.util.Results;

public class Driver {
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 3;

	public static void main(String[] args) {
		if ((args.length != 3) || (args[0].equals("${input}")) || (args[1].equals("${output}"))
				|| (args[2].equals("${modify}"))) {
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
			Results res = null;
			
//			Processing input.txt file
			fp = new FileProcessor(args[0]);
			while ((line = fp.poll()) != null) {
				HashMap<String, Object> hm = (HashMap<String, Object>) lh.lineInputProcessor(line);
				th.insert(hm);
			}
			
//			Processing modify.txt file
			fp = new FileProcessor(args[2]);
			while ((line = fp.poll()) != null) {
				HashMap<String, Object> hm = (HashMap<String, Object>) lh.lineModifyProcessor(line);
				th.modify(hm);
			}

			th.printTree();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

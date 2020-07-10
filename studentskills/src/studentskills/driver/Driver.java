package studentskills.driver;

import java.io.IOException;
import java.util.HashMap;
import studentskills.mytree.TreeHelper;
import studentskills.util.FileProcessor;
import studentskills.util.LineHandler;
import studentskills.util.Results;
import studentskills.util.StudentDetails;

public class Driver {
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 7;

	public static void main(String[] args) {
		if ((args.length != 7) || (args[0].equals("${input}")) || (args[1].equals("${out1}"))
				|| (args[2].equals("${out2}")) || (args[3].equals("${out3}")) || (args[4].equals("${modify}"))
				 || (args[5].equals("${error}"))  || (args[6].equals("${debug}"))) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.",
					REQUIRED_NUMBER_OF_CMDLINE_ARGS);
			System.exit(0);
		}

		// Initializations
		FileProcessor fp;
		TreeHelper th = new TreeHelper(3);
		
		try {
			Results res1 = new Results(args[1]);
			Results res2 = new Results(args[2]);
			Results res3 = new Results(args[3]);
			
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
			fp = new FileProcessor(args[4]);
			while ((line = fp.poll()) != null) {
				HashMap<String, Object> hm = (HashMap<String, Object>) lh.lineModifyProcessor(line);
				th.modify(hm);



			}
			
			for (int i = 0; i < 3; i++) {
				Results res11 = new Results(args[i+1]);
				th.printTree(res11, i);
				res11.closeFile();
				
				
			}
			
		
//			th.printTree(new Results(""),1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

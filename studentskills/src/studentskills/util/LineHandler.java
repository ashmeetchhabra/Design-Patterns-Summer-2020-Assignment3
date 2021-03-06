package studentskills.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import studentskills.util.MyLogger.DebugLevel;

public class LineHandler {
	/**
	 * Processes the line and return a Map
	 * 
	 * @param line: line of the Input file
	 * @return HashMap<String, Object> of student record 
	 * @throws IOException
	 */
	public HashMap<String, ?> lineInputProcessor(String line) throws IOException {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		List<String> skills = new ArrayList<String>();
		int noOfSkills = 0;

		MyLogger.writeMessage("InputLineProcessor: creating a hashMap", DebugLevel.LINEHANDLER);

		String s1[] = line.split(":");
		hm.put(StudentDetails.B_NUMBER.name(), s1[0]);
		String ss[] = s1[1].split(",");
		hm.put(StudentDetails.FIRST_NAME.name(), ss[0]);
		hm.put(StudentDetails.LAST_NAME.name(), ss[1]);
		hm.put(StudentDetails.GPA.name(), ss[2]);
		hm.put(StudentDetails.MAJOR.name(), ss[3]);

		for (int i = 4; i < ss.length; i++) {
			noOfSkills++;
			skills.add(ss[i]);
		}
		hm.put(StudentDetails.SKILL.name(), skills);
		if (noOfSkills > 10)
			throw new RuntimeException("Number of Skills in the input file is more than 10");
		return hm;
	}

	/**
	 * @param line: line of the Input file
	 * @return  HashMap<String, Object> of modify file
	 * @throws IOException
	 */
	public HashMap<String, ?> lineModifyProcessor(String line) throws IOException {
		HashMap<String, Object> hm = new HashMap<String, Object>();

		MyLogger.writeMessage("ModifyLineProcessor: creating a hashMap", DebugLevel.LINEHANDLER);
		String s1[] = line.split(",");
		String treeNumber = s1[0];
		String bNumber = s1[1];
		String s2[] = s1[2].split(":");

		if (s2.length != 2) {
			MyLogger.writeMessage("Missing new value", DebugLevel.ERROR);
			throw new RuntimeException("New value missing");
		}

		String originalValue = s2[0];
		String newValue = s2[1];

		hm.put(StudentDetails.REPLICA_ID.name(), Integer.parseInt(treeNumber));
		hm.put(StudentDetails.B_NUMBER.name(), Integer.parseInt(bNumber));
		hm.put(StudentDetails.ORIG_VALUE.name(), (String) originalValue);
		hm.put(StudentDetails.NEW_VALUE.name(), (String) newValue);
		return hm;

	}
}
package studentskills.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LineHandler {
	/**
	 * Processes the line and retun a Map
	 * 
	 * @param line: line of the Input file
	 * @return HashMap<String, ?> of videoname and the parameters(Metrics and
	 *         length)
	 */
	public HashMap<String, ?> lineInputProcessor(String line) {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		List<String> skills = new ArrayList<String>();

		System.out.println(line);
		String s1[] = line.split(":");
		hm.put(StudentDetails.B_NUMBER.name(), s1[0]);
		String ss[] = s1[1].split(",");
		hm.put(StudentDetails.FIRST_NAME.name(), ss[0]);
		hm.put(StudentDetails.LAST_NAME.name(), ss[1]);
		hm.put(StudentDetails.GPA.name(), ss[2]);
		hm.put(StudentDetails.MAJOR.name(), ss[3]);

		for (int i = 4; i < ss.length; i++) {
			skills.add(ss[i]);
		}
		hm.put(StudentDetails.SKILL.name(), skills);
		return hm;
	}
	
	public HashMap<String, ?> lineModifyProcessor(String line) {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		String s1[] = line.split(",");
		String treeNumber =s1[0];
		String bNumber = s1[1];
		String s2[]=s1[2].split(":");
		String originalValue = s2[0];
		String newValue = s2[0];
		
		hm.put(StudentDetails.REPLICA_ID.name(), Integer.parseInt(treeNumber));
		hm.put(StudentDetails.B_NUMBER.name(), Integer.parseInt(bNumber));
		hm.put(StudentDetails.ORIG_VALUE.name(), (String)originalValue);
		hm.put(StudentDetails.NEW_VALUE.name(), (String)newValue);
		return hm;
		
	}
}
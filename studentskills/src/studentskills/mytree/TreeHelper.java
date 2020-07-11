package studentskills.mytree;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import studentskills.util.MyLogger;
import studentskills.util.MyLogger.DebugLevel;
import studentskills.util.Results;
import studentskills.util.StudentDetails;
import sun.util.logging.resources.logging;

public class TreeHelper {

	Map<Integer, StudentRecords> hashMapStudentRecords = new HashMap<Integer, StudentRecords>();
//	MyLogger log = new MyLogger();
	
	
	
	public TreeHelper(int n) {
		MyLogger.setDebugValue(2);
		MyLogger.writeMessage("In Tree Helper Constructor", DebugLevel.CONSTRUCTOR);

		for (int i = 0; i < n; i++) {
			hashMapStudentRecords.put(i, new StudentRecords());
		}
	}

	public void insert(HashMap<String, Object> hm) {
		StudentRecord sr = createStudentRecord(hm);

		hashMapStudentRecords.get(0).insert(sr);
		try {
			StudentRecord clone1 = sr.myClone();
			StudentRecord clone2 = sr.myClone();

			sr.registerObserver(clone1);
			clone1.registerObserver(sr);

			sr.registerObserver(clone2);
			clone2.registerObserver(sr);

			clone1.registerObserver(clone2);
			clone2.registerObserver(clone1);

			hashMapStudentRecords.get(1).insert(clone1);
			hashMapStudentRecords.get(2).insert(clone2);

		} catch (Exception cnx) {
			throw new RuntimeException("Failed to insert, please try again", cnx);
		}
	}

	public void printTree(Results res, int i) throws IOException {

		System.out.println("Tree "+i);
		hashMapStudentRecords.get(i).inorder(res);
	}

	private StudentRecord createStudentRecord(HashMap<String, Object> hm) {

		StudentRecord sr = new StudentRecord(Integer.parseInt((String) hm.get(StudentDetails.B_NUMBER.name())));
		sr.setFirstName((String) hm.get(StudentDetails.FIRST_NAME.name()));
		sr.setLastName((String) hm.get(StudentDetails.LAST_NAME.name()));
		sr.setGpa(Double.parseDouble((String) hm.get(StudentDetails.GPA.name())) );
		sr.setMajor((String) hm.get(StudentDetails.MAJOR.name()));
		sr.setSkills((List<String>) hm.get(StudentDetails.SKILL.name()));
		return sr;

	}

	public void modify(Map<String, Object> hm) {
		StudentRecord sr = hashMapStudentRecords.get((int) hm.get(StudentDetails.REPLICA_ID.name()))
				.search((int) hm.get(StudentDetails.B_NUMBER.name()));
		
		sr.modify(hm,false);		

	}

	@Override
	public String toString() {
		return "TreeHelper [hashMapStudentRecords=" + hashMapStudentRecords + "]";
	}

}

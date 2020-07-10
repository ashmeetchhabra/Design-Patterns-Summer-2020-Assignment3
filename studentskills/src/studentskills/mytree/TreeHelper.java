package studentskills.mytree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import studentskills.util.StudentDetails;

public class TreeHelper {

	Map<Integer, StudentRecords> hashMapStudentRecords = new HashMap<Integer, StudentRecords>();

	public TreeHelper(int n) {

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

	public void printTree() {

		System.out.println("Tree 1");
		hashMapStudentRecords.get(0).inorder();

		System.out.println("Tree 2");
		hashMapStudentRecords.get(1).inorder();

		System.out.println("Tree 3");
		hashMapStudentRecords.get(2).inorder();
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
		
		sr.modify(hm);

	}

	@Override
	public String toString() {
		return "TreeHelper [hashMapStudentRecords=" + hashMapStudentRecords + "]";
	}

}

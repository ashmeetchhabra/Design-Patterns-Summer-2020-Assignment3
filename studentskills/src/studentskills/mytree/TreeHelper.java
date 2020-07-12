package studentskills.mytree;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import studentskills.util.MyLogger;
import studentskills.util.MyLogger.DebugLevel;
import studentskills.util.Results;
import studentskills.util.StudentDetails;

/**
 * @author ashmeet
 * 
 * Class which creates the replica of the trees and modify the record
 *
 */
public class TreeHelper {

	Map<Integer, StudentRecords> hashMapStudentRecords = new HashMap<Integer, StudentRecords>();

	public TreeHelper(int n) throws IOException {

		MyLogger.writeMessage("TreeHelperConstructor", DebugLevel.CONSTRUCTOR);
		MyLogger.writeMessage("In Tree Helper Constructor", DebugLevel.TREEHELPER);

		for (int i = 0; i < n; i++) {
			hashMapStudentRecords.put(i, new StudentRecords());
		}
	}

	/**
	 * @param hm, HashMap which gets inserted to tree as a student record
	 * @throws IOException
	 */
	public void insert(HashMap<String, Object> hm) throws IOException {

		MyLogger.writeMessage("Insert a record in TreeHelper", DebugLevel.TREEHELPER);

		StudentRecord sr = createStudentRecord(hm);

		hashMapStudentRecords.get(0).insert(sr);
		try {
			MyLogger.writeMessage("cloning the trees and creating the observer in TreeHelper", DebugLevel.TREEHELPER);
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

	/**
	 * @param res, Result object of the output file according to the tree
	 * @param index, to get the index of the tree in the map of trees
	 * @throws IOException
	 */
	public void printTree(Results res, int index) throws IOException {
		MyLogger.writeMessage("Printing the in TreeHelper", DebugLevel.TREEHELPER);

		System.out.println("Tree " + index);
		hashMapStudentRecords.get(index).inorder(res);
	}

	/**
	 * @param hm, create student record from hashmap
	 * @return student record
	 * @throws IOException
	 */
	private StudentRecord createStudentRecord(HashMap<String, Object> hm) throws IOException {

		MyLogger.writeMessage("Creating the studentRecord by hashMap in TreeHelper", DebugLevel.TREEHELPER);

		StudentRecord sr = new StudentRecord(Integer.parseInt((String) hm.get(StudentDetails.B_NUMBER.name())));
		sr.setFirstName((String) hm.get(StudentDetails.FIRST_NAME.name()));
		sr.setLastName((String) hm.get(StudentDetails.LAST_NAME.name()));
		sr.setGpa(Double.parseDouble((String) hm.get(StudentDetails.GPA.name())));
		sr.setMajor((String) hm.get(StudentDetails.MAJOR.name()));
		sr.setSkills((List<String>) hm.get(StudentDetails.SKILL.name()));
		return sr;

	}

	/**
	 * @param hm, modifies the student record 
	 * @throws IOException
	 */
	public void modify(Map<String, Object> hm) throws IOException {

		MyLogger.writeMessage("modifying the studentRecords in TreeHelper", DebugLevel.TREEHELPER);
		StudentRecord sr = hashMapStudentRecords.get((int) hm.get(StudentDetails.REPLICA_ID.name()))
				.search((int) hm.get(StudentDetails.B_NUMBER.name()));

		sr.modify(hm, false);

	}

	@Override
	public String toString() {
		return "TreeHelper [hashMapStudentRecords=" + hashMapStudentRecords + "]";
	}

}

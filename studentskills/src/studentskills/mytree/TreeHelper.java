package studentskills.mytree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import studentskills.util.StudentDetails;

public class TreeHelper {

	final StudentRecords t1;
	final StudentRecords t2;
	final StudentRecords t3;

	public TreeHelper() {
		t1 = new StudentRecords();
		t2 = new StudentRecords();
		t3 = new StudentRecords();
	}

	public void insert(HashMap<String, Object> hm) {
		StudentRecord sr = createStudentRecord(hm);
		t1.insert(sr);		
		try {
			StudentRecord clone1=sr.myClone();
			StudentRecord clone2=sr.myClone();
			
			sr.registerObserver(clone1);
			clone1.registerObserver(sr);
			
			sr.registerObserver(clone2);
			clone2.registerObserver(sr);
			
			clone1.registerObserver(clone2);
			clone2.registerObserver(clone1);
			
			t2.insert(clone1);
			t3.insert(clone2);
			
		} catch (Exception cnx) {
			throw new RuntimeException("Failed to insert, please try again", cnx);
		}
	}
	
	public void printTree() {

		System.out.println("Tree 1");
		t1.inorder();

		System.out.println("Tree 2");
		t2.inorder();
		
		System.out.println("Tree 3");
		t3.inorder();
	}
	
	private StudentRecord createStudentRecord(HashMap<String, Object> hm) {

		StudentRecord sr = new StudentRecord(Integer.parseInt((String) hm.get(StudentDetails.B_NUMBER.name())));
		sr.setFirstName((String) hm.get(StudentDetails.FIRST_NAME.name()));
		sr.setLastName((String) hm.get(StudentDetails.LAST_NAME.name()));
		Double.parseDouble((String) hm.get(StudentDetails.GPA.name()));
		sr.setMajor((String) hm.get(StudentDetails.MAJOR.name()));
		sr.setSkills( (List<String>) hm.get(StudentDetails.SKILL.name()));
		return sr;

	}
	
	public void modify(Map<String, Object> hm) {
		
		
		
	}
	
	

}

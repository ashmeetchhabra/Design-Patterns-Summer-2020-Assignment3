package studentskills.mytree;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import studentskills.util.MyLogger;
import studentskills.util.MyLogger.DebugLevel;
import studentskills.util.Results;
import studentskills.util.StudentDetails;

public class StudentRecords {

	protected StudentRecord root = null;

	/**
	 * inserts the sr, student record in the tree
	 * @param sr
	 * @throws IOException
	 */
	public void insert(StudentRecord sr) throws IOException {
		MyLogger.writeMessage("Insert node in tree", DebugLevel.STUDENTRECORDS);

		/* If the tree is empty, return a new node */
		if (root == null)
			root = sr;
		/* Otherwise, recur down the tree */
		insert(root, sr);
	}

	private void insert(StudentRecord root, StudentRecord sr) throws IOException {

		/* Otherwise, recur down the tree */
		if (root.bNumber > sr.bNumber) {
			if (root.leftChild == null)
				root.leftChild = sr;
			else
				insert(root.leftChild, sr);
		} else if (root.bNumber < sr.bNumber) {
			if (root.rightChild == null)
				root.rightChild = sr;
			else
				insert(root.rightChild, sr);
		} else if (root.bNumber == sr.bNumber) {
			Map<String, Object> hm = new HashMap<String, Object>();
			hm.put(StudentDetails.B_NUMBER.name(), sr.getbNumber());
			hm.put(StudentDetails.FIRST_NAME.name(), sr.getFirstName());
			hm.put(StudentDetails.LAST_NAME.name(), sr.getLastName());
			hm.put(StudentDetails.GPA.name(), sr.getGpa());
			hm.put(StudentDetails.MAJOR.name(), sr.getMajor());
			hm.put(StudentDetails.SKILL.name(), sr.getSkills());

			root.modify(hm, true);

		}

	}

	/**
	 * Serach a student record (Node) from StudentRecords (Tree)
	 * @param bNum
	 * @return
	 * @throws IOException
	 */
	public StudentRecord search(int bNum) throws IOException {
		return search(root, bNum);
	}

	private StudentRecord search(StudentRecord root, int bNum) throws IOException {
		MyLogger.writeMessage("Serach node in tree", DebugLevel.STUDENTRECORDS);
		// Base Cases: root is null or key is present at root
		if (root == null || root.bNumber == bNum)
			return root;

		// val is greater than root's key
		if (root.bNumber > bNum)
			return search(root.leftChild, bNum);

		// val is less than root's key
		return search(root.rightChild, bNum);

	}

	/**
	 * Print the Tree of StudentRecords
	 * @param res
	 * @throws IOException
	 */
	public void inorder(Results res) throws IOException {
		MyLogger.writeMessage("Print nodes in tree", DebugLevel.STUDENTRECORDS);
		inorderRec(root, res);
	}

	// A utility function to do inorder traversal of BST
	private void inorderRec(StudentRecord root, Results res) throws IOException {
		if (root != null) {
			inorderRec(root.leftChild, res);
			
			res.printToConsole(root.bNumber + " " + root.firstName + " " + root.lastName + " " + root.gpa + " "
					+ root.major + " ");
			
			res.writeToFile(String.valueOf(root.bNumber + " "));
			for (String sk : root.skills) {
				res.printToConsole(sk + " ");

				res.writeToFile(sk + " ");
			}
			inorderRec(root.rightChild, res);
		}
		res.printToConsole("\n");

		res.writeToFile("\n");
	}

}

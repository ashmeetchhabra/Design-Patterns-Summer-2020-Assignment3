package studentskills.mytree;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import studentskills.util.Results;
import studentskills.util.StudentDetails;

public class StudentRecords {
	protected StudentRecord root = null;

	public void insert(StudentRecord sr) {
		/* If the tree is empty, return a new node */
		if (root == null)
			root = sr;
		/* Otherwise, recur down the tree */
		insert(root, sr);
	}

	private void insert(StudentRecord root, StudentRecord sr) {

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

	public StudentRecord search(int bNum) {
		return search(root, bNum);
	}

	private StudentRecord search(StudentRecord root, int bNum) {
		// Base Cases: root is null or key is present at root
		if (root == null || root.bNumber == bNum)
			return root;

		// val is greater than root's key
		if (root.bNumber > bNum)
			return search(root.leftChild, bNum);

		// val is less than root's key
		return search(root.rightChild, bNum);

	}

	public void inorder(Results res) throws IOException {
		inorderRec(root, res);
	}

	// A utility function to do inorder traversal of BST
	private void inorderRec(StudentRecord root, Results res) throws IOException {
		if (root != null) {
			inorderRec(root.leftChild, res);
			System.out.print(root.bNumber + " " + root.firstName + " " + root.lastName + " " + root.gpa + " "
					+ root.major + " ");
			res.writeToFile(root.bNumber + " " + root.firstName + " " + root.lastName + " " + root.gpa + " "
					+ root.major + " ");
			for (String sk : root.skills) {
				System.out.print(sk + " ");
				res.writeToFile(sk + " ");
			}
			inorderRec(root.rightChild, res);
		}
		System.out.println();
		res.writeToFile("\n");
	}

}

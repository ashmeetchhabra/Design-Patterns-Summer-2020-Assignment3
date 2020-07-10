package studentskills.mytree;

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
		}
		else if (root.bNumber == sr.bNumber) {
//			TODO: Append skills
			
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

	public void inorder() {
		inorderRec(root);
	}

	// A utility function to do inorder traversal of BST
	private void inorderRec(StudentRecord root) {
		if (root != null) {
			inorderRec(root.leftChild);
			System.out.println();
			System.out.print(root.bNumber+" ");
			System.out.print(root.firstName+" "+root.lastName+" "+root.gpa+" "+root.major+" ");
			for (String sk : root.skills) {
				System.out.print(sk+" ");
			}
			inorderRec(root.rightChild);
		}
	}

}

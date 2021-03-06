package studentskills.mytree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import studentskills.util.MyLogger;
import studentskills.util.MyLogger.DebugLevel;
import studentskills.util.StudentDetails;

public class StudentRecord implements Cloneable, SubjectI, ObserverI {
	final int bNumber;

	StudentRecord leftChild, rightChild;
	String firstName, lastName, major;
	double gpa;

	List<String> skills = new ArrayList<String>();
	Map<String, String> updateSkill = new HashMap<String, String>();

	protected List<ObserverI> observers = new ArrayList<>();

	public void setObservers(List<ObserverI> observers) {
		this.observers = observers;
	}

	public StudentRecord(Integer bNumber) {
		this.bNumber = bNumber;
		leftChild = rightChild = null;
	}

	public Integer getbNumber() {
		return bNumber;
	}

	public StudentRecord getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(StudentRecord leftChild) {
		this.leftChild = leftChild;
	}

	public StudentRecord getRightChild() {
		return rightChild;
	}

	public void setRightChild(StudentRecord rightChild) {
		this.rightChild = rightChild;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	/**
	 * Make clones of the tree
	 * @return cloned record
	 * @throws CloneNotSupportedException
	 * @throws IOException 
	 */
	public StudentRecord myClone() throws CloneNotSupportedException, IOException {
		MyLogger.writeMessage("Cloning the student record", DebugLevel.STUDENTRECORD);
		StudentRecord cloned = (StudentRecord) super.clone();
		cloned.setObservers(new ArrayList<>());
		return cloned;
	}

	/**
	 * Register the Observer of the student record
	 * @throws IOException 
	 *
	 */
	@Override
	public void registerObserver(ObserverI observer) throws IOException {
		MyLogger.writeMessage("Register the observer", DebugLevel.STUDENTRECORD);
		observers.add(observer);

	}

	/**
	 *Remove the observer from the list of observers
	 */
	@Override
	public void removeObserver(ObserverI observer) {
		int i = observers.indexOf(observer);
		if (i >= 0)
			observers.remove(i);
	}

	/**
	 *Notifies all the observers
	 */
	@Override
	public void notifyObservers() throws IOException {
		MyLogger.writeMessage("NotifyObservers", DebugLevel.STUDENTRECORD);
		for (int i = 0; i < observers.size(); i++) {
			ObserverI observer = (ObserverI) observers.get(i);
			observer.update(firstName, lastName, major, skills);
		}
	}

	/**
	 * Updates the records of the observers
	 *
	 */
	@Override
	public void update(String firstName, String lastName, String major, List<String> skills) throws IOException {
		MyLogger.writeMessage("Update Method: Updates all the observers in StudentRecord", DebugLevel.STUDENTRECORD);
		this.firstName = firstName;
		this.lastName = lastName;
		this.major = major;
		this.skills = skills;
	}

	/**
	 * @param hm, modifies the current student record and notify other observers 
	 * @param isInsertModify, to check if it is modify from insert file
	 * @throws IOException
	 */
	public void modify(Map<String, Object> hm, boolean isInsertModify) throws IOException {

		MyLogger.writeMessage("Modify Method: Updates the current node in StudentRecord", DebugLevel.STUDENTRECORD);

		if (isInsertModify) {
			this.setFirstName((String) hm.get(StudentDetails.FIRST_NAME.name()));
			this.setLastName((String) hm.get(StudentDetails.LAST_NAME.name()));
			this.setGpa((Double) hm.get(StudentDetails.GPA.name()));
			this.setMajor((String) hm.get(StudentDetails.MAJOR.name()));

			List<String> existingSkills = this.getSkills();
			List<String> newSkills = new ArrayList<String>((List<String>) hm.get(StudentDetails.SKILL.name()));

			for (String newSkill : newSkills) {

				if (!existingSkills.contains(newSkill)) {
					existingSkills.add(newSkill);
				}

			}
			this.setSkills(existingSkills);

		} else {

			if (this.firstName.equals(hm.get(StudentDetails.ORIG_VALUE.name()))) {
				this.setFirstName((String) hm.get(StudentDetails.NEW_VALUE.name()));
			} else if (this.lastName.equals(hm.get(StudentDetails.ORIG_VALUE.name()))) {
				this.setLastName((String) hm.get(StudentDetails.NEW_VALUE.name()));
			} else if (this.major.equals(hm.get(StudentDetails.ORIG_VALUE.name()))) {
				this.setMajor((String) hm.get(StudentDetails.NEW_VALUE.name()));
			} else if (this.skills.contains(hm.get(StudentDetails.ORIG_VALUE.name()))) {
				this.skills.remove(hm.get(StudentDetails.ORIG_VALUE.name()));
				this.skills.add((String) hm.get(StudentDetails.NEW_VALUE.name()));
			}
			
		}
		this.notifyObservers();
	}

	@Override
	public String toString() {
		return "StudentRecord [bNumber=" + bNumber + ", leftChild=" + leftChild + ", rightChild=" + rightChild
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", major=" + major + ", gpa=" + gpa
				+ ", skills=" + skills + ", updateSkill=" + updateSkill + ", observers=" + observers + "]";
	}

}

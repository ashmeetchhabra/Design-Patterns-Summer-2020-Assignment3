package studentskills.mytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import studentskills.util.StudentDetails;

public class StudentRecord implements Cloneable, SubjectI, ObserverI {
	final int bNumber;

	StudentRecord leftChild, rightChild;
	String firstName, lastName, major;
	double gpa;

	List<String> skills = new ArrayList<String>();
	Map<String, String> updateSkill = new HashMap<String, String>();

	protected final List<ObserverI> observers = new ArrayList<>();

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

	public StudentRecord myClone() throws CloneNotSupportedException {
		return (StudentRecord) super.clone();
	}

	@Override
	public void registerObserver(ObserverI observer) {
		observers.add(observer);

	}

	@Override
	public void removeObserver(ObserverI observer) {
		int i = observers.indexOf(observer);
		if (i >= 0)
			observers.remove(i);
	}

	@Override
	public void notifyObservers() {
		for (int i = 0; i < observers.size(); i++) {
			ObserverI observer = (ObserverI) observers.get(i);
			observer.update(firstName, lastName, major, skills);
		}
	}

	@Override
	public void update(String firstName, String lastName, String major, List<String> skills) {
		this.firstName=firstName;
		this.lastName= lastName;
		this.major= major;
		this.skills=skills;
	}

	public void modify(Map<String, Object> hm) {
		if (this.firstName.equals(hm.get(StudentDetails.ORIG_VALUE.name()))) {
			this.setFirstName((String) hm.get(StudentDetails.NEW_VALUE.name()));
		} else if (this.lastName.equals(hm.get(StudentDetails.ORIG_VALUE.name()))) {
			this.setLastName((String) hm.get(StudentDetails.NEW_VALUE.name()));
		} else if (this.major.equals(hm.get(StudentDetails.ORIG_VALUE.name()))) {
			this.setMajor((String) hm.get(StudentDetails.NEW_VALUE.name()));
		} else {
			this.skills.remove(hm.get(StudentDetails.ORIG_VALUE.name()));
			this.skills.add((String) hm.get(StudentDetails.NEW_VALUE.name()));
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

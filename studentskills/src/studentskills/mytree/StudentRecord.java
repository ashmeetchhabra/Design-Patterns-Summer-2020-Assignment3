package studentskills.mytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			observer.update(firstName, lastName, major, updateSkill);
		}
	}

	@Override
	public void update(String firstName, String lastName, String major, Map<String, String> skill) {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}

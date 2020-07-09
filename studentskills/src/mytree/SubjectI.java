package mytree;

public interface SubjectI {
	void registerObserver(ObserverI observer);
	void removeObserver(ObserverI observer);
	void notifyObservers();

}

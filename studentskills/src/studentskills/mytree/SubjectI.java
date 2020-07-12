package studentskills.mytree;

import java.io.IOException;

public interface SubjectI {
	void registerObserver(ObserverI observer) throws IOException;

	void removeObserver(ObserverI observer);

	void notifyObservers() throws IOException;

}

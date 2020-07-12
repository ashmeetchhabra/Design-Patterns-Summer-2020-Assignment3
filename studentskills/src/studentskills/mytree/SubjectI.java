package studentskills.mytree;

import java.io.IOException;

public interface SubjectI {
	void registerObserver(ObserverI observer);

	void removeObserver(ObserverI observer);

	void notifyObservers() throws IOException;

}

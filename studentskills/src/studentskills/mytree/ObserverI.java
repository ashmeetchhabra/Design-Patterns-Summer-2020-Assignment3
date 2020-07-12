package studentskills.mytree;

import java.io.IOException;
import java.util.List;

public interface ObserverI {

	void update(String firstName, String lastName, String major, List<String> skill) throws IOException;

}

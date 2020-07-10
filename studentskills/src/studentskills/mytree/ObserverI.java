package studentskills.mytree;

import java.util.List;
import java.util.Map;

public interface ObserverI {
	
	void update(String firstName,String lastName, String major, List<String> skill);

}

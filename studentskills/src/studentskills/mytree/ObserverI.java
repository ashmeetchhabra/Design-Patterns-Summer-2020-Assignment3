package studentskills.mytree;

import java.util.Map;

public interface ObserverI {
	
	void update(String firstName,String lastName, String major, Map<String, String> skill);

}

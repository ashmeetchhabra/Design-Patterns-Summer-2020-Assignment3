package studentskills.mytree;

import java.util.List;

public interface ObserverI {
	
	void update(String firstName,String lastName, String major, List<String> skill);

}

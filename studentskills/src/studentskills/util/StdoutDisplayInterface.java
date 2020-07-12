package studentskills.util;

import java.io.IOException;

public interface StdoutDisplayInterface {

	/**
	 * Prints different number of arguments to the console
	 * 
	 * @param obj Varargs used to print on console
	 * @throws IOException 
	 */
	void printToConsole(Object[] obj) throws IOException;

}

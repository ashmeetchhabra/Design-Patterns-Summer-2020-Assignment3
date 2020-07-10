package studentskills.util;

import java.io.IOException;

public interface FileDisplayInterface {

	/**
	 * Writes to a file in append mode
	 * 
	 * @param str The string to be written in file
	 * @throws IOException
	 */
	void writeToFile(String str) throws IOException;

}

package edu.ktlab.vnner.util;

import java.io.File;
import java.nio.charset.Charset;

public class CombineFiles {
	public static void combine(File folder, File outFile) throws Exception {
		if (folder.isDirectory()) {
			File[] files = folder.listFiles();
			for (File file : files) {
				String[] lines = FileHelper.readFileAsLines(file, Charset.forName("UTF-8"));
				for (String line : lines)
					if (!line.trim().equals(""))
						FileHelper.appendToFile(line + "\n", outFile, Charset.forName("UTF-8"));
			}
		}			
	}
}

package edu.ktlab.vnner.util;

import java.io.File;

public class CombineFilesExample {

	public static void main(String[] args) throws Exception {
		File folder = new File("corpus/VNerCorpus.1.0/");
		File outFile = new File("corpus/VNerCorpus.1.0.txt");
		CombineFiles.combine(folder, outFile);
	}

}

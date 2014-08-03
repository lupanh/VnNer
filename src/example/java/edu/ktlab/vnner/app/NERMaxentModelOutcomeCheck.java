package edu.ktlab.vnner.app;

import java.io.FileInputStream;
import java.io.InputStream;

import opennlp.tools.namefind.TokenNameFinderModel;

public class NERMaxentModelOutcomeCheck {
	public static void main(String...strings) throws Exception {
		String modelPath = "models/vnner.model";
		InputStream model = new FileInputStream(modelPath);
		TokenNameFinderModel nerModel = new TokenNameFinderModel(model);
		for (int i = 0; i < nerModel.getNameFinderModel().getNumOutcomes(); i++)
			System.out.println(nerModel.getNameFinderModel().getOutcome(i));
	}
}

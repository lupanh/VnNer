package edu.ktlab.vnner.app;

import java.util.Scanner;

import opennlp.tools.util.featuregen.AdaptiveFeatureGenerator;
import opennlp.tools.util.featuregen.BigramNameFeatureGenerator;
import opennlp.tools.util.featuregen.CachedFeatureGenerator;
import opennlp.tools.util.featuregen.OutcomePriorFeatureGenerator;
import opennlp.tools.util.featuregen.PreviousMapFeatureGenerator;
import opennlp.tools.util.featuregen.SentenceFeatureGenerator;
import opennlp.tools.util.featuregen.TokenClassFeatureGenerator;
import opennlp.tools.util.featuregen.TokenFeatureGenerator;
import opennlp.tools.util.featuregen.WindowFeatureGenerator;
import edu.ktlab.vnner.features.NgramTokenFeatureGenerator;
import edu.ktlab.vnner.features.VnWordAnalysisFeatureGenerator;
import edu.ktlab.vnner.tokenizer.VNTWSSingleton;

public class NERMaxentRecognizerExample {
	static AdaptiveFeatureGenerator createFeatureGenerator() throws Exception {
		AdaptiveFeatureGenerator featureGenerator = new CachedFeatureGenerator(
				new AdaptiveFeatureGenerator[] {
						new WindowFeatureGenerator(new VnWordAnalysisFeatureGenerator(), 2, 2),
						new WindowFeatureGenerator(new TokenClassFeatureGenerator(true), 2, 2),
						new WindowFeatureGenerator(new TokenFeatureGenerator(true), 2, 2),
						new WindowFeatureGenerator(new NgramTokenFeatureGenerator(true, 2, 2), 2, 2),
						new WindowFeatureGenerator(new NgramTokenFeatureGenerator(true, 3, 3), 2, 2),
						new BigramNameFeatureGenerator(), new OutcomePriorFeatureGenerator(),
						new PreviousMapFeatureGenerator(),
						new SentenceFeatureGenerator(true, false) });
		return featureGenerator;
	}

	public static void main(String[] args) throws Exception {
		NERMaxentRecognizer nerFinder = new NERMaxentRecognizer("models/vnner.model",
				NERMaxentFactoryExample1.createFeatureGenerator());
		System.out.print("Enter your sentence: ");
		Scanner scan = new Scanner(System.in);

		while (scan.hasNext()) {
			String text = scan.nextLine();
			if (text.equals("exit")) {
				break;
			}
			String[] tokens = VNTWSSingleton.getInstance().tokenize(text);
			String output = nerFinder.recognize(tokens);
			System.out.println(output);
			System.out.print("Enter your sentence: ");
		}
		scan.close();
	}

}

package edu.ktlab.vnner.app;

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

public class NERMaxentFactoryExample1 {
	public static AdaptiveFeatureGenerator createFeatureGenerator() throws Exception {
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
		NERMaxentFactory ner = new NERMaxentFactory(createFeatureGenerator());
		ner.trainNER("corpus/VNerCorpus.1.0.txt", "models/vnner.model", 100, 2);		
		ner.evaluatebyExactMatching("corpus/VNerCorpus.1.0.txt", "models/vnner.model");
		//ner.recognize("corpus/VNerCorpus.1.0.txt", "models/vnner.model");
		//ner.nFoldEvaluate("data/phenominer/phenominer2012.bf.corpus", 10, 100, 1);
	}
}

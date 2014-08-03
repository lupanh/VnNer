package edu.ktlab.vnner.data;

import java.io.FileInputStream;
import java.nio.charset.Charset;

import opennlp.tools.namefind.NameSample;
import opennlp.tools.namefind.NameSampleDataStream;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;

public class DataReaderExample {
	static String trainingPath = "corpus/VNerCorpus.1.0.txt";

	public static void main(String[] args) throws Exception {
		int sumEntities = 0;
		Charset charset = Charset.forName("UTF-8");
		ObjectStream<String> lineStream = new PlainTextByLineStream(new FileInputStream(trainingPath), charset);
		ObjectStream<NameSample> sampleStream = new NameSampleDataStream(lineStream);
		NameSample sample;
		while ((sample = sampleStream.read()) != null) {
			System.out.println(sample.getNames().length);
			sumEntities += sample.getNames().length;
		}
		System.out.println(sumEntities);
	}

}

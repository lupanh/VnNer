package edu.ktlab.vnner.tokenizer;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import vn.hus.nlp.tokenizer.Tokenizer;
import vn.hus.nlp.tokenizer.TokenizerOptions;
import vn.hus.nlp.tokenizer.TokenizerProvider;
import vn.hus.nlp.tokenizer.tokens.TaggedWord;

public class VNTWordSegmentationExample {
	static Tokenizer tokenizer;

	public static void main(String[] args) throws Exception {
		tokenizer = TokenizerProvider.getInstance().getTokenizer();
		
		String output = segment("01 (một) đầu máy khâu công nghiệp nhãn hiệu KANSAI, Model: DLK 1503PTS, số máy 3070291 (đã qua sử dụng).");
		System.out.println(output);
	}

	public static String segment(String sentence) {
		StringBuffer result = new StringBuffer(1000);
		StringReader reader = new StringReader(sentence);
		try {
			tokenizer.tokenize(reader);
			List<TaggedWord> list = tokenizer.getResult();
			for (TaggedWord taggedWord : list) {
				String word = taggedWord.toString();
				if (TokenizerOptions.USE_UNDERSCORE) {
					word = word.replaceAll("\\s+", "_");
				} else {
					word = "[" + word + "]";
				}
				result.append(word);
				result.append(' ');
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result.toString().trim();
	}
}

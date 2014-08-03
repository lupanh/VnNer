package edu.ktlab.vnner.tokenizer;

import java.io.StringReader;
import java.util.List;

import vn.hus.nlp.tokenizer.Tokenizer;
import vn.hus.nlp.tokenizer.TokenizerOptions;
import vn.hus.nlp.tokenizer.TokenizerProvider;
import vn.hus.nlp.tokenizer.tokens.TaggedWord;

public class VNTWSSingleton {

	private static VNTWSSingleton instance = null;
	static Tokenizer tokenizer;
	
	protected VNTWSSingleton() throws Exception {
		tokenizer = TokenizerProvider.getInstance().getTokenizer();
	}

	public static VNTWSSingleton getInstance() throws Exception {
		if (instance == null) {
			instance = new VNTWSSingleton();
		}
		return instance;
	}
	
	public String[] tokenize(String text) throws Exception {
		StringReader reader = new StringReader(text);
		tokenizer.tokenize(reader);
		List<TaggedWord> list = tokenizer.getResult();
		String[] tokens = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			String word = list.get(i).toString();
			if (TokenizerOptions.USE_UNDERSCORE) {
				word = word.replaceAll("\\s+", "_");
			} else {
				word = "[" + word + "]";
			}
			tokens[i] = word;
		}
		return tokens;
	}
	
	public String segment(String text) throws Exception {
		StringBuffer result = new StringBuffer(1000);
		StringReader reader = new StringReader(text);
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
		return result.toString();
	}

}

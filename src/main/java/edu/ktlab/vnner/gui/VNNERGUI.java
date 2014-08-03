package edu.ktlab.vnner.gui;

import java.io.StringReader;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import vn.hus.nlp.sd.SentenceDetector;
import vn.hus.nlp.sd.SentenceDetectorFactory;
import edu.ktlab.vnner.app.NERMaxentFactoryExample1;
import edu.ktlab.vnner.app.NERMaxentRecognizer;
import edu.ktlab.vnner.tokenizer.VNTWSSingleton;
import org.eclipse.wb.swt.SWTResourceManager;

public class VNNERGUI {
	public static void main(String... strings) throws Exception {
		final SentenceDetector sDetector = SentenceDetectorFactory.create("vietnamese");
		final NERMaxentRecognizer nerFinder = new NERMaxentRecognizer("models/vnner.model",
				NERMaxentFactoryExample1.createFeatureGenerator());
		
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		shell.setImage(SWTResourceManager.getImage(VNNERGUI.class, "/bsh/util/lib/script.gif"));
		shell.setMinimumSize(new Point(600, 500));
		shell.setSize(600, 521);
		shell.setText("Chương trình nhận diện thực thể trong hồ sơ nghiệp vụ Công An Nhân Dân");
		shell.setLayout(null);

		shell.pack();

		final StyledText inputText = new StyledText(shell, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL
				| SWT.H_SCROLL);
		inputText.setBounds(10, 10, 564, 241);

		final StyledText outputText = new StyledText(shell, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL
				| SWT.H_SCROLL);
		outputText.setEditable(false);
		outputText.setBounds(10, 288, 564, 164);
		
		Button btnChangeText = new Button(shell, SWT.NONE);
		btnChangeText.setBounds(224, 257, 125, 25);
		btnChangeText.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					String output = "";
					StringReader reader = new StringReader(inputText.getText());
					String[] sentences = sDetector.detectSentences(reader);
					for (String sentence : sentences) {
						String[] tokens = VNTWSSingleton.getInstance().tokenize(sentence);
						String textTagged = nerFinder.recognize(tokens);
						output += textTagged + "\n";
					}
					outputText.setText(output);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnChangeText.setText("Nhận diện thực thể");		

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}

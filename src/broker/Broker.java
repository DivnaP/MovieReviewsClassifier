package broker;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.xml.transform.TransformerException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;

import gui.MainFrame;


import klase.Classifier;
import klase.PositiveNegativeSentence;
import klase.TextToPdfConverter;
import klase.Trainer;
import klase.apiCall;
import swn.classification.SentiWordNetDemo;;

public class Broker {
	String miki="";
	private static Broker instance = null;

	protected Broker() {
		// Exists only to defeat instantiation.
	}

	public static Broker getInstance() {
		if (instance == null) {
			instance = new Broker();
		}
		return instance;
	}

	public String analyze(String file, String type) {

		if (type.equals("nb"))
			
			return Classifier.main(new String[] { file, "data/istreniran.txt" });

		else if (type.contains("SWN"))
			
			return SentiWordNetDemo.main(new String[] { file });
		
		else {
	
			return "";
		}

	}
	public String load(String fileName) {
		String text="";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String line;
	
			while ((line = reader.readLine()) != null) {
                text = text + " " + line;
                
            }
			// System.out.println("===== Loaded text data: " + fileName + " =====");
			reader.close();
			// System.out.println(text);
			
		}
		catch (IOException e) {
			System.out.println("Problem found when reading: " + fileName);
		}
		for (int i = 0; i < text.length(); i++) {
			text=text.replace("\"", "");
		}
		return text;
	}
	public String train(String type) {

		
		File file = new File("data/kritikeSve3.arff");
		
		
		return Trainer.main(new String[] { file.getPath(),type });

	}

	public void createPDF(String text) throws Exception {

		TextToPdfConverter.main(new String[] { text });
		if (Desktop.isDesktopSupported()) {
			try {
				File myFile = new File("data/report.pdf");
				Desktop.getDesktop().open(myFile);
			} catch (IOException ex) {

			}
		}

	}

}

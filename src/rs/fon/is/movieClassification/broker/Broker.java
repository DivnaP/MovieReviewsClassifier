package rs.fon.is.movieClassification.broker;

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

import rs.fon.is.movieClassification.classification.Classifier;
import rs.fon.is.movieClassification.classification.Trainer;
import rs.fon.is.movieClassification.classification.swn.SentiWordNetDemo;
import rs.fon.is.movieClassification.domain.Sentences;
import rs.fon.is.movieClassification.pdf.TextToPdfConverter;
import rs.fon.is.movieClassification.gui.MainFrame;;

public class Broker {

	private static Broker instance = null;

	protected Broker() {

	}

	public static Broker getInstance() {
		if (instance == null) {
			instance = new Broker();
		}
		return instance;
	}

	public String analyze(String file, String type) {

		if (type.equals("nb")) {
			
			Classifier c = new Classifier();
			return c.classification(file, "data/model.txt");
		}

		else if (type.contains("SWN"))
		{
			SentiWordNetDemo swd = new SentiWordNetDemo();
			return swd.classification(file);
		}
		else {

			return "";
		}

	}

	public String train(String type) {

		File file = new File("data/movieReviews.arff");

		Trainer tr = new Trainer();
		return tr.train(file.getPath(), type);

	}

	public void createPDF(String text) throws Exception {

		TextToPdfConverter pdf = new TextToPdfConverter(text);

		if (pdf.convertTextToPDF()) {
			System.out.println("Text file successfully converted to PDF");

		}

		if (Desktop.isDesktopSupported()) {
			try {
				File myFile = new File("data/report.pdf");
				Desktop.getDesktop().open(myFile);
			} catch (IOException ex) {

			}
		}

	}

	public String load(String fileName) {
		String text = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String line;

			while ((line = reader.readLine()) != null) {
				text = text + " " + line;

			}
			reader.close();

		} catch (IOException e) {
			System.out.println("Problem found when reading: " + fileName);
		}
		for (int i = 0; i < text.length(); i++) {
			text = text.replace("\"", "");
		}
		return text;
	}

}

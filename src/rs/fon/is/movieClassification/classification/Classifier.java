package rs.fon.is.movieClassification.classification;

import weka.core.*;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.unsupervised.attribute.StringToWordVector;
import weka.classifiers.bayes.NaiveBayes;

import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.J48;

import java.util.List;
import java.util.ArrayList;
import java.io.*;

public class Classifier {

	private String forGUI;
	private String text;
	private Instances instances;

	private FilteredClassifier classifier;

	public void load(String fileName) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String line;
			text = "";
			while ((line = reader.readLine()) != null) {
				text = text + " " + line;
			}
			System.out.println("===== Loaded text data: " + fileName + " =====");

			forGUI = "\n ===== Loaded text data: " + fileName + " =====" + "\n" + text;

			reader.close();
			System.out.println(text);
		} catch (IOException e) {
			System.out.println("Problem found when reading: " + fileName);
		}
	}

	public void loadModel(String fileName) {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
			Object tmp = in.readObject();
			classifier = (FilteredClassifier) tmp;

			in.close();
			System.out.println("===== Loaded model: " + fileName + " =====");
			forGUI += "\n" + "=====  Loaded model: " + fileName + " =====";
		} catch (Exception e) {

			System.out.println("Problem found when reading: " + fileName);
		}
	}

	public void makeInstance() {

		FastVector fvNominalVal = new FastVector(2);
		fvNominalVal.addElement("negativeC");
		fvNominalVal.addElement("positiveC");
		Attribute attribute1 = new Attribute("class", fvNominalVal);
		Attribute attribute2 = new Attribute("text", (FastVector) null);

		FastVector fvWekaAttributes = new FastVector(2);
		fvWekaAttributes.addElement(attribute1);
		fvWekaAttributes.addElement(attribute2);
		instances = new Instances("Test relation", fvWekaAttributes, 1);

		instances.setClassIndex(0);

		Instance instance = new Instance(2);
		instance.setValue(attribute2, text);

		instances.add(instance);
		System.out.println("===== Instance created with reference dataset =====");

		System.out.println(instances);
	}

	public void classify() {
		try {
			double pred = classifier.classifyInstance(instances.instance(0));
			System.out.println("===== Classified instance =====");
			System.out.println("Class predicted: " + instances.classAttribute().value((int) pred));

			String klasa = null;
			switch (instances.classAttribute().value((int) pred)) {
			case "positiveC":
				klasa = " Positive review";
				break;
			case "negativeC":
				klasa = " Negative review";

				break;
			default:
				break;
			}
			;

			forGUI += " \n\n\n ===== Classified instance ====== \n" + "Class predicted:" + klasa;

		} catch (Exception e) {
			System.out.println("Problem found when classifying the text");
		}
	}

	public String returnResult() {

		return forGUI;

	}

	public String classification(String fileData, String fileModel) {

		Classifier classifier = null;

		classifier = new Classifier();
		classifier.load(fileData);
		classifier.loadModel(fileModel);

		classifier.makeInstance();
		classifier.classify();

		return classifier.returnResult();
	}

}
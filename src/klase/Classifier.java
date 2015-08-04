package klase;
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
		


	 String forGUI;
	 String text;
	 Instances instances;

	FilteredClassifier classifier;
	

	public void load(String fileName) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String line;
			text = "";
			while ((line = reader.readLine()) != null) {
                text = text + " " + line;
            }
			System.out.println("===== Loaded text data: " + fileName + " =====");
			
			forGUI="\n ===== Učitani tekst za klasifikaciju: " + fileName + " ====="+"\n"+text;
			
			reader.close();
			System.out.println(text);
		}
		catch (IOException e) {
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
 			forGUI+="\n"+"===== Učitani model: " + fileName + " =====";
       } 
		catch (Exception e) {
		
			System.out.println("Problem found when reading: " + fileName);
		}
	}

	
	public void makeInstance() {
	
		FastVector fvNominalVal = new FastVector(2);
		fvNominalVal.addElement("negativeC");
		fvNominalVal.addElement("positiveC");
		Attribute attribute1 = new Attribute("class", fvNominalVal);
		Attribute attribute2 = new Attribute("text",(FastVector) null);
	
		FastVector fvWekaAttributes = new FastVector(2);
		fvWekaAttributes.addElement(attribute1);
		fvWekaAttributes.addElement(attribute2);
		instances = new Instances("Test relation", fvWekaAttributes, 1);           
	
		instances.setClassIndex(0);
	
		Instance instance = new Instance(2);
		instance.setValue(attribute2, text);
		
		// instance.setValue((Attribute)fvWekaAttributes.elementAt(1), text);
		instances.add(instance);
 		System.out.println("===== Instance created with reference dataset =====");
 		
 	//	forGUI+="\n ===== Instance created with reference dataset =====";
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
				klasa=" Pozitivna kritika";
				break;
			case "negativeC":
				klasa=" Negativna kritika";
				
				break;
			default:
				break;
			};
			
			forGUI+=" \n\n\n ===== Klasifikovana instanca ====== \n"+ "Predviđena pripadnost klasi: " + klasa;
		
				
		}	
		catch (Exception e) {
			System.out.println("Problem found when classifying the text");
		}		
	}
	public String returnResult(){
		
		return forGUI;
		
	}
	
	public static String main (String[] args) {
	
		Classifier classifier=null;
		
		if (args.length < 2)
			System.out.println("Missing arguments <fileData> <fileModel>");
		else {
		
			classifier = new Classifier();
			classifier.load(args[0]);
			classifier.loadModel(args[1]);
		
			classifier.makeInstance();
			classifier.classify();
			
			
		}
		return classifier.returnResult();
	}
	
}	
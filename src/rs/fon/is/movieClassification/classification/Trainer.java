package rs.fon.is.movieClassification.classification;
/**
 * A Java class that implements a simple text learner, based on WEKA.
 * To be used with MyFilteredClassifier.java.
 * WEKA is available at: http://www.cs.waikato.ac.nz/ml/weka/
 * Copyright (C) 2013 Jose Maria Gomez Hidalgo - http://www.esp.uem.es/jmgomez
 *
 * This program is free software: you can redistribute it and/or modify
 * it for any purpose.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 */

import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import java.util.Random;

//import weka.classifiers.bayes.BayesianLogisticRegression;
//import weka.classifiers.bayes.ComplementNaiveBayes;
import weka.classifiers.bayes.DMNBtext;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.bayes.NaiveBayesMultinomial;
import weka.classifiers.functions.LibSVM;
import weka.classifiers.functions.Logistic;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.functions.SMO;
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.J48;
import weka.core.converters.ArffLoader.ArffReader;
import weka.core.converters.LibSVMLoader;
import weka.classifiers.functions.LibSVM;
import java.io.*;

public class Trainer {

	private String forGUI;
	private Instances trainData;

	private StringToWordVector filter;

	private FilteredClassifier classifier;

	public void loadDataset(String fileName) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			ArffReader arff = new ArffReader(reader);
			trainData = arff.getData();
			System.out.println("===== Loaded dataset: " + fileName + " =====");
			forGUI = "\n ===== Loaded dataset: " + fileName + " =====\n";
			reader.close();
		} catch (IOException e) {
			System.out.println("Problem found when reading: " + fileName);
		}
	}

	public void evaluate(String classifyer) {
		try {
			trainData.setClassIndex(0);
			filter = new StringToWordVector();
			filter.setAttributeIndices("last");
			filter.setStopwords(new File("data/stop_words2.txt"));
			classifier = new FilteredClassifier();
			classifier.setFilter(filter);

			switch (classifyer) {
			case "nb":
				classifier.setClassifier(new NaiveBayesMultinomial());
				break;
			case "j48":
				classifier.setClassifier(new J48());
				break;
			case "svm":
				classifier.setClassifier(new LibSVM());
				break;
			case "logistic":
				classifier.setClassifier(new Logistic());
				break;
			case "smo":
				classifier.setClassifier(new SMO());

				break;
			default:
				classifier.setClassifier(new NaiveBayesMultinomial());
				break;
			}

			Evaluation eval = new Evaluation(trainData);

			eval.crossValidateModel(classifier, trainData, 4, new Random(1));
			System.out.println(eval.toSummaryString());
			System.out.println(eval.toClassDetailsString());
			System.out.println("===== Evaluating on filtered (training) dataset done =====");

			forGUI += eval.toSummaryString() + "\n\n" + eval.toClassDetailsString()
					+ "\n ===== Evaluating on filtered (training) dataset done =====";
		} catch (Exception e) {
			System.out.println("Problem found when evaluating");
		}
	}

	public void learn(String typeClassifier) {
		try {
			trainData.setClassIndex(0);
			filter = new StringToWordVector();
			filter.setAttributeIndices("last");
			filter.setStopwords(new File("data/stop_words2.txt"));

			classifier = new FilteredClassifier();
			classifier.setFilter(filter);

			switch (typeClassifier) {
			case "nb":
				classifier.setClassifier(new NaiveBayesMultinomial());
				forGUI += "\n ===== Training with classifier Naive Bayes Multinominal=====";
				break;
			case "j48":
				classifier.setClassifier(new J48());
				forGUI += "\n ===== Training with classifier J48 tree=====";
				break;
			case "svm":

				classifier.setClassifier(new LibSVM());
				forGUI += "\n ===== Training with classifier Support Vector Machine=====";
				break;
			case "logistic":
				classifier.setClassifier(new Logistic());
				forGUI += "\n ===== Training with classifier Logistic regression=====";
				break;
			case "smo":
				classifier.setClassifier(new SMO());

				forGUI += "\n ===== Training with classifier Sequential Minimal Optimization=====";
				break;
			default:
				classifier.setClassifier(new NaiveBayesMultinomial());
				forGUI += "\n ===== Training with classifier Naive Bayes Multinominal=====";
				break;
			}

			classifier.buildClassifier(trainData);
	
			System.out.println("===== Training on filtered (training) dataset done =====");

			forGUI += "\n ===== Training on filtered (training) dataset done =====";

		} catch (Exception e) {
			System.out.println("Problem found when training");
		}
	}

	public void saveModel(String fileName) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
			out.writeObject(classifier);
			out.close();
			System.out.println("===== Saved model: " + fileName + " =====");
		} catch (IOException e) {
			System.out.println("Problem found when writing: " + fileName);
		}
	}

	public String returnResult() {

		return forGUI;

	}

	public static String main(String[] args) {

		Trainer learner = null;
		if (args.length < 1)
			System.out.println("Missing arguments <fileData> <fileModel>");
		else {

			learner = new Trainer();
			learner.loadDataset(args[0]);
			String typeClassifier = args[1];
			learner.evaluate(typeClassifier);
			learner.learn(typeClassifier);
			learner.saveModel("data/model.txt");

		
		}
		return learner.returnResult();
	}
}
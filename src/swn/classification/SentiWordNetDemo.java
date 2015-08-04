package swn.classification;
/**
 * A Java class that implements a simple Sentiment Classifier based on SentiWordNet.
 * It requires the class SWN3.java and SentiWordNet. See: http://sentiwordnet.isti.cnr.it/.
 * Copyright (C) 2013 Jose Maria Gomez Hidalgo - http://www.esp.uem.es/jmgomez
 *
 * This program is free software: you can redistribute it and/or modify
 * it for any purpose.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 */
 
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SentiWordNetDemo {
	final List<String> stopWords = readFileLineByLine(new File("data/stop_words2.txt"));
	/**
	 * String that stores the text to guess its polarity.
	 */
	String text;
	
	/**
	 * SentiWordNet object to query the polarity of a word.
	 */
	SWN3 sentiwordnet = new SWN3("data/SentiWordNet_3.0.0_20130122.txt");		

 	/**
	 * This method loads the text to be classified.
	 * @param fileName The name of the file that stores the text.
	 */
	public void load(String fileName) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String line;
			text = "";
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
	}

	/**
	 * This method performs the classification of the text.
	 * Algorithm: Use all POS, say "yes" in case of 0.
	 * @return An string with "no" (negative) or "yes" (positive).
	 */
	public String classifyAllPOSY() {
	
		int count = 0;
		try {
			String delimiters = "\\W";
			String[] tokens = text.split(delimiters);
			String feeling = "";		
			boolean stop=false;
			for (int i = 0; i < tokens.length; ++i) {
				stop=false;
				for (String q : stopWords) {
					if(tokens[i].equals(q))
					{	stop=true;
						break;}
				}
				if (stop)
					continue;
				// Add weights -- positive => +1, strong_positive => +2, negative => -1, strong_negative => -2
				if (!tokens[i].equals("")) {
					// Search as adjetive
					feeling = sentiwordnet.extract(tokens[i],"a");
					if ((feeling != null) && (!feeling.equals(""))) {
						switch (feeling) {
							case "strong_positive"	: count += 2;
													  break;
							case "positive"			: count += 1;
													  break;
							case "weak_positive"			: count += 0.5;
							  break;
							case "weak_negative"			: count -= 0.5;
							  break;
							case "negative"			: count -= 1;
													  break;
							case "strong_negative"	: count -= 2;
													  break;
						}
						// System.out.println(tokens[i]+"#"+feeling+"#"+count);
					}
					// Search as noun
					feeling = sentiwordnet.extract(tokens[i],"n");
					if ((feeling != null) && (!feeling.equals(""))) {
						switch (feeling) {
						case "weak_positive"			: count += 0.5;
						  break;
						case "weak_negative"			: count -= 0.5;
						  break;
							case "strong_positive"	: count += 2;
													  break;
							case "positive"			: count += 1;
													  break;
							case "negative"			: count -= 1;
													  break;
							case "strong_negative"	: count -= 2;
													  break;
						}
						 System.out.println(tokens[i]+"#"+feeling+"#"+count);
					}
					// Search as adverb
					feeling = sentiwordnet.extract(tokens[i],"r");
					if ((feeling != null) && (!feeling.equals(""))) {
						switch (feeling) {
						case "weak_positive"			: count += 0.5;
						  break;
						case "weak_negative"			: count -= 0.5;
						  break;
							case "strong_positive"	: count += 2;
													  break;
							case "positive"			: count += 1;
													  break;
							case "negative"			: count -= 1;
													  break;
							case "strong_negative"	: count -= 2;
													  break;
						}
						// System.out.println(tokens[i]+"#"+feeling+"#"+count);
					}
					// Search as verb
					feeling = sentiwordnet.extract(tokens[i],"v");
					if ((feeling != null) && (!feeling.equals(""))) {
						switch (feeling) {
						case "weak_positive"			: count += 0.5;
						  break;
						case "weak_negative"			: count -= 0.5;
						  break;
							case "strong_positive"	: count += 2;
													  break;
							case "positive"			: count += 1;
													  break;
							case "negative"			: count -= 1;
													  break;
							case "strong_negative"	: count -= 2;
													  break;
						}
						// System.out.println(tokens[i]+"#"+feeling+"#"+count);
					}
				}
			}
			// System.out.println(count);
		}
		catch (Exception e) {
			System.out.println("Problem found when classifying the text");
		}
		// Returns "yes" in case of 0
		if (count >= 0) 
			return "pozitivna";
		else return "negativna";
	}
	
	/**
	 * This method performs the classification of the text.
	 * Algorithm: Use all POS, say "no" in case of 0.
	 * @return An string with "no" (negative) or "yes" (positive).
	 */
	public String classifyAllPOSN() {
	
		int count = 0;
		try {
			String delimiters = "\\W";
			String[] tokens = text.split(delimiters);
			
			
			String feeling = "";
			boolean stop=false;
			for (int i = 0; i < tokens.length; ++i) {
				stop=false;
				for (String q : stopWords) {
					if(tokens[i].equals(q))
					{	stop=true;
						break;}
				}
				if (stop)
					continue;
				// Add weights -- positive => +1, strong_positive => +2, negative => -1, strong_negative => -2
				if (!tokens[i].equals("")) {
					// Search as adjetive
					feeling = sentiwordnet.extract(tokens[i],"a");
					if ((feeling != null) && (!feeling.equals(""))) {
						switch (feeling) {
						case "weak_positive"			: count += 0.5;
						  break;
						case "weak_negative"			: count -= 0.5;
						  break;
							case "strong_positive"	: count += 2;
													  break;
							case "positive"			: count += 1;
													  break;
							case "negative"			: count -= 1;
													  break;
							case "strong_negative"	: count -= 2;
													  break;
						}
						// System.out.println(tokens[i]+"#"+feeling+"#"+count);
					}
					// Search as noun
					feeling = sentiwordnet.extract(tokens[i],"n");
					if ((feeling != null) && (!feeling.equals(""))) {
						switch (feeling) {
						case "weak_positive"			: count += 0.5;
						  break;
						case "weak_negative"			: count -= 0.5;
						  break;
							case "strong_positive"	: count += 2;
													  break;
							case "positive"			: count += 1;
													  break;
							case "negative"			: count -= 1;
													  break;
							case "strong_negative"	: count -= 2;
													  break;
						}
						// System.out.println(tokens[i]+"#"+feeling+"#"+count);
					}
					// Search as adverb
					feeling = sentiwordnet.extract(tokens[i],"r");
					if ((feeling != null) && (!feeling.equals(""))) {
						switch (feeling) {
						case "weak_positive"			: count += 0.5;
						  break;
						case "weak_negative"			: count -= 0.5;
						  break;
							case "strong_positive"	: count += 2;
													  break;
							case "positive"			: count += 1;
													  break;
							case "negative"			: count -= 1;
													  break;
							case "strong_negative"	: count -= 2;
													  break;
						}
						// System.out.println(tokens[i]+"#"+feeling+"#"+count);
					}
					// Search as verb
					feeling = sentiwordnet.extract(tokens[i],"v");
					if ((feeling != null) && (!feeling.equals(""))) {
						switch (feeling) {
						case "weak_positive"			: count += 0.5;
						  break;
						case "weak_negative"			: count -= 0.5;
						  break;
							case "strong_positive"	: count += 2;
													  break;
							case "positive"			: count += 1;
													  break;
							case "negative"			: count -= 1;
													  break;
							case "strong_negative"	: count -= 2;
													  break;
						}
						// System.out.println(tokens[i]+"#"+feeling+"#"+count);
					}
				}
			}
			// System.out.println(count);
		}
		catch (Exception e) {
			System.out.println("Problem found when classifying the text");
		}
		// Returns "no" in case of 0
		if (count > 0) 
			return "pozitivna";
		else return "negativna";
	}
	
	
	///moje bez noun-a
	
	
	public String classifyAllPOSN2() {
		
		int count = 0;
		try {
			String delimiters = "\\W";
			String[] tokens = text.split(delimiters);
			String feeling = "";
			
			boolean stop=false;
			for (int i = 0; i < tokens.length; ++i) {
				stop=false;
				for (String q : stopWords) {
					if(tokens[i].equals(q))
					{	stop=true;
						break;}
				}
				if (stop)
					continue;
				// Add weights -- positive => +1, strong_positive => +2, negative => -1, strong_negative => -2
				if (!tokens[i].equals("")) {
					// Search as adjetive
					feeling = sentiwordnet.extract(tokens[i],"a");
					if ((feeling != null) && (!feeling.equals(""))) {
						switch (feeling) {
						case "weak_positive"			: count += 0.5;
						  break;
						case "weak_negative"			: count -= 0.5;
						  break;
							case "strong_positive"	: count += 2;
													  break;
							case "positive"			: count += 1;
													  break;
							case "negative"			: count -= 1;
													  break;
							case "strong_negative"	: count -= 2;
													  break;
						}
						// System.out.println(tokens[i]+"#"+feeling+"#"+count);
					}
					// Search as noun
		
					// Search as adverb
					feeling = sentiwordnet.extract(tokens[i],"r");
					if ((feeling != null) && (!feeling.equals(""))) {
						switch (feeling) {
						case "weak_positive"			: count += 0.5;
						  break;
						case "weak_negative"			: count -= 0.5;
						  break;
							case "strong_positive"	: count += 2;
													  break;
							case "positive"			: count += 1;
													  break;
							case "negative"			: count -= 1;
													  break;
							case "strong_negative"	: count -= 2;
													  break;
						}
						// System.out.println(tokens[i]+"#"+feeling+"#"+count);
					}
					// Search as verb
					feeling = sentiwordnet.extract(tokens[i],"v");
					if ((feeling != null) && (!feeling.equals(""))) {
						switch (feeling) {
						case "weak_positive"			: count += 0.5;
						  break;
						case "weak_negative"			: count -= 0.5;
						  break;
							case "strong_positive"	: count += 2;
													  break;
							case "positive"			: count += 1;
													  break;
							case "negative"			: count -= 1;
													  break;
							case "strong_negative"	: count -= 2;
													  break;
						}
						// System.out.println(tokens[i]+"#"+feeling+"#"+count);
					}
				}
			}
			// System.out.println(count);
		}
		catch (Exception e) {
			System.out.println("Problem found when classifying the text");
		}
		// Returns "no" in case of 0
		if (count > 0) 
			return "pozitivna";
		else return "negativna";
	}
	
	/**
	 * This method performs the classification of the text.
	 * Algorithm: Use only ADJ, say "yes" in case of 0.
	 * @return An string with "no" (negative) or "yes" (positive).
	 */
	public String classifyADJY() {
	
		int count = 0;
		try {
			String delimiters = "\\W";
			String[] tokens = text.split(delimiters);
			String feeling = "";		
			boolean stop=false;
			for (int i = 0; i < tokens.length; ++i) {
				stop=false;
				for (String q : stopWords) {
					if(tokens[i].equals(q))
					{	stop=true;
						break;}
				}
				if (stop)
					continue;
				// Add weights -- positive => +1, strong_positive => +2, negative => -1, strong_negative => -2
				if (!tokens[i].equals("")) {
					// Search as adjetive
					feeling = sentiwordnet.extract(tokens[i],"a");
					if ((feeling != null) && (!feeling.equals(""))) {
						switch (feeling) {
						case "weak_positive"			: count += 0.5;
						  break;
						case "weak_negative"			: count -= 0.5;
						  break;
							case "strong_positive"	: count += 2;
													  break;
							case "positive"			: count += 1;
													  break;
							case "negative"			: count -= 1;
													  break;
							case "strong_negative"	: count -= 2;
													  break;
						}
						// System.out.println(tokens[i]+"#"+feeling+"#"+count);
					}
				}
			}
			// System.out.println(count);
		}
		catch (Exception e) {
			System.out.println("Problem found when classifying the text");
		}
		// Returns "yes" in case of 0
		if (count >= 0) 
			return "pozitivna";
		else return "negativna";
	}
	
	
	//noun
	public String classifyNounN() {
		
		int count = 0;
		try {
			String delimiters = "\\W";
			String[] tokens = text.split(delimiters);
			String feeling = "";
			boolean stop=false;
			for (int i = 0; i < tokens.length; ++i) {
				stop=false;
				for (String q : stopWords) {
					if(tokens[i].equals(q))
					{	stop=true;
						break;}
				}
				if (stop)
					continue;
				// Add weights -- positive => +1, strong_positive => +2, negative => -1, strong_negative => -2
				if (!tokens[i].equals("")) {
					
					// Search as adjetive
					feeling = sentiwordnet.extract(tokens[i],"n");
					if ((feeling != null) && (!feeling.equals(""))) {
						switch (feeling) {
						case "weak_positive"			: count += 0.5;
						  break;
						case "weak_negative"			: count -= 0.5;
						  break;
							case "strong_positive"	: count += 2;
													  break;
							case "positive"			: count += 1;
													  break;
							case "negative"			: count -= 1;
													  break;
							case "strong_negative"	: count -= 2;
													  break;
						}
						// System.out.println(tokens[i]+"#"+feeling+"#"+count);
					}
				}
			}
			// System.out.println(count);
		}
		catch (Exception e) {
			System.out.println("Problem found when classifying the text");
		}
		// Returns "no" in case of 0
		if (count >= 0) 
			return "pozitivna";
		else return "negativna";
	}
	
	
	
	/**
	 * This method performs the classification of the text.
	 * Algorithm: Use only ADJ, say "no" in case of 0.
	 * @return An string with "no" (negative) or "yes" (positive).
	 */
	public String classifyADJN() {
	
		int count = 0;
		try {
			String delimiters = "\\W";
			String[] tokens = text.split(delimiters);
			String feeling = "";
			boolean stop=false;
			for (int i = 0; i < tokens.length; ++i) {
				stop=false;
				for (String q : stopWords) {
					if(tokens[i].equals(q))
					{	stop=true;
						break;}
				}
				if (stop)
					continue;
				// Add weights -- positive => +1, strong_positive => +2, negative => -1, strong_negative => -2
				if (!tokens[i].equals("")) {
					
					// Search as adjetive
					feeling = sentiwordnet.extract(tokens[i],"a");
					if ((feeling != null) && (!feeling.equals(""))) {
						switch (feeling) {
						case "weak_positive"			: count += 0.5;
						  break;
						case "weak_negative"			: count -= 0.5;
						  break;
							case "strong_positive"	: count += 2;
													  break;
							case "positive"			: count += 1;
													  break;
							case "negative"			: count -= 1;
													  break;
							case "strong_negative"	: count -= 2;
													  break;
						}
						// System.out.println(tokens[i]+"#"+feeling+"#"+count);
					}
				}
			}
			// System.out.println(count);
		}
		catch (Exception e) {
			System.out.println("Problem found when classifying the text");
		}
		// Returns "no" in case of 0
		if (count > 0) 
			return "pozitivna";
		else return "negativna";
	}
 
	/**
	 * Main method.
	 * Usage: java SentiWordNetDemo <file>
	 * @param args The command line args.
	 */
	public static String main (String[] args) {
		SentiWordNetDemo classifier = null;
		if (args.length < 1)
			System.out.println("Usage: java SentiWordNetDemo <file>");
		else {
		
		
			classifier = new SentiWordNetDemo();
			classifier.load(args[0]);
			// Comment the approaches you do not want to check
		System.out.println(classifier.classifyAllPOSY());
			System.out.println(classifier.classifyAllPOSN());
		//	System.out.println(classifier.classifyAllPOSN2());
			System.out.println(classifier.classifyADJY());
			System.out.println(classifier.classifyADJN());	
			
		}
		return "===== Klasifikacija po svim vrstama reči ===== \n\n"+" Kritika je klasifikovana kao : "+classifier.classifyAllPOSY()+ "\n\n"+
		"===== Klasifikacija po svim pridevima ===== \n\n"+" Kritika je klasifikovana kao : "+classifier.classifyAllPOSY()
		+"\n\n ===== Klasifikacija po svim imenicama ===== \n\n"+" Kritika je klasifikovana kao : "+classifier.classifyNounN();
	}
	
	private static List<String> readFileLineByLine(File f) {
		try {
			List<String> contents = new ArrayList<String>();

			BufferedReader input = new BufferedReader(new FileReader(f));
			for (String line = input.readLine(); line != null; line = input.readLine()) {
				contents.add(line);
			}
			input.close();

			return contents;

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
			return null;
		}
	}
}
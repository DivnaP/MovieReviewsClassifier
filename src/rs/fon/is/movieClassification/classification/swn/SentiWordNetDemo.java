package rs.fon.is.movieClassification.classification.swn;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SentiWordNetDemo {
	final List<String> stopWords = readFileLineByLine(new File("data/stop_words2.txt"));
	
	private String text;
	
	private SWN3 sentiwordnet = new SWN3("data/SentiWordNet_3.0.0_20130122.txt");		

	public void load(String fileName) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String line;
			text = "";
			while ((line = reader.readLine()) != null) {
                text = text + " " + line;
            }
			
			reader.close();
		
		}
		catch (IOException e) {
			System.out.println("Problem found when reading: " + fileName);
		}
	}

	
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
					
					}
				}
			}
			
		}
		catch (Exception e) {
			System.out.println("Problem found when classifying the text");
		}
	
		if (count >= 0) 
			return "positive";
		else return "negative";
	}
	
	
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
						
					}
				}
			}
			
		}
		catch (Exception e) {
			System.out.println("Problem found when classifying the text");
		}
		
		if (count > 0) 
			return "positive";
		else return "negative";
	}
	

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
					
					}
				}
			}
		
		}
		catch (Exception e) {
			System.out.println("Problem found when classifying the text");
		}
	
		if (count > 0) 
			return "positive";
		else return "negative";
	}

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
						
					}
				}
			}
			
		}
		catch (Exception e) {
			System.out.println("Problem found when classifying the text");
		}
		// Returns "yes" in case of 0
		if (count >= 0) 
			return "positive";
		else return "negative";
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
						
					}
				}
			}
			
		}
		catch (Exception e) {
			System.out.println("Problem found when classifying the text");
		}
	
		if (count >= 0) 
			return "positive";
		else return "negative";
	}
	
	
	
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
					
					}
				}
			}
			
		}
		catch (Exception e) {
			System.out.println("Problem found when classifying the text");
		}
		
		if (count > 0) 
			return "positive";
		else return "negative";
	}
 
	
	public  String classification (String file) {
		SentiWordNetDemo classifier = null;
	
		
		
			classifier = new SentiWordNetDemo();
			classifier.load(file);
			
		System.out.println(classifier.classifyAllPOSY());
			System.out.println(classifier.classifyAllPOSN());
		//	System.out.println(classifier.classifyAllPOSN2());
			System.out.println(classifier.classifyADJY());
			System.out.println(classifier.classifyADJN());	
			
		
		return "===== Classification by all kinds of words ===== \n\n"+" Review is classified as : "+classifier.classifyAllPOSY()+ "\n\n"+
		"===== Classification by adjectives ===== \n\n"+" Review is classified as : "+classifier.classifyAllPOSY()
		+"\n\n ===== Classification by nouns ===== \n\n"+" Review is classified as : "+classifier.classifyNounN();
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
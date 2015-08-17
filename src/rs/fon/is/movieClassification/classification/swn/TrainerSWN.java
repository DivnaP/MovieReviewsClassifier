package rs.fon.is.movieClassification.classification.swn;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TrainerSWN {
private static double TN=0;
private static double TP=0;
private static double FN=0;
private static double FP=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SentiWordNetDemo swd = new SentiWordNetDemo();
	
		File folder = new File("C:/Users/Divna/Desktop/tokens/neg");
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
			if (file.isFile()) {
				
			String predicted=	swd.classification(file.getPath());
			if(predicted.equals("negative")) TN++;
			else FP++;
			}
		}
		
		 folder = new File("C:/Users/Divna/Desktop/tokens/pos");
	 listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
			if (file.isFile()) {
				
			String predicted=	swd.classification(file.getPath());
			if(predicted.equals("positive")) TP++;
			else FN++;
			}
		}
		
		FP=295;FN=262;TP=432;TN=397;
		System.out.println("false positive"+FP+" false negative "+FN+" true positive "+TP+" true negative "+TN);
	double precisionP= TP/(TP+FP);
	double precisionN= TN/(TN+FN);
	double recallP=TP/(TP+FN);
	double recallN=TN/(TN+FP);
	
	double precisionAvg=(precisionP+precisionN)/2;
	double recallAvg=(recallP+recallN)/2;
	System.out.println("Precision "+TP/(TP+FP));
	System.out.println("Recall "+TP/(TP+FN));
	System.out.println("F measure "+(2*precisionAvg*recallAvg)/(precisionAvg+recallAvg));
	
	}
public void write(String classPredicted, String classReview) {

		
		BufferedWriter bufferedWriter;
		try {
			bufferedWriter = new BufferedWriter(new FileWriter("data/movieReviewsTestDatasetSWN.txt", true));

			bufferedWriter.write(classReview);
			bufferedWriter.write(classPredicted + "'");
			bufferedWriter.close();
		} catch (IOException e) {

			e.printStackTrace();

		}
	}
}

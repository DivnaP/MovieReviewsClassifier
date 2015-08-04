package klase;

public class DetailSentence {
double score;

public double getScore() {
	return score;
}
public void setScore(double score) {
	this.score = score;
}
public int getOriginal_length() {
	return original_length;
}
public void setOriginal_length(int original_length) {
	this.original_length = original_length;
}
public String getSentiment() {
	return sentiment;
}
public void setSentiment(String sentiment) {
	this.sentiment = sentiment;
}
int original_length;
String sentiment;

@Override
public String toString() {
   return "   score=" + score + "   sentiment = " + sentiment;
}
}

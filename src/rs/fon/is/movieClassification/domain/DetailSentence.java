package rs.fon.is.movieClassification.domain;

public class DetailSentence {

	private double score;
	private int original_length;
	private String sentiment;

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

	@Override
	public String toString() {
		return "   score=" + score + "   sentiment = " + sentiment;
	}
}

package rs.fon.is.movieClassification.domain;

import java.util.ArrayList;
import java.util.List;

public class Sentences {

	private List<DetailSentence> positive;
	private List<DetailSentence> negative;
	private DetailSentence aggregate;

	public DetailSentence getAggregate() {
		return aggregate;
	}

	public void setAggregate(DetailSentence aggregate) {
		this.aggregate = aggregate;
	}

	public List<DetailSentence> getPositive() {
		return positive;
	}

	public void setPositive(List<DetailSentence> positive) {
		this.positive = positive;
	}

	public List<DetailSentence> getNegative() {
		return negative;
	}

	public void setNegative(List<DetailSentence> negative) {
		this.negative = negative;
	}

	@Override
	public String toString() {
		return " ===== Results =====\n Positive= " + positive + " \n Negative = " + negative
				+ " \n\n ===== Summary ====== \n" + "" + aggregate + "";
	}
}

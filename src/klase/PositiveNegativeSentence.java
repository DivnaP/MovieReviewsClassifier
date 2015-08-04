package klase;

import java.util.ArrayList;
import java.util.List;

public class PositiveNegativeSentence {
List<DetailSentence >positive;
List<DetailSentence >negative;
DetailSentence aggregate;
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
   return " ===== Rezultati =====\n Pozitivno= " + positive + " \n Negativno = " + negative +" \n\n ===== Ukupno ====== \n"
   		+ ""+aggregate+"";
}
}

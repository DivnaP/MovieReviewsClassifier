package klase;

import java.util.ArrayList;
import java.util.List;
 
public class Reviews{
 

	private int id;
	private List<Review> results;

 
	//getter and setter methods
 
	public List<Review> getResults() {
		return results;
	}


	public void setResults(List<Review> results) {
		this.results = results;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}



	@Override
	public String toString() {
	   return "--Kritike [id Filma=" + id + " kritika = " + results +"]";
	}
 
}
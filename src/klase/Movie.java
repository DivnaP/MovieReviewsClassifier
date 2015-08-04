package klase;

import java.util.ArrayList;
import java.util.List;
 
public class Movie {
 
	private int id;
	private String original_title;
	Reviews k;
	public Reviews getK() {
		return k;
	}


	public void setK(Reviews k) {
		this.k = k;
	}


	public String getOriginal_title() {
		return original_title;
	}


	public void setOriginal_title(String original_title) {
		this.original_title = original_title;
	}





	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}




	@Override
	public String toString() {
	   return "Film  [id Filma = " + id + ", naziv = "+original_title+" "+k+"]";
	}
 
}
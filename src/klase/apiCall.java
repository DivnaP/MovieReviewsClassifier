package klase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.impl.conn.BasicClientConnectionManager;

import org.apache.http.client.ClientProtocolException;

import org.apache.http.client.HttpClient;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;

import gui.MainFrame;
public class apiCall extends Thread{
	private volatile String result;
	 String text=null;
	 MainFrame p;
	public apiCall(String text, MainFrame p) {
		this.text=text;
		this.p=p;
	}
	
	public String returnResult(){
		
		
		return result;
	}
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
@Override 
public void run(){
	//load(text);
	
	   HttpClient client = new DefaultHttpClient();
		 
	   
	   String[] niz= text.split(" ");
	   String zaSlanje="";
	   for (int i = 0; i < niz.length; i++) {
		zaSlanje+=niz[i]+"+";
	}
	 
	 
	   
		//   HttpGet request2 = new HttpGet("https://api.themoviedb.org/3/movie/244786?api_key=8f3a57ac8761d7f8c7211f73d7170bb5");
	   HttpGet request2 = new HttpGet("https://api.idolondemand.com/1/api/sync/analyzesentiment/v1?text="+zaSlanje+"+&apikey=6996e750-60ec-44a6-8838-c84b716d2dac"
			   );   
		   HttpResponse response;
		try {
			response = client.execute(request2);
			   BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
			   final StringBuilder builder = new StringBuilder(50);
			   String line = "";
			
			   while ((line = rd.readLine()) != null) {
		
				   builder.append(line);
			 
			   }
			 
			   final  PositiveNegativeSentence results = new Gson().fromJson(builder.toString(), PositiveNegativeSentence.class);
			   result=results.toString();
			   System.out.println(results);
			//   p.textArea.setText(result);
			  
			   request2.completed();
			   request2.abort();
			   
			   
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
}
/*
	 public static void main(String[] args) throws ClientProtocolException, IOException {
		
	//	 BasicConfigurator.configure();
		 text="beautiful day, sunny very happy and bad feeling";
		
			   
			/*  
			   
		   HttpGet request = new HttpGet("https://api.themoviedb.org/3/movie/244786/reviews?api_key=8f3a57ac8761d7f8c7211f73d7170bb5");
		 
		 response = client.execute(request);
		
		   rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
		
		    line = "";
		 Kritika objK;
		   while ((line = rd.readLine()) != null) {
			   Gson gson = new Gson();
			   

					 objK = gson.fromJson(line, Kritika.class);
					 obj.k=objK;
			 
					System.out.println(obj);
		 
		   }
		 */
		  //}

}

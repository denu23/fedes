package com.endava.fedes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
//import org.codehaus.jackson.map.ObjectMapper;
/**
 * Base servlet for Federated Search.
 *
 * @author <a href="mailto:roxana.paduraru@endava.com">Roxana PADURARU</a>
 * @since 3/24/14
 */
public class FedesServlet extends HttpServlet  {
	
	/*public void toJSON(String name) throws ServletException, IOException, FileNotFoundException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("out.json"));
		BufferedWriter writer2 = new BufferedWriter(new FileWriter("out2.json"));
		//do the search here
		 URL firstserver = new URL("http://localhost:8081/tracktv?query=" + name);
         URLConnection yc = firstserver.openConnection();
         BufferedReader in = new BufferedReader(
                         new InputStreamReader(
                         yc.getInputStream()));
         
         String inputLine = in.readLine();
         writer.write(inputLine);
         writer.close();
         //System.out.println(inputLine);
         

         URL intermediateserver = new URL("http://localhost:8082/movies/"+name);
         URLConnection ic = intermediateserver.openConnection();
         BufferedReader in2 = new BufferedReader(
                         new InputStreamReader(
                         ic.getInputStream()));
         
         String inputLine2 = in2.readLine();
         //System.out.println(inputLine2);
         
         StringTokenizer str = new StringTokenizer(inputLine2,":\"}");
         String bun = new String();
         while(str.hasMoreElements()) {
        	 bun = str.nextToken();
         }
         
         try {
        	  Thread.sleep(5000);
        	} catch (InterruptedException ie) {
        	    //Handle exception
        	}
         URL secondserver = new URL("http://localhost:" + bun);
         System.out.println("http://localhost:" + bun);
         URLConnection sc = secondserver.openConnection();
         BufferedReader in22 = new BufferedReader(new InputStreamReader(sc.getInputStream()));
         
         String inLine22 = in22.readLine();
         fromJSON(inLine22);
         
         
         writer2.write(inLine22);
         writer2.flush();
		 writer2.close();
		
	}*/
	
	public void to_fromJSON1(String name) throws ServletException, IOException, FileNotFoundException {
		URL firstserver = new URL("http://localhost:8081/tracktv?query=" + name);
        URLConnection yc = firstserver.openConnection();
        BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                        yc.getInputStream()));
        
        String inputLine = in.readLine();
        ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		ArrayList<TVShows1> shows = new ArrayList<TVShows1>();
		JsonFactory f = new JsonFactory();
		JsonParser jp = f.createJsonParser(inputLine);
		  // advance stream to START_ARRAY first:
		jp.nextToken();
		  // and then each time, advance to opening START_OBJECT
		while (jp.nextToken() == JsonToken.START_OBJECT) {
			
		  TVShows1 foobar = mapper.readValue(jp, TVShows1.class);
		  shows.add(foobar);
		}
        //aici vom face fromJSON si vom returna arraylistul    
	}
	public void to_fromJSON3(String name) throws ServletException, IOException, FileNotFoundException {
		
		URL yah = new URL("http://localhost:8083/tvrage/query/" + name + "?callback=http%3A%2F%2Flocalhost%3A8080%2Ffedes%2Fintoarce");
		HttpURLConnection y = (HttpURLConnection) yah.openConnection();
		y.setRequestMethod("GET");
		BufferedReader i = new BufferedReader(new InputStreamReader(
				y.getInputStream()));
		String inputLin = i.readLine();
		
	}
	
	public ArrayList<TVShows> to_fromJSON2(String name) throws ServletException, IOException, FileNotFoundException {
		
		BufferedWriter writer2 = new BufferedWriter(new FileWriter("out2.json"));
		URL intermediateserver = new URL("http://localhost:8082/movies/"+name);
        URLConnection ic = intermediateserver.openConnection();
        BufferedReader in2 = new BufferedReader(
                        new InputStreamReader(
                        ic.getInputStream()));
        
        String inputLine2 = in2.readLine();
        //System.out.println(inputLine2);
        
        StringTokenizer str = new StringTokenizer(inputLine2,":\"}");
        String bun = new String();
        while(str.hasMoreElements()) {
       	 bun = str.nextToken();
        }
        
        try {
       	  Thread.sleep(5000);
       	} catch (InterruptedException ie) {
       	    //Handle exception
       	}
        URL secondserver = new URL("http://localhost:" + bun);
        System.out.println("http://localhost:" + bun);
        URLConnection sc = secondserver.openConnection();
        BufferedReader in22 = new BufferedReader(new InputStreamReader(sc.getInputStream()));
        String inLine22 = in22.readLine();
        
        //fromJSON(inLine22);
        ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		ArrayList<TVShows> shows = new ArrayList<TVShows>();
		JsonFactory f = new JsonFactory();
		JsonParser jp = f.createJsonParser(inLine22);
		  // advance stream to START_ARRAY first:
		jp.nextToken();
		  // and then each time, advance to opening START_OBJECT
		while (jp.nextToken() == JsonToken.START_OBJECT) {
		  TVShows foobar = mapper.readValue(jp, TVShows.class);
		  shows.add(foobar);
		}
		
		System.out.println(shows);
		//return show;
		
        writer2.write(inLine22);
        writer2.flush();
		writer2.close();
		return shows;
	}
	
	/*
	public ArrayList<TVShows> fromJSON(String s) throws JsonParseException, JsonMappingException, IOException {
		
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		ArrayList<TVShows> shows = new ArrayList<TVShows>();
		JsonFactory f = new JsonFactory();
		  JsonParser jp = f.createJsonParser(s);
		  // advance stream to START_ARRAY first:
		  jp.nextToken();
		  // and then each time, advance to opening START_OBJECT
		  while (jp.nextToken() == JsonToken.START_OBJECT) {
		    TVShows foobar = mapper.readValue(jp, TVShows.class);
		    shows.add(foobar);
		    
		  }
		System.out.println(shows);
		//return show;
		return shows;
	}*/
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FileNotFoundException {
		String name = request.getParameter("searchTerms");
		
		//to_fromJSON2(name);
		to_fromJSON3(name);
		//System.out.println(fromJSON());
		request.setAttribute("results", "one result");
		request.getRequestDispatcher("/WEB-INF/results.jsp").forward(request, response);
	}
	
	
}

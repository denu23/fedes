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
import java.util.HashMap;
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
	
	public ArrayList<TVShows1> to_fromJSON1(String name) throws ServletException, IOException, FileNotFoundException {
		BufferedWriter writer2 = new BufferedWriter(new FileWriter("out1.json"));
		URL firstserver = new URL("http://localhost:8081/tracktv?query=" + name);
        URLConnection yc = firstserver.openConnection();
        
        BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                        yc.getInputStream()));
        String inputLine = in.readLine();
        inputLine = inputLine.substring(inputLine.indexOf('['),inputLine.length() - 1);
        writer2.write(inputLine);
        writer2.flush();
        writer2.close();
        
        //System.out.println(inputLine);
        ArrayList<TVShows1> shows = new ArrayList<TVShows1>();
        ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		
        JsonFactory f = new JsonFactory();
		JsonParser jp = f.createJsonParser(inputLine);
		  // advance stream to START_ARRAY first:
		jp.nextToken();
		  // and then each time, advance to opening START_OBJECT
		while (jp.nextToken() == JsonToken.START_OBJECT) {
			
			TVShows1 foobar = mapper.readValue(jp, TVShows1.class);
			shows.add(foobar);
			//System.out.println(foobar);
		}
		return shows;
        //aici vom face fromJSON si vom returna arraylistul    
	}
	public void to_fromJSON3(String name) throws ServletException, IOException, FileNotFoundException {
		
		URL yah = new URL("http://localhost:8083/tvrage/query/" + name + "?callback=http%3A%2F%2Flocalhost%3A8080%2Ffedes%2Fintoarce");
		HttpURLConnection y = (HttpURLConnection) yah.openConnection();
		y.setRequestMethod("GET");
		BufferedReader i = new BufferedReader(new InputStreamReader(y.getInputStream()));
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
       	  Thread.sleep(4000);
       	} catch (InterruptedException ie) {
       	    //Handle exception
       	}
        URL secondserver = new URL("http://localhost:" + bun);
        System.out.println("http://localhost:" + bun);
        URLConnection sc = secondserver.openConnection();
        BufferedReader in22 = new BufferedReader(new InputStreamReader(sc.getInputStream()));
        String inLine22 = in22.readLine();
        
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
		
		
        writer2.write(inLine22);
        writer2.flush();
		writer2.close();
		return shows;
	}
	
	
	public HashMap<String,Show> searchResults(String name) throws FileNotFoundException, ServletException, IOException {
		
		
		String splitedName [] = name.split(" ");
		to_fromJSON3(splitedName[0]);
		ArrayList<TVShows1> t1 = to_fromJSON1(splitedName[0]);
		ArrayList<TVShows> t2 = to_fromJSON2(splitedName[0]);
		
		/** Caut toate rezultatele ce contin primul cuvant din search box si creez un
		 *  HashMap<String, Show> cu ele
		 */
		HashMap<String,Show> hShow = new HashMap<String,Show>();
		for(TVShows1 t:t1) {
			Show obj = new Show(t.title,t.year,t.genres.toString(),t.overview,
					t.people.actors.toString(),"http://imdb.com/title/" + t.imdb_id,
					"unavailable",t.status,t.country,t.poster,t.air_day,t.network);
				hShow.put(t.title, obj);
		}
		
		for(TVShows t:t2) {
			if(!hShow.containsKey(t.Title)) {
				Show obj = new Show(t.Title,t.Year,t.Genre,t.Plot,t.Actors,
						"http://imdb.com/title/" +t.imdbID,t.imdbRating,"",t.Country,t.Poster,t.Released,"");
				hShow.put(t.Title, obj);
			}
		}
		
		for(TVShows3 t:IntoarceServlet.tv3) {
			if(!hShow.containsKey(t.showname)) {
				Show obj = new Show(t.showname,t.startdate,"","","",t.showlink,
						"unavailable",t.classification,t.origin_country,"",t.airday,t.network.toString());
			}
		}
		/** Am terminat cautarea dupa primul cuvant din search box */
		
		if(splitedName.length == 1) {
			return hShow;
		}
		
		HashMap<String,Show> hShowCopy = (HashMap<String,Show>)hShow.clone();
		
		for(int i = 1; i < splitedName.length; i++) {
			for(String s : hShowCopy.keySet()) {
				if(hShow.isEmpty())
					return hShow;
				if(hShow.containsKey(s) && (!exist(hShow.get(s), splitedName[i]))) {
					hShow.remove(s);
				}
			}
		}
		
		return hShow;
	}
	
	public static boolean exist(Show show, String token) {
		
		return show.title.contains(token) || show.actors.contains(token) ||
				show.genres.contains(token) || show.plot.contains(token) ||
				show.year.contains(token);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FileNotFoundException {
		
		String name = request.getParameter("searchTerms");
		request.setAttribute("results", searchResults(name));
		request.getRequestDispatcher("/WEB-INF/results.jsp").forward(request, response);
	}
	
	
}

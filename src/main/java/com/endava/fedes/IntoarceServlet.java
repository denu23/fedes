package com.endava.fedes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.ObjectMapper;

public class IntoarceServlet extends HttpServlet {
	public static ArrayList<TVShows3> tv3 = new ArrayList<TVShows3>();
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BufferedWriter writer = new BufferedWriter(new FileWriter("out3.json"));
		String name = request.getParameter("searchTerms");
		BufferedReader i = new BufferedReader(new InputStreamReader(
				request.getInputStream()));
		String inputLin = i.readLine();
		//while((inputLin = i.readLine()) != null) { 
		writer.write(inputLin);
		writer.flush();
		writer.close();
		
		fromJSON3(inputLin);
		
		request.setAttribute("results", "one result");
		request.getRequestDispatcher("/WEB-INF/results.jsp").forward(request, response);
	}
	
	public void fromJSON3(String s) throws JsonParseException, IOException {
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		//facem obiect
		JsonFactory f = new JsonFactory();
		JsonParser jp = f.createJsonParser(s);

		// advance stream to START_ARRAY first:
		jp.nextToken();
		// and then each time, advance to opening START_OBJECT
		while (jp.nextToken() == JsonToken.START_OBJECT) {
			TVShows3 foobar = mapper.readValue(jp, TVShows3.class);
			tv3.add(foobar);
		}
		System.out.println("e de aici\n" + tv3);
		System.out.flush();
		//return shows;
	}
}
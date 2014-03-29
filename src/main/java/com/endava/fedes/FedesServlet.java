package com.endava.fedes;

import java.io.BufferedReader;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Base servlet for Federated Search.
 *
 * @author <a href="mailto:roxana.paduraru@endava.com">Roxana PADURARU</a>
 * @since 3/24/14
 */
public class FedesServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("searchTerms");
                
                URL yahoo = new URL("http://localhost:8081/tracktv?query=" + name);
                URLConnection yc = yahoo.openConnection();
                BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                yc.getInputStream()));
                String inputLine = in.readLine();
                
                System.out.println(inputLine);
		//do the search here

		//end the search here

		request.setAttribute("results", "one result");
		request.getRequestDispatcher("/WEB-INF/results.jsp").forward(request, response);
	}
}

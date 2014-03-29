package com.endava.fedes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IntoarceServlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		BufferedWriter writer = new BufferedWriter(new FileWriter("out3.json"));
		String name = request.getParameter("searchTerms");
		BufferedReader i = new BufferedReader(new InputStreamReader(
				request.getInputStream()));
		String inputLin;
		while((inputLin = i.readLine()) != null) { 
			writer.write(inputLin);
			System.out.println(inputLin);
		}
        writer.flush();
		writer.close();
		
		request.setAttribute("results", "one result");
		request.getRequestDispatcher("/WEB-INF/results.jsp").forward(request,
				response);
	}
}
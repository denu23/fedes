package com.endava.fedes;

import java.util.ArrayList;

public class Show {
	public Show(String title,String year,String genres,String plot,String actors, String link,
			String imdbRating,String status,String country,String poster,String airday,String network) {
		this.title = title;
		this.poster = poster;
		this.year = year;
		this.actors = actors;
		this.genres = genres;
		this.plot = plot;
		this.link = link;
		this.imdbRating = imdbRating;
		this.status = status;
		this.country = country;
		this.airday = airday;
		this.network = network;
	}
	
	public String link;
	public String title;
	public String year;
	public String genres;
	public String plot;
	public String actors;
	public String imdbRating;
	public String status;
	public String country;
	public String poster;
	public String airday;
	public String network;
	
	public String toString() {
		return "\n\nFinal\n" + title + "; " + year + "; " + genres + "; " + plot + "; " + actors ;
	}
}

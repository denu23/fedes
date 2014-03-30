package com.endava.fedes;

import java.util.List;
import java.util.ArrayList;

/*class Name {
	String name;
}*/
class Actor{
	public String name;
	
	public String toString() {
		if(name != null)
		return name;
		else return "";
	}
}
class People {
	public List<Actor> actors;
	
}

/*class People {
	public Actors actors;
}*/

class Episodes {
	public String plays;
	public String season;
	public String number;
	public String title;
	public String url;
}

public class TVShows1 {
	public String title;
	public String year;
	public String url;
	public String country;
	public String overview;
	public String status;
	public String network;
	public String poster;
	public List<String> genres;
	public People people;
	public String air_day;
	public String air_time;
	public String imdb_id;
	public String tvrage_id;
	public List<Episodes> top_episodes;
	
	public String toString() {
		return "\n\nServer1\n" + title;
	}
}


	


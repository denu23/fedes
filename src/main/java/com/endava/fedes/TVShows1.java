package com.endava.fedes;

import java.util.ArrayList;

class Actors {
	public String name;
}
class People {
	public ArrayList<Actors> actors;
}
class Episodes {
	public String plays;
	public String season;
	public String number;
	public String title;
	public String url;
}

class Content {
	public String title;
	public String year;
	public String url;
	public String country;
	public String overview;
	public String status;
	public String network;
	public String poster;
	public String genres;
	public ArrayList<People> people;
	public String air_day;
	public String air_time;
	public String imdb_id;
	public String tvrage_id;
	public ArrayList<Episodes> top_episodes;
}

public class TVShows1 {
	public int no_entries;
	public Content[] content;
}

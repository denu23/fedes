package com.endava.fedes;

class TVShows3 {
	public String 
		airtime,
		showid,
		status,
		runtime,
		airday,
		origin_country,
		timezone,
		startdate,
		seasons,
		showname,
		classification,
		started,
		showlink,
		akas;
	public Network network;
	public String toString() {
		return airtime + " " + showname + ";";
	}
	
}

class Network {
	public String
		content,
		country;
	public String toString() {
		return content + " " + country;
	}
}

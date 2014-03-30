<%@page import="com.endava.fedes.Show"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="hover.js"></script>

<html>
	<head>
	
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Seriale</title>
		<link href="style.css" rel="stylesheet" type="text/css" />

		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js">
		</script>
		<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript">

		
		</script>
  		<script src="main.js" type="text/javascript"></script>  
		
	</head>

<body>
	<div class="header">
		<div class="title">
			<ul>
				<li>S</li>
				<li>E</li>
				<li>R</li>
				<li>I</li>
				<li>A</li>
				<li>L</li>
				<li>E</li>
			</ul>
		</div>
	
	<%
		java.util.HashMap <String,Show>test = (java.util.HashMap <String,Show>)request.getAttribute("results");
		String lista;
		if(test.size() != 0) {
			lista = "<div id='cssmenu'><ul><li><a href='#'><span>Rezultate</span></a></li>";
			for(String s : test.keySet()) {
				Show value = test.get(s);
				lista += "<li><a href='#'><span>" + value.title + " (" + value.year + ")" + "</span></a><ul>";
				lista += "<li><a href='#'>Genres: " + value.genres + "</a></li>";
				lista += "<li><a href='#'>Actors: " + value.actors.substring(1, value.actors.length() - 1) + "</a></li>";
				lista += "<li><a href='#'>Plot: " + value.plot + "</a></li>";
				lista += "<li><a href='#'>Country: " + value.country + "</a></li>";
				lista += "<li><a href=" + value.poster + ">Poster:</a></li>";
				lista += "<li><a href='#'>Status: " + value.status + "</a></li>";
				lista += "<li><a href='" + value.link + "'>Imdb Rating: " + value.imdbRating + "</a></li>";
				lista += "<li><a href='#'>Airday: " + value.airday + "</a></li>";
				lista += "<li><a href='#'>Network: " + value.network + "</a></li>";
				lista += "</ul></li>";
			}
		}
		else {
			lista = "<div id='cssmenu'><ul><li><a href='#'><span>Nu s-a gasit niciun rezultat :(</span></a></li>";
		}
		lista += "</ul></div>";
		out.print(lista);
	%>
</body>
</html>

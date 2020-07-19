<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page  import = "Entities.candidate" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
<title>Voting</title>
</head>

<body>

<input type="hidden" name="token" value="<%= request.getParameter("token") %>" />

<% 
String token =  request.getParameter("token");

request.setAttribute("token", token);
%>

<h1> Please Vote the Respective Candidate!</h1>

	<div class="container">		
			<div class="row">
			<div class="col-md-2"><h3>First Name</h3></div>
			<div class="col-md-2"><h3>Last Name</h3></div>
			<div class="col-md-2"><h3>Faculty</h3></div>
	</div>
	
	<%  
		List<candidate> candlist = (List<candidate>) request.getAttribute("candidatelist");
		
		for(candidate div: candlist) { 
		%>
		<div class="row">
			<div class="col-md-2"><h4><%= div.getFirstname() %></h4></div>
			<div class="col-md-2"><h4><%= div.getSurname() %></h4></div>
			<div class="col-md-2"><h4><%= div.getFaculty() %></h4></div>
			<div class="col-md-4">
				<div class="btn-group" role="group">
				<a href="/assignvote?cand_id=<%= div.getId() %>&token=<%= request.getParameter("token") %>" class="btn btn-primary">Vote</a>
					
				</div>
			</div>			
		</div>
			<% } %>
			
</div>

</body>
</html>
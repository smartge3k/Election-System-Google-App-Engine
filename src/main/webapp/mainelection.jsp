<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.*" %>
<%@ page  import = "Entities.candidate" %>
<%@ page  import = "Entities.Election" %>


<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
<title>Administrator Dashboard</title>
</head>

<body>

	<div class="container">		
			<div class="row">
			<div class="col-md-2"><h3>Election Details</h3></div>
			<div class="col-md-2"><h3>Start Date</h3></div>
			<div class="col-md-2"><h3>End Date</h3></div>
			
	</div>
	
	<%  
		List<Election> check = (List<Election>) request.getAttribute("electionlist");
		
		for(Election div: check) { 
		%>
		<div class="row">
			<div class="col-md-2"><h4><%= div.getName() %></h4></div>
			<div class="col-md-2"><h4><%= div.getStartTime()%></h4></div>
			<div class="col-md-2"><h4><%= div.getEndTime() %></h4></div>
			
		</div>
			<% } %>
			
     
<a href="/admin/addelection" class="btn btn-primary">Set Election Properties</a>
</div>


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
					<a href="/admin/editcandidate?div_id=<%= div.getId() %>" class="btn btn-primary">Edit candidate</a>
					<a href="/admin/deletecandidate?div_id=<%= div.getId() %>" class="delete btn btn-danger">Delete Candidate</a>
				
				</div>
			</div>			
		</div>
			<% } %>
			
     
<a href="/admin/addCandidate" class="btn btn-primary">Add Candidate</a>
</div>
<br> <br>
<div class="container" >		
<a href="/admin/importvoters" style="margin:auto;text-alig:center;" class="btn btn-primary">Import Voters</a>
<a href="/admin/ReminderEmail" style="margin:auto;text-alig:center;" class="btn btn-primary">Reminder Email</a>
<a href="/costvote" style="margin:auto;text-alig:center;" class="btn btn-primary">Cost Vote</a>
<a href="/electionresults" style="margin:auto;text-alig:center;" class="btn btn-primary">Election Results</a>

</div>
</body>
</html>
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
<title>Summary</title>
</head>
<body>
<div class="container">		
			<div class="row">
			<div class="col-md-2"><h3>First Name</h3></div>
			<div class="col-md-2"><h3>Last Name</h3></div>
			<div class="col-md-2"><h3>Faculty</h3></div>
			<div class="col-md-2"><h3>Votes Costed</h3></div>	
	</div>
	<%  
		List<candidate> candlist = (List<candidate>) request.getAttribute("cadidate");
		
		for(candidate div: candlist) { 
		%>
		<div class="row">
			<div class="col-md-2"><h4><%= div.getFirstname() %></h4></div>
			<div class="col-md-2"><h4><%= div.getSurname() %></h4></div>
			<div class="col-md-2"><h4><%= div.getFaculty() %></h4></div>
			<div class="col-md-2"><h4><%= div.getCostedVote() %></h4></div>			
		</div>
			<% } %>
			<br> <br>
</div>
			
<div class="container" >
<div class="row">		
<div class="col-md-2"><h3>Total Voters</h3></div>
<div class="col-md-2"><h3>Costed Votes</h3></div>
<div class="col-md-2"><h3>Percentage</h3></div>

</div>

<div class="row">
			<div class="col-md-2"><h4><%= request.getAttribute("totalVoters") %></h4></div>
			<div class="col-md-2"><h4><%= request.getAttribute("totalCostedVote")%></h4></div>
			<div class="col-md-2"><h4><%= request.getAttribute("percentage") %> %</h4></div>
			
</div>

</div>

<div class="container" >
	<a href="/admin/dashboard" class="btn btn-primary">Admin Panel</a>
</div>		
</body>
</html>
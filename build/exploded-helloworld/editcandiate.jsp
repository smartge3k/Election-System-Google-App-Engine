<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
<%@ page  import = "Entities.candidate" %>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

<title>Edit Candidate</title>
</head>
<body>
<div class="container">
		<h1>Edit Beverage</h1>
		
		<p>&nbsp;</p>
		
		<%  candidate div = (candidate)request.getAttribute("candi"); %>
		
		<form role="form" action="/admin/editcandidate" method="post">
			<input type="hidden" name="div_id" value="<%= div.getId() %>">
			
			
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">First Name</span>
					<input name="div_fname" type="text" class="form-control" value="<%= div.getFirstname() %>" required/>
				</div>
			</div>
			
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">Last Name</span>
					<input name="div_lname" type="text" class="form-control" value="<%= div.getSurname() %>" required/>
				</div>
			</div>
			
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">Faculty</span>
					<input name="div_fclty" type="text" class="form-control" value="<%= div.getFaculty() %>" required/>
				</div>
			</div>

						

			<a href="/admin/dashboard" class="btn btn-default">Cancel</a>
			<button type="submit" class="btn btn-success">Save</button>
		</form>					
		
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Candidate</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
<h1>New Candidate</h1>

		<p>&nbsp;</p>
		
<form role="form" action="/admin/addCandidate" method="post">
			
			<div class="form-group">
				<div class="input-group">
				
					<input type="hidden" name="div_bvg" type="number" class="form-control" value="0" required/>
				</div>
			</div>
			
			Enter the Candidates Details: 
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">First Name</span>
					<input name="div_fname" type="text" class="form-control" required/>
				</div>
				<div class="input-group">
					<span class="input-group-addon">Last Name</span>
					<input name="div_lname" type="text" class="form-control" required/>
				</div>
				<div class="input-group">
					<span class="input-group-addon">Faculty</span>
					<input name="div_faculty" type="text" class="form-control" required/>
				</div>
			</div>
			
			<a href="/admin/dashboard" class="btn btn-default">Cancel</a>
			<button type="submit" class="btn btn-success">Save</button>
			<a href="/admin/importvoters" class="btn btn-default">Import Voters</a>
			
		</form>	
		</div>
		
</body>
</html>
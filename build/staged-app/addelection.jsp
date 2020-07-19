<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Election</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
<h1>New Election</h1>


  <span  class= "input-group-addon" >Please Add Election </span><br>

		<p>&nbsp;</p>
		
<form role="form" action="/admin/addelection" method="post">
			
			<div class="form-group">
				<div class="input-group">
				
					<input type="hidden" name="div_bvg" type="number" class="form-control" value="0" required/>
				</div>
			</div>
			
			Enter the Election Details: 
			<div class="form-group" style = "margin-right: 800px;">
				<div class="input-group" >
					<span class="input-group-addon">Election Name</span>
					<input name="div_name" type="text" class="form-control" required/>
				</div>
				<div class="input-group">
					<span class="input-group-addon">Start Date</span>
					<input name="div_startdate" type="datetime-local" value="2019-01-01 16:14" class="form-control" required/>
				</div>
		
				<div class="input-group">
					<span class="input-group-addon">End Date</span>
					<input name="div_enddate" type="datetime-local" value="2019-03-03 16:14" class="form-control" required/>
				</div>
			
			</div>
			
			<a href="/admin/dashboard" class="btn btn-default">Cancel</a>
			<button type="submit" class="btn btn-success">Save</button>
			
		</form>	
<br> <br>
<div class="container" >		
<a href="/admin/importvoters" style="margin:auto;text-alig:center;" class="btn btn-primary">Import Voters</a>
<a href="/admin/addCandidate" style="margin:auto;text-alig:center;" class="btn btn-primary">Add Candidate</a>
</div>

</div>
</body>
</html>
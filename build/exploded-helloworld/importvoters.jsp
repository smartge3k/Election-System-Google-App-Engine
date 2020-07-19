<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>

<title>Import Voters</title>
</head>
<body>

<%  String div = (String)request.getAttribute("errormessage");
 if (div ==null){ %>
	 <span  class= "input-group-addon" > Import Students Email as text file. Please add Election first if you have not added then do the import</span><br>
	 
 <% }else { %>
  <span  class= "input-group-addon" >Please Upload a nonempty or right file</span><br>
   <% }
 
 %>




<form role="form" action="/admin/importvoters" method="post" enctype="multipart/form-data" >

<div class="form-group" >

<input type="file" name="Upload" />


</div>

<button type="submit" class="btn btn-success"> Upload</button>
<a href="/admin/dashboard" class="btn btn-primary">Cancel</a>
</form>


</body>
</html>





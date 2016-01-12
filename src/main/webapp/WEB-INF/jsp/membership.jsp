<%@  page  language="java" contentType="text/html; charset=ISO-8859-1"
 
pageEncoding="ISO-8859-1"%>

<%@  taglib  prefix="form" uri="http://www.springframework.org/tags/form"%>
 
<!DOCTYPE html  PUBLIC  "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<!-- meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"-->

<title>Spring3Example</title>

  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  <script src="//raw.github.com/botmonster/jquery-bootpag/master/lib/jquery.bootpag.min.js"></script>

  <script src="resources/js/prasweb.js"></script>
    

<script>
$(document).ready(function(){	
$.get('membership/list',null,function(responseText) { 
	
	 for(var i=0;i<responseText.data.length;i++) {
		 $("#tab tbody").append(
		    "<tr>" +
		   "<td> <a href= membership/"+responseText.data[i].id + ">"+responseText.data[i].firstName +"</a></td> " +
        
        "<td>"+ responseText.data[i].lastName + "</td> " +
        
        "<td>"+ responseText.data[i].dob + "</td> " +
        
        "<td>"+ responseText.data[i].genderId.description + "</td>"+
        
        "<td>"+ responseText.data[i].countyCode.description + "</td> "+
        
        "<td>"+ responseText.data[i].fileId + "</td> "+
        
        "<td>"+ responseText.data[i].status.description + "</td></tr> "      );
		 
	 }
	 
   });
   
prasPagination();

});
   

</script>
 </head>

<body> 

<form:form action="membership.html" commandName="membership" >
  <h2>Membership Details</h2>
  <div class="panel-group">
    <div class="panel panel-primary">
      <div class="panel-heading">Membership Details</div>
      <div class="panel-body" id="tablediv">
         
         
		<table id="tab" class="table table-striped table-hover"> 
			<thead> 
				<tr>
	 				<th  scope="col">First Name</th>  
			        <th  scope="col">Last Name</th>  
			        <th  scope="col">Date Of Birth</th>  
			        <th  scope="col">Gender</th>  
			        <th  scope="col">County</th>
			        <th  scope="col">File Number</th>  
			        <th  scope="col">Status</th>  
				</tr>   
			</thead> 
			
			<tbody id="content"> 
				<div id="show_per_page"></div>
				<div id="current_page"></div>
			</tbody> 
			
			<tfoot > 
				<div class="col-md-12 text-center" id="page_navigation"> </div>
			</tfoot> 
			
		</table> 
 
    </div>
    </div>
  </div>

</form:form>
      	
</body>
</html>
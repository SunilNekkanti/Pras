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
  
</head>

<body>

<form:form action="membership.html" commandName="membership">


<script>
var xmlhttp = new XMLHttpRequest();
var url = "http://localhost:8080/Pras/membership/";
 xmlhttp.onreadystatechange=function() {
    if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
        myFunction(xmlhttp.responseText);
    }
}
 xmlhttp.open("GET", url, true);
xmlhttp.send();

function myFunction(response) {
    var arr = JSON.parse(response);
    var out = "";
  //  alert("<arr.data length is"+arr.data.length)
  //  alert("<arr.data 1 is"+arr.data[0].firstName)
      for(var i=0;i<arr.data.length;i++){
        out += "<tr><td>" +
        
        arr.data[i].firstName + "</td> " +
        
        "<td>"+
        arr.data[i].lastName + "</td> " +
        
        "<td>"+
        arr.data[i].dob + "</td> " +
        
        "<td>"+
        arr.data[i].genderId.description + "</td>"+
        
        "<td>"+
        arr.data[i].countyCode.description + "</td> "+
        
        "<td>"+
        arr.data[i].fileId + "</td> "+
        
        "<td>"+
        arr.data[i].status.description + "</td></tr> ";
      }
    
    document.getElementById("loadHere").innerHTML = out; 
    
}


 $(document).ready(function() {
	
$.get('membership',null,function(responseText) { 
	 for(var i=0;i<responseText.data.length;i++) {
		 $("#tab tbody").append(
		    "<tr>" +
		   "<td>"+ responseText.data[i].firstName + "</td> " +
        
        "<td>"+ responseText.data[i].lastName + "</td> " +
        
        "<td>"+ responseText.data[i].dob + "</td> " +
        
        "<td>"+ responseText.data[i].genderId.description + "</td>"+
        
        "<td>"+ responseText.data[i].countyCode.description + "</td> "+
        
        "<td>"+ responseText.data[i].fileId + "</td> "+
        
        "<td>"+ responseText.data[i].status.description + "</td></tr> "      );
	 }
   });


}); 


</script>
 
</head> 

<div class="container">
  <h2>Membership Details</h2>
  <div class="panel-group">
    <div class="panel panel-primary">
      <div class="panel-heading">Membership Details</div>
      <div class="panel-body">
         
         
<table id="tab" class="table table-striped"> 
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
<tbody id="loadHere"> 
</tbody> 
<tfoot> 
<td  colspan="4"><ul id="paging" class="pagination"> 
</ul></td> 
</tfoot> 
</table> 
   

         
         
      </div>
    </div>
  </div>
</div>

</form:form>
</body>
</html>
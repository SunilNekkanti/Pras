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

<div id="id01">

</div>

<script>
var xmlhttp = new XMLHttpRequest();
var url = "http://localhost:8080/Pras/membership/161";
 xmlhttp.onreadystatechange=function() {
    if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
        myFunction(xmlhttp.responseText);
    }
}
 xmlhttp.open("GET", url, true);
xmlhttp.send();

function myFunction(response) {
    var arr = JSON.parse(response);
    var i;
    var out = "<table>";

        out += "<tr><td>First Name:<FONT color=\"red\"><form:errors   path='firstName' /></FONT></td><td>" +
        
        arr["data"].firstName + "</td> </tr>" +
        
        "<td>Last Name:<FONT color=\"red\"><form:errors   path='lastName' /></FONT></td><td>"+
        arr["data"].lastName + "</td> </tr>" +
        
        "<td>Date of Birth:<FONT color=\"red\"><form:errors   path='lastName' /></FONT></td><td>"+
        arr["data"].dob + "</td> </tr>" +
        
        "<td>Gender:<FONT color=\"red\"><form:errors   path='lastName' /></FONT></td><td>"+
        arr["data"].genderDescription + "</td> </tr>"+
        
        "<td>County:<FONT color=\"red\"><form:errors   path='county' /></FONT></td><td>"+
        arr["data"].countyDescription + "</td> </tr>"+
        
        "<td>File Id:<FONT color=\"red\"><form:errors   path='fileId' /></FONT></td><td>"+
        arr["data"].fileId + "</td> </tr>"+
        
        "<td>Status:<FONT color=\"red\"><form:errors   path='status' /></FONT></td><td>"+
        arr["data"].statusDescription + "</td> </tr>";
        
    
        out += "</table>";
    document.getElementById("id01").innerHTML = out;
}
</script>

 

 
</form:form>
</body>

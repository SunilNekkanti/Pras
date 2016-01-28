<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>   
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  
"http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title><tiles:insertAttribute name="title" ignore="true" /></title>  

<head>
  <title>Physicians' First Choice</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <link rel="stylesheet" href="/resources/css/prasPanel.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  
  <style>
    /* Remove the navbar's default margin-bottom and rounded borders */ 
    .navbar {
      margin-bottom: 0;
      border-radius: 0;
      padding: 15px;
    }
    
    /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
    .row.content {height: 85%}
    
    /* Set gray background color and 100% height */
    .sidenav {
      padding-top: 20px;
      background-color: #f1f1f1;
      height: 100%;
    }
    
    /* Set black background color, white text and some padding */
    footer {
      background-color: #555;
      color: white;
      padding: 15px;
    }
    
    /* On small screens, set height to 'auto' for sidenav and grid */
    @media screen and (max-width: 767px) {
      .sidenav {
        height: auto;
        padding: 15px;
      }
      .row.content {height:auto;} 
    }
  </style>

</head>  
<body>  
<script>

$(document).ready(function(){	
	alert("before tab");

	$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        localStorage.setItem('lastTab', $(this).attr('href'));
    });

    var lastTab = localStorage.getItem('lastTab');
    if (lastTab) {
        $('[href="' + lastTab + '"]').tab('show');
    }


$('[data-toggle="tab"]').click(function(e) {
    e.preventDefault();
    var loadurl = $(this).attr('href');
    var targ = $(this).attr('data-target');
    alert("inside tab loadurl "+loadurl);
    $.get(loadurl, function(data) {
        $(targ).html(data);

    });
    $(this).tab('show')
});

});
</script>
       <div> <tiles:insertAttribute name="header" /> </div> 
       <div> <tiles:insertAttribute name="menubar" /> </div>  
       <div class="container-fluid text-center">    
  			<div class="row content" style="padding:25px;">
    			<div class="col-sm-12 text-left"><tiles:insertAttribute name="body" /></div>  
    		</div>
      </div>
      <div ><tiles:insertAttribute name="footer" /></div>  
  
</body>  
</html>
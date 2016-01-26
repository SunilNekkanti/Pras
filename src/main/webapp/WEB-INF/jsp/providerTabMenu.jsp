<%@  page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
		
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@  taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html  PUBLIC  "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<!-- meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"-->

<title>Spring3Example</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script
	src="//raw.github.com/botmonster/jquery-bootpag/master/lib/jquery.bootpag.min.js"></script>

<script src="/Pras/resources/js/prasweb.js"></script>



</head>

<body>

<div class="panel with-nav-tabs panel-primary">
		<div class="panel-heading">
						<ul class="nav nav-tabs">
                            <li ><a data-target="#profile" href="/Pras/membership/display/${id}" data-toggle="tab">Profile</a></li>
                            <li class="active"><a data-target="#other" href="/Pras/membership/details/display/${id}" data-toggle="tab">Other Details</a></li>
                            <li><a data-target="#contact"  href="/Pras/membership/${id}/contact" data-toggle="tab">Contact</a></li>
                            <li><a data-target="#problem"  href="/Pras/membership/${id}/problem" data-toggle="tab">Problem</a></li>
                        </ul>
          </div>
			
			<div class="panel-body" id="tablediv">
				<div class="tab-content">
				 <div class="tab-pane" id="profile">
				    home tab!
				  </div>
				  <div class="tab-pane" id="other">
				    profile tab!
				  </div>
				<div class="tab-pane" id="contact">
				    profile tab!
				  </div>
				  <div class="tab-pane" id="problem">
				    profile tab!
				  </div>
	
				</div>
			</div>
		</div>


</body>
</html>
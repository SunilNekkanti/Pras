<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
    prefix="springForm"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
                            <li class="active"><a href="#" data-toggle="tab">Profile</a></li>
                        </ul>
          </div>
		<div class="panel-body" id="tablediv">


			<springForm:form method="POST" commandName="membership" action="save.do" class="form-horizontal" role="form">
				<div class="form-group">
					<label class="control-label col-sm-2" for="firstName">First Name:</label>
					<div class="col-sm-6">
						<springForm:hidden path="id" />
						<springForm:input path="firstName" class="form-control" id="firstName" placeholder="First Name" />
						<springForm:errors path="firstName" cssClass="error" />
					</div>
				</div>
				 		 
				<div class="form-group">
					<label class="control-label col-sm-2" for="lastName">Last Name:</label>
					<div class="col-sm-6">
						<springForm:input path="lastName" class="form-control" id="lastName" placeholder="Last Name" />
						<springForm:errors path="lastName" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
				 	<label class="control-label col-sm-2" for="dob">DOB:</label>
					<div class="col-sm-6">
						<springForm:input path="dob" class="form-control" id="dob" placeholder="DOB" />
						<springForm:errors path="dob" cssClass="error" />
					</div>
				</div>
				 
				<div class="form-group">
					<label class="control-label col-sm-2" for="status">Status:</label>
					<div class="col-sm-6">
						<springForm:select path="status.id" class="form-control" id="status">
				    		<springForm:options items="${statusMap}"    />
						</springForm:select>
						<springForm:errors path="status.description" cssClass="error" />
					  </div>
				</div>
				 		 
				<div class="form-group">
					<label class="control-label col-sm-2" for="gender">Gender:</label>
					<div class="col-sm-6">
						<springForm:select path="genderId.id"  class="form-control" id="gender">
				    		<springForm:options items="${genderMap}"    />
						</springForm:select>
						<springForm:errors path="genderId.description" cssClass="error" />
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-2" for="county">County:</label>
					<div class="col-sm-6">
						<springForm:select path="countyCode.code" class="form-control" id="county" >
				    		<springForm:options items="${countyMap}"    />
						</springForm:select>
						<springForm:errors path="countyCode.description" cssClass="error" />
					</div>
				</div>
				 		 
				<div class="col-sm-offset-6 col-sm-4">
				<a href="http://localhost:8080/Pras/membership/${id}">Click Here</a> Edit membership profile
				</div>
			</springForm:form>
 	</div>
	</div>
</body>
</html>
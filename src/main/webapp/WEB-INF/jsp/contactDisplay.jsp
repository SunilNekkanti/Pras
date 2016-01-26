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

<script src="resources/js/prasweb.js"></script>



</head>

<body>
<div class="panel with-nav-tabs panel-primary">
		<div class="panel-heading">
						<ul class="nav nav-tabs">
                            <li class="active"><a href="#" data-toggle="tab">Contact</a></li>
                        </ul>
          </div>
		<div class="panel-body" id="tablediv">


			<springForm:form method="POST" commandName="contact" action="save.do" class="form-horizontal" role="form">
				<div class="form-group">
					<label class="control-label col-sm-2" for="homePhone">Home Phone:</label>
					<div class="col-sm-6">
						<springForm:hidden path="id" />
						<springForm:input path="homePhone" class="form-control" id="homePhone" placeholder="Home Phone" />
						<springForm:errors path="homePhone" cssClass="error" />
					</div>
				</div>
				 		 
				<div class="form-group">
					<label class="control-label col-sm-2" for="mobilePhone">Mobile Phone:</label>
					<div class="col-sm-6">
						<springForm:input path="mobilePhone" class="form-control" id="mobilePhone" placeholder="Mobile Phone" />
						<springForm:errors path="mobilePhone" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
				 	<label class="control-label col-sm-2" for="faxNumber">Fax Number:</label>
					<div class="col-sm-6">
						<springForm:input path="faxNumber" class="form-control" id="dob" placeholder="Fax Number" />
						<springForm:errors path="faxNumber" cssClass="error" />
					</div>
				</div>
				 
				<div class="col-sm-offset-6 col-sm-4">
				<c:choose>
					 	<c:when test="${contact.refContact.mbr != null}"> 
					 		<a href="http://localhost:8080/Pras/membership/${contact.refContact.mbr.id}/contactList">Click Here</a>Membership Contact List
						 </c:when>
						 <c:when test="${contact.refContact.prvdr != null}">
							<a href="http://localhost:8080/Pras/provider/${contact.refContact.prvdr.id}/contactList">Click Here</a> Provider Contact List
						</c:when>
						 <c:when test="${contact.refContact.ins != null}">
							<a href="http://localhost:8080/Pras/insurance/${contact.refContact.ins.id}/contactList">Click Here</a> Insurance Contact List
						</c:when>
						<c:otherwise>
							issue
						</c:otherwise>
					</c:choose>
				
				</div>
			</springForm:form>
 	</div>
	</div>
</body>
</html>
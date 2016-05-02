<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="springForm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="context"
	value="${pageContext.request.contextPath}/${userpath}" />
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

<link rel="stylesheet" type="text/css" media="screen"
	href="../css/jquery-ui-1.8.18.custom.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="../css/ui.jqgrid.css" />
<script src="../js/jquery-1.7.1.min.js" type="text/javascript"></script>
<script src="../js/grid.locale-en.js" type="text/javascript"></script>
<script src="../js/jquery.jqGrid.min.js" type="text/javascript"></script>

</head>

<body>
	<div class="panel with-nav-tabs panel-primary">
		<div class="panel-heading">
			<ul class="nav nav-tabs">
				<li class="active"><a href="#" >Contact</a></li>
			</ul>
		</div>
		<div class="panel-body" id="tablediv">
			<springForm:form method="POST" commandName="contact" action="save.do"
				class="form-horizontal" role="form">
				<div class="form-group">
					<label class="control-label col-sm-2" for="homePhone">Home
						Phone:</label>
					<div class="col-sm-6">
						<springForm:hidden path="id" />
						<springForm:hidden path="refContact.id" />
						<c:choose>
							<c:when test="${contact.refContact.mbr != null}">
								<springForm:hidden path="refContact.mbr.id" />
							</c:when>
							<c:when test="${contact.refContact.prvdr != null}">
								<springForm:hidden path="refContact.prvdr.id" />
							</c:when>
							<c:otherwise>
								<springForm:hidden path="refContact.ins.id" />
							</c:otherwise>
						</c:choose>

						<springForm:input path="homePhone" class="form-control"
							id="homePhone" placeholder="Home Phone" />
						<springForm:errors path="homePhone" cssClass="error" />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-sm-2" for="mobilePhone">Mobile
						Phone:</label>
					<div class="col-sm-6">
						<springForm:input path="mobilePhone" class="form-control"
							id="mobilePhone" placeholder="Mobile Phone" />
						<springForm:errors path="mobilePhone" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="faxNumber">Fax
						Number:</label>
					<div class="col-sm-6">
						<springForm:input path="faxNumber" class="form-control" id="fax"
							placeholder="Fax Number" />
						<springForm:errors path="faxNumber" cssClass="error" />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-sm-2" for="faxNumber">Address
						1:</label>
					<div class="col-sm-6">
						<springForm:input path="address1" class="form-control"
							id="address1" placeholder="Address 1" />
						<springForm:errors path="address1" cssClass="error" />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-sm-2" for="address2">Address
						2:</label>
					<div class="col-sm-6">
						<springForm:input path="address2" class="form-control"
							id="address2" placeholder="Address 2" />
						<springForm:errors path="address2" cssClass="error" />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-sm-2" for="city">City:</label>
					<div class="col-sm-6">
						<springForm:input path="city" class="form-control" id="city"
							placeholder="city" />
						<springForm:errors path="city" cssClass="error" />
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-sm-2" for="state">State:</label>
					<div class="col-sm-6">
						<springForm:select path="stateCode.code" class="form-control"
							id="state">
							<springForm:options items="${stateList}" itemValue="code"
								itemLabel="shortName" />
						</springForm:select>
						<springForm:errors path="stateCode.code" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="zip">Zip:</label>
					<div class="col-sm-6">
						<springForm:select path="zipCode.code" class="form-control"
							id="zip">
							<springForm:options items="${zipCodeList}" itemValue="code"
								itemLabel="code" />
						</springForm:select>
						<springForm:errors path="zipCode.code" cssClass="error" />
					</div>
				</div>

				<div class="col-sm-offset-6 col-sm-4">
					<c:choose>
						<c:when test="${contact.id != null}">
							<button type="submit" class="btn btn-success btn-sm"
								id="updateButton" name="update">Update</button>
							<button type="submit" class="btn btn-success btn-sm"
								id="deleteButton" name="delete">Delete</button>
						</c:when>
						<c:otherwise>
							<button type="submit" class="btn btn-success btn-sm"
								id="updateButton" name="add">Add</button>
							<button type="submit" class="btn btn-success btn-sm"
								id="resetButton">Reset</button>
						</c:otherwise>
					</c:choose>
				</div>
			</springForm:form>
		</div>
	</div>
</body>
</html>
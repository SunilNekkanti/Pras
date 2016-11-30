<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="springForm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="context"
	value="${pageContext.request.contextPath}/${userpath}" />
<div class="panel-group">
	<div class="panel panel-success">
		<div class="panel-heading">
			User Profile <span class="clrRed">${Message}</span> <a
				class="btn btn-danger pull-right btn-xs white-text"
				href="${context}/userList"> <span
				class="glyphicon glyphicon-plus-sign "></span>User List
			</a>
		</div>
		<div class="panel-body" id="tablediv">
			<springForm:form method="POST" commandName="user"
				action="${context}/profile/save.do">
				<div class="form-group required col-sm-12">
					<label class="control-label  col-sm-2" for="username">Login</label>
					<div class="col-sm-6">
						<label class="control-label  col-sm-2">${user.username}</label>
					</div>
				</div>

				<div class="form-group required col-sm-12">
					<label class="control-label  col-sm-2" for="password">Password</label>
					<div class="col-sm-6">
					<springForm:hidden path="username" class="form-control"
							id="username" placeholder="Password" />
						<springForm:password path="password" class="form-control"
							id="password" placeholder="Password" />
						<springForm:errors path="password" cssClass="error text-danger" />
					</div>
				</div>
				
				<div class="form-group required col-sm-12">
					<label class="control-label  col-sm-2" for="cnfPassword">Confirm Password</label>
					<div class="col-sm-6">
						<input type="password" name="cnfPassword" class="form-control" id="cnfPassword" placeholder="Confirm Password" />
					</div>
				</div>

				<div class="col-sm-offset-2 col-sm-6">
					<c:choose>
						<c:when test="${user.id != null && user.activeInd == 89}">
							<button type="submit" class="btn btn-success btn-sm"
								name="update" id="updateButton">Update</button>
						</c:when>
					</c:choose>
				</div>

			</springForm:form>
		</div>
	</div>
</div>
<script>
$( "#user" ).submit(function( event ) {
	  $password =  $("#password").val();
	  $cnfPassword = 	  $("#cnfPassword").val();
	
	  if($password.length < 4)
		  {
		  	alert(" Password must be atleast 4 characters");
		  	event.preventDefault();
		  }
	  else if($password != $cnfPassword){
		  alert(" confirm password must be same as password");
		  event.preventDefault();
	  }
	
	});
	
	
</script>
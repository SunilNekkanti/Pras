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
				action="${context}/user/${id}/save.do">
				<div class="form-group required col-sm-12">
					<label class="control-label  col-sm-2" for="username">Login</label>
					<div class="col-sm-6">
						<springForm:hidden path="id" />
						<springForm:input path="username" class="form-control"
							id="username" placeholder="User Id" />
						<springForm:errors path="username" cssClass="error text-danger" />
					</div>
				</div>

				<div class="form-group required col-sm-12">
					<label class="control-label  col-sm-2" for="password">Password</label>
					<div class="col-sm-6">
						<springForm:input path="password" class="form-control"
							id="password" placeholder="Password" />
						<springForm:errors path="password" cssClass="error text-danger" />
					</div>
				</div>

				<div class="form-group required col-sm-12">
					<label class="control-label col-sm-2" for="roles">Roles</label>
					<div class="col-sm-6">
						<springForm:select multiple="true" path="roles"
							class="form-control" items="${rolesList}" itemLabel="role"
							itemValue="id" />
						<springForm:errors path="roles" cssClass="error text-danger" />
					</div>
				</div>

				<div class="form-group required col-sm-12">
					<label class="control-label col-sm-2" for="effYear">Eff.
						Year</label>
					<div class="col-sm-6">
						<springForm:select path="effectiveYear" class="form-control"
							id="effectiveYear">
							<springForm:options items="${effYearList}" />
						</springForm:select>
						<springForm:errors path="effectiveYear"
							cssClass="error text-danger" />
					</div>
				</div>

				<div class="col-sm-offset-2 col-sm-6">
					<c:choose>
						<c:when test="${user.id != null && user.activeInd == 89}">
							<button type="submit" class="btn btn-success btn-sm"
								name="update" id="updateButton">Update</button>
							<button type="submit" class="btn btn-success btn-sm"
								name="delete" id="deleteButton">Delete</button>
						</c:when>
						<c:when test="${user.id == null}">
							<button type="submit" class="btn btn-success btn-sm"
								id="updateButton" name="add">Add</button>
							<button type="submit" class="btn btn-success btn-sm"
								id="resetButton">Reset</button>
						</c:when>
					</c:choose>
				</div>

			</springForm:form>
		</div>
	</div>
</div>

<script>
$("#deleteButton").click(function(e){
	if (confirm("Action cannot be undone.Click 'Ok' to delete.") == false) 
	{
		e.preventDefault();
	}
  });
</script>


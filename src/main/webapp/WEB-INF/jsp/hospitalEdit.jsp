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
			Hospital Profile <span class="clrRed">${Message}</span> <a
				class="btn btn-danger pull-right btn-xs white-text"
				href="${context}/hospitalList"> <span
				class="glyphicon glyphicon-plus-sign "></span>Hospital List
			</a>
		</div>
		<div class="panel-body">
			<springForm:form method="POST" commandName="hospital"
				action="${context}/hospital/${id}/save.do" class="form-horizontal"
				role="form">
				<div class="form-group required">
					<label class="control-label col-sm-2" for="name">Name</label>
					<div class="col-sm-6">
						<springForm:hidden path="id" />
						<springForm:input path="name" class="form-control" id="name"
							placeholder="name" />
						<springForm:errors path="name" cssClass="error text-danger" />
					</div>
				</div>

				<div class="form-group required">
					<label class="control-label col-sm-2" for="code">Code</label>
					<div class="col-sm-6">
						<springForm:input path="code" class="form-control" id="code"
							placeholder="code" />
						<springForm:errors path="code" cssClass="error text-danger" />
					</div>
				</div>

				<div class="col-sm-offset-2 col-sm-4">
					<c:choose>
						<c:when test="${hospital.id != null && hospital.activeInd == 89}">
							<button type="submit" class="btn btn-success btn-sm"
								id="updateButton" name="update">Update</button>
							<button type="submit" class="btn btn-success btn-sm"
								id="deleteButton" name="delete">Delete</button>
						</c:when>
						<c:when test="${hospital.id == null}">
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

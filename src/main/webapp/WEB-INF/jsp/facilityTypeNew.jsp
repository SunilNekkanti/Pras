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
			Hospital Profile <a
				class="btn btn-danger pull-right btn-xs white-text"
				href="${context}/hospitalList"> <span
				class="glyphicon glyphicon-plus-sign "></span>Hospital List
			</a>
		</div>
		<div class="panel-body">
			<springForm:form method="POST" commandName="facilityType" id="facilityType"
				action="save.do" class="form-horizontal" role="form">
				<div class="form-group required">
					<label class="control-label col-sm-2" for="description">Description</label>
					<div class="col-sm-6">
						<springForm:hidden path="id" />
						<springForm:input path="description" class="form-control" id="description"
							placeholder="Description" />
						<springForm:errors path="description" cssClass="error text-danger" />
					</div>
				</div>

				<div class="form-group required">
					<label class="control-label col-sm-2" for="shortName">Short Name</label>
					<div class="col-sm-6">
						<springForm:input path="shortName" class="form-control" id="shortName"
							placeholder="shortName" />
						<springForm:errors path="shortName" cssClass="error text-danger" />
					</div>
				</div>

				<div class="col-sm-offset-2 col-sm-4">
					<c:choose>
						<c:when test="${facilityType.id != null && facilityType.activeInd == 89}">
							<button type="submit" class="btn btn-success btn-sm"
								id="updateButton" name="update">Update</button>
							<button type="submit" class="btn btn-success btn-sm"
								id="deleteButton" name="delete">Delete</button>
						</c:when>
						<c:when test="${facilityType.id == null}">
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


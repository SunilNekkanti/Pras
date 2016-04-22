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
			Hedis Measure Details <span class="clrRed">${Message}</span> <a
				class="btn btn-danger pull-right btn-xs white-text"
				href="${context}/hedis/hedisMeasureList"> <span
				class="glyphicon glyphicon-plus-sign "></span>Hedis Measure List
			</a>
		</div>
		<div class="panel-body" id="tablediv">
			<springForm:form method="POST" commandName="hedisMeasure"
				action="save.do" class="form-horizontal" role="form">
				<div class="form-group required">
					<label class="control-label col-sm-3" for="code">Code</label>
					<div class="col-sm-8">
						<springForm:hidden path="id" />
						<springForm:input path="code" class="form-control" maxlength="5"
							id="code" placeholder="Code" />
						<springForm:errors path="code" cssClass="error text-danger" />
					</div>
				</div>

				<div class="form-group required">
					<label class="control-label col-sm-3" for="description">Description</label>
					<div class="col-sm-8">
						<springForm:input path="description" class="form-control"
							id="description" placeholder="Description" />
						<springForm:errors path="description" cssClass="error text-danger" />
					</div>
				</div>

				<div class="form-group required">
					<label class="control-label col-sm-3" for="hedisMsrGrp">Group
						Id</label>
					<div class="col-sm-8">
						<springForm:select path="hedisMsrGrp" class="form-control"
							id="hedisMsrGrp">
							<springForm:options items="${hedisMeasureGroupList}"
								itemValue="id" itemLabel="description" />
						</springForm:select>
						<springForm:errors path="hedisMsrGrp.code"
							cssClass="error text-danger" />
					</div>
				</div>

				<div class="col-sm-12">
					<div class="col-sm-offset-2 col-sm-10">
						<c:choose>
							<c:when
								test="${hedisMeasure.id != null && hedisMeasure.activeInd == 89}">
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
				</div>
			</springForm:form>
		</div>
	</div>
</div>


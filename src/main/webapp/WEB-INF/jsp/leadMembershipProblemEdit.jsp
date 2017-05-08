<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="springForm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="context"
	value="${pageContext.request.contextPath}/${userpath}" />

<div class="panel-group" >
	<div class="panel panel-success">
		<div class="panel-heading">Lead Membership Problem
				<span class="clrRed">${Message}</span>
			<button class="btn btn-danger pull-right btn-xs"	onclick="return membershipProblemList();">
			<span class="glyphicon glyphicon-plus-sign "></span> Problem List</button>
		</div>
		<div class="panel-body" id="tablediv">
			<springForm:form commandName="leadMembershipProblem" class="form-horizontal" action="save.do">
				<springForm:hidden path="id"  />
				
				<div class="form-group required col-sm-3">
					<div class="col-sm-10">
						<label  class="control-label" for="name">Problem</label>
						<springForm:select path="pbm" class="form-control required">
							<springForm:option value="">Select one</springForm:option>
							<springForm:options items="${problemList}" itemValue="id" itemLabel="description" />
						</springForm:select>
						<springForm:errors path="pbm"	cssClass="error text-danger" />
					</div>
				</div>	
				
				<div class="form-group col-sm-3">
					<div class="col-sm-10">
						<label  class="control-label" for="name">Diagnose Date</label>
						<springForm:input path="startDate" class="form-control datepickerfrom" placeholder="startDate" />
						<springForm:errors path="startDate"	cssClass="error text-danger" />
					</div>
				</div>	
				
				<div class="form-group col-sm-3">
					<div class="col-sm-10">
						<label  class="control-label" for="name">Resolved Date</label>
						<springForm:input path="resolvedDate" class="form-control datepickerto" placeholder="resolvedDate" />
						<springForm:errors path="resolvedDate"	cssClass="error text-danger" />
					</div>
				</div>	
				
				
			
			<div class="col-sm-offset-8 col-sm-4">
					<c:choose>
						<c:when
							test="${leadMembershipProblem.id != null && leadMembershipProblem.activeInd == 89}">
							<button type="button" class="btn btn-success btn-sm"
								id="updateButton" name="update"
								onclick="return modifyLeadMbrProblem(${leadMembershipProblem.id})">Update</button>
							<button type="button" class="btn btn-success btn-sm"
								id="deleteButton" class="delete"
								onclick="return deleteLeadMbrProblem(${leadMembershipProblem.id})"
								name="delete">Delete</button>
						</c:when>
						<c:when test="${leadMembershipProblem.id == null}">
							<button type="button" class="btn btn-success btn-sm"
								id="addButton" name="add" onclick="return addLeadMbrProblem()">Add</button>
							<button type="button" class="btn btn-success btn-sm"
								id="resetButton">Reset</button>
						</c:when>
					</c:choose>
				</div>	
			</springForm:form>
		</div>
	</div>
</div>
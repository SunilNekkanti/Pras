<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="springForm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="context"
	value="${pageContext.request.contextPath}/${userpath}" />

<div class="panel-group">
	<div class="panel panel-success">
		<div class="panel-heading">Provider Info</div>
		<div class="panel-body" id="tablediv">
			<springForm:form commandName="membershipProvider" class="form-inline" action="save.do">
				<springForm:hidden path="id" />

				<div class="form-group col-sm-12">
					<label class="control-label col-sm-2" for="name">Name:</label>
					
							<springForm:select path="prvdr.id" class="form-control" id="prvdr.id">
									<springForm:options items="${prvdrList}" itemValue="id"
										itemLabel="name" />
								</springForm:select>
						<springForm:errors path="prvdr.name" cssClass="error text-danger" />
						
						
						<c:choose>
						<c:when
							test="${membershipProvider.id != null && membershipProvider.activeInd == 89}">
							<button type="button" class="btn btn-success btn-sm"
								id="updateButton" name="update"
								onclick="return modifyMbrInsDetails(${membershipProvider.id})">Update</button>
							<button type="button" class="btn btn-success btn-sm"
								id="deleteButton" class="delete"
								onclick="return deleteMbrInsDetails(${membershipProvider.id})"
								name="delete">Delete</button>
						</c:when>
						<c:when test="${membershipProvider.id == null}">
							<button type="button" class="btn btn-success btn-sm"
								id="updateButton" name="add" onclick="return addMbrInsDetails()">Add</button>
							<button type="button" class="btn btn-success btn-sm"
								id="resetButton">Reset</button>
						</c:when>
					</c:choose>
					
				</div>
				
			</springForm:form>

			<div class="row col-sm-12">
				<div class="col-sm-12" style="padding-top: 2px;">
					<a
						href="${context}/membership/${membershipProvider.mbr.id}/providerDetailsList">Click
						Here</a> to see Membership Provider List
				</div>
			</div>

		</div>
	</div>
</div>

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
		<div class="panel-heading">Provider Details</div>
		<div class="panel-body" id="tablediv">
			<springForm:form commandName="leadMembershipProvider" class="form-inline" action="save.do">
				<springForm:hidden path="id" />

				<div class="form-group col-sm-12">
					<label class="control-label col-sm-2" for="name">Name:</label>
					
							<springForm:select path="prvdr.id" class="form-control" id="prvdr.id">
									<springForm:options items="${prvdrList}" itemValue="id"
										itemLabel="name" />
								</springForm:select>
						<springForm:errors path="prvdr.id" cssClass="error text-danger" />
						
						
						<c:choose>
						<c:when
							test="${leadMembershipProvider.id != null && leadMembershipProvider.activeInd == 89}">
							<button type="button" class="btn btn-success btn-sm"
								id="updateButton" name="update"
								onclick="return modifyleadPrvdrDetails(${leadMembershipProvider.id})">Update</button>
							<button type="button" class="btn btn-success btn-sm delete"
								id="deleteButton" 	onclick="return deleteleadPrvdrDetails(${leadMembershipProvider.id})"
								name="delete">Delete</button>
						</c:when>
						<c:when test="${leadMembershipProvider.id == null}">
							<button type="button" class="btn btn-success btn-sm"
								id="addButton" name="add" onclick="return addLeadProvider()">Add</button>
							<button type="button" class="btn btn-success btn-sm"
								id="resetButton">Reset</button>
						</c:when>
					</c:choose>
					
				</div>
				
			</springForm:form>


		</div>
	</div>
</div>

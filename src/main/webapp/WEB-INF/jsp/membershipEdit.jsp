<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
    prefix="springForm"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <div class="panel-group">
	<div class="panel panel-primary">
		<div class="panel-heading">Profile</div>
		<div class="panel-body" id="tablediv">
			<springForm:form method="POST" commandName="membership" action="save.do" class="form-horizontal" role="form">
				<div class="form-group">
					<label class="control-label col-sm-2" for="firstName">First Name:</label>
					<div class="col-sm-6">
						<springForm:hidden path="id" />
						<springForm:input path="firstName" class="form-control" id="firstName" placeholder="First Name" />
						<springForm:errors path="firstName" cssClass="error" />
					</div>
				</div>
				 		 
				<div class="form-group">
					<label class="control-label col-sm-2" for="lastName">Last Name:</label>
					<div class="col-sm-6">
						<springForm:input path="lastName" class="form-control" id="lastName" placeholder="Last Name" />
						<springForm:errors path="lastName" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
				 	<label class="control-label col-sm-2" for="dob">DOB:</label>
					<div class="col-sm-6">
						<springForm:input path="dob" class="form-control" id="dob" placeholder="DOB" />
						<springForm:errors path="dob" cssClass="error" />
					</div>
				</div>
				 
				<div class="form-group">
					<label class="control-label col-sm-2" for="status">Status:</label>
					<div class="col-sm-6">
						<springForm:select path="status.id" class="form-control" id="status">
				    		<springForm:options items="${statusMap}"    />
						</springForm:select>
						<springForm:errors path="status.description" cssClass="error" />
					  </div>
				</div>
				 		 
				<div class="form-group">
					<label class="control-label col-sm-2" for="gender">Gender:</label>
					<div class="col-sm-6">
						<springForm:select path="genderId.id"  class="form-control" id="gender">
				    		<springForm:options items="${genderMap}"    />
						</springForm:select>
						<springForm:errors path="genderId.description" cssClass="error" />
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-2" for="county">County:</label>
					<div class="col-sm-6">
						<springForm:select path="countyCode.code" class="form-control" id="county" >
				    		<springForm:options items="${countyMap}"    />
						</springForm:select>
						<springForm:errors path="countyCode.description" cssClass="error" />
					</div>
				</div>
				 		 
				<div class="col-sm-offset-6 col-sm-4">
				<c:choose>
						 <c:when test="${membership.id != null && membership.activeInd == 89}"> 
								<button type="submit" class="btn btn-primary" name = "update" id="updateButton">Update</button>
								<button type="submit" class="btn btn-primary" name ="delete"id="deleteButton">Delete</button>
						</c:when>
				</c:choose>				
				</div>
			</springForm:form>
		</div>
	</div>
</div> 

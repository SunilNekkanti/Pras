<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"    prefix="springForm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="panel-group">
	<div class="panel panel-primary">
		<div class="panel-heading">Insurance Profile</div>
		<div class="panel-body">
			<springForm:form method="POST" commandName="insurance" action="save.do" class="form-horizontal" role="form">
			     <div class="form-group">
				 	<label class="control-label col-sm-2" for="name">Name:</label>
					<div class="col-sm-6">
						<springForm:hidden path="id" />
						<springForm:input path="name" class="form-control" id="name" placeholder="name" />
						<springForm:errors path="name" cssClass="error" />
					</div>
				 </div>		
				 <div class="form-group">
				 	<label class="control-label col-sm-2" for="createdDate">Created Date:</label>
					<div class="col-sm-6">
						<fmt:formatDate value="${insurance.createdDate}" var="dateString" pattern="MM/dd/yyyy HH:mm:ss" />
						<springForm:input path="createdDate" value="${dateString}" class="form-control" id="createdDate" placeholder="createdDate" />
						<springForm:errors path="createdDate" cssClass="error" />
					</div>
				 </div>	
				 <div class="form-group">
				 	<label class="control-label col-sm-2" for="updatedDate">Updated Date:</label>
					<div class="col-sm-6">
					<fmt:formatDate value="${insurance.updatedDate}" var="dateString" pattern="MM/dd/yyyy HH:mm:ss" />
					<springForm:input path="updatedDate" value="${dateString}" class="form-control" id="updatedDate" placeholder="updatedDate" />
					<springForm:errors path="updatedDate" cssClass="error" />
					</div>
				 </div>	
				 <div class="form-group">
				 	<label class="control-label col-sm-2" for="createdBy">Created By:</label>
					<div class="col-sm-6">
						
						<springForm:input path="createdBy" class="form-control" id="createdBy" placeholder="createdBy" />
						<springForm:errors path="createdBy" cssClass="error" />
					</div>
				 </div>		
				  <div class="form-group">
				 	<label class="control-label col-sm-2" for="updatedBy">Updated By:</label>
					<div class="col-sm-6">
						
						<springForm:input path="updatedBy" class="form-control" id="updatedBy" placeholder="updatedBy" />
						<springForm:errors path="updatedBy" cssClass="error" />
					</div>
				 </div>		
				  <div class="form-group">
				 	<label class="control-label col-sm-2" for="activeInd">Active Ind:</label>
					<div class="col-sm-6">
						
						<springForm:input path="activeInd" class="form-control" id="activeInd" placeholder="activeInd" />
						<springForm:errors path="activeInd" cssClass="error" />
					</div>
				 </div>		
				<div class="col-sm-offset-2 col-sm-4">
					<c:choose>
						 <c:when test="${insurance.id != null && insurance.activeInd == 89}"> 
						 	<button type="submit" class="btn btn-primary" id="updateButton" name="update" >Update</button>
						 	<button type="submit" class="btn btn-primary" id="deleteButton" name="delete" >Delete</button>
						 </c:when>
						 <c:when test="${insurance.id == null}"> 
							<button type="submit" class="btn btn-primary" id="updateButton" name="add" >Add</button>
							<button type="submit" class="btn btn-primary" id="resetButton" >Reset</button>
						</c:when>
					</c:choose>
					
				</div>
				
			</springForm:form>
			<div class="row col-sm-12">
				<div class="col-sm-12" style="padding-top:2px;">
					<a href="/Pras/insuranceList">Click Here</a> to see Insurance List
				</div>	
			</div>
		</div>
	</div>	
</div>						    
 		    	
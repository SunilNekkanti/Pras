<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
    prefix="springForm"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="panel-group">
	<div class="panel panel-primary">
		<div class="panel-heading">Provider Profile</div>
		<div class="panel-body" id="tablediv">
			<springForm:form method="POST" commandName="provider" action="save.do">
				<div class="form-group col-sm-12">
						    <label class="control-label col-sm-2" for="name">Name:</label>
						    <div class="col-sm-6">
						    	<springForm:hidden path="id" />
						      	<springForm:input path="name" class="form-control" id="name" placeholder="Name" />
						      	<springForm:errors path="name" cssClass="error text-danger" />
						    </div>
				 </div>
				 
				 <div class="form-group col-sm-12">
						    <label class="control-label col-sm-2" for="code">Code:</label>
						    <div class="col-sm-6">
						    	<springForm:input path="code" class="form-control" id="code" placeholder="code" />
						      	<springForm:errors path="code" cssClass="error text-danger" />
						    </div>
				</div>
				
				<div class="form-group col-sm-12">
				 	<label class="control-label col-sm-2" for="createdDate">Created Date:</label>
					<div class="col-sm-6">
						<fmt:formatDate value="${provider.createdDate}" var="dateString" pattern="MM/dd/yyyy HH:mm:ss" />
						<springForm:input path="createdDate" value="${dateString}" class="form-control" id="createdDate" placeholder="createdDate" />
						<springForm:errors path="createdDate" cssClass="error text-danger" />
					</div>
				 </div>	
				<div class="form-group col-sm-12">
				 	<label class="control-label col-sm-2" for="updatedDate">Updated Date:</label>
					<div class="col-sm-6">
					<fmt:formatDate value="${provider.updatedDate}" var="dateString" pattern="MM/dd/yyyy HH:mm:ss" />
					<springForm:input path="updatedDate" value="${dateString}" class="form-control" id="updatedDate" placeholder="updatedDate" />
					<springForm:errors path="updatedDate" cssClass="error text-danger" />
					</div>
				 </div>	
				<div class="form-group col-sm-12">
				 	<label class="control-label col-sm-2" for="createdBy">Created By:</label>
					<div class="col-sm-6">
						
						<springForm:input path="createdBy" class="form-control" id="createdBy" placeholder="createdBy" />
						<springForm:errors path="createdBy" cssClass="error text-danger" />
					</div>
				 </div>		
				<div class="form-group col-sm-12">
				 	<label class="control-label col-sm-2" for="updatedBy">Updated By:</label>
					<div class="col-sm-6">
						
						<springForm:input path="updatedBy" class="form-control" id="updatedBy" placeholder="updatedBy" />
						<springForm:errors path="updatedBy" cssClass="error text-danger" />
					</div>
				 </div>
				 
				<div class="form-group col-sm-12">
							    <label class="control-label col-sm-2" for="activeInd">ActiveInd:</label>
							    <div class="col-sm-6">
								    <springForm:select path="activeInd" class="form-control" id="activeInd" >
						    			<springForm:options items="${activeIndMap}" />
									</springForm:select>
									<springForm:errors path="activeInd" cssClass="error text-danger" />						    	
							    </div>
					</div>
				
				<div class="col-sm-offset-2 col-sm-6">
				<c:choose>
						 <c:when test="${provider.id != null && provider.activeInd == 89}"> 
							<button type="submit" class="btn btn-primary" name="update" id="updateButton">Update</button>
							<button type="submit" class="btn btn-primary" name="delete" id="deleteButton">Delete</button>
						 </c:when>
						 <c:when test="${provider.id == null}"> 
							<button type="submit" class="btn btn-primary" id="updateButton" name="add" >Add</button>
							<button type="submit" class="btn btn-primary" id="resetButton" >Reset</button>
						</c:when>
					</c:choose>	
				</div>
				 
			</springForm:form>
			
			
			<div class="row col-sm-12">
				<div class="col-sm-12" style="padding-top:2px;">
					<a href="/Pras/providerList">Click Here</a> to see Provider List
				</div>	
			</div>
			
 		</div>
	</div>
</div>		

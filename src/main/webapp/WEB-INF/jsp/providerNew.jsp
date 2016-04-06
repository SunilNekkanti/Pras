<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
    prefix="springForm"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="context" value="${pageContext.request.contextPath}" />

<script src="${context}/resources/js/validation.js"></script>
<script>
	$(document).ready(function(){	providerValidation();	});
</script>
<div class="panel-group">
	<div class="panel panel-success">
		<div class="panel-heading">Provider Profile
			<a class="btn btn-danger pull-right btn-xs"href="${context}/providerList">
          		<span class="glyphicon glyphicon-plus-sign "></span>Provider List
          	</a>
		</div>
		<div class="panel-body" id="tablediv">
			<springForm:form method="POST" id="provider" commandName="provider" action="save.do">
				<div class="form-group required col-sm-12">
						    <label class="control-label  col-sm-2" for="name">Name</label>
						    <div class="col-sm-6">
						    	<springForm:hidden path="id" />
						      	<springForm:input path="name" class="form-control" id="name" placeholder="Name" />
						      	<springForm:errors path="name" cssClass="error text-danger" />
						    </div>
				 </div>
				 
				 <div class="form-group required col-sm-12">
						    <label class="control-label  col-sm-2" for="code">Code</label>
						    <div class="col-sm-6">
						    	<springForm:input path="code" class="form-control" id="code" placeholder="code" />
						      	<springForm:errors path="code" cssClass="error text-danger" />
						    </div>
				</div>
				
				<div class="form-group required col-sm-12">
							<label class="control-label col-sm-2" for="insurance">Insurance</label>
							<div class="col-sm-6">
								<springForm:select multiple="true" path="insurances" class="form-control"  items="${insuranceList}" itemLabel="name" itemValue="id" />
								<springForm:errors path="insurances" cssClass="error text-danger" />
							</div>
				</div>
				<div class="col-sm-offset-2 col-sm-6">
					<c:choose>
						 <c:when test="${provider.id != null && provider.activeInd == 89}"> 
							<button type="submit" class="btn btn-success btn-sm" name="update" id="updateButton">Update</button>
							<button type="submit" class="btn btn-success btn-sm" name="delete" id="deleteButton">Delete</button>
						 </c:when>
						 <c:when test="${provider.id == null}"> 
							<button type="submit" class="btn btn-success btn-sm" id="updateButton" name="add" >Add</button>
							<button type="submit" class="btn btn-success btn-sm" id="resetButton" >Reset</button>
						</c:when>
					</c:choose>	
				</div>
				 
			</springForm:form>
		</div>
	</div>
</div>	


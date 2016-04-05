<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"    prefix="springForm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="context" value="${pageContext.request.contextPath}" />
<script src="${context}/resources/js/validation.js"></script>
<script>
	$(document).ready(function(){	insuranceValidation();	});
</script>


<div class="panel-group">
	<div class="panel panel-primary">
		<div class="panel-heading">Insurance Profile
			<a class="btn btn-danger pull-right btn-xs"href="${context}/insuranceList">
          		<span class="glyphicon glyphicon-plus-sign "></span>Insurance List
          	</a>
		</div>
		<div class="panel-body">
			<springForm:form method="POST" commandName="insurance" id="insurance" action="save.do" class="form-horizontal" role="form">
			     <div class="form-group required">
				 	<label class="control-label col-sm-2" for="name">Name</label>
					<div class="col-sm-6">
						<springForm:hidden path="id" />
						<springForm:input path="name" class="form-control" id="name" placeholder="name" />
						<springForm:errors path="name" cssClass="error text-danger" />
					</div>
				 </div>		
				 
				 <div class="form-group required">
					<label class="control-label col-sm-2" for="Plan">Plan</label>
					<div class="col-sm-6">
						<springForm:select path="planTypeId" class="form-control" id="planTypeId">
				    		<springForm:options items="${planTypeList}" itemValue="id" itemLabel="code"     />
						</springForm:select>
						<springForm:errors path="planTypeId.code" cssClass="error text-danger" />
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
		</div>
	</div>	
</div>	
				    
 		    	
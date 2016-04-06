<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"    prefix="springForm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="context" value="${pageContext.request.contextPath}" />
<div class="panel-group">
	<div class="panel panel-success">
		<div class="panel-heading">Insurance Profile</div>
		<div class="panel-body">
			<springForm:form method="POST" commandName="insurance" action="${context}/insurance/${id}/save.do">
			     <div class="form-group required col-sm-3">
				 	<div class="col-sm-4"><label for="name">Name</label></div>
					<div class="col-sm-8">
						<springForm:hidden path="id" />
						<springForm:input path="name" class="form-control" id="name" placeholder="name" />
					</div>	
					<div class="col-sm-12">
						<springForm:errors path="name" cssClass="error text-danger" />
					</div>	
						
					
				 </div>		
				 
				 <div class="form-group required col-sm-3">
				 	<div class="col-sm-3"><label for="Plan" >Plan</label></div>
				 	<div class="col-sm-8">
						<springForm:select path="planTypeId" class="form-control" id="planTypeId" style="width:150px;">
				    		<springForm:options items="${planTypeList}" itemValue="id" itemLabel="code"     />
							</springForm:select>
					</div>
					<div class="col-sm-12">
						<springForm:errors path="planTypeId.code" cssClass="error text-danger" />
					</div>	
					  
				</div>
				  <div class="form-group col-sm-4">
					<c:choose>
						 <c:when test="${insurance.id != null && insurance.activeInd == 89}"> 
						 	<div class="col-sm-3">
						 		<button type="button" class="btn btn-success btn-sm" id="updateButton" name="update" onclick="return modifyInsuranceDetails();" >Update</button>
						 	</div>
						 	<div class="col-sm-3">	
						 		<button type="button" class="btn btn-success btn-sm" id="deleteButton" name="delete" onclick="return deleteInsuranceDetails();" >Delete</button>
						 </div>	
						 </c:when>
						 <c:when test="${insurance.id == null}"> 
						 	<div class="col-sm-3">
								<button type="submit" class="btn btn-success btn-sm" id="updateButton" name="add" >Add</button>
							</div>
							<div class="col-sm-3">
								<button type="submit" class="btn btn-success btn-sm" id="resetButton" >Reset</button>
							</div>	
						</c:when>
					</c:choose>					
				</div>			
			</springForm:form>			
		</div>
	</div>	
</div>	

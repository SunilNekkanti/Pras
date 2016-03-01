<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
    prefix="springForm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="context" value="${pageContext.request.contextPath}" />
<c:choose>
 	<c:when test="${hedisMeasure.id != null && hedisMeasure.activeInd != 89}">
	<script>$(document).ready(function(){removeRequired();	removePlaceHolder();});	</script>
	</c:when>
	<c:when test="${hedisMeasure.id != null}"> 	
	<script>$(document).ready(function(){removePlaceHolder();});</script>
	</c:when>
</c:choose>
<div class="panel-group">
	<div class="panel panel-primary">
		<div class="panel-heading">Hedis Measure</div>
        <div class="panel-body">
			<springForm:form method="POST" commandName="hedisMeasure" action="${context}/hedis/${id}/save.do" class="form-horizontal" role="form">
				<div class="form-group required">
					<label class="control-label col-sm-3" for="code">Code</label>
					<div class="col-sm-8">
						<springForm:hidden path="id" />
						<springForm:input path="code" class="form-control " id="code" placeholder="Code" />
						<springForm:errors path="code" cssClass="error text-danger" />
					</div>
				</div>
				 		 
				<div class="form-group required">
					<label class="control-label col-sm-3" for="description">Description</label>
					<div class="col-sm-8">
						<springForm:input path="description" class="form-control" id="description" placeholder="Description" />
						<springForm:errors path="description" cssClass="error text-danger" />
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-3" for="gender">Gender</label>
					<div class="col-sm-8">
						<springForm:select path="genderId"  class="form-control" id="gender">
					   		<springForm:options items="${genderList}"  itemValue="id" itemLabel="description"    />
						</springForm:select>
						<springForm:errors path="genderId" cssClass="error text-danger" />
					</div>
				</div>
				
				<div class="form-group required">
					<label class="control-label col-sm-3" for="hedisMsrGrp">Group Id</label>
					<div class="col-sm-8">
						<springForm:select path="hedisMsrGrp" class="form-control" id="hedisMsrGrp">
				    		<springForm:options items="${hedisMeasureGroupList}" itemValue="id" itemLabel="description"     />
						</springForm:select>
						<springForm:errors path="hedisMsrGrp.code" cssClass="error text-danger" />
					  </div>
				</div>
				
				<div class="form-group">
					<label class="control-label   col-sm-3" for="lowerAgeLimit">Lower Age Limit</label>
					<div class="col-sm-8">
						<springForm:input path="lowerAgeLimit" class="form-control" id="lowerAgeLimit" placeholder="lowerAgeLimit" />
						<springForm:errors path="lowerAgeLimit" cssClass="error text-danger" />
					  </div>
				</div>
				
				<div class="form-group">
					<label class="control-label  col-sm-3" for="upperAgeLimit">Upper Age Limit</label>
					<div class="col-sm-8">
						<springForm:input path="upperAgeLimit" class="form-control" id="upperAgeLimit" placeholder="upperAgeLimit" />
						<springForm:errors path="upperAgeLimit" cssClass="error text-danger" />
					  </div>
				</div>
				
				<div class="form-group">
					<label class="control-label  col-sm-3" for="ageEffectiveFrom" >Age Effective From</label>
					<div class="col-sm-8">
						<fmt:formatDate value="${hedisMeasure.ageEffectiveFrom}" var="dateString" pattern="MM/dd/yyyy" />
						<springForm:input path="ageEffectiveFrom" value="${dateString}" class="form-control datepicker"  id="ageEffectiveFrom" placeholder="ageEffectiveFrom" />
						<springForm:errors path="ageEffectiveFrom" cssClass="error text-danger" />
					  </div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3" for="ageEffectiveTo ">Age Effective To</label>
					<div class="col-sm-8">
						<fmt:formatDate value="${hedisMeasure.ageEffectiveTo}" var="dateString" pattern="MM/dd/yyyy" />
						<springForm:input path="ageEffectiveTo" value="${dateString}" class="form-control datepicker" id="ageEffectiveTo" placeholder="ageEffectiveTo" />
						<springForm:errors path="ageEffectiveTo" cssClass="error text-danger" />
					  </div>
				</div>
			<div class="col-sm-12">	 
				<div class="col-sm-offset-2 col-sm-10">
					<c:choose>
						 <c:when test="${hedisMeasure.id != null && hedisMeasure.activeInd == 89}"> 
						 	<button type="submit" class="btn btn-primary" id="updateButton" name="update" >Update</button>
						 	<button type="submit" class="btn btn-primary" id="deleteButton" name="delete" >Delete</button>
						 </c:when>
						 <c:otherwise>
							<button type="submit" class="btn btn-primary" id="updateButton" name="add" >Add</button>
							<button type="submit" class="btn btn-primary" id="resetButton" >Reset</button>
						</c:otherwise>
						</c:choose>
			   </div>
			  </div> 
			</springForm:form>
		</div>	
 	</div>
</div>


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="springForm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context"
	value="${pageContext.request.contextPath}/${userpath}" />
<div class="panel  panel-success">
	<div class="panel-heading">
		CPT Measure <span class="clrRed">${Message}</span> <a
			class="btn btn-danger pull-right btn-xs white-text"
			href="${context}/cpt/cptMeasureList"> <span
			class="glyphicon glyphicon-plus-sign "></span>CPT Measure List
		</a>
	</div>
	<div class="panel-body" id="tablediv">
		<springForm:form method="POST" commandName="cptMeasure"
			action="${context}/cpt/${id}/save.do" class="form-horizontal"
			role="form">
			<div class="form-group required">
				<label class="control-label col-sm-2" for="code">Code</label>
				<div class="col-sm-6">
					<springForm:hidden path="id" />
					<springForm:input path="code" class="form-control" id="code"
						placeholder="Code" />
					<springForm:errors path="code" cssClass="error text-danger" />
				</div>
			</div>

			<div class="form-group required">
				<label class="control-label col-sm-2"
					for="shortDescription required">Short Description</label>
				<div class="col-sm-6">
					<springForm:input path="shortDescription" class="form-control"
						id="shortDescription" placeholder="Short Description" />
					<springForm:errors path="shortDescription"
						cssClass="error text-danger" />
				</div>
			</div>

			<div class="form-group required">
				<label class="control-label col-sm-2" for="description required">Description</label>
				<div class="col-sm-6">
					<springForm:input path="description" class="form-control"
						id="description" placeholder="Description" />
					<springForm:errors path="description" cssClass="error text-danger" />
				</div>
			</div>


			<div class="col-sm-offset-6 col-sm-4">
				<c:choose>
					<c:when
						test="${cptMeasure.id != null && cptMeasure.activeInd == 89}">
						<button type="submit" class="btn btn-success btn-sm"
							id="updateButton" name="update">Update</button>
						<button type="submit" class="btn btn-success btn-sm"
							id="deleteButton" name="delete">Delete</button>
					</c:when>
					<c:when test="${cptMeasure.id == null}">
						<button type="submit" class="btn btn-success btn-sm"
							id="updateButton" name="add">Add</button>
						<button type="submit" class="btn btn-success btn-sm"
							id="resetButton">Reset</button>
					</c:when>
				</c:choose>
			</div>
		</springForm:form>
	</div>
</div>
<script>
	$(document).ready(function(){
		 $("#deleteButton").click(function(event){
			 if (confirm("Action cannot be undone.Click 'Ok' to delete.") == false) 
			{
				 event.preventDefault();
			} 	
		 });		
});
</script>


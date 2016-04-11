<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
    prefix="springForm"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <c:set var="context" value="${pageContext.request.contextPath}/${userpath}" />
<div class="panel panel-success">
		<div class="panel-heading">ICD Measure
			<a class="btn btn-danger pull-right btn-xs white-text"href="${context}/icd/icdMeasureList">
          		<span class="glyphicon glyphicon-plus-sign "></span>ICD Measure List
          	</a>
        </div>
		<div class="panel-body" id="tablediv">
			<springForm:form method="POST" commandName="icdMeasure" action="${context}/icd/${id}/save.do" class="form-horizontal" role="form">
				<div class="form-group required">
					<label class="control-label col-sm-2" for="code">Code</label>
					<div class="col-sm-6">
						<springForm:hidden path="id" />
						<springForm:input path="code" class="form-control" id="code" placeholder="Code" />
						<springForm:errors path="code" cssClass="error text-danger" />
					</div>
				</div>
				 		 
				<div class="form-group required">
					<label class="control-label col-sm-2" for="description required">Description</label>
					<div class="col-sm-6">
						<springForm:input path="description" class="form-control" id="description" placeholder="Description" />
						<springForm:errors path="description" cssClass="error text-danger" />
					</div>
				</div>
				
					
				<div class="col-sm-offset-6 col-sm-4">
					<c:choose>
						 <c:when test="${icdMeasure.id != null && icdMeasure.activeInd == 89}"> 
						 	<button type="submit" class="btn btn-success btn-sm" id="updateButton" name="update" >Update</button>
						 	<button type="submit" class="btn btn-success btn-sm" id="deleteButton" name="delete" >Delete</button>
						 </c:when>
						 <c:when test="${icdMeasure.id == null}">
							<button type="submit" class="btn btn-success btn-sm" id="updateButton" name="add" >Add</button>
							<button type="submit" class="btn btn-success btn-sm" id="resetButton" >Reset</button>
						</c:when>
						</c:choose>
					
					
				</div>
			</springForm:form>
 	</div>
</div>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
    prefix="springForm"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="context" value="${pageContext.request.contextPath}" />

<script src="${context}/resources/js/validation.js"></script>	
<c:choose>
 	<c:when test="${contract.id != null && contract.activeInd != 89}">
	<script>$(document).ready(function(){removeRequired(); removePlaceHolder();	contractValidation();});</script>
	</c:when>
	<c:when test="${contract.id != null}"> 	
	<script>$(document).ready(function(){removePlaceHolder();contractValidation();});</script>
	</c:when>
	<c:otherwise> 	
	<script>$(document).ready(function(){contractValidation();});</script>
	</c:otherwise>
</c:choose>
<div class="panel-group">
	<div class="panel panel-primary">
		<div class="panel-heading">
			Contract Details
			<button class="btn btn-danger pull-right btn-xs" onclick= "return contractList();">
          		<span class="glyphicon glyphicon-plus-sign "></span> contract List
           </button>
    	</div>
		<div class="panel-body">
			<springForm:form method="POST" id="contract" commandName="contract" action="save.do" class="form-horizontal" role="form">
				<div class="col-sm-12">
					<div class="col-sm-2">
						<div class="form-group required">
							<label class="control-label col-sm-5" for="contractNBR">Contract NBR</label>
							<div class="col-sm-6">
								<springForm:hidden path="id" />
								<springForm:hidden path="referenceContract.id" />
								
								 <c:choose>
								 	 <c:when test="${contract.referenceContract.insPrvdr.prvdr != null}">
										<springForm:hidden path="referenceContract.insPrvdr.prvdr.id" />
									</c:when>
									<c:when test="${contract.referenceContract.ins != null}">
										<springForm:hidden path="referenceContract.ins.id" />
									</c:when>
								</c:choose>
								
								<springForm:input path="contractNBR" class="form-control" id="contractNBR" placeholder="Contract NBR" />
								<springForm:errors path="contractNBR" cssClass="error text-danger" />
							</div>
						</div>
					</div>
					<div class="col-sm-2">	
						<div class="form-group required">
							<label class="control-label required col-sm-5" for="PMPM">PMPM</label>
							<div class="col-sm-7">
								<springForm:input path="PMPM" class="form-control" id="PMPM" placeholder="PMPM" />
								<springForm:errors path="PMPM" cssClass="error text-danger" />
							</div>
						</div>
					</div>	
					
					<c:choose>
					<c:when test="${insuranceRequired}">
					<div class="col-sm-3">
								<div class="form-group required">
								<label class="control-label col-sm-2" for="status">Insurance</label>
								<div class="col-sm-6">
									<springForm:select path="insPrvdrId" class="form-control" id="status">
										<springForm:option  value="${null}" label="Select One"/>
							    		<springForm:options items="${insPrvdrList}" itemValue="id" itemLabel="ins.name"   />
									</springForm:select>
									<springForm:errors path="insPrvdrId" cssClass="error text-danger" />
								  </div>
							</div>
					</div>		
					</c:when>
					</c:choose>
					
					<div class="col-sm-2">
						<div class="form-group required">
						 	<label class="control-label col-sm-5" for="startDate">Start Date</label>
							<div class="col-sm-7">
								<fmt:formatDate value="${contract.startDate}" var="dateString" pattern="MM/dd/yyyy" />
								<springForm:input  value="${dateString}" var="startDate" path="startDate" class="form-control" id="startDate" placeholder="Start Date" />
								<springForm:errors path="startDate" cssClass="error text-danger" />
							</div>
						</div>
					</div>
					<div class="col-sm-2">	
						<div class="form-group required">
						 	<label class="control-label col-sm-5" for="endDate">End Date</label>
							<div class="col-sm-7">
								<fmt:formatDate value="${contract.endDate}" var="dateString" pattern="MM/dd/yyyy" />
								<springForm:input value="${dateString}"  path="endDate" class="form-control" id="endDate" placeholder="End Date" />
								<springForm:errors path="endDate" cssClass="error text-danger" />
							</div>
						</div>
					</div>	
				</div>
				
				<div class="col-sm-offset-10 col-sm-2">
					<c:choose>
						<c:when test="${id != null && contract.activeInd == 89}"> 
						 	<button type="button" class="btn btn-primary" id="updateButton" name="update" onclick="return modifyContract();" >Update</button>
						 	<button type="button" class="btn btn-primary" id="deleteButton" name="delete" >Delete</button>
						</c:when>
						<c:when test="${contract.id == null}">
							<button type="button" class="btn btn-primary" id="updateButton" onclick="return addContract();" name="add" >Add</button>
							<button type="button" class="btn btn-primary" id="resetButton" >Reset</button>
						</c:when>
					</c:choose>
				</div>
			</springForm:form>
	 	</div>
	 </div>	
</div>
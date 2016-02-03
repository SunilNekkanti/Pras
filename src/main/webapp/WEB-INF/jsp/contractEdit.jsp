<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
    prefix="springForm"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="panel with-nav-tabs panel-primary">
		<div class="panel-heading">
						<ul class="nav nav-tabs">
                            <li class="active"><a href="#" data-toggle="tab">Contract</a></li>
                        </ul>
          </div>
		<div class="panel-body" id="tablediv">
			<springForm:form method="POST" commandName="contract" action="save.do" class="form-horizontal" role="form">
						 		 
				<div class="form-group">
					<label class="control-label col-sm-2" for="contractNBR">Contract NBR:</label>
					<div class="col-sm-6">
						<springForm:hidden path="id" />
						<springForm:hidden path="referenceContract.id" />
						<springForm:hidden path="referenceContract.activeInd" value="Y" />
						 <c:choose>
						 	 <c:when test="${contract.referenceContract.prvdr != null}">
								<springForm:hidden path="referenceContract.prvdr.id" />
							</c:when>
							<c:when test="${contract.referenceContract.ins != null}">
								<springForm:hidden path="referenceContract.ins.id" />
							</c:when>
						</c:choose>
						
					
						<springForm:hidden path="referenceContract.createdBy" value="Mohanasundharam"/>
						<springForm:input path="contractNBR" class="form-control" id="contractNBR" placeholder="Contract NBR" />
						<springForm:errors path="contractNBR" cssClass="error" />
					</div>
				</div>
				 		 
				<div class="form-group">
					<label class="control-label col-sm-2" for="PMPM">PMPM:</label>
					<div class="col-sm-6">
						<springForm:input path="PMPM" class="form-control" id="mobilePhone" placeholder="PMPM" />
						<springForm:errors path="PMPM" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
				 	<label class="control-label col-sm-2" for="startDate">Start Date:</label>
					<div class="col-sm-6">
						<fmt:formatDate value="${contract.startDate}" var="dateString" pattern="MM/dd/yyyy" />
						<springForm:input  value="${dateString}" var="startDate" path="startDate" class="form-control" id="startDate" placeholder="Start Date" />
						<springForm:errors path="startDate" cssClass="error" />
					</div>
				</div>
				
				<div class="form-group">
				 	<label class="control-label col-sm-2" for="startDate">End Date:</label>
					<div class="col-sm-6">
						<fmt:formatDate value="${contract.endDate}" var="dateString" pattern="MM/dd/yyyy" />
						<springForm:input value="${dateString}"  path="endDate" class="form-control" id="endDate" placeholder="End Date" />
						<springForm:errors path="endDate" cssClass="error" />
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
				 
			 
					<div class="col-sm-offset-6 col-sm-4">
					
					 
					 	<c:choose>
						 		<c:when test="${contract.id != null && contract.activeInd == 89}"> 
								 	<button type="submit" class="btn btn-primary" id="updateButton" name="update" >Update</button>
								 	<button type="submit" class="btn btn-primary" id="deleteButton" name="delete" >Delete</button>
								 </c:when>
								 <c:when test="${contract.id == null}">
									<button type="submit" class="btn btn-primary" id="updateButton" name="add" >Add</button>
									<button type="submit" class="btn btn-primary" id="resetButton" >Reset</button>
								</c:when>
						</c:choose>
					</div>
			
			
			</springForm:form>
 	</div>
</div>


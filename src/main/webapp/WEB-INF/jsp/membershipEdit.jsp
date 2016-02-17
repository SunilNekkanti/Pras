<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
    prefix="springForm"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <script src="/Pras/resources/js/validation.js"></script>
 <c:choose>
 	<c:when test="${membership.id != null}"> 
	<script>
		$(document).ready(function(){	 
		removePlaceHolder();
		membershipValidation();
		});
	</script>
	</c:when>	 
</c:choose>
 <div class="panel-group">
	<div class="panel panel-primary">
		<div class="panel-heading">Profile</div>
		<div class="panel-body" id="tablediv">
			<springForm:form method="POST" id="membership" data-toggle="validator" commandName="membership" action="/Pras/membership/${membership.id}/save.do" class="form-horizontal" role="form">
				<div class="row">
					<div class="col-sm-12">
						<div class="col-sm-6">
							<div class="form-group required">
								<label class="control-label col-sm-4" for="firstName">First Name</label>
								<div class="col-sm-8">
									<springForm:hidden path="id" />
									<springForm:input path="firstName"  maxlength="20" class="form-control" id="firstName"  placeholder="First Name"/>
									<springForm:errors path="firstName" cssClass="error text-danger" />
								</div>
							</div>
							 
							<div class="form-group required">
								<label class="control-label col-sm-4" for="lastName">Last Name</label>
								<div class="col-sm-8">
									<springForm:input path="lastName" maxlength="20" class="form-control" id="lastName" placeholder="Last Name" />
									<springForm:errors path="lastName" cssClass="error text-danger" />
								</div>
							</div>
							<div class="form-group required">
								<label class="control-label col-sm-4" for="gender">Gender</label>
								<div class="col-sm-8">
									<springForm:select path="genderId"  class="form-control" id="gender">
										<springForm:option  value="${null}" label="Select One"/>
							    		<springForm:options items="${genderList}"  itemValue="id" itemLabel="description" />
									</springForm:select>
									<springForm:errors path="genderId" cssClass="error text-danger" />
								</div>
							</div>
							<div class="form-group required">
							 	<label class="control-label col-sm-4" for="dob">DOB</label>
								<div class="col-sm-8">
									<fmt:formatDate value="${membership.dob}" var="dateString" pattern="MM/dd/yyyy" />
									<springForm:input path="dob" value="${dateString}" class="form-control datepicker" id="dob" placeholder="DOB" />
									<springForm:errors path="dob" cssClass="error text-danger" />
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-sm-4" for="ethinicCode">Ethinicity</label>
								<div class="col-sm-8">
									<springForm:select path="ethinicCode" class="form-control" id="ethinicCode" >
							    		<springForm:options items="${ethinicityList}" itemValue="id" itemLabel="description"/>
									</springForm:select>
									<springForm:errors path="ethinicCode" cssClass="error text-danger" />
								</div>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="form-group required">
								<label class="control-label col-sm-4" for="status">Status</label>
								<div class="col-sm-8">
									<springForm:select path="status" class="form-control" id="status">
										<springForm:option  value="${null}" label="Select One"/>
							    		<springForm:options items="${statusList}" itemValue="id" itemLabel="description"   />
									</springForm:select>
									<springForm:errors path="status" cssClass="error text-danger" />
								  </div>
							</div>
								
							<div class="form-group required">
								<label class="control-label col-sm-4" for="county">County</label>
								<div class="col-sm-8">
									<springForm:select path="countyCode" class="form-control" id="county" >
										<springForm:option  value="${null}" label="Select One"/>
							    		<springForm:options items="${countyList}"  itemValue="code" itemLabel="description"  />
									</springForm:select>
									<springForm:errors path="countyCode" cssClass="error text-danger" />
								</div>
							</div>
							<div class="form-group drequired">
								<label class="control-label  drequired col-sm-4" for="medicaidNo">Medicaid No</label>
								<div class="col-sm-8">
									<springForm:input path="medicaidNo" maxlength="12" class="form-control" id="medicaidNo" placeholder="Medicaid No" />
									<springForm:errors path="medicaidNo" cssClass="error text-danger" />
								</div>
							</div>
							<div class="form-group drequired">
								<label class="control-label col-sm-4" for="medicareNo">Medicare No</label>
								<div class="col-sm-8">
									<springForm:input path="medicareNo" maxlength="12" class="form-control" id="medicareNo" placeholder="medicare No" />
									<springForm:errors path="medicareNo" cssClass="error text-danger" />
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-12">	 
					<div class="col-sm-offset-6 col-sm-4">
						<c:choose>
							 <c:when test="${membership.id != null && membership.activeInd == 89}"> 
								<button type="submit" class="btn btn-primary" name = "update" id="updateButton">Update</button>
								<button type="submit" class="btn btn-primary" name ="delete"id="deleteButton">Delete</button>
							</c:when>
						</c:choose>				
					</div>
				</div>	
			</springForm:form>
		</div>
	</div>
</div>

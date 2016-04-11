<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
    prefix="springForm"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="context" value="${pageContext.request.contextPath}/${userpath}" />
 <c:choose>
 	<c:when test="${membership.id != null}"> 
	<script>
		$(document).ready(function(){	 
		removePlaceHolder();
		});
	</script>
	</c:when>
</c:choose>	
<div class="panel-group" style="padding-left:0px;">
	<div class="panel panel-success">
		<div class="panel-heading">Membership Profile</div>
		<div class="panel-body" id="tablediv">
			<div class="col-sm-12" style="min-height:300px;">
				<springForm:form method="POST" commandName="membership" action="save.do" class="form-horizontal" role="form">
					<div class="row">
						<div class="col-sm-12">
							<div class="col-sm-6">
								<div class="form-group">
									<label class="control-label col-sm-4" for="firstName">First Name</label>
									<div class="col-sm-8">
										<springForm:hidden path="id" />
										<springForm:input path="firstName" class="form-control" id="firstName" placeholder="First Name" />
										<springForm:errors path="firstName" cssClass="error text-danger" />
									</div>
								</div>
					 			<div class="form-group">
									<label class="control-label col-sm-4" for="lastName">Last Name</label>
									<div class="col-sm-8">
										<springForm:input path="lastName" class="form-control" id="lastName" placeholder="Last Name" />
										<springForm:errors path="lastName" cssClass="error text-danger" />
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-4" for="gender">Gender</label>
									<div class="col-sm-8">
										<springForm:select path="genderId"  class="form-control" id="gender">
								    		<springForm:options items="${genderList}"    itemValue="id" itemLabel="description"   />
										</springForm:select>
										<springForm:errors path="genderId.description" cssClass="error text-danger" />
									</div>
								</div>
								<div class="form-group">
								 	<label class="control-label col-sm-4" for="dob">DOB</label>
									<div class="col-sm-8">
										<fmt:formatDate value="${membership.dob}" var="dateString" pattern="MM/dd/yyyy" />
										<springForm:input path="dob" value="${dateString}" class="form-control datepicker"  id="dob" placeholder="DOB" />
										<springForm:errors path="dob" cssClass="error text-danger" />
									</div>
								</div>
								
								<div class="form-group">
									<label class="control-label col-sm-4" for="ethinicCode">Ethinicity</label>
									<div class="col-sm-8">
										<springForm:select path="ethinicCode.id" class="form-control" id="ethinicCode" >
											<springForm:option value="${null}" label="Select Ethinicity"   />
								    		<springForm:options items="${ethinicityList}" itemValue="id" itemLabel="description"/>
										</springForm:select>
										<springForm:errors path="ethinicCode" cssClass="error text-danger" />
									</div>
								</div>
					 			
					 		</div>
							<div class="col-sm-6">
								<div class="form-group">
									<label class="control-label col-sm-4" for="status">Status</label>
									<div class="col-sm-8">
										<springForm:select path="status" class="form-control" id="status">
											<springForm:option value="${null}" label="Select Status"   />
								    		<springForm:options items="${statusList}"   itemValue="id" itemLabel="description"   />
										</springForm:select>
										<springForm:errors path="status.description" cssClass="error text-danger" />
									  </div>
								</div>
					 		 	<div class="form-group">
									<label class="control-label col-sm-4" for="county">County</label>
									<div class="col-sm-8">
										<springForm:select path="countyCode" class="form-control" id="county" >
											<springForm:option value="${null}" label="Select County"   />
								    		<springForm:options items="${countyList}"   itemValue="code" itemLabel="description"   />
										</springForm:select>
										<springForm:errors path="countyCode.description" cssClass="error text-danger" />
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-4" for="medicaidNo">Medicaid No</label>
									<div class="col-sm-8">
										<springForm:input path="medicaidNo" class="form-control" id="medicaidNo" placeholder="Medicaid No" />
										<springForm:errors path="medicaidNo" cssClass="error text-danger" />
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-4" for="medicaidNo">Medicare No</label>
									<div class="col-sm-8">
										<springForm:input path="medicareNo" class="form-control" id="medicareNo" placeholder="Medicare No" />
										<springForm:errors path="medicareNo" cssClass="error text-danger" />
									</div>
								</div>
								
								
							</div>
						</div>
					</div>
				</springForm:form>
				<div class="col-sm-offset-2 col-sm-10">
					<a href="${context}/membership/${id}">Click Here</a> Edit membership profile
				</div>
			</div>	
 		</div>
	</div>
</div>




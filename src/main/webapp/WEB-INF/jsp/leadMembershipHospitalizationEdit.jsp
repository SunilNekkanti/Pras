<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="springForm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="context"
	value="${pageContext.request.contextPath}/${userpath}" />

<div class="panel-group" >
	<div class="panel panel-success">
		<div class="panel-heading">Lead Membership Hospitalization
				<span class="clrRed">${Message}</span>
			<button class="btn btn-danger pull-right btn-xs"	onclick="return leadMbrHospList();">
			<span class="glyphicon glyphicon-plus-sign "></span> Hospitalization List</button>
		</div>
		<div class="panel-body" id="tablediv">
			<springForm:form commandName="leadMembershipHospitalization" class="form-horizontal" action="save.do">
				<springForm:hidden path="id"  />
				
				
				<div class="form-group required col-sm-3">
					<div class="col-sm-10">
						<label  class="control-label" for="name">Hospital</label>
						<springForm:select path="hospital" class="form-control required">
							<springForm:option value="">Select one</springForm:option>
							<springForm:options items="${hospitalList}" itemValue="id" itemLabel="name" />
						</springForm:select>
						<springForm:errors path="hospital"	cssClass="error text-danger" />
					</div>
				</div>
				
				<div class="form-group col-sm-3">
					<div class="col-sm-10">
						<label  class="control-label" for="name">Report</label>
						<springForm:input path="report" class="form-control" placeholder="Report" />
						<springForm:errors path="report"	cssClass="error text-danger" />
					</div>
				</div>
				
				<div class="form-group col-sm-3">
					<div class="col-sm-10">
						<label  class="control-label">Plan Desc</label>
						<springForm:input path="planDesc" class="form-control" placeholder="Plan Desc" />
						<springForm:errors path="planDesc"	cssClass="error text-danger" />
					</div>
				</div>
				
				<div class="form-group col-sm-3">
					<div class="col-sm-10">
						<label  class="control-label" for="name">Auth Num</label>
						<springForm:input path="authNum" class="form-control" placeholder="Auth Num" />
						<springForm:errors path="authNum"	cssClass="error text-danger" />
					</div>
				</div>
				
				<div class="form-group col-sm-3">
					<div class="col-sm-10">
						<label  class="control-label" for="name">Prior Admits</label>
						<springForm:input path="priorAdmits" class="form-control" placeholder="Prior Admits" />
						<springForm:errors path="priorAdmits"	cssClass="error text-danger" />
					</div>
				</div>
				
				<div class="form-group col-sm-3">
					<div class="col-sm-10">
						<label  class="control-label" for="name">Admit Date</label>
						<springForm:input path="admitDate" class="form-control" placeholder="Admit Date" />
						<springForm:errors path="admitDate"	cssClass="error text-danger" />
					</div>
				</div>
				
				<div class="form-group col-sm-3">
					<div class="col-sm-10">
						<label  class="control-label" for="name">ExpDis Date</label>
						<springForm:input path="expDisDate" class="form-control" placeholder="ExpDisDate" />
						<springForm:errors path="expDisDate"	cssClass="error text-danger" />
					</div>
				</div>	
				
				
				
				
				<div class="col-sm-12 hospitalization_details">
						
				</div>
				<div class="col-sm-12">		
							
							<button type="button" class='addHospitalizationDetailsRow'>+ Add Hospitalization Details</button>
				
				</div>
			
			
			<div class="col-sm-offset-8 col-sm-4">
					<c:choose>
						<c:when
							test="${leadMembershipHospitalization.id != null && leadMembershipHospitalization.activeInd == 89}">
							<button type="button" class="btn btn-success btn-sm"
								id="updateButton" name="update"
								onclick="return modifyLeadMbrHosp(${leadMembershipHospitalization.id})">Update</button>
							<button type="button" class="btn btn-success btn-sm"
								id="deleteButton" class="delete"
								onclick="return deleteLeadMbrHosp(${leadMembershipHospitalization.id})"
								name="delete">Delete</button>
						</c:when>
						<c:when test="${leadMembershipHospitalization.id == null}">
							<button type="button" class="btn btn-success btn-sm"
								id="addButton" name="add" onclick="return addLeadMbrHosp()">Add</button>
							<button type="button" class="btn btn-success btn-sm"
								id="resetButton">Reset</button>
						</c:when>
					</c:choose>
				</div>	
			</springForm:form>
		</div>
	</div>
</div>

<script>
$(document).ready(function(){
	if("${leadMembershipHospitalization.id}" == ""){
		addHosptializationDetailsRow();
	}
});
</script>
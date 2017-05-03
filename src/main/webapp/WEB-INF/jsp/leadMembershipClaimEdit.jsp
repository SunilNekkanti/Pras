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
		<div class="panel-heading">Lead Membership Claim
				<span class="clrRed">${Message}</span>
			<button class="btn btn-danger pull-right btn-xs"	onclick="return claimList();">
			<span class="glyphicon glyphicon-plus-sign "></span> Claim List</button>
		</div>
		<div class="panel-body" id="tablediv">
			<springForm:form commandName="leadMembershipClaim" class="form-horizontal" action="save.do">
				<springForm:hidden path="id"  />
				
				<div class="form-group required col-sm-3">
					<div class="col-sm-10">
						<label  class="control-label" for="name">Claim Type</label>
						<springForm:select path="claimType" class="form-control required">
							<springForm:option value="">Select one</springForm:option>
							<springForm:options items="${claimTypeList}" itemValue="name" itemLabel="name" />
						</springForm:select>
						<springForm:errors path="claimType"	cssClass="error text-danger" />
					</div>
				</div>	
				
				<div class="form-group col-sm-3">
					<div class="col-sm-10">
						<label  class="control-label" for="name">Facility Type</label>
						<springForm:select path="facilityType" class="form-control" id="facilityType">
							<springForm:option value="">Select one</springForm:option>
							<springForm:options items="${facilityTypeList}" itemValue="id" itemLabel="shortName" />
						</springForm:select>
						<springForm:errors path="facilityType"	cssClass="error text-danger" />
					</div>
				</div>
				
				<div class="form-group col-sm-3">
					<div class="col-sm-10">
						<label  class="control-label" for="name">Bill Type</label>
						<springForm:select path="billType" class="form-control" id="billType">
							<springForm:option value="">Select one</springForm:option>
							<springForm:options items="${billTypeList}" itemValue="id" itemLabel="shortName" />
						</springForm:select>
						<springForm:errors path="billType"	cssClass="error text-danger" />
					</div>
				</div>			
				
				<div class="form-group col-sm-3">
					<div class="col-sm-10">
						<label  class="control-label" for="name">Frequency Type</label>
						<springForm:select path="frequencyType" class="form-control" id="frequencyType">
							<springForm:option value="">Select one</springForm:option>
							<springForm:options items="${frequencyTypeList}" itemValue="id" itemLabel="shortName" />
						</springForm:select>
						<springForm:errors path="frequencyType"	cssClass="error text-danger" />
					</div>
				</div>	
				
				<div class="form-group col-sm-3">
					<div class="col-sm-10">
						<label  class="control-label" for="name">Discharge Status</label>
						<springForm:input path="dischargeStatus" class="form-control" placeholder="Discharge Statuss" />
						<springForm:errors path="dischargeStatus"	cssClass="error text-danger" />
					</div>
				</div>	
				
				<div class="form-group col-sm-6 required">
					<div class="col-sm-12">
						<label  class="control-label" for="name">Diagnoses: <span>
									<a href="javascript:void(0)"  id="icdModal" 
									class="btn btn-success btn-xs white-text"> <span
									class="glyphicon glyphicon-plus-sign"></span>ICD
								</a> 
							</span></label>
						<div>
							<springForm:input path="diagnosis" class="form-control checkData" placeholder="diagnosis" readonly="true" />
							
							<springForm:errors path="diagnosis"	cssClass="error text-danger" />
						
						</div>
						
					</div>	
				</div>	
				
				
				<div class="col-sm-12 claim_details">
						
				</div>
				<div class="col-sm-12">		
							
							<button type="button" class='addClaimDetailsRow'>+ Add Claim Details</button>
				
				</div>
			
			
			<div class="col-sm-offset-8 col-sm-4">
					<c:choose>
						<c:when
							test="${leadMembershipClaim.id != null && leadMembershipClaim.activeInd == 89}">
							<button type="button" class="btn btn-success btn-sm"
								id="updateButton" name="update"
								onclick="return modifyLeadMbrClaim(${leadMembershipClaim.id})">Update</button>
							<button type="button" class="btn btn-success btn-sm"
								id="deleteButton" class="delete"
								onclick="return deleteLeadMbrClaim(${leadMembershipClaim.id})"
								name="delete">Delete</button>
						</c:when>
						<c:when test="${leadMembershipClaim.id == null}">
							<button type="button" class="btn btn-success btn-sm"
								id="addButton" name="add" onclick="return addLeadMbrClaim()">Add</button>
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
	if("${leadMembershipClaim.id}" == ""){
		addClaimDetailsRow();
	}
});
</script>
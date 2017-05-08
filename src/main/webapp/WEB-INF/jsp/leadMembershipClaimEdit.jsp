<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="springForm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
						<c:set var="count" value="0" scope="page" />
						<c:forEach var="claimDetails" items="${leadMembershipClaim.leadMbrClaimDetailsList}">
							    <div class="col-sm-12 claimDetailsList list">	
							    	<div class="form-group col-sm-2 required"><div class="col-sm-12"><label class="control-label">Start Date</label>
							    		<fmt:formatDate value="${claimDetails.claimStartDate}" var="startDate" pattern="MM/dd/yyyy" />
							    		<input value="${startDate}" name ="leadMbrClaimDetailsList[][claimStartDate]" id="leadMbrClaimDetailsList${count}.claimStartDate" class="checkData form-control startDate datepicker" />
							    	</div></div>
									<div class="form-group col-sm-2  required"><div class="col-sm-12"><label class="control-label">End Date</label> 
										<fmt:formatDate value="${claimDetails.claimEndDate}" var="endDate" pattern="MM/dd/yyyy" />
										<input value="${endDate}" name="leadMbrClaimDetailsList[][claimEndDate]" id="leadMbrClaimDetailsList${count}.claimEndDate" class="checkData form-control endDate datepicker" /></div></div>
									<div class="form-group col-sm-2">
										<div class="col-sm-12">
											<label>
												<a href="javascript:void(0)" id="addCPT${count}" class="btn btn-success btn-xs white-text addModalCPT"> 
													<span class="glyphicon glyphicon-plus-sign"></span>
												CPT</a> 
												<a href="javascript:void(0)" id="removeCPT${count}" class="removeCPT btn btn-success btn-xs white-text"> 
													<span class="glyphicon glyphicon-minus-sign"></span>
												CPT</a>
											</label>
											<select name="leadMbrClaimDetailsList[][cpt][id]" id="leadMbrClaimDetailsList${count}.cpt.id" class="form-control">
												<option value=${claimDetails.cpt.id}">${claimDetails.cpt.shortDescription}</option>
											</select>
										</div>
									</div>
									<div class="form-group col-sm-2  required">
										<div class="col-sm-12">
											<label class="control-label">RiskRecon
											</label>
											<select name="leadMbrClaimDetailsList[][riskReconCosDes]" id="leadMbrClaimDetailsList${count}.riskReconCosDes" class="checkData form-control"  >
													<option value="">Select One</option>
													<c:forEach var="riskRecon" items="${riskReconList}">
													<c:choose>
													  <c:when  test = "${claimDetails.riskReconCosDes.id == riskRecon.id}">
													   <option value="${riskRecon.id}" selected="selected">${riskRecon.name}</option>
													   </c:when>
													   <c:otherwise>
													    <option value="${riskRecon.id}">${riskRecon.name}</option>
													   </c:otherwise>
												    </c:choose>
														
												    </c:forEach>
												    
											</select>
										</div>
									</div>';
									<div class="form-group col-sm-2"><div class="col-sm-12">
										<label>Srv Prvdr Name</label>
										<input name="leadMbrClaimDetailsList[][srvProviderName]" id="leadMbrClaimDetailsList${count}.srvProviderName"  value="${claimDetails.srvProviderName}" class="form-control"  />
										</div>
									</div>
									<div class="form-group col-sm-2">
										<div class="col-sm-12">
											<label>Srv Prvdr Code</label>
											<div class="col-sm-8">
												<input name="leadMbrClaimDetailsList[][srvProvider]" id="leadMbrClaimDetailsList${count}.srvProvider" value="${claimDetails.srvProvider}" class="form-control" />
											</div>
										<div class="col-sm-4"><button type="button" onclick="deleteClaimDetailsRow()" class="deleteClaimDetailsRow btn btn-success btn-xs white-text">- Delete</button>
										</div>
									</div>
								</div>
								</div>
									
						</c:forEach> 
    								
			  			
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
	if(${fn:length(leadMembershipClaim.leadMbrClaimDetailsList)} < 2)
		$(".deleteClaimDetailsRow").hide();
});
</script>
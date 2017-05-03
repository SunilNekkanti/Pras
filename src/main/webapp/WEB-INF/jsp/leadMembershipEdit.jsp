<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="springForm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="context"
	value="${pageContext.request.contextPath}/${userpath}" />
<div class="leadMembershipDetails">
	<div class="panel-group">
		<div class="panel panel-success">
			<div class="panel-heading">
				Lead Membership Profile <span class="clrRed">${Message}</span> <a
					class="btn btn-danger pull-right btn-xs white-text"
					href="${context}/leadMembershipList"> <span
					class="glyphicon glyphicon-plus-sign "></span>Lead List
				</a>
			</div>
			<div class="panel-body leadMembership" id="tablediv">
				<springForm:form method="POST" id="leadMembership"
					data-toggle="validator" commandName="leadMembership"
					action="save.do"
					class="form-horizontal" role="form">
					<div class="row"> 
						<div class="col-sm-12">
							<div class="col-sm-3">
								<div class="form-group required">
									<label class="control-label col-sm-4" for="firstName">First
										Name</label>
									<div class="col-sm-8">
										<springForm:hidden path="id" />
										<springForm:input path="firstName" maxlength="20"
											class="form-control" id="firstName" placeholder="First Name" />
										<springForm:errors path="firstName"
											cssClass="error text-danger" />
									</div>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group required">
									<label class="control-label col-sm-4" for="lastName">Last
										Name</label>
									<div class="col-sm-8">
										<springForm:input path="lastName" maxlength="20"
											class="form-control" id="lastName" placeholder="Last Name" />
										<springForm:errors path="lastName" cssClass="error text-danger" />
									</div>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group required">
									<label class="control-label col-sm-4" for="gender">Gender</label>
									<div class="col-sm-8">
										<springForm:select path="genderId" class="form-control"
											id="gender">
											<springForm:option value="${null}" label="Select One" />
											<springForm:options items="${genderList}" itemValue="id"
												itemLabel="description" />
										</springForm:select>
										<springForm:errors path="genderId" cssClass="error text-danger" />
									</div>
								</div>
							</div>
	
							<div class="col-sm-3">
								<div class="form-group required">
									<label class="control-label col-sm-4" for="dob">DOB</label>
									<div class="col-sm-8">
										<fmt:formatDate value="${membership.dob}" var="dateString"
											pattern="MM/dd/yyyy" />
										<springForm:input path="dob" value="${dateString}"
											class="form-control datepicker" id="dob" placeholder="DOB" />
										<springForm:errors path="dob" cssClass="error text-danger" />
									</div>
								</div>
							</div>
	
							<!-- <div class="form-group">
									<label class="control-label col-sm-4" for="ethinicCode">Ethinicity</label>
									<div class="col-sm-8">
										<springForm:select path="ethinicCode" class="form-control" id="ethinicCode" >
								    		<springForm:options items="${ethinicityList}" itemValue="id" itemLabel="description"/>
										</springForm:select>
										<springForm:errors path="ethinicCode" cssClass="error text-danger" />
									</div>
								</div> -->
						</div>
						<div class="col-sm-12">
							<div class="col-sm-3">
								<div class="form-group required">
									<label class="control-label col-sm-4" for="status">Status</label>
									<div class="col-sm-8">
										<springForm:select path="status" class="form-control"
											id="status">
											<springForm:option value="${null}" label="Select One" />
											<springForm:options items="${statusList}" itemValue="id"
												itemLabel="description" />
										</springForm:select>
										<springForm:errors path="status" cssClass="error text-danger" />
									</div>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group">
									<label class="control-label col-sm-4" for="county">County</label>
									<div class="col-sm-8">
										<springForm:select path="countyCode" class="form-control"
											id="county">
											<springForm:option value="${null}" label="Select One" />
											<springForm:options items="${countyList}" itemValue="code"
												itemLabel="description" />
										</springForm:select>
										<springForm:errors path="countyCode"
											cssClass="error text-danger" />
									</div>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group drequired">
									<label class="control-label  drequired col-sm-4"
										for="medicaidNo">Medicaid No</label>
									<div class="col-sm-8">
										<springForm:input path="medicaidNo" maxlength="12"
											class="form-control" id="medicaidNo" placeholder="Medicaid No" />
										<springForm:errors path="medicaidNo"
											cssClass="error text-danger" />
									</div>
								</div>
							</div>
							<div class="col-sm-3">
								<div class="form-group drequired">
									<label class="control-label col-sm-4" for="medicareNo">Medicare
										No</label>
									<div class="col-sm-8">
										<springForm:input path="medicareNo" maxlength="12"
											class="form-control" id="medicareNo" placeholder="medicare No" />
										<springForm:errors path="medicareNo"
											cssClass="error text-danger" />
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-sm-offset-9 col-sm-3">
						<c:choose>
							<c:when test="${leadMembership.id != null && leadMembership.activeInd == 89}">
								<button type="button" class="btn btn-success btn-sm"
									name="update" id="updateButton"
									onclick="return modifyLeadMbrDetails();">Update</button>
								<button type="button" class="btn btn-success btn-sm"
									name="delete" id="deleteButton"
									onclick="return deleteLedMbrDetails();">Delete</button>
							</c:when>
							<c:when test="${leadMembership.id == null}">
								<button type="submit" class="btn btn-success btn-sm"
									id="addButton" name="add" onclick="return addMembership()">Add</button>
								<button type="submit" class="btn btn-success btn-sm"
									id="resetButton">Reset</button>
							</c:when>
						</c:choose>
					</div>
					<div class="col-sm-12">
						<div class="col-sm-offset-10 col-sm-2">
							<c:choose>
								<c:when
									test="${membership.id != null && membership.activeInd == 89}">
									<button type="button" class="btn btn-success btn-sm"
										name="update" id="updateButton"
										onclick="return modifyMbrDetails()">Update</button>
									<button type="button" class="btn btn-success btn-sm"
										name="delete" id="deleteButton"
										onclick="return deleteMbrDetails()">Delete</button>
								</c:when>
							</c:choose>
						</div>
					</div>
				</springForm:form>
			</div>
		</div>
	</div>
</div>	
<script>
function addMembership() {
		var url = getContextPath() + 'leadMembership/save.do?add';
		var dataList = $("#leadMembership").serialize();
		$.ajax({
			type : "POST",
			url : url,
			data : dataList,
			success : function(data) {
				$('.leadMembershipDetails').html(data);
				alert("success");
			},
			error : function(data) {
				alert('Lead Membership Add Contact Error ' + data);
			}
		});
		return false;
	}
</script>
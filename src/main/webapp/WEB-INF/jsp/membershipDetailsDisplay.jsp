<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="springForm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="panel-group">
	<div class="panel panel-success">
		<div class="panel-heading">
			Membership Insurance
			<button class="btn btn-danger pull-right btn-xs"
				onclick="return mbrInsList();">
				<span class="glyphicon glyphicon-plus-sign "></span> Membership
				Insurance List
			</button>

		</div>
		<div class="panel-body" id="tablediv">
			<springForm:form method="POST" commandName="membershipInsurance"
				action="save.do" class="form-horizontal" role="form">
				<div class="col-sm-12">
					<div class="col-sm-4">
						<div class="form-group required">
							<label class="control-label col-sm-5" for=insId>Insurance</label>
							<div class="col-sm-7">
								<springForm:select path="insId" class="form-control" id="insId">
									<springForm:options items="${insList}" itemValue="id"
										itemLabel="name" />
								</springForm:select>

							</div>
						</div>
					</div>

					<div class="col-sm-4">
						<div class="form-group">
							<label class="control-label col-sm-5" for="dob">New
								Benifits</label>
							<div class="col-sm-7">

								<springForm:hidden path="id" />

								<springForm:input path="newBenifits" class="form-control"
									id="newBenifits" placeholder="newBenifits" />
								<springForm:errors path="newBenifits"
									cssClass="error text-danger" />
							</div>
						</div>
					</div>

					<div class="col-sm-4">
						<div class="form-group required">
							<label class="control-label required col-sm-5" for="activityDate">Activity
								Date</label>
							<div class="col-sm-7">
								<fmt:formatDate value="${membershipInsurance.activityDate}"
									var="dateString" pattern="MM/dd/yyyy" />
								<springForm:input path="activityDate" value="${dateString}"
									class="form-control datepicker" onfocus="datePicker()"
									id="activityDate" placeholder="activityDate" />
								<springForm:errors path="activityDate"
									cssClass="error text-danger" />
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-12">
					<div class="col-sm-4">
						<div class="form-group required">
							<label class="control-label required col-sm-5"
								for="activityMonth">Activity Month</label>
							<div class="col-sm-7">
								<springForm:input path="activityMonth" maxlength="4"
									class="form-control" id="activityMonth" placeholder="MMYY" />
								<springForm:errors path="activityMonth"
									cssClass="error text-danger" />
							</div>
						</div>
					</div>

					<div class="col-sm-4">
						<div class="form-group required">
							<label class="control-label col-sm-5" for="effStartDate">Effective
								Start Date</label>
							<div class="col-sm-7">
								<fmt:formatDate value="${membershipInsurance.effStartDate}"
									var="dateString" pattern="MM/dd/yyyy" />
								<springForm:input path="effStartDate" value="${dateString}"
									class="form-control datepickerfrom" id="effStartDate"
									placeholder="effStartDate" />
								<springForm:errors path="effStartDate"
									cssClass="error text-danger" />
							</div>
						</div>
					</div>

					<div class="col-sm-4">
						<div class="form-group required">
							<label class="control-label col-sm-5" for="effEndDate">Effective
								End Date</label>
							<div class="col-sm-7">
								<fmt:formatDate value="${membershipInsurance.effEndDate}"
									var="dateString" pattern="MM/dd/yyyy" />
								<springForm:input path="effEndDate" value="${dateString}"
									class="form-control datepickerto" id="effEndDate"
									placeholder="effEndDate" />
								<springForm:errors path="effEndDate"
									cssClass="error text-danger" />
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-12">
					<div class="col-sm-4">
						<div class="form-group">
							<label class="control-label col-sm-5" for="product">Product</label>
							<div class="col-sm-7">
								<springForm:input path="product" class="form-control"
									id="product" placeholder="product" />
								<springForm:errors path="product" cssClass="error text-danger" />
							</div>
						</div>
					</div>

					<div class="col-sm-4">
						<div class="form-group">
							<label class="control-label col-sm-5" for="productLabel">Product
								Label</label>
							<div class="col-sm-7">
								<springForm:input path="productLabel" class="form-control"
									id="productLabel" placeholder="productLabel" />
								<springForm:errors path="productLabel"
									cssClass="error text-danger" />
							</div>
						</div>
					</div>

					<div class="col-sm-4">
						<div class="form-group">
							<label class="control-label col-sm-5" for="planId">Plan
								Id</label>
							<div class="col-sm-7">
								<springForm:input path="planId" class="form-control" id="planId"
									placeholder="planId" />
								<springForm:errors path="planId" cssClass="error text-danger" />
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-12">
					<div class="col-sm-4">
						<div class="form-group">
							<label class="control-label col-sm-5" for="dob">Src Sys
								Mbr Nbr</label>
							<div class="col-sm-7">
								<springForm:input path="srcSysMbrNbr" class="form-control"
									id="srcSysMbrNbr" placeholder="srcSysMbrNbr" />
								<springForm:errors path="srcSysMbrNbr"
									cssClass="error text-danger" />
							</div>
						</div>
					</div>

					<div class="col-sm-4">
						<div class="form-group">
							<label class="control-label col-sm-5" for="dob">Risk Flag</label>
							<div class="col-sm-7">
								<springForm:input path="riskFlag" class="form-control"
									id="riskFlag" placeholder="riskFlag" />
								<springForm:errors path="riskFlag" cssClass="error text-danger" />
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-offset-6 col-sm-4">
					<c:choose>
						<c:when
							test="${membershipInsurance.id != null && membershipInsurance.activeInd == 89}">
							<button type="button" class="btn btn-success btn-sm"
								id="updateButton" name="update"
								onclick="return modifyMbrInsDetails(${membershipInsurance.id})">Update</button>
							<button type="button" class="btn btn-success btn-sm"
								id="deleteButton" class="delete"
								onclick="return deleteMbrInsDetails(${membershipInsurance.id})"
								name="delete">Delete</button>
						</c:when>
						<c:when test="${membershipInsurance.id == null}">
							<button type="button" class="btn btn-success btn-sm"
								id="updateButton" name="add" onclick="return addMbrInsDetails()">Add</button>
							<button type="button" class="btn btn-success btn-sm"
								id="resetButton">Reset</button>
						</c:when>
					</c:choose>
				</div>
			</springForm:form>

		</div>
	</div>
</div>

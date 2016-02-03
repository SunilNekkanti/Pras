<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
    prefix="springForm"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="panel-group">
	<div class="panel panel-primary">
		<div class="panel-heading">Profile</div>
		<div class="panel-body" id="tablediv">
			<springForm:form method="POST" commandName="membershipInsurance" action="save.do" class="form-horizontal" role="form">
				<div class="form-group">
					<label class="control-label col-sm-2" for=insId>insId:</label>
					<div class="col-sm-6">
						<springForm:select path="insId"  class="form-control" id="insId">
				    		<springForm:options items="${insList}" itemValue="id" itemLabel="name"    />
						</springForm:select>
						
					</div>
				</div>
				
				<div class="form-group">
				 	<label class="control-label col-sm-2" for="dob">New Benifits:</label>
					<div class="col-sm-6">
						<springForm:hidden path="mbr.id" />
						<springForm:hidden path="id" />
						<springForm:input path="newBenifits" class="form-control" id="newBenifits" placeholder="newBenifits" />
						<springForm:errors path="newBenifits" cssClass="error" />
					</div>
				</div>
				
				<div class="form-group">
				 	<label class="control-label col-sm-2" for="activityDate">Activity Date:</label>
					<div class="col-sm-6">
						<springForm:input path="activityDate" class="form-control" id="activityDate" placeholder="activityDate" />
						<springForm:errors path="activityDate" cssClass="error" />
					</div>
				</div>
				
				
				<div class="form-group">
				 	<label class="control-label col-sm-2" for="activityMonth">Activity Month:</label>
					<div class="col-sm-6">
						<springForm:input path="activityMonth" class="form-control" id="activityMonth" placeholder="activityMonth" />
						<springForm:errors path="activityMonth" cssClass="error" />
					</div>
				</div>
				
				
				<div class="form-group">
				 	<label class="control-label col-sm-2" for="effStartDate">Effective Start Date:</label>
					<div class="col-sm-6">
						<springForm:input path="effStartDate" class="form-control" id="effStartDate" placeholder="effStartDate" />
						<springForm:errors path="effStartDate" cssClass="error" />
					</div>
				</div>
				
				<div class="form-group">
				 	<label class="control-label col-sm-2" for="effEndDate">Effective End Date:</label>
					<div class="col-sm-6">
						<springForm:input path="effEndDate" class="form-control" id="effEndDate" placeholder="effEndDate" />
						<springForm:errors path="effEndDate" cssClass="error" />
					</div>
				</div>
				
				<div class="form-group">
				 	<label class="control-label col-sm-2" for="product">Product:</label>
					<div class="col-sm-6">
						<springForm:input path="product" class="form-control" id="product" placeholder="product" />
						<springForm:errors path="product" cssClass="error" />
					</div>
				</div>
				
				
				<div class="form-group">
				 	<label class="control-label col-sm-2" for="productLabel">Product Label:</label>
					<div class="col-sm-6">
						<springForm:input path="productLabel" class="form-control" id="productLabel" placeholder="productLabel" />
						<springForm:errors path="productLabel" cssClass="error" />
					</div>
				</div>
				
				
				<div class="form-group">
				 	<label class="control-label col-sm-2" for="planId">Plan Id:</label>
					<div class="col-sm-6">
						<springForm:input path="planId" class="form-control" id="planId" placeholder="planId" />
						<springForm:errors path="planId" cssClass="error" />
					</div>
				</div>
				
				<div class="form-group">
				 	<label class="control-label col-sm-2" for="dob">Src Sys Mbr Nbr:</label>
					<div class="col-sm-6">
						<springForm:input path="srcSysMbrNbr" class="form-control" id="srcSysMbrNbr" placeholder="srcSysMbrNbr" />
						<springForm:errors path="srcSysMbrNbr" cssClass="error" />
					</div>
				</div>
				
				<div class="form-group">
				 	<label class="control-label col-sm-2" for="dob">Risk Flag:</label>
					<div class="col-sm-6">
						<springForm:input path="riskFlag" class="form-control" id="riskFlag" placeholder="riskFlag" />
						<springForm:errors path="riskFlag" cssClass="error" />
					</div>
				</div>
				<div class="col-sm-offset-6 col-sm-4">
					<c:choose>
						 <c:when test="${membershipInsurance.id != null && membershipInsurance.activeInd == 89}"> 
								<button type="submit" class="btn btn-primary" id="updateButton" name="update" >Update</button>
								<button type="submit" class="btn btn-primary" id="deleteButton" class="delete" name="delete" >Delete</button>
						</c:when>
						 <c:when test="${membershipInsurance.id == null}"> 
							<button type="submit" class="btn btn-primary" id="updateButton" name="add" >Add</button>
							<button type="submit" class="btn btn-primary" id="resetButton" >Reset</button>
						</c:when>
					</c:choose>			
				</div>	
			</springForm:form>

		</div>
	</div>
 </div>

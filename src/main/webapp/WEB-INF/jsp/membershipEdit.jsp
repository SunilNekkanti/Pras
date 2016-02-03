<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
    prefix="springForm"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <div class="panel-group">
	<div class="panel panel-primary">
		<div class="panel-heading">Profile</div>
		<div class="panel-body" id="tablediv">
		
		
			<springForm:form method="POST" commandName="membership" action="save.do" class="form-horizontal" role="form">
			
			<div class="row">
					<div class="col-sm-12">
						<div class="col-sm-6">
							<div class="form-group">
								<label class="control-label col-sm-4" for="firstName">First Name:</label>
								<div class="col-sm-8">
									<springForm:hidden path="id" />
									<springForm:input path="firstName" class="form-control" id="firstName" placeholder="First Name" />
									<springForm:errors path="firstName" cssClass="error" />
								</div>
							</div>
				 		 
							<div class="form-group">
								<label class="control-label col-sm-4" for="lastName">Last Name:</label>
								<div class="col-sm-8">
									<springForm:input path="lastName" class="form-control" id="lastName" placeholder="Last Name" />
									<springForm:errors path="lastName" cssClass="error" />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-4" for="gender">Gender:</label>
								<div class="col-sm-8">
									<springForm:select path="genderId"  class="form-control" id="gender">
							    		<springForm:options items="${genderList}"  itemValue="id" itemLabel="description"    />
									</springForm:select>
									<springForm:errors path="genderId.description" cssClass="error" />
								</div>
							</div>
							<div class="form-group">
							 	<label class="control-label col-sm-4" for="dob">DOB:</label>
								<div class="col-sm-8">
									<springForm:input type="date" path="dob" class="form-control" id="dob" placeholder="DOB" />
									<springForm:errors path="dob" cssClass="error" />
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-sm-4" for="ethinicCode">Ethinicity:</label>
								<div class="col-sm-8">
									<springForm:select path="ethinicCode.id" class="form-control" id="ethinicCode" >
							    		<springForm:options items="${ethinicMap}"    />
									</springForm:select>
									<springForm:errors path="ethinicCode.description" cssClass="error" />
								</div>
							</div>
				 
							<div class="form-group">
								<label class="control-label col-sm-4" for="status">Status:</label>
								<div class="col-sm-8">
									<springForm:select path="status" class="form-control" id="status">
							    		<springForm:options items="${statusList}" itemValue="id" itemLabel="description"   />
									</springForm:select>
									<springForm:errors path="status.description" cssClass="error" />
								  </div>
							</div>
				 		 
							
				
							<div class="form-group">
								<label class="control-label col-sm-4" for="county">County:</label>
								<div class="col-sm-8">
									<springForm:select path="countyCode" class="form-control" id="county" >
							    		<springForm:options items="${countyList}"  itemValue="code" itemLabel="description"  />
									</springForm:select>
									<springForm:errors path="countyCode.description" cssClass="error" />
								</div>
							</div>
				 		 
							
						</div>
						<div class="col-sm-6">
							<div class="form-group">
								<label class="control-label col-sm-4" for="medicaidNo">Medicaid No:</label>
								<div class="col-sm-8">
									
									<springForm:input path="medicaidNo" class="form-control" id="medicaidNo" placeholder="Medicaid No" />
									<springForm:errors path="medicaidNo" cssClass="error" />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-4" for="medicareNo">Medicare No:</label>
								<div class="col-sm-8">
									<springForm:input path="medicareNo" class="form-control" id="medicareNo" placeholder="medicare No" />
									<springForm:errors path="medicareNo" cssClass="error" />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-4" for="file_id">File ID:</label>
								<div class="col-sm-8">
									
									<springForm:input path="fileId" class="form-control" id="fileId" placeholder="fileId" />
									<springForm:errors path="fileId" cssClass="error" />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-4" for="createdDate">Created Date:</label>
								<div class="col-sm-8">
									
									<springForm:input path="createdDate" class="form-control" id="createdDate" placeholder="createdDate" />
									<springForm:errors path="createdDate" cssClass="error" />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-4" for="updatedDate">UpdatedDate:</label>
								<div class="col-sm-8">
								
									<springForm:input path="updatedDate" class="form-control" id="updatedDate" placeholder="Updated Date" />
									<springForm:errors path="updatedDate" cssClass="error" />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-4" for="createdBy">CreatedBy:</label>
								<div class="col-sm-8">
									
									<springForm:input path="createdBy" class="form-control" id="createdBy" placeholder="Created By" />
									<springForm:errors path="createdBy" cssClass="error" />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-4" for="updatedBy">UpdatedBY:</label>
								<div class="col-sm-8">
									
									<springForm:input path="updatedBy" class="form-control" id="updatedBy" placeholder="Updated By" />
									<springForm:errors path="updatedBy" cssClass="error" />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-4" for="active_ind">ActiveInd:</label>
								<div class="col-sm-8">
									
									<springForm:input path="activeInd" class="form-control" id="activeInd" placeholder="Active IND" />
									<springForm:errors path="activeInd" cssClass="error" />
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

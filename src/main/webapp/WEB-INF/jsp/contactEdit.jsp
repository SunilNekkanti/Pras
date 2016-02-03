<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
    prefix="springForm"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="panel-group">
	<div class="panel panel-primary">
		<div class="panel-heading">Contact</div>
		<div class="panel-body" id="tablediv">
			<springForm:form method="POST" commandName="contact" action="save.do" class="form-horizontal" role="form">
				<div class="form-group">
					<label class="control-label col-sm-2" for="homePhone">Home Phone:</label>
					<div class="col-sm-6">
						<springForm:hidden path="id" />
						<springForm:hidden path="refContact.id" />
						 <c:choose>
						 <c:when test="${contact.refContact.mbr != null}"> 
							<springForm:hidden path="refContact.mbr.id" />
						 </c:when>
						 <c:when test="${contact.refContact.prvdr != null}">
							<springForm:hidden path="refContact.prvdr.id" />
						</c:when>
						<c:otherwise>
							<springForm:hidden path="refContact.ins.id" />
						</c:otherwise>
						</c:choose>
						
						<springForm:hidden path="refContact.createdBy" value="sarath"/>
						<springForm:input path="homePhone" class="form-control" id="homePhone" placeholder="Home Phone" />
						<springForm:errors path="homePhone" cssClass="error" />
					</div>
				</div>
				 		 
				<div class="form-group">
					<label class="control-label col-sm-2" for="mobilePhone">Mobile Phone:</label>
					<div class="col-sm-6">
						<springForm:input path="mobilePhone" class="form-control" id="mobilePhone" placeholder="Mobile Phone" />
						<springForm:errors path="mobilePhone" cssClass="error" />
					</div>
				</div>
				<div class="form-group">
				 	<label class="control-label col-sm-2" for="faxNumber">Fax Number:</label>
					<div class="col-sm-6">
						<springForm:input path="faxNumber" class="form-control" id="fax" placeholder="Fax Number" />
						<springForm:errors path="faxNumber" cssClass="error" />
					</div>
				</div>
				 
				 <div class="form-group">
				 	<label class="control-label col-sm-2" for="faxNumber">Address 1:</label>
					<div class="col-sm-6">
						<springForm:input path="address1" class="form-control" id="address1" placeholder="Address 1" />
						<springForm:errors path="address1" cssClass="error" />
					</div>
				</div>
				
				<div class="form-group">
				 	<label class="control-label col-sm-2" for="address2">Address 2:</label>
					<div class="col-sm-6">
						<springForm:input path="address2" class="form-control" id="address2" placeholder="Address 2" />
						<springForm:errors path="address2" cssClass="error" />
					</div>
				</div>
				
				<div class="form-group">
				 	<label class="control-label col-sm-2" for="city">City:</label>
					<div class="col-sm-6">
						<springForm:input path="city" class="form-control" id="city" placeholder="city"/>
						<springForm:errors path="city" cssClass="error" />
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-2" for="state">State:</label>
					<div class="col-sm-6">
						<springForm:select path="stateCode" class="form-control" id="state">
				    		<springForm:options items="${stateList}" itemValue="code" itemLabel="shortName"     />
						</springForm:select>
						<springForm:errors path="stateCode.code" cssClass="error" />
					  </div>
				</div>
					<div class="form-group">
					<label class="control-label col-sm-2" for="zip">Zip:</label>
					<div class="col-sm-6">
						<springForm:select path="zipCode" class="form-control" id="zip">
				    		<springForm:options items="${zipCodeList}" itemValue="code"  itemLabel="code" />
						</springForm:select>
						<springForm:errors path="zipCode.code" cssClass="error" />
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
						 <c:when test="${contact.id != null}"> 
						 	<button type="submit" class="btn btn-primary" id="updateButton" name="update" >Update</button>
						 	<button type="submit" class="btn btn-primary" id="deleteButton" name="delete" >Delete</button>
						 </c:when>
						 <c:otherwise>
							<button type="submit" class="btn btn-primary" id="updateButton" name="add" >Add</button>
							<button type="submit" class="btn btn-primary" id="resetButton" >Reset</button>
						</c:otherwise>
						</c:choose>
					
					
				</div>
			</springForm:form>
 		</div>
	</div>
</div>	

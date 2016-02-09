<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
    prefix="springForm"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="panel-group">
	<div class="panel panel-primary">
		<div class="panel-heading">Membership Provider Details</div>
		<div class="panel-body" id="tablediv">
			<div class="col-sm-12 fdl">
				<springForm:form  commandName="membershipProvider" action="save.do">
					<div class="panel panel-primary">
						<div class="panel-body" id="tablediv">
							<springForm:hidden path="id" />
							<div class="form-group col-sm-12">
								    <label class="control-label col-sm-4" for="name">Name:</label>
									    <div class="col-sm-8">
									    	<springForm:input path="prvdr.name" class="form-control" id="name" placeholder="${membershipProvider.prvdr.name}" />
									      	<springForm:errors path="prvdr.name" cssClass="error text-danger" />
									    </div>
							</div>
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-4" for="name">Code:</label>
								<div class="col-sm-8">
								 	<springForm:input path="prvdr.code" class="form-control" id="code" placeholder="${membershipProvider.prvdr.code}" />
								   	<springForm:errors path="prvdr.name" cssClass="error text-danger" />
								</div>
							 </div>
						</div>
					</div>
					<div class="panel panel-primary">
						<div class="panel-body" id="tablediv">			 
							<c:forEach items="${membershipProvider.prvdr.refContacts}" var="refCnt" varStatus = "status">
								<c:choose>
									<c:when test="${fn:contains(refCnt.cnt.activeInd, 'Y')}">
										<div class="form-group col-sm-12">
											<label class="control-label col-sm-4" for="homePhone">Home Phone:</label>
											<div class="col-sm-8">
												<springForm:input path="prvdr.refContacts[${status.index}].cnt.homePhone" class="form-control" id="code"  />
												<springForm:errors path="prvdr.refContacts[${status.index}].cnt.homePhone" cssClass="error text-danger" />
											</div>
										</div> 
										<div class="form-group col-sm-12">
												<label class="control-label col-sm-4" for="mobilePhone">Mobile Phone:</label>
												 <div class="col-sm-8">
													<springForm:input path="prvdr.refContacts[${status.index}].cnt.mobilePhone" class="form-control" id="code"  />
													<springForm:errors path="prvdr.refContacts[${status.index}].cnt.mobilePhone" cssClass="error text-danger" />
										</div>
										</div> 
										<div class="form-group col-sm-12">
												<label class="control-label col-sm-4" for="faxNumber">FaxNumber:</label>
												 <div class="col-sm-8">
													<springForm:input path="prvdr.refContacts[${status.index}].cnt.faxNumber" class="form-control" id="code"  />
													<springForm:errors path="prvdr.refContacts[${status.index}].cnt.faxNumber" cssClass="error text-danger" />
												 </div>
										</div> 
										<div class="form-group col-sm-12">
												<label class="control-label col-sm-4" for="email">Email:</label>
												 <div class="col-sm-8">
													<springForm:input path="prvdr.refContacts[${status.index}].cnt.email" class="form-control" id="code"  />
													<springForm:errors path="prvdr.refContacts[${status.index}].cnt.email" cssClass="error text-danger" />
												 </div>
										</div> 
										<div class="form-group col-sm-12">
												<label class="control-label col-sm-4" for="address1">Address 1:</label>
												 <div class="col-sm-8">
													<springForm:input path="prvdr.refContacts[${status.index}].cnt.address1" class="form-control" id="code"  />
													<springForm:errors path="prvdr.refContacts[${status.index}].cnt.address1" cssClass="error text-danger" />
												 </div>
										</div> 
										<div class="form-group col-sm-12">
												<label class="control-label col-sm-4" for="address2">Address 2</label>
												 <div class="col-sm-8">
													<springForm:input path="prvdr.refContacts[${status.index}].cnt.address2" class="form-control" id="code"  />
													<springForm:errors path="prvdr.refContacts[${status.index}].cnt.address2" cssClass="error text-danger" />
												 </div>
										</div> 
										<div class="form-group col-sm-12">
												<label class="control-label col-sm-4" for="city">City:</label>
												 <div class="col-sm-8">
													<springForm:input path="prvdr.refContacts[${status.index}].cnt.city" class="form-control" id="code"  />
													<springForm:errors path="prvdr.refContacts[${status.index}].cnt.city" cssClass="error text-danger" />
												 </div>
										</div> 
										<div class="form-group col-sm-12">
											<label class="control-label col-sm-4" for="stateCode">State Code:</label>
											 <div class="col-sm-8">
												<springForm:input path="prvdr.refContacts[${status.index}].cnt.stateCode" class="form-control" id="code"  />
												<springForm:errors path="prvdr.refContacts[${status.index}].cnt.stateCode" cssClass="error text-danger" />
											 </div>
										</div> 
										<div class="form-group col-sm-12">
											<label class="control-label col-sm-4" for="zipCode">Zip Code:</label>
											 <div class="col-sm-8">
												<springForm:input path="prvdr.refContacts[${status.index}].cnt.zipCode" class="form-control" id="code"  />
												<springForm:errors path="prvdr.refContacts[${status.index}].cnt.zipCode" cssClass="error text-danger" />
											 </div>
										</div> 
									</c:when>
									<c:when test="${fn:contains(refCnt.cnt.activeInd, 'y')}">
										<div class="form-group col-sm-12">
											<label class="control-label col-sm-4" for="address2">Contact:</label>
											<div class="col-sm-6">
												<springForm:input path="prvdr.refContacts[${status.index}].cnt.address2" class="form-control" id="code"  />
												<springForm:errors path="prvdr.refContacts[${status.index}].cnt.address2" cssClass="error text-danger" />
											</div>
										</div> 
									</c:when>
								</c:choose>
							</c:forEach>
						</div>
					</div>
				</springForm:form>	
			</div>					
		</div>
	</div>
</div>		
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
    prefix="springForm"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="panel-group">
	<div class="panel panel-success">
		<div class="panel-heading">Membership Contact</div>
		<div class="panel-body" id="tablediv">
			<div class="col-sm-12 fdl">
				<c:forEach items="${membership.refContacts}" var="refCnt" varStatus = "status">
					 <c:choose>
					 	<c:when test="${fn:contains(refCnt.cnt.activeInd, 'Y')}">
					 		<springForm:form method="POST" commandName="membership" action="save.do" class="form-horizontal" role="form">
									<div class="form-group">
										<label class="control-label col-sm-4" for="homePhone">Home Phone:</label>
										<div class="col-sm-8">
											<springForm:input path="refContacts[${status.index}].cnt.homePhone" class="form-control" id="refContacts[${status.index}].cnt.homePhone" placeholder="Home Phone" />
											<springForm:errors path="refContacts[${status.index}].cnt.homePhone" cssClass="error text-danger" />
										</div>
									</div>
									 		 
									<div class="form-group">
										<label class="control-label col-sm-4" for="mobilePhone">Mobile Phone:</label>
										<div class="col-sm-8">
											<springForm:input path="refContacts[${status.index}].cnt.mobilePhone" class="form-control" id="refContacts[${status.index}].cnt.mobilePhone" placeholder="Mobile Phone" />
											<springForm:errors path="refContacts[${status.index}].cnt.mobilePhone" cssClass="error text-danger" />
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-4" for="faxNumber">Fax Number:</label>
										<div class="col-sm-8">
											<springForm:input path="refContacts[${status.index}].cnt.faxNumber" class="form-control" id="fax" placeholder="Fax Number" />
											<springForm:errors path="refContacts[${status.index}].cnt.faxNumber" cssClass="error text-danger" />
										</div>
									</div>
									
									<div class="form-group">
									 	<label class="control-label col-sm-4" for="email">Email:</label>
										<div class="col-sm-8">
											<springForm:input path="refContacts[${status.index}].cnt.email" class="form-control" id="refContacts[${status.index}].cnt.email" placeholder="email" />
											<springForm:errors path="refContacts[${status.index}].cnt.email" cssClass="error text-danger" />
										</div>
									</div>
									 
									<div class="form-group">
									 	<label class="control-label  required col-sm-4" for="faxNumber">Address 1:</label>
										<div class="col-sm-8">
											<springForm:input path="refContacts[${status.index}].cnt.address1" class="form-control" id="refContacts[${status.index}].cnt.address1" placeholder="Address 1" />
											<springForm:errors path="refContacts[${status.index}].cnt.address1" cssClass="error text-danger" />
										</div>
									</div>
									
									<div class="form-group">
									 	<label class="control-label col-sm-4" for="address2">Address 2:</label>
										<div class="col-sm-8">
											<springForm:input path="refContacts[${status.index}].cnt.address2" class="form-control" id="refContacts[${status.index}].cnt.address2" placeholder="Address 2" />
											<springForm:errors path="refContacts[${status.index}].cnt.address2" cssClass="error text-danger" />
										</div>
									</div>
									
									<div class="form-group">
									 	<label class="control-label  required col-sm-4" for="city">City:</label>
										<div class="col-sm-8">
											<springForm:input path="refContacts[${status.index}].cnt.city" class="form-control" id="refContacts[${status.index}].cnt.city" placeholder="city"/>
											<springForm:errors path="refContacts[${status.index}].cnt.city" cssClass="error text-danger" />
										</div>
									</div>
									
									<div class="form-group">
										<label class="control-label required col-sm-4" for="state">State:</label>
										<div class="col-sm-8">
											<springForm:select path="refContacts[${status.index}].cnt.stateCode" class="form-control" id="state">
									    		<springForm:options items="${stateList}" itemValue="code" itemLabel="shortName"     />
											</springForm:select>
											<springForm:errors path="refContacts[${status.index}].cnt.stateCode.code" cssClass="error text-danger" />
										  </div>
									</div>
										
									<div class="form-group">
										<label class="control-label  required col-sm-4" for="zip">Zip:</label>
										<div class="col-sm-8">
											<springForm:select path="refContacts[${status.index}].cnt.zipCode" class="form-control" id="zip">
										   		<springForm:options items="${zipCodeList}" itemValue="code"  itemLabel="code" />
											</springForm:select>
											<springForm:errors path="refContacts[${status.index}].cnt.zipCode.code" cssClass="error text-danger" />
										 </div>
									</div>
							</springForm:form>
						</c:when>
					</c:choose>
				</c:forEach>
			</div>
		</div>
	</div>
</div>		


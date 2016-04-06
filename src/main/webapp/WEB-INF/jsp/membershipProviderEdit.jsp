<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
    prefix="springForm"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="context" value="${pageContext.request.contextPath}" />

<div class="panel-group">
	<div class="panel panel-success">
		<div class="panel-heading">Provider Info</div>
		<div class="panel-body" id="tablediv">
			<springForm:form  commandName="membershipProvider" action="save.do">
				<springForm:hidden path="id" />
						      	
				<div class="form-group col-sm-12">
						    <label class="control-label col-sm-2" for="name">Name:</label>
						    <div class="col-sm-6">
						    	<springForm:input path="prvdr.name" class="form-control" id="name" placeholder="${membershipProvider.prvdr.name}" />
						      	<springForm:errors path="prvdr.name" cssClass="error text-danger" />
						    </div>
				 </div>
				 <div class="form-group col-sm-12">
						    <label class="control-label col-sm-2" for="name">Code:</label>
						    <div class="col-sm-6">
						      	<springForm:input path="prvdr.code" class="form-control" id="code" placeholder="${membershipProvider.prvdr.code}" />
						      	<springForm:errors path="prvdr.name" cssClass="error text-danger" />
						    </div>
				 </div>
				 <c:forEach items="${membershipProvider.prvdr.refContacts}" var="refCnt" varStatus = "status">
				     <c:choose>
							    	<c:when test="${fn:contains(refCnt.cnt.activeInd, 'Y')}">
									 	 <div class="form-group col-sm-12">
												    <label class="control-label col-sm-2" for="name">Contact:</label>
												    <div class="col-sm-6">
												      	<springForm:input path="prvdr.refContacts[${status.index}].cnt.address1" class="form-control" id="code"  />
												      	<springForm:errors path="prvdr.refContacts[${status.index}].cnt.address1" cssClass="error text-danger" />
												    </div>
										 </div> 
									</c:when>
									<c:when test="${fn:contains(refCnt.cnt.activeInd, 'y')}">
									 		<div class="form-group col-sm-12">
												    <label class="control-label col-sm-2" for="name">Contact:</label>
												    <div class="col-sm-6">
												      	<springForm:input path="prvdr.refContacts[${status.index}].cnt.address1" class="form-control" id="code"  />
												      	<springForm:errors path="prvdr.refContacts[${status.index}].cnt.address1" cssClass="error text-danger" />
												    </div>
										 </div> 
									</c:when>
									
								</c:choose>
				</c:forEach>
			</springForm:form>
			
			<div class="row col-sm-12">
				<div class="col-sm-12" style="padding-top:2px;">
					<a href="${context}/membership/${membershipProvider.mbr.id}/providerDetailsList">Click Here</a> to see Membership Provider List
				</div>	
			</div>
			
 		</div>
	</div>
</div>		

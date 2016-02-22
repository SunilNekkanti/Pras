<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<c:set var="context" value="${pageContext.request.contextPath}" />
 <h2> Contact Updated Successfully.</h2>
<div class="panel-group">
	<div class="panel panel-primary">
		<div class="panel-heading">Contact Update</div>
		<div class="row">
			<div class="col-sm-12">
				<div class="well well-sm">
				
					<c:choose>
					 	<c:when test="${contact.refContact.mbr != null}"> 
					 		<p> <strong>Name:${contact.refContact.mbr.firstName}  ${contact.refContact.mbr.lastName}</strong> </p>
						 </c:when>
						 <c:when test="${contact.refContact.prvdr != null}">
							<p> <strong>Name:${contact.refContact.prvdr.name}  </strong> </p>
						</c:when>
						 <c:when test="${contact.refContact.ins != null}">
							<p> <strong>Name:${contact.refContact.ins.name}  </strong> </p>
						</c:when>
						<c:otherwise>
							issue
						</c:otherwise>
					</c:choose>
					
  					
  					<p><strong>Home Phone:${contact.homePhone}</strong>  </p>
  					<p><strong>Mobile Phone:${contact.mobilePhone}</strong>  </p>
  					<p> Updated Successfully  </p>
  					
  					<c:choose>
					 	<c:when test="${contact.refContact.mbr != null}"> 
					 		<a href="${context}/membership/${id}/contactList">Click Here</a> Move to contact list
						 </c:when>
						 <c:when test="${contact.refContact.prvdr != null}">
							<a href="${context}/provider/${id}/contactList">Click Here</a> Move to contact list
						</c:when>
						 <c:when test="${contact.refContact.ins != null}">
							<a href="${context}/insurance/${id}/contactList">Click Here</a> Move to contact list
						</c:when>
						<c:otherwise>
							issue
						</c:otherwise>
					</c:choose>
										
					
				</div>	
			</div>
		</div>
	</div>
</div>	
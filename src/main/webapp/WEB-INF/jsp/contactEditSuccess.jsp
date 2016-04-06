<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<c:set var="context" value="${pageContext.request.contextPath}" />
<div class="panel-group">
	<div class="panel panel-success">
		<div class="panel-heading">Info
			<button class="btn btn-success pull-right btn-xs" onclick= "return contactList();">
	          	<span class="glyphicon glyphicon-plus-sign "></span> contact List
	         </button>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<div class="well well-sm">
					<p>${Message}</p>
				<!-- 
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
				 -->	
  					
  					
  				</div>	
			</div>
		</div>
	</div>
</div>	
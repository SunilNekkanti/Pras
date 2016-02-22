<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="context" value="${pageContext.request.contextPath}" />
<%@ page session="false" %>
<div class="panel-group">
	<div class="panel panel-primary">
		<div class="panel-heading">Membership Insurance Details Updated Successfully</div>
		<div class="row">
			<div class="col-sm-12">
				<div class="well well-sm">
						<p><strong>First Name:${membershipInsurance.mbr.firstName}</strong></p>
						<p><strong>Last Name:${membershipInsurance.mbr.lastName}</strong></p>
						<p><strong>Status:${membershipInsurance.mbr.status.description}</strong></p>
						 <p>Updated Successfully </p>
						<a href="${context}/membership/${membershipInsurance.mbr.id}/detailsList">Click Here</a> Move to membership Insurance list
 				</div>	
			</div>
		</div>
	</div>
</div>
		

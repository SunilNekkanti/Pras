<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="context" value="${pageContext.request.contextPath}/${userpath}" />
<%@ page session="false" %>
<div class="panel-group">
	<div class="panel panel-success">
		<div class="panel-heading">Info
			<button class="btn btn-danger pull-right btn-xs" onclick= "return membershipDetails();">
          		<span class="glyphicon glyphicon-plus-sign "></span> Membership Details
        	</button>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<div class="well well-sm">
					<p><strong>${Message}</strong></p>
					<p><strong>First Name:${membership.firstName}</strong></p>
					<p><strong>Last Name:${membership.lastName}</strong></p>
					<p><strong>Date of Birth:${membership.dob}</strong></p>
					<p><strong>Membership Status:${membership.status.description}</strong></p>
					<p><strong>Gender:${membership.genderId.description}</strong></p>
					<p><strong>county:${membership.countyCode.description}</strong></p>
					
				</div>	
			</div>
		</div>
	</div>
</div>	

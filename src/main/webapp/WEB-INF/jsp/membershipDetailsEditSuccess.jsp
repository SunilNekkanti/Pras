<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="context" value="${pageContext.request.contextPath}" />
<%@ page session="false" %>
<div class="panel-group">
	<div class="panel panel-success">
		<div class="panel-heading">Info
			<button class="btn btn-danger pull-right btn-xs" onclick= "return mbrInsList();">
          		<span class="glyphicon glyphicon-plus-sign "></span> Member Insurance List
          	</button>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<div class="well well-sm">
						<p><strong>${Message}</strong></p>
						<p><strong>First Name:${membershipInsurance.mbr.firstName}</strong></p>
						<p><strong>Last Name:${membershipInsurance.mbr.lastName}</strong></p>
						<p><strong>Status:${membershipInsurance.mbr.status.description}</strong></p>
						
						
 				</div>	
			</div>
		</div>
	</div>
</div>
		

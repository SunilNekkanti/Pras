<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<c:set var="context" value="${pageContext.request.contextPath}" />
<title>Insurance Updated Successfully</title>

<div class="panel-group">
	<div class="panel panel-primary">
		<div class="panel-heading">Insurance Updated Successfully</div>
		<div class="row">
			<div class="col-sm-12">
				<div class="well well-sm">
					<p><strong>Name:${insurance.name}</strong></p>
					<p> Updated Successfully  </p>
					<a href="${context}/insuranceList">Click Here</a> Move to Insurance list
				</div>	
			</div>
		</div>
	</div>
</div>	

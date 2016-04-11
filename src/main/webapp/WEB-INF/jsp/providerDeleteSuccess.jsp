<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<c:set var="context" value="${pageContext.request.contextPath}/${userpath}" />
<title>Provider Deleted Successfully</title>

<div class="panel-group">
	<div class="panel panel-success">
		<div class="panel-heading">Provider Deleted Successfully</div>
		<div class="row">
			<div class="col-sm-12">
				<div class="well well-sm">
					<p><strong>Name:${provider.name}</strong></p>
					<p> Updated Successfully  </p>
					<a href="${context}/providerList">Click Here</a> Move to Provider list
				</div>	
			</div>
		</div>
	</div>
</div>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<c:set var="context" value="${pageContext.request.contextPath}" />
 <h2> Provider Updated Successfully.</h2>
<div class="panel-group">
	<div class="panel panel-primary">
		<div class="panel-heading">Provider Update</div>
		<div class="row">
			<div class="col-sm-12">
				<div class="well well-sm">
  					<p> <strong>Name:${provider.name}</strong>  </p>
  					<p><strong>Code:${provider.code}</strong>  </p>
  					<p> Updated Successfully  </p>
					<a href="${context}/providerList">Click Here</a> Move to Provider list
				</div>	
			</div>
		</div>
	</div>
</div>	
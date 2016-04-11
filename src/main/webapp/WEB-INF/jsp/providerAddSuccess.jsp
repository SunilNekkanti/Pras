<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<c:set var="context" value="${pageContext.request.contextPath}/${userpath}" />
 <h2> Provider Added Successfully.</h2>
<div class="panel-group">
	<div class="panel panel-success">
		<div class="panel-heading">New Provider Added </div>
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
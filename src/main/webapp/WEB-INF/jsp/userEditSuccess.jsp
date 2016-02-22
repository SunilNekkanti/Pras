<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<c:set var="context" value="${pageContext.request.contextPath}" />
 <h2> Login Updated Successfully.</h2>
<div class="panel-group">
	<div class="panel panel-primary">
		<div class="panel-heading">Login Update</div>
		<div class="row">
			<div class="col-sm-12">
				<div class="well well-sm">
  					<p> <strong>Login:${user.login}</strong>  </p>
  					<p> Updated Successfully  </p>
					<a href="${context}/userList">Click Here</a> Move to User list
				</div>	
			</div>
		</div>
	</div>
</div>	
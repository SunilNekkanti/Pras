<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<c:set var="context" value="${pageContext.request.contextPath}/${userpath}" />
<title>New Insurance Added Successfully</title>
<div class="panel-group">
	<div class="panel panel-success">
		<div class="panel-heading">Info
			<a class="btn btn-danger pull-right btn-xs white-text"href="${context}/insuranceList">
          		<span class="glyphicon glyphicon-plus-sign "></span>Insurance List
          	</a> 
		</div>
		<div class="row">
			<div class="col-sm-12">
				<div class="well well-sm">
					<p><strong>${Message}</strong></p>
					<p><strong>Name:${insurance.name}</strong></p>
				</div>	
			</div>
		</div>
	</div>
</div>	

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<c:set var="context"
	value="${pageContext.request.contextPath}/${userpath}" />
<div class="panel-group">
	<div class="panel panel-success">
		<div class="panel-heading">
			Info
			<button class="btn btn-danger pull-right btn-xs"
				onclick="return providerDetails();">
				<span class="glyphicon glyphicon-plus-sign "></span> Provider
				Details
			</button>

		</div>
		<div class="row">
			<div class="col-sm-12">
				<div class="well well-sm">
					<p>${Message}</p>
					<p>
						<strong>Name:${provider.name}</strong>
					</p>
					<p>
						<strong>Code:${provider.code}</strong>
					</p>
					<p>Updated Successfully</p>
				</div>
			</div>
		</div>
	</div>
</div>

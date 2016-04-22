<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<c:set var="context"
	value="${pageContext.request.contextPath}/${userpath}" />
<div class="panel-group">
	<div class="panel panel-success">
		<div class="panel-heading">
			Info
			<button class="btn btn-danger pull-right btn-xs"
				onclick="return insuranceDetails();">
				<span class="glyphicon glyphicon-plus-sign "></span> Insurance
				Details
			</button>

		</div>
		<div class="row">
			<div class="col-sm-12">
				<div class="well well-sm">
					<p>${Message}</p>
					<p>
						<strong>Name:${insurance.name}</strong>
					</p>
				</div>
			</div>
		</div>
	</div>
</div>

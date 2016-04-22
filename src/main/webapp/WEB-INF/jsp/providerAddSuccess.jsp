<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<c:set var="context"
	value="${pageContext.request.contextPath}/${userpath}" />
<div class="panel-group">
	<div class="panel panel-success">
		<div class="panel-heading">
			Info <a class="btn btn-danger pull-right btn-xs white-text"
				href="${context}/providerList"> <span
				class="glyphicon glyphicon-plus-sign "></span>Provider List
			</a>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<div class="well well-sm">
					<p>
						<strong>${Message}</strong>
					</p>
					<p>
						<strong>Name:${provider.name}</strong>
					</p>
					<p>
						<strong>Code:${provider.code}</strong>
					</p>
				</div>
			</div>
		</div>
	</div>
</div>

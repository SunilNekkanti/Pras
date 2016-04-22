<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<c:set var="context"
	value="${pageContext.request.contextPath}/${userpath}" />
<div class="panel-group">
	<div class="panel panel-success">
		<div class="panel-heading">
			Info <a class="btn btn-danger pull-right btn-xs white-text"
				href="${context}/hedis/hedisMeasureList"> <span
				class="glyphicon glyphicon-plus-sign "></span>Hedis Measure List
			</a>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<div class="well well-sm">
					<p>
						<strong>${Message}</strong>
					</p>
					<p>
						<strong>Code:${hedisMeasure.code}</strong>
					</p>
					<p>
						<strong>Description:${hedisMeasure.description}</strong>
					</p>
					<p>
						<strong>Group:${hedisMeasure.hedisMsrGrp.description}</strong>
					</p>
				</div>
			</div>
		</div>
	</div>
</div>

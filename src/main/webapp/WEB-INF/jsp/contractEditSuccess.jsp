<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<div class="panel-group">
	<div class="panel panel-success">
		<div class="panel-heading">
			Info
			<button class="btn btn-danger pull-right btn-xs"
				onclick="return contractList(${pmpmRequired});">
				<span class="glyphicon glyphicon-plus-sign "></span> contract List
			</button>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<div class="well well-sm">
					<p>${Message}</p>

					<p>
						<strong>PMPM:${contract.pmpm}</strong>
					</p>
					<p>
						<strong>Contract NBR:${contract.contractNBR}</strong>
					</p>
					<p>Updated Successfully</p>

				</div>
			</div>
		</div>
	</div>
</div>

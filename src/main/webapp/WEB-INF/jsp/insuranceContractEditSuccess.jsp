<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

 <h2> Contract Updated Successfully.</h2>
<div class="panel-group">
	<div class="panel panel-primary">
		<div class="panel-heading">Contract Update</div>
		<div class="row">
			<div class="col-sm-12">
				<div class="well well-sm">
  				
  					<p><strong>PMPM:${contract.PMPM}</strong>  </p>
  					<p><strong>Contract NBR:${contract.contractNBR}</strong>  </p>
  					<p> Updated Successfully  </p>
					<a href="http://localhost:8080/Pras/insurance/${id}/contractList">Click Here</a> Move to insurance contract list
				</div>	
			</div>
		</div>
	</div>
</div>	
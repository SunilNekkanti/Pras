<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

 <h2> Hedis Measure Rule Updated Successfully.</h2>
<div class="panel-group">
	<div class="panel panel-primary">
		<div class="panel-heading">Hedis Measure Rule Update</div>
		<div class="row">
			<div class="col-sm-12">
				<div class="well well-sm">
				
					<p> <strong>Hedis Code:${hedisMeasureRule.hedisMeasure.id}</strong> </p>
					<p><strong>CPT Code:${hedisMeasureRule.cptMeasure.id}</strong>  </p>
  					<p><strong>ICD Code:${hedisMeasureRule.icdMeasure.id}</strong>  </p>
  					<p><strong>Effective Year:${hedisMeasureRule.effectiveYear}</strong>  </p>
  					<p> Updated Successfully  </p>
  					
			 		<a href="/Pras/hedisMeasureRule/hedisMeasureRuleList">Click Here </a> Move to Hedis Measure Rule list
										
					
				</div>	
			</div>
		</div>
	</div>
</div>	
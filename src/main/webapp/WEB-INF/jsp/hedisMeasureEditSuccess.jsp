<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

 <h2> Hedis Measure Updated Successfully.</h2>
<div class="panel-group">
	<div class="panel panel-primary">
		<div class="panel-heading">Hedis Measure Update</div>
		<div class="row">
			<div class="col-sm-12">
				<div class="well well-sm">
				
					<p> <strong>Code:${hedisMeasure.code}</strong> </p>
  					<p><strong>Description:${hedisMeasure.description}</strong>  </p>
  					<p><strong>Group:${hedisMeasure.hedisMsrGrp.description}</strong>  </p>
  					<p> Updated Successfully  </p>
  					
			 		<a href="http://localhost:8080/Pras/hedis/hedisMeasureList">Click Here </a> Move to Hedis Measure list
										
					
				</div>	
			</div>
		</div>
	</div>
</div>	

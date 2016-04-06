<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<c:set var="context" value="${pageContext.request.contextPath}" />
 <h2> CPT Measure Updated Successfully.</h2>
<div class="panel-group">
	<div class="panel panel-success">
		<div class="panel-heading">CPT Measure Update</div>
		<div class="row">
			<div class="col-sm-12">
				<div class="well well-sm">
				
					<p> <strong>Code:${cptMeasure.code}</strong> </p>
  					<p><strong>Description:${cptMeasure.description}</strong>  </p>
  					<p> Updated Successfully  </p>
  					
			 		<a href="${context}/cpt/cptMeasureList">Click Here </a> Move to CPT Measure list
										
					
				</div>	
			</div>
		</div>
	</div>
</div>	
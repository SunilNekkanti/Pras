<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<c:set var="context" value="${pageContext.request.contextPath}" />
 <h2> ICD Measure Updated Successfully.</h2>
<div class="panel-group">
	<div class="panel panel-primary">
		<div class="panel-heading">ICD Measure Update</div>
		<div class="row">
			<div class="col-sm-12">
				<div class="well well-sm">
				
					<p> <strong>Code:${icdMeasure.code}</strong> </p>
  					<p><strong>Description:${icdMeasure.description}</strong>  </p>
  					<p> Updated Successfully  </p>
  					
			 		<a href="${context}/icd/icdMeasureList">Click Here </a> Move to ICD Measure list
					
				</div>	
			</div>
		</div>
	</div>
</div>	
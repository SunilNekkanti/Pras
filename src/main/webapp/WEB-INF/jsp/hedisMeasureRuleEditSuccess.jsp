<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<c:set var="context" value="${pageContext.request.contextPath}" />
 <h2> Hedis Measure Rule Updated Successfully.</h2>
<div class="panel-group">
	<div class="panel panel-primary">
		<div class="panel-heading">Hedis Measure Rule Update</div>
		<div class="row">
			<div class="col-sm-12">
				<div class="well well-sm">
				
					<p> <strong>Hedis Code:${hedisMeasureRule.hedisMeasure.id}</strong> </p>
					<p><strong>CPT Codes: </strong>  </p>
					<c:forEach items="${hedisMeasureRule.cptCodes}" var="cptCodes">
					<p><strong> ${cptCodes.code}</strong>  </p>
					</c:forEach>
  					<p><strong>ICD Code:</strong>  </p>
  					<c:forEach items="${hedisMeasureRule.icdCodes}" var="icdCodes">
					<p><strong> ${icdCodes.code}</strong>  </p>
					</c:forEach>
  					<p><strong>Effective Year:${hedisMeasureRule.effectiveYear}</strong>  </p>
  					<p> Updated Successfully  </p>
  					
			 		<a href="${context}/hedisMeasureRule/hedisMeasureRuleList">Click Here </a> Move to Hedis Measure Rule list
										
					
				</div>	
			</div>
		</div>
	</div>
</div>	
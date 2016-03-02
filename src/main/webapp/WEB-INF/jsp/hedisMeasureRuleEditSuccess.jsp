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
					<c:set var="cptCodesText" value="" />
					<c:forEach items="${hedisMeasureRule.cptCodes}" var="cptMeasure">
					   <c:choose>
					   		<c:when test="${not empty cptCodesText}"> 
					   			<c:set var="cptCodesText" value="${cptCodesText},${cptMeasure.code}" />
					   		</c:when>
					   		<c:otherwise>
					   			<c:set var="cptCodesText" value="${cptMeasure.code}" />
					   		</c:otherwise>
					   </c:choose>
					</c:forEach>
					<p><strong>CPT Codes: ${cptCodesText} </strong></p>
					
					<c:set var="icdCodesText" value="" />
					<c:forEach items="${hedisMeasureRule.icdCodes}" var="icdMeasure">
					   <c:choose>
					   		<c:when test="${not empty icdCodesText}"> 
					   			<c:set var="icdCodesText" value="${icdCodesText},${icdMeasure.code}" />
					   		</c:when>
					   		<c:otherwise>
					   			<c:set var="icdCodesText" value="${icdMeasure.code}" />
					   		</c:otherwise>
					   </c:choose>
					</c:forEach>
  					<p><strong>ICD Codes:${icdCodesText}</strong> </p>
  					<p><strong>Effective Year:${hedisMeasureRule.effectiveYear}</strong> </p>
  					<p><strong>Gender:${hedisMeasureRule.genderId.description}</strong>  </p>
  					<p><strong>Lower Age Limit:${hedisMeasureRule.lowerAgeLimit}</strong> </p>
  					<p><strong>Upper Age Limit:${hedisMeasureRule.upperAgeLimit}</strong> </p>
  					<p><strong>Eff. Date From:${hedisMeasureRule.ageEffectiveFrom}</strong> </p>
  					<p><strong>Eff. Date To:${hedisMeasureRule.ageEffectiveTo}</strong> </p>
  					<p> Updated Successfully  </p>
  					
			 		<a href="${context}/hedisMeasureRule/hedisMeasureRuleList">Click Here </a> Move to Hedis Measure Rule list
										
					
				</div>	
			</div>
		</div>
	</div>
</div>	
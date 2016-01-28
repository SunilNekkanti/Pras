<%@  page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
		
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@  taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script	src="//raw.github.com/botmonster/jquery-bootpag/master/lib/jquery.bootpag.min.js"></script>
<script src="/Pras/resources/js/prasweb.js"></script>
<script>
	$(document).ready(function(){	
		
	prasPagination();
	
	});
</script>
<div class="panel-group">
	<div class="panel panel-primary">
		<div class="panel-heading">Hedis Measure Rule List <span class="badge">${hedisMeasureRuleList.size()}</span> </div>
		<div class="panel-body" id="tablediv">
				<table id="tab" class="table table-striped table-hover">
					<thead>
						<tr>
							<th  scope="col">Action</th> 
							<th  scope="col">Hedis Code</th> 
							<th  scope="col">CPT Code</th>  
					        <th  scope="col">ICD Code</th> 
					        <th  scope="col">Effective Year</th> 
						</tr>
					</thead>

					<tbody id="content">
						<c:forEach items="${hedisMeasureRuleList}" var="hedisMeasureRule">
							    <tr>
							    <td> 
							    <c:choose>
							    	<c:when test="${fn:contains(hedisMeasureRule.activeInd, 'Y')}">
									 		<a href="/Pras/hedisMeasureRule/${hedisMeasureRule.id}"   rel='tab' >Edit</a> 
									</c:when>
									<c:when test="${fn:contains(hedisMeasureRule.activeInd, 'y')}">
									 		<a href="/Pras/hedisMeasureRule/${hedisMeasureRule.id}"   rel='tab' >Edit</a> 
									</c:when>
									<c:otherwise>
											<a href="/Pras/hedisMeasureRule/${hedisMeasureRule.id}/display"   rel='tab' >View</a>
									</c:otherwise>
								</c:choose>
							    </td>
								   	<td>  ${hedisMeasureRule.hedisMeasure.code}</td> 
						        	<td> ${hedisMeasureRule.cptMeasure.code}</td>
						        	<td> ${hedisMeasureRule.icdMeasure.code}</td>
						        	<td> ${hedisMeasureRule.effectiveYear}</td>
						       </tr>     
						        
						</c:forEach>
					</tbody>

						
				</table>
		</div>
		<div class="col-md-12 text-center" id="page_navigation"></div>
		<div id="show_per_page"></div>
		<div id="current_page"></div>
					
	</div>
</div>

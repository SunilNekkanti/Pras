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
		
	prasPagination('icd');
	
	});
</script>
<div class="panel-group">
	<div class="panel panel-primary">
		<div class="panel-heading">ICD Measure List <span class="badge">${icdMeasureList.size()}</span> </div>
		<div class="panel-body" id="tablediv">
				<table id="tab" class="table table-striped table-hover">
					<thead>
						<tr>
							<th  scope="col">Action</th> 
							<th  scope="col">ICD Code</th> 
							<th  scope="col">Description</th>  
						</tr>
					</thead>

					<tbody id="contenticd">
						<c:forEach items="${icdMeasureList}" var="icdMeasure">
							    <tr>
							    <td> 
							    <c:choose>
							    	<c:when test="${fn:contains(icdMeasure.activeInd, 'Y')}">
									 		<a href="/Pras/icd/${icdMeasure.id}"   rel='tab' >Edit</a> 
									</c:when>
									<c:when test="${fn:contains(icdMeasure.activeInd, 'y')}">
									 		<a href="/Pras/icd/${icdMeasure.id}"   rel='tab' >Edit</a> 
									</c:when>
									<c:otherwise>
											<a href="/Pras/icd/${icdMeasure.id}/display"   rel='tab' >View</a>
									</c:otherwise>
								</c:choose>
							    </td>
								   	<td>  ${icdMeasure.code}</td> 
						        	<td> ${icdMeasure.description}</td>
						       </tr>     
						        
						</c:forEach>
					</tbody>
				</table>
		</div>
		<div class="col-md-12 text-center" id="page_navigationicd"></div>
		<div id="show_per_pageicd"></div>
		<div id="current_pageicd"></div>
				
	</div>
</div>

<%@  page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
		
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script	src="//raw.github.com/botmonster/jquery-bootpag/master/lib/jquery.bootpag.min.js"></script>

<script src="/Pras/resources/js/prasweb.js"></script>

<script>

$(document).ready(function(){	
	
prasPagination('provider');

});
</script>
<div class="panel-group">
	<div class="panel panel-primary">
		<div class="panel-heading">Membership Hedis Measure</span></div>
		<div class="panel-body" id="tablediv">
			<table id="tab" class="table table-striped table-hover">
				<thead>
					<tr>
							<th scope="col">Hedis Measure Code</th>
							<th scope="col">Description</th>
							<th scope="col">Due Date</th>
							<th scope="col">Date of Service</th>
							<th scope="col">Status</th>
					</tr>
				</thead>

				<tbody id="contentprovider">
					
					<c:forEach items="${mbrHedisMeasureList}" var="mbrHedisMeasure">
						<tr>
						    	<c:forEach items="${hedisMeasureList}" var="hedisMeasure">
						    		 	
									<c:choose>
								 		<c:when test="${mbrHedisMeasure.hedisMeasureRule.hedisMeasure.id == hedisMeasure.id}"> 
								 		<td>${hedisMeasure.code}</td>
								 		<td>${hedisMeasure.description}</td>
										<td>	<a href="/Pras/membership/${mbrHedisMeasure.mbr.id}/memberHedisMeasure/${mbrHedisMeasure.id}"   rel='tab' > ${mbrHedisMeasure.dueDate} </a> 	 </td> 
								 		<td>${mbrHedisMeasure.dos}</td>
								 		
								 		 <c:choose>
									    	<c:when test="${not empty mbrHedisMeasure.dos}">
											 		<td>Completed</td>
											</c:when>
											<c:otherwise>
													<td>Pending</td>
											</c:otherwise>
										</c:choose>
								
								 		</c:when>
									</c:choose>
								</c:forEach>
						    
						 </tr>     
					</c:forEach>
				</tbody>
			</table>
			<div class="col-md-12 text-center" id="page_navigationprovider"></div>
			<div id="show_per_pageprovider"></div>
			<div id="current_pageprovider"></div>
		</div>
	</div>
</div>

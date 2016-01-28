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
		<div class="panel-heading">CPT Measure List <span class="badge">${cptMeasureList.size()}</span> </div>
		<div class="panel-body" id="tablediv">
				<table id="tab" class="table table-striped table-hover">
					<thead>
						<tr>
							<th  scope="col">Action</th> 
							<th  scope="col">CPT Code</th> 
							<th  scope="col">Description</th>  
						</tr>
					</thead>

					<tbody id="content">
						<c:forEach items="${cptMeasureList}" var="cptMeasure">
							    <tr>
							    <td> 
							    <c:choose>
							    	<c:when test="${fn:contains(cptMeasure.activeInd, 'Y')}">
									 		<a href="/Pras/cpt/${cptMeasure.id}"   rel='tab' >Edit</a> 
									</c:when>
									<c:when test="${fn:contains(cptMeasure.activeInd, 'y')}">
									 		<a href="/Pras/cpt/${cptMeasure.id}"   rel='tab' >Edit</a> 
									</c:when>
									<c:otherwise>
											<a href="/Pras/cpt/${cptMeasure.id}/display"   rel='tab' >View</a>
									</c:otherwise>
								</c:choose>
							    </td>
								   	<td>  ${cptMeasure.code}</td> 
						        	<td> ${cptMeasure.description}</td>
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

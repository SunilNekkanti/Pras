<%@  page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
		
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@  taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
		<div class="panel-heading">Hedis Measure List</div>
		<div class="panel-body" id="tablediv">
				<table id="tab" class="table table-striped table-hover">
					<thead>
						<tr>
							<th  scope="col">Action</th> 
							<th  scope="col">Hedis Code</th> 
							<th  scope="col">Description</th>  
					        <th  scope="col">Hedis Group</th> 
						</tr>
					</thead>

					<tbody id="content">
						<div id="show_per_page"></div>
						<div id="current_page"></div>
						<c:forEach items="${hedisMeasureList}" var="hedisMeasure">
							    <tr>
							    <td> 
							    <c:choose>
							    	<c:when test="${hedisMeasure.activeInd == 89}">
									 		<a href="/Pras/hedis/${hedisMeasure.id}"   rel='tab' >Edit</a> 
									</c:when>
									<c:otherwise>
											<a href="/Pras/hedis/${hedisMeasure.id}/display"   rel='tab' >View</a>
									</c:otherwise>
								</c:choose>
							    </td>
								   	<td>  ${hedisMeasure.code}</td> 
						        	<td> ${hedisMeasure.description}</td>
						        	<td> ${hedisMeasure.hedisMsrGrp.description}</td>
						       </tr>     
						        
						</c:forEach>
					</tbody>

					<tfoot>
						<div class="col-md-12 text-center" id="page_navigation"></div>
					</tfoot>
				</table>
		</div>
	</div>
</div>

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
		<div class="panel-heading">New Provider <span class="badge">${membershipList.size()}</span></div>
		<div class="panel-body" id="tablediv">
			<table id="tab" class="table table-striped table-hover">
				<thead>
					<tr>
							<th scope="col">Name</th>
							<th scope="col">Code</th>
					</tr>
				</thead>

				<tbody id="content">
					
					<c:forEach items="${providerList}" var="provider">
						<tr>
							<td> <a href="provider/${provider.id}"   rel='tab' > ${provider.name}</a></td> 
						    <td> ${provider.code}  </td> 
						 </tr>     
					</c:forEach>
				</tbody>
			</table>
			<div class="col-md-12 text-center" id="page_navigation"></div>
			<div id="show_per_page"></div>
			<div id="current_page"></div>
		</div>
	</div>
</div>

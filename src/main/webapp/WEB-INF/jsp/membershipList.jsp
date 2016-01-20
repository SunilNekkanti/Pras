<%@  page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
		
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@  taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html  PUBLIC  "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<!-- meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"-->

<title>Spring3Example</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script
	src="//raw.github.com/botmonster/jquery-bootpag/master/lib/jquery.bootpag.min.js"></script>

<script src="resources/js/prasweb.js"></script>


<script>

$(document).ready(function(){	
	
prasPagination();

});
</script>

</head>

<body>

	<div class="panel-group">
		<div class="panel panel-primary">
			<div class="panel-heading">Membership List</div>
			<div class="panel-body" id="tablediv">
				<table id="tab" class="table table-striped table-hover">
					<thead>
						<tr>
							<th scope="col">First Name</th>
							<th scope="col">Last Name</th>
							<th scope="col">Date Of Birth</th>
							<th scope="col">Gender</th>
							<th scope="col">County</th>
							<th scope="col">File Number</th>
							<th scope="col">Status</th>
						</tr>
					</thead>

					<tbody id="content">
						<div id="show_per_page"></div>
						<div id="current_page"></div>
						<c:forEach items="${membershipList}" var="mbr">
							    <tr>
								   <td> <a href="membership/${mbr.id}"   rel='tab' > ${mbr.firstName}</a></td> 
						        <td> ${mbr.lastName} </td> 
						        <td> ${mbr.dob}  </td> 
						        <td> ${mbr.genderId.description }</td>
						        <td> ${mbr.countyCode.description} </td> 
						        <td> ${mbr.fileId} </td> 
						        <td> ${mbr.status.description}  </td></tr>     
						</c:forEach>
					</tbody>

					<tfoot>
						<div class="col-md-12 text-center" id="page_navigation"></div>
					</tfoot>

				</table>

			</div>
		</div>
	</div>


</body>
</html>
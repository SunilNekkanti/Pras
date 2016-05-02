<%@  page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@  taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="context" value="${pageContext.request.contextPath}" />

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script
	src="//raw.github.com/botmonster/jquery-bootpag/master/lib/jquery.bootpag.min.js"></script>


<script>
$(document).ready(function(){
		$.get('insurance/list',null,function(responseText) { 
			
			for(var i=0;i<responseText.data.length;i++) {
				 $("#tab tbody").append(
				    "<tr>" +
				   "<td>"+ responseText.data[i].insuranceId.name + "</td> " +
		       
		       "<td>"+ responseText.data[i].contract + "</td> " +
		       
		       "<td>"+ responseText.data[i].pmpm + "</td> " +
		       
		       "<td>"+ responseText.data[i].startDate + "</td>"+
		       
		       "<td>"+ responseText.data[i].endDate+ "</td></tr>" );
				 
			 }
			 
		  });
		
		prasPagination();
});		

</script>

<form:form action="insurance.html" commandName="insurance">
	<h2>Insurance List</h2>
	<div class="panel-group">
		<div class="panel panel-success">
			<div class="panel-heading">Insurance Details</div>
			<div class="panel-body" id="tablediv">


				<table id="tab" class="table table-striped">
					<thead>
						<tr>
							<th scope="col">Insurance Name</th>
							<th scope="col">Contract</th>
							<th scope="col">PMPM</th>
							<th scope="col">Start Date</th>
							<th scope="col">End Date</th>

						</tr>
					</thead>
					<tbody id="content">
					</tbody>
					<tfoot>
					</tfoot>

				</table>
			</div>
		</div>
	</div>
</form:form>




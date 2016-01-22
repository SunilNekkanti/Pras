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



</head>

<body>
<div class="panel with-nav-tabs panel-primary">
		<div class="panel-heading">
						<ul class="nav nav-tabs">
                            <li class="active"><a href="#" data-toggle="tab">Insurance Details</a></li>
                        </ul>
          </div>
			<div class="panel-body" id="tablediv">
				<table id="tab" class="table table-striped table-hover">
					<thead>
						<tr>
							<th scope="col">Insurance Name</th>
							<th scope="col">New Benefits</th>
							<th scope="col">Last Name</th>
							<th scope="col">Activity Date</th>
							<th scope="col">Activity Month</th>
							<th scope="col">Effective Start Date</th>
							<th scope="col">Effective End Date</th>
							<th scope="col">Product</th>
							<th scope="col">Product Label</th>
							<th scope="col">Plan Id</th>
							<th scope="col">SRC_SYS_MBR_NBR</th>
							<th scope="col">Risk</th>
						</tr>
					</thead>

					<tbody id="content">
						<div id="show_per_page"></div>
						<div id="current_page"></div>
						<c:forEach items="${membershipDetailsList}" var="mbrDetails">
							    <tr>
								   <td> <a href="/Pras/membership/details/display/${mbrDetails.id}"   rel='tab' > ${mbrDetails.insId.name}</a></td> 
						        <td> ${mbrDetails.newBenifits} </td> 
						        <td> ${mbrDetails.activityDate}  </td> 
						        <td> ${mbrDetails.activityMonth}</td>
						        <td> ${mbrDetails.effStartDate} </td> 
						        <td> ${mbrDetails.effEndDate} </td> 
						        <td> ${mbrDetails.product} </td> 
						        <td> ${mbrDetails.productLabel} </td> 
						        <td> ${mbrDetails.planId} </td> 
						        <td> ${mbrDetails.srcSysMbrNbr} </td> 
						        <td> ${mbrDetails.riskFlag}  </td></tr>     
						</c:forEach>
					</tbody>

					<tfoot>
						<div class="col-md-12 text-center" id="page_navigation"></div>
					</tfoot>

				</table>

			</div>
		</div>

</body>
</html>
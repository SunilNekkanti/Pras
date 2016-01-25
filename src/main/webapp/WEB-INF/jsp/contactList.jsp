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
		<div class="panel-heading">Contact List</div>
		<div class="panel-body" id="tablediv">
				<table id="tab" class="table table-striped table-hover">
					<thead>
						<tr>
							<th  scope="col">Action</th> 
							<th  scope="col">Home Phone</th>  
					        <th  scope="col">Mobile Phone</th> 
					        <th  scope="col">Fax Number</th> 
					        <th  scope="col">Address 1</th> 
					        <th  scope="col">City</th> 
					        <th  scope="col">State</th> 
					        <th  scope="col">Zip Code</th> 
						</tr>
					</thead>

					<tbody id="content">
						<div id="show_per_page"></div>
						<div id="current_page"></div>
						<c:forEach items="${contactList}" var="cntct">
							    <tr>
							    <td> 
							    <c:choose>
							    <c:when test="${cntct.activeInd == 89}">
							    	<c:choose>
									 	<c:when test="${cntct.refContact.mbr != null}"> 
									 		<a href="/Pras/membership/${cntct.refContact.mbr.id}/contact/${cntct.id}"   rel='tab' >Edit</a> 
										 </c:when>
										 <c:when test="${cntct.refContact.prvdr != null}">
											<a href="/Pras/provider/${cntct.refContact.prvdr.id}/contact/${cntct.id}"   rel='tab' >Edit</a> 
										</c:when>
										 <c:when test="${cntct.refContact.ins != null}">
											<a href="/Pras/insurance/${cntct.refContact.ins.id}/contact/${cntct.id}"   rel='tab' >Edit</a> 
										</c:when>
										<c:otherwise>
											issue
										</c:otherwise>
										</c:choose>
							    </c:when>
							    <c:otherwise> 
							    	<c:choose>
									 <c:when test="${cntct.refContact.mbr != null}"> 
										<a href="/Pras/membership/contact/${cntct.id}/display"   rel='tab' >View</a>
									 </c:when>
									 <c:when test="${cntct.refContact.prvdr != null}">
										<a href="/Pras/provider/contact/${cntct.id}/display"   rel='tab' >View</a>
									</c:when>
									 <c:when test="${cntct.refContact.ins != null}">
										<a href="/Pras/insurance/contact/${cntct.id}/display"   rel='tab' >View</a>
									</c:when>
									<c:otherwise>
										issue
									</c:otherwise>
									</c:choose>
							    </c:otherwise>
							    </c:choose>
							    </td>
								   	<td>  ${cntct.homePhone}</td> 
						        	<td> ${cntct.mobilePhone}</td>
						        	<td> ${cntct.faxNumber}</td>
						        	<td> ${cntct.address1}</td>
						        	<td> ${cntct.city}</td>
						        	<td> ${cntct.stateCode.shortName}</td>
						        	<td> ${cntct.zipCode.code}</td>
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

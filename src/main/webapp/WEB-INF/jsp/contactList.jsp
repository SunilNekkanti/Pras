<%@  page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
		
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@  taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="context" value="${pageContext.request.contextPath}" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script	src="//raw.github.com/botmonster/jquery-bootpag/master/lib/jquery.bootpag.min.js"></script>
<div class="panel-group">
	<div class="panel panel-success">
		<div class="panel-heading">
			Contact List <span class="badge">${contactList.size()}</span>
			<button class="btn btn-danger pull-right btn-xs" onclick= "return newContact();">
	        	<span class="glyphicon glyphicon-plus-sign "></span> New Contact
	        </button>
	    </div>	 
		<div class="panel-body" id="tablediv">
				<table id="tab" class="table table-striped table-hover table-responsive">
					<thead>
						<tr>
							<th  scope="col">Action</th> 
							<th  scope="col">Contact Person</th>
							<th  scope="col">Land Phone</th>  
					        <th  scope="col">Mobile Phone</th> 
					        <th  scope="col">Fax Number</th>
					        <th  scope="col">Email</th> 
					        <th  scope="col">Address 1</th> 
					        <th  scope="col">Address 2</th> 
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
									 		<a onclick ="return contact(${cntct.refContact.mbr.id},${cntct.id})" href="#"  rel='tab' >Edit</a> 
										 </c:when>
										 <c:when test="${cntct.refContact.prvdr != null}">
											<a  onclick ="return contact(${cntct.refContact.prvdr.id},${cntct.id})"  href="#"   rel='tab' >Edit</a> 
										</c:when>
										 <c:when test="${cntct.refContact.ins != null}">
											<a onclick ="return contact(${cntct.refContact.ins.id},${cntct.id})" href="#"   rel='tab' >Edit</a> 
										</c:when>
										<c:otherwise>
											issue
										</c:otherwise>
										</c:choose>
							    </c:when>
							    <c:otherwise> 
							    	<c:choose>
									 <c:when test="${cntct.refContact.mbr != null}"> 
										<a onclick ="return contact(${cntct.refContact.mbr.id},${cntct.id})" href="#"  rel='tab' >View</a> 
									 </c:when>
									 <c:when test="${cntct.refContact.prvdr != null}">
										<a href="${context}/provider/${cntct.refContact.prvdr.id}/contact/${cntct.id}/display"   rel='tab' >View</a>
									</c:when>
									 <c:when test="${cntct.refContact.ins != null}">
										<a href="${context}/insurance/${cntct.refContact.ins.id}/contact/${cntct.id}/display"   rel='tab' >View</a>
									</c:when>
									<c:otherwise>
										issue
									</c:otherwise>
									</c:choose>
							    </c:otherwise>
							    </c:choose>
							    </td>
							    	<td> ${cntct.contactPerson}</td>
								   	<td> ${cntct.homePhone}</td> 
						        	<td> ${cntct.mobilePhone}</td>
						        	<td> ${cntct.faxNumber}</td>
						        	<td> ${cntct.email}</td>
						        	<td> ${cntct.address1}</td>
						        	<td> ${cntct.address2}</td>
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

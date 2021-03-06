<%@  page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="context"
	value="${pageContext.request.contextPath}/${userpath}" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script
	src="//raw.github.com/botmonster/jquery-bootpag/master/lib/jquery.bootpag.min.js"></script>

<div class="panel-group">
	<div class="panel panel-success">
		<div class="panel-heading">Provider Details<span class="clrRed">${Message}</span>
		<c:choose>
						<c:when
							test="${fn:length(leadMembershipProviderList) < 1 }">
								<button id="mbrPrvdrList" class="btn btn-danger pull-right btn-xs"
								onclick="return mbrNewPrvdr();">
								<span class="glyphicon glyphicon-plus-sign "></span> New Provider
							</button>
						</c:when>
					</c:choose>	
				
		</div>
		<div class="panel-body" id="tablediv">
			<table id="tab" class="table table-striped table-hover">
				<thead>
					<tr>
						<th scope="col">Name</th>
						<th scope="col">NPI</th>
						<th scope="col">Contact Person</th>
						<th scope="col">Contact No</th>
					</tr>
				</thead>

				<tbody id="contentprovider">

					<c:forEach items="${leadMembershipProviderList}" var="mbrProvider">
						<tr> 
							<td> <a onclick="leadPrvdrDetails(${id})" href="javascript:void(0)"
								rel='tab'> ${mbrProvider.prvdr.name}</a></td>
							<td>${mbrProvider.prvdr.code}</td>
							<c:forEach items="${mbrProvider.prvdr.refContacts}"
								var="refContacts">
								<td>${refContacts.cnt.contactPerson}</td>
								<td>${refContacts.cnt.homePhone}</td>
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

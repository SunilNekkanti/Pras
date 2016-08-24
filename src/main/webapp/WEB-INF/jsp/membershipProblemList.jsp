<%@  page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="springForm"%>

<c:set var="context"
	value="${pageContext.request.contextPath}/${userpath}" />

<div id="content" class="membershipProblemList">
	<ul id="tabs" class="nav nav-pills" data-tabs="tabs">
		<li class="active"><a href="#active" data-toggle="tab">Active</a></li>
		<li><a href="#problemHistory" data-toggle="tab">Past History</a></li>
	</ul>
	<div class="row">
		<div class="col-sm-12">
			<div id="my-tab-content fade active in" class="tab-content">
				<div class="tab-pane active" id="active">
					<div class="panel-group">
						<div class="panel panel-success">
							<div class="panel-heading">Membership Problem List
							 <span class="clrRed"></span> <a
										class="btn btn-danger pull-right btn-xs white-text"
										href="javascript:void(0)" onclick="return newMembershipProblem();"> <span
										class="glyphicon glyphicon-plus-sign "></span>Add Problem
									</a>
							</div>
							<div class="panel-body" id="tablediv">
								<div class="col-sm-12">
									<table id="tab" class="table table-striped table-hover">
										<thead>
											<tr>
												<th scope="col">Description</th>
												<th scope="col">Diagnose Date</th>
												<th scope="col">Resolved Date</th>
											</tr>
										</thead>
										<tbody id="contentprovider">

											<c:forEach items="${mbrProblemList}" var="mbrProblem">
												<c:choose>
													<c:when test="${empty mbrProblem.resolvedDate}">
														<tr>
															<td><a href="javascript:void(0)" onclick="return membershipProblemDetails(${mbrProblem.id});">${mbrProblem.pbm.description}</a></td>
															<td>${mbrProblem.startDate}</td>
															<td>${mbrProblem.resolvedDate}</td>
														</tr>
													</c:when>
												</c:choose>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="tab-pane" id="problemHistory">
					<div class="panel-group">
						<div class="panel panel-success">
							<div class="panel-heading">Membership Problem List</div>
							<div class="panel-body" id="tablediv">
								<div class="col-sm-12">
									<table id="tab" class="table table-striped table-hover">
										<thead>
											<tr>
												<th scope="col">Description</th>
												<th scope="col">Diagnosis Date</th>
												<th scope="col">Resolved Date</th>
											</tr>
										</thead>
										<tbody id="contentprovider">

											<c:forEach items="${mbrProblemList}" var="mbrProblem">
												<c:choose>
													<c:when test="${not empty mbrProblem.resolvedDate}">
														<tr>
															<td><a href="javascript:void(0)" onclick="return membershipProblemDetails(${mbrProblem.id});">${mbrProblem.pbm.description}</a></td>
															<td>${mbrProblem.startDate}</td>
															<td>${mbrProblem.resolvedDate}</td>
														</tr>
													</c:when>
												</c:choose>
											</c:forEach>
										</tbody>

									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="membershipProblemDetails"></div>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$('#tabs').tab();
	});
</script>



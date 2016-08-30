<%@  page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="context"
	value="${pageContext.request.contextPath}/${userpath}" />

<div id="content">
	<ul id="tabs" class="nav nav-pills" data-tabs="tabs">
		<li class="active"><a href="#hospitalizationActive"
			data-toggle="tab">Active</a></li>
		<li><a href="#hospitalizationHistory" data-toggle="tab">Past
				History</a></li>
	</ul>
	<div class="row">
		<div class="col-sm-12">
			<div id="my-tab-content fade active in" class="tab-content">
				<div class="tab-pane active" id="hospitalizationActive">
					<div class="panel-group">
						<div class="panel panel-success">
							<div class="panel-heading">Membership Hospitalization List</div>
							<div class="panel-body" id="tablediv">
								<div class="col-sm-12">
									<table id="tab" class="table table-striped table-hover">
										<thead>
											<tr>
												<th scope="col">Hospital</th>
												<th scope="col">Admit Date</th>
												<th scope="col">Exp Discharge Date</th>
												<th scope="col">Prior Admits</th>
											</tr>
										</thead>
										<tbody id="contentprovider">

											<c:forEach items="${mbrHospitalizationList}"
												var="mbrHospitalization">
												<c:choose>
													<c:when test="${empty mbrHospitalization.expDisDate}">
														<tr>
															<td>${mbrHospitalization.hospital.name}</td>
															<td>${mbrHospitalization.admitDate}</td>
															<td>${mbrHospitalization.expDisDate}</td>
															<td>${mbrHospitalization.priorAdmits}</td>
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
				<div class="tab-pane" id="hospitalizationHistory">
					<div class="panel-group">
						<div class="panel panel-success">
							<div class="panel-heading">Membership Hospitalization List</div>
							<div class="panel-body" id="tablediv">
								<div class="col-sm-12">
									<table id="tab" class="table table-striped table-hover">
										<thead>
											<tr>
												<th scope="col">Hospital</th>
												<th scope="col">Admit Date</th>
												<th scope="col">Exp Discharge Date</th>
												<th scope="col">Prior Admits</th>
											</tr>
										</thead>
										<tbody id="contentprovider">

											<c:forEach items="${mbrHospitalizationList}"
												var="mbrHospitalization">
												<c:choose>
													<c:when test="${not empty mbrHospitalization.expDisDate}">
														<tr>
															<td>${mbrHospitalization.hospital.name}</td>
															<td>${mbrHospitalization.admitDate}</td>
															<td>${mbrHospitalization.expDisDate}</td>
															<td>${mbrHospitalization.priorAdmits}</td>
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


<script type="text/javascript">
	jQuery(document).ready(function($) {
		$('#tabs').tab();
		//  prasPagination('provider');
	});
</script>



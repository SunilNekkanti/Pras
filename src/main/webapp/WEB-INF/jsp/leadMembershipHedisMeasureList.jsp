<%@  page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="context"
	value="${pageContext.request.contextPath}/${userpath}" />

<div id="content">
	<ul id="tabs" class="nav nav-pills" data-tabs="tabs">
		<li class="active"><a href="#pending" data-toggle="tab">Pending</a></li>
		<li><a href="#completed" data-toggle="tab">Completed</a></li>
	</ul>
	<div class="row">
		<div class="col-sm-12">
			<div id="my-tab-content fade active in" class="tab-content">
				<div class="tab-pane active" id="pending">
					<div class="panel-group">
						<div class="panel panel-success">
							<div class="panel-heading">Lead Membership Hedis Measure
									
							</div>
							<div class="panel-body" id="tablediv">
								<div class="col-sm-12">
									<table id="tab" class="table table-striped table-hover">
										<thead>
											<tr>
												<th scope="col">Hedis Measure Code</th>
												<th scope="col">Description</th>
												<th scope="col">Due Date</th>
											</tr>
										</thead>

										<tbody id="contentprovider">

											<c:forEach items="${leadMbrHedisMeasureList}"
												var="leadMbrHedisMeasure">
												<tr>
													<c:forEach items="${hedisMeasureList}" var="hedisMeasure">

														<c:choose>
															<c:when
																test="${leadMbrHedisMeasure.hedisMeasureRule.hedisMeasure.id == hedisMeasure.id && empty leadMbrHedisMeasure.dos   }">
																<td>${hedisMeasure.code}</td>
																<td>${hedisMeasure.description}</td>
																<td><a
																	href="${context}/membership/${leadMbrHedisMeasure.mbr.id}/memberHedisMeasure/${leadMbrHedisMeasure.id}"
																	rel='tab'> ${leadMbrHedisMeasure.dueDate} </a></td>

															</c:when>
														</c:choose>
													</c:forEach>

												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>

				</div>
				<div class="tab-pane" id="completed">
					<div class="panel-group">
						<div class="panel panel-success">
							<div class="panel-heading">Lead Membership Hedis Measure</div>
							<div class="panel-body" id="tablediv">
								<div class="col-sm-12 ">
									<table id="tab" class="table table-striped table-hover">
										<thead>
											<tr>
												<th scope="col">Hedis Measure Code</th>
												<th scope="col">Description</th>
												<th scope="col">Date of Service</th>
											</tr>
										</thead>

										<tbody id="contentprovider">

											<c:forEach items="${mbrHedisMeasureList}"
												var="leadMbrHedisMeasure">
												<tr>
													<c:forEach items="${hedisMeasureList}" var="hedisMeasure">

														<c:choose>
															<c:when
																test="${leadMbrHedisMeasure.hedisMeasureRule.hedisMeasure.id == hedisMeasure.id && not empty leadMbrHedisMeasure.dos }">
																<td>${hedisMeasure.code}</td>
																<td>${hedisMeasure.description}</td>
																<td><a
																	href="${context}/membership/${leadMbrHedisMeasure.mbr.id}/memberHedisMeasure/${leadMbrHedisMeasure.id}"
																	rel='tab'> ${leadMbrHedisMeasure.dos} </a></td>

															</c:when>
														</c:choose>
													</c:forEach>

												</tr>
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
    jQuery(document).ready(function ($) {
        $('#tabs').tab();
      //  prasPagination('provider');
    });
</script>



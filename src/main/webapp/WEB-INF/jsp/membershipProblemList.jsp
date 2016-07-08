<%@  page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="context"
	value="${pageContext.request.contextPath}/${userpath}" />

<div id="content">
	<div class="row">
		<div class="col-sm-12">
			<div class="panel-group">
				<div class="panel panel-success">
					<div class="panel-heading">Membership Problem List</div>
					<div class="panel-body" id="tablediv">
						<div class="col-sm-12">
							<table id="tab" class="table table-striped table-hover">
								<thead>
									<tr>
										<th scope="col">Description</th>
										<th scope="col">Start Date</th>
										<th scope="col">Resolved Date</th>
									</tr>
								</thead>
								<tbody id="contentprovider">

									<c:forEach items="${mbrProblemList}" var="mbrProblem">
										<tr>
											<td>${mbrProblem.pbm.description}</td>
											<td>${mbrProblem.startDate}</td>
											<td>${mbrProblem.resolvedDate}</td>
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


<script type="text/javascript">
	jQuery(document).ready(function($) {
		$('#tabs').tab();
		//  prasPagination('provider');
	});
</script>



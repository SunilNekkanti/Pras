<%@  page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="springForm"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="context"
	value="${pageContext.request.contextPath}/${userpath}" />


<div id="content" class="membershipProblem">
	<div class="tab-pane active" id="active">
		<div class="panel-group">
			<div class="panel panel-success">
				<div class="panel-heading">
					Membership Problem<span class="clrRed">${Message}</span> <a
						class="btn btn-danger pull-right btn-xs white-text"
						href="javascript:void(0)" onclick="return membershipProblemList();">
						<span class="glyphicon glyphicon-plus-sign "></span>Problem List
					</a>
				</div>
				<div class="panel-body">
					<springForm:form method="POST" commandName="membershipProblem"
						class="form-horizontal" role="form">


						<div class="form-group required">
							<label class="control-label col-sm-2"
								for="shortDescription required">Diagnose Date</label>
							<div class="col-sm-6">
							<springForm:hidden path="mbr" class="form-control"
									maxlength="255" id="mbr" placeholder="mbr"/>
							<springForm:hidden path="fileId" class="form-control"
									maxlength="255" id="fileId" placeholder="fileId" />
							<springForm:hidden path="id" class="form-control"
									maxlength="255" id="id" placeholder="id" />		
										<fmt:formatDate value="${membershipProblem.startDate}" var="dateString"
										pattern="MM/dd/yyyy" />
								<springForm:input path="startDate" class="form-control datepickerfrom"
									maxlength="255" id="startDate" placeholder="startDate"  value="${dateString}" />
								<springForm:errors path="startDate" cssClass="error text-danger" />
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-2"
								for="shortDescription required">Resolved Date</label>
							<div class="col-sm-6">
							<fmt:formatDate value="${membershipProblem.resolvedDate}" var="dateString"
										pattern="MM/dd/yyyy" />
								<springForm:input path="resolvedDate" class="form-control datepickerto"
									maxlength="255" id="resolvedDate" placeholder="resolvedDate" value="${dateString}"/>
								<springForm:errors path="resolvedDate"
									cssClass="error text-danger" />
							</div>
						</div>

						<div class="form-group required">
							<label class="control-label col-sm-2" for="pbm">Problem</label>
							<div class="col-sm-6">
								<springForm:select path="pbm" class="form-control" id="pbm">
									<springForm:option value="${null}" label="Select One" />
									<springForm:options items="${problemList}" itemValue="id"
										itemLabel="description" />
								</springForm:select>
								<springForm:errors path="pbm" cssClass="error text-danger" />
							</div>
						</div>
						<div class="col-sm-offset-6 col-sm-4">
									<button type="submit" class="btn btn-success btn-sm"
										id="updateButton" name="update" onclick="return modifyMembershipProblem();">update</button>
									<button type="submit" name ="delete" id="deleteButton" onclick="return deleteMembershipProblem();" class="btn btn-success btn-sm">Delete</button>
						</div>
					</springForm:form>
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



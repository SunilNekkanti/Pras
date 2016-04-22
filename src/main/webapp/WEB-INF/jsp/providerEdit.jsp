<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="springForm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="context"
	value="${pageContext.request.contextPath}/${userpath}" />
<div class="panel-group">
	<div class="panel panel-success">
		<div class="panel-heading">
			Provider Profile <span class="clrRed">${Message}</span> <a
				class="btn btn-danger pull-right btn-xs white-text"
				href="${context}/providerList"> <span
				class="glyphicon glyphicon-plus-sign "></span>Provider List
			</a>
		</div>
		<div class="panel-body" id="tablediv">
			<springForm:form method="POST" id="provider" commandName="provider"
				action="${context}/provider/${id}/save.do">
				<div class="col-sm-12">
					<div class="form-group required col-sm-4">
						<label class="control-label  col-sm-5" for="name">Name</label>
						<div class="col-sm-7">
							<springForm:hidden path="id" />
							<springForm:input path="name" class="form-control" id="name"
								placeholder="Name" />
							<springForm:errors path="name" cssClass="error text-danger" />
						</div>
					</div>
					<div class="form-group required col-sm-4">
						<label class="control-label  col-sm-5" for="code">NPI</label>
						<div class="col-sm-7">
							<springForm:input path="code" class="form-control" id="code"
								placeholder="code" />
							<springForm:errors path="code" cssClass="error text-danger" />
						</div>
					</div>
				</div>
				<div class="col-sm-offset-9 col-sm-3">
					<c:choose>
						<c:when test="${provider.id != null && provider.activeInd == 89}">
							<button type="button" class="btn btn-success btn-sm"
								name="update" id="updateButton"
								onclick="return modifyProviderDetails();">Update</button>
							<button type="button" class="btn btn-success btn-sm"
								name="delete" id="deleteButton"
								onclick="return deleteProviderDetails();">Delete</button>
						</c:when>
						<c:when test="${provider.id == null}">
							<button type="submit" class="btn btn-success btn-sm"
								id="updateButton" name="add">Add</button>
							<button type="submit" class="btn btn-success btn-sm"
								id="resetButton">Reset</button>
						</c:when>
					</c:choose>
				</div>

			</springForm:form>
		</div>
	</div>
</div>

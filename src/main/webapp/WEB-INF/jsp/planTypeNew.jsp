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
			Plan Type <span class="clrRed">${Message}</span> <a
				class="btn btn-danger pull-right btn-xs white-text"
				href="${context}/planTypeList"> <span
				class="glyphicon glyphicon-plus-sign "></span>Plan Type List
			</a>
		</div>


		<div class="panel-body" id="tablediv">
			<springForm:form method="POST" commandName="planType"
				action="save.do">
				<div class="form-group required col-sm-12">
					<label class="control-label  col-sm-2" for="code">Code</label>
					<div class="col-sm-6">
						<springForm:hidden path="id" />
						<springForm:input path="code" class="form-control" id="code"
							placeholder="User Id" />
						<springForm:errors path="code" cssClass="error text-danger" />
					</div>
				</div>

				<div class="form-group required col-sm-12">
					<label class="control-label  col-sm-2" for="description">description</label>
					<div class="col-sm-6">
						<springForm:input path="description" class="form-control"
							id="description" placeholder="description" />
						<springForm:errors path="description" cssClass="error text-danger" />
					</div>
				</div>



				<div class="col-sm-offset-2 col-sm-6">
					<c:choose>
						<c:when test="${planType.id != null && planType.activeInd == 89}">
							<button type="submit" class="btn btn-success btn-sm"
								name="update" id="updateButton">Update</button>
							<button type="submit" class="btn btn-success btn-sm"
								name="delete" id="deleteButton">Delete</button>
						</c:when>
						<c:when test="${planType.id == null}">
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


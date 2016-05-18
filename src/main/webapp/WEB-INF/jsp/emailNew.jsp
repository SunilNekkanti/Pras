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
			Email Template Details <span class="clrRed">${Message}</span> <a
				class="btn btn-danger pull-right btn-xs white-text"
				href="${context}/email/emailList"> <span
				class="glyphicon glyphicon-plus-sign "></span>Email Template List
			</a>
		</div>
		<div class="panel-body" id="tablediv">
			<springForm:form method="POST" commandName="email"
				action="save.do" class="form-horizontal" role="form">
				
				<div class="form-group required">
					<label class="control-label col-sm-2" for="emailFrom">From</label>
					<div class="col-sm-8">
						<springForm:hidden path="id" />
						<springForm:input path="emailFrom" class="form-control" maxlength="250"
							id="code" placeholder="Email From" />
						<springForm:errors path="emailFrom" cssClass="error text-danger" />
					</div>
				</div>
				
				<div class="form-group required">
					<label class="control-label col-sm-2" for="emailTo">To</label>
					<div class="col-sm-8">
						<springForm:input path="emailTo" class="form-control" maxlength="250"
							id="code" placeholder="Email To" />
						<springForm:errors path="emailTo" cssClass="error text-danger" />
					</div>
				</div>
				
				<div class="form-group required">
					<label class="control-label col-sm-2" for="emailCc">CC</label>
					<div class="col-sm-8">
						<springForm:input path="emailCc" class="form-control" maxlength="250"
							id="code" placeholder="Email CC" />
						<springForm:errors path="emailCc" cssClass="error text-danger" />
					</div>
				</div>
				
				<div class="form-group required">
					<label class="control-label col-sm-2" for="emailTemplate">Email Template</label>
					<div class="col-sm-8">
						<springForm:select path="emailTemplate" class="form-control"
							id="county">
							<springForm:option value="${null}" label="Select One" />
							<springForm:options items="${emailTemplateList}" itemValue="id"
											itemLabel="description" />
						</springForm:select>
						<springForm:errors path="emailTemplate"
										cssClass="error text-danger" />
					</div>
				</div>
				
				<div class="form-group required">
					<label class="control-label col-sm-2" for="body">Content</label>
					<div class="col-sm-8">
						<springForm:textarea path="body" rows="10" class="form-control" maxlength="250"
							id="code" placeholder="Content" />
						<springForm:errors path="body" cssClass="error text-danger" />
					</div>
				</div>
				
				<div class="col-sm-12">
					<div class="col-sm-offset-2 col-sm-9">
						<c:choose>
							<c:when
								test="${email.id != null && email.activeInd == 89}">
								<button type="submit" class="btn btn-success btn-sm"
									id="updateButton" name="update">Update</button>
								<button type="submit" class="btn btn-success btn-sm"
									id="deleteButton" name="delete">Delete</button>
							</c:when>
							<c:otherwise>
								<button type="submit" class="btn btn-success btn-sm"
									id="updateButton" name="add">Add</button>
								<button type="submit" class="btn btn-success btn-sm"
									id="resetButton">Reset</button>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</springForm:form>
		</div>
	</div>
</div>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
    prefix="springForm"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="row">
	<div class="col-sm-12">
		<div class="col-sm-6">
			<jsp:include page="/WEB-INF/jsp/membershipDisplay.jsp">
 				<jsp:param name="membershipDisplay" value="${membershipDisplay}"/>
			</jsp:include>
				
		</div>						
		<div class="col-sm-6">
			<jsp:include page="/WEB-INF/jsp/membershipContact.jsp">
 				<jsp:param name="membershipContact" value="${membershipContact}"/>
			</jsp:include>
		</div>
	</div>
</div>	
<!--  Provider Details -->
<div class="row">
	<div class="col-sm-12">
		<div class="col-sm-6">
			<jsp:include page="/WEB-INF/jsp/membershipProviderContact.jsp">
 				<jsp:param name="membershipProviderContact" value="${membershipProviderContact}"/>
			</jsp:include>
		</div>
		<div class="col-sm-6">
			<div class="panel-group">
				<jsp:include page="/WEB-INF/jsp/membershipInsuranceContact.jsp">
 				<jsp:param name="membershipInsuranceContact" value="${membershipInsuranceContact}"/>
			</jsp:include>
			</div>
		</div>
	</div>
</div>	
<!--  Insurance Details -->
<div class="row">
	<div class="col-sm-12">
		<div class="col-sm-6">
			<div class="panel-group">
				<div class="panel panel-primary">
					<div class="panel-heading">Hedis Measure</div>
					<div class="panel-body"></div>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="panel-group">
				<div class="panel panel-primary">
					<div class="panel-heading">Problem</div>
					<div class="panel-body"></div>
				</div>
			</div>
		</div>
	</div>
</div>	
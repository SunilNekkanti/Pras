<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<title>PF Choice</title>

<div class="row" style="height: 550px;overflow-y: scroll;">

	<div class="col-sm-12" >
			<jsp:include page="/WEB-INF/jsp/membershipList.jsp">
			 <jsp:param name="membershipList" value="${membershipList}"/>
			</jsp:include>
			
			<jsp:include page="/WEB-INF/jsp/providerList.jsp">
			 <jsp:param name="providerList" value="${providerList}"/>
			</jsp:include>
			
			
			<jsp:include page="/WEB-INF/jsp/insuranceList.jsp">
			 <jsp:param name="insuranceList" value="${insuranceList}"/>
			</jsp:include>
	</div>

</div>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PF Choice</title>
</head>
<body>

<jsp:include page="/WEB-INF/jsp/membershipList.jsp">
 <jsp:param name="membershipList" value="${membershipList}"/>
</jsp:include>

<jsp:include page="/WEB-INF/jsp/providerList.jsp">
 <jsp:param name="providerList" value="${providerList}"/>
</jsp:include>


<jsp:include page="/WEB-INF/jsp/insuranceList.jsp">
 <jsp:param name="insuranceList" value="${insuranceList}"/>
</jsp:include>


</body>
</html>
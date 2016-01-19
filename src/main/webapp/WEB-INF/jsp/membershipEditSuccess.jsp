<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>Membership Updated Successfully</title>
</head>
<body>
<h3>
    Membership Updated Successfully.
</h3>
 
<strong>First Name:${membership.firstName}</strong><br>
<strong>Last Name:${membership.lastName}</strong><br>
<strong>Date of Birth:${membership.dob}</strong><br>
 
</body>
</html>

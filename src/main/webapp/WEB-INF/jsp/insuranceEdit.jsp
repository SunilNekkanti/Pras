<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form"
    prefix="springForm"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insurance Edit Page</title>
<style>
.error {
    color: #ff0000;
    font-style: italic;
    font-weight: bold;
}
</style>
</head>
<body>
 
    <springForm:form method="POST" commandName="insurance"
        action="save.do">
        <table>
        	<tr>
                <td><springForm:hidden path="id" /></td>
            </tr>
            <tr>
                <td>Name:</td>
                <td><springForm:input path="name" /></td>
                <td><springForm:errors path="name" cssClass="error" /></td>
            </tr>
            <tr>
                <td>
                <div class="col-sm-offset-2 col-sm-4">
						<button type="submit" class="btn btn-primary" id="cancelButton">Create</button>
						<button type="submit" class="btn btn-primary" id="updateButton">Update</button>
				</div></td>
            </tr>
            
        </table>
 
    </springForm:form>
 
</body>
</html>

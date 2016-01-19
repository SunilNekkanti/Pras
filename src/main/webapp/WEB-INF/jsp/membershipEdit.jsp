<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form"
    prefix="springForm"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Membership Profile Edit Page</title>
<style>
.error {
    color: #ff0000;
    font-style: italic;
    font-weight: bold;
}
</style>
</head>
<body>
 
    <springForm:form method="POST" commandName="membership"
        action="save.do">
        <table>
        	<tr>
                <td><springForm:hidden path="id" /></td>
            </tr>
            <tr>
                <td>First Name:</td>
                <td><springForm:input path="firstName" /></td>
                <td><springForm:errors path="firstName" cssClass="error" /></td>
            </tr>
            <tr>
                <td>Last Name:</td>
                <td><springForm:input path="lastName" /></td>
                <td><springForm:errors path="lastName" cssClass="error" /></td>
            </tr>
            <tr>
                <td>Date Of Birth:</td>
                <td><springForm:input path="dob" /></td>
                <td><springForm:errors path="dob" cssClass="error" /></td>
            </tr>
           <tr>
                <td>Status:</td>
                <td>
                 <springForm:select path="status.id">
    			 <springForm:options items="${statusMap}"    />
				</springForm:select>
				</td>
				<td><springForm:errors path="status.description" cssClass="error" /></td>
            </tr>
            <tr>
                <td>Gender:</td>
                <td>
                 <springForm:select path="genderId.id">
    			 <springForm:options items="${genderMap}"    />
				</springForm:select>
				</td>
				<td><springForm:errors path="genderId.description" cssClass="error" /></td>
            </tr>
            <tr>
                <td>County:</td>
                <td>
                 <springForm:select path="countyCode.code">
    			 <springForm:options items="${countyMap}"    />
				</springForm:select>
				</td>
				<td><springForm:errors path="countyCode.description" cssClass="error" /></td>
            </tr>
            <tr>
                <td>
                <div class="col-sm-offset-2 col-sm-4">
						<button type="submit" class="btn btn-primary" id="updateButton">Update</button>
						<button type="submit" class="btn btn-primary" id="cancelButton">Cancel</button>
				</div></td>
            </tr>
            
        </table>
 
    </springForm:form>
 
</body>
</html>

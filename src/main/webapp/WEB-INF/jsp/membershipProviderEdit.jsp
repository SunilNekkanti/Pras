<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
    prefix="springForm"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="panel-group">
	<div class="panel panel-primary">
		<div class="panel-heading">Provider Info</div>
		<div class="panel-body" id="tablediv">
			<springForm:form method="GET" commandName="membershipProvider" action="save.do">
				<div class="form-group col-sm-12">
						    <label class="control-label col-sm-2" for="name">Name:</label>
						    <div class="col-sm-6">
						    	<springForm:hidden path="id" />
						    	<springForm:hidden path="prvdr.id" />
						      	<springForm:input path="prvdr.name" class="form-control" id="name" placeholder="${membershipProvider.prvdr.name}" />
						      	<springForm:errors path="prvdr.name" cssClass="error" />
						      	<c:out value="${membershipProvider.prvdr.id}"> issue</c:out>
						      	
						    </div>
				 </div>
				
				 
			</springForm:form>
			
			
			<div class="row col-sm-12">
				<div class="col-sm-12" style="padding-top:2px;">
					<a href="/Pras/ProviderList">Click Here</a> to see Provider List
				</div>	
			</div>
			
 		</div>
	</div>
</div>		

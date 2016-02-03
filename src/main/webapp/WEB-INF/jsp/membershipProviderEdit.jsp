<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
    prefix="springForm"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="panel-group">
	<div class="panel panel-primary">
		<div class="panel-heading">Provider Info</div>
		<div class="panel-body" id="tablediv">
			<springForm:form  commandName="membershipProvider" action="save.do">
				<springForm:hidden path="id" />
						      	
				<div class="form-group col-sm-12">
						    <label class="control-label col-sm-2" for="name">Name:</label>
						    <div class="col-sm-6">
						    	<springForm:input path="prvdr.name" class="form-control" id="name" placeholder="${membershipProvider.prvdr.name}" />
						      	<springForm:errors path="prvdr.name" cssClass="error" />
						    </div>
				 </div>
				 <div class="form-group col-sm-12">
						    <label class="control-label col-sm-2" for="name">Code:</label>
						    <div class="col-sm-6">
						      	<springForm:input path="prvdr.code" class="form-control" id="code" placeholder="${membershipProvider.prvdr.code}" />
						      	<springForm:errors path="prvdr.name" cssClass="error" />
						    </div>
				 </div>
			</springForm:form>
			
			<div class="row col-sm-12">
				<div class="col-sm-12" style="padding-top:2px;">
					<a href="/Pras/membership/${membershipProvider.mbr.id}/providerDetailsList">Click Here</a> to see Membership Provider List
				</div>	
			</div>
			
 		</div>
	</div>
</div>		

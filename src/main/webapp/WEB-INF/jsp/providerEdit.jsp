<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
    prefix="springForm"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="row" style="padding:20px;">
	
 </div>

<div class="panel-group">
	<div class="panel panel-primary">
		<div class="panel-heading">Provider Profile</div>
		<div class="panel-body" id="tablediv">
			<springForm:form method="POST" commandName="provider" action="save.do">
				<div class="form-group col-sm-12">
						    <label class="control-label col-sm-2" for="name">Name:</label>
						    <div class="col-sm-6">
						    	<springForm:hidden path="id" />
						      	<springForm:input path="name" class="form-control" id="name" placeholder="Name" />
						      	<springForm:errors path="name" cssClass="error" />
						    </div>
				 </div>
				 
				 <div class="form-group col-sm-12">
						    <label class="control-label col-sm-2" for="code">Code:</label>
						    <div class="col-sm-6">
						    	<springForm:input path="code" class="form-control" id="code" placeholder="code" />
						      	<springForm:errors path="code" cssClass="error" />
						    </div>
				</div>
				<div class="form-group col-sm-12">
							    <label class="control-label col-sm-2" for="activeInd">ActiveInd:</label>
							    <div class="col-sm-6">
								    <springForm:select path="activeInd" class="form-control" id="activeInd" >
						    			<springForm:options items="${activeIndMap}" />
									</springForm:select>
									<springForm:errors path="activeInd" cssClass="error text-danger" />						    	
							    </div>
					</div>
				
				<div class="col-sm-offset-2 col-sm-6">
					<button type="submit" class="btn btn-primary" id="updateButton">Update</button>
					<button type="submit" class="btn btn-primary" id="deleteButton">Delete</button>
				</div>
				 
			</springForm:form>
 		</div>
	</div>
</div>		

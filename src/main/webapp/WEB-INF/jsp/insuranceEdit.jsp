<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"    prefix="springForm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="panel-group">
	<div class="panel panel-primary">
		<div class="panel-heading">Insurance Profile</div>
		<div class="panel-body">
			<springForm:form method="POST" commandName="insurance" action="save.do" class="form-horizontal" role="form">
			     <div class="form-group">
				 	<label class="control-label col-sm-2" for="name">Name:</label>
					<div class="col-sm-6">
						<springForm:hidden path="id" />
						<springForm:input path="name" class="form-control" id="name" placeholder="name" />
						<springForm:errors path="name" cssClass="error" />
					</div>
				 </div>			
				<div class="col-sm-offset-2 col-sm-4">
					<button type="submit" class="btn btn-primary" id="updateButton">Update</button>
					<button type="submit" class="btn btn-primary" name="delete" id="deleteButton">Delete</button>
				</div>
			</springForm:form>
		</div>
	</div>
</div>						    
 		    	
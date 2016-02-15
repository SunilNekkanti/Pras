<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
    prefix="springForm"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="panel-group">
	<div class="panel panel-primary">
		<div class="panel-heading">User Profile</div>
		<div class="panel-body" id="tablediv">
			<springForm:form method="POST" commandName="user" action="/Pras/user/${id}/save.do">
				<div class="form-group required col-sm-12">
						    <label class="control-label  col-sm-2" for="name">Login</label>
						    <div class="col-sm-6">
						    	<springForm:hidden path="id" />
						      	<springForm:input path="login" class="form-control" id="login" placeholder="User Id" />
						      	<springForm:errors path="login" cssClass="error text-danger" />
						    </div>
				 </div>
				 
				 <div class="form-group required col-sm-12">
						    <label class="control-label  col-sm-2" for="code">Password</label>
						    <div class="col-sm-6">
						    	<springForm:input path="password" class="form-control" id="password" placeholder="Password" />
						      	<springForm:errors path="password" cssClass="error text-danger" />
						    </div>
				</div>
				
				<div class="form-group required">
							<label class="control-label col-sm-4" for="county">Role</label>
							<div class="col-sm-8">
								<springForm:select multiple="true" path="roles" class="form-control"  items="${roles}" itemLabel="role" itemValue="id" />
								<springForm:errors path="roles" cssClass="error text-danger" />
							</div>
				</div>
							
				<div class="col-sm-offset-2 col-sm-6">
					<c:choose>
						 <c:when test="${user.id != null && user.activeInd == 89}"> 
							<button type="submit" class="btn btn-primary" name="update" id="updateButton">Update</button>
							<button type="submit" class="btn btn-primary" name="delete" id="deleteButton">Delete</button>
						 </c:when>
						 <c:when test="${user.id == null}"> 
							<button type="submit" class="btn btn-primary" id="updateButton" name="add" >Add</button>
							<button type="submit" class="btn btn-primary" id="resetButton" >Reset</button>
						</c:when>
					</c:choose>	
				</div>
				 
			</springForm:form>
			
			
			<div class="row col-sm-12">
				<div class="col-sm-12" style="padding-top:2px;">
					<a href="/Pras/userList">Click Here</a> to see User List
				</div>	
			</div>
			
 		</div>
	</div>
</div>	


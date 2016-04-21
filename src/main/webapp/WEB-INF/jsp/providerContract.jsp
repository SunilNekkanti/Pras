<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
    prefix="springForm"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h2>  ${provider.name} ${provider.id} </h2>
<div class="panel-group">
	<div class="panel panel-success">
		<div class="panel-heading">${contractType} Details</div>
		<div class="panel-body" id="tablediv">
			<springForm:form method="POST" commandName="providerContract" action="save.do">
				<div class="form-group col-sm-12">
						    <label class="control-label col-sm-2" for="name">Contract NBR:</label>
						    <div class="col-sm-6">
						    	
						      	<springForm:input path="contractNBR" class="form-control" id="contractNBR" placeholder="contractNBR" />
						      	<springForm:errors path="contractNBR" cssClass="error text-danger" />
						    </div>
				 </div>
				 
				 <div class="form-group col-sm-12">
						    <label class="control-label col-sm-2" for="code">PMPM:</label>
						    <div class="col-sm-6">
						    	<springForm:input path="pmpm" class="form-control" id="pmpm" placeholder="PMPM" />
						      	<springForm:errors path="pmpm" cssClass="error text-danger" />
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
					<button type="submit" class="btn btn-success btn-sm" id="updateButton">Update</button>
					<button type="submit" class="btn btn-success btn-sm" id="deleteButton">Delete</button>
				</div>
				 
			</springForm:form>
 		</div>
	</div>
</div>		

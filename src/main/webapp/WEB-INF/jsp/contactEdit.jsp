<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
    prefix="springForm"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<c:set var="context" value="${pageContext.request.contextPath}" />
<div class="panel-group">
	<div class="panel panel-success">
		<div class="panel-heading">
			Contact Details
			<button class="btn btn-danger pull-right btn-xs" onclick= "return contactList();">
          		<span class="glyphicon glyphicon-plus-sign "></span> contact List
          	</button>
        </div>
		<div class="panel-body" id="tablediv">
			<springForm:form method="POST" id="contact" commandName="contact" action="save.do" class="form-horizontal" role="form">
				<div class ="col-sm-5">	
					<div class="form-group">
						<label class="control-label col-sm-4" for="contactPerson">Contact Person:</label>
						<div class="col-sm-8">
							<springForm:input path="contactPerson" class="form-control" id="contactPerson" placeholder="contact Person" />
							<springForm:errors path="contactPerson" cssClass="error text-danger" />
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-sm-4" for="homePhone">Land Phone</label>
						<div class="col-sm-8">
							<springForm:hidden path="id" />
							<springForm:hidden path="refContact.id" />
							 <c:choose>
							 <c:when test="${contact.refContact.mbr != null}"> 
								<springForm:hidden path="refContact.mbr.id" />
							 </c:when>
							 <c:when test="${contact.refContact.prvdr != null}">
								<springForm:hidden path="refContact.prvdr.id" />
							</c:when>
							<c:otherwise>
								<springForm:hidden path="refContact.ins.id" />
							</c:otherwise>
							</c:choose>
							
							<springForm:hidden path="refContact.createdBy" value="sarath"/>
							<springForm:input path="homePhone" class="form-control" id="homePhone" placeholder="Home Phone" />
							<springForm:errors path="homePhone" cssClass="error text-danger" />
						</div>
					</div>
				 	
					 
					<div class="form-group">
						<label class="control-label col-sm-4" for="mobilePhone">Mobile Phone</label>
						<div class="col-sm-8">
							<springForm:input path="mobilePhone" class="form-control" id="mobilePhone" placeholder="Mobile Phone" />
							<springForm:errors path="mobilePhone" cssClass="error text-danger" />
						</div>
					</div>
					<div class="form-group">
					 	<label class="control-label col-sm-4" for="faxNumber">Fax Number</label>
						<div class="col-sm-8">
							<springForm:input path="faxNumber" class="form-control" id="fax" placeholder="Fax Number" />
							<springForm:errors path="faxNumber" cssClass="error text-danger" />
						</div>
					</div>
				
					<div class="form-group">
					 	<label class="control-label col-sm-4" for="email">Email</label>
						<div class="col-sm-8">
							<springForm:input path="email" class="form-control" id="email" placeholder="email" />
							<springForm:errors path="email" cssClass="error text-danger" />
						</div>
					</div>
				</div>	
				<div class ="col-sm-5">	 
					 <div class="form-group required">
					 	<label class="control-label  col-sm-4" for="faxNumber">Address 1</label>
						<div class="col-sm-8">
							<springForm:input path="address1" class="form-control" id="address1" placeholder="Address 1" />
							<springForm:errors path="address1" cssClass="error text-danger" />
						</div>
					</div>
					
					<div class="form-group">
					 	<label class="control-label col-sm-4" for="address2">Address 2</label>
						<div class="col-sm-8">
							<springForm:input path="address2" class="form-control" id="address2" placeholder="Address 2" />
							<springForm:errors path="address2" cssClass="error text-danger" />
						</div>
					</div>
					
					<div class="form-group required">
					 	<label class="control-label  col-sm-4" for="city">City</label>
						<div class="col-sm-8">
							<springForm:input path="city" class="form-control" id="city" placeholder="city"/>
							<springForm:errors path="city" cssClass="error text-danger" />
						</div>
					</div>
					
					<div class="form-group required">
						<label class="control-label col-sm-4" for="state">State</label>
						<div class="col-sm-8">
							<springForm:select path="stateCode" class="form-control" id="state">
					    		<springForm:options items="${stateList}" itemValue="code" itemLabel="shortName"     />
							</springForm:select>
							<springForm:errors path="stateCode.code" cssClass="error text-danger" />
						  </div>
					</div>
						<div class="form-group required">
						<label class="control-label  col-sm-4" for="zip">Zip</label>
						<div class="col-sm-8" id="tmpZip">
							<springForm:select path="zipCode" class="form-control" id="zip">
					    		
							</springForm:select>
							<springForm:errors path="zipCode.code" cssClass="error text-danger" />
						  </div>
					</div>
					<div class="col-sm-offset-6 col-sm-6">
						<c:choose>
							 <c:when test="${id != null && contact.activeInd == 89}">  
							 	<button type="button" class="btn btn-success btn-sm" id="updateButton" onclick="return modifyContact();"  name="update" >Update</button>
							 	<button type="button" class="btn btn-success btn-sm" id="deleteButton" name="delete" onclick="return deleteContact();" >Delete</button>
							 </c:when>
							 <c:when test="${contact.id == null}">
								<button type="button" class="btn btn-success btn-sm" id="addButton" onclick="return addContact();"  name="add" >Add</button>
								<button type="button" class="btn btn-success btn-sm" id="resetButton" >Reset</button>
							</c:when>
							</c:choose>
					</div>
				</div>	
			</springForm:form>
 		</div>
	</div>
</div>

<c:choose>
	<c:when test="${contact.stateCode != null || request.getParameter('stateCode')}"> 
		<script>
				 var zCode;
				 var stateId = $( "#state" ).val();
			     var $select = $('#zip');
			   
			   //request the JSON data and parse into the select element
				   $.getJSON(getContextPath()+'contact/state/'+stateId, function(data){
				   }).success(function(data) 
				   {  
					    //clear the current content of the select
					     $select.html('');
					     $select.append('<option value="">Select Zip Code</option>');
					     var zipRequest = "<%= request.getParameter("zipCode") %>";
					     var zipCode = "${contact.zipCode.code}";
					     if(zipCode != null)
					    	 zCode = zipCode; 
					     else if(zipRequest != null)		 
					          zCode = zipRequest;
					     
					     //iterate over the data and append a select option
					     $.each(data.data, function(key, val){
					    	
					      if(val.code == zCode)
					    	  $select.append('<option value="'+val.code+'" selected>' + val.code +'</option>');
					      else
					    	  $select.append('<option value="' + val.code + '">' + val.code +'</option>');
						   
					     });	   
					});
			
			   $( "#state" ).change(function() {
				 
			     var stateId = $( "#state" ).val();
			     var $select = $('#zip');
			   //request the JSON data and parse into the select element
				   $.getJSON(getContextPath()+'contact/state/'+stateId, function(data){
				    
				     //clear the current content of the select
				     $select.html('');
				     $select.append('<option value="">Select Zip Code</option>');
				     //iterate over the data and append a select option
				     $.each(data.data, function(key, val){
				      
				       $select.append('<option value="' + val.code + '">' + val.code +'</option>');
				     })
				   });
			    
			   });
	  	</script>
	</c:when>
	<c:otherwise>
		
	</c:otherwise>
</c:choose>




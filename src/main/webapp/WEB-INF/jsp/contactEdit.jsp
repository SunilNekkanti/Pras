<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
    prefix="springForm"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<c:set var="context" value="${pageContext.request.contextPath}" />
<script src="${context}/resources/js/validation.js"></script>
<c:choose>
 	<c:when test="${contact.id != null}"> 	
	<script>
		$(document).ready(function(){				
			removePlaceHolder();contactValidation();
		});
	</script>
	</c:when>
	<c:otherwise>
		<script>$(document).ready(function(){contactValidation();});</script>
	</c:otherwise>
</c:choose>

<div class="panel-group">
	<div class="panel panel-primary">
		<div class="panel-heading">Contact</div>
		<div class="panel-body" id="tablediv">
			<springForm:form method="POST" id="contact" commandName="contact" action="save.do" class="form-horizontal" role="form">
				<div class="form-group">
					<label class="control-label col-sm-2" for="homePhone">Home Phone</label>
					<div class="col-sm-6">
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
					<label class="control-label col-sm-2" for="mobilePhone">Mobile Phone</label>
					<div class="col-sm-6">
						<springForm:input path="mobilePhone" class="form-control" id="mobilePhone" placeholder="Mobile Phone" />
						<springForm:errors path="mobilePhone" cssClass="error text-danger" />
					</div>
				</div>
				<div class="form-group">
				 	<label class="control-label col-sm-2" for="faxNumber">Fax Number</label>
					<div class="col-sm-6">
						<springForm:input path="faxNumber" class="form-control" id="fax" placeholder="Fax Number" />
						<springForm:errors path="faxNumber" cssClass="error text-danger" />
					</div>
				</div>
				
				<div class="form-group">
				 	<label class="control-label col-sm-2" for="email">Email</label>
					<div class="col-sm-6">
						<springForm:input path="email" class="form-control" id="email" placeholder="email" />
						<springForm:errors path="email" cssClass="error text-danger" />
					</div>
				</div>
				 
				 <div class="form-group required">
				 	<label class="control-label  col-sm-2" for="faxNumber">Address 1</label>
					<div class="col-sm-6">
						<springForm:input path="address1" class="form-control" id="address1" placeholder="Address 1" />
						<springForm:errors path="address1" cssClass="error text-danger" />
					</div>
				</div>
				
				<div class="form-group">
				 	<label class="control-label col-sm-2" for="address2">Address 2</label>
					<div class="col-sm-6">
						<springForm:input path="address2" class="form-control" id="address2" placeholder="Address 2" />
						<springForm:errors path="address2" cssClass="error text-danger" />
					</div>
				</div>
				
				<div class="form-group required">
				 	<label class="control-label  col-sm-2" for="city">City</label>
					<div class="col-sm-6">
						<springForm:input path="city" class="form-control" id="city" placeholder="city"/>
						<springForm:errors path="city" cssClass="error text-danger" />
					</div>
				</div>
				
				<div class="form-group required">
					<label class="control-label col-sm-2" for="state">State</label>
					<div class="col-sm-6">
						<springForm:select path="stateCode" class="form-control" id="state">
				    		<springForm:options items="${stateList}" itemValue="code" itemLabel="shortName"     />
						</springForm:select>
						<springForm:errors path="stateCode.code" cssClass="error text-danger" />
					  </div>
				</div>
					<div class="form-group required">
					<label class="control-label  col-sm-2" for="zip">Zip</label>
					<div class="col-sm-6" id="tmpZip">
						<springForm:select path="zipCode" class="form-control" id="zip">
				    		<springForm:options items="${zipCodeList}" itemValue="code"  itemLabel="code" />
						</springForm:select>
						<springForm:errors path="zipCode.code" cssClass="error text-danger" />
					  </div>
				</div>
				<div class="col-sm-offset-6 col-sm-4">
					<c:choose>
						 <c:when test="${contact.id != null}"> 
						 	<button type="submit" class="btn btn-primary" id="updateButton" name="update" >Update</button>
						 	<button type="submit" class="btn btn-primary" id="deleteButton" name="delete" >Delete</button>
						 </c:when>
						 <c:otherwise>
							<button type="submit" class="btn btn-primary" id="updateButton" name="add" >Add</button>
							<button type="submit" class="btn btn-primary" id="resetButton" >Reset</button>
						</c:otherwise>
						</c:choose>
				</div>
			</springForm:form>
 		</div>
	</div>
</div>

<script>
  $( "#state" ).change(function() {
	 
     var stateId = $( "#state" ).val();
     var $select = $('#zip');
     alert( "stateId"+stateId );
   //request the JSON data and parse into the select element
	   $.getJSON('http://localhost:8080/Pras/contact/state/'+stateId, function(data){
	    
		   alert( data.data);
	     //clear the current content of the select
	     $select.html('');
	    
	     //iterate over the data and append a select option
	     $.each(data.data, function(key, val){
	       $select.append('<option id="' + val.code + '">' + val.code +'</option>');
	     })
	   });
    
   });
  
</script>



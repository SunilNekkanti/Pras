<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"    prefix="springForm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="context" value="${pageContext.request.contextPath}" />
<script src="${context}/resources/js/validation.js"></script>
 <c:choose>
 	<c:when test="${insurance.id != null}"> 
	<script>
		$(document).ready(function(){	 
		
	
		});
	</script>
	</c:when>	
	<c:otherwise>
		<script>$(document).ready(function(){});</script>
	</c:otherwise>
</c:choose>
<div class="panel-group">
	<div class="panel panel-primary">
		<div class="panel-heading">Insurance Profile</div>
		<div class="panel-body">
			<springForm:form method="POST" commandName="insurance" action="${context}/insurance/${id}/save.do" class="form-inline" role="form">
			     <div class="form-group required col-sm-3">
				 	<div class="col-sm-4"><label for="name">Name</label></div>
					<div class="col-sm-8">
						<springForm:hidden path="id" />
						<springForm:input path="name" class="form-control" id="name" placeholder="name" />
					</div>	
					<div class="col-sm-12">
						<springForm:errors path="name" cssClass="error text-danger" />
					</div>	
						
					
				 </div>		
				 
				 <div class="form-group required col-sm-3">
				 	<div class="col-sm-3"><label for="Plan" >Plan</label></div>
				 	<div class="col-sm-8">
						<springForm:select path="planTypeId" class="form-control" id="planTypeId" style="width:150px;">
				    		<springForm:options items="${planTypeList}" itemValue="id" itemLabel="code"     />
							</springForm:select>
					</div>
					<div class="col-sm-12">
						<springForm:errors path="planTypeId.code" cssClass="error text-danger" />
					</div>	
					  
				</div>
				 <div class="form-group col-sm-4">
					<c:choose>
						
						 <c:when test="${insurance.id != null && insurance.activeInd == 89}"> 
						 	<div class="col-sm-3">
						 		<button type="submit" class="btn btn-primary" id="updateButton" name="update" >Update</button>
						 	</div>
						 	<div class="col-sm-3">	
						 		<button type="submit" class="btn btn-primary" id="deleteButton" name="delete" >Delete</button>
						 </div>	
						 </c:when>
						 <c:when test="${insurance.id == null}"> 
						 	<div class="col-sm-3">
								<button type="submit" class="btn btn-primary" id="updateButton" name="add" >Add</button>
							</div>
							<div class="col-sm-3">
								<button type="submit" class="btn btn-primary" id="resetButton" >Reset</button>
							</div>	
						</c:when>
					</c:choose>					
				</div>				
			</springForm:form>			
		</div>
	</div>	
</div>	
<div id="insuranceContractList"></div>
<div id="insuranceContractEdit"></div>
<div id="insuranceContractNew"></div>
<div id="insuranceContactList"></div>
<div id="insuranceContactEdit"></div>
<div id="insuranceContactNew"></div>

<script>
	var source = getContextPath()+'insurance/${id}/contractList';
	$.ajax({
		url : source,
	    success: function(data, textStatus, jqXHR)
	    {
	       $('#insuranceContractList').html(data);
	    },
	    error: function (jqXHR, textStatus, errorThrown)
	    {
	  	  alert("Error Insurance");
	    }
	});
	
	var source = getContextPath()+'insurance/${id}/contactList';
	$.ajax({
		url : source,
	    success: function(data, textStatus, jqXHR)
	    {
	       $('#insuranceContactList').html(data);
	    },
	    error: function (jqXHR, textStatus, errorThrown)
	    {
	  	  alert("Error");
	    }
	});
		
	function contract(insuranceId,contractId)
	{
		var source = getContextPath()+'insurance/'+insuranceId+'/contract/'+contractId;
		$.ajax({
			url : source,
		     success: function(data, textStatus, jqXHR)
		    {
		    	$('#insuranceContractList').css("display","none");
		    	$('#insuranceContractNew').show();
		        $('#insuranceContractEdit').html(data);
		    },
		    error: function (jqXHR, textStatus, errorThrown)
		    {
		  	  alert("Error");
		    }
		});
		return false;	
	}
	
	function contact(insuranceId,contactId)
	{
		var source = getContextPath()+'insurance/'+insuranceId+'/contact/'+contactId;
		$.ajax({
			url : source,
		     success: function(data, textStatus, jqXHR)
		    {
		    	$('#insuranceContactList').css("display","none");
		    	$('#insuranceContactNew').show();
		        $('#insuranceContactEdit').html(data);
		    },
		    error: function (jqXHR, textStatus, errorThrown)
		    {
		  	  alert("Error");
		    }
		});
		return false;	
	}
	
	var source = getContextPath()+'insurance/${id}/contractList';
	$.ajax({
		url : source,
	    success: function(data, textStatus, jqXHR)
	    {
	       $('#insuranceContractList').html(data);
	    },
	    error: function (jqXHR, textStatus, errorThrown)
	    {
	  	  alert("Error");
	    }
	});

	function newContract()
	{
		var source = getContextPath()+'insurance/${id}/contract/new';
		$.ajax({
			url : source,
		 	success: function(data, textStatus, jqXHR)
		    {
		 		$('#insuranceContractList').css("display","none");
		 		$('#insuranceContractNew').show();
		        $('#insuranceContractNew').html(data);
		        $('#contract').on('submit', function (event) {
		        	
		        });
		    },
		    error: function (jqXHR, textStatus, errorThrown)
		    {
		  	  alert("Error");
		    }
		});
	}
	
	function newContact()
	{
		var source = getContextPath()+'insurance/${id}/contact/new';
		$.ajax({
			url : source,
		 	success: function(data, textStatus, jqXHR)
		    {
		 		$('#insuranceContactList').css("display","none");
		 		$('#insuranceContactNew').show();
		        $('#insuranceContactNew').html(data);
		    },
		    error: function (jqXHR, textStatus, errorThrown)
		    {
		  	  alert("Error");
		    }
		});
	}
	
	function contractList()
	{
		$('#insuranceContractNew').css("display","none");
 		$('#insuranceContractEdit').css("display","none");
 		$('#insuranceContractList').show();
		
	}
	
	function contactList()
	{
		$('#insuranceContactNew').css("display","none");
 		$('#insuranceContactEdit').css("display","none");
 		$('#insuranceContactList').show();
		
	}
	
	function addContract()
	{
		var url = getContextPath()+'insurance/${id}/contract/save.do?add'; 
		var dataList = 	$("#contract").serializeArray();
		$.ajax({
	           type: "POST",
	           url: url,
	           data: dataList, 
	           success: function(data)
	           {
	              
	               $('#insuranceContractNew').html(data);
	           },
	    		error:function(data)
	    		{
	    			 alert(data); 
	    		}
	         });
	}
	function modifyContract()
	{
		var url = getContextPath()+'insurance/${id}/contract/save.do?update'; 
		var dataList = 	$("#contract").serializeArray();
		$.ajax({
	           type: "POST",
	           url: url,
	           data: dataList, 
	           success: function(data)
	           {
	              
	               $('#insuranceContractNew').html(data);
	           },
	    		error:function(data)
	    		{
	    			 alert(data); 
	    		}
	     });
	}
	
	function addContact()
	{
		var url = getContextPath()+'insurance/${id}/contact/save.do?add'; 
		var dataList = 	$("#contact").serializeArray();
		$.ajax({
	           type: "POST",
	           url: url,
	           data: dataList, 
	           success: function(data)
	           {
	              
	               $('#insuranceContactNew').html(data);
	           },
	    		error:function(data)
	    		{
	    			 alert(data); 
	    		}
	         });
	}
</script>

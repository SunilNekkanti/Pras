<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
    prefix="springForm"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="context" value="${pageContext.request.contextPath}" />
<script src="${context}/resources/js/validation.js"></script>
 <c:choose>
 	<c:when test="${provider.id != null}"> 
	<script>
		$(document).ready(function(){	 
		removePlaceHolder();
		providerValidation();
		});
	</script>
	</c:when>
	<c:otherwise>
		<script>$(document).ready(function(){providerValidation();});</script>
	</c:otherwise>	 
</c:choose>
<div class="panel-group">
	<div class="panel panel-primary">
		<div class="panel-heading">Provider Profile</div>
		<div class="panel-body" id="tablediv">
			<springForm:form method="POST" id="provider" commandName="provider" action="${context}/provider/${id}/save.do">
				<div class="col-sm-12">
					<div class="form-group required col-sm-4">
						<label class="control-label  col-sm-2" for="name">Name</label>
					    <div class="col-sm-10">
					    	<springForm:hidden path="id" />
					      	<springForm:input path="name" class="form-control" id="name" placeholder="Name" />
					      	<springForm:errors path="name" cssClass="error text-danger" />
					    </div>
					 </div>
					<div class="form-group required col-sm-4">
					    <label class="control-label  col-sm-2" for="code">NPI</label>
						<div class="col-sm-10">
						   	<springForm:input path="code" class="form-control" id="code" placeholder="code" />
						   	<springForm:errors path="code" cssClass="error text-danger" />
						 </div>
					</div>
					
					<div class="form-group required col-sm-4">
						<label class="control-label col-sm-3" for="insurance">Insurance</label>
						<div class="col-sm-9">
							<springForm:select multiple="true" path="insurances" class="form-control"  items="${insuranceList}" itemLabel="name" itemValue="id" />
							<springForm:errors path="insurances" cssClass="error text-danger" />
						</div>
					</div>
				</div>
				<div class="col-sm-offset-9 col-sm-3">
					<c:choose>
						 <c:when test="${provider.id != null && provider.activeInd == 89}"> 
							<button type="submit" class="btn btn-primary" name="update" id="updateButton">Update</button>
							<button type="submit" class="btn btn-primary" name="delete" id="deleteButton">Delete</button>
						 </c:when>
						 <c:when test="${provider.id == null}"> 
							<button type="submit" class="btn btn-primary" id="updateButton" name="add" >Add</button>
							<button type="submit" class="btn btn-primary" id="resetButton" >Reset</button>
						</c:when>
					</c:choose>	
				</div>
				 
			</springForm:form>
		</div>
	</div>
</div>	
<div id="providerContractList"></div>
<div id="providerContractEdit"></div>
<div id="providerContractNew"></div>
<div id="providerContactList"></div>
<div id="providerContactEdit"></div>
<div id="providerContactNew"></div>

<script>
	var source = getContextPath()+'provider/${id}/contractList';
	$.ajax({
		url : source,
	    success: function(data, textStatus, jqXHR)
	    {
	       $('#providerContractList').html(data);
	    },
	    error: function (jqXHR, textStatus, errorThrown)
	    {
	  	  alert("Error provider");
	    }
	});
	
	var source = getContextPath()+'provider/${id}/contactList';
	$.ajax({
		url : source,
	    success: function(data, textStatus, jqXHR)
	    {
	       $('#providerContactList').html(data);
	    },
	    error: function (jqXHR, textStatus, errorThrown)
	    {
	  	  alert("Error");
	    }
	});
		
	function contract(providerId,contractId)
	{
		var source = getContextPath()+'provider/'+providerId+'/contract/'+contractId;
		$.ajax({
			url : source,
		     success: function(data, textStatus, jqXHR)
		    {
		    	$('#providerContractList').css("display","none");
		    	$('#providerContractNew').show();
		        $('#providerContractEdit').html(data);
		    },
		    error: function (jqXHR, textStatus, errorThrown)
		    {
		  	  alert("Error");
		    }
		});
		return false;	
	}
	
	function contact(providerId,contactId)
	{
		var source = getContextPath()+'provider/'+providerId+'/contact/'+contactId;
		$.ajax({
			url : source,
		     success: function(data, textStatus, jqXHR)
		    {
		    	$('#providerContactList').css("display","none");
		    	$('#providerContactNew').show();
		        $('#providerContactEdit').html(data);
		    },
		    error: function (jqXHR, textStatus, errorThrown)
		    {
		  	  alert("Error");
		    }
		});
		return false;	
	}
	
	var source = getContextPath()+'provider/${id}/contractList';
	$.ajax({
		url : source,
	    success: function(data, textStatus, jqXHR)
	    {
	       $('#providerContractList').html(data);
	    },
	    error: function (jqXHR, textStatus, errorThrown)
	    {
	  	  alert("Error");
	    }
	});

	function newContract()
	{
		var source = getContextPath()+'provider/${id}/contract/new';
		$.ajax({
			url : source,
		 	success: function(data, textStatus, jqXHR)
		    {
		 		$('#providerContractList').css("display","none");
		 		$('#providerContractNew').show();
		        $('#providerContractNew').html(data);
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
		var source = getContextPath()+'provider/${id}/contact/new';
		$.ajax({
			url : source,
		 	success: function(data, textStatus, jqXHR)
		    {
		 		$('#providerContactList').css("display","none");
		 		$('#providerContactNew').show();
		        $('#providerContactNew').html(data);
		    },
		    error: function (jqXHR, textStatus, errorThrown)
		    {
		  	  alert("Error");
		    }
		});
	}
	
	function contractList()
	{
		$('#providerContractNew').css("display","none");
 		$('#providerContractEdit').css("display","none");
 		$('#providerContractList').show();
		
	}
	
	function contactList()
	{
		$('#providerContactNew').css("display","none");
 		$('#providerContactEdit').css("display","none");
 		$('#providerContactList').show();
		
	}
	
	function addContract()
	{
		var url = getContextPath()+'provider/${id}/contract/save.do?add'; 
		var dataList = 	$("#contract").serializeArray();
		dataList.push({})
		$.ajax({
	           type: "POST",
	           url: url,
	           data: dataList, 
	           success: function(data)
	           {
	               $('#providerContractNew').html(data);
	           },
	    		error:function(data)
	    		{
	    			 alert(data); 
	    		}
	         });
	}
	function modifyContract()
	{
		var url = getContextPath()+'provider/${id}/contract/save.do?update'; 
		var dataList = 	$("#contract").serializeArray();
		dataList.push({})
		$.ajax({
	           type: "POST",
	           url: url,
	           data: dataList, 
	           success: function(data)
	           {
	    			$('#providerContractNew').html(data);
	           },
	    		error:function(data)
	    		{
	    			 alert('modify error '+data); 
	    		}
	     });
	}
	
	function addContact()
	{
		var url = getContextPath()+'provider/${id}/contact/save.do?add'; 
		var dataList = 	$("#contact").serializeArray();
		$.ajax({
	           type: "POST",
	           url: url,
	           data: dataList, 
	           success: function(data)
	           {
	            	$('#providerContactNew').html(data);
	           },
	    		error:function(data)
	    		{
	    			 alert('add error '+ data); 
	    		}
	         });
	}
</script>



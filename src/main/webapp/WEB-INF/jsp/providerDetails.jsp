<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
    prefix="springForm"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="context" value="${pageContext.request.contextPath}" />
<div id="providerDetails">
<div class="panel-group">
	<div class="panel panel-primary">
		<div class="panel-heading">Provider Profile</div>
		<div class="panel-body" id="tablediv">
			<springForm:form method="POST" id="provider" commandName="provider" action="${context}/provider/${id}/save.do">
				<div class="col-sm-12">
					<div class="form-group required col-sm-4">
						<label class="control-label  col-sm-5" for="name">Name</label>
					    <div class="col-sm-7">
					    	<springForm:hidden path="id" />
					      	<springForm:input path="name" class="form-control" id="name" placeholder="Name" />
					      	<springForm:errors path="name" cssClass="error text-danger" />
					    </div>
					 </div>
					<div class="form-group required col-sm-4">
					    <label class="control-label  col-sm-5" for="code">NPI</label>
						<div class="col-sm-7">
						   	<springForm:input path="code" class="form-control" id="code" placeholder="code" />
						   	<springForm:errors path="code" cssClass="error text-danger" />
						 </div>
					</div>
					
					<div class="form-group required col-sm-4">
						<label class="control-label col-sm-5" for="insurance">Insurance</label>
						<div class="col-sm-7">
							<springForm:select multiple="true" path="insPrvdrs" class="form-control"  items="${insPrvdrList}" itemLabel="ins.name" itemValue="id" />
							<springForm:errors path="insPrvdrs" cssClass="error text-danger" />
						</div>
					</div>
				</div>
				<div class="col-sm-offset-9 col-sm-3">
					<c:choose>
						 <c:when test="${provider.id != null && provider.activeInd == 89}"> 
							<button type="button" class="btn btn-primary" name="update" id="updateButton" onclick="return modifyProviderDetails();">Update</button>
							<button type="button" class="btn btn-primary" name="delete" id="deleteButton" onclick="return deleteProviderDetails();">Delete</button>
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
</div>
<div id="providerContractList"></div>
<div id="providerContactList"></div>


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
	  	  alert("Provider Contract List Error");
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
	  	  alert("Provider Contact List Error");
	    }
	});
	
	function providerDetails()
	{
		var source = getContextPath()+'provider/${id}/details';
		$.ajax({
			url : source,
		    success: function(data, textStatus, jqXHR)
		    {
		       $('#providerDetails').html(data);
		    },
		    error: function (jqXHR, textStatus, errorThrown)
		    {
		  	  alert("Provider Details Error");
		    }
		});
	}
	function modifyProviderDetails()
	{
		var url = getContextPath()+'provider/${id}/save.do?update'; 
		var dataList = 	$("#provider").serialize();
		$.ajax({
	           type: "POST",
	           url: url,
	           data: dataList, 
	           success: function(data)
	           {
	        	  $('#providerDetails').html(data);
	           },
	    		error:function(data)
	    		{
	    			 alert('Provider Modify Error '+ data); 
	    		}
	         });
	}
	function deleteProviderDetails()
	{
		if (confirm("Action cannot be undone.Are you want to delete it?!") == true) 
		{
			var url = getContextPath()+'provider/${id}/save.do?delete'; 
			var dataList = 	$("#provider").serialize();
			$.ajax({
		           type: "POST",
		           url: url,
		           data: dataList, 
		           success: function(data)
		           {
		        	  $('#providerDetails').html(data);
		           },
		    		error:function(data)
		    		{
		    			 alert('Provider Delete Error '+ data); 
		    		}
		         });
		}	
	}
	function contract(providerId,contractId)
	{
		var source = getContextPath()+'provider/'+providerId+'/contract/'+contractId;
		$.ajax({
			url : source,
		     success: function(data, textStatus, jqXHR)
		    {
		    	$('#providerContractList').html(data);
		    },
		    error: function (jqXHR, textStatus, errorThrown)
		    {
		  	  alert("Provider Contract Error");
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
		    	 $('#providerContactList').html(data);
		    },
		    error: function (jqXHR, textStatus, errorThrown)
		    {
		  	  alert("Provider Contact Error");
		    }
		});
		return false;	
	}
	
	

	function newContract()
	{
		var source = getContextPath()+'provider/${id}/contract/new';
		$.ajax({
			url : source,
		 	success: function(data, textStatus, jqXHR)
		    {
		 		$('#providerContractList').html(data);
		        $('#contract').on('submit', function (event) {
		        	
		        });
		    },
		    error: function (jqXHR, textStatus, errorThrown)
		    {
		  	  alert("Provider New Contract Error");
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
		 		$('#providerContactList').html(data);
		    },
		    error: function (jqXHR, textStatus, errorThrown)
		    {
		  	  alert("Provider New Contact Error");
		    }
		});
	}
	
	function contractList()
	{
	  var source = getContextPath()+'provider/${id}/contractList';
 	   $.ajax({
 	    url : source,
 	       success: function(data, textStatus, jqXHR)
 	       {
 	          $('#providerContractList').html(data);
 	       },
 	       error: function (jqXHR, textStatus, errorThrown)
 	       {
 	        alert("Providr Contract List Error");
 	       }
 	   });
 	   $('#providerContactList').show();
	}
	
	function contactList()
	{
		
		   var source = getContextPath()+'provider/${id}/contactList';
		   alert(source);
			$.ajax({
				url : source,
			    success: function(data, textStatus, jqXHR)
			    {
			       $('#providerContactList').html(data);
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			  	  alert("Provider Contact List Error");
			    }
			});
	}

	
	function addContract()
	{
		var url = getContextPath()+'provider/${id}/contract/save.do?add'; 
		if(window.FormData !== undefined)  // for HTML5 browsers
	    {
		var formData = new FormData($('#contract')[0]);
        formData.append('fileUpload', $('input[type=file]')[0].files[0]);
	    $.ajax({
	        url: url,
	        type: 'POST',
	        mimeType:"multipart/form-data",
	        data: formData,
	        async: false,
	        success: function (data) {
	        	$('#providerContractList').html(data);
	        },
	        cache: false,
	        contentType: false,
	        processData: false
	    });
	    }
	    return false;
	}
	
	function modifyContract()
	{
		var url = getContextPath()+'provider/${id}/contract/save.do?update'; 
		var dataList = 	$("#contract").serialize();
		$.ajax({
	           type: "POST",
	           url: url,
	           data: dataList, 
	           success: function(data)
	           {
	    			$('#providerContractList').html(data);
	           },
	    		error:function(data)
	    		{
	    			 alert('Provider Modify Error '+data); 
	    		}
	     });
	}
	function deleteContract()
	{
		if (confirm("Action cannot be undone.Are you want to delete it?!") == true) 
		{
			var url = getContextPath()+'provider/${id}/contract/save.do?delte'; 
			var dataList = 	$("#contract").serialize();
			$.ajax({
		           type: "POST",
		           url: url,
		           data: dataList, 
		           success: function(data)
		           {
		            	$('#providerContractList').html(data);
		           },
		    		error:function(data)
		    		{
		    			 alert('Provider Delete Contract Error '+ data); 
		    		}
		         });
		}	
	}
	
	function addContact()
	{
		var url = getContextPath()+'provider/${id}/contact/save.do?add'; 
		var dataList = 	$("#contact").serialize();
		$.ajax({
	           type: "POST",
	           url: url,
	           data: dataList, 
	           success: function(data)
	           {
	            	$('#providerContactList').html(data);
	           },
	    		error:function(data)
	    		{
	    			 alert('Provider Add Contact Error '+ data); 
	    		}
	         });
	}
	
	function modifyContact()
	{
		var url = getContextPath()+'provider/${id}/contact/save.do?update'; 
		var dataList = 	$("#contact").serialize();
		$.ajax({
	           type: "POST",
	           url: url,
	           data: dataList, 
	           success: function(data)
	           {
	            	$('#providerContactList').html(data);
	           },
	    		error:function(data)
	    		{
	    			 alert('Provider Modify Contact Error '+ data); 
	    		}
	         });
	}
	function deleteContact()
	{
		if (confirm("Action cannot be undone.Are you want to delete it?!") == true) 
		{
			var url = getContextPath()+'provider/${id}/contact/save.do?delete'; 
			var dataList = 	$("#contact").serialize();
			$.ajax({
		           type: "POST",
		           url: url,
		           data: dataList, 
		           success: function(data)
		           {
		            	$('#providerContactList').html(data);
		           },
		    		error:function(data)
		    		{
		    			 alert('Provider Delete Contact Error '+ data); 
		    		}
		         });
		}
	}	
	
	$(document).on("click", "#addButton",   function() { addContract(); } );

</script>



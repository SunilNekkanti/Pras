<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
    prefix="springForm"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="context" value="${pageContext.request.contextPath}" />
<div id="providerDetails">
<div class="panel-group">
	<div class="panel panel-success">
		<div class="panel-heading">Provider Profile</div>
		<div class="panel-body" id="tablediv">
			<springForm:form method="POST"  commandName="provider" action="${context}/provider/${id}/save.do">
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
				</div>
				<div class="col-sm-offset-9 col-sm-3">
					<c:choose>
						 <c:when test="${provider.id != null && provider.activeInd == 89}"> 
							<button type="button" class="btn btn-success btn-sm" name="update" id="updateButton" onclick="return modifyProviderDetails();">Update</button>
							<button type="button" class="btn btn-success btn-sm" name="delete" id="deleteButton" onclick="return deleteProviderDetails();">Delete</button>
						 </c:when>
						 <c:when test="${provider.id == null}"> 
							<button type="submit" class="btn btn-success btn-sm" id="updateButton" name="add" >Add</button>
							<button type="submit" class="btn btn-success btn-sm" id="resetButton" >Reset</button>
						</c:when>
					</c:choose>	
				</div>
				 
			</springForm:form>
		</div>
	</div>
</div>	
</div>
<div id="providerContractList"></div>
<div id="providerInsuranceContractList"></div>
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
	
	var source = getContextPath()+'provider/${id}/prvdrInsContractList';
	$.ajax({
		url : source,
	    success: function(data, textStatus, jqXHR)
	    {
	       $('#providerInsuranceContractList').html(data);
	    },
	    error: function (jqXHR, textStatus, errorThrown)
	    {
	  	  alert("Provider Insurance Contract List Error");
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
		if (confirm("Action cannot be undone.Click 'Ok' to delete.") == true) 
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
	function contract(providerId,contractId,pmpmRequired)
	{
		if(pmpmRequired){
			prvdrInscontract(providerId,contractId);
		}else{
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
		
	}
	
	function prvdrInscontract(providerId,contractId)
	{
		var source = getContextPath()+'provider/'+providerId+'/prvdrInsContract/'+contractId;
		$.ajax({
			url : source,
		     success: function(data, textStatus, jqXHR)
		    {
		    	$('#providerInsuranceContractList').html(data);
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
	
	

	function newContract(pmpmRequired)
	{
		if(pmpmRequired){
			newPrvdrInsContract(pmpmRequired);
		}else{
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
		
	}
	
	function newPrvdrInsContract(pmpmRequired)
	{
		var source = getContextPath()+'provider/${id}/prvdrInsContract/new';
		$.ajax({
			url : source,
		 	success: function(data, textStatus, jqXHR)
		    {
		 		$('#providerInsuranceContractList').html(data);
		        $('#contract'+pmpmRequired).on('submit', function (event) {
		        	
		        });
		    },
		    error: function (jqXHR, textStatus, errorThrown)
		    {
		  	  alert("Provider Insurance New Contract Error");
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
	
	function contractList(pmpmRequired)
	{
		if(pmpmRequired){
			prvdrInscontractList();
		}else{
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
	  
	}
	
	function prvdrInscontractList()
	{
	  var source = getContextPath()+'provider/${id}/prvdrInsContractList';
 	   $.ajax({
 	    url : source,
 	       success: function(data, textStatus, jqXHR)
 	       {
 	          $('#providerInsuranceContractList').html(data);
 	       },
 	       error: function (jqXHR, textStatus, errorThrown)
 	       {
 	        alert("Provider Insurance Contract List Error");
 	       }
 	   });
 	   $('#providerContactList').show();
	}
	
	function contactList()
	{
		
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
	}

	
	function addContract(pmpmRequired)
	{
		if(pmpmRequired){
			addprvdrInsContract(pmpmRequired);
		}else{
			var url = getContextPath()+'provider/${id}/contract/save.do?add'; 
			if(window.FormData !== undefined)  // for HTML5 browsers
		    {
			var formData = new FormData($('#contract'+pmpmRequired)[0]);
			alert(formData);
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
		}
		
	    return false;
	}
	
	function addprvdrInsContract(pmpmRequired)
	{
		var url = getContextPath()+'provider/${id}/prvdrInsContract/save.do?add'; 
		if(window.FormData !== undefined)  // for HTML5 browsers
	    {
		var formData = new FormData($('#contract'+pmpmRequired)[0]);
        formData.append('fileUpload', $('input[type=file]')[0].files[0]);
	    $.ajax({
	        url: url,
	        type: 'POST',
	        mimeType:"multipart/form-data",
	        data: formData,
	        async: false,
	        success: function (data) {
	        	$('#providerInsuranceContractList').html(data);
	        },
	        cache: false,
	        contentType: false,
	        processData: false
	    });
	    }
	    return false;
	}
	
	function modifyContract(pmpmRequired)
	{
		if(pmpmRequired){
			modifyPrvdrInsContract(pmpmRequired);
		}else{
			var url = getContextPath()+'provider/${id}/contract/save.do?update'; 
			var dataList = 	$("#contract"+pmpmRequired).serialize();
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
		return false;
	}
	
	function modifyPrvdrInsContract(pmpmRequired)
	{
		var url = getContextPath()+'provider/${id}/prvdrInsContract/save.do?update'; 
		var dataList = 	$("#contract"+pmpmRequired).serialize();
		$.ajax({
	           type: "POST",
	           url: url,
	           data: dataList, 
	           success: function(data)
	           {
	    			$('#providerInsuranceContractList').html(data);
	           },
	    		error:function(data)
	    		{
	    			 alert('Provider Insurance Modify Error '+data); 
	    		}
	     });
	}
	
	function deleteContract(pmpmRequired)
	{
		if(pmpmRequired){
			deletePrvdrInsContract(pmpmRequired);
		}else{
			if (confirm("Action cannot be undone.Click 'Ok' to delete.") == true) 
			{
				var url = getContextPath()+'provider/${id}/contract/save.do?delete'; 
				var dataList = 	$("#contract"+pmpmRequired).serialize();
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
			
	}
	
	function deletePrvdrInsContract(pmpmRequired)
	{
		if (confirm("Action cannot be undone.Click 'Ok' to delete.") == true) 
		{
			var url = getContextPath()+'provider/${id}/prvdrInsContract/save.do?delete'; 
			var dataList = 	$("#contract"+pmpmRequired).serialize();
			$.ajax({
		           type: "POST",
		           url: url,
		           data: dataList, 
		           success: function(data)
		           {
		            	$('#providerInsuranceContractList').html(data);
		           },
		    		error:function(data)
		    		{
		    			 alert('Provider Insurance Delete Contract Error '+ data); 
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
		if (confirm("Action cannot be undone.Click 'Ok' to delete.") == true) 
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
	
	//$(document).on("click", "#addButton",   function() { addContract("${pmpmRequired}"); } );

</script>



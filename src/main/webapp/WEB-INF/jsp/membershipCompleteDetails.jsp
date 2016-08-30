<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="springForm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row">
	<div class="col-sm-12" id="mbrDetails">
		<jsp:include page="/WEB-INF/jsp/membershipEdit.jsp">
			<jsp:param name="membershipEdit" value="${membershipEdit}" />
		</jsp:include>
	</div>
</div>
<div id="membershipContactList"></div>


<div class="row">
	<div class="col-sm-12" id="mbrPrdvrDetails">
		<jsp:include page="/WEB-INF/jsp/membershipProviderList.jsp">
			<jsp:param name="membershipProviderList"
				value="${membershipProviderList}" />
		</jsp:include>
	</div>
</div>

<div class="row">
	<div class="col-sm-12" id="mbrInsList"></div>
</div>

<div class="row">
	<div class="col-sm-12">
		<jsp:include page="/WEB-INF/jsp/membershipHedisMeasure.jsp">
			<jsp:param name="mbrHedisMeasureList" value="${mbrHedisMeasureList}" />
		</jsp:include>
	</div>
</div>
<div class="row">
	<div class="col-sm-12" id="membershipProblemList"></div>
</div>

<div class="row">
	<div class="col-sm-12">
		<jsp:include page="/WEB-INF/jsp/membershipHospitalizationRecord.jsp">
			<jsp:param name="mbrHospitalizationList"
				value="${mbrHospitalizationList}" />
		</jsp:include>
	</div>
</div>


<script>		

	function modifyMbrDetails()
	{
		
		var url = getContextPath()+'membership/${id}/save.do?update';
		var dataList = 	$("#membership").serialize();
		$.ajax({
	           type: "POST",
	           url: url,
	           data: dataList, 
	           success: function(data)
	           {
	               $('#mbrDetails').html(data);
	           },
	    		error:function(data)
	    		{
	    			 alert("Membership Details Modify Error");
	    		}
	         });
	}
	function deleteMbrDetails()
	{
		if (confirm("Action cannot be undone.Click 'Ok' to delete.") == true) 
		{
			var url = getContextPath()+'membership/${id}/save.do?delete';
			var dataList = 	$("#membership").serialize();
			$.ajax({
		           type: "POST",
		           url: url,
		           data: dataList, 
		           success: function(data)
		           {
		        	   $('#mbrDetails').html(data);
		           },
		    		error:function(data)
		    		{
		    			 alert("Membership Details Delete Error");
		    		}
		         });
		}		
	}
	var source = getContextPath()+'membership/${id}/contactList';
	$.ajax({
		url : source,
	    success: function(data, textStatus, jqXHR)
	    {
	       $('#membershipContactList').html(data);
	    },
	    error: function (jqXHR, textStatus, errorThrown)
	    {
	  	  alert("Error");
	    }
	});
	
	var source = getContextPath()+'membership/${id}/detailsList';
	$.ajax({
		url : source,
	    success: function(data, textStatus, jqXHR)
	    {
	       $('#mbrInsList').html(data);
	    },
	    error: function (jqXHR, textStatus, errorThrown)
	    {
	  	  alert("Error mbrInsList");
	    }
	});
		
	function contact(memberId,contactId)
	{
		var source = getContextPath()+'membership/'+memberId+'/contact/'+contactId;
		$.ajax({
			url : source,
		     success: function(data, textStatus, jqXHR)
		    {
		    	$('#membershipContactList').html(data);
		    },
		    error: function (jqXHR, textStatus, errorThrown)
		    {
		  	  alert("Error");
		    }
		});
		return false;	
	}
		
	function newContact()
	{
		var source = getContextPath()+'membership/${id}/contact/new';
		$.ajax({
			url : source,
		 	success: function(data, textStatus, jqXHR)
		    {
		 		$('#membershipContactList').html(data);
		    },
		    error: function (jqXHR, textStatus, errorThrown)
		    {
		  	  alert("Error");
		    }
		});
	}
	
	
	
	function contactList()
	{
		var source = getContextPath()+'membership/${id}/contactList';
		$.ajax({
			url : source,
		    success: function(data, textStatus, jqXHR)
		    {
		       $('#membershipContactList').html(data);
		    },
		    error: function (jqXHR, textStatus, errorThrown)
		    {
		  	  alert("Error");
		    }
		});
	}
	
	function addContact()
	{
		var url = getContextPath()+'membership/${id}/contact/save.do?add'; 
		var dataList = 	$("#contact").serializeArray();
		$.ajax({
	           type: "POST",
	           url: url,
	           data: dataList, 
	           success: function(data)
	           {
	               $('#membershipContactList').html(data);
	           },
	    		error:function(data)
	    		{
	    			 alert(data); 
	    		}
	         });
	}
	
	function modifyContact()
	{
		var url = getContextPath()+'membership/${id}/contact/save.do?update'; 
		var dataList = 	$("#contact").serializeArray();
		$.ajax({
	           type: "POST",
	           url: url,
	           data: dataList, 
	           success: function(data)
	           {
	               $('#membershipContactList').html(data);
	           },
	    		error:function(data)
	    		{
	    			 alert(data); 
	    		}
	     });
	}
	function deleteContact()
	{
		if (confirm("Action cannot be undone.Click 'Ok' to delete.") == true) 
		{
			var url = getContextPath()+'membership/${id}/contact/save.do?delete'; 
			var dataList = 	$("#contact").serializeArray();
			$.ajax({
		           type: "POST",
		           url: url,
		           data: dataList, 
		           success: function(data)
		           {
		               $('#membershipContactList').html(data);
		           },
		    		error:function(data)
		    		{
		    			 alert("Member Contact Delete Error"); 
		    		}
		     });
	    } 
	}
	
	function prvdr(memberId,prvdrId)
	{
			var url = getContextPath()+'/membership/'+prvdrId+'/providerDetails'; 
			$.ajax({
		          url: url,
		          success: function(data, textStatus, jqXHR)
		           {
		        	   $('#mbrPrdvrDetails').html(data);
		           },
		    		error:function(data)
		    		{
		    			 alert(data); 
		    		}
		     });
	}
	
	function mbrNewIns()
	{
		var source = getContextPath()+'membership/${id}/details/new';
		$.ajax({
			url : source,
		    success: function(data, textStatus, jqXHR)
		    {
		       $('#mbrInsList').html(data);
		    },
		    error: function (jqXHR, textStatus, errorThrown)
		    {
		  	  alert("Member New Isurance Error");
		    }
		});
	}
	
	function mbrDetails(mbrId,mbrDetailsId)
	{
		
		var source = getContextPath()+'membership/${id}/details/'+mbrDetailsId+'/display';
		$.ajax({
			url : source,
		    success: function(data, textStatus, jqXHR)
		    {
		       $('#mbrInsList').html(data);
		    },
		    error: function (jqXHR, textStatus, errorThrown)
		    {
		  	  alert("Error mbrInsDetails");
		    }
		});
	}
	
	function mbrInsList()
	{
		
		var source = getContextPath()+'membership/${id}/detailsList';
		$.ajax({
			url : source,
		    success: function(data, textStatus, jqXHR)
		    {
		       $('#mbrInsList').html(data);
		    },
		    error: function (jqXHR, textStatus, errorThrown)
		    {
		  	  alert("Error mbrInsList");
		    }
		});
	}
	
	function addMbrInsDetails()
	{
		var url = getContextPath()+'membership/${id}/details/save.do?add'; 
		var dataList = 	$("#membershipInsurance").serialize();
		$.ajax({
	           type: "POST",
	           url: url,
	           data: dataList, 
	           success: function(data)
	           {
	            	$('#mbrInsList').html(data);
	           },
	    		error:function(data)
	    		{
	    			 alert('Add Member New Insurance Error '+ data); 
	    		}
	         });
	}
	
	function modifyMbrInsDetails(mbrInsId)
	{
		var url = getContextPath()+'membership/${id}/details/'+mbrInsId+'/save.do?update'; 
		var dataList = 	$("#membershipInsurance").serialize();
		$.ajax({
	           type: "POST",
	           url: url,
	           data: dataList, 
	           success: function(data)
	           {
	            	$('#mbrInsList').html(data);
	           },
	    		error:function(data)
	    		{
	    			 alert('Modify Membership Insurance Error '+ data); 
	    		}
	         });
	}
	function deleteMbrInsDetails(mbrInsId)
	{
		if (confirm("Action cannot be undone.Click 'Ok' to delete.") == true) 
		{
			var url = getContextPath()+'membership/${id}/details/'+mbrInsId+'/save.do?delete'; 
			var dataList = 	$("#membershipInsurance").serialize();
			$.ajax({
		           type: "POST",
		           url: url,
		           data: dataList, 
		           success: function(data)
		           {
		            	$('#mbrInsList').html(data);
		           },
		    		error:function(data)
		    		{
		    			 alert('Delete Member Insurance Error '+ data); 
		    		}
		         });
		}	
	}
	function membershipDetails()
	{
		
		var url = getContextPath()+'/membership/${id}'; 
		$.ajax({
	          url: url,
	          success: function(data, textStatus, jqXHR)
	           {
	        	   $('#mbrDetails').html(data);
	           },
	    		error:function(data)
	    		{
	    			 alert(data); 
	    		}
	     });
	}
	membershipProblemList();
	
	function membershipProblemList(message){
		var source = getContextPath()+'membershipProblemList/${id}';
		$.ajax({
			url : source,
		    success: function(data, textStatus, jqXHR)
		    {
		       $('#membershipProblemList').html(data);
		       $(".membershipProblemList").show();
				$(".membershipProblemList .clrRed").text(message);
				$('#membershipProblemDetails').html('');
		    },
		    error: function (jqXHR, textStatus, errorThrown)
		    {
		  	  alert("Error MembershipProblem List");
		    }
		});
		
		
	}	
	
	function newMembershipProblem() {
			var source = getContextPath() + 'membershipProblem/${id}/new';
			$.ajax({
				url : source,
				success : function(data, textStatus, jqXHR) {
					$(".membershipProblemList").hide();
					$('#membershipProblemDetails').html(data);
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert("Error in membership Problem");
				}
			});
		
		}
	
	
	function addMembershipProblem() {
		var url = getContextPath() + 'membershipProblem/${id}/save.do?add';
		$("#mbr").val('${id}')
		var dataList = $("#membershipProblem").serialize();
		$.ajax({
			type : "POST",
			url : url,
			data : dataList,
			success : function(data) {
				$('#membershipProblemDetails').html(data);
				if($('#membershipProblemDetails .error').length < 1)
				{
					membershipProblemList(" Problem added ");
				}	
			},
			error : function(data) {
				alert('Error in membership problelm' + data);
			}
		});
		return false;
	}
	
	function membershipProblemDetails(mbrPbmId) {
		var source = getContextPath() + 'membershipProblem/'+mbrPbmId;
		$.ajax({
			url : source,
			success : function(data, textStatus, jqXHR) {
				$(".membershipProblemList").hide();
				$('#membershipProblemDetails').html(data);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert("Error in membership Problem");
			}
		});
	}
	
	function modifyMembershipProblem() {
		var url = getContextPath() + 'membershipProblem/${id}/save.do?update';
		$("#mbr").val('${id}')
		var dataList = $("#membershipProblem").serialize();
		$.ajax({
			type : "POST",
			url : url,
			data : dataList,
			success : function(data) {
				$('#membershipProblemDetails').html(data);
				if($('#membershipProblemDetails .error').length < 1)
				{
					membershipProblemList(" Problem updated ");
				}	
			},
			error : function(data) {
				alert('Error in membership problelm' + data);
			}
		});
		return false;
	}
	
	function deleteMembershipProblem() {
		var url = getContextPath() + 'membershipProblem/${id}/save.do?delete';
		$("#mbr").val('${id}')
		var dataList = $("#membershipProblem").serialize();
		$.ajax({
			type : "POST",
			url : url,
			data : dataList,
			success : function(data) {
				$('#membershipProblemDetails').html(data);
				if($('#membershipProblemDetails .error').length < 1)
				{
					membershipProblemList(" Problem deleted ");
				}	
			},
			error : function(data) {
				alert('Error in membership problelm' + data);
			}
		});
		return false;
	}
</script>





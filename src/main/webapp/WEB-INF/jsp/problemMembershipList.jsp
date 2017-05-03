<%@  page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@  taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="context"
	value="${pageContext.request.contextPath}/${userpath}" />
<c:set var="contextHome" value="${pageContext.request.contextPath}" />
<link rel="stylesheet"
	href="${contextHome}/resources/css/bootstrap-multiselect.css"
	type="text/css">
<script type="text/javascript"
	src="${contextHome}/resources/js/bootstrap-multiselect.js"></script>
<script>
$(document).ready(function() {
		
		$("#problemGenerate").click(function(event)
		{
			if(!$("#problemRule").val()){
				$(".clrRed").text("Select problem measure");
				return false;
			}
			callProblemGenerate();
		});
	 		
    	 var $selectIns = $('#extFilterIns');
    	  $.getJSON("${context}/"+'/insurance/list?pageNo=0&pageSize=200', function(data){
			    
			     //clear the current content of the select
			     var s = $('<select id=\"insu\"  class=\"btn btn-default\" style=\"width:150px;\">');
			     //iterate over the data and append a select option
			     $.each(data.data.list, function(key, val){
			    	 s.append('<option value="'+val.id+'">' + val.name +'</option>');
			     });
			     s.append('</select>');
			     $selectIns.html(s);
			    
		 }).success(function() { 
			 problemRuleDropdown(false);
			 providerDropdown();
			 
		 });
		 
		 var providerDropdown = function(){
			 $("#problemGenerate").hide();
			 $("#problemGenerate").hide();
			 var insSelectValue = Cookies.get('insu');
			 if(insSelectValue != undefined)
					insSelectValue = insSelectValue;
				else{
					insSelectValue= $("#insu option:selected").val();
					Cookies.set('insu', insSelectValue, {path: cookiePath});
				}
				
 			 var $selectPrvdr = $('#extFilterPrvdr');
 	    	  $.getJSON("${context}/"+'/insurance/providerlist?insId='+insSelectValue, function(data){
 				    
 				     //clear the current content of the select
 				     var s = $('<select id=\"prvdr\"  class=\"btn btn-default\" style=\"width:150px;\">');
 				     //iterate over the data and append a select option
 				     $.each(data.data.list, function(key, val){
 				    	 s.append('<option value="'+val.id+'">' + val.name +'</option>');
 				     });
 				     s.append('<option value="9999">All</option>');
 				     s.append('</select>');
 				     $selectPrvdr.html(s);
 			 }).success(function() { 
 				var prvdrSelectValue = Cookies.get('prvdr');
				 if(prvdrSelectValue != undefined) 
					 prvdrSelectValue = prvdrSelectValue;
				 else{
					prvdrSelectValue= $("#prvdr option:selected").val();
					Cookies.set('prvdr', prvdrSelectValue, {path:cookiePath});
				 }	
				$('select[id="prvdr"]').val(prvdrSelectValue);
 				 
 			 });
  		    
    	  }
    	  
    	  var columns ;
    	  var problemRuleDropdown = function(problemDropDownSet){
    	
    		if(problemDropDownSet){
    			$('#problemRule').find('option').remove();
    			if ( $.fn.DataTable.isDataTable('#membershipTable') ) {
					$('#membershipTable').DataTable().destroy();
	   			}
    			$('#membershipTable tbody').empty();
    		}
    		  	
				
			var $selectProblemRule = $('#extFilterProblemRule');
			 var insSelectValue = Cookies.get('insu');
			 if(insSelectValue != undefined)
					insSelectValue = insSelectValue;
			else{
					insSelectValue= $("#insu option:selected").val();
					Cookies.set('insu', insSelectValue, {path: cookiePath});
			}
		     
			$('select[id="insu"]').val(insSelectValue);
			
		 	 var $selectPrvdr = $('#extFilterPrvdr');
		 	 var restParams = new Array();
		 	 restParams.push({"name" : "pageSize", "value" : 12});
		 	 restParams.push({"name" : "pageNo", "value" : 0 });
		 	 restParams.push({"name" : "insId" , "value" :  insSelectValue });
		 	 restParams.push({"name" : "effYear" , "value" : 2016  });
		 	 $.ajax({
		 		  method: "GET",
		 		  url: "${context}/"+"/problem/list",
		 		  data: restParams
		 	})
		 	 .done(function( data ) {
		 		 var problemRuleCacheList = getDropDownCache('problemRule');
		 		
		 		 var s = $('<select id=\"problemRule\"  class=\"btn btn-default\" multiple style=\"width:150px;\">');
				 //iterate over the data and append a select option
				 $.each(data.data.list, function(key, val){
					 var problemRuleSelected = "";
			    	 if(jQuery.inArray(val.id, problemRuleCacheList) != -1){
			    		 	problemRuleSelected ="selected";
						}
			    	 
				   	 s.append('<option value="'+val.id+'" '+problemRuleSelected+' >' + val.description +'</option>');
				 });
				 s.append('</select>');
				 $selectProblemRule.html(s);	
				 $("#problemGenerate").show();
				 $('#problemRule').multiselect({numberDisplayed: 0, 
					 buttonWidth: '150px',
					 includeSelectAllOption: true,
					 templates: {
						 ul: '<ul class="multiselect-container dropdown-menu problemRule"></ul>'
					 },
					 });
		 	});
		    	 
    	  }
    	  
    	  var callProblemGenerate = function(){

				columns = new Array();
				columns.push({ "mDataProp": "lastName","bSearchable" : true, "bSortable" : true,"sClass": "center","bVisible" : false, "sWidth" : "1%"});
	     		columns.push({ "mDataProp": "mbrProviderList.0.prvdr.name","bSearchable" : true, "bSortable" : true,"sClass": "center widthM","sWidth" : "15%"});
	     		columns.push({ "mDataProp": "lastName","bSearchable" : true, "bSortable": true,"sClass": "center widthS","sWidth" : "8%"  });
	     		columns.push({ "mDataProp": "firstName","bSearchable" : true, "bSortable": true,"sClass": "center widthS","sWidth" : "7%"  });
	     		columns.push({ "mDataProp": "dob","bSearchable" : true, "bSortable": true,"sClass": "center","sWidth" : "5%",
	     			"render": function (data) {
	   		   		    if(data == null) return null;
   		        		var date = new Date(data);
   	        			var month = date.getMonth() + 1;
   	       				 return (month > 9 ? month : "0" + month) + "/" + date.getDate() + "/" + date.getFullYear();
   		   	 		 }	
	     		});
	     		columns.push({ "mDataProp": "id","bSearchable" : true, "bSortable": true,"sClass": "center","sWidth" : "5%",
	     			"render": function (data, type, full, meta) {
	     				 var bDate = new Date(full.dob);
        		   		 var today = new Date();
        		   		 var calculateYear = today.getFullYear();
        		   	     var calculateMonth = today.getMonth();
        		   	     var calculateDay = today.getDate();
        		   	     var birthYear = bDate.getFullYear();
        		   	     var birthMonth = bDate.getMonth();
        		   	     var birthDay = bDate.getDate();
						 var age = calculateYear - birthYear;
        		   	     var ageMonth = calculateMonth - birthMonth;
        		   	     var ageDay = calculateDay - birthDay;

        		   	    if (ageMonth < 0 || (ageMonth == 0 && ageDay < 0)) {
        		   	        age = parseInt(age) - 1;
        		   	    }
        		   	    return age;
   		   	 		 }	
	     		});
	     		columns.push({ "mDataProp": "genderId.code","bSearchable" : true, "bSortable": true,"sClass": "center","sWidth" : "5%" });
	     		columns.push({ "mDataProp": "mbrProblemList.0.startDate","bSearchable" : true, "bSortable": false,"sClass": "center","sWidth" : "5%", "sDefaultContent": "",
	     			 "render": function (data) {
		   		   		    if(data == null) return null;
	   		        		var date = new Date(data);
	   	        			var month = date.getMonth() + 1;
	   	       				 return (month > 9 ? month : "0" + month) + "/" + date.getDate() + "/" + date.getFullYear();
	   		   	 		 }	
	     		});
	     		columns.push({ "mDataProp": "mbrProblemList.0.resolvedDate","bSearchable" : true, "bSortable": false,"sClass": "center","sWidth" : "5%", "sDefaultContent": "",
	     			 "render": function (data) {
		   		   		    if(data == null) return null;
	   		        		var date = new Date(data);
	   	        			var month = date.getMonth() + 1;
	   	       				 return (month > 9 ? month : "0" + month) + "/" + date.getDate() + "/" + date.getFullYear();
	   		   	 		 }	
	     		});
	     		
	     		var myTable = $("#membershipTable");
	     		var thead = myTable.find("thead");  
	     		$("#membershipTable th").each(function() {
					if($(this).attr("role") == "row"){
					}else{
						$(this).remove();
					}
							
       			}); 
	     		
	     		var problemRuleList = document.getElementById('problemRule').options;
	     		
	     		$.each( problemRuleList, function(m, value ){
	     				$('#membershipTable').find('tr').each(function(){
	     					if($("#problemRule option:selected").text().indexOf(value.text) >= 0)
	     					{	
	     						$(this).find('th').eq(-1).after('<th> <center>'+value.text+'</center></th>'); 
	     					}
	     				});
	     		});
	     		
	     		$.each( problemRuleList, function( i, value ){
		     			if($("#problemRule option:selected").text().indexOf(value.text) >= 0)
	     				{
		     				columns.push({ "mDataProp": "mbrProblemList[ ].pbm.description","bSearchable" : false, "bSortable" : false,"sClass": "center","sWidth" : "5%", "sDefaultContent": "",
		      							    "render": function (data, type, full, meta) {
		      							    	full.mbrProblemList.forEach(function( item ) {
		      							    		if(item.activeInd == 'N' ){
		      							    			data = data.replace(item.problemMeasureRule.description,'');
		      							    		}
		      							    	});
				      									   var returnType ='';
				      										if(data.indexOf(value.text) >= 0)
				      											return 'X';
				      										else
			      											return '';
				      								  
		      								}
		      						  });	
	     				}	
	      		});
	     		callDatableWithChangedDropDown();
    	  }
    	  
    	  function callDatableWithChangedDropDown(){
    		   var insSelectValue= $("#insu option:selected").val();
    		   var prvdrSelectValue= $("#prvdr option:selected").val();
    		   var problemRuleSelectValue= $("#problemRule option:selected").val();
    		   Cookies.set('prvdr', prvdrSelectValue, {path:cookiePath});
    		   Cookies.set('insu', insSelectValue, {path: cookiePath});
    		  
    		   var ruleArray = new Array;
    		    $("#problemRule option:selected").each  ( function() {
    		    	ruleArray.push ( $(this).val() );
    		    });

    		    
    		   if ( $.fn.DataTable.isDataTable('#membershipTable') ) {
  						$('#membershipTable').DataTable().destroy();
			   }
				$('#membershipTable tbody').empty();
    		   GetMembershipByInsPrvdrProblemRule(insSelectValue,prvdrSelectValue,problemRuleSelectValue, ruleArray, columns);
    	}  
    	     
    	$(document.body).on('change',"#insu",function (e) {
    		 Cookies.remove('prvdr',{path: cookiePath });
  		     Cookies.set('insu', $("#insu option:selected").val(), {path: cookiePath});
    		problemRuleDropdown(true);
    		providerDropdown();
    		
  		});
    	
    	$(document.body).on('change',"#prvdr",function (e) {
    		Cookies.set('prvdr', $("#prvdr option:selected").val(), {path:cookiePath});
    		if ( $.fn.DataTable.isDataTable('#membershipTable') ) {
					$('#membershipTable').DataTable().destroy();
	   		}
    		$('#membershipTable tbody').empty();
  		});
  	
  	  $(document.body).on('change',"#problemRule",function (e) {
  		if ( $.fn.DataTable.isDataTable('#membershipTable') ) {
			$('#membershipTable').DataTable().destroy();
			}
  			$('#membershipTable tbody').empty();
  			dropDownCache('problemRule'); 
  			
   		});
  	  
  	  var datatable2RestMembership = function(sSource, aoData, fnCallback) {
     		
     		//extract name/value pairs into a simpler map for use later
		  var paramMap = {};
		  for ( var i = 0; i < aoData.length; i++) {
		      paramMap[aoData[i].name] = aoData[i].value;
		  }
		 
		   //page calculations
		   var pageSize = paramMap.iDisplayLength;
		   var start = paramMap.iDisplayStart;
		   var pageNum = (start == 0) ? 1 : (start / pageSize) + 1; // pageNum is 1 based
		 
		   // extract sort information
		   var sortCol = paramMap.iSortCol_0;
		   var sortDir = paramMap.sSortDir_0;
		   var sortName = paramMap['mDataProp_' + sortCol];
		 
		   var sSearchIns = paramMap.sSearchIns;
		   var sSearchPrvdr = paramMap.sSearchPrvdr;
		   var sSearchProblemRule = paramMap.sSearchProblemRule;
		   var ruleIds = paramMap.ruleIds;
		   
		   if($( window ).width() > 900){
				 var width;
				 width = ($("#membershipTable th").length) * 120;
				 if(width > 1200){
					 width = width + "px";
					 $('#membershipClaimTable').width(width);
				 }	
				 
			} 
		   
		   
		   
		   dropDownCache('problemRule');
		   //create new json structure for parameters for REST request
		   var restParams = new Array();
		   restParams.push({"name" : "pageSize", "value" : pageSize});
		   restParams.push({"name" : "pageNo", "value" : pageNum });
		   restParams.push({"name" : "sort", "value" : sortName });
		   restParams.push({"name" : "sortdir", "value" : sortDir });
		   restParams.push({"name" : "sSearch" , "value" : paramMap.sSearch  });
		   restParams.push({"name" : "sSearchIns" , "value" : sSearchIns  });
		   restParams.push({"name" : "sSearchPrvdr" , "value" : sSearchPrvdr  });
		   restParams.push({"name" : "sSearchProblemRule" , "value" : sSearchProblemRule  });
		   restParams.push({"name" : "ruleIds" , "value" : ruleIds  });
		   
		   Cookies.set('problemRule', ruleIds, {path:cookiePath});
		 $.ajax( {
              dataType: 'json',
              contentType: "application/json;charset=UTF-8",
              type: 'GET',
              url: sSource,
              data: restParams,
              success: function(res) {
                  res.iTotalRecords = res.data.totalCount;
                  res.iTotalDisplayRecords = res.data.totalCount;
             		fnCallback(res);
             		
            },
              error : function (e) {
              }
          } );
     	}
     	
     	
     	  GetMembershipByInsPrvdrProblemRule = function (insId, prvdrId, problemRuleId,ruleArray, aoColumns) {
      		
  	        var oTable = $('#membershipTable').removeAttr( "width" ).dataTable({  
  	        	"sDom": 'Bfrtip',
	        	 "buttons": [
	        	             {
	        	                 extend: 'excelHtml5',
	        	                 title: 'Problem Measure RuleTable Export'
	        	             }
	        	             
		                   ],
  	         "bDestroy" : true,	
     	     "sAjaxSource" : "${context}/"+'/reports/problemMembership/list',
     	     "sAjaxDataProp" : 'data.list',
              "aoColumns":  aoColumns,      
     	     "bLengthChange": false,
     	     "iDisplayLength": 12,
     	     "sPaginationType": "full_numbers",
     	     "bProcessing": true,
     	     "bServerSide" : true,
     	     "fnServerParams": function ( aoData ) {
                aoData.push(
                    {"name": "sSearchIns", "value": insId},
                    {"name": "sSearchPrvdr", "value": prvdrId },
                    {"name": "sSearchProblemRule", "value": problemRuleId },
                    {"name": "ruleIds", "value": ruleArray }
                );
             },        
     	     "fnServerData" : datatable2RestMembership
     	});
     }
     	$('select').css({'width': 150});
     	
 } );
</script>

<div class="panel-group">
	<div class="panel panel-success">
		<div class="panel-heading">
			Problem Report <span class="clrRed"> </span>
		</div>
		<div class="panel-body">
		
			<div class="col-sm-12">
					<div class="col-sm-3">
						<label class="control-label col-sm-4">Insurance</label>
						<div class=" col-sm-8" id="extFilterIns"></div>
					</div>
					<div class="col-sm-3">
						<label class="control-label col-sm-3">Provider</label>
						<div class="col-sm-9" id="extFilterPrvdr"></div>
					</div>
					<div class="col-sm-3">
						<label class="control-label col-sm-5">Problem Measures</label>
						<div class="col-sm-7" id="extFilterProblemRule"></div>
					</div>
					<div class="col-sm-3">
						<button type="button" id="problemGenerate"
							class="btn btn-success btn-sm btn-xs">Generate</button>
					</div>
				</div>
				
			<div class="table-responsive col-sm-12">
				
				<table id="membershipTable"
					class="table table-striped table-hover table-responsive">

					<thead>
						<tr>
							<th scope="col" role="row">Sno</th>
							<th scope="col" role="row">Provider</th>
							<th scope="col" role="row">Last Name</th>
							<th scope="col" role="row">First Name</th>
							<th scope="col" role="row">Birthday</th>
							<th scope="col" role="row">Age</th>
							<th scope="col" role="row">Sex</th>
							<th scope="col" role="row">Start Date</th>
							<th scope="col" role="row">Resolved Date</th>
						</tr>
					</thead>

					<tbody>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>


<style>
#mbrProblemMeasureTable {
	width: 100% !important;
}
</style>


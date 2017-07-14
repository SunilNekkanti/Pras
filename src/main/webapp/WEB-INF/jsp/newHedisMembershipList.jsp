<%@  page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@  taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="context1"
	value="${pageContext.request.contextPath}/${userpath}" />
<link rel="stylesheet"
	href="${contextHome}/resources/css/bootstrap-multiselect.css"
	type="text/css">
<link href="http://cdn.rawgit.com/davidstutz/bootstrap-multiselect/master/dist/css/bootstrap-multiselect.css"
    rel="stylesheet" type="text/css" />
<script src="${contextHome}/resources/js/bootstrap-multiselect.js"
    type="text/javascript"></script>
<script>
$language = {"processing": "<i class='fa fa-refresh fa-spin' style='font-size:80px;color:#3c763d'></i>"};

$(document).ready(function() {
	
	  var completed = 0;
	  var pending = 0;

	 
	  var reportDateDropdown = function(){
			
		insSelectValue= $("#insu option:selected").val();
		prvdrSelectValue 	= $("#prvdr option:selected").val(); 
		var params = { insId:insSelectValue, prvdrId:prvdrSelectValue, pageNo:0, pageSize:200 };
	 	var str = jQuery.param( params );
	 	    
	 	$.getJSON("${context1}/"+'mlrReportDate/list?'+str, function(data){
					    
					  //clear the current content of the select
					   var $selectReportDat = $('#extFilterReportDate');
					     var s = $('<select id=\"clmReportDate\" style=\"width:150px;\" class=\"btn btn-default selectAll content\" multiple=\"multiple\">');
					     //iterate over the data and append a select option
					     $.each(data.data.list, function(key, val){
					    	 if(key == 0) {
					    		 s.append('<option value="'+val.reportMonth+'" Selected>' + val.reportMonth +'</option>');
					    	 }  else {
					    		 s.append('<option value="'+val.reportMonth+'">' + val.reportMonth +'</option>');
					    	 }
					    	 
					     });
					     
					     s.append('</select>');
					     $selectReportDat.html(s);
					     $('#clmReportDate').multiselect({numberDisplayed: 0, 
					    	 buttonWidth: '150px',
					    	 includeSelectAllOption: true
					    });
					     
					    if(data.data.list.length < 1)
					    	 	$("#claimReportGenerate").hide();
					     else
					    	 $("#claimReportGenerate").show();
					     
				 }).success(function() { 
					
			 });
	 	
	    }
	
		$("#hedisGenerate").click(function(event)
		{
			$(".clrRed").text("");
			callHedisGenerate();
		});
		 var hedisDescription = new Array;	
    	 var $selectIns = $('#extFilterIns');
    	  $.getJSON("${context1}/"+'/insurance/list?pageNo=0&pageSize=200', function(data){
			    
			     //clear the current content of the select
			     var s = $('<select id=\"insu\" style=\"width:150px;\" class=\"btn btn-default\">');
			     //iterate over the data and append a select option
			     $.each(data.data.list, function(key, val){
			    	 s.append('<option value="'+val.id+'">' + val.name +'</option>');
			     });
			     s.append('</select>');
			     $selectIns.html(s);
			    
		 }).success(function() { 
			 providerDropdown();
		 });
		 
		 var providerDropdown = function(){
			 var insSelectValue = Cookies.get('insu');
			 if(insSelectValue != undefined)
					insSelectValue = insSelectValue;
				else{
					insSelectValue= $("#insu option:selected").val();
					Cookies.set('insu', insSelectValue,{path: cookiePath });
				}
				$('select[id="insu"]').val(insSelectValue);
 			 var $selectPrvdr = $('#extFilterPrvdr');
 	    	  $.getJSON("${context1}/"+'/insurance/providerlist?insId='+insSelectValue, function(data){
 				     //clear the current content of the select
 				     var s = $('<select id=\"prvdr\" style=\"width:150px;\" class=\"btn btn-default\">');
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
					Cookies.set('prvdr', prvdrSelectValue,{path: cookiePath });
				 }	
				$('select[id="prvdr"]').val(prvdrSelectValue);
 				 hedisRuleDropdown(true);
 	    	 });
    	  }
    	  
    	  var columns ;
    	  var hedisRuleDropdown = function(hedisDropDownSet){
    		 
    		  if(hedisDropDownSet)
    		  	$('#hedisRule').find('option').remove();
				
				var $selectHedisRule = $('#extFilterHedisRule');
				var $selectHedisRule1 = $('#extFilterHedisRule1');
				var insSelectValue1 = $("#insu option:selected").val();
				//var hedisSelectValue1 = $("#").val();
		 		 if( $("#insu option:selected").val() == null){
		     		 insSelectValue1 = 1;
		     	 }
		 		
		    	  $.getJSON("${context1}/"+'/hedisMeasureRule/list?insId='+insSelectValue1, function(data){
		    		 
		    		  var hedisRuleList = new Array;
		    		  if(hedisDropDownSet)
		    		  {
		    			  var hedisRuleCacheList = getDropDownCache('hedisRule');
		    			 
		    			  //clear the current content of the select
						     var s = $('<select id=\"hedisRule\" style=\"width:150px;\" multiple=\"multiple\">');
						     //iterate over the data and append a select option
						     $.each(data.data, function(key, val){
						    	 hedisRuleSelected = "";
						    	 if(jQuery.inArray(val.id, hedisRuleCacheList) != -1){
						    		 hedisRuleSelected ="selected";
									}
						    	 s.append('<option value='+val.id+' '+ hedisRuleSelected+ '>' + val.shortDescription +'</option>');
						    	 hedisDescription.push(val.description);
						     });
						     s.append('</select>');
						     $selectHedisRule.html(s);
						     $('#hedisRule').multiselect({numberDisplayed: 0, 
						    	 buttonWidth: '150px',
						    	 includeSelectAllOption: true,
						    	 templates: {
									 ul: '<ul class="multiselect-container dropdown-menu hedisRule"></ul>'
								 },
						    });
		    		  }
		    		  	dropDownCache("hedisRule");
		    		  	reportDateDropdown();
				 });
    	  }
    	  
    	  var callHedisGenerate = function(){
    		  if(!$("#hedisRule option:selected").text())
    			  {
    			  	$(".clrRed").text("Select atleast one hedis measure");
    			  	return false;
    			  }

				
	     		var myTable = $("#membershipTable");
	     		var thead = myTable.find("thead");  
	     		$("#membershipTable th").each(function() {
					if($(this).attr("role") == "row"){
					}else{
						$(this).remove();
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
	     		
	     		columns = new Array();
	     		columns.push({ "sTitle": "Actions",
	     			
	     			"render": function (data, type, full, meta) {
							return '<a href="javascript:void(0)" id="'+full.id+'" onclick="mbrHedisFollowup('+full[5]+',\''+full[1]+'\',\''+full[2]+'\')"><span class="glyphicon glyphicon-pencil"></span></a>';
					  }
	     		});
	     		columns.push({ "sTitle": "Last Name"}),
	     		columns.push({ "sTitle": "First Name"});
	     		columns.push({ "sTitle": "DOB"});
	     		columns.push({ "sTitle": "Gender"});
	     		columns.push({ "sTitle": "Member Id"});
	     		columns.push({ "sTitle": "Rule Id"});
	     		
	     		var hedisRuleList = document.getElementById('hedisRule').options;
	     		$headerCount = 7;
	     		$.each( hedisRuleList, function(m, value ){
	     				$('#membershipTable').find('tr:eq(0)').each(function(index, text1){
	     					if($("#hedisRule option:selected").text().indexOf(value.text) >= 0)
	     					{	
	     						$(this).find('th').eq(-1).after('<th> <center title="'+hedisDescription[m]+'">'+value.text+'</center></th>'); 
	     						columns.push({ "sTitle": value.text,
	     							"mDataProp": $headerCount,	
	     							"render": function (data, type, full, meta) {
						     				if(data){
						     					$clrClass ="completed";
						     					if(data.indexOf('?') != -1){
						     						$clrClass ="pending";
						     					}
						     					return '<a href="javascript:void(0)"  class="'+$clrClass+'" onclick="getMbrNewHedisMeasure('+full[5]+','+full[6]+')">'+data+'<span class="glyphicon glyphicon-zoom-in"></span></a>';
						     				}
						   	       				 return "";
							  		}
	     						});
	     						$headerCount++;
	     						
	     					}
	     					
	     				});
	     		});
	     		
	     		callDatableWithChangedDropDown();
    	  }
    	  
    	  function dropDownArray($reference){
    		  var ruleArray = new Array;
    		  $("#"+$reference+" option:selected").each  ( function() {
     		    	ruleArray.push ($(this).val());
     		    });
    		  return ruleArray;
    	  }
    	  
    	  function callDatableWithChangedDropDown(){
    		   
    		   var insSelectValue= $("#insu option:selected").val();
    		   var prvdrSelectValue= $("#prvdr option:selected").val();
    		   var hedisRuleSelectValue= dropDownArray("hedisRule"); // $("#hedisRule option:selected").val();
    		   
    		   var reportMonth 	= 	$("#clmReportDate option:selected").val();
    		   var startDate 	= 	$("#startDate").val();
    		   var endDate		=	$("#endDate").val();
    		   var rosterList  	= 	dropDownArray("clmRoster"); // $("# option:selected").val();
    		   var capList		=	dropDownArray("clmCap"); // $("#clmCap option:selected").val();
    		   
    		  

    		   
    		   if ( $.fn.DataTable.isDataTable('#membershipTable') ) {
  						$('#membershipTable').DataTable().destroy();
			   }
				
    		   $('#membershipTable tbody').empty();
				
    		   GetMembershipByInsPrvdrHedisRule(insSelectValue,prvdrSelectValue,hedisRuleSelectValue,reportMonth, 
    				   startDate, endDate, rosterList, capList, columns);
    	}  
    	     
    	$(document.body).on('change',"#insu",function (e) {
    		Cookies.set('insu', $("#insu option:selected").val(),{path: cookiePath });
    		Cookies.remove('prvdr',{path: cookiePath });
    		if ( $.fn.DataTable.isDataTable('#membershipTable') ) {
					$('#membershipTable').DataTable().destroy();
	   		}
    		$('#membershipTable tbody').empty();
    		providerDropdown();
  		});
    	
    	$(document.body).on('change',"#prvdr",function (e) {
    		Cookies.set('prvdr', $("#prvdr option:selected").val(),{path: cookiePath });
    		if ( $.fn.DataTable.isDataTable('#membershipTable') ) {
					$('#membershipTable').DataTable().destroy();
	   		}
    		$('#membershipTable tbody').empty();
  		});
  	
  	  $(document.body).on('change',"#hedisRule",function (e) {
  		if ( $.fn.DataTable.isDataTable('#membershipTable') ) {
			$('#membershipTable').DataTable().destroy();
			}
  			hedisRuleDropdown(false);
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
		   var sSearchHedisRule = paramMap.sSearchHedisRule;
		   
		   var sSearchReportMonth = paramMap.sSearchReportMonth;
		   var sSearchStartDate = paramMap.sSearchStartDate;
		   var sSearchEndDate = paramMap.sSearchEndDate;
		   var sSearchCap = paramMap.sSearchCap;
		   var sSearchRoster = paramMap.sSearchRoster;
		  
		   dropDownCache('hedisRule');
		   //create new json structure for parameters for REST request
		   var restParams = new Array();
		   restParams.push({"name" : "pageSize", "value" : pageSize});
		   restParams.push({"name" : "pageNo", "value" : pageNum });
		   restParams.push({"name" : "sort", "value" : sortName });
		   restParams.push({"name" : "sortdir", "value" : sortDir });
		   restParams.push({"name" : "sSearch" , "value" : paramMap.sSearch  });
		   restParams.push({"name" : "sSearchIns" , "value" : sSearchIns  });
		   restParams.push({"name" : "sSearchPrvdr" , "value" : sSearchPrvdr  });
		   restParams.push({"name" : "sSearchHedisRule" , "value" : sSearchHedisRule  });
		   
		   restParams.push({"name" : "sSearchStartDate" , "value" : sSearchStartDate  });
		   restParams.push({"name" : "sSearchEndDate" , "value" : sSearchEndDate  });
		   restParams.push({"name" : "sSearchReportMonth" , "value" : sSearchReportMonth  });
		   restParams.push({"name" : "sSearchRoster" , "value" : sSearchRoster  });
		   restParams.push({"name" : "sSearchCap" , "value" : sSearchCap  });
		   
		   var width;
		   width = $('#membershipTable th').length * 100;
		   if(parseInt(width) > 1200){
			   width = width + "px";
			   $('#membershipTable').width(width);
		   }	   
		 $.ajax( {
              dataType: 'json',
              contentType: "application/json;charset=UTF-8",
              type: 'GET',
              url: sSource,
              data: restParams,
              success: function(res) {
            	 fnCallback(res);
            	 res.iTotalRecords = res.data.list.totalCount;
                 res.iTotalDisplayRecords = res.data.list.totalCount;
            },
              error : function (e) {
              }
          } );
     	}
     	
     	
     	  GetMembershipByInsPrvdrHedisRule = function (insId, prvdrId, hedisRuleId,reportMonth, startDate, endDate, rosterList, capList, columns){
     		  var oTable = $('#membershipTable').removeAttr( "width" ).DataTable({  
  	        	"language": $language,
  	        	"sDom": 'lBfrtip',
	        	 "buttons": [
	        	             {
	        	                 extend: 'excelHtml5',
	        	                 title: 'Hedis Measure RuleTable Export'
	        	             },{
	        	                 extend: 'pdfHtml5',
	        	                 orientation: 'landscape',
	        	                 pageSize: 'LEGAL',
	        	                 exportOptions: {
	        	                         columns: ':visible'
	        	                  }
	        	             },'colvis'
	        	             
		                   ],
  	         "bDestroy" : true,	
     	     "sAjaxSource" : "${context1}/"+'reports/newHedisMembership/list',
     	     "sAjaxDataProp" : 'data.list',
     	    "aoColumns":  columns,    
     	     "bLengthChange": true,
     	     "aLengthMenu": [[12, 24, 36, 100000], [12, 24, 36, "All"]],
     	     "bStateSave": true,
     	     "iDisplayLength": 12,
     	     "sPaginationType": "full_numbers",
     	     "bProcessing": true,
     	    "bServerSide": true,
     	    "columnDefs": [
			     	   
			     	    {
			                 "targets": [5],
			                 "visible": false,
			                 "searchable": false
			             },
			             {
			                 "targets": [6],
			                 "visible": false,
			                 "searchable": false
			             }
			   ],          
     	     "fnServerParams": function ( aoData ) {
                aoData.push(
                    {"name": "sSearchIns", 			"value": insId},
                    {"name": "sSearchPrvdr", 		"value": prvdrId },
                    {"name": "sSearchHedisRule", 	"value": hedisRuleId },
                    {"name": "sSearchReportMonth", 	"value": reportMonth },
                    {"name": "sSearchStartDate", 	"value": startDate },
                    {"name": "sSearchEndDate", 		"value": endDate },
                    {"name": "sSearchRoster", 		"value": rosterList },
                    {"name": "sSearchCap", 			"value": capList }
                );
             },        
     	     "fnServerData" : datatable2RestMembership,
     	    "fnFooterCallback": function ( nRow, aaData, iStart, iEnd, aiDisplay ) {}
     	});
  	        
     }


     	$('select').css({'width': 150});
  } );

function mbrHedisFollowup(id,lastName,firstName) 
{
	   if ( $.fn.DataTable.isDataTable('#mbrHedisMeasureTable') ) {
				$('#mbrHedisMeasureTable').DataTable().destroy();
		}
		$('#mbrHedisMeasureTable tbody').empty();
		
		
	 var datatable2MbrHedisMeasure = function(sSource, aoData, fnCallback) {
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
		   //create new json structure for parameters for REST request
		   var restParams = new Array();
		  
		  var ruleArray1 = new Array;
		   $("#hedisRule option:selected").each  ( function() {
		    	ruleArray1.push ( $(this).val() );
		    });
		   restParams.push({"name" : "hedisRuleIds", "value" :  ruleArray1 });
		
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
 	
 	$('#mbrHedisMeasureTable').dataTable({
 	     "sAjaxSource" : "${context1}/"+'membership/'+id+'/hedisMeasureList',
 	     "sAjaxDataProp" : 'data',
 	     "aoColumns": [
                        { "mDataProp": "id", "bSearchable" : false, "bVisible" : true ,"bSortable": false },
                        { "mDataProp": "hedisMeasureRule.shortDescription","bSearchable" : true, "bSortable" : true,"sWidth" : "45%",
                        	"render": function (data, type, full, meta) {
                        		return '<span data-toggle="tooltip" title="'+full.hedisMeasureRule.description+'">'+data+'</span>';
                        	}
                        },
                        { "mDataProp": "dos","bSearchable" : true, "bSortable" : true,"sWidth" : "45%"}
                       
                    ],
           "aoColumnDefs": [ 
								{ "sName": "id", "aTargets": [ 0 ],
									   "render": function ( data, type, full, meta ) {
								       return '<input type="checkbox" class="chkRule" name="rule_group[]"   id="'+data+'" value="'+data+'"/>';
								}},
								{ "sName": "hedisMeasureRule.shortDescription", "aTargets": [1]},
								{ "sName": "dos", "aTargets": [ 2 ],
									"render": function ( data, type, full, meta ) {
									       return '<input type="text" class="'+full.id+'" name="dos[]" readonly /><span class="clrRed" name ="dosError[]"></span>';
								}}
                             
           ],          
 	     "bLengthChange": false,
 	     "paging": false,
 	     "info": false,
 	     "bFilter": false,
 	     "bProcessing": true,
 	     "bServerSide" : true,
 	     "bStateSave": true,
 	     "fnServerData" : datatable2MbrHedisMeasure
 	});
 	
			$(".clrRed").html("");
			
			
		//	$( "#modal-body" ).html('');
			
			$(".modal-title").html(lastName+","+firstName+" - Hedis Followup");
			$( "#modal-body .notes" ).remove();
			$( "#modal-body .history" ).remove();
   			$( "#modal-body #mbr_id" ).remove();
 			$( "#modal-body" ).append('<div class="notes"> <br /> Notes <textarea  id="followup_details"  class="form-control" rows="5" ></textarea></div>');
 			$( "#modal-body" ).append('<div class="history"><br /> History<textarea  id="followup_history" readonly class="form-control" rows="5" ></textarea></div>');
 			$( "#modal-body" ).append('<input type="hidden"  value="'+id+'" id="mbr_id"  class="form-control" />');
 			
		  var  mbr_id =id;
		  var followup_text = $("#followup_history");
		  
		  var source = "${context1}/"+'reports/membershipHedis/'+id+'/followupDetails';
		  
		  $.ajax({
			  dataType: 'json',
           contentType: "application/json;charset=UTF-8",
		      url : source,
		       success: function(data, textStatus, jqXHR)
		      {
		          $.each(data.data, function(key, val)
		          {
				      followup_text.append(" >>>> "+val.createdDate+ " >>>>  " +val.createdBy+ " >>>> ");			      
				      followup_text.append(" \n");				      
				      followup_text.append(val.followupDetails);
				      followup_text.append("  \n");
				      followup_text.append(" \n");
		         })
				     
		          $('#myModal').modal('show');
		      },
		      error: function (jqXHR, textStatus, errorThrown)
		      {
		    	  alert("Error");
		      }
		  });
 			
 			return false;
	}
	
	// get newMbrHedis
	function getMbrNewHedisMeasure(mbrId, ruleId) 
	{
		alert(" mbrId "+mbrId+" ruleId "+ruleId);
		$tableReference = "mbrNewHedisMeasureTable";
	   if ( $.fn.DataTable.isDataTable('#'+$tableReference) ) {
				$('#'+$tableReference).DataTable().destroy();
		}
		$('#'+$tableReference+' tbody').empty();
		$('#mbrNewHedisMeasure').modal('show');
		
		
		
		var datatable2MbrNewHedisMeasure = function(sSource, aoData, fnCallback) {
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
			   //create new json structure for parameters for REST request
			   var restParams = new Array();
			  
			   restParams.push({"name" : "mbrId", "value" :  mbrId });
			   restParams.push({"name" : "ruleId", "value" : ruleId });
			
			   $.ajax( {
	             dataType: 'json',
	             contentType: "application/json;charset=UTF-8",
	             type: 'GET',
	             url: sSource,
	             data: restParams,
	             success: function(res) {
	            	fnCallback(res);
	             },
	             error : function (e) {
	             }
	         } );
	 	}
	 	
	 	$('#mbrNewHedisMeasureTable').dataTable({
	 	     "sAjaxSource" : "${context1}/"+'reports/mbrNewHedisMembership/list',
	 	     "sAjaxDataProp" : 'data.list',
	 	    "columnDefs": [
				     	   
				     	    {
				                 "targets": [5],
				                 "visible": false,
				                 "searchable": false
				             },
				             {
				                 "targets": [6],
				                 "visible": false,
				                 "searchable": false
				             }
				   ],          
	     	     "fnServerParams": function ( aoData ) {
	                aoData.push(
	                    {"name": "sSearchMbrId", 			"value": mbrId},
	                    {"name": "sSearchRuleId", 		"value": ruleId }
	                );
	             },  
	 	     "bLengthChange": false,
	 	     "paging": false,
	 	     "info": false,
	 	     "bFilter": false,
	 	     "bProcessing": true,
	 	     "bServerSide" : true,
	 	     "bStateSave": true,
	 	     "fnServerData" : datatable2MbrNewHedisMeasure
	 	});
	}
    </script>    

<div class="panel-group membershipHedisReport">
	<div class="panel panel-success">
		<div class="panel-heading">
			Hedis Report <span class="clrRed"> </span>
		</div>
		<div class="panel-body">
		
			<div class="col-sm-12">
					<div class="col-sm-2">
						<label class="control-label col-sm-12">Insurance</label>
						<div class=" col-sm-12" id="extFilterIns"></div>
					</div>
					<div class="col-sm-2">
						<label class="control-label col-sm-12">Provider</label>
						<div class="col-sm-12" id="extFilterPrvdr"></div>
					</div>
					<div class="col-sm-2">
						<label class="control-label col-sm-12">Hedis Measures</label>
						<div class="col-sm-12" id="extFilterHedisRule"></div>
					</div>
					
					<div class="col-sm-2">
						<label class="control-label col-sm-12">Report Month</label>
						<div class=" col-sm-12" id="extFilterReportDate"></div>
					</div>
					
					<div class="col-sm-2">
						<label class="control-label col-sm-12">Start Date</label>
						<div class="col-sm-12" id="extFilterStartDate">
							<input type="text" name="startDate" id="startDate" class="form-control datepicker1">
						</div>
					</div>
					
					<div class="col-sm-2">
						<label class="control-label col-sm-12">End  Date</label>
						<div class="col-sm-12" id="extFilterEndDate">
							<input type="text" name="endDate" id="endDate" class="form-control datepicker3">
						</div>
					</div>
					
		 	</div>
		 	<div class="col-sm-12">
		 			
		 			<div class="col-sm-2 col-sm-offset-6">
		 			<label class="control-label col-sm-12">Roster</label>
		 				<div class="col-sm-12 multiple" id="extFilterRoster">
							 <select id="clmRoster" style="width:150px;" class="btn btn-default selectAll"  multiple="multiple">
								<option value="Y" selected>Yes</option>
								<option value="N" selected>No</option>
								<option value="NULL" selected>N/A</option>
							</select>	
						</div>
					</div>
					<div class="col-sm-2">
						<label class="control-label col-sm-12">Cap</label>
						<div class="col-sm-12 multiple" id="extFilterCap">
							 <select id="clmCap" style="width:150px;" class="btn btn-default selectAll"  multiple="multiple">
								<option value="Y" selected>Yes</option>
								<option value="N" selected>No</option>
								<option value="NULL" selected>N/A</option>
							</select>	
							
						</div>
					</div>
					<div class="col-sm-2">
					<label class="control-label col-sm-12"><br /></label>
						<button type="button" id="hedisGenerate"
							class="btn btn-success btn-sm btn-xs">Generate</button>
					</div>
			</div>
				
			<div class="table-responsive col-sm-12">
				
				<table id="membershipTable"
					class="table table-striped table-hover table-responsive">

					<thead>
						<tr>
									<th scope="col" role="row">Action</th>
									<th scope="col" role="row">Last Name</th>
									<th scope="col" role="row">First Name</th>
									<th scope="col" role="row">DOB</th>
									<th scope="col" role="row">Gender</th>
									<th scope="col" role="row">MBR ID</th>
									<th scope="col" role="row">Rule ID</th>
						</tr>
					</thead>

					<tbody>

					</tbody>

				</table>
			</div>

		</div>

	</div>
</div>
<link rel="stylesheet" href="${context}/resources/css/bootstrap-multiselect.css" type="text/css">
<script type="text/javascript" src="${context}/resources/js/bootstrap-multiselect.js"></script>
<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog">
	<div class="modal-dialog modal-lg">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Membership Hedis Followup</h4>
			</div>
			<div class="modal-body" id="modal-body">
				<table id="mbrHedisMeasureTable"
					class="table table-striped table-hover table-responsive">
					<thead>
						<tr>
							<th scope="col">Select</th>
							<th scope="col">Hedis Measure</th>
							<th scope="col">Date of Service</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
			<div class="modal-footer">
				<button type="button" id="followupSubmit" class="btn btn-default">Submit</button>
				<button type="button" id="hedisGenerate" class="btn btn-default"
					data-dismiss="modal">Cancel</button>
			</div>
		</div>

	</div>
</div>


<div class="modal fade" id="mbrNewHedisMeasure" role="dialog">
	<div class="modal-dialog modal-lg">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Membership New Hedis Rule</h4>
			</div>
			<div class="modal-body" id="modal-body">
				<table id="mbrNewHedisMeasureTable"
					class="table table-striped table-hover table-responsive">
					<thead>
						<tr>
							<th scope="col" role="row">Action</th>
							<th scope="col" role="row">Last Name</th>
							<th scope="col" role="row">First Name</th>
							<th scope="col" role="row">DOB</th>
							<th scope="col" role="row">Gender</th>
							<th scope="col" role="row">MBR ID</th>
							<th scope="col" role="row">Rule ID</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
			
		</div>

	</div>
</div>

<script>
  jQuery( document ).ready(function( $ ) {
	    $('body').on('click',".chkRule", function(){
	    	if($(this).is(':checked'))
	    	{
	    		$("."+this.id).addClass( "datepicker" );
	    		$(".datepicker").datepicker({maxDate:"0"}).datepicker("setDate",new Date());
	    		$(".datepicker" ).show();
	    	}
	    	else
	    	{
	    		$(".datepicker" ).datepicker( "destroy" );
	    		$("."+this.id).removeClass("datepicker" );
	    		$("."+this.id).removeClass("hasDatepicker" );
	    		$("."+this.id).removeAttr('id');
	    		$("."+this.id).val('');	    		
	    	}
	       		
	    });
	    $(document).ready(function() {

	    	
	    	$('#clmRoster, #clmCap').multiselect({numberDisplayed: 0, 
	    		 buttonWidth: '150px',
	    		 includeSelectAllOption: true
	    		 
	    	});
	    	});
	});
  </script>
<style>
	 td {
  overflow: hidden; /* this is what fixes the expansion */
  text-overflow: ellipsis; /* not supported in all browsers, but I accepted the tradeoff */
  white-space: nowrap;
}
a.pending {color:red !important;} 
</style>
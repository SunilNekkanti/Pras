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
<link href="http://cdn.rawgit.com/davidstutz/bootstrap-multiselect/master/dist/css/bootstrap-multiselect.css"
    rel="stylesheet" type="text/css" />
<script src="${contextHome}/resources/js/bootstrap-multiselect.js"
    type="text/javascript"></script>
<script src="https://cdn.datatables.net/fixedcolumns/3.2.2/js/dataTables.fixedColumns.min.js"></script>
<script src="http://cdn.datatables.net/buttons/1.1.0/js/buttons.html5.js"></script>
<link rel="stylesheet" href="https://cdn.datatables.net/fixedcolumns/3.2.2/css/fixedColumns.dataTables.min.css">

	<script>
	
$('.selectAll').multiselect({numberDisplayed: 0, 
	 buttonWidth: '150px',
	 includeSelectAllOption: true
	 
});
</script>
<script>
$(document).ready(function() {
	$.fn.dataTable.ext.errMode = 'none';
		$("#tabs").tabs( {
			"show": function(event, ui) {
				var oTable = $('div.dataTables_scrollBody>table.display', ui.panel).dataTable();
				if ( oTable.length > 0 ) {
					oTable.fnAdjustColumnSizing();
				}
			}
		} );
		
		$("#claimReportGenerate").click(function(event)
		{
			if($("#clmReportDate").val() == null)
				{
				 alert(" Select Report Date");
				  return false;
				}
			
			callClaimReportGenerate();
			$( "#tabs" ).tabs({ active: 0});
			$( "#tabLevel2" ).addClass("hide");
			$( "#tabLevel3" ).addClass("hide");
		});
		
		
	
    	 var $selectIns = $('#extFilterIns');
    	  $.getJSON(getContextPath()+'/insurance/list?pageNo=0&pageSize=200', function(data){
			    
			     //clear the current content of the select
			     var s = $('<select id=\"clmInsu\" style=\"width:150px;\" class=\"btn btn-default\">');
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
 			 var insSelectValue = Cookies.get('clmInsu');
 				
 			 if(insSelectValue != undefined)
 					insSelectValue = insSelectValue;
 				else{
 					insSelectValue= $("clmInsu option:selected").val();
 					Cookies.set('clmInsu', insSelectValue,{path: cookiePath });
 				}
 			     
 				$('select[id="clmInsu"]').val(insSelectValue);
  			 var $selectPrvdr = $('#extFilterPrvdr');
  	    	  $.getJSON(getContextPath()+'/insurance/providerlist?insId='+insSelectValue, function(data){
  				    
  				     //clear the current content of the select
  				     var s = $('<select id=\"clmPrvdr\" style=\"width:150px;\" class=\"btn btn-default selectAll\" multiple=\"multiple\">');
  				     //iterate over the data and append a select option
  				     
  				     $.each(data.data.list, function(key, val){
  				    	
 				    		 s.append('<option value="'+val.id+'" Selected>' + val.name +'</option>');
 				    	
  				    	 
  				     });
  				     s.append('</select>');
  				     $selectPrvdr.html(s);
  			 }).success(function() { 
  				//$('select[id="mlrPrvdr"]').val(prvdrSelectValue);
 				  $('#clmPrvdr').multiselect({numberDisplayed: 0, 
 				    	 buttonWidth: '150px',
 				    	 includeSelectAllOption: true
 				    });
  				var prvdrSelectValue = Cookies.get('clmPrvdr');
 				 if(prvdrSelectValue != undefined) 
 				 {
 					 prvdrSelectValue = dropDownSelectedValue("clmPrvdr",false, false);
 				 }	 
 				 else{
 					prvdrSelectValue= $("#clmPrvdr option:selected").val();
 					Cookies.set('clmPrvdr', prvdrSelectValue,{path: cookiePath });
 				 }	
 				
 				reportDateDropdown();
  	    	
  			}); 
     	  }
		 
		 
		 var reportDateDropdown = function(){
			 var insSelectValue = Cookies.get('clmInsu');
				
			 if(insSelectValue != undefined)
					insSelectValue = insSelectValue;
				else{
					insSelectValue= $("#clmInsu option:selected").val();
					Cookies.set('clmInsu', insSelectValue,{path: cookiePath });
				}
			 prvdrSelectValue 	= dropDownSelectedValue("clmPrvdr",false, false);
			 var params = { insId:insSelectValue, prvdrId:prvdrSelectValue, pageNo:0, pageSize:200 };
	 	    	var str = jQuery.param( params );
	 	    
	 	    	  $.getJSON(getContextPath()+'/mlrReportDate/list?'+str, function(data){
					    
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
	 	    	  
	 	    	  
	 	    	 
	 	    	 
	 	    	$.getJSON(getContextPath()+'/claimType/list?pageNo=0&pageSize=200', function(data){
					  //clear the current content of the select
					   var $selectReportDat = $('#extFiltercategory');
					   var s = $('<select id=\"clmType\" style=\"width:150px;\" class=\"btn btn-default selectAll content\" multiple=\"multiple\">');
					     //iterate over the data and append a select option
					     $.each(data.data.list, function(key, val){
					    	   		 s.append('<option value="'+val.id+'" Selected>' + val.name +'</option>');
					     });
					     s.append('</select>');
					     $selectReportDat.html(s);
					    
					     $('#clmType').multiselect({numberDisplayed: 0, 
					    	 buttonWidth: '150px',
					    	 includeSelectAllOption: true
					    });
					    if(data.data.list.length < 1)
					    	 	$("#claimReportGenerate").hide();
					     else
					    	 $("#claimReportGenerate").show();
					     
				 }).success(function() { 
					 categoryDropDown();
		    	 });
	 	    	 
		 }
    	  
    	  var columns ;
    	  var callClaimReportGenerate = function(){
    		  var rowHeader = $('#claimReport1 thead tr');
    			for(var col = 0; col<25; col++)
    				rowHeader.append('<th scope="col" role="row" class="hide"></th>');
    			
	     		callDatableWithChangedDropDown();
    	  }
    	  
    	  function callDatableWithChangedDropDown(){
    		   insSelectValue			= dropDownSelectedValue("clmInsu",false, true);
    		   prvdrSelectValue 	    = dropDownSelectedValue("clmPrvdr",false, false);
      		   reportDateSelectValue 	= dropDownSelectedValue("clmReportDate",true, false);
      		   rosterSelectValue 		= dropDownSelectedValue("clmRoster",false, true);
      		   capSelectValue 			= dropDownSelectedValue("clmCap",false, true);
      		   claimTypeSelectValue 	= dropDownSelectedValue("clmType",true, false);
      		   categorySelectValue 		= dropDownSelectedValue("clmRiskRecon",false, true);
    		   
      		   datatableDelete("claimReport1");
      		   $('#claimReport1 tbody').remove();
    		   GetClaimReport(insSelectValue,prvdrSelectValue,reportDateSelectValue, claimTypeSelectValue, categorySelectValue, rosterSelectValue, capSelectValue,  columns);
    	}  
    	  
    	 
    	     
    	$(document.body).on('change',"#clmInsu",function (e) {
    		Cookies.set('clmInsu', $("#clmInsu option:selected").val(),{path: cookiePath });
    		providerDropdown();
    		datatableDelete("claimReport1");
  		});
    	
    	$(document.body).on('change',"#clmType",function (e) {
    		if($("#clmType").val() == null)
			{
			  alert(" Select Claim Type");
			  return false;
			}
    		categoryDropDown();
  		});
    	
    	
    	var categoryDropDown = function(){
    		var categoryList = new Array();
    		$("#clmType option:selected").each(function() {
    			categoryList.push ($(this).val());
    				
    			});
	 	    	$.getJSON(getContextPath()+'/riskReconClaimType/list?pageNo=0&pageSize=200&claimType='+categoryList, function(data){
	 	    		
					     
				 }).success(function(data, arrayList) { 
					//clear the current content of the select
					$("#extFilterRiskRecon").html('');
					if(categoryList.length > 0){
						   var $selectReportDat = $('#extFilterRiskRecon');
						   var s = $('<select id=\"clmRiskRecon\" style=\"width:150px;\" class=\"btn btn-default selectAll  content \" multiple=\"multiple\">');
						     $.each(data.data.list, function(key, val){
						    	 if(key == 0) {
						    		 s.append('<option value="'+val.id+'" Selected>' + val.name +'</option>');
						    	 }  else {
						    		 s.append('<option value="'+val.id+'">' + val.name +'</option>');
						    	 }
						     });
						     s.append('</select>');
						     $selectReportDat.html(s);
						    
						     $('#clmRiskRecon').multiselect({numberDisplayed: 0, 
						    	 buttonWidth: '150px',
						    	 includeSelectAllOption: true
						    });
						    if(data.data.list.length < 1)
						    	 	$("#claimReportGenerate").hide();
						     else
						    	 $("#claimReportGenerate").show();
						}
					else
					{	$("#claimReportGenerate").hide(); $("#extFilterRiskRecon").html(''); }
			 });
		}
    	
     	
  	  var datatable2ClaimReport = function(sSource, aoData, fnCallback) {
     		
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
		   var sSearchGenerateDate = paramMap.sSearchGenerateDate;
		   var sSearchClaimType = paramMap.sSearchClaimType;
		   var sSearchCategory = paramMap.sSearchCategory;
		   var sSearchRoster= paramMap.sSearchRoster;
		   var sSearchCap= paramMap.sSearchCap;
		   
		   //create new json structure for parameters for REST request
		   var restParams = new Array();
		   restParams.push({"name" : "pageSize", "value" : pageSize});
		   restParams.push({"name" : "pageNo", "value" : pageNum });
		   restParams.push({"name" : "sort", "value" : "reportMonth" });
		   restParams.push({"name" : "sortdir", "value" : sortDir });
		   restParams.push({"name" : "sSearch" , "value" : paramMap.sSearch  });
		   restParams.push({"name" : "insId" , "value" : sSearchIns  });
		   restParams.push({"name" : "prvdrId" , "value" : sSearchPrvdr  });
		   restParams.push({"name" : "mbrId" , "value" : 0  });
		   restParams.push({"name" : "repMonth" , "value" : sSearchGenerateDate  });
		   restParams.push({"name" : "activityMonth" , "value" : 0  });
		   restParams.push({"name" : "claimType" , "value" : sSearchClaimType  });
		   restParams.push({"name" : "category" , "value" : sSearchCategory  });
		   restParams.push({"name" : "roster" , "value" : sSearchRoster  });
		   restParams.push({"name" : "cap" , "value" : sSearchCap  });
		   restParams.push({"name" : "levelNo" , "value" : 1  });
		   
		   if($( window ).width() > 900){
				 var width;
				 width = $('#claimReport1 th').length * 120;
				 if(width > 1200){
					 width = width + "px";
					 $('#claimReport1').width(width);
				 }	 
			} 
		   
		 $.ajax( {
              dataType: 'json',
              contentType: "application/json;charset=UTF-8",
              type: 'GET',
              url: sSource,
              data: restParams,
              success: function(res) {
            	  
            	  var td = res.data[0].length;
            	 if(td > 0){
            	  var th =  $("#claimReport1 thead tr th").length;
            	  $("#claimReport1 thead tr").html('');
            	  jQuery.each(res.data, function( i, val ) {
             		 if(i == 0){
             			 jQuery.each(val, function( index, text ) {
             					$("#claimReport1 thead tr").append('<th scope="col" role="row" tabindex="0" aria-controls="claimReport1" rowspan="1" colspan="1" aria-sort="ascending" >'+text+'</th>');
             			 });
             		 }
             		
             		});
            	  if(th > td)
            		  {
            		  	for(i = td; i < th; i++){
            		  		$("#claimReport1 thead tr").append('<th scope="col" class="hide" role="row" tabindex="0" aria-controls="claimReport1" rowspan="1" colspan="1" aria-sort="ascending" ></th>');
            		  	}
            		  }
            	 }
            	 else
            		 res.data = [];
             	  //clear the current content of the select
                   res.iTotalRecords = res.data.length-1;
                   res.iTotalDisplayRecords = res.data.length-1;
                   fnCallback(res);
             		
            },
              error : function (e) {
              }
          } );
     	}
     	
     	
     	  GetClaimReport = function (insId, prvdrId, generateDate, claimType,category, roster,cap,aoColumns) {
  	        var oTable = $('#claimReport1').removeAttr( "width" ).dataTable({  
  	        	"sDom": 'Bfrtip',
	        	 "buttons": [
	        	             {
	        	                 extend: 'excelHtml5',
	        	                 title: 'Medical Loss Ratio Table Excel Export',
	        	             },
	        	             {
								    extend: 'pdfHtml5',
								    orientation: 'landscape',
								    pageSize: 'LEGAL',
								    title: 'Medical Loss Ratio Table PDF Export',
								}
	        	             
		                   ],
  	         "bDestroy" : true,	
     	     "sAjaxSource" : getContextPath()+'/claimReport/list', 
     	     "sAjaxDataProp" : 'data',
     	     "bLengthChange": false,
     	     "bPagination":false,
     	     "iDisplayLength": 500,
     	     "info":     false,
     	     "sPaginationType": "full_numbers",
     	     "bProcessing": true,
     	     "bServerSide" : true,
     	   "initComplete": function(settings, json) {
     	    	if($("#claimReport1 tbody tr").length > 0){
	     			var val = ""; var count = 0;
	     		    var riskRecon = "";
	     		    var riskReconList = new Array();
	     		    
	     		   var riskReconId =""; var activityMonth =""; 
	     		   $('#claimReport1 tbody ').html('');
	     		   var colSum = new Array()
	     		    $.each(json.data, function(index, key)
	     		    {
	     		    	if(index > 0){
	     		    		 $('#claimReport1 tbody ').append("<tr></tr>");
	     		    		$.each(json.data[index], function(tdindex, tdvalue){
	     		    		  if(tdvalue == null) tdvalue = 0;
		     		    		if(tdindex == 4)
		     		    		{	riskRecon =tdvalue;   }
		     		    		if(tdindex > 4){
		     		    			activityMonth = json.data[0][tdindex];
		     		    			$('#clmRiskRecon option').each(function() {
		     			     		    if($(this).text() == riskRecon) {
		     			     		  	   	riskReconId = $(this).val();
		     			     			}
		     			           	});
		     		    			link = '<a href="javascript:void(0)"  onclick="return level2('+json.data[index][3].replace('-', '')+','+riskReconId+','+activityMonth.replace('-', '')+');">'+(Math.round(tdvalue*100)/100).formatMoney(2, '.', ',')+'</a>';
		     		    			 $('#claimReport1 tbody tr:last').append("<td>$"+link+"</td>");
		     		    			 if(typeof(colSum[tdindex])  === "undefined") {  colSum[tdindex] = 0;}
		     		    			 	colSum[tdindex] = colSum[tdindex] +  parseFloat(tdvalue); 
		     		    		}
		     		    		else
		     		    		{
		     		    			 $('#claimReport1 tbody tr:last').append("<td>"+tdvalue+"</td>");
		     		    		}	
	     		    		});
	     		    	}	
	     		    });
	     		   
	     		  $.each(json.data, function(index, key)
	  	     		    {
	     			 		
	  	     		    	if(index == 0){
	  	     		    		$('#claimReport1 tbody').append("<tr></tr>");
	  	     		    		$.each(json.data[index], function(tdindex, tdvalue){
	  	     		    			if(tdindex > 4)
	  	     		    				$('#claimReport1 tbody tr:last').append("<td>$"+(Math.round(colSum[tdindex]*100)/100).formatMoney(2, '.', ',')+"</td>");
	  	     		    			else
	  	     		    				$('#claimReport1 tbody tr:last').append("<td></td>");
	  	     		    		});
	  	     		    	}
	  	     		    });	
	     		    
	     			var colLen = json.data[0].length -1;
	     			$('#claimReport1  tbody  tr').find('td:gt('+colLen+')').remove();
	     			$('#claimReport1  thead  tr').find('th:gt('+colLen+')').remove(); 
	     			$('#claimReport1  tbody  tr').find('td:lt(3)').remove();
	     			$('#claimReport1  thead  tr').find('th:lt(3)').remove();
	     			
	     			var thead = $('#claimReport1 thead').html();
	     			var tbody= $('#claimReport1 tbody').html();
	     			var claimReport1  = $('#claimReport1').html();
	     			
	     			datatableDelete("claimReport1");

	     			$('#claimReport1').html('');
	     			$('#claimReport1').append("<thead>"+thead+"<thead>");
	     			$('#claimReport1').append("<tbody>"+tbody+"<tbody>");
	     			
	     			datatableCreate("claimReport1", 'Claim Report');
	     		
     	    	}
     	    	else{
     	    		 	datatableDelete("claimReport1");
     	    		 	$('#claimReport1  thead tr').find('th:gt(3)').remove();
     	    			$('#claimReport1').DataTable();
					}
     		    	
	     	 } ,
	     	
	        "fnServerParams": function ( aoData ) {
                aoData.push(
                    {"name": "sSearchIns", "value": insId},
                    {"name": "sSearchPrvdr", "value": prvdrId },
                    {"name": "sSearchGenerateDate", "value": generateDate },
                    {"name": "sSearchCategory", "value": category },
                    {"name": "sSearchRoster", "value": "'"+roster+"'"},
                    {"name": "sSearchCap", "value": "'"+cap+"'" },
                    {"name": "sSearchClaimType", "value": claimType }
                );
             },        
             "fnDrawCallback": function (setting, json) {
            	 var $paginate = this.siblings('.dataTables_paginate');

 	     	    if (this.api().data().length <= this.fnSettings()._iDisplayLength){
 	     	        $paginate.hide();
 	     	    }
 	     	    else{
 	     	        $paginate.show();
 	     	    }
 	     	    
	 	     	  if(this.api().data().length == 0){
	 	     		$('#claimReport1').empty();
	 	     		 $paginate.hide();
	 	     	  }
             },
     	     "fnServerData" : datatable2ClaimReport
     	});
     }
 } );
 

</script>

<div class="panel-group">
	<div class="panel panel-success">
		<div class="panel-heading">
			Claims Report <span class="clrRed"> </span>
		</div>
		<div class="panel-body" style="max-height: 750;">
		
			<div class="col-sm-12">
					<div class="col-sm-2 multiple">
						<label class="control-label col-sm-12">Insurance</label>
						<div class=" col-sm-12" id="extFilterIns"></div>
					</div>
					<div class="col-sm-2 multiple">
						<label class="control-label col-sm-12" >Provider</label>
						<div class="col-sm-12" id="extFilterPrvdr"></div>
					</div>
					
					<div class="col-sm-2 multiple">
						<label class="control-label col-sm-12">Report Date</label>
						<div class="col-sm-12" id="extFilterReportDate"></div>
					</div>
					
					<div class="col-sm-2">
						<label class="control-label col-sm-12">Claim Type</label>
						<div class="col-sm-12 multiple" id="extFiltercategory">
						</div>
					</div>
				
					<div class="col-sm-2 multiple">
						<label class="control-label col-sm-12">Category</label>
						<div class="col-sm-12" id="extFilterRiskRecon">
						</div>
					</div>
				
				
					<div class="col-sm-2">
						<label class="control-label col-sm-12">Roster</label>
						<div class="col-sm-12 multiple" id="extFilterRoster">
							 <select id="clmRoster" style="width:150px;" class="btn btn-default selectAll"  multiple="multiple">
								<option value="Y" selected>Yes</option>
								<option value="N" selected>No</option>
								<option value="NULL" selected>N/A</option>
							</select>	
							
						</div>
					</div>
				</div>	
				<div class="col-sm-12">		
					<div class="col-sm-2 col-sm-offset-8">
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
					<label class="control-label col-sm-12">Cap</label>
						<button type="button" id="claimReportGenerate"
							class="btn btn-success btn-sm btn-sm multiple">Generate</button>
					</div>
				</div>	
			<div class="table-responsive col-sm-12">
				
				
					<div id="demo" class="col-sm-12">
						<div id="tabs" class="claimReportTab">
								<ul>
									<li id="tabLevel1"><a href="#level1" onclick="return hideLevel('tabLevel2', 'tabLevel3', 'tabLevel4',0);">Level 1</a></li>
									<li id="tabLevel2" class="hide"><a href="#level2" onclick="return hideLevel('tabLevel4','tabLevel3',false,1);">Level 2</a></li>
									<li id="tabLevel3" class="hide"><a href="#level3" onclick="return hideLevel('tabLevel4',false, false,2);">Level 3</a></li>
									<li id="tabLevel4" class="hide"><a href="#level4" onclick="return hideLevel(false,false, false,2);">Level 4</a></li>
								</ul>
				
								
								  	<div class="level1" id="level1">   
										<table id="claimReport1" class="table table-hover table-responsive ">
											<thead><tr>
												<th>Risk Category</th>
												<th>201601</th>
												<th>201602</th>
												<th>201603</th>
												<th>201604</th>
												<th>201605</th>
												<th>201606</th>
											</tr></thead>
											<tbody></tbody>
										</table>
									</div>	
									<div class="level2"  id="level2">   
										<table id="claimReport2" class="table table-hover table-responsive">
												<thead><tr><th>Provider</th><th>Report Month</th>
												<th>Activity Month</th><th>Risk Category</th><th>Claim Type</th>
												<th>In Cap</th><th>In Roster</th><th>No Of Visits</th><th>Claims</th>
												<th></th><th></th>
												</tr></thead>
											<tbody></tbody>
										</table>
									</div>	
									<div class="level3"  id="level3">   
										<table id="claimReport3" class="table table-hover table-responsive">
											<thead><tr>
													<th>First Name</th><th>Last Name</th>
													<th>Provider</th><th>Report Month</th>
													<th>Activity Month</th><th>Risk Category</th>
													<th>Claim Type</th><th> In Cap</th>
													<th>In Roster</th><th>No Of Visits</th><th>Claims</th><th></th><th></th><th></th>
											
											</tr></thead>
											<tbody></tbody>
										</table>
									</div>	
									<div class="level4"  id="level4">   
										<table id="claimReport4" class="table table-hover table-responsive">
											<thead><tr>
													<th>First Name</th><th>Last Name</th>
													<th>Provider</th><th>Report Month</th>
													<th>Activity Month</th><th>Risk Category</th>
													<th>Claim Type</th><th> In Cap</th>
													<th>In Roster</th>
													<th>Date Of Service</th><th>No Of Visits</th>
													<th>Claims</th><th></th><th></th><th></th>
											
											</tr></thead>
											<tbody></tbody>
										</table>
									</div>	
							</div>			
							  	
						</div>
					</div>	
		</div>
	</div>
</div>	
  
 <script>
 
 Number.prototype.formatMoney = function(c, d, t){
	 var n = this, 
	     c = isNaN(c = Math.abs(c)) ? 2 : c, 
	     d = d == undefined ? "." : d, 
	     t = t == undefined ? "," : t, 
	     s = n < 0 ? "-" : "", 
	     i = String(parseInt(n = Math.abs(Number(n) || 0).toFixed(c))), 
	     j = (j = i.length) > 3 ? j % 3 : 0;
	    return s + (j ? i.substr(0, j) + t : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + t) + (c ? d + Math.abs(n - i).toFixed(c).slice(2) : "");
	  };
	  
$('#clmCap,  #clmRoster, #clmType').multiselect({numberDisplayed: 0, 
	 buttonWidth: '150px',
	 includeSelectAllOption: true,
	 templates: {
		 ul: '<ul class="multiselect-container dropdown-menu clmReportDate"></ul>'
	 },
});
function level2(reportMonth, riskRecon, activityMonth){
	var table ="claimReport2";
	$("#tabLevel2").removeClass();
	$( "#tabs" ).tabs({ active: 1});
	
	
	insSelectValue= $("#clmInsu option:selected").val();
	datatableDelete("claimReport2");
	 rosterSelectValue 		= dropDownSelectedValue("clmRoster",false, true);
	 capSelectValue 		= dropDownSelectedValue("clmCap",false, true);
	 claimTypeSelectValue 	= dropDownSelectedValue("clmType",true, false);
	prvdrSelectValue 	    = dropDownSelectedValue("clmPrvdr",false, false);
	   
	var params = { "insId":insSelectValue, "prvdrId":prvdrSelectValue, "mbrId":0, "repMonth":reportMonth, "activityMonth":activityMonth,
			"category":"'"+riskRecon+"'",  "claimType":claimTypeSelectValue,
			"roster":"'"+rosterSelectValue+"'", "cap":"'"+capSelectValue+"'",'levelNo':2,
			"pageSize":500, "pageNo":0};
	
	var str = jQuery.param( params );
	var url =  getContextPath()+'/claimReport/list?'+str;
	
	$.ajax({
		  url: url,
		  dataType: "json",
		})
		.success(function(json)
		{
			$("#"+table+" thead tr").append("<th></th><th></th><th></th><th></th>");
			$("#"+table+" tbody").html('');
	    	$.each(json.data, function (index, key) {
	    		$('#'+table+' tbody ').append("<tr></tr>");
	    		$.each(json.data[index], function (tdindex, tdvalue){
	    			if(tdvalue == null) {  tdvalue ="";  }
	    			if(json.data[index].length == tdindex+1)
	    				$('#'+table+' tbody tr:last').append("<td>$"+(Math.round(tdvalue*100)/100).formatMoney(2, '.', ',')+"</td>");
	    			else
	    				$('#'+table+' tbody tr:last').append("<td>"+tdvalue+"</td>");
	    		});
	    		prvdr_id = json.data[index][1];
	    		reportMonth = json.data[index][2];
	    		activityMonth = json.data[index][3];
	    		riskReconText = json.data[index][4];
	    		
	    		$('#'+table+' tbody tr:last').attr('onclick', 'return level3('+prvdr_id+', "'+riskReconText+'", '+reportMonth.replace('-', '')+', '+activityMonth.replace('-', '')+')');
 			});	

	    	var colLen = json.data[0].length - 2;
 			
 			$('#'+table+'  thead  tr').find('th:gt('+colLen+')').remove();
 			$('#'+table+'  tbody  tr').find('td:eq(1)').remove();
 			$('#'+table+'  tbody  tr').find('td:gt('+colLen+')').remove();
 			$('#'+table).width('100%');
 			aggregate(table, true);
 			datatableCreate(table, " Claim Report ");
 			$("#"+table+" tr").css('cursor', 'pointer');
		})
		.done(function() {
		console.log( " Level 2 finished");
		});
	
	
}

function hideLevel(level1, level2, level3, index){
	if(level1) {  $("#"+level1).addClass("hide");}
	if(level2) { $("#"+level2).addClass("hide");}
	if(level3) { $("#"+level3).addClass("hide");}
	$( "#tabs" ).tabs({ active: index});
	return false;
}

function level3(prvdr_id, riskRecon, reportMonth, activityMonth)
{
	 $("#tabLevel3").removeClass();
	 $( "#tabs" ).tabs({ active: 2});
	 var table = "claimReport3";
	 datatableDelete(table);
	   insSelectValue= $("#clmInsu option:selected").val();
	    $("#clmRiskRecon option").each(function (index, key){
	    	if($(this).text() == riskRecon)
	    		{
	    				riskRecon = $(this).val();
	    		}
	    });
		
		 rosterSelectValue 		= dropDownSelectedValue("clmRoster",false, true);
		 capSelectValue 		= dropDownSelectedValue("clmCap",false, true);
		 claimTypeSelectValue 	= dropDownSelectedValue("clmType",true, false);
		   
		var params = { "insId":insSelectValue, "prvdrId":prvdr_id, "mbrId":0, "repMonth":reportMonth, "activityMonth":activityMonth,
				"category":riskRecon,  "claimType":claimTypeSelectValue,
				"roster":"'"+rosterSelectValue+"'", "cap":"'"+capSelectValue+"'",'levelNo':3,
				"pageSize":500, "pageNo":0};
		
		var str = jQuery.param( params );
		var url =  getContextPath()+'/claimReport/list?'+str;
		
	 	$.ajax({
		  url: url,
		  dataType: "json",
		})
		.success(function(json)
		{
			 $("#"+table+" thead tr").append("<th></th><th></th><th></th><th></th>");
			 $("#"+table+" tbody").html('');
		    	$.each(json.data, function (index, key) {
		    		
		    		$("#"+table+" tbody ").append("<tr></tr>");
		    		$.each(json.data[index], function (tdindex, tdvalue){
		    			if(tdvalue == null) {  tdvalue = "";  }
		    			if(json.data[index].length == tdindex+1)
		    				$('#'+table+' tbody tr:last').append("<td>$"+(Math.round(tdvalue*100)/100).formatMoney(2, '.', ',')+"</td>");
		    			else
		    				$('#'+table+' tbody tr:last').append("<td>"+tdvalue+"</td>");
		    		});
		    		mbr_id  =  json.data[index][4];
		    		prvdr_id = json.data[index][3];
		    		reportMonth = json.data[index][5];
		    		activityMonth = json.data[index][6];
		    		riskReconText = json.data[index][7];
		    		$('#'+table+' tbody tr:last').attr('onclick', 'return level4('+prvdr_id+', "'+riskReconText+'", '+reportMonth.replace('-', '')+', '+activityMonth.replace('-', '')+', '+mbr_id+')');
		    		
	 			});	
		    	
			var colLen = json.data[0].length - 3;
	    	 $('#'+table+'  thead  tr').find('th:gt('+colLen+')').remove();
	    	 $('#'+table+'  tbody  tr').find('td:eq(3)').remove();
	    	 $('#'+table+'  tbody  tr').find('td:eq(3)').remove();
	    	 $('#'+table+'  tbody  tr').find('td:gt('+colLen+')').remove();
	    	 aggregate(table,true);
	    	 datatableCreate(table, "Claim Report ");
	    	 $('#'+table).width('100%');
		})
		.done(function() {
			console.log( " Level 3 finished");
		});
}

function level4(prvdr_id, riskRecon, reportMonth, activityMonth, mbr_id)
{
	 $("#tabLevel4").removeClass();
	 $( "#tabs" ).tabs({ active: 3});
	 var table = "claimReport4";
		
	   insSelectValue= $("#clmInsu option:selected").val();
	    $("#clmRiskRecon option").each(function (index, key){
	    	if($(this).text() == riskRecon)
	    		{
	    				riskRecon = $(this).val();
	    		}
	    });
		
		 rosterSelectValue 		= dropDownSelectedValue("clmRoster",false, true);
		 capSelectValue 		= dropDownSelectedValue("clmCap",false, true);
		 claimTypeSelectValue 	= dropDownSelectedValue("clmType",true, false);
		   
		var params = { "insId":insSelectValue, "prvdrId":prvdr_id, "mbrId":mbr_id, "repMonth":reportMonth, "activityMonth":activityMonth,
				"category":riskRecon,  "claimType":claimTypeSelectValue,
				"roster":"'"+rosterSelectValue+"'", "cap":"'"+capSelectValue+"'",'levelNo':4,
				"pageSize":500, "pageNo":0};
		
		var str = jQuery.param( params );
		var url =  getContextPath()+'/claimReport/list?'+str;
		datatableDelete(table);
	 	$.ajax({
		  url: url,
		  dataType: "json",
		})
		.success(function(json)
		{
			 $("#"+table+" thead tr").append("<th></th><th></th><th></th><th></th>");
			 $("#"+table+" tbody").html('');
		    	$.each(json.data, function (index, key) {
		    		
		    		$("#"+table+" tbody ").append("<tr></tr>");
		    		$.each(json.data[index], function (tdindex, tdvalue){
		    			if(tdvalue == null) {  tdvalue = "";  }
		    			if(json.data[index].length == tdindex+1)
		    				$('#'+table+' tbody tr:last').append("<td>$"+(Math.round(tdvalue*100)/100).formatMoney(2, '.', ',')+"</td>");
		    			else
		    				$('#'+table+' tbody tr:last').append("<td>"+tdvalue+"</td>");
		    		});
		    		mbr_id  =  json.data[index][4];
		    		prvdr_id = json.data[index][3];
		    		reportMonth = json.data[index][5];
		    		activityMonth = json.data[index][6];
		    		riskReconText = json.data[index][7];
		    		$('#'+table+' tbody tr:last').attr('onclick', 'return level4('+prvdr_id+', "'+riskReconText+'", '+reportMonth+', '+activityMonth+', '+mbr_id+')');
		    		
	 			});	
		    	
			var colLen = json.data[0].length - 3;
	    	 $('#'+table+'  thead  tr').find('th:gt('+colLen+')').remove();
	    	 $('#'+table+'  tbody  tr').find('td:eq(3)').remove();
	    	 $('#'+table+'  tbody  tr').find('td:eq(3)').remove();
	    	 $('#'+table+'  tbody  tr').find('td:gt('+colLen+')').remove();
	    	 aggregate(table,true);
	    	 datatableCreate(table, "Claim Report ");
	    	 $('#'+table).width('100%');
		})
		.done(function() {
			console.log( " Level 4 finished");
		});
}


function aggregate(table, newRow)
{
	var total = 0;
	if(newRow){
		var tbody = $('#'+table+' tbody tr');
		 total = 0;
		 $.each(tbody, function()
		 {
			 var myText = $(this).find('td:last').text();
			 myText =  myText.replace('$','') ;
			 myText = myText.replace(',','');
			 total = total +  parseFloat(myText); 
		 });
		 $trLast =  $("#"+table+" tbody").find("tr:last");
		 $trNew = $trLast.clone();
		 $trLast.after($trNew);
		 total =   Math.round(total* 100) / 100;
		 $('#'+table+' tbody  tr:last').find('td').text("");
		 $('#'+table+' tbody  tr:last').find('td:last').text('$'+ (Math.round(total*100)/100).formatMoney(2, '.', ',') );
	}
	else{
		var tbody = $('#'+table+' tbody tr');
		var td = $('#'+table+' tbody tr td');
		
		$.each(tbody, function(trindex, trvalue)
		{
			total = 0;
			 $.each($("#"+table+" tbody tr:eq("+trindex+") td"), function(index, value)
			 {
				 if(index > 1)
				 	{ total = total +  parseFloat($(this).text());   };
			 });
			 
			 $("#"+table+" tbody tr:eq("+trindex+") td:last").text(total);
		});		
	}
}

function dropDownSelectedValue(elementId, text, index){
	var arrayList = new Array();
	$("#"+elementId+" option:selected").each(function() {
		if(text)
		{
			if(index)
				arrayList.push ("'"+ $(this).val()+"'");
			else
				arrayList.push ("'"+ $(this).text()+"'");
		}	
		else
			arrayList.push ($(this).val());
	    });
	 return arrayList.join(", ")
}

function datatableDelete(table){
	if ( $.fn.DataTable.isDataTable('#'+table) ) {
		$('#'+table).DataTable().destroy();
	}
    $('#'+table+' tbody').empty();
  
}

function datatableCreate(table, caption)
{
	
	
	
	  var oTable = $('#'+table).DataTable( {
		dom: 'Bfrtip',
		"buttons": [
   	             {
   	                 extend: 'excelHtml5',
   	                 title: caption+' Excel Export',
   	             },
   	             {
						    extend: 'pdf',
						    orientation: 'landscape',
						    pageSize: 'TABLOID',
						    title: caption+' PDF Export',
						}
   	             
                  ],
                  fixedColumns:   {
                      leftColumns: 2
                  },
        scrollY:        "450px",
        scrollX:        true,
        scrollCollapse: false,
        paging:         false,
        "iDisplayLength": 500,
	    "sPaginationType": "full_numbers",
	    "order": [[ 1, "desc" ]],
	    "initComplete": function(settings, json) {
	    
	    $('#'+table).width('100%');
	    }
    } );
}

$('#clmRoster, #clmCap').multiselect({numberDisplayed: 0, 
	 buttonWidth: '150px',
	 includeSelectAllOption: true
	 
});
</script>

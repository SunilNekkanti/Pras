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
			 reportDateDropdown();
		 });
		 
		 
		 var reportDateDropdown = function(){
			 var insSelectValue = Cookies.get('clmInsu');
				
			 if(insSelectValue != undefined)
					insSelectValue = insSelectValue;
				else{
					insSelectValue= $("#clmInsu option:selected").val();
					Cookies.set('clmInsu', insSelectValue,{path: cookiePath });
				}
			 prvdrSelectValue= 9999;
			 var params = { insId:insSelectValue, prvdrId:prvdrSelectValue, pageNo:0, pageSize:200 };
	 	    	var str = jQuery.param( params );
	 	    
	 	    	  $.getJSON(getContextPath()+'/mlrReportDate/list?'+str, function(data){
					    
					  //clear the current content of the select
					   var $selectReportDat = $('#extFilterReportDate');
					     var s = $('<select id=\"clmReportDate\" style=\"width:150px;\" class=\"btn btn-default selectAll\" multiple=\"multiple\">');
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
					   var s = $('<select id=\"clmType\" style=\"width:150px;\" class=\"btn btn-default selectAll\" multiple=\"multiple\">');
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
					
		    	 });
	 	    	  
	 	    	 $.getJSON(getContextPath()+'/riskRecon/list?pageNo=0&pageSize=200', function(data){
					  //clear the current content of the select
					   var $selectReportDat = $('#extFilterRiskRecon');
					   var s = $('<select id=\"clmRiskRecon\" style=\"width:150px;\" class=\"btn btn-default selectAll\" multiple=\"multiple\">');
					     //iterate over the data and append a select option
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
					     
				 }).success(function() { 
					 
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
      		   prvdrSelectValue			= 9999;
      		   reportDateSelectValue 	= dropDownSelectedValue("clmReportDate",true, false);
      		   rosterSelectValue 		= dropDownSelectedValue("clmRoster",false, true);
      		   capSelectValue 			= dropDownSelectedValue("clmCap",false, true);
      		   claimTypeSelectValue 	= dropDownSelectedValue("clmType",true, false);
      		   categorySelectValue 		= dropDownSelectedValue("clmRiskRecon",false, true);
    		   
      		   datatableDelete("claimReport1");
    		   GetClaimReport(insSelectValue,9999,reportDateSelectValue, claimTypeSelectValue, categorySelectValue, rosterSelectValue, capSelectValue,  columns);
    	}  
    	  
    	 
    	     
    	$(document.body).on('change',"#clmInsu",function (e) {
    		Cookies.set('clmInsu', $("#clmInsu option:selected").val(),{path: cookiePath });
    		datatableDelete("claimReport1");
  		});
    	
    	
    	$(document.body).on('change',"#clmType",function (e) {
    		 datatableDelete("claimReport1");
  		});
    	
     	
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
	     	    	$('#claimReport1 tbody tr:first').remove();
	     	    	all = $('#claimReport1  tbody  tr');	
	     			th = $('#claimReport1 > thead > tr > th');	
	     			var val = ""; var count = 0;
	     		    var riskRecon = "";
	     		    var riskReconList = new Array();
	     		    
	     		    var riskReconId =""; var activityMonth =""; 
	     			$("#claimReport1 tr").each(function (index, key) {
	     				$(this).find('td').each(function(tdindex, tdvalue){
	     					if(tdindex == 4)
	     					{	riskRecon = $(this).text();  }
	     			        if(tdindex > 4){
	     			        	activityMonth = $('#claimReport1 tr th:eq('+tdindex+')').text();
	     			        	$('#clmRiskRecon option').each(function() {
	     			        	    if($(this).text() == riskRecon) {
	     			        	    	riskReconId = $(this).val();
	     			        	    	
	     			        	    }
	     			        	});
	     			    	   $(this).html('<a href="javascript:void(0)"  onclick="return level2('+index+','+riskReconId+','+activityMonth+');">'+$(this).text()+'</a>');
	     			        }
	     			    })
	     			});
	     			var colLen = json.data[0].length -1;
	     			
	     			$('#claimReport1  tbody  tr').find('td:gt('+colLen+')').remove();
	     			$('#claimReport1  thead  tr').find('th:gt('+colLen+')').remove(); 
	     			$('#claimReport1  tbody  tr').find('td:lt(3)').remove();
	     			$('#claimReport1  thead  tr').find('th:lt(3)').remove();
	     			
	     	/*		var claimReport1  = $('#claimReport1').html();
	     			datatableDelete("claimReport1");
	     			$('#claimReport1').html(claimReport1);
	     			 datatableCreate("claimReport1", 'Claim Report');
	     		*/	
     	    	}
     	    	else{
     	    		 	datatableDelete("claimReport1");
     	    		 	$('#claimReport1  thead tr').find('th:gt(3)').remove();
     	    			$('#claimReport1').DataTable();
					}
     		    	
	     	 },
	     	
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
			Medical Loss Ratio <span class="clrRed"> </span>
		</div>
		<div class="panel-body">
			<div class="table-responsive">
				<div class="col-sm-12">
					<div class="col-sm-2 multiple">
						<label class="control-label col-sm-12">Insurance</label>
						<div class=" col-sm-12" id="extFilterIns"></div>
					</div>
					<div class="col-sm-2" style="display:none;">
						<label class="control-label col-sm-12" >Provider</label>
						<div class="col-sm-12" id="extFilterPrvdr"></div>
					</div>
					
					<div class="col-sm-2 multiple">
						<label class="control-label col-sm-12">Report Date</label>
						<div class="col-sm-12" id="extFilterReportDate"></div>
					</div>
					
					<div class="col-sm-2 ">
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
								<option value="NULL" selected>NULL</option>
							</select>	
							
						</div>
					</div>
					
					<div class="col-sm-2">
						<label class="control-label col-sm-12">Cap</label>
						<div class="col-sm-12 multiple" id="extFilterCap">
							 <select id="clmCap" style="width:150px;" class="btn btn-default selectAll"  multiple="multiple">
								<option value="Y" selected>Yes</option>
								<option value="N" selected>No</option>
								<option value="NULL" selected>NULL</option>
							</select>	
							
						</div>
					</div>
					
				</div>	
				
					<div class="col-sm-12 text-right">
						<button type="button" id="claimReportGenerate"
							class="btn btn-success btn-sm btn-xs">Generate</button>
					</div>
					
					<div id="demo" class="col-sm-12">
						<div id="tabs">
								<ul>
									<li id="tabLevel1"><a href="#level1" onclick="return hideLevel('tabLevel2', 'tabLevel3',0);">Level 1</a></li>
									<li id="tabLevel2" class="hide"><a href="#level2" onclick="return hideLevel('tabLevel3',false,1);">Level 2</a></li>
									<li id="tabLevel3" class="hide"><a href="#level3" onclick="return hideLevel(false, false,2);">Level 3</a></li>
								</ul>
				
								
								  	<div class="level1" id="level1">   
										<table id="claimReport1" class="table table-hover table-responsive">
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
												<th>Is Cap</th><th>Is Roster</th><th>Claims</th>
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
													<th>Claim Type</th><th> Is Cap</th>
													<th>Is Roster</th><th>Claims</th><th></th><th></th><th></th>
											
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
$('#clmCap,  #clmRoster, #clmType').multiselect({numberDisplayed: 0, 
	 buttonWidth: '150px',
	 includeSelectAllOption: true,
	 templates: {
		 ul: '<ul class="multiselect-container dropdown-menu clmReportDate"></ul>'
	 },
});
function level2(index, riskRecon, activityMonth){
	
	$("#tabLevel2").removeClass();
	$( "#tabs" ).tabs({ active: 1});
	
	
	insSelectValue= $("#clmInsu option:selected").val();
	datatableDelete("claimReport2");
	var reportMonth = "";
	th = $('#claimReport1 > thead > tr > th');
	th.each(function(thindex, thtext)
  	{
		if(thindex == index-1)
			{
				 reportMonth = $('#claimReport1 tbody tr:nth-child('+index+') td:eq(0)').text();;
			}
	});
	
	 rosterSelectValue 		= dropDownSelectedValue("clmRoster",false, true);
	 capSelectValue 		= dropDownSelectedValue("clmCap",false, true);
	 claimTypeSelectValue 	= dropDownSelectedValue("clmType",true, false);
	   
	   
	var params = { "insId":insSelectValue, "prvdrId":9999, "mbrId":0, "repMonth":reportMonth, "activityMonth":activityMonth,
			"category":"'"+riskRecon+"'",  "claimType":claimTypeSelectValue,
			"roster":"'"+rosterSelectValue+"'", "cap":"'"+capSelectValue+"'",'levelNo':2,
			"pageSize":500, "pageNo":0};
	
	var str = jQuery.param( params );
	var url =  getContextPath()+'/claimReport/list?'+str;
	
	$("#claimReport2 thead tr").append("<th></th><th></th><th></th><th></th>");
	
	$('#claimReport2').dataTable( {
        "ajax":url,
        "iDisplayLength": 500,
	    "sPaginationType": "full_numbers",
	    "initComplete": function(settings, json) {
	    	
	    	$("#claimReport2 tr").each(function (index, key) {
	    		prvdr_id = "";
 				$(this).find('td').each(function(tdindex, tdvalue){
 			        if(tdindex == 0){
 			        	prvdr_id = $("#claimReport2").find("tr:eq("+index+")").find("td:eq(1)").text();
 			        	$(this).html('<a href="javascript:void(0)"  onclick="return level3('+index+','+prvdr_id+');">'+$(this).text()+'</a>');
 			        }
 			    })
 			    $(this).attr('onclick', 'return level3('+index+','+prvdr_id+')');
 			});	
	    	var colLen = json.data[0].length - 2;
 			
 			$('#claimReport2  thead  tr').find('th:gt('+colLen+')').remove();
 			$('#claimReport2  tbody  tr').find('td:eq(1)').remove();
 			$('#claimReport2  tbody  tr').find('td:gt('+colLen+')').remove();
 			$('#claimReport2').width('100%');
 			aggregate("claimReport2",true);
 			 $("#claimReport2 tr").css('cursor', 'pointer');
     	 }
    } );
	
}

function hideLevel(level1, level2,index){
	if(level1) {  $("#"+level1).addClass("hide");}
	if(level2) { $("#"+level2).addClass("hide");}
	$( "#tabs" ).tabs({ active: index});
	return false;
}

function level3(index, prvdr_id)
{
	 $("#tabLevel3").removeClass();
	 $( "#tabs" ).tabs({ active: 2});
	 $("#claimReport3 thead tr").append("<th></th><th></th><th></th><th></th>");
	
	   insSelectValue= $("#clmInsu option:selected").val();
	   datatableDelete("claimReport3");
	    riskRecon  		= $('#claimReport2 tbody tr:nth-child('+index+') td:eq(3)').text();
	    
	    $("#clmRiskRecon option").each(function (index, key){
	    	if($(this).text() == riskRecon)
	    		{
	    				riskRecon = $(this).val();
	    		}
	    });
	    
		reportMonth 	= $('#claimReport2 tbody tr:nth-child('+index+') td:eq(1)').text();
		activityMonth 	= $('#claimReport2 tbody tr:nth-child('+index+') td:eq(2)').text();
		
		 rosterSelectValue 		= dropDownSelectedValue("clmRoster",false, true);
		 capSelectValue 		= dropDownSelectedValue("clmCap",false, true);
		 claimTypeSelectValue 	= dropDownSelectedValue("clmType",true, false);
		   
		var params = { "insId":insSelectValue, "prvdrId":prvdr_id, "mbrId":0, "repMonth":reportMonth, "activityMonth":activityMonth,
				"category":riskRecon,  "claimType":claimTypeSelectValue,
				"roster":"'"+rosterSelectValue+"'", "cap":"'"+capSelectValue+"'",'levelNo':3,
				"pageSize":500, "pageNo":0};
		
		var str = jQuery.param( params );
		var url =  getContextPath()+'/claimReport/list?'+str;
		
		$('#claimReport3').dataTable( {
	        "ajax":url,
	        "iDisplayLength": 500,
		    "sPaginationType": "full_numbers",
		    "initComplete": function(settings, json) {
		    	var colLen = json.data[0].length - 3;
		    	 $('#claimReport3  thead  tr').find('th:gt('+colLen+')').remove();
		    	 $('#claimReport3  tbody  tr').find('td:eq(3)').remove();
		    	 $('#claimReport3  tbody  tr').find('td:eq(3)').remove();
		    	 $('#claimReport3  tbody  tr').find('td:gt('+colLen+')').remove();
		    	 aggregate("claimReport3",true);
		    	 $('#claimReport3').width('100%');
		    }
	    } );
		
}

function aggregate(table, newRow)
{
	if(newRow){
		var tbody = $('#'+table+' tbody tr');
		var total = 0;
		 $.each(tbody, function()
		 {
			 total = total +  parseFloat($(this).find('td:last').text()); 
		 });
		 $trLast =  $("#"+table+" tbody").find("tr:last");
		 $trNew = $trLast.clone();
		 $trLast.after($trNew);
		 total =   Math.round(total* 100) / 100;
		 $('#'+table+' tbody  tr:last').find('td').text("");
		 $('#'+table+' tbody  tr:last').find('td:last').text(total);
	}
	else{
		var tbody = $('#'+table+' tbody tr');
		var td = $('#'+table+' tbody tr td');
		var total = 0;
		$.each(tbody, function(trindex, trvalue)
		{
			 $.each($("#"+table+" tbody tr:eq("+trindex+") td"), function(index, value)
			 {
				 if(index > 1)
				 	{ total = total +  parseFloat($(this).text());   };
			 });
			 
			 $("#"+table+" tbody tr:eq("+trindex+")").append("<td>"+total+"</td>");
		});		
		$("#"+table+" thead tr:last").append("<th>Total</th>");
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
	$('#'+table).DataTable( {
		dom: 'Bfrtip',
		"buttons": [
   	             {
   	                 extend: 'excelHtml5',
   	                 title: caption+' Excel Export',
   	             },
   	             {
						    extend: 'pdfHtml5',
						    orientation: 'landscape',
						    pageSize: 'LEGAL',
						    title: caption+' PDF Export',
						}
   	             
                  ],
        "scrollX": true,
        "iDisplayLength": 500,
	    "sPaginationType": "full_numbers",
	    "initComplete": function(settings, json) {
	    	 aggregate(table, false);
	    	 $('#'+table).width('100%');
	    }
    } );
}

$('#clmRoster, #clmCap').multiselect({numberDisplayed: 0, 
	 buttonWidth: '150px',
	 includeSelectAllOption: true
	 
});
</script>

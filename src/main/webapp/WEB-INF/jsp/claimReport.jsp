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
<script type="text/javascript"
	src="http://cdn.datatables.net/plug-ins/1.10.12/api/average().js"></script>
<script>
$(document).ready(function() {
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
					insSelectValue= $("#clmInsu option:selected").val();
					Cookies.set('clmInsu', insSelectValue,{path: cookiePath });
				}
			     
				$('select[id="clmInsu"]').val(insSelectValue);
 			 var $selectPrvdr = $('#extFilterPrvdr');
 	    	  $.getJSON(getContextPath()+'/insurance/providerlist?insId='+insSelectValue, function(data){
 				    
 				     //clear the current content of the select
 				     var s = $('<select id=\"clmPrvdr\" style=\"width:150px;\" class=\"btn btn-default\">');
 				     //iterate over the data and append a select option
 				     $.each(data.data.list, function(key, val){
 				    	 
 				    	 s.append('<option value="'+val.id+'">' + val.name +'</option>');
 				     });
 				     s.append('<option value="9999">All</option>');
 				     s.append('</select>');
 				     $selectPrvdr.html(s);
 			 }).success(function() { 
 				var prvdrSelectValue = Cookies.get('clmPrvdr');
				 if(prvdrSelectValue != undefined) 
					 prvdrSelectValue = prvdrSelectValue;
				 else{
					prvdrSelectValue= $("#clmPrvdr option:selected").val();
					Cookies.set('clmPrvdr', prvdrSelectValue,{path: cookiePath });
				 }	
				$('select[id="clmPrvdr"]').val(prvdrSelectValue);
				reportDateDropdown();
 	    	
 			}); 
    	  }
		 
		 
		 var reportDateDropdown = function(){
			 insSelectValue= $("#clmInsu option:selected").val();
			 prvdrSelectValue= $("#clmPrvdr option:selected").val();
			 var params = { insId:insSelectValue, prvdrId:prvdrSelectValue, pageNo:0, pageSize:200 };
	 	    	var str = jQuery.param( params );
	 	    
	 	    	  $.getJSON(getContextPath()+'/mlrReportDate/list?'+str, function(data){
					    
					  //clear the current content of the select
					   var $selectReportDat = $('#extFilterReportDate');
					     var s = $('<select id=\"clmReportDate\" style=\"width:150px;\" class=\"btn btn-default\" multiple=\"multiple\">');
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
					    	 includeSelectAllOption: true,
					    	 templates: {
								 ul: '<ul class="multiselect-container dropdown-menu clmReportDate"></ul>'
							 },
					    });
					     
					    if(data.data.list.length < 1)
					    	 	$("#claimReportGenerate").hide();
					     else
					    	 $("#claimReportGenerate").show();
					     
				 }).success(function() { 
					reportDateSelectValue= $("#clmReportDate option:selected").val();
					$('select[id="clmReportDate"]').val(reportDateSelectValue);
		    	 });
	 	    	  
	 	    	  
	 	    	 $.getJSON(getContextPath()+'/riskRecon/list?pageNo=0&pageSize=200', function(data){
					  //clear the current content of the select
					   var $selectReportDat = $('#extFilterRiskRecon');
					   var s = $('<select id=\"clmRiskRecon\" style=\"width:150px;\" class=\"btn btn-default\">');
					     //iterate over the data and append a select option
					     $.each(data.data.list, function(key, val){
					    	 if(key == 0) {
					    		 s.append('<option value="'+val.id+'" Selected>' + val.name +'</option>');
					    	 }  else {
					    		 s.append('<option value="'+val.id+'">' + val.name +'</option>');
					    	 }
					    	 
					     });
					     s.append('<option value="9999">All</option>');
					     s.append('</select>');
					     $selectReportDat.html(s);
					    
					     
					    if(data.data.list.length < 1)
					    	 	$("#claimReportGenerate").hide();
					     else
					    	 $("#claimReportGenerate").show();
					     
				 }).success(function() { 
					 riskReconSelectValue= $("#clmRiskRecon option:selected").val();
					 $('select[id="clmRiskRecon"]').val(riskReconSelectValue);
		    	 });
	 	    	 
	 	    	 
	 	    	 
	 	    	var $selectPrvdr = $('#extFilterRosterCap');
	 				    
	 				     //clear the current content of the select
	 				     var s = $('<select id=\"clmRosterCap\" style=\"width:150px;\" class=\"btn btn-default\" multiple=\"multiple\">');
	 				     //iterate over the data and append a select option
	 				     s.append('<option value="isRoster">Roster</option>');
	 				     s.append('<option value="isCap">Cap</option>');
	 				     s.append('</select>');
	 				     $selectPrvdr.html(s);
	 				     
	 				    $('#clmRosterCap').multiselect({numberDisplayed: 0, 
					    	 buttonWidth: '150px',
					    	 includeSelectAllOption: true,
					    	 templates: {
								 ul: '<ul class="multiselect-container dropdown-menu clmReportDate"></ul>'
							 },
					    });
		 }
    	  
    	  var columns ;
    	  var callClaimReportGenerate = function(){
    		  var rowHeader = $('#claimReport thead tr');
    			for(var col = 0; col<50; col++)
    				rowHeader.append('<th scope="col" role="row" class="hide"></th>');
    			
				columns = new Array();
	     		columns.push({ "mDataProp": "reportMonth","bSearchable" : true, "bSortable": true,"sClass": "center widthS","sWidth" : "10%"  });
	     		columns.push({ "mDataProp": "activityMonth","bSearchable" : true, "bSortable": true,"sClass": "center widthS","sWidth" : "10%"  });
	     		columns.push({ "mDataProp": "patients","bSearchable" : true, "bSortable": true,"sClass": "center widthS","sWidth" : "10%"  });
	     		columns.push({ "mDataProp": "fund","bSearchable" : true, "bSortable": true,"sClass": "center widthS","sWidth" : "10%"  });
	     		columns.push({ "mDataProp": "prof","bSearchable" : true, "bSortable": true,"sClass": "center widthS","sWidth" : "10%"  });
	     		columns.push({ "mDataProp": "inst","bSearchable" : true, "bSortable": true,"sClass": "center widthS","sWidth" : "10%"  });
	     		columns.push({ "mDataProp": "pharmacy","bSearchable" : true, "bSortable": true,"sClass": "center widthS","sWidth" : "10%"  });
	     		var myTable = $("#claimReport");
	     		var thead = myTable.find("thead");  
	     		
	     		$("#claimReport th").each(function() {
					if($(this).attr("role") == "row"){
					}else{
						$(this).remove();
					}
							
       			}); 
	     		callDatableWithChangedDropDown();
    	  }
    	  
    	  function callDatableWithChangedDropDown(){
    		   var insSelectValue= $("#clmInsu option:selected").val();
    		   var prvdrSelectValue= $("#clmPrvdr option:selected").val();
    		   var reportDateSelectValue= $("#clmReportDate option:selected").val();
    		   var rosterCapSelectValue= $("#clmRosterCap option:selected").val();
    		   var categorySelectValue = $("#clmRiskRecon option:selected").val();
    		   var ruleArray = new Array;
    		   $("#clmReportDate option:selected").each  ( function() {
    		    	ruleArray.push ( $(this).val()  );
    		    });
    		   reportDateSelectValue = ruleArray.join(", ");
    		  
    		   var rosterCapArray = new Array;
    		   $("#clmRosterCap option:selected").each  ( function() {
    			   rosterCapArray.push ( $(this).val()  );
    		    });
    		   rosterCapSelectValue = rosterCapArray.join(", ");
    		   
    		   alert(rosterCapSelectValue);
    		   
    		   
    		   if ( $.fn.DataTable.isDataTable('#claimReport') ) {
  						$('#claimReport').DataTable().destroy();
			   }
    		  
				$('#claimReport tbody').empty();
    		   GetMembershipByInsPrvdr(insSelectValue,prvdrSelectValue,reportDateSelectValue, categorySelectValue, rosterCapSelectValue, columns);
    	}  
    	     
    	$(document.body).on('change',"#clmInsu",function (e) {
    		Cookies.set('clmInsu', $("#clmInsu option:selected").val(),{path: cookiePath });
    		Cookies.remove('clmPrvdr',{path: cookiePath });
    		
    		if ( $.fn.DataTable.isDataTable('#claimReport') ) {
					$('#claimReport').DataTable().destroy();
	   		}
    		$('#claimReport tbody').empty();
    		providerDropdown();
  		});
    	
    	$(document.body).on('change',"#clmPrvdr",function (e) {
    		Cookies.set('clmPrvdr', $("#clmPrvdr option:selected").val(),{path: cookiePath });
    		if ( $.fn.DataTable.isDataTable('#claimReport') ) {
					$('#claimReport').DataTable().destroy();
	   		}
    		$('#claimReport tbody').empty();
    		
    		reportDateDropdown();
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
		   var sSearchGenerateDate = paramMap.sSearchGenerateDate;
		   var sSearchCategory = paramMap.sSearchCategory;
		   var sSearchRosterCap= paramMap.sSearchRosterCap;
		   
		   //create new json structure for parameters for REST request
		   var restParams = new Array();
		   restParams.push({"name" : "pageSize", "value" : pageSize});
		   restParams.push({"name" : "pageNo", "value" : pageNum });
		   restParams.push({"name" : "sort", "value" : "reportMonth" });
		   restParams.push({"name" : "sortdir", "value" : sortDir });
		   restParams.push({"name" : "sSearch" , "value" : paramMap.sSearch  });
		   restParams.push({"name" : "insId" , "value" : sSearchIns  });
		   restParams.push({"name" : "prvdrId" , "value" : sSearchPrvdr  });
		   restParams.push({"name" : "repMonth" , "value" : sSearchGenerateDate  });
		   restParams.push({"name" : "category" , "value" : sSearchCategory  });
		   restParams.push({"name" : "rosterCap" , "value" : sSearchRosterCap  });
		   
		   if($( window ).width() > 900){
				 var width;
				 width = $('#claimReport th').length * 120;
				 if(width > 1200){
					 width = width + "px";
					 $('#claimReport').width(width);
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
            	  var th =  $("#claimReport thead tr th").length;
            	  $("#claimReport thead tr").html('');
            	  jQuery.each(res.data, function( i, val ) {
             		 if(i == 0){
             			 jQuery.each(val, function( index, text ) {
             					$("#claimReport thead tr").append('<th scope="col" role="row" tabindex="0" aria-controls="claimReport" rowspan="1" colspan="1" aria-sort="ascending" >'+text+'</th>');
             			 });
             		 }
             		
             		});
            	  if(th > td)
            		  {
            		  	for(i = td; i < th; i++){
            		  		$("#claimReport thead tr").append('<th scope="col" class="hide" role="row" tabindex="0" aria-controls="claimReport" rowspan="1" colspan="1" aria-sort="ascending" ></th>');
            		  	}
            		  }
             	     	  
             	  //clear the current content of the select
                   res.iTotalRecords = res.data.length-1;
                   res.iTotalDisplayRecords = res.data.length-1;
                   fnCallback(res);
             		
            },
              error : function (e) {
              }
          } );
     	}
     	
     	
     	  GetMembershipByInsPrvdr = function (insId, prvdrId, generateDate, category, rosterCap,aoColumns) {
  	        var oTable = $('#claimReport').removeAttr( "width" ).dataTable({  
  	        	"sDom": 'Bfrtip',
	        	 "buttons": [
	        	             {
	        	                 extend: 'excelHtml5',
	        	                
	        	                 title: 'Medical Loss Ratio Table Excel Export',
	        	                 exportOptions: {
	        	                   
	        	                 }
	        	             },
	        	             {
								    extend: 'pdfHtml5',
								    orientation: 'landscape',
								    pageSize: 'LEGAL',
								     title: 'Medical Loss Ratio Table PDF Export',
								     mColumns: [2, 3, 4]
								}
	        	             
		                   ],
  	         "bDestroy" : true,	
     	     "sAjaxSource" : getContextPath()+'/claimReport/list',
     	     "sAjaxDataProp" : 'data',
     	     "bLengthChange": false,
     	     "bPagination":false,
     	     "iDisplayLength": 500,
     	     "sPaginationType": "full_numbers",
     	     "bProcessing": true,
     	     "bServerSide" : true,
     	     "initComplete": function(settings, json) {
		    	     reDraw();
	     	 },
	     	
	        "fnServerParams": function ( aoData ) {
                aoData.push(
                    {"name": "sSearchIns", "value": insId},
                    {"name": "sSearchPrvdr", "value": prvdrId },
                    {"name": "sSearchGenerateDate", "value": generateDate },
                    {"name": "sSearchCategory", "value": category },
                    {"name": "sSearchRosterCap", "value": rosterCap }
                );
             },        
             "fnDrawCallback": function () {
            	 var $paginate = this.siblings('.dataTables_paginate');

 	     	    if (this.api().data().length <= this.fnSettings()._iDisplayLength){
 	     	        $paginate.hide();
 	     	    }
 	     	    else{
 	     	        $paginate.show();
 	     	    }
 	     	    
     	    	
	    		 
	    		if($( window ).width() > 900){
    				 var width;
    				 width = $('#claimReport tbody tr:eq(0) td').length * 120;
    				 if(width > 1500){
    					 width = width + "px";
    					 $('#claimReport').width(width);
    				 }
    				 else{
    					 $('#claimReport').width("100%");
    				 }
    			} 
	    		
             },
     	     "fnServerData" : datatable2RestMembership
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
					<div class="col-sm-2">
						<label class="control-label col-sm-12">Insurance</label>
						<div class=" col-sm-12" id="extFilterIns"></div>
					</div>
					<div class="col-sm-2">
						<label class="control-label col-sm-12">Provider</label>
						<div class="col-sm-12" id="extFilterPrvdr"></div>
					</div>
					
					<div class="col-sm-2">
						<label class="control-label col-sm-12">Report Date</label>
						<div class="col-sm-12" id="extFilterReportDate"></div>
					</div>
					
					<div class="col-sm-2">
						<label class="control-label col-sm-12">Category</label>
						<div class="col-sm-12" id="extFilterRiskRecon">
						</div>
					</div>
					
					<div class="col-sm-2">
						<label class="control-label col-sm-12">Roster / Cap</label>
						<div class="col-sm-12" id="extFilterRosterCap">
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
									<li><a href="#details">Detailed</a></li>
								</ul>
								<div id="details" class="col-sm-12 table-responsive">
									<table id="claimReport" class="table table-hover table-responsive">
										<thead><tr></tr></thead>
										<tbody></tbody>
									</table>
							  	</div>	
						</div>
				</div>
			</div>
		</div>
	</div>
</div>	
  



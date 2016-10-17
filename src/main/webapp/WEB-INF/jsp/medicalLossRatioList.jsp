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
		
		$("#medicalLossRatioGenerate").click(function(event)
		{
			if($("#mlrReportDate").val() == null)
				{
				 alert(" Select Report Date");
				  return false;
				}
			callmedicalLossRatioGenerate();
			$( "#tabs" ).tabs({ active: 0});
		});
		
		
	
    	 var $selectIns = $('#extFilterIns');
    	  $.getJSON(getContextPath()+'/insurance/list?pageNo=0&pageSize=200', function(data){
			    
			     //clear the current content of the select
			     var s = $('<select id=\"mlrInsu\" style=\"width:150px;\" class=\"btn btn-default\">');
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
			 var insSelectValue = Cookies.get('mlrInsu');
				
			 if(insSelectValue != undefined)
					insSelectValue = insSelectValue;
				else{
					insSelectValue= $("#mlrInsu option:selected").val();
					Cookies.set('mlrInsu', insSelectValue,{path: cookiePath });
				}
			     
				$('select[id="mlrInsu"]').val(insSelectValue);
 			 var $selectPrvdr = $('#extFilterPrvdr');
 	    	  $.getJSON(getContextPath()+'/insurance/providerlist?insId='+insSelectValue, function(data){
 				    
 				     //clear the current content of the select
 				     var s = $('<select id=\"mlrPrvdr\" style=\"width:150px;\" class=\"btn btn-default\">');
 				     //iterate over the data and append a select option
 				     $.each(data.data.list, function(key, val){
 				    	 
 				    	 s.append('<option value="'+val.id+'">' + val.name +'</option>');
 				     });
 				     s.append('<option value="9999">All</option>');
 				     s.append('</select>');
 				     $selectPrvdr.html(s);
 			 }).success(function() { 
 				var prvdrSelectValue = Cookies.get('mlrPrvdr');
				 if(prvdrSelectValue != undefined) 
					 prvdrSelectValue = prvdrSelectValue;
				 else{
					prvdrSelectValue= $("#mlrPrvdr option:selected").val();
					Cookies.set('mlrPrvdr', prvdrSelectValue,{path: cookiePath });
				 }	
				$('select[id="mlrPrvdr"]').val(prvdrSelectValue);
				reportDateDropdown();
 	    	
 			}); 
    	  }
		 
		 
		 var reportDateDropdown = function(){
			 insSelectValue= $("#mlrInsu option:selected").val();
			 prvdrSelectValue= $("#mlrPrvdr option:selected").val();
			 var params = { insId:insSelectValue, prvdrId:prvdrSelectValue, pageNo:0, pageSize:200 };
	 	    	var str = jQuery.param( params );
	 	    
	 	    	  $.getJSON(getContextPath()+'/mlrReportDate/list?'+str, function(data){
					    
					  //clear the current content of the select
					   var $selectReportDat = $('#extFilterReportDate');
					     var s = $('<select id=\"mlrReportDate\" style=\"width:150px;\" class=\"btn btn-default\" multiple=\"multiple\">');
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
					     $('#mlrReportDate').multiselect({numberDisplayed: 0, 
					    	 buttonWidth: '150px',
					    	 includeSelectAllOption: true,
					    	 templates: {
								 ul: '<ul class="multiselect-container dropdown-menu mlrReportDate"></ul>'
							 },
					    });
					     
					     if(data.data.list.length < 1)
					    	 	$("#medicalLossRatioGenerate").hide();
					     else
					    	 $("#medicalLossRatioGenerate").show();
					     
				 }).success(function() { 
					reportDateSelectValue= $("#mlrReportDate option:selected").val();
					$('select[id="mlrReportDate"]').val(reportDateSelectValue);
		    	 });
		 }
    	  
    	  var columns ;
    	  var callmedicalLossRatioGenerate = function(){
    		  
    		  if ( $.fn.DataTable.isDataTable('#medicalLossRatio') ) {
					$('#medicalLossRatio').DataTable().destroy();
		   }
		  
			$('#medicalLossRatio tbody').empty();
			$('#medicalLossRatio thead tr').empty();
    		  var rowHeader = $('#medicalLossRatio thead tr');
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
	     		var myTable = $("#medicalLossRatio");
	     		var thead = myTable.find("thead");  
	     		
	     		$("#medicalLossRatio th").each(function() {
					if($(this).attr("role") == "row"){
					}else{
						$(this).remove();
					}
							
       			}); 
	     		callDatableWithChangedDropDown();
    	  }
    	  
    	  function callDatableWithChangedDropDown(){
    		   var insSelectValue= $("#mlrInsu option:selected").val();
    		   var prvdrSelectValue= $("#mlrPrvdr option:selected").val();
    		   var reportDateSelectValue= $("#mlrReportDate option:selected").val();
    		   var categorySelectValue = $("#mlrCategory option:selected").val();
    		   var ruleArray = new Array;
    		   $("#mlrReportDate option:selected").each  ( function() {
    		    	ruleArray.push ( $(this).val()  );
    		    });
    		   reportDateSelectValue = ruleArray.join(", ");
    		   if ( $.fn.DataTable.isDataTable('#medicalLossRatio') ) {
  						$('#medicalLossRatio').DataTable().destroy();
			   }
    		  
				$('#medicalLossRatio tbody').empty();
    		   GetMembershipByInsPrvdr(insSelectValue,prvdrSelectValue,reportDateSelectValue, categorySelectValue, columns);
    	}  
    	     
    	$(document.body).on('change',"#mlrInsu",function (e) {
    		Cookies.set('mlrInsu', $("#mlrInsu option:selected").val(),{path: cookiePath });
    		Cookies.remove('mlrPrvdr',{path: cookiePath });
    		
    		if ( $.fn.DataTable.isDataTable('#medicalLossRatio') ) {
					$('#medicalLossRatio').DataTable().destroy();
	   		}
    		$('#medicalLossRatio tbody').empty();
    		providerDropdown();
  		});
    	
    	$(document.body).on('change',"#mlrPrvdr",function (e) {
    		Cookies.set('mlrPrvdr', $("#mlrPrvdr option:selected").val(),{path: cookiePath });
    		if ( $.fn.DataTable.isDataTable('#medicalLossRatio') ) {
					$('#medicalLossRatio').DataTable().destroy();
	   		}
    		$('#medicalLossRatio tbody').empty();
    		
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
		   
		   if($( window ).width() > 900){
				 var width;
				 width = $('#medicalLossRatio th').length * 120;
				 if(width > 1200){
					 width = width + "px";
					 $('#medicalLossRatio').width(width);
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
            	  var th =  $("#medicalLossRatio thead tr th").length;
            	  $("#medicalLossRatio thead tr").html('');
            	  jQuery.each(res.data, function( i, val ) {
             		 if(i == 0){
             			 jQuery.each(val, function( index, text ) {
             					$("#medicalLossRatio thead tr").append('<th scope="col" role="row" tabindex="0" aria-controls="medicalLossRatio" rowspan="1" colspan="1" aria-sort="ascending" >'+text+'</th>');
             			 });
             		 }
             		
             		});
            	  if(th > td)
            		  {
            		  	for(i = td; i < th; i++){
            		  		$("#medicalLossRatio thead tr").append('<th scope="col" class="hide" role="row" tabindex="0" aria-controls="medicalLossRatio" rowspan="1" colspan="1" aria-sort="ascending" ></th>');
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
     	
     	
     	  GetMembershipByInsPrvdr = function (insId, prvdrId, generateDate, category, aoColumns) {
  	        var oTable = $('#medicalLossRatio').removeAttr( "width" ).dataTable({  
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
     	     "sAjaxSource" : getContextPath()+'/medicalLossRatio/list',
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
                    {"name": "sSearchCategory", "value": category }
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
    				 width = $('#medicalLossRatio tbody tr:eq(0) td').length * 120;
    				 if(width > 1500){
    					 width = width + "px";
    					 $('#medicalLossRatio').width(width);
    				 }
    				 else{
    					 $('#medicalLossRatio').width("100%");
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
					<div class="col-sm-3">
						<label class="control-label col-sm-12">Insurance</label>
						<div class=" col-sm-12" id="extFilterIns"></div>
					</div>
					<div class="col-sm-3">
						<label class="control-label col-sm-12">Provider</label>
						<div class="col-sm-12" id="extFilterPrvdr"></div>
					</div>
					
					<div class="col-sm-3">
						<label class="control-label col-sm-12">Report Date</label>
						<div class="col-sm-12" id="extFilterReportDate"></div>
					</div>
					
					<div class="col-sm-3">
						<label class="control-label col-sm-12">Category</label>
						<div class="col-sm-12" id="extFiltercategory">
							<select id="mlrCategory" style="width:150px;" class="btn btn-default">
									<option value="9999">All</option>
									<option value="Fund">Fund</option>
									<option value="Inst">Inst</option>
									<option value="Patients">Patients</option>
									<option value="Pharmacy">Pharmacy</option>
									<option value="MLR">MLR</option>
									<option value="QMLR">QMLR</option>
									<option value="Total">Total</option>
									<option value="UNWANTED_CLAIMS">Unwanted Claims</option>
									<option value="STOP_LOSS">Stop Loss</option>
							</select>
						</div>
					</div>
				</div>
					<div class="col-sm-12 text-right">
						<button type="button" id="medicalLossRatioGenerate"
							class="btn btn-success btn-sm btn-xs">Generate</button>
					</div>
				<div id="demo" class="col-sm-12">
						<div id="tabs">
								<ul>
									<li><a href="#details">Detailed</a></li>
									<li class="hide" id="summaryDetails"><a href="#summary" onclick="summary();">Summary</a></li>
								</ul>
								<div id="details" class="col-sm-12 table-responsive">
									<table id="medicalLossRatio" class="table table-hover table-responsive">
										<thead><tr></tr></thead>
										<tbody></tbody>
									</table>
							  	</div>	
								  <div id="summary" class="col-sm-12 table-responsive">
									<table id="medicalLossRatioSummary" class="table table-striped table-hover table-responsive">
										<thead><tr><td></td></tr></thead>
										<tbody></tbody>
									</table>
								</div>
						</div>
				</div>
			</div>
		</div>
	</div>
</div>	
<!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog modal-lg">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Unwanted Claims</h4>
        </div>
        <div class="modal-body">
        	<p class="text-primary info">Your request is processing... please wait</p>
        	<i class="fa fa-spinner fa-spin info" style="font-size:24px"></i>
          	<table id="unwantedClaims" class="display table-responsive  table table-striped table-hover" style="width:100%;">
          			<thead>
          				<tr>
	          				<th> Claim Type </th>
	          				<th> Last Name </th>
	          				<th> First Name </th>
	          				<th> Gender </th>
	          				<th> DOB </th>
	          				<th> Amount </th>
	          			</tr>	
          			</thead>
          			<tbody></tbody>
          	</table>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  
<script>

	function reDraw(){
		
		 var thempty = new Array;
	    	 var activeMonthList = new Array();
	    	 jQuery.each($("#medicalLossRatio thead tr th"), function( index, text ) {
	    		activeMonthList[index] = $(this).text();
	    		 if(index == 0) $(this).addClass("hide");
	    			if($(this).text() == "" || $(this).text() == "null" ){
	    				thempty.push(index);
	    				$(this).addClass("hide");
	    			} 
	    	 });
	    	var unwantedCount = new Array();
	    	var unwanted = stoploss =  total = accumulatedTotal = 0; 
	    	var repMonth =  activityMonth = prvdrName = prvdrId = "";
	    	var mlrCategory = $("#mlrCategory"). val();
	    	
	    	jQuery.each($("#medicalLossRatio tbody tr"), function( index, text ) {
			
	    	 unwanted = stoploss = total = 0;  
			 prvdrName = prvdrId = repMonth = activityMonth="";
			 repMonth = $("#medicalLossRatio tbody tr:eq("+index+") td:eq(2)").text();
			 prvdrName = "'"+$("#medicalLossRatio tbody tr:eq("+index+") td:eq(1)").text()+"'";
			 prvdrId = "'"+$("#medicalLossRatio tbody tr:eq("+index+") td:eq(0)").text()+"'";
			 jQuery.each($("#medicalLossRatio tbody tr:eq("+index+") td"), function( tdindex, tdtext ) {
			 		
			 	 activityMonth = activeMonthList[tdindex];
				if(mlrCategory == 9999){
    			 	 
					if(tdindex == 3 && $(this).text() == "UNWANTED_CLAIMS" && unwanted != 1)
	    				 {
	    					 	unwanted = 1;
	    				 }
    				 else if(tdindex == 3 && $(this).text() == "STOP_LOSS" && stoploss != 1)
	    				 {
    					 stoploss = 1;
	    				 }
    				 else if (tdindex == 3 && $(this).text() == "TOTAL" && total != 1)
    				 {
    					  total = 1; 
    				 }
    				
    				   
    				 if(tdindex > 3 && unwanted == 1){
    					  if(activityMonth != "Total"){
    						  if(unwantedCount[activityMonth] !== undefined && $.trim($(this).text())){ 
    							  unwantedCount[activityMonth] =  parseFloat(unwantedCount[activityMonth]) + parseFloat($(this).text());
    						  }
    						  else if ($.trim($(this).text()) && unwantedCount[activityMonth] === undefined){
    							  unwantedCount[activityMonth] = parseFloat($(this).text());
    						 } 
    						  else if(unwantedCount[activityMonth] !== undefined && !$.trim($(this).text())){ 
    							  unwantedCount[activityMonth] =  unwantedCount[activityMonth];
    						  }
    						  else{
    							  unwantedCount[activityMonth] = 0;
    						  }
 	    					 $(this).html('<a  href="javascript:void(0)" onclick="mlrUnwantedList('+activityMonth+','+repMonth+',true,'+prvdrName+','+prvdrId+');">'+$(this).text()+'</a>');
 	    					
    					  }
    					  else
    						  $(this).html($(this).text());
    				 }
    				 
    				 else if(tdindex > 3 && stoploss == 1){
    					  if(activityMonth != "Total"){
    						  $(this).html('<a  href="javascript:void(0)" onclick="mlrUnwantedList('+activityMonth+','+repMonth+',false,'+prvdrName+','+prvdrId+');">'+$(this).text()+'</a>');
    					  }	 
    					  else
       						  $(this).html($(this).text());
    				 }
    				 
    				 else if(tdindex >3 && total == 1){
    					  if(activityMonth != "Total"){
    						  if(unwantedCount[activityMonth] !== undefined && $.trim($(this).text())){ 
    							  unwantedCount[activityMonth] =  parseFloat(unwantedCount[activityMonth]) + parseFloat($(this).text());
    							 
    						  }
    						  else if ($.trim($(this).text()) && unwantedCount[activityMonth] === undefined){
    							  unwantedCount[activityMonth] = parseFloat($(this).text());
    						 } 
    						 else if(unwantedCount[activityMonth] !== undefined && !$.trim($(this).text())){ 
    							  unwantedCount[activityMonth] =  unwantedCount[activityMonth];
    						 }
    						 else{
    							  unwantedCount[activityMonth] = 0;
    						 }
    					  }	 
    				 }
				}
				else{
					 
    					if(tdindex > 3 && index > 0)
    					{	
    						  if(unwantedCount[activityMonth] !== undefined && $.trim($(this).text())){ 
    							  unwantedCount[activityMonth] =  parseFloat(unwantedCount[activityMonth]) + parseFloat($(this).text());
    						  }
    						  else if ($.trim($(this).text()) && unwantedCount[activityMonth] === undefined){
    							  unwantedCount[activityMonth] = parseFloat($(this).text());
    						 } 
    						 else if(unwantedCount[activityMonth] !== undefined && !$.trim($(this).text())){ 
    							  unwantedCount[activityMonth] =  unwantedCount[activityMonth];
    						 }
    						 else{
    							  unwantedCount[activityMonth] = 0;
    						 }
    				  }
				}		
			 });	
  			 });
	    	
	    	 $("#medicalLossRatio tbody").append("<tr></tr>");
	    	
	    	 if(mlrCategory == "MLR" || mlrCategory == "QMLR"){
 	    	var sum = 0,
  	        count = 0,
  	        all = $('#medicalLossRatio > tbody > tr');
  	    	th = $('#medicalLossRatio > thead > tr > th');	
  	    	th.each(function(thindex, thtext)
  	    	{
  	    		if(thindex > 3 && $(this).text() != "null"){
  	    			sum = 0; count = 0;
	     	    		all.each(function( index, text) {
	         	   			if(index > 0 && (all.length -1) != index && $('td:eq('+thindex+')', this).text() && $('td:eq('+thindex+')', this).text() > 0){
	    		     	       	sum += +$('td:eq('+thindex+')', this).text();
	    		     	       	count++;
	         	   			}
	         	   		});
	     	    		$("#medicalLossRatio tbody tr:last").append('<td>'+(sum / count).toFixed(2)+'</td>');
	     	    		
  	    		}	
  	    		else{
  	    			$("#medicalLossRatio tbody tr:last").append('<td></td>');
  	    		}	
  	    	});
	    	 }
	    	 else
	    	 {
	    			var num;
		    	 jQuery.each($("#medicalLossRatio thead tr th"), function( index, text ) {
		    		if(unwantedCount[$(this).text()] !== undefined  && $(this).text() != "null"){
		    			num = unwantedCount[$(this).text()];
		    			num = Math.round(num * 1000) / 1000;
		    			accumulatedTotal = accumulatedTotal + num;
		    	 		$("#medicalLossRatio tbody tr:last").append('<td>'+num+'</td>');
		    		}		
		    		else if($(this).text() == "Total" && accumulatedTotal > 0){
		    			$("#medicalLossRatio tbody tr:last").append('<td>'+Math.round(accumulatedTotal * 1000) / 1000 +'</td>');
		    		}
		    		else{
		    			$("#medicalLossRatio tbody tr:last").append('<td></td>');
		    		}
		    	
		    	 }); 
	     }	 
 	    	 
		 jQuery.each($("#medicalLossRatio tbody tr"), function( index, text ) {
			 
			 jQuery.each($("#medicalLossRatio tbody tr:eq("+index+") td"), function( tdindex, tdtext ) {
				
				 if(tdindex == 0) $(this).remove();
    		 		if($.inArray(tdindex, thempty) != -1) {
    		 			$(this).remove();
    		 		}
			 });	
    	});
		 
		 $("#medicalLossRatio tbody tr:first").remove();
		 tbody = $("#medicalLossRatio tbody").html();
		 
		 
		if ( $.fn.DataTable.isDataTable('#medicalLossRatio') ) {
			$('#medicalLossRatio').DataTable().destroy();
		}
		
		all = $('#medicalLossRatio > tbody > tr');	
		th = $('#medicalLossRatio > thead > tr > th')
		
		
		
		
		th.each(function(thindex, thtext)
		    	{
					if($(this).attr("class") !== undefined)
						$(this).remove();
		    	});
		header = $('#medicalLossRatio thead').html();
		$("#medicalLossRatio thead").html($('#medicalLossRatio thead').html());
		$("#medicalLossRatio tbody").html( tbody);
		
		
		 $('#medicalLossRatio').DataTable( {
	 		    dom: 'Bfrtip',
	 		   "iDisplayLength": 500,
	 		   "scrollY": 500,
	 	       "scrollX": true,
	 	        buttons: [
							{
							    extend: 'pdfHtml5',
							    orientation: 'landscape',
							    pageSize: 'LEGAL',
							    title: 'Medical Loss Ratio Detailed PDF Export'
							},
							{
								extend: 'excelHtml5',
								title: 'Medical Loss Ratio Detailed Excel Export'
							}
					],	
					 "order": [[ 0, "desc" ]]
							
	 		} );
		 mlrPrvdr = $("#mlrPrvdr"). val();
		 
		 if(mlrCategory == 9999 && mlrPrvdr == 9999){
			 $("#summaryDetails").removeClass();
		 }
		 else{
			 $("#summaryDetails").addClass('hide');
		 }
		
		
	}
	
	function summary()
	{
		var summaryList = new Array();
		var headerList = new Array();
		var keyList = new Array();
		var categoryList = new Array();
		var reportList = new Array();
		var monthList = new Array();
		indexposition = 0;
		
		
		
		if ( $.fn.DataTable.isDataTable('#medicalLossRatioSummary') ) {
				$('#medicalLossRatioSummary').DataTable().destroy();
   		}
  
		$('#medicalLossRatioSummary thead').html('');
		$("#medicalLossRatioSummary tbody").empty();
		$("#medicalLossRatioSummary thead").append("<tr></tr>");
		
		header =  $('#medicalLossRatio > thead > tr > th');
		header.each(function(index, text){
			
				headerList[indexposition] = $(this).text();
				if(index > 0)
					$("#medicalLossRatioSummary thead tr").append("<td>"+$(this).text()+"</td>")
				indexposition++;
			
		});
		
		 
		 
		all = $('#medicalLossRatio > tbody > tr');	
		
		th = $('#medicalLossRatio > thead > tr > th');	
		var val = ""; var count = 0;
	   
		th.each(function(thindex, thtext)
	    	{
	    		sum = 0; count = 0; val = "";
	    		headerText = $(this).text();
		  	    		all.each(function( index, text) {
		  	    			if(index < all.length-1){
		  	    				val = $('td:eq(2)', this).text() +""+$('td:eq(1)', this).text()+""+headerText;
		  	    				
		  	    				if($.inArray($('td:eq(1)', this).text(), reportList) == -1)
		  	    				{
		  	    					reportList.push($('td:eq(1)', this).text());
		  	    				}
		  	    				
		  	    				if($.inArray( $('td:eq(2)', this).text(), categoryList) == -1)
		  	    				{
		  	    					categoryList.push($('td:eq(2)', this).text());
		  	    				}
		  	    				if($.inArray(val, keyList) == -1)
		  	    				{
		  	    					keyList.push(val);
		  	    				}
		  	    				
		  	    				if(thindex != 1){
			  	    				if($('td:eq('+thindex+')', this).text()){
			  	    					if(summaryList["'"+val+"'"])
			  	    						summaryList["'"+val+"'"] = parseFloat(summaryList["'"+val+"'"]) + parseFloat($('td:eq('+thindex+')', this).text());
			  	    					else
			  	    						summaryList["'"+val+"'"] = parseFloat($('td:eq('+thindex+')', this).text());
			  	    				}
			  	    				else{
			  	    					if(!summaryList["'"+val+"'"])
			  	    						summaryList["'"+val+"'"] = 0;
			  	    					
			  	    						
			  	    				}	
		  	    				}
		  	    				
		  	    			}
		      	   		});
	    		
	    	});
	    	
	    	$("#medicalLossRatioSummary tbody tr:last").append('<td>'+val+'</td>');
	    	$.each(reportList,function(reportIndex, reportVal){
		    	$.each(categoryList,function(i, val)
		    	{
		    		$("#medicalLossRatioSummary tbody").append("<tr></tr>");
		    		$("#medicalLossRatioSummary tbody tr:last").append('<td>'+reportVal+'</td><td>'+val+'</td>');
		    		th.each(function(thindex, thtext)
		    		{
		    			headerText = $(this).text();
		    			var value = val +""+reportVal+""+headerText;
		    				if(!isNaN(summaryList["'"+value+"'"])){
		    					$("#medicalLossRatioSummary tbody tr:last").append('<td>'+Math.round(summaryList["'"+value+"'"] * 1000) / 1000 +'</td>');
		    				}
		    		});
		    		
		    	});
	    	});	
	    	
	    	 $("#medicalLossRatioSummary tbody").append("<tr></tr>");
	    	 $("#medicalLossRatioSummary tbody tr:last").html($("#medicalLossRatio tbody tr:last").html());
	    	 $('#medicalLossRatioSummary > thead > tr > th:eq(0)').remove();
	    	 $('#medicalLossRatioSummary  tbody  tr:last td:eq(0)').remove();
	    	 
	    	 $('#medicalLossRatioSummary').dataTable( {
	 		    dom: 'Bfrtip',
	 		   "scrollY": 500,
	 	        "scrollX": true,
	 	        buttons: [
							{
							    extend: 'pdfHtml5',
							    orientation: 'landscape',
							    pageSize: 'LEGAL',
							    title: 'Medical Loss Ratio Summary PDF Export'
							},
							{
								extend: 'excelHtml5',
								title: 'Medical Loss Ratio Summary Excel Export'
							}
	 	        ],
	 	       "order": [[ 0, "desc" ]],
	 	      "iDisplayLength": 500
	 	      
	 		} );
	}
	

	function mlrUnwantedList(activityMonth, reportMonth, isUnwanted,prvdrName, prvdrId){
		
		 $("#myModal").modal('show');
		 $(".info").show();
		     $("#unwantedClaims tbody").empty();
				var url ;
				if(isUnwanted){
					url= getContextPath()+"/unwantedClaims/list";
					$(".modal-title").html( "Unwanted Claim Details - "+prvdrName);
				}else{
					url= getContextPath()+"/stoploss/list";
					$(".modal-title").html( "Stop Loss Details - "+prvdrName);
				}
				var insId = $("#mlrInsu").val();
				var params = { "insId":insId, "prvdrId":prvdrId, "reportMonth" :reportMonth, "activityMonth":activityMonth };
				var str = jQuery.param( params );
				  $.getJSON(url+'?'+str, function(data){
					 var sum = 0;
					 $.each(data.data, function( index, value ) {
						
					 	$("#unwantedClaims tbody").append("<tr><td>"+value.claimType+"</td><td>"+value.lastName+"</td><td>"+value.firstName+"</td><td>"+value.gender+"</td><td>"+value.dob+"</td><td>"+value.unwantedClaims+"</td></tr>");
					 		sum = sum + value.unwantedClaims;
					 	});
					 if(data.data.length > 0){
						 $("#unwantedClaims tbody").append("<tr><td></td><td></td><td></td><td></td><td>Total</td><td>"+sum+"</td></tr>");
					 }
					 
				 }).success(function() {
					 $(".info").hide();
					 $("#myModal").modal('show');
				  })
				 
				 .done(function() {
					    console.log( "second success" );
					  })
					  .fail(function() {
					    console.log( "error" );
					  })
					  .always(function() {
					    console.log( "complete" );
					  });
			}
</script>


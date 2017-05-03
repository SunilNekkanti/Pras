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
<script src="https://cdn.datatables.net/fixedcolumns/3.2.2/js/dataTables.fixedColumns.min.js"></script>
<script src="http://cdn.datatables.net/buttons/1.1.0/js/buttons.html5.js"></script>
<link rel="stylesheet" href="https://cdn.datatables.net/fixedcolumns/3.2.2/css/fixedColumns.dataTables.min.css">
<script>
$(document).ready(function() {
	
	Number.prototype.format  = function(c, d, t){
		 var n = this, 
		     c = isNaN(c = Math.abs(c)) ? 2 : c, 
		     d = d == undefined ? "." : d, 
		     t = t == undefined ? "," : t, 
		     s = n < 0 ? "-" : "", 
		     i = String(parseInt(n = Math.abs(Number(n) || 0).toFixed(c))), 
		     j = (j = i.length) > 3 ? j % 3 : 0;
		    return s + (j ? i.substr(0, j) + t : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + t) + (c ? d + Math.abs(n - i).toFixed(c).slice(2) : "");
		  };
		  
		  
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
			drawTable();
			$( "#tabs" ).tabs({ active: 0});
		});
		
		
	
    	 var $selectIns = $('#extFilterIns');
    	  $.getJSON("${context}/"+'/insurance/list?pageNo=0&pageSize=200', function(data){
			    
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
 	    	  $.getJSON("${context}/"+'/insurance/providerlist?insId='+insSelectValue, function(data){
 				    
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
	 	    
	 	    	  $.getJSON("${context}/"+'/mlrReportDate/list?'+str, function(data){
					    
					  //clear the current content of the select
					   var $selectReportDat = $('#extFilterReportDate');
					     var s = $('<select id=\"mlrReportDate\" style=\"width:150px;\" class=\"btn btn-default\" >');
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
					    
					     
					     if(data.data.list.length < 1)
					    	 	$("#medicalLossRatioGenerate").hide();
					     else
					    	 $("#medicalLossRatioGenerate").show();
					     
				 }).success(function() { 
					reportDateSelectValue= $("#mlrReportDate option:selected").val();
					$('select[id="mlrReportDate"]').val(reportDateSelectValue);
		    	 });
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
						<label class="control-label col-sm-4">Insurance</label>
						<div class=" col-sm-8" id="extFilterIns"></div>
					</div>
					<div class="col-sm-3">
						<label class="control-label col-sm-3">Provider</label>
						<div class="col-sm-9" id="extFilterPrvdr"></div>
					</div>
					
					<div class="col-sm-3">
						<label class="control-label col-sm-3">Report Date</label>
						<div class="col-sm-9" id="extFilterReportDate"></div>
					</div>
					
					<div class="col-sm-3">
						<label class="control-label col-sm-3">Category</label>
						<div class="col-sm-9" id="extFiltercategory">
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
									<li id="detailedTab"><a href="#details">Detailed</a></li>
									<li id="summaryTab" onclick='$("#detailedTab").removeClass();' class="hide"><a href="#summary" >Summary</a></li>
								</ul>
								<div id="details" class="col-sm-12 table-responsive">
									<table id="medicalLossRatio" class="table table-hover table-responsive">
										
									</table>
							  	</div>	
								  <div id="summary" class="col-sm-12 table-responsive">
									<table id="medicalLossRatioSummary" class="table  table-hover table-responsive">
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
   
	function drawTable(){
		showModal("Medical Loss Ratio")
		$("#summaryTab").addClass('hide');
		var table = "medicalLossRatio";
		$("#"+table).html('');
		reportMonth = $("#mlrReportDate option:selected").val();
		category = $("#mlrCategory option:selected").val();
		ins_id 	= $("#mlrInsu option:selected").val();
		prvdr_id = $("#mlrPrvdr option:selected").val();
		
		var params = { "insId":ins_id, "prvdrId":prvdr_id, "repMonth":reportMonth, "category":category,
					  "pageSize":500, "pageNo":0};
		var str = jQuery.param( params );
		var url =  "${context}/"+'/medicalLossRatio/list?'+str;
	 	$.ajax({
		  url: url,
		  dataType: "json",
		})
		.success(function(json)
		{
			 datatableDelete(table);
			
			$("#"+table).html('');
			$("#"+table).append('<thead><tr></tr></thead>');
			$("#"+table).append('<tbody></tbody>');
			var mlrCategory = $("#mlrCategory"). val();
			$.each(json.data, function (index, key) {
				if(index != 0){ $("#"+table+" tbody ").append("<tr></tr>"); }
				$.each(json.data[index], function (tdindex, tdvalue){
					if(tdvalue == null) { tdvalue = 0; }
					if(index == 0){
						$("#"+table+" thead tr").append("<th>"+tdvalue+"</th>");
					}
					else {
						if(tdindex > 4  && json.data[index][4] != "PATIENTS" ){
							 
							tdvalue = (Math.round(tdvalue *100)/100 ).formatMoney(2, '.', ',')
						}
							if(json.data[index][4] == "UNWANTED_CLAIMS" && tdindex > 4){
								activityMonth = json.data[0][tdindex];
								repMonth = json.data[index][3];
								prvdrName = "'"+json.data[index][2]+"'";
								prvdrId = json.data[index][1];
								$link = '<a  href="javascript:void(0)" onclick="mlrUnwantedList('+activityMonth+','+repMonth+',true,'+prvdrName+','+prvdrId+');">'+tdvalue+'</a>'
								$("#"+table+" tbody tr:last").append("<td>$"+$link+"</td>");
							}
							else if(json.data[index][4] == "STOP_LOSS" && tdindex > 4){
								activityMonth = json.data[0][tdindex];
								repMonth = json.data[index][3];
								prvdrName = "'"+json.data[index][2]+"'";
								prvdrId = json.data[index][1];
								$link = '<a  href="javascript:void(0)" onclick="mlrUnwantedList('+activityMonth+','+repMonth+',false,'+prvdrName+','+prvdrId+');">'+tdvalue+'</a>'
								$("#"+table+" tbody tr:last").append("<td>$"+$link+"</td>");
							}
							else if((json.data[index][4] == "MLR" || json.data[index][4] == "QMLR") && tdindex > 4){
																$("#"+table+" tbody tr:last").append("<td>"+tdvalue+"%</td>");
							}
							
							else if( tdindex <= 4  ||  json.data[index][4] == "PATIENTS" ) {
									$("#"+table+" tbody tr:last").append("<td style='width:200px'>"+tdvalue+"</td>");
							}else{
								$("#"+table+" tbody tr:last").append("<td>$"+tdvalue+"</td>");
							}
						}
				});	
				
			});
			
				 $("#"+table+" tbody ").append("<tr></tr>");
				 var sum;
				 var column = json.data[0].length;
				 var row = json.data.length;
				for(var i = 0; i < column; i++)
				{
					if(i > 4 ){
						sum = count = 0;
						for( var y = 0; y < row-1; y++){
							$value = json.data[y+1][i];
							if($value == null) { $value = 0; }
							if(category == "MLR" || category == "QMLR")
							{	sum = sum +   parseFloat( $value); if( $value != 0) count++;  }
							else if(category == 9999){
								if(json.data[y+1][4] == "UNWANTED_CLAIMS" || json.data[y+1][4] == "TOTAL")
								{	sum = sum +   parseFloat($value); }
							}
							else{
								sum = sum +   parseFloat($value);
							}	
									
						}
						totalSum = sum ;
						if(category == "MLR" || category == "QMLR") { totalSum =  totalSum / count }
						$("#"+table+" tbody tr:last").append("<td>$"+(Math.round(totalSum *100)/100).formatMoney(2, '.', ',')+"</td>");
					}
					else
						$("#"+table+" tbody tr:last").append("<td></td>");
				}
					
			
			 $('#'+table+'  thead  tr').find('th:lt(2)').remove();
	    	 $('#'+table+'  tbody  tr').find('td:lt(2)').remove();
	    	 removeHeader(table);
	    	 datatableCreate(table, "Detailed Report");
	    	 $('#'+table).width('100%');
	    	
			if(prvdr_id == 9999) { 	summary(json);	}
	    	
		})
		.done(function() {
			console.log( "  Details finished");
			hideModal();
		});
	}
	
	
	
	
	function summary(json)
	{
		
		$("#summaryTab").removeClass();
		var summaryList = new Array();
		var categoryList = new Array();
		var reportList = new Array();
		var table = "medicalLossRatioSummary";
		datatableDelete(table);
		$("#"+table+" thead").html('');
		$("#"+table+" thead").append("<tr></tr>");
		$("#"+table+" tbody").html('');
		
		$.each(json.data[0], function(index, text){
			$("#"+table+" thead tr").append("<th>"+text+"</th>");
		});
		
		 
		all = $('#medicalLossRatio > tbody > tr');	
		th = $('#'+table+' > thead > tr > th');	
		var val = ""; var count = 0;
	   
		$.each(json.data[0],function(thindex, thtext) 	{
		
			if(thindex > 4){
	    		sum = 0; count = 0; val = "";
	    		headerText =thtext;
	    		 
		  	    		$.each(json.data, function( index, text) {
		  	    			
		  	    			if(index < json.data.length  && index > 0){ 
		  	    				if(json.data[index][thindex] == null) { json.data[index][thindex] = 0; }
		  	    				val = json.data[index][4]+""+ json.data[index][3]+""+headerText;	
		  	    				if(summaryList["'"+val+"'"]){
		  	    					summaryList["'"+val+"'"] = parseFloat(summaryList["'"+val+"'"]) + parseFloat(json.data[index][thindex]);
		  	    				} else {
		  	    					summaryList["'"+val+"'"] = parseFloat(json.data[index][thindex]);
		  	    				}
	  	    						
		  	    				if($.inArray(json.data[index][3], reportList) == -1)
		  	    				{
		  	    					reportList.push(json.data[index][3]);
		  	    				}
		  	    				
		  	    				if($.inArray( json.data[index][4], categoryList) == -1)
		  	    				{
		  	    					categoryList.push(json.data[index][4]);
		  	    				}
		  	    			}
		      	   		});
 		  	    } 
	    		
	    	});
	    	
	
	    	$.each(reportList,function(reportIndex, reportVal){
		    	$.each(categoryList,function(i, val){
		    		$("#"+table+" tbody").append("<tr></tr>");
		    		$("#"+table+" tbody tr:last").append('<td>'+reportVal+'</td><td>'+val+'</td>');
		    		th.each(function(thindex, thtext){
		    			if(thindex > 4 ){
		    			headerText = $(this).text();
		    			var value = val +""+reportVal+""+headerText;
		    			console.log(value + "  "+summaryList["'"+value+"'"]);
		    				if(!isNaN(summaryList["'"+value+"'"])){
		    					if(val != 'PATIENTS'){
		    						$("#"+table+" tbody tr:last").append('<td>$'+Math.round((summaryList["'"+value+"'"] * 100) / 100).formatMoney(2,'.',',') +'</td>');
		    					}else{
		    						$("#"+table+" tbody tr:last").append('<td>'+  summaryList["'"+value+"'"]  +'</td>');
		    					}
		    				}
		    			}
		    		});
		    		
		    	});
	    	});	
	    	
	    	
	    	 $('#'+table+' > thead  tr:last th:lt(3)').remove();
	    	 removeHeader(table);
	    	 $("#"+table+" tbody").append("<tr></tr>");
		    	$("#"+table+" tbody tr:last").html($("#medicalLossRatio tbody tr:last").html());
		    	 $('#'+table+' > tbody  tr:last td:eq(0)').remove();
	    	 datatableCreateSummary(table, "Summary Report");
	}
	

	function mlrUnwantedList(activityMonth, reportMonth, isUnwanted,prvdrName, prvdrId){
		     $("#unwantedClaims tbody").empty();
				var url ;
				if(isUnwanted){
					url= "${context}/"+"/unwantedClaims/list";
					$(".modal-title").html( "Unwanted Claim Details - "+prvdrName);
				}else{
					url= "${context}/"+"/stoploss/list";
					$(".modal-title").html( "Stop Loss Details - "+prvdrName);
				}
				showModal($(".modal-title").html(), "unwantedClaims")
				var insId = $("#mlrInsu").val();
				var params = { "insId":insId, "prvdrId":prvdrId, "reportMonth" :reportMonth, "activityMonth":activityMonth };
				var str = jQuery.param( params );
				  $.getJSON(url+'?'+str, function(data){
					 var sum = 0;
					 $.each(data.data, function( index, value ) {
						
					 	$("#unwantedClaims tbody").append("<tr><td>"+value.claimType+"</td><td>"+value.lastName+"</td><td>"+value.firstName+"</td><td>"+value.gender+"</td><td>"+value.dob+"</td><td>$"+value.unwantedClaims.formatMoney(2, '.', ',')+"</td></tr>");
					 		sum = sum + value.unwantedClaims;
					 	});
					 if(data.data.length > 0){
						 $("#unwantedClaims tbody").append("<tr><td></td><td></td><td></td><td></td><td>Total</td><td>$"+sum.formatMoney(2, '.', ',')+"</td></tr>");
					 }
					 
				 }).success(function() {
				
				  })
				 
				 .done(function() {
					 $(".info").hide();
					    console.log( "second success" );
					  })
					  
					  .fail(function() {
					    console.log( "error" );
					  })
					  .always(function() {
					    console.log( "complete" );
					  });
			}
	
	function showModal(title, table){
		 $("#myModal").modal('show');
		 $("#unwantedClaims").show();
			$(".modal-title").html(title);
			$(".hide").show();
		if(!table) $("#unwantedClaims").hide();
			
	}
	function hideModal(){
		 $("#myModal").modal('hide');
		
	}
	function datatableDelete(table){
		if ( $.fn.DataTable.isDataTable('#'+table) ) {
			$('#'+table).DataTable().destroy();
		}
	    $('#'+table+' tbody').empty();
	  
	}

	function datatableCreateSummary(table, caption)
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
	        "iDisplayLength": 500,
		    "sPaginationType": "full_numbers",
		    "order": [[ 0, "desc" ]],
		    "initComplete": function(settings, json) {
		    
		    	$('#'+table).width('100%');
		    }
	    } );
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
		                      leftColumns: 3
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
		  
		  function removeHeader(table)
		  {
			  	 $length = $('#'+table+'  thead  tr th').length;
		    	 $position = new Array();
		    	 $( $('#'+table+'  thead  tr th').get().reverse()).each(function(index, text) { 
		    		   if($(this).text() == 0 || $(this).text() == "null"){
		    			   $position.push($length - 1 - index);
		    			 }
		    		});
		    	 
		    	 $.each($position, function(index,value){
		    		 $('#'+table+'  thead  tr').find('th:eq('+value+')').remove();
		    		 $('#'+table+'  tbody  tr').find('td:eq('+value+')').remove();
			    	
		    	 });
		  }
</script>


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
	
		$("#medicalLossRatioGenerate").click(function(event)
		{
			if($("#mlrReportDate").val() == null)
				{
				 alert(" Select Report Date");
				  return false;
				}
			callmedicalLossRatioGenerate();
		});
		
		var rowHeader = $('#medicalLossRatio thead tr');
		for(var col = 0; col<50; col++)
			rowHeader.append('<th scope="col" role="row" class="hide"></th>');
	
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
					    	 var date = new Date(val.reportDate);
					    	 var m = (date.getMonth() + 1);
					    	 if(m < 10 ) { m = '0'+(date.getMonth() + 1);}
					    	 date =  date.getFullYear() + '-' + m + '-' + date.getDate();
					    	 if(key == 0) {
					    		 s.append('<option value="'+date+'" Selected>' + val.reportDate +'</option>');
					    	 }  else {
					    		 s.append('<option value="'+date+'">' + val.reportDate +'</option>');
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
				columns = new Array();
	     		columns.push({ "mDataProp": "reportGenDate","bSearchable" : true, "bSortable": true,"sClass": "center widthS","sWidth" : "10%"  });
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
    		    	ruleArray.push ("'"+$(this).val()+"'" );
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
		   restParams.push({"name" : "sort", "value" : "reportGenDate" });
		   restParams.push({"name" : "sortdir", "value" : sortDir });
		   restParams.push({"name" : "sSearch" , "value" : paramMap.sSearch  });
		   restParams.push({"name" : "insId" , "value" : sSearchIns  });
		   restParams.push({"name" : "prvdrId" , "value" : sSearchPrvdr  });
		   restParams.push({"name" : "repGenDate" , "value" : sSearchGenerateDate  });
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
	        	                 title: 'Medical Loss Ratio Table Export'
	        	             }
	        	             
		                   ],
  	         "bDestroy" : true,	
     	     "sAjaxSource" : getContextPath()+'/medicalLossRatio/list',
     	     "sAjaxDataProp" : 'data',
     	     "bLengthChange": false,
     	     "bPagination":false,
     	     "iDisplayLength": 100,
     	     "sPaginationType": "full_numbers",
     	     "bProcessing": true,
     	     "bServerSide" : true,
     	     "initComplete": function(settings, json) {
	    		 var totalRecord = $("#medicalLossRatio tbody tr").length -1;
		    	     jQuery.each($("#medicalLossRatio tbody tr"), function( index, text ) {
		    	    	
		    	    		 if(index == 0){
		    	    			 $("#medicalLossRatio tbody tr").eq(index).remove();
		    	    		 }
		    	    			
		    	    });
	     	 },
	     	"fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
	     	      // Bold the grade for all 'A' grade browsers
	     	      if ( iDisplayIndex == 0 )
	     	      {
	     	    	  var d = '"'+aData+'"';
	     	    	arr = d.split(',');
	     	        $('td', nRow).hide();
	     	      }
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
     	    	 var totalRecord = $("#medicalLossRatio thead tr th").length;
     	    	 var thempty = new Array;
     	    	 jQuery.each($("#medicalLossRatio thead tr th"), function( index, text ) {
     	    		 if(index == 0) $(this).addClass("hide");
     	    			if($(this).text() == "" || $(this).text() == "null" ){
     	    				thempty.push(index);
     	    				$(this).addClass("hide");
     	    			} 
     	    	 });
	    		 jQuery.each($("#medicalLossRatio tbody tr"), function( index, text ) {
	    			 jQuery.each($("#medicalLossRatio tbody tr:eq("+index+") td"), function( tdindex, tdtext ) {
	    				 if(tdindex == 0) $(this).remove();
    	    		 		if($.inArray(tdindex, thempty) != -1) {
    	    		 			$(this).remove();
    	    		 			$(this).addClass("hide");
    	    		 		}
    	    		 			
	    			 });	
	    			 
	    			 
    	    });
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
									<option value="Prof">Prof</option>
							</select>
						</div>
					</div>
				</div>
					<div class="col-sm-12 text-right">
						<button type="button" id="medicalLossRatioGenerate"
							class="btn btn-success btn-sm btn-xs">Generate</button>
					</div>
				
				<table id="medicalLossRatio"
					class="table table-striped table-hover table-responsive">
					<thead>
						<tr>
							
						</tr>
					</thead>

					<tbody>

					</tbody>

				</table>
			</div>
		</div>
	</div>
</div>

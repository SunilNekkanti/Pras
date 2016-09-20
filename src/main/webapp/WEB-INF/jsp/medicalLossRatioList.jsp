<%@  page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@  taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script>
$(document).ready(function() {
	
		$("#medicalLossRatioGenerate").click(function(event)
		{
			callmedicalLossRatioGenerate();
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
					     var s = $('<select id=\"mlrReportDate\" style=\"width:150px;\" class=\"btn btn-default\">');
					     //iterate over the data and append a select option
					     s.append('<option value="">select Report Geerate Date</option>');
					     $.each(data.data.list, function(key, val){
					    	 s.append('<option value="'+val.reportDate+'">' + val.reportDate +'</option>');
					     });
					     s.append('<option value="9999">All</option>');
					     s.append('</select>');
					     $selectReportDat.html(s);
				 }).success(function() { 
					var reportDateSelectValue = Cookies.get('mlrReportDate');
				 if(reportDateSelectValue != undefined) 
					 reportDateSelectValue = reportDateSelectValue;
				 else{
					 reportDateSelectValue= $("#mlrReportDate option:selected").val();
					Cookies.set('mlrReportDate', reportDateSelectValue,{path: cookiePath });
				 }	
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
    		   
    		   var ruleArray = new Array;
    		    $("#mlrReportDate option").each  ( function() {
    		    	ruleArray.push ( $(this).val() );
    		    });

    		    
    		   if ( $.fn.DataTable.isDataTable('#medicalLossRatio') ) {
  						$('#medicalLossRatio').DataTable().destroy();
			   }
    		  
				$('#medicalLossRatio tbody').empty();
    		   GetMembershipByInsPrvdr(insSelectValue,prvdrSelectValue,reportDateSelectValue, ruleArray, columns);
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
		   var ruleIds = paramMap.ruleIds;
		   
		   //create new json structure for parameters for REST request
		   var restParams = new Array();
		   restParams.push({"name" : "pageSize", "value" : pageSize});
		   restParams.push({"name" : "pageNo", "value" : pageNum });
		   restParams.push({"name" : "sort", "value" : sortName });
		   restParams.push({"name" : "sortdir", "value" : sortDir });
		   restParams.push({"name" : "sSearch" , "value" : paramMap.sSearch  });
		   restParams.push({"name" : "insId" , "value" : sSearchIns  });
		   restParams.push({"name" : "prvdrId" , "value" : sSearchPrvdr  });
		   restParams.push({"name" : "sSearchGenerateDate" , "value" : sSearchGenerateDate  });
		   restParams.push({"name" : "ruleIds" , "value" : ruleIds  });
		   
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
                  res.iTotalRecords = res.data.totalCount;
                  res.iTotalDisplayRecords = res.data.totalCount;
             		fnCallback(res);
             		
            },
              error : function (e) {
              }
          } );
     	}
     	
     	
     	  GetMembershipByInsPrvdr = function (insId, prvdrId, generateDate,ruleArray, aoColumns) {
      		
  	        var oTable = $('#medicalLossRatio').removeAttr( "width" ).dataTable({  
  	        	"sDom": 'Bfrtip',
	        	 "buttons": [
	        	             {
	        	                 extend: 'excelHtml5',
	        	                 title: 'Membership Activity Month Table Export'
	        	             }
	        	             
		                   ],
  	         "bDestroy" : true,	
     	     "sAjaxSource" : getContextPath()+'/medicalLossRatio/list',
     	     "sAjaxDataProp" : 'data.list',
              "aoColumns":  aoColumns,      
     	     "bLengthChange": false,
     	     "iDisplayLength": 15,
     	     "sPaginationType": "full_numbers",
     	     "bProcessing": true,
     	     "bServerSide" : true,
     	     "fnServerParams": function ( aoData ) {
                aoData.push(
                    {"name": "sSearchIns", "value": insId},
                    {"name": "sSearchPrvdr", "value": prvdrId },
                    {"name": "sSearchGenerateDate", "value": generateDate },
                    {"name": "ruleIds", "value": ruleArray }
                );
             },        
     	     "fnServerData" : datatable2RestMembership
     	});
  	        
     }

     		
 } );
</script>

<div class="panel-group">
	<div class="panel panel-success">
		<div class="panel-heading">
			Membership Activity Month List <span class="clrRed"> </span>
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
						<button type="button" id="medicalLossRatioGenerate"
							class="btn btn-success btn-sm btn-xs">Generate</button>
					</div>
				</div>
				<table id="medicalLossRatio"
					class="table table-striped table-hover table-responsive">

					<thead>
						<tr>
							<th scope="col" role="row">reportGenDate</th>
							<th scope="col" role="row">activityMonth</th>
							<th scope="col" role="row">patients</th>
							<th scope="col" role="row">fund</th>
							<th scope="col" role="row">prof</th>
							<th scope="col" role="row">inst</th>
							<th scope="col" role="row">pharmacy</th>
						</tr>
					</thead>

					<tbody>

					</tbody>

				</table>
			</div>

		</div>

	</div>
</div>
<%@  page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@  taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script>
$(document).ready(function() {
	
		$("#membershipActivityMonthGenerate").click(function(event)
		{
			callmembershipActivityMonthGenerate();
		});
		for (i = new Date().getFullYear(); i > new Date().getFullYear() - 3; i--)
		{
		    $('#yearpicker').append($('<option />').val(i).html(i));
		}	
		if(getCookie("yearpicker"))
			yearSelectValue = getCookie("yearpicker");
		else
		{	
			setSelectedValue('yearpicker', "", $("#yearpicker option:selected").val());
			yearSelectValue= $("#yearpicker option:selected").val();
		}
	     
		$('select[id="yearpicker"]').val(yearSelectValue);
		
		var result = ["JAN", "FEB", "MAR","APR","MAY", "JUN", "JUL","AUG","SEP","OCT", "NOV", "DEC"];
    	 var $selectIns = $('#extFilterIns');
    	  $.getJSON(getContextPath()+'/insurance/list?pageNo=0&pageSize=200', function(data){
			    
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
			 var insSelectValue;
			 if(getCookie("insu"))
					insSelectValue = getCookie("insu");
				else{
					insSelectValue= $("#insu option:selected").val();
					setSelectedValue('insu', "",insSelectValue);
				}
			     
				$('select[id="insu"]').val(insSelectValue);
 			 var $selectPrvdr = $('#extFilterPrvdr');
 	    	  $.getJSON(getContextPath()+'/insurance/providerlist?insId='+insSelectValue, function(data){
 				    
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
 				 var prvdrSelectValue;
 				 if(getCookie("prvdr"))
 					 prvdrSelectValue = getCookie("prvdr");
 				 else{
 					prvdrSelectValue= $("#prvdr option:selected").val();
 					setSelectedValue('prvdr', "",prvdrSelectValue);
 				 }	
 				 
 				$('select[id="prvdr"]').val(prvdrSelectValue);
 	    	 });
    	  }
    	  
    	  var columns ;
    	  var callmembershipActivityMonthGenerate = function(){
				columns = new Array();
				columns.push({ "mDataProp": "lastName","bSearchable" : false, "bSortable" : true,"bVisible" : false, "sClass": "center","sWidth" : "10%"});
	     		columns.push({ "mDataProp": "mbrProviderList.0.prvdr.name","bSearchable" : true, "bSortable" : true,"sClass": "center","sWidth" : "10%"});
	     		columns.push({ "mDataProp": "lastName","bSearchable" : true, "bSortable": true,"sClass": "center","sWidth" : "10%"  });
	     		columns.push({ "mDataProp": "firstName","bSearchable" : true, "bSortable": true,"sClass": "center","sWidth" : "10%"  });
	     		var myTable = $("#membershipActivityMonth");
	     		var thead = myTable.find("thead");  
	     		
	     		$("#membershipActivityMonth th").each(function() {
					if($(this).attr("role") == "row"){
					}else{
						$(this).remove();
					}
							
       			}); 
	     		var year = $("#yearpicker").val(); var yearMonth;
	     		 $.each(result, function(index, value){
	     		 	columns.push({ "mDataProp": "mbrActivityMonthList[ ].activityMonth","bSearchable" : false, "bSortable" : false,"sClass": "center","sWidth" : "5%", "sDefaultContent": "",
						    "render": function (data, type, full, meta) {
						    	full.mbrActivityMonthList.forEach(function( item ) {
						    		 if(index < 9){ yearMonth = year+"0"+ (index+1);}
					     			 else {yearMonth = year+""+ (index+1);}
						    	});
						    	 var returnType ='';
					    		
									if(data.indexOf(yearMonth) >= 0)
										return 'X';
									else
									return '';
							}
					}); 	
	     		});	
	     		callDatableWithChangedDropDown();
    	  }
    	  
    	  function callDatableWithChangedDropDown(){
    		   var insSelectValue= $("#insu option:selected").val();
    		   var prvdrSelectValue= $("#prvdr option:selected").val();
    		   var activityYearSelectValue= $("#yearpicker option:selected").val();
    		   
    		   var ruleArray = new Array;
    		    $("#yearpicker option").each  ( function() {
    		    	ruleArray.push ( $(this).val() );
    		    });

    		    
    		   if ( $.fn.DataTable.isDataTable('#membershipActivityMonth') ) {
  						$('#membershipActivityMonth').DataTable().destroy();
			   }
    		  
    		   $.each( result, function( key, value ) {
	     		
	     			$("#membershipActivityMonth tr").append("<th>"+value+"</th>");
	     			
	     		});
				$('#membershipActivityMonth tbody').empty();
    		   GetMembershipByInsPrvdr(insSelectValue,prvdrSelectValue,activityYearSelectValue, ruleArray, columns);
    	}  
    	     
    	$(document.body).on('change',"#insu",function (e) {
    		setSelectedValue('insu', "", $("#insu option:selected").val());
    		setSelectedValue('prvdr', "", "");
    		
    		if ( $.fn.DataTable.isDataTable('#membershipActivityMonth') ) {
					$('#membershipActivityMonth').DataTable().destroy();
	   		}
    		$('#membershipActivityMonth tbody').empty();
    		providerDropdown();
  		});
    	
    	$(document.body).on('change',"#prvdr",function (e) {
    		setSelectedValue('prvdr', "", $("#prvdr option:selected").val());
    		if ( $.fn.DataTable.isDataTable('#membershipActivityMonth') ) {
					$('#membershipActivityMonth').DataTable().destroy();
	   		}
    		$('#membershipActivityMonth tbody').empty();
  		});
    	
    	$(document.body).on('change',"#yearpicker",function (e) {
    		setSelectedValue('yearpicker', "", $("#yearpicker option:selected").val());
    		if ( $.fn.DataTable.isDataTable('#membershipActivityMonth') ) {
					$('#membershipActivityMonth').DataTable().destroy();
	   		}
    		$('#membershipActivityMonth tbody').empty();
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
		   var sSearchActivityYear = paramMap.sSearchActivityYear;
		   var ruleIds = paramMap.ruleIds;
		   
		   //create new json structure for parameters for REST request
		   var restParams = new Array();
		   restParams.push({"name" : "pageSize", "value" : pageSize});
		   restParams.push({"name" : "pageNo", "value" : pageNum });
		   restParams.push({"name" : "sort", "value" : sortName });
		   restParams.push({"name" : "sortdir", "value" : sortDir });
		   restParams.push({"name" : "sSearch" , "value" : paramMap.sSearch  });
		   restParams.push({"name" : "sSearchIns" , "value" : sSearchIns  });
		   restParams.push({"name" : "sSearchPrvdr" , "value" : sSearchPrvdr  });
		   restParams.push({"name" : "sSearchActivityYear" , "value" : sSearchActivityYear  });
		   restParams.push({"name" : "ruleIds" , "value" : ruleIds  });
		   
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
     	
     	
     	  GetMembershipByInsPrvdr = function (insId, prvdrId, activityYear,ruleArray, aoColumns) {
      		
  	        var oTable = $('#membershipActivityMonth').removeAttr( "width" ).dataTable({  
  	        	"sDom": 'Bfrtip',
	        	 "buttons": [
	        	             {
	        	                 extend: 'excelHtml5',
	        	                 title: 'Membership Activity Month Table Export'
	        	             }
	        	             
		                   ],
  	         "bDestroy" : true,	
     	     "sAjaxSource" : getContextPath()+'/membership/membershipActivityMonth/list',
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
                    {"name": "sSearchActivityYear", "value": activityYear },
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
						<label class="control-label col-sm-5">Year</label>
						<div class="col-sm-7">
								<select name="yearpicker" id="yearpicker" class="btn btn-default">
								</select>
						</div>
					</div>
					<div class="col-sm-3">
						<button type="button" id="membershipActivityMonthGenerate"
							class="btn btn-success btn-sm btn-xs">Generate</button>
					</div>
				</div>
				<table id="membershipActivityMonth"
					class="table table-striped table-hover table-responsive">

					<thead>
						<tr>
							<th scope="col" role="row">Sno</th>
							<th scope="col" role="row">Provider</th>
							<th scope="col" role="row">Last Name</th>
							<th scope="col" role="row">First Name</th>
						</tr>
					</thead>

					<tbody>

					</tbody>

				</table>
			</div>

		</div>

	</div>
</div>
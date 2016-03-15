<%@  page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
		
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@  taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html  PUBLIC  "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<title>Membership List</title>

<meta name="viewport" content="width=device-width, initial-scale=1">

<script>


     $(document).ready(function() {
    	
    	 var $selectIns = $('#extFilterIns');
    	  $.getJSON(getContextPath()+'/insurance/list?pageNo=0&pageSize=200', function(data){
			    
			     //clear the current content of the select
			     var s = $('<select id=\"insu\">');
			     //iterate over the data and append a select option
			     $.each(data.data.list, function(key, val){
			    	 s.append('<option value="'+val.id+'">' + val.name +'</option>');
			     });
			     s.append('</select>');
			     $selectIns.html(s);
		 });
    	  
    	 var $selectPrvdr = $('#extFilterPrvdr');
    	  $.getJSON(getContextPath()+'/provider/list?pageNo=0&pageSize=200', function(data){
			    
			     //clear the current content of the select
			     var s = $('<select id=\"prvdr\">');
			     //iterate over the data and append a select option
			     $.each(data.data.list, function(key, val){
			    	 s.append('<option value="'+val.id+'">' + val.name +'</option>');
			     });
			     s.append('<option value="9999">All</option>');
			     s.append('</select>');
			     $selectPrvdr.html(s);
		 });
    	  
    	 var $selectHedisCode = $('#extFilterHedisCode');
    	  $.getJSON(getContextPath()+'/hedisMeasureRule/list', function(data){
			    
			     //clear the current content of the select
			     var s = $('<select id=\"hedisRule\" >');
			     //iterate over the data and append a select option
			     $.each(data.data, function(key, val){
			    	 s.append('<option value="'+val.id+'" >' + val.hedisMeasureCode + '('+val.description +')</option>');
			     });
			     s.append('<option value="9999">All</option>');
			     s.append('</select>');
			     $selectHedisCode.html(s);
		 });
    	  
    	  $(document.body).on('change',"#insu",function (e) {
    		  callDatableWithChangedDropDown();
    		});
    	
    	  $(document.body).on('change',"#prvdr",function (e) {
    		  callDatableWithChangedDropDown();
    		});
    	
    	  $(document.body).on('change',"#hedisRule",function (e) {
    		  callDatableWithChangedDropDown();
      		});
       	
    	var callDatableWithChangedDropDown = function(){
    		   var insSelectValue= $("#insu option:selected").val();
    		   var prvdrSelectValue= $("#prvdr option:selected").val();
    		   var hedisRuleSelectValue= $("#hedisRule option:selected").val();
    		  $('#membershipTable').dataTable().fnDestroy();
    		   GetMembershipByInsPrvdrHedisRule(insSelectValue,prvdrSelectValue,hedisRuleSelectValue);
    	}  
    	            
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
		   var sSearchHedisCode = paramMap.sSearchHedisCode;
		   
		   //create new json structure for parameters for REST request
		   var restParams = new Array();
		   restParams.push({"name" : "pageSize", "value" : pageSize});
		   restParams.push({"name" : "pageNo", "value" : pageNum });
		   restParams.push({"name" : "sort", "value" : sortName });
		   restParams.push({"name" : "sortdir", "value" : sortDir });
		   restParams.push({"name" : "sSearch" , "value" : paramMap.sSearch  });
		   restParams.push({"name" : "sSearchIns" , "value" : sSearchIns  });
		   restParams.push({"name" : "sSearchPrvdr" , "value" : sSearchPrvdr  });
		   restParams.push({"name" : "sSearchHedisCode" , "value" : sSearchHedisCode  });
		   
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
     	
     	  GetMembershipByInsPrvdrHedisRule = function (insId, prvdrId, hedisRuleId) {
  	        var oTable = $('#membershipTable').dataTable({
  	         
     	     "sAjaxSource" : getContextPath()+'/reports/hedisMembership/list',
     	     "sAjaxDataProp" : 'data.list',
              "aoColumns": [
                            { "mDataProp": "id", 	"bSearchable" : false, "bVisible" : false, "asSorting" : [ "asc" ]  },
                            { "mDataProp": "mbrInsuranceList[0].insId.name","bSearchable" : true, "bSortable" : true,"sWidth" : "10%"},
                            { "mDataProp": "mbrProviderList[0].prvdr.name","bSearchable" : true, "bSortable" : true,"sWidth" : "10%"},
                            { "mDataProp": "mbrHedisMeasureList.hedisMeasureRule.hedisMeasure.code","bSearchable" : true, "bSortable" : true,"sWidth" : "10%"},
                            { "mDataProp": "mbrHedisMeasureList.hedisMeasureRule.description","bSearchable" : true, "bSortable" : true,"sWidth" : "10%"},
                            { "mDataProp": "firstName","bSearchable" : true, "bSortable": true,"sWidth" : "15%"  },
                            { "mDataProp": "lastName","bSearchable" : true, "bSortable": true,"sWidth" : "15%"  },
                            { "mDataProp": "dob","bSearchable" : true, "bSortable": true,"sWidth" : "10%"  },
                            { "mDataProp": "genderId.description","bSearchable" : true, "bSortable": true,"sWidth" : "10%" },
                            { "mDataProp": "mbrHedisMeasureList[0].dueDate","bSearchable" : true, "bSortable": true,"sWidth" : "10%", "sDefaultContent": ""  }
                          ],
               "aoColumnDefs": [ 
               		   		    
               		   		    { "sName": "dob", "aTargets": [ 7 ] ,
               		   		   	   "render": function (data) {
               		   		        		var date = new Date(data);
               		   	        			var month = date.getMonth() + 1;
               		   	       				 return (month > 9 ? month : "0" + month) + "/" + date.getDate() + "/" + date.getFullYear();
               		   		   	 }}
               ],          
     	     "bLengthChange": false,
     	     "iDisplayLength": 15,
     	     "sPaginationType": "full_numbers",
     	     "bProcessing": true,
     	     "bServerSide" : true,
     	     "fnServerParams": function ( aoData ) {
                aoData.push(
                    {"name": "sSearchIns", "value": insId},
                    {"name": "sSearchPrvdr", "value": prvdrId },
                    {"name": "sSearchHedisCode", "value": hedisRuleId }
                );
             },        
     	     "fnServerData" : datatable2RestMembership
     	});
     }

     	
 } );
    </script>

</head>

<body>

	<div class="panel-group">
		<div class="panel panel-primary">
			<div class="panel-heading">Hedis Report </div>
			<div class="col-sm-12" style="padding:5px 5px 0 5px;">
			 <div class=" col-sm-2" id="extFilterIns"> </div>
			 <div class="col-sm-2"  id="extFilterPrvdr"> </div>
			 <div class=" col-sm-2" id="extFilterHedisCode"> </div>
			 </div>
			<div class="panel-body" >
			
				<div class="table-responsive">
					<table id="membershipTable" class="table table-striped table-hover table-responsive">
					
						<thead>
							<tr>
								<th scope="col">Action</th>
								<th scope="col">Insurance</th>
								<th scope="col">Provider</th>
								<th scope="col">Hedis Code</th>
								<th scope="col">Rule Description</th>
								<th scope="col">First Name</th>
								<th scope="col">Last Name</th>
								<th scope="col">Date Of Birth</th>
								<th scope="col">Gender</th>
								<th scope="col">Due Date</th>
							</tr>
						</thead>
	
						<tbody >
							
						</tbody>
	
					</table>
				</div>	
				
			</div>
			
		</div>
	</div>


</body>
</html>
<%@  page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@  taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<script>


     $(document).ready(function() {
    	
    	 var $selectIns = $('#extFilterIns');
    	  $.getJSON("${context}/"+'/insurance/list?pageNo=0&pageSize=200', function(data){
			    
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
    	  $.getJSON("${context}/"+'/provider/list?pageNo=0&pageSize=200', function(data){
			    
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
    	  
    	 var $selectHedisRule = $('#extFilterHedisRule');
    	  $.getJSON("${context}/"+'/hedisMeasureRule/list', function(data){
			    
			     //clear the current content of the select
			     var s = $('<select id=\"hedisRule\" >');
			     //iterate over the data and append a select option
			     $.each(data.data, function(key, val){
			    	 s.append('<option value="'+val.id+'" >' + val.hedisMeasureCode + '('+val.description +')</option>');
			     });
			     s.append('<option value="9999">All</option>');
			     s.append('</select>');
			     $selectHedisRule.html(s);
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
		   var sSearchHedisRule = paramMap.sSearchHedisRule;
		   
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
     		 var columns = new Array();
     		 
     		columns.push({ "mDataProp": "mbr.id", 	"bSearchable" : false, "bVisible" : false, "asSorting" : [ "asc" ]  });
     		columns.push({ "mDataProp": "mbr..mbrProviderList.0.prvdr.name","bSearchable" : true, "bSortable" : true,"sWidth" : "10%"});
     		columns.push({ "mDataProp": "hedisMeasureRule.hedisMeasure.code","bSearchable" : true, "bSortable" : true,"sWidth" : "10%"});
     		columns.push({ "mDataProp": "hedisMeasureRule.description","bSearchable" : true, "bSortable" : true,"sWidth" : "10%","sDefaultContent": ""});
     		columns.push({ "mDataProp": "mbr.firstName","bSearchable" : true, "bSortable": true,"sWidth" : "13%"  });
     		columns.push({ "mDataProp": "mbr.lastName","bSearchable" : true, "bSortable": true,"sWidth" : "13%"  });
     		columns.push({ "mDataProp": "mbr.dob","bSearchable" : true, "bSortable": true,"sWidth" : "10%"  });
     		columns.push({ "mDataProp": "mbr.genderId.description","bSearchable" : true, "bSortable": true,"sWidth" : "10%" });
     		columns.push({ "mDataProp": "dueDate","bSearchable" : true, "bSortable": true,"sWidth" : "10%", "sDefaultContent": ""  });
     		columns.push({ "mDataProp": "id","bSearchable" : true, "bSortable": true,"sWidth" : "4%" });
           
     		 
  	        var oTable = $('#membershipTable').dataTable({
  	         
     	     "sAjaxSource" : "${context}/"+'/reports/hedisMembership/list2',
     	     "sAjaxDataProp" : 'data.list',
              "aoColumns": columns,
               "aoColumnDefs": [ 
               		   		    { "sName": "mbr.dob", "aTargets": [ 6 ] ,
               		   		   	   "render": function (data) {
               		   		        		var date = new Date(data);
               		   	        			var month = date.getMonth() + 1;
               		   	       				 return (month > 9 ? month : "0" + month) + "/" + date.getDate() + "/" + date.getFullYear();
               		   		   	 }},
               		   			{ "sName": "dueDate", "aTargets": [ 8 ] ,
                 		   		   "render": function (data, type, full, meta) {
							   					var date = new Date(data);
	 		   	        						var month = date.getMonth() + 1;
	 		   	        					return  (month > 9 ? month : "0" + month) + "/" + date.getDate() + "/" + date.getFullYear();
                 		           }
               		   		   	 },
	               		   		{ "sName": "hedisMeasureRule.id", "aTargets": [ 9 ] ,
	               		   		   "render": function (data, type, full, meta) {
	               		      				return '<a href="#" id="'+data+'" onclick="myFunction('+data+')"><span class="glyphicon glyphicon-pencil"></span></a>';
	               		        	}
               		     	 	}
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
                    {"name": "sSearchHedisRule", "value": hedisRuleId }
                );
             },        
     	     "fnServerData" : datatable2RestMembership
     	});
     }

     	
 } );
    </script>


<div class="panel-group">
	<div class="panel panel-success">
		<div class="panel-heading">Hedis Report</div>
		<div class="panel-body">
			<div class="table-responsive">
				<div class="col-sm-12">
					<div class="col-sm-3">
						<label class="control-label col-sm-4">Insurance</label>
						<div class=" col-sm-6" id="extFilterIns"></div>
					</div>
					<div class="col-sm-4">
						<label class="control-label col-sm-3">Provider</label>
						<div class="col-sm-6" id="extFilterPrvdr"></div>
					</div>
					<div class="col-sm-4">
						<label class="control-label col-sm-3">Hedis</label>
						<div class="col-sm-6" id="extFilterHedisRule"></div>
					</div>
				</div>
				<table id="membershipTable"
					class="table table-striped table-hover table-responsive">

					<thead>
						<tr>
							<th scope="col">Action</th>
							<th scope="col">Provider</th>
							<th scope="col">Hedis Code</th>
							<th scope="col">Rule Description</th>
							<th scope="col">First Name</th>
							<th scope="col">Last Name</th>
							<th scope="col">Date Of Birth</th>
							<th scope="col">Gender</th>
							<th scope="col">Due Date</th>
							<th scope="col">Notes</th>
						</tr>
					</thead>

					<tbody>

					</tbody>

				</table>
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
				<h4 class="modal-title">Membership Hedis Followup</h4>
			</div>
			<div class="modal-body" id="modal-body"></div>
			<div class="modal-footer">
				<button type="button" id="followupSubmit" class="btn btn-default">Submit</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
			</div>
		</div>

	</div>
</div>



<script>
	function myFunction(id) 
	{
			$( "#modal-body" ).html('');
   			$( "#modal-body" ).append('<textarea  id="followup_details"  class="form-control" rows="5" ></textarea>');
   			$( "#modal-body" ).append('<input type="hidden"  value="'+id+'" id="mbr_hedis_msr_id"  class="form-control" />');
   			$('#myModal').modal('show');
   			return false;
	}
	$( "#followupSubmit" ).click(function(event) {
		  
		  var followup_details  = $("#followup_details").val();
		  var  mbr_hedis_msr_id = $("#mbr_hedis_msr_id").val();
		  var restParams1 ="{\"followupDetails\" :\""+ followup_details+"\",\"mbrHedisMsrId\": "+mbr_hedis_msr_id+"}";
		   
		  var source = "${context}/"+'/reports/membershipHedis/followup';
		  
		  $.ajax({
			  dataType: 'json',
              contentType: "application/json;charset=UTF-8",
		      url : source,
		      type: 'POST',
		      data : restParams1,
		      success: function(data, textStatus, jqXHR)
		      {
		          alert("Success");
		      },
		      error: function (jqXHR, textStatus, errorThrown)
		      {
		    	  alert("Error");
		      }
		  });
		  event.preventDefault();
		});
</script>


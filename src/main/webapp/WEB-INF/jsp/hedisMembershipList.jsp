<%@  page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
		
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@  taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
    	  
    	 var $selectHedisRule = $('#extFilterHedisRule');
    	  $.getJSON(getContextPath()+'/hedisMeasureRule/list', function(data){
			    
			     //clear the current content of the select
			     var s = $('<select id=\"hedisRule\" >');
			     //iterate over the data and append a select option
			     $.each(data.data, function(key, val){
			    	 s.append('<option value="'+val.id+'" >' + val.description +'</option>');
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
     		 var hedisRuleList = document.getElementById('hedisRule').options;
     		 var columns = new Array();
     		 
     		columns.push({ "mDataProp": "id", 	"bSearchable" : false,  "asSorting" : [ "asc" ] ,
     						"render": function (data, type, full, meta) {
	      								return '<a href="#" id="'+data+'" onclick="myFunction('+data+')"><span class="glyphicon glyphicon-pencil"></span></a>';
	        						  }
     					});
     		columns.push({ "mDataProp": "mbrProviderList.0.prvdr.name","bSearchable" : true, "bSortable" : true,"sWidth" : "15%"});
     		columns.push({ "mDataProp": "firstName","bSearchable" : true, "bSortable": true,"sWidth" : "10%"  });
     		columns.push({ "mDataProp": "lastName","bSearchable" : true, "bSortable": true,"sWidth" : "10%"  });
     		columns.push({ "mDataProp": "dob","bSearchable" : true, "bSortable": true,"sWidth" : "10%"  });
     		columns.push({ "mDataProp": "genderId.code","bSearchable" : true, "bSortable": true,"sWidth" : "5%" });
     		columns.push({ "mDataProp": "mbrHedisMeasureList.0.dueDate","bSearchable" : true, "bSortable": true,"sWidth" : "10%", "sDefaultContent": ""  });
     		
     		var myTable = $("#membershipTable");
         	var thead = myTable.find("thead");
         	 
     		$.each( hedisRuleList, function( i, value ){
     			if(i < hedisRuleList.length-1){
      			columns.push({ "mDataProp": "mbrHedisMeasureList."+i+".hedisMeasureRule.description","bSearchable" : true, "bSortable" : true,"sWidth" : "10%", "sDefaultContent": "",
      							"render": function (data, type, full, meta) {
      											$.each( hedisRuleList, function( index, value1 ){
			      									if(data == value1.text)
			      										data= 'X';
			      								});
      								 		if(data == 'X')
      								 			return data;
      								 		else
      								 			return '';
		        				          }
      						});
      			
      				$('table').find('tr').each(function(){
             			$(this).find('th').eq(-1).after('<th>'+value.text+'</th>');
             		});
      			}
      			
      		});
      		 
  	        var oTable = $('#membershipTable').dataTable({
  	         
     	     "sAjaxSource" : getContextPath()+'/reports/hedisMembership/list',
     	     "sAjaxDataProp" : 'data.list',
              "aoColumns":  columns,      
     	     "bLengthChange": false,
     	     "iDisplayLength": 10,
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
		<div class="panel panel-primary">
			<div class="panel-heading">Hedis Report </div>
			<div class="panel-body" >
				<div class="table-responsive">
					<div class="col-sm-12">
							<div class="col-sm-3">
								<label class="control-label col-sm-4">Insurance</label>
								 <div class=" col-sm-6" id="extFilterIns">  </div>
							</div>	 
							<div class="col-sm-4">	 
								<label class="control-label col-sm-3">Provider</label>
								 <div class="col-sm-6"  id="extFilterPrvdr"> </div>
							</div>	 
							<div class="col-sm-4">	 
								 <label class="control-label col-sm-3">Hedis</label>
								 <div class="col-sm-6" id="extFilterHedisRule"></div>
							</div>	 
					 	</div>
					<table id="membershipTable" class="table table-striped table-hover table-responsive">
					
						<thead>
							<tr>
								<th scope="col">Notes</th>
								<th scope="col">Provider</th>
								<th scope="col">First Name</th>
								<th scope="col">Last Name</th>
								<th scope="col">Birthday</th>
								<th scope="col">Sex</th>
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

  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog modal-lg">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Membership Hedis Followup</h4>
        </div>
        <div class="modal-body" id="modal-body">
         
        </div>
        <div class="modal-footer">
          <button type="button" id="followupSubmit" class="btn btn-default" >Submit</button>
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
   			$( "#modal-body" ).append('<input type="hidden"  value="'+id+'" id="mbr_id"  class="form-control" />');
   			$('#myModal').modal('show');
   			return false;
	}
	$( "#followupSubmit" ).click(function(event) {
		  
		  var followup_details  = $("#followup_details").val();
		  var  mbr_id = $("#mbr_id").val();
		  var restParams1 ="{\"followupDetails\" :\""+ followup_details+"\",\"mbr\": {\"id\":"+mbr_id+"}}";
		  alert(restParams1);
		   
		  var source = getContextPath()+'/reports/membershipHedis/followup';
		  
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


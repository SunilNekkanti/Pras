<%@  page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
		
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@  taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script>
$(document).ready(function() {
	
		$("#hedisGenerate").click(function(event)
		{
			callHedisGenerate();
		});
	 		
    	 var $selectIns = $('#extFilterIns');
    	  $.getJSON(getContextPath()+'/insurance/list?pageNo=0&pageSize=200', function(data){
			    
			     //clear the current content of the select
			     var s = $('<select id=\"insu\" style=\"width:150px;\">');
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
    		  var insSelectValue= $("#insu option:selected").val();
 			 var $selectPrvdr = $('#extFilterPrvdr');
 	    	  $.getJSON(getContextPath()+'/insurance/providerlist?insId='+insSelectValue, function(data){
 				    
 				     //clear the current content of the select
 				     var s = $('<select id=\"prvdr\" style=\"width:150px;\">');
 				     //iterate over the data and append a select option
 				     $.each(data.data.list, function(key, val){
 				    	 s.append('<option value="'+val.id+'">' + val.name +'</option>');
 				     });
 				     s.append('<option value="9999">All</option>');
 				     s.append('</select>');
 				     $selectPrvdr.html(s);
 			 }).success(function() { 
 				 hedisRuleDropdown(true);
 	    	 });
    	  }
    	  
    	  var columns ;
    	  var hedisRuleDropdown = function(hedisDropDownSet){
    		 
    		  if(hedisDropDownSet)
    		  	$('#hedisRule').find('option').remove();
				
				var $selectHedisRule = $('#extFilterHedisRule');
				var insSelectValue1 = $("#insu option:selected").val();
				//var hedisSelectValue1 = $("#").val();
		 		 if( $("#insu option:selected").val() == null){
		     		 insSelectValue1 = 1;
		     	 }
		 		
		    	  $.getJSON(getContextPath()+'/hedisMeasureRule/list?insId='+insSelectValue1, function(data){
		    		  if(hedisDropDownSet)
		    		  {
		    			  //clear the current content of the select
						     var s = $('<select id=\"hedisRule\" style=\"width:150px;\">');
						     //iterate over the data and append a select option
						     $.each(data.data, function(key, val){
						    	 s.append('<option value="'+val.id+'" >' + val.description +'</option>');
						     });
						     s.append('<option value="9999">All</option>');
						     s.append('</select>');
						     $selectHedisRule.html(s);
		    		  }
				 });
    	  }
    	  
    	  var callHedisGenerate = function(){

				columns = new Array();
	     		columns.push({ "mDataProp": "id", 	"bSearchable" : false,  "asSorting" : [ "asc" ] ,"sClass": "center","sWidth" : "3%",
	     						"render": function (data, type, full, meta) {
		      								return '<a href="#" id="'+data+'" onclick="myFunction('+data+')"><span class="glyphicon glyphicon-pencil"></span></a>';
		        						  }
	     					});
	     		columns.push({ "mDataProp": "mbrProviderList.0.prvdr.name","bSearchable" : true, "bSortable" : true,"sClass": "center","sWidth" : "10%"});
	     		columns.push({ "mDataProp": "firstName","bSearchable" : true, "bSortable": true,"sClass": "center","sWidth" : "10%"  });
	     		columns.push({ "mDataProp": "lastName","bSearchable" : true, "bSortable": true,"sClass": "center","sWidth" : "10%"  });
	     		columns.push({ "mDataProp": "dob","bSearchable" : true, "bSortable": true,"sClass": "center","sWidth" : "5%"  });
	     		columns.push({ "mDataProp": "genderId.code","bSearchable" : true, "bSortable": true,"sClass": "center","sWidth" : "5%" });
	     		columns.push({ "mDataProp": "mbrHedisMeasureList.0.dueDate","bSearchable" : true, "bSortable": true,"sClass": "center","sWidth" : "5%", "sDefaultContent": ""  });
	     		
	     		var myTable = $("#membershipTable");
	     		var thead = myTable.find("thead");  
	     		$("#membershipTable th").each(function() {
					if($(this).attr("role") == "row"){
					}else{
						$(this).remove();
					}
							
       			}); 
	     		
	     		var hedisRuleList = document.getElementById('hedisRule').options;
	     		
	     		$.each( hedisRuleList, function(m, value ){
	     			if(m < hedisRuleList.length-1){
	     				$('table').find('tr').each(function(){
	     					if(value.text == $("#hedisRule option:selected").text() || $("#hedisRule option:selected").text() == "All")
	             				$(this).find('th').eq(-1).after('<th> <center>'+value.text+'</center></th>');
	     				});
	     			}
	     		});
					
				
	     		$.each( hedisRuleList, function( i, value ){
	     			if(i < hedisRuleList.length-1){
	     				if(value.text == $("#hedisRule option:selected").text() || $("#hedisRule option:selected").text() == "All")
	     				{
		     				columns.push({ "mDataProp": "mbrHedisMeasureList[ ].hedisMeasureRule.description","bSearchable" : false, "bSortable" : false,"sClass": "center","sWidth" : "5%", "sDefaultContent": "",
		      							    "render": function (data, type, full, meta) {
		      							    	full.mbrHedisMeasureList.forEach(function( item ) {
		      							    		if(item.activeInd == 'N' ){
		      							    			data = data.replace(item.hedisMeasureRule.description,'');
		      							    		}
		      							    	});
				      									   var returnType ='';
				      										if(data.indexOf(value.text) >= 0)
				      											return 'X';
				      										else
			      											return '';
				      								  
		      								}
		      						  });	
	     				}	
	      			}
	      		});
	     		callDatableWithChangedDropDown();
    		  
    	  }
    	  
    	var callDatableWithChangedDropDown = function(){
    		   var insSelectValue= $("#insu option:selected").val();
    		   var prvdrSelectValue= $("#prvdr option:selected").val();
    		   var hedisRuleSelectValue= $("#hedisRule option:selected").val();
    		   
    		   var ruleArray = new Array;
    		    $("#hedisRule option").each  ( function() {
    		    	ruleArray.push ( $(this).val() );
    		    });

    		    
    		   if ( $.fn.DataTable.isDataTable('#membershipTable') ) {
  						$('#membershipTable').DataTable().destroy();
			   }
				$('#membershipTable tbody').empty();
    		   GetMembershipByInsPrvdrHedisRule(insSelectValue,prvdrSelectValue,hedisRuleSelectValue, ruleArray, columns);
    	}  
    	     
    	$(document.body).on('change',"#insu",function (e) {
    		if ( $.fn.DataTable.isDataTable('#membershipTable') ) {
					$('#membershipTable').DataTable().destroy();
	   		}
    		$('#membershipTable tbody').empty();
    		providerDropdown();
  		});
    	
    	$(document.body).on('change',"#prvdr",function (e) {
    		if ( $.fn.DataTable.isDataTable('#membershipTable') ) {
					$('#membershipTable').DataTable().destroy();
	   		}
    		$('#membershipTable tbody').empty();
  		});
  	
  	  $(document.body).on('change',"#hedisRule",function (e) {
  		if ( $.fn.DataTable.isDataTable('#membershipTable') ) {
			$('#membershipTable').DataTable().destroy();
			}
  			$('#membershipTable tbody').empty();
  			hedisRuleDropdown(false);
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
		   var sSearchHedisRule = paramMap.sSearchHedisRule;
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
		   restParams.push({"name" : "sSearchHedisRule" , "value" : sSearchHedisRule  });
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
             		if($('#hedisRule').val() == 9999)
             		$('#membershipTable').width(3000);
            },
              error : function (e) {
              }
          } );
     	}
     	
     	
     	  GetMembershipByInsPrvdrHedisRule = function (insId, prvdrId, hedisRuleId,ruleArray, aoColumns) {
      		
  	        var oTable = $('#membershipTable').removeAttr( "width" ).dataTable({  
  	        	"sDom": 'Bfrtip',
	        	 "buttons": [
	        	             {
	        	                 extend: 'excelHtml5',
	        	                 title: 'Hedis Measure RuleTable Export'
	        	             }
	        	             
		                   ],
  	         "bDestroy" : true,	
     	     "sAjaxSource" : getContextPath()+'/reports/hedisMembership/list',
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
                    {"name": "sSearchHedisRule", "value": hedisRuleId },
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
			<div class="panel-heading">Hedis Report </div>
			<div class="panel-body" >
				<div class="table-responsive">
					<div class="col-sm-12">
							<div class="col-sm-3">
								<label class="control-label col-sm-4">Insurance</label>
								 <div class=" col-sm-8" id="extFilterIns">  </div>
							</div>	 
							<div class="col-sm-3">	 
								<label class="control-label col-sm-3">Provider</label>
								 <div class="col-sm-9"  id="extFilterPrvdr"> </div>
							</div>	 
							<div class="col-sm-3">	 
								 <label class="control-label col-sm-5">Hedis Measures</label>
								 <div class="col-sm-7" id="extFilterHedisRule"></div>
							</div>	
							<div class="col-sm-3">	 
								 <button type="button" id="hedisGenerate" class="btn btn-success btn-sm btn-xs">Generate</button>
							</div>	 
					 	</div>
					<table id="membershipTable" class="table table-striped table-hover table-responsive">
					
						<thead>
							<tr>
								<th scope="col" role="row"> Notes</th>
								<th scope="col" role="row">Provider</th>
								<th scope="col" role="row">First Name</th>
								<th scope="col" role="row">Last Name</th>
								<th scope="col" role="row">Birthday</th>
								<th scope="col" role="row">Sex</th>
								<th scope="col" role="row">Due Date</th>
								
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
          <button type="button" id="hedisGenerate"  class="btn btn-default" data-dismiss="modal">Cancel</button>
        </div>
      </div>
      
    </div>
  </div>
 
<script>
	function myFunction(id) 
	{
			var ruleMap = {};
			
			$("#hedisRule > option").each(function() {
			    ruleMap[this.text] = this.value;
			});
			
			$( "#modal-body" ).html('');
   			
   			$('#membershipTable tr').each(function(index) {
   				
   				if(id == ($('#membershipTable tr:eq('+index+') td:eq(0) a').attr('id')))
   				{
   					$('#membershipTable tr:eq('+index+') td').each(function(tdIndex) {
   						if($(this).text() == 'X')
   							{
   								var label = $.trim($('#membershipTable tr:eq(0) th:eq('+tdIndex+')').text());
   								var ruleId = ruleMap[label];
   								$( "#modal-body" ).append('<input type="checkbox" name="rule_group[]"  value="'+ruleId+'"/> <label>'+label+'</label>');
   							}
   						
   					});
   				}		
   			
   			});
   			$( "#modal-body" ).append('<textarea  id="followup_details"  class="form-control" rows="5" ></textarea>');
   			$( "#modal-body" ).append('<br /><br /><textarea  id="followup_history" readonly class="form-control" rows="5" ></textarea>');
   			$( "#modal-body" ).append('<input type="hidden"  value="'+id+'" id="mbr_id"  class="form-control" />');
   			
		  var  mbr_id =id;
		  var followup_text = $("#followup_history");
		  
		  var source = getContextPath()+'reports/membershipHedis/'+id+'/followupDetails';
		  
		  $.ajax({
			  dataType: 'json',
             contentType: "application/json;charset=UTF-8",
		      url : source,
		       success: function(data, textStatus, jqXHR)
		      {
		          $.each(data.data, function(key, val)
		          {
				      followup_text.append(" >>>> "+val.createdDate+ " >>>>  " +val.createdBy+ " >>>> ");			      
				      followup_text.append(" \n");				      
				      followup_text.append(val.followupDetails);
				      followup_text.append("  \n");
				      followup_text.append(" \n");
		         })
				     
		          $('#myModal').modal('show');
		      },
		      error: function (jqXHR, textStatus, errorThrown)
		      {
		    	  alert("Error");
		      }
		  });
   			
   			return false;
	}
	$('select').css({'width': 150});
	$( "#followupSubmit" ).click(function(event) {
		
		 
		  if($("#followup_details").val().length <= 5)
		  {
		   		alert(" Followup Details must be minimum 5 charactes");
		   		return false;
		 }
		  var followup_details  = $("#followup_details").val();
		  var  mbr_id = $("#mbr_id").val();
		  
		  var ruleIds = [];
		  $.each($("input[name='rule_group[]']:checked"), function() {
			 ruleIds.push($(this).val());
			});
		  var restParams1 ="{\"followupDetails\" :\""+ followup_details+"\",\"mbr\": {\"id\":"+mbr_id+"},\"ruleIds\":"+JSON.stringify(ruleIds)+"}";
		   alert("restParams1"+restParams1);
		  var source = getContextPath()+'/reports/membershipHedis/followup';
		  
		  $.ajax({
			  dataType: 'json',
              contentType: "application/json;charset=UTF-8",
		      url : source,
		      type: 'POST',
		      data : restParams1,
		      success: function(data, textStatus, jqXHR)
		      {
		          alert("Followup Success Done");
		          $('#myModal').modal('hide');
		      },
		      error: function (jqXHR, textStatus, errorThrown)
		      {
		    	  alert("Error");
		      }
		  });
		  event.preventDefault();
		});
</script>


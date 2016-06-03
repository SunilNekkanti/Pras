<%@  page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@  taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<script>
$(document).ready(function() {
		
		$("#problemGenerate").click(function(event)
		{
			callProblemGenerate();
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
			 problemRuleDropdown(false);
			 providerDropdown();
			 
		 });
		 
		 var providerDropdown = function(){
			 $("#problemGenerate").hide();
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
 			 });
    	  }
    	  
    	  var columns ;
    	  var problemRuleDropdown = function(problemDropDownSet){
    	
    		if(problemDropDownSet){
    			$('#problemRule').find('option').remove();
    			if ( $.fn.DataTable.isDataTable('#membershipTable') ) {
					$('#membershipTable').DataTable().destroy();
	   			}
    			$('#membershipTable tbody').empty();
    		}
    		  	
				
			var $selectProblemRule = $('#extFilterProblemRule');
			var insSelectValue1 = $("#insu option:selected").val();
			//var problemSelectValue1 = $("#").val();
		 	 if( $("#insu option:selected").val() == null){
		    	 insSelectValue1 = 1;
		     }
		 	
		     var insSelectValue= $("#insu option:selected").val();
		 	 var $selectPrvdr = $('#extFilterPrvdr');
		 	 var restParams = new Array();
		 	 restParams.push({"name" : "pageSize", "value" : 12});
		 	 restParams.push({"name" : "pageNo", "value" : 0 });
		 	 restParams.push({"name" : "insId" , "value" :  insSelectValue });
		 	 restParams.push({"name" : "effYear" , "value" : 2016  });
		 	 $.ajax({
		 		  method: "GET",
		 		  url: getContextPath()+"/problem/list",
		 		  data: restParams
		 	})
		 	 .done(function( data ) {
		 		 var s = $('<select id=\"problemRule\" style=\"width:150px;\">');
				 //iterate over the data and append a select option
				 $.each(data.data.list, function(key, val){
				   	 s.append('<option value="'+val.id+'" >' + val.description +'</option>');
				 });
				 s.append('<option value="9999">All</option>');
				 s.append('</select>');
				 $selectProblemRule.html(s);	
				 $("#problemGenerate").show();
		 	});
		    	 
    	  }
    	  
    	  var callProblemGenerate = function(){

				columns = new Array();
	     		columns.push({ "mDataProp": "id", 	"bSearchable" : false,  "asSorting" : [ "asc" ] ,"sClass": "center","sWidth" : "3%",
	     						"render": function (data, type, full, meta) {
		      								return '<a href="javascript:void(0)" id="'+data+'" onclick="myFunction('+data+',\''+full.lastName+'\',\''+full.firstName+'\')"><span class="glyphicon glyphicon-pencil"></span></a>';
		        						  }
	     					});
	     		columns.push({ "mDataProp": "mbrProviderList.0.prvdr.name","bSearchable" : true, "bSortable" : true,"sClass": "center","sWidth" : "15%"});
	     		columns.push({ "mDataProp": "lastName","bSearchable" : true, "bSortable": true,"sClass": "center","sWidth" : "8%"  });
	     		columns.push({ "mDataProp": "firstName","bSearchable" : true, "bSortable": true,"sClass": "center","sWidth" : "7%"  });
	     		columns.push({ "mDataProp": "dob","bSearchable" : true, "bSortable": true,"sClass": "center","sWidth" : "5%"  });
	     		columns.push({ "mDataProp": "genderId.code","bSearchable" : true, "bSortable": true,"sClass": "center","sWidth" : "5%" });
	     		columns.push({ "mDataProp": "mbrProblemList.0.startDate","bSearchable" : true, "bSortable": false,"sClass": "center","sWidth" : "5%", "sDefaultContent": "",
	     			 "render": function (data) {
		   		   		    if(data == null) return null;
	   		        		var date = new Date(data);
	   	        			var month = date.getMonth() + 1;
	   	       				 return (month > 9 ? month : "0" + month) + "/" + date.getDate() + "/" + date.getFullYear();
	   		   	 		 }	
	     		});
	     		columns.push({ "mDataProp": "mbrProblemList.0.resolvedDate","bSearchable" : true, "bSortable": false,"sClass": "center","sWidth" : "5%", "sDefaultContent": "",
	     			 "render": function (data) {
		   		   		    if(data == null) return null;
	   		        		var date = new Date(data);
	   	        			var month = date.getMonth() + 1;
	   	       				 return (month > 9 ? month : "0" + month) + "/" + date.getDate() + "/" + date.getFullYear();
	   		   	 		 }	
	     		});
	     		
	     		var myTable = $("#membershipTable");
	     		var thead = myTable.find("thead");  
	     		$("#membershipTable th").each(function() {
					if($(this).attr("role") == "row"){
					}else{
						$(this).remove();
					}
							
       			}); 
	     		
	     		var problemRuleList = document.getElementById('problemRule').options;
	     		
	     		$.each( problemRuleList, function(m, value ){
	     			if(m < problemRuleList.length-1){
	     				$('#membershipTable').find('tr').each(function(){
	     					if(value.text == $("#problemRule option:selected").text() || $("#problemRule option:selected").text() == "All")
	             				$(this).find('th').eq(-1).after('<th> <center>'+value.text+'</center></th>');
	     				});
	     			}
	     		});
					
				
	     		$.each( problemRuleList, function( i, value ){
	     			if(i < problemRuleList.length-1){
	     				if(value.text == $("#problemRule option:selected").text() || $("#problemRule option:selected").text() == "All")
	     				{
		     				columns.push({ "mDataProp": "mbrProblemList[ ].pbm.description","bSearchable" : false, "bSortable" : false,"sClass": "center","sWidth" : "5%", "sDefaultContent": "",
		      							    "render": function (data, type, full, meta) {
		      							    	full.mbrProblemList.forEach(function( item ) {
		      							    		if(item.activeInd == 'N' ){
		      							    			data = data.replace(item.problemMeasureRule.description,'');
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
    	  
    	  function callDatableWithChangedDropDown(){
    		   var insSelectValue= $("#insu option:selected").val();
    		   var prvdrSelectValue= $("#prvdr option:selected").val();
    		   var problemRuleSelectValue= $("#problemRule option:selected").val();
    		   
    		   var ruleArray = new Array;
    		    $("#problemRule option").each  ( function() {
    		    	ruleArray.push ( $(this).val() );
    		    });

    		    
    		   if ( $.fn.DataTable.isDataTable('#membershipTable') ) {
  						$('#membershipTable').DataTable().destroy();
			   }
				$('#membershipTable tbody').empty();
    		   GetMembershipByInsPrvdrProblemRule(insSelectValue,prvdrSelectValue,problemRuleSelectValue, ruleArray, columns);
    	}  
    	     
    	$(document.body).on('change',"#insu",function (e) {
    		problemRuleDropdown(true);
    		providerDropdown();
    		
  		});
    	
    	$(document.body).on('change',"#prvdr",function (e) {
    		if ( $.fn.DataTable.isDataTable('#membershipTable') ) {
					$('#membershipTable').DataTable().destroy();
	   		}
    		$('#membershipTable tbody').empty();
  		});
  	
  	  $(document.body).on('change',"#problemRule",function (e) {
  		if ( $.fn.DataTable.isDataTable('#membershipTable') ) {
			$('#membershipTable').DataTable().destroy();
			}
  			$('#membershipTable tbody').empty();
  			
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
		   var sSearchProblemRule = paramMap.sSearchProblemRule;
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
		   restParams.push({"name" : "sSearchProblemRule" , "value" : sSearchProblemRule  });
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
             		if($('#problemRule').val() == 9999)
             		$('#membershipTable').width(3000);
            },
              error : function (e) {
              }
          } );
     	}
     	
     	
     	  GetMembershipByInsPrvdrProblemRule = function (insId, prvdrId, problemRuleId,ruleArray, aoColumns) {
      		
  	        var oTable = $('#membershipTable').removeAttr( "width" ).dataTable({  
  	        	"sDom": 'Bfrtip',
	        	 "buttons": [
	        	             {
	        	                 extend: 'excelHtml5',
	        	                 title: 'Problem Measure RuleTable Export'
	        	             }
	        	             
		                   ],
  	         "bDestroy" : true,	
     	     "sAjaxSource" : getContextPath()+'/reports/problemMembership/list',
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
                    {"name": "sSearchProblemRule", "value": problemRuleId },
                    {"name": "ruleIds", "value": ruleArray }
                );
             },        
     	     "fnServerData" : datatable2RestMembership
     	});
  	        
     }


     	$('select').css({'width': 150});
     	
     	$( "#followupSubmit" ).click(function(event) {
     		var rulesList = [];
     		 var ruleIds = []; var dos = [];
     		$("span[name='dosError[]']").html("");
     		
    		  $.each($("input[name='rule_group[]']"), function(i) {
    			  var ruleMap ={}; 
    			  if(this.checked)
    			  {
    				  ruleIds.push($(this).val());
    				  
    				  if($("input[name='dos[]']").eq(i).val().length < 1)
    				  {
    					  $("span[name='dosError[]']").eq(i).html("Date of Service required");
    				  }
    				  else
    					{
    					  dos.push($("input[name='dos[]']").eq(i).val());
    					  ruleMap[$(this).val()] = $("input[name='dos[]']").eq(i).val();
    					  rulesList.push(ruleMap);
    					}	  
    			  }	 
    		  });
     		 
     		  if($("#followup_details").val().length <= 5)
     		  {
     		   		alert(" Followup Details must be minimum 5 charactes");
     		   		return false;
     		 }
     		  var followup_details  = $("#followup_details").val();
     		  var  mbr_id = $("#mbr_id").val();
     		  
     		  var restParams1 ="{\"followupDetails\" :\""+ followup_details+"\",\"mbr\": {\"id\":"+mbr_id+"},\"mbrProblemMeasureIds\":"+JSON.stringify(rulesList)+"}";
     		  var source = getContextPath()+'/reports/membershipProblem/followup';
     		  
     		  $.ajax({
     			  dataType: 'json',
                   contentType: "application/json;charset=UTF-8",
     		      url : source,
     		      type: 'POST',
     		      data : restParams1,
     		      success: function(data, textStatus, jqXHR)
     		      {
     		    	  
     		          alert("Followup Success Done");
     		          $(".clrRed").text("Problem Followup Notes recorded successfully");
     		          $('#myModal').modal('hide');
     		         callDatableWithChangedDropDown();
     		      },
     		      error: function (jqXHR, textStatus, errorThrown)
     		      {
     		    	  alert("Error");
     		      }
     		  });
     		  event.preventDefault();
     		});	
 } );

 function myFunction(id,lastName,firstName) 
{
	   if ( $.fn.DataTable.isDataTable('#mbrProblemMeasureTable') ) {
				$('#mbrProblemMeasureTable').DataTable().destroy();
		}
		$('#mbrProblemMeasureTable tbody').empty();
		
		
	 var datatable2MbrProblemMeasure = function(sSource, aoData, fnCallback) {
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
		 
		   //create new json structure for parameters for REST request
		   var restParams = new Array();
		  restParams.push({"name" : "problemRuleId", "value" :  $("#problemRule").val() });
		
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
 	
 	$('#mbrProblemMeasureTable').dataTable({
 	     "sAjaxSource" : getContextPath()+'membership/'+id+'/problemMeasureList',
 	     "sAjaxDataProp" : 'data',
 	     "aoColumns": [
                        { "mDataProp": "id", "bSearchable" : false, "bVisible" : true, "asSorting" : [ "asc" ]  },
                        { "mDataProp": "problemMeasureRule.description","bSearchable" : true, "bSortable" : true,"sWidth" : "45%"},
                        { "mDataProp": "dos","bSearchable" : true, "bSortable" : true,"sWidth" : "45%"}
                       
                    ],
           "aoColumnDefs": [ 
								{ "sName": "id", "aTargets": [ 0 ],
									   "render": function ( data, type, full, meta ) {
								       return '<input type="checkbox" class="chkRule" name="rule_group[]"   id="'+data+'" value="'+data+'"/>';
								}},
								{ "sName": "problemMeasureRule.description", "aTargets": [1]},
								{ "sName": "dos", "aTargets": [ 2 ],
									"render": function ( data, type, full, meta ) {
									       return '<input type="text" class="'+full.id+'" name="dos[]" readonly /><span class="clrRed" name ="dosError[]"></span>';
								}}
                             
           ],          
 	     "bLengthChange": false,
 	     "paging": false,
 	     "info": false,
 	     "bFilter": false,
 	     "bProcessing": true,
 	     "bServerSide" : true,
 	     "fnServerData" : datatable2MbrProblemMeasure
 	});
 	
			$(".clrRed").html("");
			
			
		//	$( "#modal-body" ).html('');
			
			$(".modal-title").html(lastName+","+firstName+" - Problem Followup");
			$( "#modal-body .notes" ).remove();
			$( "#modal-body .history" ).remove();
   			$( "#modal-body #mbr_id" ).remove();
 			$( "#modal-body" ).append('<div class="notes"> <br /> Notes <textarea  id="followup_details"  class="form-control" rows="5" ></textarea></div>');
 			$( "#modal-body" ).append('<div class="history"><br /> History<textarea  id="followup_history" readonly class="form-control" rows="5" ></textarea></div>');
 			$( "#modal-body" ).append('<input type="hidden"  value="'+id+'" id="mbr_id"  class="form-control" />');
 			
		  var  mbr_id =id;
		  var followup_text = $("#followup_history");
		  
		  var source = getContextPath()+'reports/membershipProblem/'+id+'/followupDetails';
		  
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
    </script>

<div class="panel-group">
	<div class="panel panel-success">
		<div class="panel-heading">
			Problem Report <span class="clrRed"> </span>
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
						<label class="control-label col-sm-5">Problem Measures</label>
						<div class="col-sm-7" id="extFilterProblemRule"></div>
					</div>
					<div class="col-sm-3">
						<button type="button" id="problemGenerate"
							class="btn btn-success btn-sm btn-xs">Generate</button>
					</div>
				</div>
				<table id="membershipTable"
					class="table table-striped table-hover table-responsive">

					<thead>
						<tr>
							<th scope="col" role="row">Notes</th>
							<th scope="col" role="row">Provider</th>
							<th scope="col" role="row">Last Name</th>
							<th scope="col" role="row">First Name</th>
							<th scope="col" role="row">Birthday</th>
							<th scope="col" role="row">Sex</th>
							<th scope="col" role="row">Start Date</th>
							<th scope="col" role="row">Resolved Date</th>

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
				<h4 class="modal-title">Membership Problem Followup</h4>
			</div>
			<div class="modal-body" id="modal-body">
				<table id="mbrProblemMeasureTable"
					class="table table-striped table-hover table-responsive">
					<thead>
						<tr>
							<th scope="col">Select</th>
							<th scope="col">Problem Measure</th>
							<th scope="col">Date of Service</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
			<div class="modal-footer">
				<button type="button" id="followupSubmit" class="btn btn-default">Submit</button>
				<button type="button" id="problemGenerate" class="btn btn-default"
					data-dismiss="modal">Cancel</button>
			</div>
		</div>

	</div>
</div>
<style>
#mbrProblemMeasureTable {
	width: 100% !important;
}
</style>
<script>
  jQuery( document ).ready(function( $ ) {
	    //set initial state.
	    $('body').on('click',".chkRule", function(){
	    	if($(this).is(':checked'))
	    	{
	    		$("."+this.id).addClass( "datepicker" );
	    		$(".datepicker").datepicker({maxDate:"0"}).datepicker("setDate",new Date());
	    		$(".datepicker" ).show();
	    	}
	    	else
	    	{
	    		$(".datepicker" ).datepicker( "destroy" );
	    		$("."+this.id).removeClass("datepicker" );
	    		$("."+this.id).removeClass("hasDatepicker" );
	    		$("."+this.id).removeAttr('id');
	    		$("."+this.id).val('');	    		
	    	}
	       		
	    });
	});
  </script>

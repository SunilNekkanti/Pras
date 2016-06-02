<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="springForm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="context"
	value="${pageContext.request.contextPath}/${userpath}" />
<script type="text/javascript">
    
$(document).ready(function() {
	
	var checkedICDItemsMap = {};
	
	$("#addICD").click(function (e)    {
       	var icdCodes = $("#icdCodes"); 
       	
       	for (var e in checkedICDItemsMap){
       		var value = checkedICDItemsMap[e];
       		var optionExists = ($('#icdCodes option[value=' + e + ']').length > 0);
               if(!optionExists)
               {
            	   icdCodes.append('<option value ="'+e+'" selected ="selected">'+value+'</option>');
               }
       	} 
	});

	$("#removeICD").click(function (e) {
         	$('#icdCodes option:selected').each(function () {
         			$(this).remove();                       
          });
             e.preventDefault();
    });

   $('#icdListTable tbody').on('click', 'input[type="checkbox"]', function (e) {

	  	 if ($(this).is(':checked')) {
	  		checkedICDItemsMap[$(this).attr('id')] = $(this).attr('value');		   		   
	        	
	    } else {
	    		delete checkedICDItemsMap[$(this).attr('id')];
	    }
	} ); 
   
 var datatable2Rest = function(sSource, aoData, fnCallback) {
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
  restParams.push({"name" : "pageSize", "value" : pageSize});
  restParams.push({"name" : "pageNo", "value" : pageNum });
  restParams.push({"name" : "sort", "value" : sortName });
  restParams.push({"name" : "sortdir", "value" : sortDir });
  restParams.push({"name" : "sSearch" , "value" : paramMap.sSearch  });

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
  	
  	var hedisRuleId =  $('#id').val();
  	
        // ICD POPUP   	
   	$('#icdListTable').dataTable({
   	     "sAjaxSource" : getContextPath()+'/icd/icdMeasureLists',
   	     "sAjaxDataProp" : 'data.list',
   	     "aoColumns": [
			 { "mData" : "id",  
			  "render" : function(data, type, full, meta) {
											return '<input type="checkbox" name="chkbox"  id="'+data+'" value="'+full.code+' ('+full.description+')"/>';
									}, 
						  "sWidth" : "5%", "bSearchable" : false,  "asSorting" : [ "asc" ]
					 },
                          { "mDataProp": "code","bSearchable" : true, "bSortable" : true,"sWidth" : "10%"},
                          { "mDataProp": "description","bSearchable" : true, "bSortable" : true,"sWidth" : "85%"}
                        
                      ],
   	     "bLengthChange": false,
   	     "sPaginationType": "full_numbers",
   	     "bProcessing": true,
   	     "bServerSide" : true,
   	     "fnRowCallback": function(row, data, dataIndex){
   	         // If row ID is in the list of selected row IDs
                if ( (data.id in checkedICDItemsMap) ) {
               	 $(row).find('input[type="checkbox"]').prop('checked', true);
                }
   	      },
   	      "fnServerData" : datatable2Rest
   	});
          	
} );
        
</script>

<div class="panel  panel-success">
	<div class="panel-heading">
		Problem <a class="btn btn-danger pull-right btn-xs white-text"
			href="${context}/problemList"> <span
			class="glyphicon glyphicon-plus-sign "></span>Problem List
		</a>
	</div>
	<div class="panel-body" id="tablediv">
		<springForm:form id="problem" method="POST" commandName="problem"
			action="${context}/problem/save.do" class="form-horizontal"
			role="form">
			<springForm:hidden path="id" />

			<div class="form-group required">
				<label class="control-label col-sm-2" for="effYear">Effective
					Year (YYYY)</label>
				<div class="col-sm-6">
					<springForm:select path="effectiveYear" class="form-control"
						id="effectiveYear">
						<springForm:options items="${effYearList}" />
					</springForm:select>
					<springForm:errors path="effectiveYear"
						cssClass="error text-danger" />
				</div>
			</div>

			<div class="form-group required">
				<label class="control-label   col-sm-2" for="description">Description</label>
				<div class="col-sm-6">
					<springForm:input path="description" class="form-control"
						maxlength="500" id="description" placeholder="description" />
					<springForm:errors path="description" cssClass="error text-danger" />
				</div>
			</div>

			<div class="form-group required">
				<label class="control-label col-sm-2" for="insurance">Insurance</label>
				<div class="col-sm-6">
					<springForm:select path="insId" class="form-control" id="insurance">
						<springForm:option value="${null}" label="Select One" />
						<springForm:options items="${insuranceList}" itemValue="id"
							itemLabel="name" />
					</springForm:select>
					<springForm:errors path="insId" cssClass="error text-danger" />
				</div>
			</div>




			<div class="form-group required">
				<label class="control-label col-sm-2" for="icd">ICD Code</label>
				<div class="col-sm-6">
					<springForm:select multiple="true" path="icdCodes"
						class="form-control" size="9" items="${icdMeasureListAjax}"
						itemLabel="codeAndDescription" itemValue="id" />
					<springForm:errors path="icdCodes" cssClass="error text-danger" />
				</div>
				<div class="col-sm-2">
					<a href="#" data-toggle="modal" data-target="#icdModal"
						class="btn btn-success btn-sm white-text"> <span
						class="glyphicon glyphicon-plus-sign"></span>ICD
					</a> <a href="#" id="removeICD"
						class="btn btn-success btn-sm white-text"> <span
						class="glyphicon glyphicon-minus-sign"></span>ICD
					</a>
				</div>
			</div>

			<div class="col-sm-offset-6 col-sm-4">
				<c:choose>
					<c:when test="${problem.id != null}">
						<button type="submit" class="btn btn-success btn-sm"
							id="updateButton" name="update">Update</button>
						<button type="submit" class="btn btn-success btn-sm"
							id="deleteButton" name="delete">Delete</button>
					</c:when>
					<c:otherwise>
						<button type="submit" class="btn btn-success btn-sm"
							id="updateButton" name="add">Add</button>
						<button type="submit" class="btn btn-success btn-sm"
							id="resetButton">Reset</button>
					</c:otherwise>
				</c:choose>
			</div>
		</springForm:form>

	</div>
</div>

<div id="icdModal" class="modal fade" role="dialog">
	<div class="modal-dialog modal-lg">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">ICD Measure List</h4>
			</div>
			<div class="modal-body">
				<div class="panel-group">
					<div class="panel panel-success">

						<div class="panel-body">
							<div class="table-responsive">
								<table id="icdListTable"
									class="display table-responsive  table table-striped table-hover">
									<thead>
										<tr>
											<th scope="col">Select</th>
											<th scope="col">ICD Code</th>
											<th scope="col">Description</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer" style="text-align: left;">
				<button type="button" id="addICD" class="btn btn-success btn-sm"
					data-dismiss="modal">ADD</button>
			</div>
		</div>
	</div>
</div>
<script>
$(document).ready(function() {
	
    $("#effectiveYear").keydown(function(event) {
    	 if( !(event.keyCode == 8                                // backspace
    		        || event.keyCode == 46                              // delete
    		        || (event.keyCode >= 35 && event.keyCode <= 40)     // arrow keys/home/end
    		        || (event.keyCode >= 48 && event.keyCode <= 57)     // numbers on keyboard
    		        || (event.keyCode >= 96 && event.keyCode <= 105))   // number on keypad
    		        ) {
    		            event.preventDefault();     // Prevent character input
    		    }
    });
});
$( "#problem" ).submit(function( event ) {
	$(".text-danger").html('');
	$( "has-error" ).remove();
	$('#icdCodes option').prop('selected', true);
	
	var error_count=0;
	var insurance = $("#insurance").val();
	if (!insurance) {
		$("#insurance").closest( "div" ).addClass( "has-error" );
		$('#insurance').closest( "div" ).find('span').remove();
		$( "#insurance" ).after("<span  class='text-danger'>Select Insurance.</span>" );
		error_count++;
	}
	else
	{
		$('#insurance').closest( "div" ).find('span').remove();
		$("#insurance").closest( "div" ).removeClass( "has-error" );
	}	
	
	var description = $("#description").val().length;
	if (description < 3) {
		$("#description").closest( "div" ).addClass( "has-error" );
		$('#description').closest( "div" ).find('span').remove();
		$( "#description" ).after("<span  class='text-danger'>Description atleast 3 characters</span>" );
		error_count++;
	}
	else
	{
		$('#description').closest( "div" ).find('span').remove();
		$("#description").closest( "div" ).removeClass( "has-error" );
	}	
	
	var effectiveYear = $("#effectiveYear").val();
		if (!effectiveYear) {
			$("#effectiveYear").closest( "div" ).addClass( "has-error" );
			$('#effectiveYear').closest( "div" ).find('span').remove();
			$( "#effectiveYear" ).after("<span  class='text-danger'>Select Effective Year.</span>" );
			error_count++;
		}
		else
		{
			$('#effectiveYear').closest( "div" ).find('span').remove();
			$("#effectiveYear").closest( "div" ).removeClass( "has-error" );
		}	
	    var icdCodes = $("#icdCodes").val();
	  	
		if (!icdCodes) {
			$("#icdCodes").closest( "div" ).addClass( "has-error" );
			$('#icdCodes').closest( ".text-danger" ).find('span').remove();
			$("#icdCodes" ).after("<span  class='text-danger'>Select ICD Codes.</span>" );
			error_count++;
		}
		else
		{
			$('#icdCodes').closest( "div" ).find('icdCodes').remove();
			$("#icdCodes").closest( "div" ).removeClass( "has-error" );
		}
		
	
		
	    if(error_count >0){ event.preventDefault();}
	});

</script>


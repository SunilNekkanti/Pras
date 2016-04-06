<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
    prefix="springForm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <c:set var="context" value="${pageContext.request.contextPath}" />
 <!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>PF Choice head page</title>
<style type="text/css">

 /* Outer */
.popup {
    width:100%;
    height:100%;
    display:none;
    position:fixed;
    top:0px;
    left:0px;
    opacity: "0.1";
    background:rgba(0,0,0,0.75);
}
 
/* Inner */
.popup-inner {
    max-width:700px;
    width:90%;
    padding:20px;
    position:absolute;
    top:50%;
    left:60%;
    opacity: "1";
    -webkit-transform:translate(-50%, -50%);
    transform:translate(-50%, -50%);
    box-shadow:0px 2px 6px rgba(0,0,0,1);
    border-radius:3px;
    background:#fff;
}
 
/* Close Button */
.popup-close {
    width:30px;
    height:30px;
    padding-top:4px;
    display:inline-block;
    position:absolute;
    top:0px;
    right:0px;
    transition:ease 0.25s all;
    -webkit-transform:translate(50%, -50%);
    transform:translate(50%, -50%);
    border-radius:1000px;
    background:rgba(0,0,0,0.8);
    font-family:Arial, Sans-Serif;
    font-size:20px;
    text-align:center;
    line-height:100%;
    opacity: "0"  ;
    color:#fff;
}
 
.popup-close:hover {
    -webkit-transform:translate(50%, -50%) rotate(180deg);
    transform:translate(50%, -50%) rotate(180deg);
    background:rgba(0,0,0,1);
    text-decoration:none;
}
</style>
<script type="text/javascript">
    
$(function() {
    //----- OPEN
    $('[data-popup-open]').on('click', function(e)  {
        var targeted_popup_class = jQuery(this).attr('data-popup-open');
        $('[data-popup="' + targeted_popup_class + '"]').fadeIn(650);
        
        e.preventDefault();
    });
 
    //----- CLOSE
    $('[data-popup-close]').on('click', function(e)  {
        var targeted_popup_class = jQuery(this).attr('data-popup-close');
        $('[data-popup="' + targeted_popup_class + '"]').fadeOut(650);
 
        e.preventDefault();
    });
});


$(document).ready(function() {
	
	var checkedCPTItemsMap = {};
	var checkedICDItemsMap = {};
	
	$("#addCPT").click(function (e)    {
       	var cptCodes = $("#cptCodes"); 
       	
       	for (var e in checkedCPTItemsMap){
       		var value = checkedCPTItemsMap[e];
       		var optionExists = ($('#cptCodes option[value=' + e + ']').length > 0);
               if(!optionExists)
               {
            	   cptCodes.append('<option value ="'+e+'" selected ="selected">'+value+'</option>');
               }
       	} 
	});

	$("#removeCPT").click(function (e) {
         	$('#cptCodes option:selected').each(function () {
         			$(this).remove();                       
          });
             e.preventDefault();
    });


   $('#cptListTable tbody').on('click', 'input[type="checkbox"]', function (e) {

	  	 if ($(this).is(':checked')) {
	  		checkedCPTItemsMap[$(this).attr('id')] = $(this).attr('value');		   		   
	        	
	    	} else {
	    		delete checkedCPTItemsMap[$(this).attr('id')];
	    	}
	   } );
	   
	$("#addICD").click(function (e)    {
       	var cptCodes = $("#icdCodes"); 
       	
       	for (var e in checkedICDItemsMap){
       		var value = checkedICDItemsMap[e];
       		var optionExists = ($('#icdCodes option[value=' + e + ']').length > 0);
               if(!optionExists)
               {
            	   cptCodes.append('<option value ="'+e+'" selected ="selected">'+value+'</option>');
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
  	
  	 // CPT POPUP  
  	 
  	$('#cptListTable').dataTable({
  	     "sAjaxSource" : getContextPath()+'/hedisMeasureRule/'+hedisRuleId+'/cpt/cptMeasureLists',
  	     "sAjaxDataProp" : 'data.list',
  	     "aoColumns": [
						 { "mData" : "id",  
		  				  "render" : function(data, type, full, meta) {
										return '<input type="checkbox" name="cptchkbox"   id="'+data+'" value="'+full.code+' ('+full.shortDescription+')"/>';
									 }, 
					  		"sWidth" : "10%", "bSearchable" : false,  "asSorting" : [ "asc" ]
						 },
                         { "mDataProp": "code","bSearchable" : true, "bSortable" : true,"sWidth" : "10%"}, 
                         { "mDataProp": "shortDescription","bSearchable" : true, "bSortable" : true,"sWidth" : "80%"}
                     ],
  	     "bLengthChange": false,
  	     "sPaginationType": "full_numbers",
  	     "bProcessing": true,
  	     "bServerSide" : true,
  	     "fnRowCallback": function(row, data, dataIndex){
  	         // If row ID is in the list of selected row IDs
               if ( (data.id in checkedCPTItemsMap) ) {
              	 $(row).find('input[type="checkbox"]').prop('checked', true);
               }
  	      },
  	      "fnServerData" : datatable2Rest
  	});
  	
      
        // ICD POPUP   	
   	$('#icdListTable').dataTable({
   	     "sAjaxSource" : getContextPath()+'/hedisMeasureRule/'+hedisRuleId+'/icd/icdMeasureLists',
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
</head>
<body>


<div class="panel with-nav-tabs panel-primary">
		<div class="panel-heading">
						<ul class="nav nav-tabs">
                            <li class="active"><a href="#" data-toggle="tab">Hedis Measure Rule</a></li>
                        </ul>
          </div>
		<div class="panel-body" id="tablediv">
			<springForm:form method="POST" commandName="hedisMeasureRule" action="${context}/hedisMeasureRule/${id}/save.do" class="form-horizontal" role="form">
				<springForm:hidden path="id" />
				<div class="form-group required">
					<label class="control-label col-sm-2" for="hedis">Hedis Code</label>
					<div class="col-sm-6">
						<springForm:select path="hedisMeasure" class="form-control" id="hedisCode" >
				    		<springForm:options items="${hedisMeasureList}" itemValue="id" itemLabel="codeAndDescription"   />
						</springForm:select>
						<springForm:errors path="hedisMeasure.code" cssClass="error text-danger" />
					</div>
				</div>
				
				<div class="form-group required">
					<label class="control-label col-sm-2" for="cpt">CPT Code</label>
					<div class="col-sm-6">
						<springForm:select multiple="true" path="cptCodes" class="form-control"  size="9" items="${cptMeasureListAjax}" itemLabel="codeAndDescription" itemValue="id" />
						<springForm:errors path="cptCodes" cssClass="error text-danger" />
					</div>
					<div class="col-sm-1">
					    <a class="btn" data-popup-open="popup-1" href="#"> <span class="glyphicon glyphicon-plus"></span> ADD </a>
						<a id='removeCPT'> <span class="glyphicon glyphicon-minus"></span> Remove</a>
					 </div>	
				</div>
				 		 
				<div class="form-group required">
					<label class="control-label col-sm-2" for="cpt">ICD Code</label>
					<div class="col-sm-6">
						<springForm:select multiple="true" path="icdCodes" class="form-control"  size="9" items="${icdMeasureListAjax}" itemLabel="codeAndDescription" itemValue="id" />
						<springForm:errors path="icdCodes" cssClass="error text-danger" />
					</div>
					<div class="col-sm-1">
						<a class="btn" data-popup-open="popup-2" href="#">  <span class="glyphicon glyphicon-plus"></span>ADD </a>
						<a id="removeICD"> <span class="glyphicon glyphicon-minus"></span> Remove</a>
					 </div>	
				</div>
				
				<div class="form-group required">
					<label class="control-label col-sm-2" for="year">Effective Year (YYYY)</label>
					<div class="col-sm-6">
						<springForm:input path="effectiveYear" class="form-control" id="effectiveYear" placeholder="Effective Year" />
						<springForm:errors path="effectiveYear" cssClass="error text-danger" />
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-2" for="gender">Gender</label>
					<div class="col-sm-6">
						<springForm:select path="genderId"  class="form-control" id="gender">
							<springForm:option  value="${null}" label="Select One" />
					   		<springForm:options items="${genderList}"  itemValue="id" itemLabel="description"    />
						</springForm:select>
						<springForm:errors path="genderId" cssClass="error text-danger" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label   col-sm-2" for="lowerAgeLimit">Lower Age Limit</label>
					<div class="col-sm-6">
						<springForm:input path="lowerAgeLimit" class="form-control" id="lowerAgeLimit" placeholder="lowerAgeLimit" />
						<springForm:errors path="lowerAgeLimit" cssClass="error text-danger" />
					  </div>
				</div>
				
				<div class="form-group">
					<label class="control-label  col-sm-2" for="upperAgeLimit">Upper Age Limit</label>
					<div class="col-sm-6">
						<springForm:input path="upperAgeLimit" class="form-control" id="upperAgeLimit" placeholder="upperAgeLimit" />
						<springForm:errors path="upperAgeLimit" cssClass="error text-danger" />
					  </div>
				</div>
				
				<div class="form-group">
					<label class="control-label  col-sm-2" for="doseCount">Dose Count</label>
					<div class="col-sm-6">
						<springForm:input path="doseCount" class="form-control" id="doseCount" placeholder="doseCount" />
						<springForm:errors path="doseCount" cssClass="error text-danger" />
					  </div>
				</div>
				
				<div class="form-group">
					<label class="control-label  col-sm-2" for="ageEffectiveFrom" >Age Effective From</label>
					<div class="col-sm-6">
						<fmt:formatDate value="${hedisMeasureRule.ageEffectiveFrom}" var="dateString" pattern="MM/dd/yyyy" />
						<springForm:input path="ageEffectiveFrom" value="${dateString}" class="form-control datepicker"  id="ageEffectiveFrom" placeholder="ageEffectiveFrom" />
						<springForm:errors path="ageEffectiveFrom" cssClass="error text-danger" />
					  </div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="ageEffectiveTo ">Age Effective To</label>
					<div class="col-sm-6">
						<fmt:formatDate value="${hedisMeasureRule.ageEffectiveTo}" var="dateString" pattern="MM/dd/yyyy" />
						<springForm:input path="ageEffectiveTo" value="${dateString}" class="form-control datepicker" id="ageEffectiveTo" placeholder="ageEffectiveTo" />
						<springForm:errors path="ageEffectiveTo" cssClass="error text-danger" />
					  </div>
				</div>
				<div class="col-sm-offset-6 col-sm-4">
					<c:choose>
						 <c:when test="${hedisMeasureRule.id != null}"> 
						 	<button type="submit" class="btn btn-success btn-sm" id="updateButton" name="update" >Update</button>
						 	<button type="submit" class="btn btn-success btn-sm" id="deleteButton" name="delete" >Delete</button>
						 </c:when>
						 <c:otherwise>
							<button type="submit" class="btn btn-success btn-sm" id="updateButton" name="add" >Add</button>
							<button type="submit" class="btn btn-success btn-sm" id="resetButton" >Reset</button>
						</c:otherwise>
						</c:choose>
				</div>
			</springForm:form>
			
</div>
</div>


 
<div class="popup" data-popup="popup-1">
    <div class="popup-inner">
        <div class="panel-group">
		<div class="panel panel-success">
		<div class="panel-heading">CPT Measure List  </div>
		<div class="panel-body" id="tablediv">
				<div class="table-responsive">
		
				<table id="cptListTable" class="display table-responsive  table table-striped table-hover"> 
					<thead>
						<tr>
							<th  scope="col">Select</th> 
							<th  scope="col">CPT Code</th> 
							<th  scope="col">Short Description</th> 
						</tr>
					</thead>

					<tbody >
						
					</tbody>
				</table>
				</div>
		</div>
		
	</div>
</div>
        <p><a data-popup-close="popup-1" href="#" id="addCPT">Add</a></p>
        <a class="popup-close" data-popup-close="popup-1" href="#">x</a>
    </div>
</div>


<div class="popup" data-popup="popup-2">
    <div class="popup-inner">
        <div class="panel-group">
		<div class="panel panel-success">
		<div class="panel-heading">ICD Measure List  </div>
		<div class="panel-body" id="tablediv">
		
				<table id="icdListTable" class="display table-responsive  table table-striped table-hover"> 
					<thead>
						<tr>
							<th  scope="col">Select</th> 
							<th  scope="col">ICD Code</th> 
							<th  scope="col">Description</th> 
						</tr>
					</thead>

					<tbody >
						
					</tbody>
				</table>
		</div>
		
	</div>
</div>
        <p><a data-popup-close="popup-2" href="#" id="addICD">Add</a></p>
        <a class="popup-close" data-popup-close="popup-2" href="#">x</a>
    </div>
</div>
</body>
</html>



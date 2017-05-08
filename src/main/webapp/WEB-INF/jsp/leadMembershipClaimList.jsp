<%@  page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:set var="context"
	value="${pageContext.request.contextPath}/${userpath}" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script
	src="//raw.github.com/botmonster/jquery-bootpag/master/lib/jquery.bootpag.min.js"></script>
	
<script	src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>

<script	src="https://cdn.datatables.net/1.10.15/js/dataTables.bootstrap.min.js"></script>
	
<div class="panel-group">
	<div class="panel panel-success">
		<div class="panel-heading">Claim List<span class="clrRed">${Message}</span>
			<button class="btn btn-danger pull-right btn-xs"	onclick="return claimNew();">
			<span class="glyphicon glyphicon-plus-sign "></span> New Claim</button>
		</div>
		<div class="panel-body" id="tablediv">
			<table id="leadClaimList" class="table table-striped table-hover">
				<thead>
					<tr>
						<th>Claim Type</th>
						<th>Facility Type</th>
						<th>Bill Type</th>
						<th>Frequency Type</th>
						<th>Discharge Status</th>
						<th>Diagnosis</th>
						<th>Start Date</th>
						<th>End Date</th>
						<th>Procedure</th>
						<th>Risk Recon</th>
						
					</tr>
				</thead>

				<tbody id="contentclaimList">

					
				</tbody>
			</table>
			<div class="col-md-12 text-center" id="page_navigationclaimList"></div>
			<div id="show_per_pageclaimList"></div>
			<div id="current_pageclaimList"></div>
		</div>
	</div>
</div>


<script>
        $(document).ready(function() {
        	      
        	var datatable2RestLeadMbrClaims = function(sSource, aoData, fnCallback) {
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
        	var ex = document.getElementById('leadClaimList');
        	if ( ! $.fn.DataTable.isDataTable(ex ) ) {
        	$('#leadClaimList').dataTable({
        	     "sAjaxSource" : "${context}/"+'leadMembership/${id}/claim/list?pageSize=20000',
        	     "sAjaxDataProp" : 'data.list',
        	     "aoColumns": [
        	                   { "mDataProp": "claimType", "bSearchable" : false, "bVisible" : true, "asSorting" : [ "asc" ] },
                               { "mDataProp": "facilityType.shortName", "bSearchable" : false, "bVisible" : true, "asSorting" : [ "asc" ] ,"sDefaultContent": "" },
                               { "mDataProp": "billType.shortName", "bSearchable" : false, "bVisible" : true, "asSorting" : [ "asc" ] ,"sDefaultContent": "" },
                               { "mDataProp": "frequencyType.shortName", "bSearchable" : false, "bVisible" : true, "asSorting" : [ "asc" ],"sDefaultContent": ""  },
                               { "mDataProp": "dischargeStatus", "bSearchable" : false, "bVisible" : true, "asSorting" : [ "asc" ] ,"sDefaultContent": "" },
                               { "mDataProp": "diagnosis", "bSearchable" : false, "bVisible" : true, "asSorting" : [ "asc" ] ,"sDefaultContent": "" },
                               { "mDataProp": "leadMbrClaimDetailsList[].claimStartDate", "bSearchable" : false, "bVisible" : true, "asSorting" : [ "asc" ],
                            	   "mRender": function(data, type, full, meta) {
                                       return full.leadMbrClaimDetailsList[meta.row].claimStartDate;
                                       }
                               },
                               { "mDataProp": "leadMbrClaimDetailsList[].claimEndDate", "bSearchable" : false, "bVisible" : true, "asSorting" : [ "asc" ],
                            	   "mRender": function(data, type, full, meta) {
                                       return full.leadMbrClaimDetailsList[meta.row].claimEndDate;
                                       }
                               },
                               { "mDataProp": "leadMbrClaimDetailsList[].cpt.code", "bSearchable" : false, "bVisible" : true, "asSorting" : [ "asc" ] ,
                            	   "mRender": function(data, type, full, meta) {
                                       return full.leadMbrClaimDetailsList[meta.row].cpt.code;
                                       }
                               },
                               { "mDataProp": "leadMbrClaimDetailsList[].riskReconCosDes", "bSearchable" : false, "bVisible" : true, "asSorting" : [ "asc" ] ,
                            	   "mRender": function(data, type, full, meta) {
                                       return full.leadMbrClaimDetailsList[meta.row].riskReconCosDes;
                                       }}
                           ],
                         
        	     "bLengthChange": false,
        	     "sPaginationType": "full_numbers",
        	     "bProcessing": true,
        	     "bServerSide" : true,
        	     "iDisplayLength": 15,
        	     "fnServerData" : datatable2RestLeadMbrClaims
        	});
        }

        	
    } );
    </script>
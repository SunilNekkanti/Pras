<%@  page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@  taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<title>Frequency Type List</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<c:set var="context"
	value="${pageContext.request.contextPath}/${userpath}" />
<script>
        $(document).ready(function() {
        	
        	var datatable2RestFrequencyType = function(sSource, aoData, fnCallback) {
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
	               		$(".rowClick tbody tr").css('cursor', 'pointer');
	                },
	                error : function (e) {
	                }
	            } );
        	}
        	
        	$('#frequencyTypeTable').dataTable({
        	     "sAjaxSource" : '${context}/frequencyType/list',
        	     "sAjaxDataProp" : 'data.list',
        	     "aoColumns": [
                               { "mDataProp": "description","bSearchable" : true, "bSortable" : true,"sWidth" : "10%"},
                               { "mDataProp": "shortName","bSearchable" : true, "bSortable" : true,"sWidth" : "20%"},
                               { "mDataProp": "numberOfDays","bSearchable" : true, "bSortable" : true,"sWidth" : "20%", "sDefaultContent": "" }
                             
                           ],
                  "aoColumnDefs": [ 
                           		    { "sName": "description", "aTargets": [ 0 ],
                             		   "render": function ( data, type, full, meta ) {
                                              return '<a href="${context}/frequencyType/'+full.id+'">'+data+'</a>';
                                    }},
                                    { "sName": "shortName", "aTargets": [ 1 ] },
                                    { "sName": "numberOfDays", "aTargets": [ 2 ] }
                                 
                  ],          
        	     "bLengthChange": false,
        	     "sPaginationType": "full_numbers",
        	     "bProcessing": true,
        	     "bServerSide" : true,
        	     "iDisplayLength": 15,
        	     "fnServerData" : datatable2RestFrequencyType
        	});

        	
    } );
    </script>
<c:set var="context"
	value="${pageContext.request.contextPath}/${userpath}" />
<div class="panel-group">
	<div class="panel panel-success">
		<div class="panel-heading">
			Frequency Type List <span class="clrRed">${Message}</span> <a
				class="btn btn-danger pull-right btn-xs white-text"
				href="${context}/frequencyType/new"> <span
				class="glyphicon glyphicon-plus-sign "></span>New Frequency Type
			</a>
		</div>
		<div class="panel-body">
			<table id="frequencyTypeTable"
				class="table table-striped table-hover table-responsive rowClick">
				<thead>
					<tr>
						<th scope="col">Description</th>
						<th scope="col">Name</th>
						<th scope="col">Number of days</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>

		</div>
	</div>
</div>

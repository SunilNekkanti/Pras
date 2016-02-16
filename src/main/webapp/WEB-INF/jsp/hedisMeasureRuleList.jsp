<%@  page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
		
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@  taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<meta name="viewport" content="width=device-width, initial-scale=1">
<script>
        $(document).ready(function() {
        	
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
	                	alert('failed');
	                }
	            } );
        	}
        	
        	$('#hedisMeasureRuleTable').dataTable({
        	     "sAjaxSource" : '/Pras/hedisMeasureRule/hedisMeasureRuleLists',
        	     "sAjaxDataProp" : 'data.list',
        	     "aoColumns": [
                               { "mDataProp": "id", "bSearchable" : false, "bVisible" : false, "asSorting" : [ "asc" ]  },
                               { "mDataProp": "hedisMeasure.code","bSearchable" : true, "bSortable" : true,"sWidth" : "20%"},
                               { "mDataProp": "cptMeasure.code","bSearchable" : true, "bSortable" : true,"sWidth" : "20%"},
                               { "mDataProp": "icdMeasure.code","bSearchable" : true, "bSortable": true,"sWidth" : "20%",  },
                               { "mDataProp": "effectiveYear","bSearchable" : true, "bSortable": true,"sWidth" : "20%",  }
                              
                           ],
                  "aoColumnDefs": [ 
                           		    { "sName": "id", "aTargets": [ 0 ] },
                           		    { "sName": "hedisMeasure.code", "aTargets": [ 1 ],
                           		      "render": function ( data, type, full, meta ) {
                                            return '<a href="/Pras/hedisMeasureRule/'+full.id+'">'+data+'</a>';
                           		      }},
                           		    { "sName": "cptMeasure.code", "aTargets": [ 2 ] },
                           			{ "sName": "icdMeasure.code", "aTargets": [ 3] },
                           			{ "sName": "effectiveYear", "aTargets": [ 4] }
                  ],          
        	     "bLengthChange": false,
        	     "sPaginationType": "full_numbers",
        	     "bProcessing": true,
        	     "bServerSide" : true,
        	     "fnServerData" : datatable2Rest
        	});

        	
        	        } );
    </script>

<div class="panel-group">
	<div class="panel panel-primary">
		<div class="panel-heading">Hedis Measure Rule List  </div>
		<div class="panel-body" id="tablediv">
			<div class="table-responsive">
				<table id="hedisMeasureRuleTable" class="display table-responsive  table table-striped table-hover"> 
				
					<thead>
						<tr>
							<th  scope="col">Action</th> 
							<th  scope="col">Hedis Code</th> 
							<th  scope="col">CPT Code</th>  
					        <th  scope="col">ICD Code</th> 
					        <th  scope="col">Effective Year</th> 
						</tr>
					</thead>

					<tbody>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

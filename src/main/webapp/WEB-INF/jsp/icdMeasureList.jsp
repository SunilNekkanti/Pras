<%@  page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
		
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@  taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<meta name="viewport" content="width=device-width, initial-scale=1">

<script src="/Pras/resources/js/prasweb.js"></script>  

<script>

        $(document).ready(function() {
        	
        	$("#myTable").dataTable( {
        		"bProcessing": true,
        		"bServerSide": true,
        		'sAjaxSource': '/Pras/icd/icdMeasureLists',
        		"fnServerParams": function ( aoData ) {
		            aoData.push( { "name": "pageNo", "value": $('#myTable').DataTable().page.info().page + 1 },
		            			 { "name": "pageSize", "value": $('#myTable').DataTable().page.info().length },
		            			 { "name": "sSearch", "value":  $('.dataTables_filter input').val()} );
		        },
        		"fnServerData": function ( sSource, aoData, fnCallback ) {
        			 $.ajax( {
 		                dataType: 'json',
 		                contentType: "application/json;charset=UTF-8",
 		                type: 'GET',
 		                url: sSource,
 		                data: aoData,
 		                success: function (res) {
 		                		fnCallback( { 
 	                    	 "recordsTotal": res.data.totalCount,
 	      	                 "recordsFiltered": res.data.totalCount,
 	      	                 "data": res.data.list
 		                		});
 		                },
 		                error : function (e) {
 		                	
 		                }
 		            } );
                },
        		"bLengthChange": false,
        		"bFilter" : true,
        		"bRetrieve" :true,
        		"aaSorting": [[ 0, "asc" ]],
        		"iDisplayLength": 25,
        		"sPaginationType": "full_numbers",
        		"bAutoWidth": false,
        		"aoColumns": [
                              { "mDataProp": "id", 	"bSearchable" : false, "sWidth" : "20%", "asSorting" : [ "asc" ]  },
                              { "mDataProp": "code","bSearchable" : true, "bSortable" : true,"sWidth" : "20%"},
                              { "mDataProp": "description","bSearchable" : true, "bSortable": true,"sWidth" : "60%"  }
                          ],
        		"aoColumnDefs": [ 
        		    { "sName": "id", "aTargets": [ 0 ] ,
        		      "render": function ( data, type, full, meta ) {
                        return '<a href="/Pras/icd/'+data+'">Edit</a>';
                      }},
        		    { "sName": "code", "aTargets": [ 1 ] },
        		    { "sName": "description", "aTargets": [ 2 ] }
        		]

        		})
        		.columnFilter({ 	sPlaceHolder: "head:after",
					aoColumns: [ 	{ type: "number-range" },
				    	 			{ type: "text" },
                                    { type: "text" }
						]

				});

        	

        } );
    </script>



<div class="panel-group">
	<div class="panel panel-primary">
		<div class="panel-heading">ICD Measure List  </div>
		<div class="panel-body" id="tablediv">
		<div class="table-responsive">
		
				<table id="myTable" class="display table-responsive  table table-striped table-hover"> 
					<thead>
						<tr>
							<th  scope="col">Action</th> 
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
</div>
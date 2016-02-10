<%@  page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
		
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@  taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html  PUBLIC  "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<title>Membership List</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<script>

$(document).ready(function(){	
	
	$("#myTable").dataTable( {
		"bProcessing": true,
		"bServerSide": true,
		'sAjaxSource': '/Pras/membership/list',
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
		"iDisplayLength": 5,
		"sPaginationType": "full_numbers",
		"bAutoWidth": false,
		"aoColumns": [
                      { "mDataProp": "id", 	"bSearchable" : false, "sWidth" : "10%", "asSorting" : [ "asc" ]  },
                      { "mDataProp": "firstName","bSearchable" : true, "bSortable" : true,"sWidth" : "25%", "sDefaultContent": ""},
                      { "mDataProp": "lastName","bSearchable" : true, "bSortable": true,"sWidth" : "25%", "sDefaultContent": ""  },
                      { "mDataProp": "dob","bSearchable" : true, "bSortable": true,"sWidth" : "10%", "sDefaultContent": ""  },
                      { "mDataProp": "genderId.description","bSearchable" : true, "bSortable": true,"sWidth" : "10%", "sDefaultContent": ""  },
                      { "mDataProp": "countyCode.description","bSearchable" : true, "bSortable": true,"sWidth" : "10%", "sDefaultContent": ""  },
                      { "mDataProp": "status.description","bSearchable" : true, "bSortable": true,"sWidth" : "10%", "sDefaultContent": ""  }
                  ],
		"aoColumnDefs": [ 
		    { "sName": "id", "aTargets": [ 0 ] ,
		      "render": function ( data, type, full, meta ) {
                return '<a href="/Pras/membership/'+data+'">Edit</a>';
              }},
		    { "sName": "firstName", "aTargets": [ 1 ] },
		    { "sName": "lastName", "aTargets": [ 2 ] },
		    { "sName": "dob", "aTargets": [ 3 ] },
		    { "sName": "genderId.id", "aTargets": [ 4 ] },
		    { "sName": "countyCode.code", "aTargets": [ 5 ] },
		    { "sName": "status.id", "aTargets": [ 6 ] }
		]

		})
		.columnFilter({ 	sPlaceHolder: "head:after",
			aoColumns: [ 	{ type: "number-range" },
		    	 			{ type: "text" },
		    	 			{ type: "text" },
		    	 			{ type: "text" },
		    	 			{ type: "text" },
                            { type: "text" },
                            { type: "text" }
				]

		});

	

	
});
</script>

</head>

<body>

	<div class="panel-group">
		<div class="panel panel-primary">
			<div class="panel-heading">Membership List  </div>
			<div class="panel-body" id="tablediv">
				<div class="table-responsive">
					<table id="myTable" class="display table-responsive  table table-striped table-hover">
					
						<thead>
							<tr>
								<th scope="col">Action</th>
								<th scope="col">First Name</th>
								<th scope="col">Last Name</th>
								<th scope="col">Date Of Birth</th>
								<th scope="col">Gender</th>
								<th scope="col">County</th>
								<th scope="col">Status</th>
							</tr>
						</thead>
	
						<tbody >
							
						</tbody>
	
					</table>
				</div>	
				
			</div>
			
		</div>
	</div>


</body>
</html>
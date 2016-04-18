<%@  page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
		
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@  taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<title>Provider List</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<c:set var="context" value="${pageContext.request.contextPath}/${userpath}" />
<script>

    $(document).ready(function() {
    	
    	var datatable2RestProvider = function(sSource, aoData, fnCallback) {
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
    	
    	$('#providerTable').dataTable({
    	     "sAjaxSource" :  getContextPath()+'/provider/list',
    	     "sAjaxDataProp" : 'data.list',
    	     "aoColumns": [
                           { "mDataProp": "id", "bSearchable" : false, "bVisible" : false, "asSorting" : [ "asc" ]  },
                           { "mDataProp": "name","bSearchable" : true, "bSortable" : true,"sWidth" : "33%"},
                           { "mDataProp": "code","bSearchable" : true, "bSortable": true,"sWidth" : "33%"  },
                           { "mDataProp": "refContracts[].ins.name","bSearchable" : true, "bSortable": true,"sWidth" : "33%","asSorting" : [ 3,"asc" ]  }
                       ],
              "aoColumnDefs": [ 
                       		    { "sName": "id", "aTargets": [ 0 ] },
                       		    { "sName": "name", "aTargets": [ 1 ],
                         		   "render": function ( data, type, full, meta ) {
                                          return '<a href="${context}/provider/'+full.id+'">'+data+'</a>';
                                }},
                       		    { "sName": "code", "aTargets": [ 2 ] },
                       		    { "sName": "refContracts[].ins.name", "aTargets": [ 3 ],
                       		    	"render": function  ( data, type, full, meta )  {
                                        var insNames=[];
                                        var index =0;
                                        full.refContracts.forEach(function( item ) {
                                        	if(item.activeInd == 'Y' && item.ins != null)
                                    		{
                                        		insNames[index] = item.ins.name;  
                                        		++index;
                                        	}
                                        });
                  						return insNames.join(', ');
                					}
                       		    }
              ],          
    	     "bLengthChange": false,
    	     "iDisplayLength": 15,
    	     "sPaginationType": "full_numbers",
    	     "bProcessing": true,
    	     "bServerSide" : true,
    	     "fnServerData" : datatable2RestProvider
    	});

    	
} );
    </script>
<div class="panel-group">
	<div class="panel panel-success">
		<div class="panel-heading"> Provider List
		<span class="clrRed">${Message}</span>
			<a class="btn btn-danger pull-right btn-xs white-text"href="${context}/provider/new">
          		<span class="glyphicon glyphicon-plus-sign "></span> New Provider
          	</a>
		</div>
		<div class="panel-body" >
			<table id="providerTable" class="table table-striped table-hover table-responsive">
				<thead>
					<tr>
							<th scope="col">Action</th>
							<th scope="col">Name</th>
							<th scope="col">NPI</th>
							<th scope="col">Insurances</th>
					</tr>
				</thead>

				<tbody >
					
				</tbody>
			</table>
			
		</div>
	</div>
</div>

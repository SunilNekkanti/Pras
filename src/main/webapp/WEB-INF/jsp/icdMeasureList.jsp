<%@  page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
		
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@  taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<meta name="viewport" content="width=device-width, initial-scale=1">

<script src="/Pras/resources/js/prasweb.js"></script>

<script>
        $(document).ready(function() {
            $('#myTable').dataTable( {
                "ajax": {
                    "url":"/Pras/icd/icdMeasureLists",
                    "dataSrc": 'data.list',
                    "deferRender": true,
                    "data":function(d) {
                        var table = $('#myTable').DataTable()
                        d.pageNo = (table != undefined)?(table.page.info().page+1):1
                        d.pageSize = 25
                        d.sort = d.columns[d.order[0].column].data + ',' + d.order[0].dir
                      //  alert('d.pageNo :'+d.pageNo + 'd.pageSize :'+d.pageSize)
                    }
                },
                "processing": true,
                "serverSide": true,
                "columns": [
                    { "data": "code" },
                    { "data": "code" },
                    { "data": "description" }
                ],
               
                "pagingType": "full_numbers",
                "bLengthChange": false
 
            } );
 
        } );
 
    </script>



<div class="panel-group">
	<div class="panel panel-primary">
		<div class="panel-heading">ICD Measure List <span class="badge">${icdMeasureList.size()}</span> </div>
		<div class="panel-body" id="tablediv">
		<div class="table-responsive">
		
				<table id="myTable" class="display table-responsive table-bordered "> 
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
		<div class="col-md-12 text-center">
      <ul class="pagination pagination-lg pager" id="myPager"></ul>
      </div>
	</div>
</div>
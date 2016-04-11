<%@  page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
		
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@  taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html  PUBLIC  "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<title>Membership List</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<c:set var="context" value="${pageContext.request.contextPath}/${userpath}" />
<script>


     $(document).ready(function() {
    	 
    	 $("#mbrGenerate").click(function(event){
    		 var insSelectValue= $("#insu option:selected").val();
  		     var prvdrSelectValue= $("#prvdr option:selected").val();
    		 callMembershipDataTable(insSelectValue, prvdrSelectValue);
    	 });
    	 
    	 $(document.body).on('change',"#insu",function (e) {
     		if ( $.fn.DataTable.isDataTable('#membershipTable') ) {
 					$('#membershipTable').DataTable().destroy();
 	   		}
     		$('#membershipTable tbody').empty();
     		providerDropdown();
   		});
     	
     	$(document.body).on('change',"#prvdr",function (e) {
     		if ( $.fn.DataTable.isDataTable('#membershipTable') ) {
 					$('#membershipTable').DataTable().destroy();
 	   		}
     		$('#membershipTable tbody').empty();
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
				 providerDropdown();
			 });
		 
		     var providerDropdown = function(){
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
		 
		   //create new json structure for parameters for REST request
		   var restParams = new Array();
		   restParams.push({"name" : "pageSize", "value" : pageSize});
		   restParams.push({"name" : "pageNo", "value" : pageNum });
		   restParams.push({"name" : "sort", "value" : sortName });
		   restParams.push({"name" : "sortdir", "value" : sortDir });
		   restParams.push({"name" : "sSearch" , "value" : paramMap.sSearch  });
		   restParams.push({"name" : "sSearchIns" , "value" : paramMap.sSearchIns  });
		   restParams.push({"name" : "sSearchPrvdr" , "value" : paramMap.sSearchPrvdr  });

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
     	
     	var callMembershipDataTable  = function (insId,prvdrId) {
     		$('#membershipTable').dataTable({
        	     "sAjaxSource" : getContextPath()+'/membership/list',
        	     "sAjaxDataProp" : 'data.list',
                 "aoColumns": [
                               { "mDataProp": "id", 	"bSearchable" : false, "bVisible" : false, "asSorting" : [ "asc" ]  },
                               { "mDataProp": "firstName","bSearchable" : true, "bSortable" : true,"sWidth" : "20%"},
                               { "mDataProp": "lastName","bSearchable" : true, "bSortable": true,"sWidth" : "20%"  },
                               { "mDataProp": "dob","bSearchable" : true, "bSortable": true,"sWidth" : "15%"  },
                               { "mDataProp": "genderId.description","bSearchable" : true, "bSortable": true,"sWidth" : "15%" },
                               { "mDataProp": "countyCode.description","bSearchable" : true, "bSortable": true,"sWidth" : "15%", "sDefaultContent": ""  },
                               { "mDataProp": "status.description","bSearchable" : true, "bSortable": true,"sWidth" : "15%"  }
                             ],
                  "aoColumnDefs": [ 
                  		   		    { "sName": "id", "aTargets": [ 0 ] },
                  		   		    { "sName": "firstName", "aTargets": [ 1 ],
                  		               "render": function ( data, type, full, meta ) {
               		                   return '<a href="${context}/membership/'+full.id+'/complete">'+data+'</a>';
             		                 }},
                  		   		    { "sName": "lastName", "aTargets": [ 2 ] },
                  		   		    { "sName": "dob", "aTargets": [ 3 ] ,
                  		   		   	   "render": function (data) {
                  		   		        		var date = new Date(data);
                  		   	        			var month = date.getMonth() + 1;
                  		   	       				 return (month > 9 ? month : "0" + month) + "/" + date.getDate() + "/" + date.getFullYear();
                  		   		   	 }},
                  		   		    { "sName": "genderId.id", "aTargets": [ 4 ] },
                  		   		    { "sName": "countyCode.code", "aTargets": [ 5 ] },
                  		   		    { "sName": "status.id", "aTargets": [ 6 ] }
                  ],          
        	     "bLengthChange": false,
        	     "iDisplayLength": 13,
        	     "sPaginationType": "full_numbers",
        	     "bProcessing": true,
        	     "bServerSide" : true,
        	     "fnServerParams": function ( aoData ) {
                     aoData.push(
                         {"name": "sSearchIns", "value": insId},
                         {"name": "sSearchPrvdr", "value": prvdrId }
                     );
                  },    
        	     "fnServerData" : datatable2RestMembership
        	});
     	}
     	
     	
     	
 } );
    </script>

</head>

<body>

	<div class="panel-group">
		<div class="panel panel-success">
			<div class="panel-heading">Membership List  </div>
			<div class="panel-body" >
				<div class="table-responsive">
					<div class="col-sm-12">
							<div class="col-sm-3">
								<label class="control-label col-sm-4">Insurance</label>
								 <div class=" col-sm-8" id="extFilterIns">  </div>
							</div>	 
							<div class="col-sm-3">	 
								<label class="control-label col-sm-3">Provider</label>
								 <div class="col-sm-9"  id="extFilterPrvdr"> </div>
							</div>	 
							<div class="col-sm-3">	 
								 <button type="button" id="mbrGenerate" class="btn btn-success btn-xs">Generate</button>
							</div>	 
					</div>
					<table id="membershipTable" class="table table-striped table-hover table-responsive">
					
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
<%@  page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@  taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html  PUBLIC  "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<title>Membership List</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<c:set var="context"
	value="${pageContext.request.contextPath}/${userpath}" />
<script>
     $(document).ready(function() {
    	 
    	 
    	 $("#mbrGenerate").click(function(event){
    		 if ( $.fn.DataTable.isDataTable('#leadMembershipTable') ) {
					$('#leadMembershipTable').DataTable().destroy();
	   		}
  			$('#leadMembershipTable tbody').empty();
    		 var insSelectValue= $("#insu option:selected").val();
  		     var prvdrSelectValue= $("#prvdr option:selected").val();
    		 callMembershipDataTable(insSelectValue, prvdrSelectValue);
    	 });
    	 
    	 
    	 $(document.body).on('change',"#insu",function (e) {
    		 Cookies.set('insu', $("#insu option:selected").val(), {path: cookiePath});
      		Cookies.remove('prvdr', { path: cookiePath }); 
     		if ( $.fn.DataTable.isDataTable('#leadMembershipTable') ) {
 					$('#leadMembershipTable').DataTable().destroy();
 	   		}
     		$('#leadMembershipTable tbody').empty();
     		providerDropdown();
   		});
     	
     	$(document.body).on('change',"#prvdr",function (e) {
     		Cookies.set('prvdr', $("#prvdr option:selected").val(), {path:cookiePath});
     		if ( $.fn.DataTable.isDataTable('#leadMembershipTable') ) {
 					$('#leadMembershipTable').DataTable().destroy();
 	   		}
     		$('#leadMembershipTable tbody').empty();
   		});
     	
    	  var $selectIns = $('#extFilterIns');
	   	  $.getJSON(getContextPath()+'/insurance/list?pageNo=0&pageSize=200', function(data){
				    
				     //clear the current content of the select
				     var s = $('<select id=\"insu\" style=\"width:150px;\" class=\"btn btn-default\">');
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
		    	 var insSelectValue = Cookies.get('insu');
		    	 if(insSelectValue != undefined)
						insSelectValue = insSelectValue;
					else{
						insSelectValue= $("#insu option:selected").val();
						Cookies.set('insu', insSelectValue, {path: cookiePath});
					}
			  var $selectPrvdr = $('#extFilterPrvdr');
	    	  $.getJSON(getContextPath()+'/insurance/providerlist?insId='+insSelectValue, function(data){
					$('select[id="insu"]').val(insSelectValue);
				     //clear the current content of the select
				     var s = $('<select id=\"prvdr\" style=\"width:150px;\" class=\"btn btn-default\">');
				     //iterate over the data and append a select option
				     $.each(data.data.list, function(key, val){
				    	 s.append('<option value="'+val.id+'">' + val.name +'</option>');
				     });
				     s.append('<option value="9999">All</option>');
				     s.append('</select>');
				     $selectPrvdr.html(s);
			 }).success(function() {
				 var prvdrSelectValue = Cookies.get('prvdr');
 				 if(prvdrSelectValue != undefined) 
					 prvdrSelectValue = prvdrSelectValue;
				 else{
					prvdrSelectValue= $("#prvdr option:selected").val();
					Cookies.set('prvdr', prvdrSelectValue, {path:cookiePath});
				 }	
				$('select[id="prvdr"]').val(prvdrSelectValue);
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
                  res.iTotalRecords = res.data.pagination.totalCount;
                  res.iTotalDisplayRecords = res.data.pagination.totalCount;
             		fnCallback(res);
             		$(".rowClick tbody tr").css('cursor', 'pointer');
              },
              error : function (e) {
              }
          } );
     	}
     	
     	var callMembershipDataTable  = function (insId,prvdrId) {
     		$('#leadMembershipTable').dataTable({
        	     "sAjaxSource" : getContextPath()+'/leadMembership/list',
        	     "sAjaxDataProp" : 'data.pagination.list',
                 "aoColumns": [
							   { "mDataProp": "lastName","bSearchable" : true, "bSortable": true,"sWidth" : "20%"  },
                               { "mDataProp": "firstName","bSearchable" : true, "bSortable" : true,"sWidth" : "20%"},
                               { "mDataProp": "dob","bSearchable" : true, "bSortable": true,"sWidth" : "10%"  },
                               { "mDataProp": "id","bSearchable" : true, "bSortable": true,"sWidth" : "10%"  },
                               { "mDataProp": "genderId.code","bSearchable" : true, "bSortable": true,"sWidth" : "10%" },
                               { "mDataProp": "countyCode.description","bSearchable" : true, "bSortable": true,"sWidth" : "15%", "sDefaultContent": ""  },
                               { "mDataProp": "status.description","bSearchable" : true, "bSortable": true,"sWidth" : "15%", "sDefaultContent": ""   }
                             ],
                  "aoColumnDefs": [ 
                  		   		    { "sName": "lastName", "aTargets": [ 0 ],
                  		               "render": function ( data, type, full, meta ) {
               		                   return '<a href="${context}/leadMembership/'+full.id+'/complete">'+data+'</a>';
               		                   
             		                 }},
                  		   		    { "sName": "firstName", "aTargets": [ 1 ] },
                  		   		    { "sName": "dob", "aTargets": [ 2 ] ,
                  		   		   	   "render": function (data) {
                  		   		        		var date = new Date(data);
                  		   	        			var month = date.getMonth() + 1;
                  		   	        			var d = date.getDate();
                  		   	       				 return (month > 9 ? month : "0" + month) + "/" + (d > 9 ? d : "0" + d) + "/" + date.getFullYear();
                  		   		   	 }},
                  		   			{ "sName": "id", "aTargets": [ 3 ] ,
                    		   		   	   "render": function (data, type, full, meta) {
                    		   		   	 var bDate = new Date(full.dob);
	                    		   		 var today = new Date();
	                    		   		 var calculateYear = today.getFullYear();
	                    		   	     var calculateMonth = today.getMonth();
	                    		   	     var calculateDay = today.getDate();
	                    		   	     var birthYear = bDate.getFullYear();
	                    		   	     var birthMonth = bDate.getMonth();
	                    		   	     var birthDay = bDate.getDate();
										 var age = calculateYear - birthYear;
	                    		   	     var ageMonth = calculateMonth - birthMonth;
	                    		   	     var ageDay = calculateDay - birthDay;
	
	                    		   	    if (ageMonth < 0 || (ageMonth == 0 && ageDay < 0)) {
	                    		   	        age = parseInt(age) - 1;
	                    		   	    }
	                    		   	    return age;
                    		   		       
                    		   		   	 }},
                  		   		    { "sName": "genderId.code", "aTargets": [ 4 ] },
                  		   		    { "sName": "countyCode.code", "aTargets": [ 5 ] },
                  		   		    { "sName": "status.id", "aTargets": [ 6 ] }
                  ],          
        	     "bLengthChange": false,
        	     "iDisplayLength": 15,
        	     "sPaginationType": "full_numbers",
        	     "bProcessing": true,
        	     "bServerSide" : true,
        	     "aaSorting":[[0,"asc"],[1,"asc"]],
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
			<div class="panel-heading">Membership List
				<a
				class="btn btn-danger pull-right btn-xs white-text"
				href="${context}/leadMembership/new"> <span
				class="glyphicon glyphicon-plus-sign "></span> New Lead
			</a>
			</div>
			<div class="panel-body">
				<div class="table-responsive">
					<div class="col-sm-12">
						<div class="col-sm-3">
							<label class="control-label col-sm-4">Insurance</label>
							<div class=" col-sm-8" id="extFilterIns"></div>
						</div>
						<div class="col-sm-3">
							<label class="control-label col-sm-3">Provider</label>
							<div class="col-sm-9" id="extFilterPrvdr"></div>
						</div>
						<div class="col-sm-3">
							<button type="button" id="mbrGenerate"
								class="btn btn-success btn-xs">Generate</button>
						</div>
					</div>
					<table id="leadMembershipTable"
						class="table table-striped table-hover table-responsive rowClick">
						<thead>
							<tr>
								<th scope="col">Last Name</th>
								<th scope="col">First Name</th>
								<th scope="col">Date Of Birth</th>
								<th scope="col">Age</th>
								<th scope="col">Sex</th>
								<th scope="col">County</th>
								<th scope="col">Status</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
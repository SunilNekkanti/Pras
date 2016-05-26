<%@  page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@  taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="context"
	value="${pageContext.request.contextPath}/${userpath}" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<script>

  $(document).ready(function() {
	  
	  //Setting Eff Year dropdown values
	  var $selectEY = $('#extFilterEY');
	  var date = new Date();
	  var prevYear = date.getFullYear()-1 ;
	  var currentYear = date.getFullYear() ;
	  var nextYear = date.getFullYear()+1 ;
		     //clear the current content of the select
	  var s = $('<select id=\"ey\" style=\"width:150px;\">');
		     //iterate over the data and append a select option
	   s.append('<option value="'+prevYear+'">' + prevYear +'</option>');
	   s.append('<option value="'+currentYear+'" selected=\"selected\">' + currentYear +'</option>');
	   s.append('<option value="'+nextYear+'">' + nextYear +'</option>');
	   s.append('</select>');
	    $selectEY.html(s);
		    
	  //Setting Insurance dropdown values
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
	    
	 }).success(function() {  callDatableWithChangedDropDown();	 });
					 
	 $(document.body).on('change',"#insu",function (e) {
		 callDatableWithChangedDropDown();
 		});
 	
 	  $(document.body).on('change',"#ey",function (e) {
 		 callDatableWithChangedDropDown();
 		});
 	  
 	 var callDatableWithChangedDropDown = function(){
		   var insSelectValue= $("#insu option:selected").val();
		   var eySelectValue= $("#ey option:selected").val();
		   if ( $.fn.DataTable.isDataTable('#problemTable') ) {
					$('#problemTable').DataTable().destroy();
		   }

			$('#problemTable tbody').empty();
		   
			GetproblemsByInsAndEffYear (eySelectValue,insSelectValue);
	}  
 	 
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
  restParams.push({"name" : "insId" , "value" : paramMap.insId  });
  restParams.push({"name" : "effYear" , "value" : paramMap.ey  });
  
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
          		$('[data-toggle="tooltip"]').tooltip();
           },
           error : function (e) {
           }
       } );
  	}
  	
    GetproblemsByInsAndEffYear = function (ey, insId ) {
  		
	        var oTable = 	$('#problemTable')
	        					.dataTable({
								  	     "sAjaxSource" : getContextPath()+'/problem/list',
								  	     "sAjaxDataProp" : 'data.list',
								  	     "aoColumns": [
								                         { "mDataProp": "id", "bSearchable" : false, "bVisible" : false, "asSorting" : [ "asc" ],"sClass":"center"  },
								                         { "mDataProp": "description", "bSearchable" : true, "sWidth" : "30%" , "sDefaultContent": "" ,
								                        	 "render": function ( data, type, full, meta ) {
								                                 return '<a href="${context}/problem/'+full.id+'">'+data+'</a>';
								                		      }
								                         },
								                        
						                                  { "mDataProp": "icdCodes[ ].code","bSearchable" : true, "bSortable": true,"sWidth" : "70%" , "asSorting" : [ "asc" ],"sClass":"icdCodes",
						                                        "render": function  ( data, type, full, meta )  {
										                                         var icdCodeList=[];
										                                         for(var i = 0; i<full.icdCodes.length; i++)
										                                         {
										                                          icdCodeList[i] = '<span data-toggle="tooltip" title="'+full.icdCodes[i].description+'">'+full.icdCodes[i].code+'</span>';  
										                                         }
											                                   return icdCodeList.join(', ');
											                               }  
						                                 }
								                         
								                     ],
								  	     "bLengthChange": false,
								  	     "iDisplayLength": 10,
								  	     "sPaginationType": "full_numbers",
								  	     "bProcessing": true,
								  	     "bServerSide" : true,
								  	     "bAutoWidth": false,
								  	     "fnServerParams": function ( aoData ) {
							                aoData.push(
							                    {"name": "insId", "value": insId},
							                    {"name": "ey", "value": ey }
							                );
							             }, 
								  	     "fnServerData" : datatable2Rest
						             });
    }

  	
} );
    </script>

<div class="panel-group">
	<div class="panel panel-success">
		<div class="panel-heading">
			Problem List <span class="clrRed">${Message}</span> <a
				class="btn btn-danger pull-right btn-xs white-text"
				href="${context}/problem/new"> <span
				class="glyphicon glyphicon-plus-sign "></span>New Problem
			</a>
		</div>
		<div class="panel-body" id="tablediv">
			<div class="table-responsive">
				<div class="col-sm-6">
					<div class="col-sm-4">
						<label class="control-label col-sm-5">Eff. Year</label>
						<div class=" col-sm-7" id="extFilterEY"></div>
					</div>
					<div class="col-sm-4">
						<label class="control-label col-sm-4">Insurance</label>
						<div class="col-sm-8" id="extFilterIns"></div>
					</div>
				</div>
				<table id="problemTable"
					class="display table-responsive  table table-striped table-hover">

					<thead>
						<tr>
							<th scope="col">Action</th>
							<th scope="col">Description</th>
							<th scope="col">ICD Codes</th>
							
						</tr>
					</thead>

					<tbody>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

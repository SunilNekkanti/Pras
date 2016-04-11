<%@  page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
		
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@  taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="context" value="${pageContext.request.contextPath}/${userpath}" />
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
		   if ( $.fn.DataTable.isDataTable('#hedisMeasureRuleTable') ) {
					$('#hedisMeasureRuleTable').DataTable().destroy();
		   }

			$('#hedisMeasureRuleTable tbody').empty();
		   
			GetHedisMeasureRulesByInsAndEffYear (eySelectValue,insSelectValue);
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
  	
    GetHedisMeasureRulesByInsAndEffYear = function (ey, insId ) {
  		
	        var oTable = 	$('#hedisMeasureRuleTable').dataTable({
														  	     "sAjaxSource" : getContextPath()+'/hedisMeasureRule/hedisMeasureRuleLists',
														  	     "sAjaxDataProp" : 'data.list',
														  	     "aoColumns": [
														                         { "mDataProp": "id", "bSearchable" : false, "bVisible" : false, "asSorting" : [ "asc" ],"sClass":"center"  },
														                         { "mDataProp": "description", "bSearchable" : true, "sWidth" : "10%" , "sDefaultContent": "" ,"sClass":"center" ,
														                        	 "render": function ( data, type, full, meta ) {
														                                 return '<a href="'+full.id+'">'+data+'</a>';
														                		      }
														                         },
														                         { "mDataProp": "hedisMeasure.code","bSearchable" : true, "bSortable" : true,"sWidth" : "10%" , "asSorting" : [ "asc" ],"sClass":"center",
														                        	 "render": function  ( data, type, full, meta )  {
												                                          			return '<span data-toggle="tooltip" title="'+full.hedisMeasure.description+'">'+data+'</span>';  
								                                 								}
														                         },
														                         { "mDataProp": "cptCodes[ ].code","bSearchable" : true, "bSortable" : true,"sWidth" : "15%", "asSorting" : [ "asc" ],"sClass":"cptCodes",
												                                        "render": function  ( data, type, full, meta )  {
																                                         var cptcodeList=[];
																                                         for(var i = 0; i<full.cptCodes.length; i++)
																                                         {
																                                          cptcodeList[i] = '<span data-toggle="tooltip" title="'+full.cptCodes[i].shortDescription+'">'+full.cptCodes[i].code+'</span>';  
																                                         }
												                                   						return cptcodeList.join(', ');
												                                 					}
												                                  },
												                                  { "mDataProp": "icdCodes[ ].code","bSearchable" : true, "bSortable": true,"sWidth" : "15%" , "asSorting" : [ "asc" ],"sClass":"icdCodes",
												                                        "render": function  ( data, type, full, meta )  {
																                                         var icdCodeList=[];
																                                         for(var i = 0; i<full.icdCodes.length; i++)
																                                         {
																                                          icdCodeList[i] = '<span data-toggle="tooltip" title="'+full.icdCodes[i].description+'">'+full.icdCodes[i].code+'</span>';  
																                                         }
																	                                   return icdCodeList.join(', ');
																	                               }  
												                                 },
														                         { "mDataProp": "doseCount","bSearchable" : true, "bSortable": true,"sWidth" : "10%" ,"sClass":"center", "sDefaultContent": ""},
														                         { "mDataProp": "genderId.description","bSearchable" : true, "bSortable": true,"sWidth" : "10%", "sDefaultContent": "","sClass":"center"   },
														                         { "mDataProp": "lowerAgeLimit","bSearchable" : true, "bSortable": true,"sWidth" : "5%", "sDefaultContent": "" ,"sClass":"center"  },
														                         { "mDataProp": "upperAgeLimit","bSearchable" : true, "bSortable": true,"sWidth" : "5%" , "sDefaultContent": "" ,"sClass":"center" },
														                         { "mDataProp": "ageEffectiveFrom","bSearchable" : true, "bSortable": true,"sWidth" : "10%" , "sDefaultContent": "","sClass":"center",
														                        	 "render": function (data) {
														     		   		   		    if(data == null) return null;
														   		   		        		var date = new Date(data);
														   		   	        			var month = date.getMonth() + 1;
														   		   	       				 return (month > 9 ? month : "0" + month) + "/" + date.getDate() + "/" + date.getFullYear();
														   		   		   	 		 }
														                         },
														                         { "mDataProp": "ageEffectiveTo","bSearchable" : true, "bSortable": true,"sWidth" : "10%" , "sDefaultContent": "","sClass":"center",
														                        	 "render": function (data) {
														      		   		   			if(data == null) return null;
														   		   		        		var date = new Date(data);
														   		   	        			var month = date.getMonth() + 1;
														   		   	       				 return (month > 9 ? month : "0" + month) + "/" + date.getDate() + "/" + date.getFullYear();
														   		   		   	 		}
														                         },
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
		<div class="panel-heading">Hedis Measure Rule List  
			<a class="btn btn-danger pull-right btn-xs white-text"href="${context}/hedisMeasureRule/new">
          		<span class="glyphicon glyphicon-plus-sign "></span>New Hedis Rule
          	</a>
         </div>	
		<div class="panel-body" id="tablediv">
			<div class="table-responsive">
					<div class="col-sm-6">
							<div class="col-sm-4">
								<label class="control-label col-sm-4">Eff. Year</label>
								 <div class=" col-sm-8" id="extFilterEY">  </div>
							</div>	 
							<div class="col-sm-4">	 
								<label class="control-label col-sm-4">Insurance</label>
								 <div class="col-sm-8"  id="extFilterIns"> </div>
							</div>	 
					 </div>
				<table id="hedisMeasureRuleTable" class="display table-responsive  table table-striped table-hover"> 
				
					<thead>
						<tr>
							<th  scope="col">Action</th> 
							<th  scope="col">Description</th> 
							<th  scope="col">Hedis Code</th> 
							<th  scope="col">CPT Codes</th>  
					        <th  scope="col">ICD Codes</th> 
					        <th  scope="col">Dose Count</th> 
					        <th  scope="col">Gender</th> 
					        <th  scope="col">Lower Age Limit</th> 
					        <th  scope="col">Upper Age Limit</th> 
					        <th  scope="col">Eff. Date From</th> 
					        <th  scope="col">Eff. Date To</th> 
						</tr>
					</thead>

					<tbody>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="springForm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row">
	<div class="col-sm-12" id="leadMbrDetails">
		<jsp:include page="/WEB-INF/jsp/leadMembershipEdit.jsp">
			<jsp:param name="leadMembershipEdit" value="${leadMembershipEdit}" />
		</jsp:include>
	</div>
</div>

<div id="leadMembershipContactList"></div>

<div class="row">
	<div class="col-sm-12" id="leadMbrInsList"></div>
</div>


<div class="row">
	<div class="col-sm-12" id="leadMbrPrdvrDetails"></div>
</div>

<div class="row">
     <div class="col-sm-12" id="leadMbrClaim"></div>
</div>

<div class="modal fade" id="modalICD" role="dialog">
	<div class="modal-dialog modal-lg">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<button type="button" id="addICD" class="btn btn-success btn-sm pull-right"
					data-dismiss="modal">ADD</button>
				<h4 class="modal-title">ICD Measure List</h4>
			</div>
			<div class="modal-body">
				<div class="panel-group">
					<div class="panel panel-success">

						<div class="panel-body">
							<div class="table-responsive">
								<table id="icdListTable"
									class="display table-responsive  table table-striped table-hover">
									<thead>
										<tr>
											<th scope="col">Select</th>
											<th scope="col">ICD Code</th>
											<th scope="col">Description</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer" style="text-align: left;">
				<button type="button" id="addICD" class="btn btn-success btn-sm"
					data-dismiss="modal">ADD</button>
			</div>
		</div>
	</div>
</div>


<div id="modalCPT" class="modal fade" role="dialog">
	<div class="modal-dialog modal-lg">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">CPT Measure List</h4>
			</div>
			<div class="modal-body">
				<div class="panel-group">
					<div class="panel panel-success">

						<div class="panel-body">
							<div class="table-responsive">
								<table id="cptListTable"
									class="display table-responsive  table table-striped table-hover">
									<thead>
										<tr>
											<th scope="col">Select</th>
											<th scope="col">CPT Code</th>
											<th scope="col">Short Description</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer" style="text-align: left;">
				<button type="button" id="addCPT" class="btn btn-success btn-sm"
					data-dismiss="modal">ADD</button>
			</div>
		</div>
	</div>
</div>

<div class="row">
	<div class="col-sm-12" id="leadMbrHedisMeasureList"></div>
</div>


<script>
 
$listLength = 0;
$(document).ready(function() {
	var checkedCPTItemsMap = {};
	var checkedICDItemsMap = {};
	var cptListReference;
	
	
	$("#addCPT").click(function (e)    {
		var cpt = $("[id='leadMbrClaimDetailsList"+cptListReference+".cpt.id']");
       	for (var e in checkedCPTItemsMap){
       		var value = checkedCPTItemsMap[e];
       		var optionExists = ($("[id='leadMbrClaimDetailsList"+cptListReference+".cpt.id'] option[value=" + e + "]").length > 0);
               if(!optionExists)
               {
            	   $("[id='leadMbrClaimDetailsList"+cptListReference+".cpt.id']").append('<option value ="'+e+'" selected ="selected">'+value+'</option>');
               }
       	} 
    });
	
	$('body').on('click',".removeCPT", function(e){
		 res = $(this).attr('id').split("removeCPT");
		 $("[id='leadMbrClaimDetailsList"+res[1]+".cpt.id']").html('');
             e.preventDefault();
    });


   $('#cptListTable tbody').on('click', 'input[type="radio"]', function (e) {
	   checkedCPTItemsMap = {};
	  	 if ($(this).is(':checked')) {
	  		checkedCPTItemsMap[$(this).attr('id')] = $(this).attr('value');		   		   
	        	
	    	} 
	   } );
	   
   
	
	$('#icdListTable tbody').on('click', 'input[type="checkbox"]', function (e) {

	  	 if ($(this).is(':checked')) {
	  		checkedICDItemsMap[$(this).attr('id')] = $(this).attr('value');		   		   
	        	
	    } else {
	    		delete checkedICDItemsMap[$(this).attr('id')];
	    }
	} ); 
	
	
	
          	
	$("#addICD").click(function (e)    {
       	var diagnosisList = []; 
       	for (var e in checkedICDItemsMap){
       		var value = checkedICDItemsMap[e];
       	 	diagnosisList.push(value);
       	} 
       	$("#diagnosis").val(diagnosisList.toString());
	});
	
	
	  $('body').on('click',"#icdModal", function(){
		  $("#modalICD").modal('show');
	});
	  
	  $('body').on('click',".addModalCPT", function(){
		  cptListReference = $(this).attr('id');
		  res = cptListReference.split("addCPT");
		  cptListReference = res[1];
		  $("#modalCPT").modal('show');
	});
	  
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
	           }
	       } );
	  	}
	  	
	 
$('#icdListTable').dataTable({
	     "sAjaxSource" : getContextPath()+'/icd/icdMeasureLists',
	     "sAjaxDataProp" : 'data.list',
	     "aoColumns": [
		 { "mData" : "id",  
		  "render" : function(data, type, full, meta) {
										return '<input type="checkbox" name="chkbox"  id="'+data+'" value="'+full.code+'"/>';
								}, 
					  "sWidth" : "5%", "bSearchable" : false,  "asSorting" : [ "asc" ]
				 },
                     { "mDataProp": "code","bSearchable" : true, "bSortable" : true,"sWidth" : "10%"},
                     { "mDataProp": "description","bSearchable" : true, "bSortable" : true,"sWidth" : "85%"}
                   
                 ],
	     "bLengthChange": false,
	     "sPaginationType": "full_numbers",
	     "bProcessing": true,
	     "bServerSide" : true,
	     "fnRowCallback": function(row, data, dataIndex){
	         // If row ID is in the list of selected row IDs
           if ( (data.id in checkedICDItemsMap) ) {
          	   $(row).find('input[type="checkbox"]').prop('checked', true);
           }
	      },
	      "fnServerData" : datatable2Rest
	});
	
	
	
	$('#cptListTable').dataTable({
	     "sAjaxSource" : getContextPath()+'/cpt/cptMeasureLists',
	     "sAjaxDataProp" : 'data.list',
	     "aoColumns": [
						 { "mData" : "id",  
		  				  "render" : function(data, type, full, meta) {
										return '<input type="radio" name="cptchkbox"   id="'+data+'" value="'+full.code+' ('+full.shortDescription+')"/>';
									 }, 
					  		"sWidth" : "10%", "bSearchable" : false,  "asSorting" : [ "asc" ]
						 },
	                   { "mDataProp": "code","bSearchable" : true, "bSortable" : true,"sWidth" : "10%"}, 
	                   { "mDataProp": "shortDescription","bSearchable" : true, "bSortable" : true,"sWidth" : "80%"}
	               ],
	     "bLengthChange": false,
	     "sPaginationType": "full_numbers",
	     "bProcessing": true,
	     "bServerSide" : true,
	     "fnRowCallback": function(row, data, dataIndex){
	         // If row ID is in the list of selected row IDs
	         if ( (data.id in checkedCPTItemsMap) ) {
	        	 $(row).find('input[type="checkbox"]').prop('checked', true);
	         }
	      },
	      "fnServerData" : datatable2Rest
	});

});

	var source = getContextPath()+'leadMembership/${id}/hedisMeasureList';
	$.ajax({
		url : source,
	    success: function(data, textStatus, jqXHR)
	    {
	       $('#leadMbrHedisMeasureList').html(data);
	    },
	    error: function (jqXHR, textStatus, errorThrown)
	    {
	  	  alert("Error leadMbrHedisMeasureList");
	    }
	});
	
	
	function leadMbrNewHedisMeasure(){
		
		var source = getContextPath()+'leadMembership/${id}/hedisMeasureDetails';
		$.ajax({
			url : source,
		 	success: function(data, textStatus, jqXHR)
		    {
		 		$('#leadMbrPrdvrDetails').html(data);
		    },	
		    error: function (jqXHR, textStatus, errorThrown)
		    {
		  	  alert("Error");
		    }
		});
	}
	
	claimList();  // call leadMembershipClaimList
	
	function claimList(){
		var source = getContextPath()+'/leadMembership/${id}/claimList';
		$.ajax({
			url : source,
		    success: function(data, textStatus, jqXHR)
		    {
		       $('#leadMbrClaim').html(data);
		    },
		    error: function (jqXHR, textStatus, errorThrown)
		    {
		  	  alert("Error leadMbrClaim");
		    }
		});	
	}
		
	function claimNew(){
		var source = getContextPath()+'leadMembership/${id}/claim/new';
		$.ajax({
			url : source,
		    success: function(data, textStatus, jqXHR)
		    {
		       $('#leadMbrClaim').html(data);
		    },
		    error: function (jqXHR, textStatus, errorThrown)
		    {
		  	  alert("Error leadMbrClaim");
		    }
		});	
	}
	
	
	function addLeadMbrClaim(){
		(function(root, factory) {

			  // AMD
			  if (typeof define === "function" && define.amd) {
			    define(["exports", "jquery"], function(exports, $) {
			      return factory(exports, $);
			    });
			  }

			  // CommonJS
			  else if (typeof exports !== "undefined") {
			    var $ = require("jquery");
			    factory(exports, $);
			  }

			  // Browser
			  else {
			    factory(root, (root.jQuery || root.Zepto || root.ender || root.$));
			  }

			}(this, function(exports, $) {

			  var patterns = {
			    validate: /^[a-z_][a-z0-9_]*(?:\[(?:\d*|[a-z0-9_]+)\])*$/i,
			    key:      /[a-z0-9_]+|(?=\[\])/gi,
			    push:     /^$/,
			    fixed:    /^\d+$/,
			    named:    /^[a-z0-9_]+$/i
			  };

			  function FormSerializer(helper, $form) {

			    // private variables
			    var data     = {},
			        pushes   = {};

			    // private API
			    function build(base, key, value) {
			      base[key] = value;
			      return base;
			    }

			    function makeObject(root, value) {

			      var keys = root.match(patterns.key), k;

			      // nest, nest, ..., nest
			      while ((k = keys.pop()) !== undefined) {
			        // foo[]
			        if (patterns.push.test(k)) {
			          var idx = incrementPush(root.replace(/\[\]$/, ''));
			          value = build([], idx, value);
			        }

			        // foo[n]
			        else if (patterns.fixed.test(k)) {
			          value = build([], k, value);
			        }

			        // foo; foo[bar]
			        else if (patterns.named.test(k)) {
			          value = build({}, k, value);
			        }
			      }

			      return value;
			    }

			    function incrementPush(key) {
			      if (pushes[key] === undefined) {
			        pushes[key] = 0;
			      }
			      return pushes[key]++;
			    }

			    function encode(pair) {
			      switch ($('[name="' + pair.name + '"]', $form).attr("type")) {
			        case "checkbox":
			          return pair.value === "on" ? true : pair.value;
			        default:
			          return pair.value;
			      }
			    }

			    function addPair(pair) {
			      if (!patterns.validate.test(pair.name)) return this;
			      var obj = makeObject(pair.name, encode(pair));
			      data = helper.extend(true, data, obj);
			      return this;
			    }

			    function addPairs(pairs) {
			      if (!helper.isArray(pairs)) {
			        throw new Error("formSerializer.addPairs expects an Array");
			      }
			      for (var i=0, len=pairs.length; i<len; i++) {
			        this.addPair(pairs[i]);
			      }
			      return this;
			    }

			    function serialize() {
			      return data;
			    }

			    function serializeJSON() {
			      return JSON.stringify(serialize());
			    }

			    // public API
			    this.addPair = addPair;
			    this.addPairs = addPairs;
			    this.serialize = serialize;
			    this.serializeJSON = serializeJSON;
			  }

			  FormSerializer.patterns = patterns;

			  FormSerializer.serializeObject = function serializeObject() {
			    return new FormSerializer($, this).
			      addPairs(this.serializeArray()).
			      serialize();
			  };

			  FormSerializer.serializeJSON = function serializeJSON() {
			    return new FormSerializer($, this).
			      addPairs(this.serializeArray()).
			      serializeJSON();
			  };

			  if (typeof $.fn !== "undefined") {
			    $.fn.serializeObject = FormSerializer.serializeObject;
			    $.fn.serializeJSON   = FormSerializer.serializeJSON;
			  }

			  exports.FormSerializer = FormSerializer;

			  return FormSerializer;
			}));

		alert( " addLeadMbrClaim ");
		
		$("#leadMbrClaim .clrRed").html("");
		check=0;
		$(".checkData").each(function( index ) {
			if($(this).val()){
				
			}
			else{ check++;}
		});
		if(check == 0){
			
			 var leadClaimData = $('#leadMembershipClaim').serializeJSON();
			  leadClaimData = removeAllBlankOrNull(JSON.parse(leadClaimData));
			 console.log(JSON.stringify(leadClaimData)); 
			var source = getContextPath()+'leadMembership/${id}/claim/save.do?add' ;
			$.ajax({
				url : source,
				type: "POST",
		         data: JSON.stringify(leadClaimData),
		         dataType: "json",
		         contentType: "application/json",
			    success: function(data, textStatus, jqXHR)
			    {
			       $('#leadMbrClaim').html(data);
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			  	  alert("Error leadMbrClaim");
			    }
			});	
		}
		else{
			$("#leadMbrClaim .clrRed").html("Some of the required fields are missing. Please rectify it");
		}
			return false;
	}
	

	  
	function removeAllBlankOrNull(JsonObj) {
		
	    $.each(JsonObj, function(key, value) {
	    	 console.log('JsonObj'+ key);
	        if (value === "" || value === null) {
	            console.log('%s (%s) : DELETE', key, value);
	            delete JsonObj[key];
	        } else if (typeof(value) === "object") {
	            console.log('%s (%o) : OBJECT', key, value);
	            JsonObj[key] = removeAllBlankOrNull(value);
	        }
	    });
	    return JsonObj;
	}

	function modifyLeadMbrClaim($refrenceId){
		alert(" inside  modifyLeadMbrClaim ");
		return false;
	}
	
	function deleteLeadMbrClaim($refrenceId){
		alert(" inside  deleteLeadMbrClaim ");
		return false;
	}
	
	


	var source = getContextPath()+'leadMembership/${id}/providerDetailsList';
	$.ajax({
		url : source,
	    success: function(data, textStatus, jqXHR)
	    {
	       $('#leadMbrPrdvrDetails').html(data);
	    },
	    error: function (jqXHR, textStatus, errorThrown)
	    {
	  	  alert("Error leadMbrInsList");
	    }
	});
	
	
	function mbrNewPrvdr(){
		
		var source = getContextPath()+'leadMembership/${id}/providerDetails';
		$.ajax({
			url : source,
		 	success: function(data, textStatus, jqXHR)
		    {
		 		$('#leadMbrPrdvrDetails').html(data);
		    },	
		    error: function (jqXHR, textStatus, errorThrown)
		    {
		  	  alert("Error");
		    }
		});
	}
	
	function addLeadProvider()
	{
		var url = getContextPath()+'leadMembership/${id}/provider/save.do?add'; 
		var dataList = 	$("#leadMembershipProvider").serializeArray();
		$.ajax({
	           type: "POST",
	           url: url,
	           data: dataList, 
	           success: function(data)
	           {
	               $('#leadMbrPrdvrDetails').html(data);
	           },
	    		error:function(data)
	    		{
	    			 alert(data); 
	    		}
	         });
	}
	
	function leadPrvdrDetails(id){
		
		var source = getContextPath()+'leadMembership/${id}/providerDetails';
		$.ajax({
			url : source,
		 	success: function(data, textStatus, jqXHR)
		    {
		 		$('#leadMbrPrdvrDetails').html(data);
		    },	
		    error: function (jqXHR, textStatus, errorThrown)
		    {
		  	  alert("Error");
		    }
		});
	}
	
function modifyleadPrvdrDetails(id){
		
	var url = getContextPath()+'leadMembership/${id}/provider/save.do?update'; 
	var dataList = 	$("#leadMembershipProvider").serializeArray();
		$.ajax({
			 type: "POST",
	         url: url,
	        data: dataList, 
		 	success: function(data, textStatus, jqXHR){
		 		$('#leadMbrPrdvrDetails').html(data);
		    },	
		    error: function (jqXHR, textStatus, errorThrown)
		    {
		  	  alert("Error");
		    }
		});
	}

function deleteleadPrvdrDetails(id){
	
	var url = getContextPath()+'leadMembership/${id}/provider/save.do?delete'; 
	var dataList = 	$("#leadMembershipProvider").serializeArray();
		$.ajax({
			 type: "POST",
	         url: url,
	        data: dataList, 
		 	success: function(data, textStatus, jqXHR){
		 		$('#leadMbrPrdvrDetails').html(data);
		    },	
		    error: function (jqXHR, textStatus, errorThrown)
		    {
		  	  alert("Error");
		    }
		});
	}

	function modifyLeadMbrDetails()
	{
		
		var url = getContextPath()+'leadMembership/${id}/save.do?update';
		var dataList = 	$("#leadMembership").serialize();
		$.ajax({
	           type: "POST",
	           url: url,
	           data: dataList, 
	           success: function(data)
	           {
	               $('#leadMbrDetails').html(data);
	           },
	    		error:function(data)
	    		{
	    			 alert("Lead Membership Details Modify Error");
	    		}
	         });
	}
	
	
	
	function deleteLeadMbrDetails()
	{
		if (confirm("Action cannot be undone.Click 'Ok' to delete.") == true) 
		{
			var url = getContextPath()+'membership/${id}/save.do?delete';
			var dataList = 	$("#leadMembership").serialize();
			$.ajax({
		           type: "POST",
		           url: url,
		           data: dataList, 
		           success: function(data)
		           {
		        	   $('#leadMbrDetails').html(data);
		           },
		    		error:function(data)
		    		{
		    			 alert("Membership Details Delete Error");
		    		}
		         });
		}		
	}
	var source = getContextPath()+'leadMembership/${id}/contactList';
	$.ajax({
		url : source,
	    success: function(data, textStatus, jqXHR)
	    {
	       $('#leadMembershipContactList').html(data);
	    },
	    error: function (jqXHR, textStatus, errorThrown)
	    {
	  	  alert("Error");
	    }
	});
	
	var source = getContextPath()+'leadMembership/${id}/detailsList';
	$.ajax({
		url : source,
	    success: function(data, textStatus, jqXHR)
	    {
	       $('#leadMbrInsList').html(data);
	    },
	    error: function (jqXHR, textStatus, errorThrown)
	    {
	  	  alert("Error leadMbrInsList");
	    }
	});
		
	function contact(leadId,contactId)
	{
		var source = getContextPath()+'leadMembership/'+leadId+'/contact/'+contactId;
		$.ajax({
			url : source,
		     success: function(data, textStatus, jqXHR)
		    {
		    	$('#leadMembershipContactList').html(data);
		    },
		    error: function (jqXHR, textStatus, errorThrown)
		    {
		  	  alert("Error");
		    }
		});
		return false;	
	}
		
	function newContact()
	{
		var source = getContextPath()+'leadMembership/${id}/contact/new';
		$.ajax({
			url : source,
		 	success: function(data, textStatus, jqXHR)
		    {
		 		$('#leadMembershipContactList').html(data);
		    },
		    error: function (jqXHR, textStatus, errorThrown)
		    {
		  	  alert("Error");
		    }
		});
	}
	
	
	
	function contactList()
	{
		var source = getContextPath()+'leadMembership/${id}/contactList';
		$.ajax({
			url : source,
		    success: function(data, textStatus, jqXHR)
		    {
		       $('#membershipContactList').html(data);
		    },
		    error: function (jqXHR, textStatus, errorThrown)
		    {
		  	  alert("Error");
		    }
		});
	}
	
	function addContact()
	{
		var url = getContextPath()+'leadMembership/${id}/contact/save.do?add'; 
		var dataList = 	$("#contact").serializeArray();
		$.ajax({
	           type: "POST",
	           url: url,
	           data: dataList, 
	           success: function(data)
	           {
	               $('#leadMembershipContactList').html(data);
	           },
	    		error:function(data)
	    		{
	    			 alert(data); 
	    		}
	         });
	}
	
	function modifyContact()
	{
		var url = getContextPath()+'leadMembership/${id}/contact/save.do?update'; 
		alert(" url "+url);
		var dataList = 	$("#contact").serializeArray();
		$.ajax({
	           type: "POST",
	           url: url,
	           data: dataList, 
	           success: function(data)
	           {
	               $('#leadMembershipContactList').html(data);
	           },
	    		error:function(data)
	    		{
	    			 alert(data); 
	    		}
	     });
	}
	function deleteContact()
	{
		if (confirm("Action cannot be undone.Click 'Ok' to delete.") == true) 
		{
			var url = getContextPath()+'membership/${id}/contact/save.do?delete'; 
			var dataList = 	$("#contact").serializeArray();
			$.ajax({
		           type: "POST",
		           url: url,
		           data: dataList, 
		           success: function(data)
		           {
		               $('#membershipContactList').html(data);
		           },
		    		error:function(data)
		    		{
		    			 alert("Member Contact Delete Error"); 
		    		}
		     });
	    } 
	}
	
	function prvdr(memberId,prvdrId)
	{
			var url = getContextPath()+'/membership/'+prvdrId+'/providerDetails'; 
			$.ajax({
		          url: url,
		          success: function(data, textStatus, jqXHR)
		           {
		        	   $('#mbrPrdvrDetails').html(data);
		           },
		    		error:function(data)
		    		{
		    			 alert(data); 
		    		}
		     });
	}
	
	function mbrNewIns()
	{
		var source = getContextPath()+'leadmembership/${id}/details/new';
		$.ajax({
			url : source,
		    success: function(data, textStatus, jqXHR)
		    {
		       $('#leadMbrInsList').html(data);
		       $('#leadMbrInsList form').attr('id','leadMembershipInsurance');
		    },
		    error: function (jqXHR, textStatus, errorThrown)
		    {
		  	  alert("Member New Isurance Error");
		    }
		});
	}
	
	function mbrDetails(mbrId,mbrDetailsId)
	{
		
		var source = getContextPath()+'leadMembership/${id}/details/'+mbrDetailsId+'/display';
		$.ajax({
			url : source,
		    success: function(data, textStatus, jqXHR)
		    {
		       $('#leadMbrInsList').html(data);
		       $('#leadMbrInsList form').attr('id','leadMembershipInsurance');
		    },
		    error: function (jqXHR, textStatus, errorThrown)
		    {
		  	  alert("Error mbrInsDetails");
		    }
		});
	}
	
	function leadMbrInsList()
	{
		
		var source = getContextPath()+'membership/${id}/detailsList';
		$.ajax({
			url : source,
		    success: function(data, textStatus, jqXHR)
		    {
		       $('#leadMbrInsList').html(data);
		    },
		    error: function (jqXHR, textStatus, errorThrown)
		    {
		  	  alert("Error leadMbrInsList");
		    }
		});
	}
	
	function addMbrInsDetails()
	{
		var url = getContextPath()+'leadMembership/${id}/details/save.do?add'; 
		var dataList = 	$("#leadMembershipInsurance").serialize();
		$.ajax({
	           type: "POST",
	           url: url,
	           data: dataList, 
	           success: function(data)
	           {
	            	$('#leadMbrInsList').html(data);
	            	   $('#leadMbrInsList form').attr('id','leadMembershipInsurance');
	       		    
	           },
	    		error:function(data)
	    		{
	    			 alert('Add Member New Insurance Error '+ data); 
	    		}
	         });
	}
	
	function modifyMbrInsDetails(mbrInsId)
	{
		var url = getContextPath()+'leadMembership/${id}/details/'+mbrInsId+'/save.do?update'; 
		var dataList = 	$("#leadMembershipInsurance").serialize();
		$.ajax({
	           type: "POST",
	           url: url,
	           data: dataList, 
	           success: function(data)
	           {
	            	$('#leadMbrInsList').html(data);
	            	$('#leadMbrInsList form').attr('id','leadMembershipInsurance');
	           },
	    		error:function(data)
	    		{
	    			 alert('Modify Membership Insurance Error '+ data); 
	    		}
	         });
	}
	function deleteMbrInsDetails(mbrInsId)
	{
		if (confirm("Action cannot be undone.Click 'Ok' to delete.") == true) 
		{
			var url = getContextPath()+'leadMembership/${id}/details/'+mbrInsId+'/save.do?delete'; 
			var dataList = 	$("#leadMembershipInsurance").serialize();
			$.ajax({
		           type: "POST",
		           url: url,
		           data: dataList, 
		           success: function(data)
		           {
		            	$('#leadMbrInsList').html(data);
		            	$('#leadMbrInsList form').attr('id','leadMembershipInsurance');
		           },
		    		error:function(data)
		    		{
		    			 alert('Delete Member Insurance Error '+ data); 
		    		}
		         });
		}	
	}
	function membershipDetails()
	{
		
		var url = getContextPath()+'/membership/${id}'; 
		$.ajax({
	          url: url,
	          success: function(data, textStatus, jqXHR)
	           {
	        	   $('#mbrDetails').html(data);
	           },
	    		error:function(data)
	    		{
	    			 alert(data); 
	    		}
	     });
	}
	
	var source = getContextPath()+'leadMembership/${id}/detailsList';
	$.ajax({
		url : source,
	    success: function(data, textStatus, jqXHR)
	    {
	       $('#leadMbrInsList').html(data);
	    },
	    error: function (jqXHR, textStatus, errorThrown)
	    {
	  	  alert("Error leadMbrInsList");
	    }
	});
	
	function mbrInsList()
	{
		
		var source = getContextPath()+'leadMembership/${id}/detailsList';
		$.ajax({
			url : source,
		    success: function(data, textStatus, jqXHR)
		    {
		       $('#leadMbrInsList').html(data);
		    },
		    error: function (jqXHR, textStatus, errorThrown)
		    {
		  	  alert("Error mbrInsList");
		    }
		});
	}
	
$(document).ready(function(){
	if("${leadMembershipClaim.id}" == ""){
		addClaimDetailsRow(false);
	}
	 $('body').on('click',".addClaimDetailsRow", function(){
    	addClaimDetailsRow(true);
    });
	$('body').on('click',".deleteClaimDetailsRow", function(){
    	$(".claim_details .claimDetailsList:last").remove();
    	showDeleteClaimDetailsButton();
    });
});


function deleteClaimDetailsRow($row){
	$(".claim_details .list"+$row).remove();
	showDeleteClaimDetailsButton();
}

function addClaimDetailsRow($checkData){
	$("#leadMbrClaim .clrRed").html("");
	check=0;
	$(".checkData").each(function( index ) {
		if($(this).val()){
			
		}
		else{ check++;}
	});
	if(check == 0 || !$checkData){
		$claimStartDate = '<div class="form-group col-sm-2 required"><div class="col-sm-12"><label class="control-label">Start Date</label><input name ="leadMbrClaimDetailsList[][claimStartDate]" id="leadMbrClaimDetailsList'+$listLength+'.claimStartDate" class="checkData form-control startDate datepicker" /></div></div>';
		$claimEndDate = '<div class="form-group col-sm-2  required"><div class="col-sm-12"><label class="control-label">End Date</label><input name="leadMbrClaimDetailsList[][claimEndDate]" id="leadMbrClaimDetailsList'+$listLength+'.claimEndDate" class="checkData form-control endDate datepicker" /></div></div>';
		$cpt = '<div class="form-group col-sm-2"><div class="col-sm-12"><label><a href="javascript:void(0)" id="addCPT'+$listLength+'" class="btn btn-success btn-xs white-text addModalCPT"> <span class="glyphicon glyphicon-plus-sign"></span>CPT</a> <a href="javascript:void(0)" id="removeCPT'+$listLength+'" class="removeCPT btn btn-success btn-xs white-text"> <span class="glyphicon glyphicon-minus-sign"></span>CPT</a></label><select name="leadMbrClaimDetailsList[][cpt][id]" id="leadMbrClaimDetailsList'+$listLength+'.cpt.id" class="form-control" /></select></div></div>';
		$riskReconCosDes = '<div class="form-group col-sm-2  required"><div class="col-sm-12"><label class="control-label">RiskRecon</label><select name="leadMbrClaimDetailsList[][riskReconCosDes]" id="leadMbrClaimDetailsList'+$listLength+'.riskReconCosDes" class="checkData form-control"  ></select></div></div>';
		$srvProviderName = '<div class="form-group col-sm-2"><div class="col-sm-12"><label>Srv Prvdr Name</label><input name="leadMbrClaimDetailsList[][srvProviderName]" id="leadMbrClaimDetailsList'+$listLength+'.srvProviderName" class="form-control"  /></div></div>';
		$srvProviderDesc = '<div class="form-group col-sm-2"><div class="col-sm-12"><label>Srv Prvdr Code</label><div class="col-sm-8"><input name="leadMbrClaimDetailsList[][srvProvider]" id="leadMbrClaimDetailsList'+$listLength+'.srvProvider" class="form-control" /></div><div class="col-sm-4"><button type="button" onclick="deleteClaimDetailsRow('+$listLength+')" class="deleteClaimDetailsRow btn btn-success btn-xs white-text">- Delete</button></div></div></div>';
		$(".claim_details").append('<div class="col-sm-12 claimDetailsList list'+$listLength+'">'+$claimStartDate+$claimEndDate+$cpt+$riskReconCosDes+$srvProviderName+$srvProviderDesc+'</div>');
		showDeleteClaimDetailsButton();
		$.getJSON(getContextPath()+'/riskRecon/list?pageNo=0&pageSize=200', function(data){
		    //clear the current content of the select
		    //iterate over the data and append a select option
		    if(data.data.list){
		    	$("[id='leadMbrClaimDetailsList"+$listLength+".riskReconCosDes']").append('<option value="">Select one</option>');
		    }
		    $.each(data.data.list, function(key, val){
		    	 $("[id='leadMbrClaimDetailsList"+$listLength+".riskReconCosDes']").append('<option value="'+val.id+'">' + val.name +'</option>');
		    });
		   
		}).success(function() {  
			$listLength++;
		});
	}
	else if($checkData){
		$("#leadMbrClaim .clrRed").html("Some of the required fields are missing. Please rectify it");
	}
}

function showDeleteClaimDetailsButton(){
	if($(".claimDetailsList").length > 1){
    	$(".deleteClaimDetailsRow").show();
    }
    else{
    	$(".deleteClaimDetailsRow").hide();
    }
}


 
</script>


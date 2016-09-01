<%@  page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@  taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="context1"
	value="${pageContext.request.contextPath}/${userpath}" />
<script>

$(document).ready(function() {
	
	  var completed = 0;
	  var pending = 0;

	
		$("#hedisGenerate").click(function(event)
		{
			$(".clrRed").text("");
			callHedisGenerate();
		});
		 var hedisDescription = new Array;	
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
					Cookies.set('insu', insSelectValue,{path: cookiePath });
				}
				$('select[id="insu"]').val(insSelectValue);
 			 var $selectPrvdr = $('#extFilterPrvdr');
 	    	  $.getJSON(getContextPath()+'/insurance/providerlist?insId='+insSelectValue, function(data){
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
					Cookies.set('prvdr', prvdrSelectValue,{path: cookiePath });
				 }	
				$('select[id="prvdr"]').val(prvdrSelectValue);
 				 hedisRuleDropdown(true);
 	    	 });
    	  }
    	  
    	  var columns ;
    	  var hedisRuleDropdown = function(hedisDropDownSet){
    		 
    		  if(hedisDropDownSet)
    		  	$('#hedisRule').find('option').remove();
				
				var $selectHedisRule = $('#extFilterHedisRule');
				var $selectHedisRule1 = $('#extFilterHedisRule1');
				var insSelectValue1 = $("#insu option:selected").val();
				//var hedisSelectValue1 = $("#").val();
		 		 if( $("#insu option:selected").val() == null){
		     		 insSelectValue1 = 1;
		     	 }
		 		
		    	  $.getJSON(getContextPath()+'/hedisMeasureRule/list?insId='+insSelectValue1, function(data){
		    		 
		    		  var hedisRuleList = new Array;
		    		  if(hedisDropDownSet)
		    		  {
		    			  var hedisRuleCacheList = getDropDownCache('hedisRule');
		    			 
		    			  //clear the current content of the select
						     var s = $('<select id=\"hedisRule\" style=\"width:150px;\" multiple=\"multiple\">');
						     //iterate over the data and append a select option
						     $.each(data.data, function(key, val){
						    	 hedisRuleSelected = "";
						    	 if(jQuery.inArray(val.id, hedisRuleCacheList) != -1){
						    		 hedisRuleSelected ="selected";
									}
						    	 s.append('<option value='+val.id+' '+ hedisRuleSelected+ '>' + val.shortDescription +'</option>');
						    	 hedisDescription.push(val.description);
						     });
						     s.append('</select>');
						     $selectHedisRule.html(s);
						     $('#hedisRule').multiselect({numberDisplayed: 0, 
						    	 buttonWidth: '150px',
						    	 includeSelectAllOption: true,
						    	 templates: {
									 ul: '<ul class="multiselect-container dropdown-menu hedisRule"></ul>'
								 },
						    });
		    		  }
		    		  	dropDownCache("hedisRule")
				 });
    	  }
    	  
    	  var callHedisGenerate = function(){
    		  if(!$("#hedisRule option:selected").text())
    			  {
    			  	$(".clrRed").text("Select atleast one hedis measure");
    			  	return false;
    			  }

				columns = new Array();
	     		columns.push({ "mDataProp": "lastName", 	"bSearchable" : false,  "sClass": "center","sWidth" : "3%",
	     						"render": function (data, type, full, meta) {
		      								return '<a href="javascript:void(0)" id="'+full.id+'" onclick="mbrHedisFollowup('+full.id+',\''+full.lastName+'\',\''+full.firstName+'\')"><span class="glyphicon glyphicon-pencil"></span></a>';
		        						  }
	     					});
	     		columns.push({ "mDataProp": "mbrProviderList.0.prvdr.name","bSearchable" : true, "bSortable" : true,"sClass": "center widthM","sWidth" : "10%",
	     			"render": function (data, type, full, meta) {
	     				var prvdId;
	     				for(var i = 0; i<full.mbrProviderList.length; i++)
                        {
	     					prvdId = full.mbrProviderList[i].prvdr.id;  
                        }
 						
						return '<a href="${context1}/provider/'+prvdId+'">'+data+'</a>';
				  }
	     		});
	     		columns.push({ "mDataProp": "lastName","bSearchable" : true, "bSortable": true,"sClass": "center widthS","sWidth" : "10%" ,
						"render": function (data, type, full, meta) {
								return '<a href="${context1}/membership/'+full.id+'/complete">'+data+'</a>';
						  }
				});
	     		columns.push({ "mDataProp": "firstName","bSearchable" : true, "bSortable": true,"sClass": "center widthS","sWidth" : "10%"  });
	     		columns.push({ "mDataProp": "dob","bSearchable" : true, "bSortable": true,"sClass": "center","sWidth" : "5%",
				     			"render": function (data, type, full, meta) {
				     				var date = new Date(data);
      		   	        			var month = date.getMonth() + 1;
      		   	        			var d = date.getDate();
      		   	       				 return (month > 9 ? month : "0" + month) + "/" + (d > 9 ? d : "0" + d) + "/" + date.getFullYear();
							}
	     		});
	     		columns.push({ "mDataProp": "id","bSearchable" : true, "bSortable": true,"sClass": "center","sWidth" : "5%",
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
						}
				});
	     		columns.push({ "mDataProp": "genderId.code","bSearchable" : true, "bSortable": true,"sClass": "center","sWidth" : "5%" });
	     		columns.push({ "mDataProp": "medicaidNo","bSearchable" : true, "bSortable": true,"sClass": "center","sWidth" : "5%", "sDefaultContent": "" });
	     		columns.push({ "mDataProp": "medicareNo","bSearchable" : true, "bSortable": true,"sClass": "center","sWidth" : "5%", "sDefaultContent": "" });
	     		columns.push({ "mDataProp": "mbrInsuranceList[0].srcSysMbrNbr","bSearchable" : true, "bSortable": true,"sClass": "center","sWidth" : "5%", "sDefaultContent": "" });
	     		columns.push({ "mDataProp": "contactList[].homePhone","bSearchable" : true, "bSortable": true,"sClass": "center","sWidth" : "5%", "sDefaultContent": "",
	     			
                           "render": function  ( data, type, full, meta )  {
	                                         var homePhoneList=[];
	                                         for(var i = 0; i<full.contactList.length; i++)
	                                         {
	                                        	 homePhoneList[i] = full.contactList[i].homePhone;  
	                                         }
                      						return homePhoneList.join(', ');
                    					}
	     		});
	     		columns.push({ "mDataProp": "contactList[].mobilePhone","bSearchable" : true, "bSortable": true,"sClass": "center","sWidth" : "5%", "sDefaultContent": "",
	     			  "render": function  ( data, type, full, meta )  {
                          var mobilePhoneList=[];
                          for(var i = 0; i<full.contactList.length; i++)
                          {
                        	  mobilePhoneList[i] = full.contactList[i].mobilePhone;  
                          }
   						return mobilePhoneList.join(', ');
 					}
	     		});
	     		columns.push({ "mDataProp": "mbrHedisMeasureList.0.dueDate","bSearchable" : true, "bSortable": false,"sClass": "center","sWidth" : "5%", "sDefaultContent": "",
				     			"render": function (data, type, full, meta) {
				     				var date = new Date(data);
				   	        			var month = date.getMonth() + 1;
				   	        			var d = date.getDate();
				   	       				 return (month > 9 ? month : "0" + month) + "/" + (d > 9 ? d : "0" + d) + "/" + date.getFullYear();
					  		}
	     		});
	     		
	     		var myTable = $("#membershipTable");
	     		var thead = myTable.find("thead");  
	     		$("#membershipTable th").each(function() {
					if($(this).attr("role") == "row"){
					}else{
						$(this).remove();
					}
							
       			}); 
	     		
	     		var hedisRuleList = document.getElementById('hedisRule').options;
	     		
	     		$.each( hedisRuleList, function(m, value ){
	     				$('#membershipTable').find('tr').each(function(){
	     					if($("#hedisRule option:selected").text().indexOf(value.text) >= 0)
	     					{	
	     						$(this).find('th').eq(-1).after('<th> <center title="'+hedisDescription[m]+'">'+value.text+'</center></th>'); }
	     				});
	     		});
					
				
	     		$.each( hedisRuleList, function( i, value ){
	     				if($("#hedisRule option:selected").text().indexOf(value.text) >= 0)
	     				{
		     				columns.push({ "mDataProp": "mbrHedisMeasureList[ ].hedisMeasureRule.shortDescription","bSearchable" : false, "bSortable" : false,"sClass": "center","sWidth" : "5%", "sDefaultContent": "",
		      							    "render": function (data, type, full, meta) {
		      							    	var params = {};
		      							    	full.mbrHedisMeasureList.forEach(function( item ) {
		      							    		if(item.activeInd == 'N' ){
		      							    			params[item.hedisMeasureRule.shortDescription] = item.hedisMeasureRule.shortDescription;
		      							    			data = data.replace(item.hedisMeasureRule.description,'');
		      							    		}
		      							    	});
				      									   var returnType ='';
				      										if(data.indexOf(value.text) >= 0){
				      											if(value.text in params){
				      												completed++;
				      												return '<label class="text-danger">X</label>';
				      											} else {
				      												pending++;
				      												return 'X';
				      											}
				      												
				      										}	
				      										else
			      											return '';
		      								}
		      						  });	
	      			}
	      		});
	     		callDatableWithChangedDropDown();
    	  }
    	  
    	  function callDatableWithChangedDropDown(){
    		   var insSelectValue= $("#insu option:selected").val();
    		   var prvdrSelectValue= $("#prvdr option:selected").val();
    		   var hedisRuleSelectValue= $("#hedisRule option:selected").val();
    		   
    		   var ruleArray = new Array;
    		   $("#hedisRule option:selected").each  ( function() {
    		    	ruleArray.push ( $(this).val() );
    		    });

    		   if ( $.fn.DataTable.isDataTable('#membershipTable') ) {
  						$('#membershipTable').DataTable().destroy();
			   }
				$('#membershipTable tbody').empty();
    		   GetMembershipByInsPrvdrHedisRule(insSelectValue,prvdrSelectValue,hedisRuleSelectValue, ruleArray, columns);
    	}  
    	     
    	$(document.body).on('change',"#insu",function (e) {
    		Cookies.set('insu', $("#insu option:selected").val(),{path: cookiePath });
    		Cookies.remove('prvdr',{path: cookiePath });
    		if ( $.fn.DataTable.isDataTable('#membershipTable') ) {
					$('#membershipTable').DataTable().destroy();
	   		}
    		$('#membershipTable tbody').empty();
    		providerDropdown();
  		});
    	
    	$(document.body).on('change',"#prvdr",function (e) {
    		Cookies.set('prvdr', $("#prvdr option:selected").val(),{path: cookiePath });
    		if ( $.fn.DataTable.isDataTable('#membershipTable') ) {
					$('#membershipTable').DataTable().destroy();
	   		}
    		$('#membershipTable tbody').empty();
  		});
  	
  	  $(document.body).on('change',"#hedisRule",function (e) {
  		if ( $.fn.DataTable.isDataTable('#membershipTable') ) {
			$('#membershipTable').DataTable().destroy();
			}
  			hedisRuleDropdown(false);
   		});
     	
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
		 
		   var sSearchIns = paramMap.sSearchIns;
		   var sSearchPrvdr = paramMap.sSearchPrvdr;
		   var sSearchHedisRule = paramMap.sSearchHedisRule;
		   var ruleIds = paramMap.ruleIds;
		   Cookies.set('hedisRule', ruleIds,{path: cookiePath });
		   dropDownCache('hedisRule');
		   //create new json structure for parameters for REST request
		   var restParams = new Array();
		   restParams.push({"name" : "pageSize", "value" : pageSize});
		   restParams.push({"name" : "pageNo", "value" : pageNum });
		   restParams.push({"name" : "sort", "value" : sortName });
		   restParams.push({"name" : "sortdir", "value" : sortDir });
		   restParams.push({"name" : "sSearch" , "value" : paramMap.sSearch  });
		   restParams.push({"name" : "sSearchIns" , "value" : sSearchIns  });
		   restParams.push({"name" : "sSearchPrvdr" , "value" : sSearchPrvdr  });
		   restParams.push({"name" : "sSearchHedisRule" , "value" : sSearchHedisRule  });
		   restParams.push({"name" : "ruleIds" , "value" : ruleIds  });
		   var width;
		   width = $('#membershipTable th').length * 100;
		   if(parseInt(width) > 1200){
			   width = width + "px";
			   $('#membershipTable').width(width);
		   }	   
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
     	
     	
     	  GetMembershipByInsPrvdrHedisRule = function (insId, prvdrId, hedisRuleId,ruleArray, aoColumns) {
      		
  	        var oTable = $('#membershipTable').removeAttr( "width" ).dataTable({  
  	        	"sDom": 'Bfrtip',
	        	 "buttons": [
	        	             {
	        	                 extend: 'excelHtml5',
	        	                 title: 'Hedis Measure RuleTable Export'
	        	             }
	        	             
		                   ],
  	         "bDestroy" : true,	
     	     "sAjaxSource" : getContextPath()+'/reports/hedisMembership/list',
     	     "sAjaxDataProp" : 'data.pagination.list',
             "aoColumns":  aoColumns,      
     	     "bLengthChange": false,
     	     "iDisplayLength": 15,
     	     "sPaginationType": "full_numbers",
     	     "bProcessing": true,
     	     "bServerSide" : true,
     	     "fnServerParams": function ( aoData ) {
                aoData.push(
                    {"name": "sSearchIns", "value": insId},
                    {"name": "sSearchPrvdr", "value": prvdrId },
                    {"name": "sSearchHedisRule", "value": hedisRuleId },
                    {"name": "ruleIds", "value": ruleArray }
                );
             },        
     	     "fnServerData" : datatable2RestMembership,
     	    "fnFooterCallback": function ( nRow, aaData, iStart, iEnd, aiDisplay ) {}
     	});
  	        
     }


     	$('select').css({'width': 150});
     	
     	$( "#followupSubmit" ).click(function(event) {
     		var rulesList = [];
     		 var ruleIds = []; var dos = [];
     		$("span[name='dosError[]']").html("");
     		
    		  $.each($("input[name='rule_group[]']"), function(i) {
    			  var ruleMap ={}; 
    			  if(this.checked)
    			  {
    				  ruleIds.push($(this).val());
    				  
    				  if($("input[name='dos[]']").eq(i).val().length < 1)
    				  {
    					  $("span[name='dosError[]']").eq(i).html("Date of Service required");
    				  }
    				  else
    					{
    					  dos.push($("input[name='dos[]']").eq(i).val());
    					  ruleMap[$(this).val()] = $("input[name='dos[]']").eq(i).val();
    					  rulesList.push(ruleMap);
    					}	  
    			  }	 
    		  });
     		 
     		  if($("#followup_details").val().length <= 5)
     		  {
     		   		alert(" Followup Details must be minimum 5 charactes");
     		   		return false;
     		 }
     		  var followup_details  = $("#followup_details").val();
     		  var  mbr_id = $("#mbr_id").val();
     		  
     		  var restParams1 ="{\"followupDetails\" :\""+ followup_details+"\",\"mbr\": {\"id\":"+mbr_id+"},\"mbrHedisMeasureIds\":"+JSON.stringify(rulesList)+"}";
     		  var source = getContextPath()+'/reports/membershipHedis/followup';
     		  
     		  $.ajax({
     			  dataType: 'json',
                   contentType: "application/json;charset=UTF-8",
     		      url : source,
     		      type: 'POST',
     		      data : restParams1,
     		      success: function(data, textStatus, jqXHR)
     		      {
     		    	  
     		          alert("Followup Success Done");
     		          $(".clrRed").text("Hedis Followup Notes recorded successfully");
     		          $('#myModal').modal('hide');
     		         callDatableWithChangedDropDown();
     		      },
     		      error: function (jqXHR, textStatus, errorThrown)
     		      {
     		    	  alert("Error");
     		      }
     		  });
     		  event.preventDefault();
     		});	
 } );

 function mbrHedisFollowup(id,lastName,firstName) 
{
	   if ( $.fn.DataTable.isDataTable('#mbrHedisMeasureTable') ) {
				$('#mbrHedisMeasureTable').DataTable().destroy();
		}
		$('#mbrHedisMeasureTable tbody').empty();
		
		
	 var datatable2MbrHedisMeasure = function(sSource, aoData, fnCallback) {
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
		  
		  var ruleArray1 = new Array;
		   $("#hedisRule option:selected").each  ( function() {
		    	ruleArray1.push ( $(this).val() );
		    });
		   restParams.push({"name" : "hedisRuleIds", "value" :  ruleArray1 });
		
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
 	
 	$('#mbrHedisMeasureTable').dataTable({
 	     "sAjaxSource" : getContextPath()+'membership/'+id+'/hedisMeasureList',
 	     "sAjaxDataProp" : 'data',
 	     "aoColumns": [
                        { "mDataProp": "id", "bSearchable" : false, "bVisible" : true ,"bSortable": false },
                        { "mDataProp": "hedisMeasureRule.shortDescription","bSearchable" : true, "bSortable" : true,"sWidth" : "45%",
                        	"render": function (data, type, full, meta) {
                        		return '<span data-toggle="tooltip" title="'+full.hedisMeasureRule.description+'">'+data+'</span>';
                        	}
                        },
                        { "mDataProp": "dos","bSearchable" : true, "bSortable" : true,"sWidth" : "45%"}
                       
                    ],
           "aoColumnDefs": [ 
								{ "sName": "id", "aTargets": [ 0 ],
									   "render": function ( data, type, full, meta ) {
								       return '<input type="checkbox" class="chkRule" name="rule_group[]"   id="'+data+'" value="'+data+'"/>';
								}},
								{ "sName": "hedisMeasureRule.shortDescription", "aTargets": [1]},
								{ "sName": "dos", "aTargets": [ 2 ],
									"render": function ( data, type, full, meta ) {
									       return '<input type="text" class="'+full.id+'" name="dos[]" readonly /><span class="clrRed" name ="dosError[]"></span>';
								}}
                             
           ],          
 	     "bLengthChange": false,
 	     "paging": false,
 	     "info": false,
 	     "bFilter": false,
 	     "bProcessing": true,
 	     "bServerSide" : true,
 	     "fnServerData" : datatable2MbrHedisMeasure
 	});
 	
			$(".clrRed").html("");
			
			
		//	$( "#modal-body" ).html('');
			
			$(".modal-title").html(lastName+","+firstName+" - Hedis Followup");
			$( "#modal-body .notes" ).remove();
			$( "#modal-body .history" ).remove();
   			$( "#modal-body #mbr_id" ).remove();
 			$( "#modal-body" ).append('<div class="notes"> <br /> Notes <textarea  id="followup_details"  class="form-control" rows="5" ></textarea></div>');
 			$( "#modal-body" ).append('<div class="history"><br /> History<textarea  id="followup_history" readonly class="form-control" rows="5" ></textarea></div>');
 			$( "#modal-body" ).append('<input type="hidden"  value="'+id+'" id="mbr_id"  class="form-control" />');
 			
		  var  mbr_id =id;
		  var followup_text = $("#followup_history");
		  
		  var source = getContextPath()+'reports/membershipHedis/'+id+'/followupDetails';
		  
		  $.ajax({
			  dataType: 'json',
           contentType: "application/json;charset=UTF-8",
		      url : source,
		       success: function(data, textStatus, jqXHR)
		      {
		          $.each(data.data, function(key, val)
		          {
				      followup_text.append(" >>>> "+val.createdDate+ " >>>>  " +val.createdBy+ " >>>> ");			      
				      followup_text.append(" \n");				      
				      followup_text.append(val.followupDetails);
				      followup_text.append("  \n");
				      followup_text.append(" \n");
		         })
				     
		          $('#myModal').modal('show');
		      },
		      error: function (jqXHR, textStatus, errorThrown)
		      {
		    	  alert("Error");
		      }
		  });
 			
 			return false;
	}
    </script>

<div class="panel-group membershipHedisReport">
	<div class="panel panel-success">
		<div class="panel-heading">
			Hedis Report <span class="clrRed"> </span>
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
						<label class="control-label col-sm-5">Hedis Measures</label>
						<div class="col-sm-7" id="extFilterHedisRule"></div>
					</div>
					<div class="col-sm-3">
						<button type="button" id="hedisGenerate"
							class="btn btn-success btn-sm btn-xs">Generate</button>
					</div>
				</div>
				<table id="membershipTable"
					class="table table-striped table-hover table-responsive">

					<thead>
						<tr>
							<th scope="col" role="row">Notes</th>
							<th scope="col" role="row">Provider</th>
							<th scope="col" role="row">Last Name</th>
							<th scope="col" role="row">First Name</th>
							<th scope="col" role="row">Birthday</th>
							<th scope="col" role="row">Age</th>
							<th scope="col" role="row">Sex</th>
							<th scope="col" role="row">Medicaid No</th>
							<th scope="col" role="row">Medicare No</th>
							<th scope="col" role="row">Sub ID</th>
							<th scope="col" role="row">Land Phone</th>
							<th scope="col" role="row">Mobile Phone</th>
							<th scope="col" role="row">Due Date</th>

						</tr>
					</thead>

					<tbody>

					</tbody>

				</table>
			</div>

		</div>

	</div>
</div>
<link rel="stylesheet" href="${context}/resources/css/bootstrap-multiselect.css" type="text/css">
<script type="text/javascript" src="${context}/resources/js/bootstrap-multiselect.js"></script>
<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog">
	<div class="modal-dialog modal-lg">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Membership Hedis Followup</h4>
			</div>
			<div class="modal-body" id="modal-body">
				<table id="mbrHedisMeasureTable"
					class="table table-striped table-hover table-responsive">
					<thead>
						<tr>
							<th scope="col">Select</th>
							<th scope="col">Hedis Measure</th>
							<th scope="col">Date of Service</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
			<div class="modal-footer">
				<button type="button" id="followupSubmit" class="btn btn-default">Submit</button>
				<button type="button" id="hedisGenerate" class="btn btn-default"
					data-dismiss="modal">Cancel</button>
			</div>
		</div>

	</div>
</div>
<style>
#mbrHedisMeasureTable {
	width: 100% !important;
}
</style>
<script>
  jQuery( document ).ready(function( $ ) {
	    //set initial state.
	    $('body').on('click',".chkRule", function(){
	    	if($(this).is(':checked'))
	    	{
	    		$("."+this.id).addClass( "datepicker" );
	    		$(".datepicker").datepicker({maxDate:"0"}).datepicker("setDate",new Date());
	    		$(".datepicker" ).show();
	    	}
	    	else
	    	{
	    		$(".datepicker" ).datepicker( "destroy" );
	    		$("."+this.id).removeClass("datepicker" );
	    		$("."+this.id).removeClass("hasDatepicker" );
	    		$("."+this.id).removeAttr('id');
	    		$("."+this.id).val('');	    		
	    	}
	       		
	    });
	    $(document).ready(function() {
	    	
	    	});
	});
  </script>

  

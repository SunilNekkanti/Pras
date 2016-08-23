<%@  page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@  taglib prefix="springForm"
	uri="http://www.springframework.org/tags/form"%>
<c:set var="context"
	value="${pageContext.request.contextPath}/${userpath}" />
	<c:set var="contextHome"
	value="${pageContext.request.contextPath}" />
<script>
	$(document)
			.ready(
					function() {

						$("#claimGenerate").click(function(event) {
							callClaimGenerate();
							var index = 0;
							 $('#membershipClaimTable tr').each(function()
							 {
								$('#mbrClaimField option').each(function() {
									if(!$(this).is(':selected')){
									
										$('table#membershipClaimTable tr th').eq(index).hide();
									}
									else
									{
										$('table#membershipClaimTable tr th').eq(index).show();
									}	
									index++;
								});
								        	
							});
							
						});
						
						
						var $selectIns = $('#extFilterIns');
						$
								.getJSON(
										getContextPath()
												+ '/insurance/list?pageNo=0&pageSize=200',
										function(data) {

											//clear the current content of the select
											var s = $('<select id=\"insu\" style=\"width:150px;\" class=\"btn btn-default\">');
											//iterate over the data and append a select option
											$
													.each(
															data.data.list,
															function(key, val) {
																s
																		.append('<option value="'+val.id+'">'
																				+ val.name
																				+ '</option>');
															});
											s.append('</select>');
											$selectIns.html(s);

										}).success(function() {
									providerDropdown();
								});

						var providerDropdown = function() {
							var insSelectValue = Cookies.get('insu');
							 if(insSelectValue != undefined)
									insSelectValue = insSelectValue;
								else{
									insSelectValue= $("#insu option:selected").val();
									Cookies.set('insu', insSelectValue,{path: cookiePath });
								}
								$('select[id="insu"]').val(insSelectValue);
							var $selectPrvdr = $('#extFilterPrvdr');
							$
									.getJSON(
											getContextPath()
													+ '/insurance/providerlist?insId='
													+ insSelectValue,
											function(data) {

												//clear the current content of the select
												var s = $('<select id=\"prvdr\" style=\"width:150px;\" class=\"btn btn-default\">');
												//iterate over the data and append a select option
												$
														.each(
																data.data.list,
																function(key,
																		val) {
																	s
																			.append('<option value="'+val.id+'">'
																					+ val.name
																					+ '</option>');
																});
												s
														.append('<option value="9999">All</option>');
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

						var columns;

						var callClaimGenerate = function() {

							columns = new Array();
							
							columns.push({
								"mDataProp" : "mbr.lastName",
								"bSearchable" : false,
								"asSorting" : [ "asc" ],
								"sClass" : "center",
								"sWidth" : "3%",
								"render" : function(data, type, full,
										meta) {
									return '<a href="javascript:void(0)" id="'
											+ full.id
											+ '" onclick="mbrClaimIdDetails('
											+ full.id
											+ ',\''
											+ full.mbr.lastName
											+ '\',\''
											+ full.mbr.firstName
											+ '\')"><span class="glyphicon glyphicon-book"></span></a>';
								}
							});
							columns.push({
								"mDataProp" : "mbr.lastName",
								"bSearchable" : true,
								"bSortable" : true,
								"sClass" : "center widthM",
								"sWidth" : "10%",
								"render" : function(data, type, full,
										meta) {
									return '<a href="${context}/membership/'+full.mbr.id+'/complete">'
											+ full.mbr.lastName
											+ ', '
											+ full.mbr.firstName + '</a>';
								}
							});
							columns.push({	"mDataProp" : "claimNumber", "bSearchable" : true, "bSortable" : true, "sClass" : "center", "sWidth" : "10%" });							
							columns.push({  "mDataProp" : "prvdr.name",	"bSearchable" : true,	"bSortable" : true,	"sClass" : "center widthM",	"sWidth" : "10%", "sDefaultContent": ""  });
							columns.push({	"mDataProp" : "claimType", "bSearchable" : true, "bSortable" : true, "sClass" : "center", "sWidth" : "10%", "sDefaultContent": ""   });
							columns.push({	"mDataProp" : "facilityType.description", "bSearchable" : true, "bSortable" : true, "sClass" : "center widthM", "sWidth" : "10%", "sDefaultContent": "" });
							columns.push({	"mDataProp" : "diagnosis",  "bSearchable" : true, "bSortable" : true, "sClass" : "center", "sWidth" : "10%" });
							columns.push({	"mDataProp" : "tin", "bSearchable" : true, "bSortable" : true, "sClass" : "center", "sWidth" : "10%", "sDefaultContent": "" });
							columns.push({	"mDataProp" : "billType.description", "bSearchable" : true, "bSortable" : true, "sClass" : "center", "sWidth" : "10%", "sDefaultContent": "" });
							columns.push({	"mDataProp" : "billTypec", "bSearchable" : true, "bSortable" : true, "sClass" : "center", "sWidth" : "10%", "sDefaultContent": "" });
							columns.push({	"mDataProp" : "frequencyType.description", "bSearchable" : true, "bSortable" : true, "sClass" : "center", "sWidth" : "10%", "sDefaultContent": "" });
							columns.push({	"mDataProp" : "dischargeStatus",  "bSearchable" : true, "bSortable" : true, "sClass" : "center", "sWidth" : "10%", "sDefaultContent": "" });
							columns.push({	"mDataProp" : "MemEnrollId",  "bSearchable" : true, "bSortable" : true, "sClass" : "center", "sWidth" : "10%", "sDefaultContent": "" });
							columns.push({	"mDataProp" : "productLabel","bVisible":false,  "bSearchable" : true, "bSortable" : true, "sClass" : "center", "sWidth" : "10%", "sDefaultContent": "" });
							columns.push({	"mDataProp" : "productLvl1", "bSearchable" : true, "bSortable" : true, "sClass" : "center", "sWidth" : "10%", "sDefaultContent": "" });
							columns.push({	"mDataProp" : "productLvl2", "bSearchable" : true, "bSortable" : true, "sClass" : "center", "sWidth" : "10%", "sDefaultContent": "" });
							columns.push({	"mDataProp" : "productLvl3", "bSearchable" : true, "bSortable" : true, "sClass" : "center", "sWidth" : "10%", "sDefaultContent": "" });
							columns.push({	"mDataProp" : "productLvl4", "bSearchable" : true, "bSortable" : true, "sClass" : "center", "sWidth" : "10%", "sDefaultContent": "" });
							columns.push({	"mDataProp" : "productLvl5", "bSearchable" : true, "bSortable" : true, "sClass" : "center", "sWidth" : "10%", "sDefaultContent": "" });
							columns.push({	"mDataProp" : "productLvl6", "bSearchable" : true, "bSortable" : true, "sClass" : "center", "sWidth" : "10%", "sDefaultContent": "" });
							columns.push({	"mDataProp" : "productLvl7", "bSearchable" : true, "bSortable" : true, "sClass" : "center", "sWidth" : "10%", "sDefaultContent": "" });
							columns.push({	"mDataProp" : "marketLvl1", "bSearchable" : true, "bSortable" : true, "sClass" : "center", "sWidth" : "10%", "sDefaultContent": "" });
							columns.push({	"mDataProp" : "marketLvl2", "bSearchable" : true, "bSortable" : true, "sClass" : "center", "sWidth" : "10%", "sDefaultContent": ""});
							columns.push({	"mDataProp" : "marketLvl3", "bSearchable" : true, "bSortable" : true, "sClass" : "center", "sWidth" : "10%", "sDefaultContent": "" });
							columns.push({	"mDataProp" : "marketLvl4", "bSearchable" : true, "bSortable" : true, "sClass" : "center", "sWidth" : "10%", "sDefaultContent": "" });
							columns.push({	"mDataProp" : "marketLvl5", "bSearchable" : true, "bSortable" : true, "sClass" : "center", "sWidth" : "10%", "sDefaultContent": "" });
							columns.push({	"mDataProp" : "marketLvl6", "bSearchable" : true, "bSortable" : true, "sClass" : "center", "sWidth" : "10%", "sDefaultContent": "" });
							columns.push({	"mDataProp" : "marketLvl7", "bSearchable" : true, "bSortable" : true, "sClass" : "center", "sWidth" : "10%", "sDefaultContent": "" });
							columns.push({	"mDataProp" : "marketLvl8", "bSearchable" : true, "bSortable" : true, "sClass" : "center", "sWidth" : "10%" , "sDefaultContent": ""});
							columns.push({	"mDataProp" : "dxTypeCode", "bSearchable" : true, "bSortable" : true, "sClass" : "center", "sWidth" : "10%", "sDefaultContent": "" });
							columns.push({	"mDataProp" : "procTypeCode", "bSearchable" : true, "bSortable" : true, "sClass" : "center", "sWidth" : "10%", "sDefaultContent": "" });
							
							
							

							var myTable = $("#membershipClaimTable");
							var thead = myTable.find("thead");
							$("#membershipClaimTable th").each(
									function() {
										if ($(this).attr("role") == "row") {
										} else {
											$(this).remove();
										}

									});

							callDatableWithChangedDropDown();
						}

						function callDatableWithChangedDropDown() {
							var processClaimValue = $(
									"input[name=processClaim]:checked").val();
							var insSelectValue = $("#insu option:selected")
									.val();
							var prvdrSelectValue = $("#prvdr option:selected")
									.val();
							var processFromValue = $("#processfrom").val();
							var processToValue = $("#processto").val();
							var fileTypeSelectValue = $("#fileType option:selected")  
							if ($.fn.DataTable
									.isDataTable('#membershipClaimTable')) {
								$('#membershipClaimTable')
										.DataTable().destroy();
							}
							$('#membershipClaimTable tbody').empty();
							GetMembershipByInsPrvdr(insSelectValue,
									prvdrSelectValue, processFromValue,
									processToValue, processClaimValue, columns);
						}

						$(document.body)
								.on(
										'change',
										"#insu",
										function(e) {
											Cookies.set('insu', $("#insu option:selected").val(),{path: cookiePath });
								    		Cookies.remove('prvdr',{path: cookiePath });
											if ($.fn.DataTable
													.isDataTable('#membershipClaimTable')) {
												$(
														'#membershipClaimTable')
														.DataTable().destroy();
											}
											$(
													'#membershipClaimTable tbody')
													.empty();
											providerDropdown();
										});

						$(document.body)
								.on(
										'change',
										"#prvdr",
										function(e) {
											Cookies.set('prvdr', $("#prvdr option:selected").val(),{path: cookiePath });
											if ($.fn.DataTable
													.isDataTable('#membershipClaimTable')) {
												$(
														'#membershipClaimTable')
														.DataTable().destroy();
											}
											$(
													'#membershipClaimTable tbody')
													.empty();
										});

						$(document.body)
								.on(
										'change',
										"#mbrClaimField",
										function(e) {
											if ($.fn.DataTable
													.isDataTable('#membershipClaimTable')) {
												$(
														'#membershipClaimTable')
														.DataTable().destroy();
											}
											$(
													'#membershipClaimTable tbody')
													.empty();
											dropDownCache('mbrClaimField'); 

										});

						var datatable2RestMembership = function(sSource,
								aoData, fnCallback) {
							//extract name/value pairs into a simpler map for use later
							var paramMap = {};
							for (var i = 0; i < aoData.length; i++) {
								paramMap[aoData[i].name] = aoData[i].value;
							}

							//page calculations
							var pageSize = paramMap.iDisplayLength;
							var start = paramMap.iDisplayStart;
							var pageNum = (start == 0) ? 1
									: (start / pageSize) + 1; // pageNum is 1 based

							// extract sort information
							var sortCol = paramMap.iSortCol_0;
							var sortDir = paramMap.sSortDir_0;
							var sortName = paramMap['mDataProp_' + sortCol];

							var sSearchIns = paramMap.sSearchIns;
							var sSearchPrvdr = paramMap.sSearchPrvdr;
							var processFrom = paramMap.processFrom;
							var processTo = paramMap.processTo;
							var processClaim = paramMap.processClaim;

							//create new json structure for parameters for REST request
							var restParams = new Array();
							restParams.push({
								"name" : "pageSize",
								"value" : pageSize
							});
							restParams.push({
								"name" : "pageNo",
								"value" : pageNum
							});
							restParams.push({
								"name" : "sort",
								"value" : sortName
							});
							restParams.push({
								"name" : "sortdir",
								"value" : sortDir
							});
							restParams.push({
								"name" : "sSearch",
								"value" : paramMap.sSearch
							});
							restParams.push({
								"name" : "sSearchIns",
								"value" : sSearchIns
							});
							restParams.push({
								"name" : "sSearchPrvdr",
								"value" : sSearchPrvdr
							});
							restParams.push({
								"name" : "processFrom",
								"value" : processFrom
							});
							restParams.push({
								"name" : "processTo",
								"value" : processTo
							});
							restParams.push({
								"name" : "processClaim",
								"value" : processClaim
							});
							
							 
							 var  selectedItem = new Array;
							 selectedItem = selectedList('mbrClaimField');
							 Cookies.set('mbrClaimField',selectedItem,{path: cookiePath });
							 dropDownCache('mbrClaimField');

							$
									.ajax({
										dataType : 'json',
										contentType : "application/json;charset=UTF-8",
										type : 'GET',
										url : sSource,
										data : restParams,
										success : function(res) {
											res.iTotalRecords = res.data.totalCount;
											res.iTotalDisplayRecords = res.data.totalCount;
											fnCallback(res);
											var index1 = 0; var row = 0;
											var mbrClaimFieldCheck = [];
											$('#mbrClaimField option').each(function(index, value) {
												if($(this).is(':selected')){
													mbrClaimFieldCheck[row++] = index;
												}
											});
											if($( window ).width() > 900){
												 var width;
												 width = mbrClaimFieldCheck.length * 120;
												 if(width > 1200){
													 width = width + "px";
													 $('#membershipClaimTable').width(width);
												 }	 
											} 
								           $('table#membershipClaimTable tr').each(function(trindex){
								        	  
								        	   $('table#membershipClaimTable tr:eq('+trindex+') td').each(function(tdindex){
								        		   if($.inArray(tdindex, mbrClaimFieldCheck) != -1)
								        			{
								        			  $(this).show();
								        			}   
							        		       else{
							        		    	   $(this).hide();
							        		       }
								        	   });
								           }); 
											
										},
										error : function(e) {
										}
									});
						}

						GetMembershipByInsPrvdr = function(insId, prvdrId,
								processFrom, processTo, processClaim, aoColumns) {

							var oTable = $('#membershipClaimTable')
									.removeAttr("width")
									.dataTable(
											{
												"sDom": 'Bfrtip',
									        	 "buttons": [
									        	             {
									        	                 extend: 'excelHtml5',
									        	                 title: 'Membership Claim Report Export'
									        	             }
									        	             
										                   ],
												"bDestroy" : true,
												"sAjaxSource" : getContextPath()
														+ '/reports/membershipClaim/list',
												"sAjaxDataProp" : 'data.list',
												"aoColumns" : aoColumns,
												"aoColumnDefs" : [],
												"bLengthChange" : false,
												"iDisplayLength" : 12,
												"sPaginationType" : "full_numbers",
												"bProcessing" : true,
												"bServerSide" : true,
												"initComplete": function () {
													
												},
												"fnServerParams" : function(
														aoData) {
													aoData
															.push(
																	{
																		"name" : "sSearchIns",
																		"value" : insId
																	},
																	{
																		"name" : "sSearchPrvdr",
																		"value" : prvdrId
																	},
																	{
																		"name" : "processFrom",
																		"value" : processFrom
																	},
																	{
																		"name" : "processTo",
																		"value" : processTo
																	},
																	{
																		"name" : "processClaim",
																		"value" : processClaim
																	});
												},
												"fnServerData" : datatable2RestMembership
											});

						}
						
						$('select').css({
							'width' : 150
						});

						$("#followupSubmit")
								.click(
										function(event) {

											if ($("#followup_details").val().length <= 5) {
												alert(" Followup Details must be minimum 5 charactes");
												return false;
											}
											var followup_details = $(
													"#followup_details").val();
											var mbr_id = $("#mbr_id").val();

											var restParams1 = "{\"followupDetails\" :\""
													+ followup_details
													+ "\",\"mbr\": {\"id\":"
													+ mbr_id + "}}";
											var source = getContextPath()
													+ '/reports/membershipClaim/followup';

											$
													.ajax({
														dataType : 'json',
														contentType : "application/json;charset=UTF-8",
														url : source,
														type : 'POST',
														data : restParams1,
														success : function(
																data,
																textStatus,
																jqXHR) {

															alert("Followup Success Done");
															$(".clrRed")
																	.text(
																			"Claim Followup Notes recorded successfully");
															$('#myModal')
																	.modal(
																			'hide');
															callDatableWithChangedDropDown();
														},
														error : function(jqXHR,
																textStatus,
																errorThrown) {
															alert("Error");
														}
													});
											event.preventDefault();
										});
					});

	function mbrClaimIdDetails(id, lastName, firstName) {
		if ($.fn.DataTable.isDataTable('#mbrClaimDetailsTable')) {
			$('#mbrClaimDetailsTable').DataTable().destroy();
		}
		$('#mbrClaimDetailsTable tbody').empty();

		var datatable2MbrHosIdDetails = function(sSource, aoData, fnCallback) {

			//extract name/value pairs into a simpler map for use later
			var paramMap = {};
			for (var i = 0; i < aoData.length; i++) {
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
			restParams.push({});

			$.ajax({
				dataType : 'json',
				contentType : "application/json;charset=UTF-8",
				type : 'GET',
				url : sSource,
				data : restParams,
				success : function(res) {
					res.iTotalRecords = res.data.totalCount;
					res.iTotalDisplayRecords = res.data.totalCount;
					fnCallback(res);
				},
				error : function(e) {
				}
			});
		}

		$('#mbrClaimDetailsTable')
				.dataTable(
						{
							"sAjaxSource" : getContextPath()
									+ 'reports/membershipClaimDetails/'
									+ id + '/list',
							"sAjaxDataProp" : 'data.list',
							"aoColumns" : [ 
							                {"mDataProp" : "claimLineseqNbr", "bSearchable" : true,	"bSortable" : true,"sClass" : "center", "sWidth" : "10%", "sDefaultContent" : ""},
							                {"mDataProp" : "clmLineAdjSeqNbr", "bSearchable" : true,	"bSortable" : true,"sClass" : "center", "sWidth" : "10%", "sDefaultContent" : ""},
							                {"mDataProp" : "activityDate", "bSearchable" : true, "bSortable" : true, "sClass" : "center", "sWidth" : "10%" ,
							                	"render": function (data) {
                  		   		        		var date = new Date(data);
                  		   	        			var month = date.getMonth() + 1;
                  		   	       				 return (month > 9 ? month : "0" + month) + "/" + date.getDate() + "/" + date.getFullYear();
                  		   		   	 		}},
											{"mDataProp" : "activityMonth", "bSearchable" : true, "bSortable" : true, "sClass" : "center", "sWidth" : "10%" },
											{"mDataProp" : "claimStartDate", "bSearchable" : true, "bSortable" : true, "sClass" : "center", "sWidth" : "10%",
												"render": function (data) {
	                  		   		        		var date = new Date(data);
	                  		   	        			var month = date.getMonth() + 1;
	                  		   	       				 return (month > 9 ? month : "0" + month) + "/" + date.getDate() + "/" + date.getFullYear();
	                  		   		   	    }},
											{"mDataProp" : "claimEndDate", "bSearchable" : true, "bSortable" : true, "sClass" : "center", "sWidth" : "10%",
	                  		   		   	    	"render": function (data) {
                  		   		        		var date = new Date(data);
                  		   	        			var month = date.getMonth() + 1;
                  		   	       				 return (month > 9 ? month : "0" + month) + "/" + date.getDate() + "/" + date.getFullYear();
                  		   		   	 		}},
											{"mDataProp" : "paidDate", "bSearchable" : true, "bSortable" : true, "sClass" : "center", "sWidth" : "10%", "sDefaultContent": "",
												"render": function (data) {
	                  		   		        		var date = new Date(data);
	                  		   	        			var month = date.getMonth() + 1;
	                  		   	       				 return (month > 9 ? month : "0" + month) + "/" + date.getDate() + "/" + date.getFullYear();
	                  		   		   	    }},
							                {"mDataProp" : "revenueCode", "bSearchable" : true,	"bSortable" : true,"sClass" : "center", "sWidth" : "10%"},
							                {"mDataProp" : "cpt.code", "bSearchable" : true,	"bSortable" : true,"sClass" : "center", "sWidth" : "10%", "sDefaultContent" : ""},
							                {"mDataProp" : "cptCodeModifier1", "bSearchable" : true,	"bSortable" : true,"sClass" : "center", "sWidth" : "10%", "sDefaultContent" : ""},
							                {"mDataProp" : "cptCodeModifier2", "bSearchable" : true,	"bSortable" : true,"sClass" : "center", "sWidth" : "10%", "sDefaultContent" : ""},
							                {"mDataProp" : "claimStatus", "bSearchable" : true,	"bSortable" : true,"sClass" : "center", "sWidth" : "10%", "sDefaultContent" : ""},
							                {"mDataProp" : "roomType.name", "bSearchable" : true, "bSortable" : true, "sClass" : "center", "sWidth" : "10%", "sDefaultContent": "" },
							                {"mDataProp" : "riskReconCosDes", "bSearchable" : true, "bSortable" : true, "sClass" : "center", "sWidth" : "10%", "sDefaultContent": "" },
							                {"mDataProp" : "amountPaid", "bSearchable" : true,	"bSortable" : true,"sClass" : "center", "sWidth" : "10%", "sDefaultContent" : ""},
							                {"mDataProp" : "allowAmt", "bSearchable" : true,	"bSortable" : true,"sClass" : "center", "sWidth" : "10%", "sDefaultContent" : ""},
							                {"mDataProp" : "coInsurance", "bSearchable" : true,	"bSortable" : true,"sClass" : "center", "sWidth" : "10%", "sDefaultContent" : ""},
							                {"mDataProp" : "coPay", "bSearchable" : true,	"bSortable" : true,"sClass" : "center", "sWidth" : "10%", "sDefaultContent" : ""},
							                {"mDataProp" : "deductible", "bSearchable" : true,	"bSortable" : true,"sClass" : "center", "sWidth" : "10%", "sDefaultContent" : ""},
							                {"mDataProp" : "cobPaidAmount", "bSearchable" : true,	"bSortable" : true,"sClass" : "center", "sWidth" : "10%", "sDefaultContent" : ""},
							                {"mDataProp" : "processingStatus", "bSearchable" : true,	"bSortable" : true,"sClass" : "center", "sWidth" : "10%", "sDefaultContent" : ""},
							                {"mDataProp" : "pharmacyName", "bSearchable" : true,	"bSortable" : true,"sClass" : "center", "sWidth" : "10%", "sDefaultContent" : ""},
							                {"mDataProp" : "quantity", "bSearchable" : true,	"bSortable" : true,"sClass" : "center", "sWidth" : "10%", "sDefaultContent" : ""},
							                {"mDataProp" : "npos", "bSearchable" : true,	"bSortable" : true,"sClass" : "center", "sWidth" : "10%", "sDefaultContent" : ""},
							                {"mDataProp" : "riskId", "bSearchable" : true,	"bSortable" : true,"sClass" : "center", "sWidth" : "10%", "sDefaultContent" : ""},
							                {"mDataProp" : "runnDate", "bSearchable" : true,	"bSortable" : true,"sClass" : "center", "sWidth" : "10%", "sDefaultContent" : ""},
							                {"mDataProp" : "ndc", "bSearchable" : true,	"bSortable" : true,"sClass" : "center", "sWidth" : "10%", "sDefaultContent" : ""},
											{"mDataProp" : "mony", "bSearchable" : true, "bSortable" : true, "sClass" : "center", "sWidth" : "10%", "sDefaultContent": "" },
											{"mDataProp" : "drugLabelName", "bSearchable" : true, "bSortable" : true, "sClass" : "center", "sWidth" : "10%", "sDefaultContent": "" },
											{"mDataProp" : "drugVersion", "bSearchable" : true, "bSortable" : true, "sClass" : "center", "sWidth" : "10%", "sDefaultContent": "" },
							                {"mDataProp" : "pharmacy", "bSearchable" : true,	"bSortable" : true,"sClass" : "center", "sWidth" : "10%", "sDefaultContent" : ""},
							                {"mDataProp" : "membershipClaims", "bSearchable" : true,	"bSortable" : true,"sClass" : "center", "sWidth" : "10%", "sDefaultContent" : ""},
							                {"mDataProp" : "psychare", "bSearchable" : true,	"bSortable" : true,"sClass" : "center", "sWidth" : "10%", "sDefaultContent" : ""},
							                {"mDataProp" : "simpleCounty", "bSearchable" : true,	"bSortable" : true,"sClass" : "center", "sWidth" : "10%", "sDefaultContent" : ""},
							                {"mDataProp" : "triangles", "bSearchable" : true,	"bSortable" : true,"sClass" : "center", "sWidth" : "10%", "sDefaultContent" : ""},
							                {"mDataProp" : "cover", "bSearchable" : true,	"bSortable" : true,"sClass" : "center", "sWidth" : "10%", "sDefaultContent" : ""}
							],
							"aoColumnDefs" : [],
							"bLengthChange" : false,
							"paging" : false,
							"info" : false,
							"bFilter" : false,
							"bProcessing" : true,
							"bServerSide" : true,
							"fnServerData" : datatable2MbrHosIdDetails
						});

		$(".modal-title").html(
				lastName + "," + firstName + " - Claim Details");

		$('#mbrClaimDetails').modal('show');

		return false;
	}
</script>
<link rel="stylesheet" href="${contextHome}/resources/css/bootstrap-multiselect.css" type="text/css">
<script type="text/javascript" src="${contextHome}/resources/js/bootstrap-multiselect.js"></script>
<div class="panel-group">
	<div class="panel panel-success">
		<div class="panel-heading">
			Membership Claim File Upload <span class="clrRed mbrHosUpload"></span>
		</div>
		<div class="panel-body">
			<span class="updateError"></span>
			<springForm:form method="POST" id="mbrClaim" commandName="mbrClaim"
				action="fileProcessing.do" class="form-inline" role="form"
				enctype="multipart/form-data">
				<div class="col-sm-12">
					<div class="form-group insId">
						<div class="col-sm-5">
							<label for="insurance">Insurance</label>
							 <select id="insId" name="insId" class="btn btn-default">
							 	<option value="">Select One</option>
						     <c:forEach items="${insList}" var="ins">
						        <option value="${ins.id}">${ins.name}</option>
						     </c:forEach>
						    </select>
						</div>
					</div>	
					<div class="form-group fileType">
						<div class="col-sm-5">
							<label for="fileType">File Type</label>
							 <select id="fileType" name="fileType" class="btn btn-default">
							 <option value="">Select One</option>
						     <c:forEach items="${fileTypeList}" var="fileType">
						        <option value="${fileType.code}">${fileType.description}</option>
						     </c:forEach>
						    </select>
						</div>
					</div>	
					<div class="form-group fileUpload">
						<div class="col-sm-6">
							<label for="filesUpload" class="fileName">Membership
							Claim File</label>
							<span class="btn btn-danger btn-file btn-sm">  <input
								type="file" accept=".xls,.xlsx,.csv" class="file"
								name="fileUpload">
							</span>
						</div>
					</div>
					<div class="form-group">	
						<div class="col-sm-1">
						 <button type="button" class="btn btn-success btn-sm "
								id="updateButton" name="update"
								onclick="return fileUploadAndProcessing()">Upload</button>
						</div>
					</div>
				</div>
			</springForm:form>
		</div>
	</div>
</div>

<div class="panel-group membershipClaimReport">
	<div class="panel panel-success">
		<div class="panel-heading">
			Claim Report <span class="clrRed"> </span>
		</div>
		<div class="panel-body">
			<div class="table-responsive">
				<div class="col-sm-12 hosFilter">
					<div class="col-sm-4">
						<label class="control-label col-sm-5">Insurance</label>
						<div class=" col-sm-7" id="extFilterIns"></div>
					</div>
					<div class="col-sm-4">
						<label class="control-label col-sm-5">Provider</label>
						<div class="col-sm-7" id="extFilterPrvdr"></div>
					</div>
					
					<div class="col-sm-4">
						<label class="control-label col-sm-2">Fields</label>
						<div class="col-sm-7" class="mbrClaimField">
								<select id="mbrClaimField" class="btn btn-default" multiple></select>
						</div>
					</div>
					
					<br />
					<br />
					<div class="col-sm-offset-6 col-sm-6">
						<div class="col-sm-12">
							<label class="radio-inline col-sm-4"> <input type="radio"
								name="processClaim" id="processClaim" value="0" />Fi1e Process
								Date
							</label> <span class="col-sm-2"> From </span> <input type="text"
								id="processfrom" class="processfrom col-sm-4">

						</div>
						<div class="col-sm-12">
							<label class="radio-inline col-sm-4"> <input type="radio"
								name="processClaim" value="1" /> Claim Date
							</label> <span class="col-sm-2"> To </span> <input type="text"
								id="processto" class="processto col-sm-4"> <span
								class="col-sm-2"><button type="button" id="claimGenerate"
									class="btn btn-success btn-sm btn-xs">Generate</button></span>
						</div>
					</div>

				</div>


				<table id="membershipClaimTable"
					class="table table-striped table-hover table-responsive">

					<thead>
						<tr>
							<th scope="col" role="row">Details</th>
							<th scope="col" data-value="Member Name" role="row">Member Name</th>
							<th scope="col" role="row">claim Id Number</th>
							<th scope="col" role="row">Provider Name</th>
							<th scope="col" role="row">Claim Type</th>
							<th scope="col" role="row">Facility</th>
							<th scope="col" role="row">Diagnoses</th>
							<th scope="col" role="row">TIN</th>
							<th scope="col" role="row">Bill Type</th>
							<th scope="col" role="row">Bill Type Code</th>
							<th scope="col" role="row">Frequency</th>
							<th scope="col" role="row">Discharge Status</th>
							<th scope="col" role="row">Mem Enroll Id</th>
							<th scope="col" role="row">Product Label</th>
							<th scope="col" role="row">Product Level1</th>
							<th scope="col" role="row">Product Level2</th>
							<th scope="col" role="row">Product Level3</th>
							<th scope="col" role="row">Product Level4</th>
							<th scope="col" role="row">Product Level5</th>
							<th scope="col" role="row">Product Level6</th>
							<th scope="col" role="row">Product Level7</th>
							<th scope="col" role="row">Market Level1</th>
							<th scope="col" role="row">Market Level2</th>
							<th scope="col" role="row">Market Level3</th>
							<th scope="col" role="row">Market Level4</th>
							<th scope="col" role="row">Market Level5</th>
							<th scope="col" role="row">Market Level6</th>
							<th scope="col" role="row">Market Level7</th>
							<th scope="col" role="row">Market Level8</th>
							<th scope="col" role="row">DX Type Cd</th>
							<th scope="col" role="row">Proc Type Cd</th>
							

						</tr>
					</thead>

					<tbody>

					</tbody>

				</table>
			</div>

		</div>

	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="mbrClaimDetails" role="dialog">
	<div class="modal-dialog modal-lg">

		<!-- Modal content-->
		<div class="modal-content table-responsive">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Membership Claim Details</h4>
			</div>
			<div class="modal-body table-responsive" id="modal-body">
				<table id="mbrClaimDetailsTable"
					class="table table-striped table-hover table-responsive">

					<thead>
						<tr>
							<th scope="col" role="row">Claim Line Seq Nbr</th>
							<th scope="col" role="row">Claim Line Adj Seq Nbr</th>
							<th scope="col" role="row">Activity Date</th>
							<th scope="col" role="row">Activity Month</th>
							<th scope="col" role="row">Claim Start Date</th>
							<th scope="col" role="row">Claim End Date</th>
							<th scope="col" role="row">Paid Date</th>
							<th scope="col" role="row">Revenue Code</th>
							<th scope="col" role="row">CPT</th>
							<th scope="col" role="row">CPT Modifier 1</th>
							<th scope="col" role="row">CPT Modifier 2</th>
							<th scope="col" role="row">claimStatus</th>
							<th scope="col" role="row">Location</th>
							<th scope="col" role="row">risk_recon_cos_des</th>
							<th scope="col" role="row">Amount Paid</th>
							<th scope="col" role="row">Allow Amt</th>
							<th scope="col" role="row">Co Insurance</th>
							<th scope="col" role="row">Co Pay</th>
							<th scope="col" role="row">Deductible</th>
							<th scope="col" role="row">COB Paid Amount</th>
							<th scope="col" role="row">Processing Status</th>
							<th scope="col" role="row">Pharmacy Name</th>
							<th scope="col" role="row">Quantity</th>
							<th scope="col" role="row">Npos</th>
							<th scope="col" role="row">Risk Id</th>
							<th scope="col" role="row">Runn Date</th>
							<th scope="col" role="row">Ndc</th>
							<th scope="col" role="row">Mony</th>
							<th scope="col" role="row">Drug Label Name</th>
							<th scope="col" role="row">Drug Version</th>
							<th scope="col" role="row">Pharmacy</th>
							<th scope="col" role="row">Membership Claims</th>
							<th scope="col" role="row">Psychare</th>
							<th scope="col" role="row">Simple County</th>
							<th scope="col" role="row">Triangles</th>
							<th scope="col" role="row">Cover</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
			<div class="modal-footer">
				<button type="button" id="claimGenerate" class="btn btn-default"
					data-dismiss="modal">Cancel</button>
			</div>
		</div>

	</div>
</div>


<style>
#mbrClaimTable {
	width: 100% !important;
}
</style>
<script>
$(document.body).on('change',"#fileType",function (e) {
	$(".fileName").text($("#fileType option:selected").text());
});
		jQuery(document).ready(
				
				function($) {
					$('input:radio[name=processClaim]').filter('[value="0"]')
							.attr('checked', true);

					 $('body').on('focus',".processfrom", function(){
					 	 $(this).datepicker({
						          dateFormat: 'mm/dd/yy',
						          changeMonth: true,
						          changeYear: true,
						          maxDate: new Date(),
						          minDate:'01/01/2015',
						          onClose: function( selectedDate ) {
						              $( ".processto" ).datepicker( "option", "minDate", selectedDate );
						              $(".processto").val( $(".processfrom").val());
						            }
						      });
						   });

				 $('body').on('focus',".processto", function(){
			  		 var date1 = new Date($('.processfrom').val());
					  $(this).datepicker({
				          dateFormat: 'mm/dd/yy',
				          changeMonth: true,
				          changeYear: true,
				          minDate: date1,
				          maxDate: new Date()
				         
				      });

				});

					if (!$(".processfrom").val()) {
						var d = new Date();
						var month = d.getMonth() + 1;
						var day = d.getDate();
						var output = (month < 10 ? '0' : '') + month + '/'
								+ (day < 10 ? '0' : '') + day + '/'
								+ d.getFullYear();
						$(".processfrom").val(output);
						$(".processto").val(output);
					}

					//set initial state.
					$('body').on('click', ".chkRule", function() {
						if ($(this).is(':checked')) {
							$("." + this.id).addClass("datepicker");
							$(".datepicker").datepicker({
								maxDate : "0"
							}).datepicker("setDate", new Date());
							$(".datepicker").show();
						} else {
							$(".datepicker").datepicker("destroy");
							$("." + this.id).removeClass("datepicker");
							$("." + this.id).removeClass("hasDatepicker");
							$("." + this.id).removeAttr('id');
							$("." + this.id).val('');
						}

					});
				});

		function fileUploadAndProcessing() {
			$(".mbrHosUpload").html('');
			if(!$('#insId').val()){
				$(".mbrHosUpload").html(
				" Choose a insurance");
				return false;
			}
			if(!$('#fileType').val()){
				$(".mbrHosUpload").html(
				" Choose a fileType");
				return false;
			}
			var fileName = $('input[type=file]').val();
			if (!fileName) {
				$(".mbrHosUpload").html(
						" Choose a file and click upload button");
			} else {
				var validExtensions = /(\.csv|\.xls|\.xlsx)$/i;
				if (validExtensions.test(fileName)) {
					var url = getContextPath()
							+ 'reports/claim/fileProcessing.do?claimOrHospital=0&fileTypeCode='+$('#fileType').val();
					var selector = 'mbrClaim';
					if (window.FormData !== undefined) // for HTML5 browsers
					{
						var formData = new FormData($('#mbrClaim')[0]);
						formData.append('fileUpload',
								$('input[type=file]')[0].files[0]);
						$
								.ajax({
									url : url,
									type : 'POST',
									mimeType : "multipart/form-data",
									data : formData,
									async : false,
									success : function(data) {
										var msg = $.parseJSON(data);
										if (msg['message'] == 'Membership Claim Followup List') {
											msg['message'] = msg['data']
													+ " Records added successfully";
										}
										$(".mbrHosUpload").html(msg['message']);
									},
									cache : false,
									contentType : false,
									processData : false
								});
					} else {
						alert('fileupload error');
					}
				} else {
					$(".mbrHosUpload").html(
							" Only csv, xls, xlsx files are allowed ");
				}
			}
		}
		$(".fileType").hide();
		$(".fileUpload").hide();
		$( "#insId" ).change(function() {
			    $(".fileUpload").hide();
			  if($( "#insId" ).val())
			  {
				  $(".fileType").show();
				  $("#fileType").val('');
			  }
			  else{
				    $(".fileType").hide();
				    $(".fileUpload").hide();
			   }
			});
		$( "#fileType" ).change(function() {
			  if($( "#fileType" ).val())
			  {
				  $(".fileUpload").show();
			  }
			  else{
				    $(".fileUpload").hide();
			   }
			});
	</script>
	
	<br/>
<div class="container">
  <div class="row">
       <div class="col-lg-12">
     <div class="button-group">
        
  </div>
</div>
  </div>
</div>
<script>
var options = [];
var mbrClaimCol = new Array;
mbrClaimCol = getDropDownCache("mbrClaimField");
$('#membershipClaimTable tr th').each(function(index)
 {
			var mbrclaimcheck ="";
			var mbrclaimselect ="";
			if(index < 8 && mbrClaimCol.length <  1){
				mbrclaimcheck ="selected";
			}
			else if(mbrClaimCol.length > 0 && jQuery.inArray($(this).html(), mbrClaimCol) != -1)
			{
				mbrclaimcheck ="selected";
			}
				$("#mbrClaimField").append('<option value="'+$(this).html()+'"' +mbrclaimcheck+'>' + $(this).html() +'</option>');
});
$('#mbrClaimField').multiselect({numberDisplayed: 0, 
	 buttonWidth: '150px',
	 includeSelectAllOption: true,
	 templates: {
		 ul: '<ul class="multiselect-container dropdown-menu mbrClaimField"></ul>'
	 },
	 });

</script>

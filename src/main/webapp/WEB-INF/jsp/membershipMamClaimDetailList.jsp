<%@  page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@  taglib prefix="springForm"
	uri="http://www.springframework.org/tags/form"%>
<c:set var="context"
	value="${pageContext.request.contextPath}/${userpath}" />
<c:set var="contextHome" value="${pageContext.request.contextPath}" />
<script>
	$(document)
			.ready(
					function() {
						$("#claimGenerate")
								.click(
										function(event) {
											$(".clrRed").text("");
											var i = 0;
											var error = new Array();
											if(!$("#yearPicker").val()){
												error[i++] = "Select Year";
											}	
											if(!$("#monthPicker").val()){
												error[i++] = "Select month";
											}
											if(error.length > 0)
											{
												$(".clrRed").text(error.toString());
												return false;
											}
											
											callClaimGenerate();
											var index = 0;
											$('#membershipClaimTable tr')
													.each(
															function() {
																$(
																		'#mbrClaimField option')
																		.each(
																				function() {
																					if (!$(
																							this)
																							.is(
																									':selected')) {

																						$(
																								'table#membershipClaimTable tr th')
																								.eq(
																										index)
																								.hide();
																					} else {
																						$(
																								'table#membershipClaimTable tr th')
																								.eq(
																										index)
																								.show();
																					}
																					index++;
																				});

															});

										});

						var $selectIns = $('#extFilterIns');
						$
								.getJSON(
										"${context}/"
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
									Cookies.set('insu', insSelectValue, {path: cookiePath});
								}
								$('select[id="insu"]').val(insSelectValue);
							var $selectPrvdr = $('#extFilterPrvdr');
							$
									.getJSON(
											"${context}/"
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

						var callClaimGenerate = function() {
							columns = new Array();
							columns
									.push({
										"mDataProp" : "mbrClaim.mbr.lastName",
										"bSearchable" : true,
										"bSortable" : true,
										"sClass" : "center widthM",
										"sWidth" : "10%",
										"render" : function(data, type, full,
												meta) {
											return '<a href="${context}/membership/'+full.mbrClaim.mbr.id+'/complete">'
													+ full.mbrClaim.mbr.lastName
													+ ', '
													+ full.mbrClaim.mbr.firstName
													+ '</a>';
										}
									});
							columns.push({
								"mDataProp" : "mbrClaim.claimNumber",
								"bSearchable" : true,
								"bSortable" : true,
								"sClass" : "center",
								"sWidth" : "10%"
							});
							columns.push({
								"mDataProp" : "mbrClaim.prvdr.name",
								"bSearchable" : true,
								"bSortable" : true,
								"sClass" : "center widthM",
								"sWidth" : "10%",
								"sDefaultContent" : ""
							});
							columns.push({
								"mDataProp" : "mbrClaim.claimType",
								"bSearchable" : true,
								"bSortable" : true,
								"sClass" : "center",
								"sWidth" : "10%",
								"sDefaultContent" : ""
							});
							columns
									.push({
										"mDataProp" : "mbrClaim.facilityType.description",
										"bSearchable" : true,
										"bSortable" : true,
										"sClass" : "center widthM",
										"sWidth" : "10%",
										"sDefaultContent" : ""
									});
							columns.push({
								"mDataProp" : "mbrClaim.diagnosis",
								"bSearchable" : true,
								"bSortable" : true,
								"sClass" : "center",
								"sWidth" : "10%",
								"render": function  ( data, type, full, meta )  {
                                    var icdCodeList1=[];
                                    for(var i = 0; i<full.mbrClaim.icdCodesList.length; i++)
                                    {
                                     icdCodeList1[i] = '<span data-toggle="tooltip" title="'+full.mbrClaim.icdCodesList[i].description+'">'+full.mbrClaim.icdCodesList[i].code+'</span>';  
                                    }
                                  return icdCodeList1.join(', ');
                              }  
							});
							columns.push({
								"mDataProp" : "mbrClaim.tin",
								"bSearchable" : true,
								"bSortable" : true,
								"sClass" : "center",
								"sWidth" : "10%",
								"sDefaultContent" : ""
							});
							columns.push({
								"mDataProp" : "mbrClaim.billType.description",
								"bSearchable" : true,
								"bSortable" : true,
								"sClass" : "center",
								"sWidth" : "10%",
								"sDefaultContent" : ""
							});
							columns.push({
								"mDataProp" : "mbrClaim.billTypec",
								"bSearchable" : true,
								"bSortable" : true,
								"sClass" : "center",
								"sWidth" : "10%",
								"sDefaultContent" : ""
							});
							columns
									.push({
										"mDataProp" : "mbrClaim.frequencyType.description",
										"bSearchable" : true,
										"bSortable" : true,
										"sClass" : "center",
										"sWidth" : "10%",
										"sDefaultContent" : ""
									});
							columns.push({
								"mDataProp" : "mbrClaim.dischargeStatus",
								"bSearchable" : true,
								"bSortable" : true,
								"sClass" : "center",
								"sWidth" : "10%",
								"sDefaultContent" : ""
							});
							columns.push({
								"mDataProp" : "mbrClaim.MemEnrollId",
								"bSearchable" : true,
								"bSortable" : true,
								"sClass" : "center",
								"sWidth" : "10%",
								"sDefaultContent" : ""
							});
							columns.push({
								"mDataProp" : "claimLineseqNbr",
								"bSearchable" : true,
								"bSortable" : true,
								"sClass" : "center",
								"sWidth" : "10%",
								"sDefaultContent" : ""
							});
							columns.push({
								"mDataProp" : "clmLineAdjSeqNbr",
								"bSearchable" : true,
								"bSortable" : true,
								"sClass" : "center",
								"sWidth" : "10%",
								"sDefaultContent" : ""
							});
							columns.push({
								"mDataProp" : "activityDate",
								"bSearchable" : true,
								"bSortable" : true,
								"sClass" : "center",
								"sWidth" : "10%",
								"render" : function(data) {
									var date = new Date(data);
									var month = date.getMonth() + 1;
									return (month > 9 ? month : "0" + month)
											+ "/" + date.getDate() + "/"
											+ date.getFullYear();
								}
							});
							columns.push({
								"mDataProp" : "activityMonth",
								"bSearchable" : true,
								"bSortable" : true,
								"sClass" : "center",
								"sWidth" : "10%"
							});
							columns.push({
								"mDataProp" : "claimStartDate",
								"bSearchable" : true,
								"bSortable" : true,
								"sClass" : "center",
								"sWidth" : "10%",
								"render" : function(data) {
									var date = new Date(data);
									var month = date.getMonth() + 1;
									return (month > 9 ? month : "0" + month)
											+ "/" + date.getDate() + "/"
											+ date.getFullYear();
								}
							});
							columns.push({
								"mDataProp" : "claimEndDate",
								"bSearchable" : true,
								"bSortable" : true,
								"sClass" : "center",
								"sWidth" : "10%",
								"render" : function(data) {
									var date = new Date(data);
									var month = date.getMonth() + 1;
									return (month > 9 ? month : "0" + month)
											+ "/" + date.getDate() + "/"
											+ date.getFullYear();
								}
							});
							columns.push({
								"mDataProp" : "paidDate",
								"bSearchable" : true,
								"bSortable" : true,
								"sClass" : "center",
								"sWidth" : "10%",
								"sDefaultContent" : "",
								"render" : function(data) {
									var date = new Date(data);
									var month = date.getMonth() + 1;
									return (month > 9 ? month : "0" + month)
											+ "/" + date.getDate() + "/"
											+ date.getFullYear();
								}
							});
							columns.push({
								"mDataProp" : "revenueCode",
								"bSearchable" : true,
								"bSortable" : true,
								"sClass" : "center",
								"sWidth" : "10%"
							});
							columns.push({
								"mDataProp" : "cpt.code",
								"bSearchable" : true,
								"bSortable" : true,
								"sClass" : "center",
								"sWidth" : "10%",
								"sDefaultContent" : "",
								"render" : function(data, type, full,meta) {
									if(full.cpt != null)
										return "<span title='"+full.cpt.shortDescription+"'>"+data+"</span>";
									else
										return "";
								}
							});
							columns.push({
								"mDataProp" : "cptCodeModifier1",
								"bSearchable" : true,
								"bSortable" : true,
								"sClass" : "center",
								"sWidth" : "10%",
								"sDefaultContent" : ""
							});
							columns.push({
								"mDataProp" : "cptCodeModifier2",
								"bSearchable" : true,
								"bSortable" : true,
								"sClass" : "center",
								"sWidth" : "10%",
								"sDefaultContent" : ""
							});
							columns.push({
								"mDataProp" : "claimStatus",
								"bSearchable" : true,
								"bSortable" : true,
								"sClass" : "center",
								"sWidth" : "10%",
								"sDefaultContent" : ""
							});
							columns.push({
								"mDataProp" : "roomType.name",
								"bSearchable" : true,
								"bSortable" : true,
								"sClass" : "center",
								"sWidth" : "10%",
								"sDefaultContent" : ""
							});
							columns.push({
								"mDataProp" : "riskReconCosDes",
								"bSearchable" : true,
								"bSortable" : true,
								"sClass" : "center",
								"sWidth" : "10%",
								"sDefaultContent" : ""
							});
							columns.push({
								"mDataProp" : "amountPaid",
								"bSearchable" : true,
								"bSortable" : true,
								"sClass" : "center",
								"sWidth" : "10%",
								"sDefaultContent" : ""
							});
							columns.push({
								"mDataProp" : "allowAmt",
								"bSearchable" : true,
								"bSortable" : true,
								"sClass" : "center",
								"sWidth" : "10%",
								"sDefaultContent" : ""
							});
							columns.push({
								"mDataProp" : "coInsurance",
								"bSearchable" : true,
								"bSortable" : true,
								"sClass" : "center",
								"sWidth" : "10%",
								"sDefaultContent" : ""
							});
							columns.push({
								"mDataProp" : "coPay",
								"bSearchable" : true,
								"bSortable" : true,
								"sClass" : "center",
								"sWidth" : "10%",
								"sDefaultContent" : ""
							});
							columns.push({
								"mDataProp" : "deductible",
								"bSearchable" : true,
								"bSortable" : true,
								"sClass" : "center",
								"sWidth" : "10%",
								"sDefaultContent" : ""
							});
							columns.push({
								"mDataProp" : "cobPaidAmount",
								"bSearchable" : true,
								"bSortable" : true,
								"sClass" : "center",
								"sWidth" : "10%",
								"sDefaultContent" : ""
							});
							columns.push({
								"mDataProp" : "membershipClaims",
								"bSearchable" : true,
								"bSortable" : true,
								"sClass" : "center",
								"sWidth" : "10%",
								"sDefaultContent" : ""
							});
							columns.push({
								"mDataProp" : "pharmacyName",
								"bSearchable" : true,
								"bSortable" : true,
								"sClass" : "center",
								"sWidth" : "10%",
								"sDefaultContent" : ""
							});
							columns.push({
								"mDataProp" : "runnDate",
								"bSearchable" : true,
								"bSortable" : true,
								"sClass" : "center",
								"sWidth" : "10%",
								"sDefaultContent" : ""
							});
							columns.push({
								"mDataProp" : "ndc",
								"bSearchable" : true,
								"bSortable" : true,
								"sClass" : "center",
								"sWidth" : "10%",
								"sDefaultContent" : ""
							});
							columns.push({
								"mDataProp" : "drugLabelName",
								"bSearchable" : true,
								"bSortable" : true,
								"sClass" : "center",
								"sWidth" : "10%",
								"sDefaultContent" : ""
							});
							columns.push({
								"mDataProp" : "pharmacy",
								"bSearchable" : true,
								"bSortable" : true,
								"sClass" : "center",
								"sWidth" : "10%",
								"sDefaultContent" : ""
							});
							columns.push({
								"mDataProp" : "psychare",
								"bSearchable" : true,
								"bSortable" : true,
								"sClass" : "center",
								"sWidth" : "10%",
								"sDefaultContent" : ""
							});

							var myTable = $("#membershipClaimTable");
							var thead = myTable.find("thead");
							$("#membershipClaimTable th").each(function() {
								if ($(this).attr("role") == "row") {
								} else {
									$(this).remove();
								}

							});

							callDatableWithChangedDropDown();
						}

						function callDatableWithChangedDropDown() {
							var processClaimValue = $("#claim option:selected")
									.val();
							var insSelectValue = $("#insu option:selected")
									.val();
							var prvdrSelectValue = $("#prvdr option:selected")
									.val();
							var monthPickerValue = $("#monthPicker").val();
							var yearPickerValue = $("#yearPicker").val();

							var monthArray = new Array;
							var yearArray = new Array;
							$("#yearPicker option:selected").each(
									function() {
										yearValue = $(this).val();
										yearArray.push(yearValue);
										$("#monthPicker option:selected").each(
												function() {
													monthArray.push(yearValue
															+ ""
															+ $(this).val());
												});
									});

							if ($.fn.DataTable
									.isDataTable('#membershipClaimTable')) {
								$('#membershipClaimTable').DataTable()
										.destroy();
							}
							$('#membershipClaimTable tbody').empty();

							GetMembershipByInsPrvdr(insSelectValue,
									prvdrSelectValue, monthArray, yearArray,
									processClaimValue, columns);
						}

						$(document.body)
								.on(
										'change',
										"#insu",
										function(e) {
											Cookies.set('insu', $("#insu option:selected").val(), {path:cookiePath});
								    		Cookies.remove('prvdr',{path: cookiePath });
											if ($.fn.DataTable
													.isDataTable('#membershipClaimTable')) {
												$('#membershipClaimTable')
														.DataTable().destroy();
											}
											$('#membershipClaimTable tbody')
													.empty();
											providerDropdown();
										});

						$(document.body)
								.on(
										'change',
										"#prvdr",
										function(e) {
											Cookies.set('prvdr', $("#prvdr option:selected").val(), {path:cookiePath});
											if ($.fn.DataTable
													.isDataTable('#membershipClaimTable')) {
												$('#membershipClaimTable')
														.DataTable().destroy();
											}
											$('#membershipClaimTable tbody')
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
							var monthPicker = paramMap.monthPicker;
							var yearPicker = paramMap.yearPicker;
							var processClaim = paramMap.processClaim;
							
							 var  selectedItem = new Array;
							 selectedItem = selectedList('mbrClaimField');
							 Cookies.set('mbrClaimField', selectedItem, {path:cookiePath});
							 dropDownCache('mbrClaimField');
							 
							 selectedItem = selectedList('monthPicker');
							 Cookies.set('monthPicker', selectedItem, {path:cookiePath});
							 dropDownCache('monthPicker');
							 
							 selectedItem = selectedList('yearPicker');
							 Cookies.set('yearPicker', selectedItem, {path:cookiePath});
							 dropDownCache('yearPicker');

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
								"name" : "yearPicker",
								"value" : yearPicker
							});
							restParams.push({
								"name" : "monthPicker",
								"value" : monthPicker
							});
							restParams.push({
								"name" : "processClaim",
								"value" : processClaim
							});
							
							
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
											$(".rowClick tbody tr").css('cursor', 'pointer');
											var index1 = 0;
											var row = 0;
											var mbrClaimFieldCheck = [];
											$('#mbrClaimField option')
													.each(
															function(index,
																	value) {
																if ($(this)
																		.is(
																				':selected')) {
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
											
											$('table#membershipClaimTable tr')
													.each(
															function(trindex) {

																$(
																		'table#membershipClaimTable tr:eq('
																				+ trindex
																				+ ') td')
																		.each(
																				function(
																						tdindex) {
																					if ($
																							.inArray(
																									tdindex,
																									mbrClaimFieldCheck) != -1) {
																						$(
																								this)
																								.show();
																					} else {
																						$(
																								this)
																								.hide();
																					}
																				});
															});
										},
										error : function(e) {
										}
									});
						}

						GetMembershipByInsPrvdr = function(insId, prvdrId,
								monthPicker, yearPicker, processClaim,
								aoColumns) {

							var oTable = $('#membershipClaimTable')
									.removeAttr("width")
									.dataTable(
											{
												"sDom" : 'Bfrtip',
												"buttons" : [ {
													extend : 'excelHtml5',
													title : 'Membership Claim Report Export'
												}

												],
												"bDestroy" : true,
												"sAjaxSource" : "${context}/"
														+ '/reports/membershipMamClaim/list',
												"sAjaxDataProp" : 'data.list',
												"aoColumns" : aoColumns,
												"aoColumnDefs" : [],
												"bLengthChange" : false,
												"iDisplayLength" : 12,
												"sPaginationType" : "full_numbers",
												"bProcessing" : true,
												"bServerSide" : true,
												"initComplete" : function() {

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
																		"name" : "monthPicker",
																		"value" : monthPicker
																	},
																	{
																		"name" : "yearPicker",
																		"value" : yearPicker
																	},
																	{
																		"name" : "processClaim",
																		"value" : processClaim
																	});
												},
												"fnServerData" : datatable2RestMembership
											});
						}
					});
</script>
<link rel="stylesheet"
	href="${contextHome}/resources/css/bootstrap-multiselect.css"
	type="text/css">
<script type="text/javascript"
	src="${contextHome}/resources/js/bootstrap-multiselect.js"></script>


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
					<div class="col-sm-3">
						<label class="control-label col-sm-5">Provider</label>
						<div class="col-sm-7" id="extFilterPrvdr"></div>
					</div>

					<div class="col-sm-4">
						<label class="control-label col-sm-2">Fields</label>
						<div class="col-sm-7" class="mbrClaimField">
							<select id="mbrClaimField" class="btn btn-default" multiple></select>
						</div>
					</div>
					<br /> <br />
					<div class="col-sm-12">
						<div class="col-sm-4">
							<label class="control-label col-sm-5">Year</label>
							<div class="col-sm-7">
								<select name="yearPicker" id="yearPicker"
									class="btn btn-default" multiple></select>
							</div>
						</div>

						<div class="col-sm-3">
							<label class="control-label col-sm-5">Month</label>
							<div class="col-sm-7">
								<select name="monthPicker" id="monthPicker"
									class="btn btn-default" multiple></select>
							</div>
						</div>

						<div class="col-sm-5">
							<div class="col-sm-8">
								<label class="control-label col-sm-4">Claim</label> <select
									name="claim" id="claim" class="btn btn-default">
									<option value="1">Acceptable</option>
									<option value="2">UnAcceptable</option>
									<option value="3">Both</option>
								</select>
							</div>
							<div class="col-sm-4">
								<button type="button" id="claimGenerate"
									class="btn btn-success btn-sm btn-xs">Generate</button>
							</div>
						</div>
					</div>
					<table id="membershipClaimTable"
						class="table table-striped table-hover table-responsive rowClick">

						<thead>
							<tr>
								<th scope="col" data-value="Member Name" role="row">Member
									Name</th>
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
								<th scope="col" role="row">Membership Claims</th>
								<th scope="col" role="row">Pharmacy Name</th>
								<th scope="col" role="row">Runn Date</th>
								<th scope="col" role="row">Ndc</th>
								<th scope="col" role="row">Drug Label Name</th>
								<th scope="col" role="row">Pharmacy</th>
								<th scope="col" role="row">Psychare</th>
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
<div class="container">
	<div class="row">
		<div class="col-lg-12">
			<div class="button-group"></div>
		</div>
	</div>
</div>
<script>
	var options = [];
	var mbrClaimCol = new Array;
	var yearList = new Array;
	var monthList = new Array;
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
	var  yearList = getDropDownCache("yearPicker");
	var yearSelected;
	for (i = new Date().getFullYear(); i > new Date().getFullYear() - 3; i--) {
			yearSelected = "";
		if(jQuery.inArray(i, yearList) != -1){yearSelected = "Selected";}
			$("#yearPicker").append('<option value="'+i+'" '+ yearSelected +' >' +i +'</option>');
	}
	var result = [ "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG",
			"SEP", "OCT", "NOV", "DEC" ];
	var month = "";
	var  monthList = getDropDownCache("monthPicker");
	var  montSelected;
	$.each(result, function(index, value) {
		if (index < 9)
			month = "0" + (index + 1);
		else
			month = index + 1;
		monthSelected = "";
		  if(jQuery.inArray(parseInt(month), monthList) != -1){monthSelected = "Selected";}
		//	$("#monthPicker").append('<option value="'+i+'" '+ monthSelected +' >' +i +'</option>');
			$("#monthPicker").append('<option value="'+month+'" '+ monthSelected +'>' +value +'</option>');
		//$('#monthPicker').append($('<option />').val(month).html(value));
	});
	$('#mbrClaimField').multiselect({
		numberDisplayed : 0,
		 templates: {
			 ul: '<ul class="multiselect-container dropdown-menu mbrClaimField"></ul>'
		 },
		buttonWidth : '150px',
		includeSelectAllOption : true,
		 onChange: function(option, checked) {
			
		 }
	});
	$('#yearPicker').multiselect({
		numberDisplayed : 0,
		buttonWidth : '150px',
		includeSelectAllOption : true,
		 templates: {
			 ul: '<ul class="multiselect-container dropdown-menu yearPicker"></ul>'
		 },
	});
	$('#monthPicker').multiselect({
		numberDisplayed : 0,
		buttonWidth : '150px',
		includeSelectAllOption : true,
		 templates: {
			 ul: '<ul class="multiselect-container dropdown-menu monthPicker"></ul>'
		 },
	});
</script>

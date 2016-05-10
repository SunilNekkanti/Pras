<%@  page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@  taglib prefix="springForm"  uri="http://www.springframework.org/tags/form"%>
<c:set var="context"
	value="${pageContext.request.contextPath}/${userpath}" />
<script>
	$(document)
			.ready(
					function() {

						$("#hospitalizationGenerate").click(function(event) {
							callhospitalizationGenerate();
						});

						var $selectIns = $('#extFilterIns');
						$
								.getJSON(
										getContextPath()
												+ '/insurance/list?pageNo=0&pageSize=200',
										function(data) {

											//clear the current content of the select
											var s = $('<select id=\"insu\" style=\"width:150px;\">');
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
							var insSelectValue = $("#insu option:selected")
									.val();
							var $selectPrvdr = $('#extFilterPrvdr');
							$
									.getJSON(
											getContextPath()
													+ '/insurance/providerlist?insId='
													+ insSelectValue,
											function(data) {

												//clear the current content of the select
												var s = $('<select id=\"prvdr\" style=\"width:150px;\">');
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

									});
						}

						var columns;

						var callhospitalizationGenerate = function() {

							columns = new Array();
							columns
									.push({
										"mDataProp" : "id",
										"bSearchable" : false,
										"asSorting" : [ "asc" ],
										"sClass" : "center",
										"sWidth" : "3%",
										"render" : function(data, type, full,
												meta) {
											return '<a href="javascript:void(0)" id="'
													+ data
													+ '" onclick="myFunction('
													+ data
													+ ',\''
													+ full.lastName
													+ '\',\''
													+ full.firstName
													+ '\')"><span class="glyphicon glyphicon-pencil"></span></a>';
										}
									});
							columns.push({
								"mDataProp" : "mbrProviderList.0.prvdr.name",
								"bSearchable" : true,
								"bSortable" : true,
								"sClass" : "center",
								"sWidth" : "10%"
							});
							columns
									.push({
										"mDataProp" : "firstName",
										"bSearchable" : true,
										"bSortable" : true,
										"sClass" : "center",
										"sWidth" : "10%",
										"render" : function(data, type, full,
												meta) {
											return '<a href="${context}/membership/'+full.id+'/complete">'
													+ full.lastName
													+ ', '
													+ full.firstName + '</a>';
										}
									});
							columns
									.push({
										"mDataProp" : "mbrHospitalizationList.0.hospital.name",
										"bSearchable" : true,
										"bSortable" : true,
										"sClass" : "center",
										"sWidth" : "10%"
									});
							columns
									.push({
										"mDataProp" : "mbrHospitalizationList.0.admitDate",
										"bSearchable" : true,
										"bSortable" : true,
										"sClass" : "center",
										"sWidth" : "10%"
									});
							columns
									.push({
										"mDataProp" : "mbrHospitalizationList.0.expDisDate",
										"bSearchable" : true,
										"bSortable" : true,
										"sClass" : "center",
										"sWidth" : "10%"
									});
							columns
									.push({
										"mDataProp" : "mbrHospitalizationList.0.planDesc",
										"bSearchable" : true,
										"bSortable" : true,
										"sClass" : "center",
										"sWidth" : "10%"
									});
							columns
									.push({
										"mDataProp" : "mbrHospitalizationList.0.authNum",
										"bSearchable" : true,
										"bSortable" : true,
										"sClass" : "center",
										"sWidth" : "10%"
									});
							columns
									.push({
										"mDataProp" : "mbrHospitalizationList.0.priorAdmits",
										"bSearchable" : true,
										"bSortable" : true,
										"sClass" : "center",
										"sWidth" : "10%"
									});
							columns
									.push({
										"mDataProp" : "id",
										"bSearchable" : false,
										"asSorting" : [ "asc" ],
										"sClass" : "center",
										"sWidth" : "3%",
										"render" : function(data, type, full,
												meta) {
											return '<a href="javascript:void(0)" id="'
													+ data
													+ '" onclick="mbrHosIdDetails('
													+ full.mbrHospitalizationList[0].id
													+ ',\''
													+ full.lastName
													+ '\',\''
													+ full.firstName
													+ '\')"><span class="glyphicon glyphicon-book"></span></a>';
										}
									});

							var myTable = $("#membershipHospitalizationTable");
							var thead = myTable.find("thead");
							$("#membershipHospitalizationTable th").each(
									function() {
										if ($(this).attr("role") == "row") {
										} else {
											$(this).remove();
										}

									});

							callDatableWithChangedDropDown();
						}

						function callDatableWithChangedDropDown() {
							var insSelectValue = $("#insu option:selected")
									.val();
							var prvdrSelectValue = $("#prvdr option:selected")
									.val();

							if ($.fn.DataTable
									.isDataTable('#membershipHospitalizationTable')) {
								$('#membershipHospitalizationTable')
										.DataTable().destroy();
							}
							$('#membershipHospitalizationTable tbody').empty();
							GetMembershipByInsPrvdr(insSelectValue,
									prvdrSelectValue, columns);
						}

						$(document.body)
								.on(
										'change',
										"#insu",
										function(e) {
											if ($.fn.DataTable
													.isDataTable('#membershipHospitalizationTable')) {
												$(
														'#membershipHospitalizationTable')
														.DataTable().destroy();
											}
											$(
													'#membershipHospitalizationTable tbody')
													.empty();
											providerDropdown();
										});

						$(document.body)
								.on(
										'change',
										"#prvdr",
										function(e) {
											if ($.fn.DataTable
													.isDataTable('#membershipHospitalizationTable')) {
												$(
														'#membershipHospitalizationTable')
														.DataTable().destroy();
											}
											$(
													'#membershipHospitalizationTable tbody')
													.empty();
										});

						$(document.body)
								.on(
										'change',
										"#hedisRule",
										function(e) {
											if ($.fn.DataTable
													.isDataTable('#membershipHospitalizationTable')) {
												$(
														'#membershipHospitalizationTable')
														.DataTable().destroy();
											}
											$(
													'#membershipHospitalizationTable tbody')
													.empty();

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
							var sSearchHedisRule = paramMap.sSearchHedisRule;
							var ruleIds = paramMap.ruleIds;

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
											/*	$('#membershipHospitalizationTable').width(1000); */
										},
										error : function(e) {
										}
									});
						}

						GetMembershipByInsPrvdr = function(insId,
								prvdrId, aoColumns) {

							var oTable = $('#membershipHospitalizationTable')
									.removeAttr("width")
									.dataTable(
											{
												
												
												"bDestroy" : true,
												"sAjaxSource" : getContextPath()
														+ '/reports/membershipHospitalization/list',
												"sAjaxDataProp" : 'data.list',
												"aoColumns" : aoColumns,
												"bLengthChange" : false,
												"iDisplayLength" : 12,
												"sPaginationType" : "full_numbers",
												"bProcessing" : true,
												"bServerSide" : true,
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
																	}

															);
												},
												"fnServerData" : datatable2RestMembership
											});

						}

						$('select').css({
							'width' : 150
						});

						$( "#followupSubmit" ).click(function(event) {
				     		
				     		 
				     		  if($("#followup_details").val().length <= 5)
				     		  {
				     		   		alert(" Followup Details must be minimum 5 charactes");
				     		   		return false;
				     		 }
				     		  var followup_details  = $("#followup_details").val();
				     		  var  mbr_id = $("#mbr_id").val();
				     		  
				     		  var restParams1 ="{\"followupDetails\" :\""+ followup_details+"\",\"mbr\": {\"id\":"+mbr_id+"}}";
				     		  var source = getContextPath()+'/reports/membershipHospitalization/followup';
				     		  
				     		  $.ajax({
				     			  dataType: 'json',
				                   contentType: "application/json;charset=UTF-8",
				     		      url : source,
				     		      type: 'POST',
				     		      data : restParams1,
				     		      success: function(data, textStatus, jqXHR)
				     		      {
				     		    	  
				     		          alert("Followup Success Done");
				     		          $(".clrRed").text("Hospitalization Followup Notes recorded successfully");
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


	function myFunction(id, lastName, firstName) {
		if ($.fn.DataTable.isDataTable('#mbrHospitalizationTable')) {
			$('#mbrHospitalizationTable').DataTable().destroy();
		}
		$('#mbrHospitalizationTable tbody').empty();

		
		$(".clrRed").html("");

		//	$( "#modal-body" ).html('');

		$(".modal-title").html(
				lastName + "," + firstName + " - Hospitalization Followup");
		$("#modal-body .notes").remove();
		$("#modal-body .history").remove();
		$("#modal-body #mbr_id").remove();
		$("#modal-body")
				.append(
						'<div class="notes"> <br /> Notes <textarea  id="followup_details"  class="form-control" rows="5" ></textarea></div>');
		$("#modal-body")
				.append(
						'<div class="history"><br /> History<textarea  id="followup_history" readonly class="form-control" rows="5" ></textarea></div>');
		$("#modal-body")
				.append(
						'<input type="hidden"  value="'+id+'" id="mbr_id"  class="form-control" />');

		var mbr_id = id;
		var followup_text = $("#followup_history");

		var source = getContextPath() + 'reports/membershipHospitalization/' + id
				+ '/followupDetails';

		$.ajax({
			dataType : 'json',
			contentType : "application/json;charset=UTF-8",
			url : source,
			success : function(data, textStatus, jqXHR) {
				$.each(data.data, function(key, val) {
					followup_text.append(" >>>> " + val.dateOfContact + " >>>>  "
							+ val.dateOfContact + " >>>> ");
					followup_text.append(" \n");
					followup_text.append(val.followupDetails);
					followup_text.append("  \n");
					followup_text.append(" \n");
				})

				$('#myModal').modal('show');
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert("Error");
			}
		});

		return false;
	}

	function mbrHosIdDetails(id, lastName, firstName) {
		if ($.fn.DataTable.isDataTable('#mbrHospitalizationDetailsTable')) {
			$('#mbrHospitalizationDetailsTable').DataTable().destroy();
		}
		$('#mbrHospitalizationDetailsTable tbody').empty();

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
			restParams.push({
							});

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

		$('#mbrHospitalizationDetailsTable')
				.dataTable(
						{
							"sAjaxSource" :getContextPath()+'reports/membershipHospitalizationDetails/'+ id + '/list',
							"sAjaxDataProp" : 'data.list',
							"aoColumns" : [ 
 											 { "mDataProp": "attPhysician.name","bSearchable" : true, "bSortable": true,"sClass": "center","sWidth" : "10%"  },
											 { "mDataProp": "roomType.name","bSearchable" : true, "bSortable": true,"sClass": "center","sWidth" : "10%"  },
											 { "mDataProp": "admDx","bSearchable" : true, "bSortable": true,"sClass": "center","sWidth" : "10%"  },
											 { "mDataProp": "authDays","bSearchable" : true, "bSortable": true,"sClass": "center","sWidth" : "10%"  },
											 { "mDataProp": "diseaseCohort","bSearchable" : true, "bSortable": true,"sClass": "center","sWidth" : "10%", "sDefaultContent": ""  },
											 { "mDataProp": "comorbidities","bSearchable" : true, "bSortable": true,"sClass": "center","sWidth" : "10%", "sDefaultContent": ""  },
											 { "mDataProp": "cmPriUser","bSearchable" : true, "bSortable": true,"sClass": "center","sWidth" : "10%", "sDefaultContent": "" },
											 { "mDataProp": "expDisDate","bSearchable" : true, "bSortable": true,"sClass": "center","sWidth" : "10%"  }
							],
							"bLengthChange" : false,
							"paging" : false,
							"info" : false,
							"bFilter" : false,
							"bProcessing" : true,
							"bServerSide" : true,
							"fnServerData" : datatable2MbrHosIdDetails
						});

		$(".modal-title").html(lastName+","+firstName+" - Hospital Details");

		$('#mbrHospitalizationDetails').modal('show');
			

		return false;
	}
</script>

<div class="panel-group">
	<div class="panel panel-success">
		<div class="panel-heading">
			 Membership Hospitalization File Upload 
		</div>
		<div class="panel-body">
			<span class="updateError"></span>
			<springForm:form method="POST" id="mbrHospitalization"
				commandName="mbrHospitalization" action="fileProcessing.do"
				class="form-horizontal" role="form" enctype="multipart/form-data">
				<div class="col-sm-12">
						<div class="form-group">
							<label class="control-label col-sm-2" for="filesUpload">Membership Hospitalization File</label>
							<div class="col-sm-5">
								<span class="btn btn-danger btn-file btn-sm"> Browse <input
									type="file" accept=".xls,.xlsx, .csv" class="file" name="fileUpload">
								</span>
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

<div class="panel-group">
	<div class="panel panel-success">
		<div class="panel-heading">
			Hospitalization Report <span class="clrRed"> </span>
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
						<label class="control-label col-sm-3">Date From</label>
						<div class="col-sm-9">
							<input type="text" class="datepicker1">
						</div>
					</div>

					<div class="col-sm-3">
						<label class="control-label col-sm-3">Date To</label>
						<div class="col-sm-9">
							<input type="text" class="datepicker3">
						</div>
					</div>

					<div class="col-sm-3">
						<button type="button" id="hospitalizationGenerate"
							class="btn btn-success btn-sm btn-xs">Generate</button>
					</div>
				</div>
				<table id="membershipHospitalizationTable"
					class="table table-striped table-hover table-responsive">

					<thead>
						<tr>
							<th scope="col" role="row">Notes</th>
							<th scope="col" role="row">Provider Name</th>
							<th scope="col" role="row">Member Name</th>
							<th scope="col" role="row">Hospital</th>
							<th scope="col" role="row">Admit Date</th>
							<th scope="col" role="row">Exp Discharge Date</th>
							<th scope="col" role="row">Plan</th>
							<th scope="col" role="row">Auth No</th>
							<th scope="col" role="row">Prior Admits</th>
							<th scope="col" role="row">Details</th>

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
<div class="modal fade" id="myModal" role="dialog">
	<div class="modal-dialog modal-lg">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Membership Hospital Followup</h4>
			</div>
			<div class="modal-body" id="modal-body">
				
			</div>
			<div class="modal-footer">
				<button type="button" id="followupSubmit" class="btn btn-default">Submit</button>
				<button type="button" id="hospitalizationGenerate"
					class="btn btn-default" data-dismiss="modal">Cancel</button>
			</div>
		</div>

	</div>
</div>

<div class="modal fade" id="mbrHospitalizationDetails" role="dialog">
	<div class="modal-dialog modal-lg">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Membership Hospitalization Details</h4>
			</div>
			<div class="modal-body" id="modal-body">
				<table id="mbrHospitalizationDetailsTable"
					class="table table-striped table-hover table-responsive">
					
					<thead>
						<tr>
							<th scope="col" role="row">Att Physician</th>
							<th scope="col" role="row">Room Type</th>
						    <th scope="col" role="row">Admit Dx</th>
							<th scope="col" role="row">Auth Days</th>
							<th scope="col" role="row">Disease Cohort</th>
							<th scope="col" role="row">comorbidities</th>
							<th scope="col" role="row">CM Pri User</th>
							<th scope="col" role="row">Exp Discharge Date</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
			<div class="modal-footer">
				<button type="button" id="hospitalizationGenerate"
					class="btn btn-default" data-dismiss="modal">Cancel</button>
			</div>
		</div>

	</div>
</div>


<style>
#mbrHospitalizationTable {
	width: 100% !important;
}
</style>
<script>
	jQuery(document).ready(function($) {
		
		$(".datepicker1, .datepicker3").datepicker({
			maxDate : "0"
		}).datepicker("setDate", new Date());
		
		
		
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
		var url = getContextPath()
		+ 'reports/hospitalization/fileProcessing.do';
		var selector = 'mbrHospitalization';
		if (window.FormData !== undefined) // for HTML5 browsers
		{
			var formData = new FormData($('#mbrHospitalization')[0]);
			formData.append('fileUpload', $('input[type=file]')[0].files[0]);
			$.ajax({
				url : url,
				type : 'POST',
				mimeType : "multipart/form-data",
				data : formData,
				async : false,
				success : function(data) {
					$('#' + selector).html(data);
				},
				cache : false,
				contentType : false,
				processData : false
			});
		} else {
			alert('fileupload error');
		}
	}
</script>

<%@  page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@  taglib prefix="springForm"
	uri="http://www.springframework.org/tags/form"%>
<c:set var="context"
	value="${pageContext.request.contextPath}/${userpath}" />
<c:set var="contextHome" value="${pageContext.request.contextPath}" />
<link rel="stylesheet"
	href="${contextHome}/resources/css/bootstrap-multiselect.css"
	type="text/css">
<script type="text/javascript"
	src="${contextHome}/resources/js/bootstrap-multiselect.js"></script>
<div class="panel-group">
	<div class="panel panel-success">
		<div class="panel-heading">
			File Upload <span class="clrRed mbrFileUpload"></span>
		</div>
		<div class="panel-body">
			<span class="updateError"></span>
			<springForm:form method="POST" id="mbrClaim" commandName="mbrClaim"
				action="fileProcessing.do" class="form-inline" role="form"
				enctype="multipart/form-data">
				<div class="col-sm-12">
					<div class="form-group insId col-sm-2">
						
							<label for="insurance">Insurance</label> 
						<div class="col-sm-12">	
							<select id="insId"
								name="insId" class="btn btn-default">
								<option value="">Select One</option>
								<c:forEach items="${insList}" var="ins">
									<option value="${ins.id}">${ins.name}</option>
								</c:forEach>
							</select>
						</div>	
						
					</div>
					<div class="form-group fileType col-sm-3">
						
							<label for="fileType">File Type</label> 
							
							<div class="col-sm-12" id="fileTypeList">
							
							</div>
							
						
					</div>
					<div class="form-group activityMonth col-sm-2">
							<label for="activityMonth" class="text-center">Activity
								Month</label> 
							<div class="col-sm-12">
								<input type="text" name="activityMonth" id="activityMonth"
								class="activityMonth form-control datepicker input-sm" />
							</div>
					</div>
					<div class="form-group fileUpload col-sm-3">
							<label for="filesUpload" class="fileName">Membership
								Claim File</label> <span class="btn btn-danger btn-file btn-sm">
								<input type="file" accept=".xlsx,.csv" class="file"
								name="fileUpload">
							</span>
					</div>
					<div class="form-group col-sm-1">
							<label for="button" class="visibleH">Button</label>
							<button type="button" class="btn btn-success btn-sm "
								id="updateButton" name="update"
								onclick="return fileUploadAndProcessing()">Upload</button>
					</div>
				</div>
			</springForm:form>
		</div>
	</div>
</div>
<script>
$(document.body).on('change',"#fileType",function (e) {
	$(".fileName").text($("#fileType option:selected").text());
	var activeMonthInd = $("#fileType option:selected").attr('class');
	activeMonthIndType(activeMonthInd);
});

function fileUploadAndProcessing() {
	$(".mbrFileUpload").html('');
	
	if(!$('#insId').val()){
		$(".mbrFileUpload").html(
		 "Choose a insurance");
		return false;
	}
	
	if(!$('#fileType').val()){
				$(".mbrFileUpload").html(
				" Choose a fileType");
				return false;
	}
			
	var fileName = $('input[type=file]').val();
	if (!fileName) {
				$(".mbrFileUpload").html(
						" Choose a file and click upload button");
	} 
	else {
		var validExtensions = /(\.csv|\.xls|\.xlsx)$/i;
		if (validExtensions.test(fileName)) {
				
				var selector = 'mbrClaim';
				if (window.FormData !== undefined) // for HTML5 browsers
				{
					var activeMonthInd = $("#fileType option:selected").attr('class');
					if(activeMonthInd == "Y" && !$('#activityMonth').val())
					{
							$(".mbrFileUpload").html(
							" Choose activity month");
							return false;
					}
					var fileTypeText = $("#fileType option:selected").text();
					var formData = new FormData($('#mbrClaim')[0]);
					var ajaxUrl = getContextPath()
					+ 'reports/claim/fileProcessing.do';
					formData.append('fileUpload',$('input[type=file]')[0].files[0]);
					if(fileTypeText.indexOf("Claim") != -1)
						formData.append('claimOrHospital',0); 
					else if(fileTypeText.indexOf("Hospitalization") != -1)
						formData.append('claimOrHospital',1);  
					$.ajax({
						url : ajaxUrl,
						type : 'POST',
						mimeType : "multipart/form-data",
						data : formData,
						async : false,
						success : function(data) {
							var msg = $.parseJSON(data);
							if (msg['data'] != undefined) {
								msg['message'] = msg['data'];
									//	+ " Records added successfully";
							}
							$(".mbrFileUpload").html(msg['message']);
						},
						cache : false,
						contentType : false,
						processData : false
					});
							
				} else {
						alert('fileupload error');
					}
				} 
				else {
					$(".mbrFileUpload").html(
							" Only csv, xlsx files are allowed ");
				}
			}
		}
		$(".fileType").hide();
		$(".fileUpload").hide();
		$(".activityMonth").hide();
		
		$( "#insId" ).change(function() {
			    $(".fileUpload").hide();
			    $(".activityMonth").hide();
			   var insSelectValue = $( "#insId" ).val()
			  if(insSelectValue)
			  {
				  $(".fileType").show();
				  
					var $selectFileTypeList = $('#fileTypeList');
					$.getJSON(getContextPath()+ '/insurance/fileTypeList?insId='+ insSelectValue,
					function(data) {

										//clear the current content of the select
										//iterate over the data and append a select option
											var s = $('<select id=\"fileType\" name=\"fileType\" style=\"width:150px;\" class=\"btn btn-default\">');
										s.append('<option value="">Select One</option>');
										$
												.each(
														data.data.list,
														function(key,
																val) {
															s
																	.append('<option value="'+val.code+'" class="'+val.activityMonthInd+'">'
																			+ val.description
																			+ '</option>');
														});
										$selectFileTypeList.html(s);
									}).success(function() {
										
							});
				  $("#fileType").val('');
			  }
			  else{
				    $(".fileType").hide();
				    $("#fileType").val('');
				    $(".fileUpload").hide();
			   }
		});
		
				
		$(".datepicker").datepicker({maxDate: '0',dateFormat: 'yymm' });
		
		function activeMonthIndType(activeMonthInd)
		{
			if(activeMonthInd == "Y")
					$(".activityMonth").show();
			else{
					$(".activityMonth").val('');		
					$(".activityMonth").hide();
				}
			$(".fileUpload").show();
		}
</script>
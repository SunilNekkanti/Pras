<%@  page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@  taglib prefix="springForm"
	uri="http://www.springframework.org/tags/form"%>
<c:set var="context"
	value="${pageContext.request.contextPath}/${userpath}" />


<div class="panel-group">
	<div class="panel panel-success">
		<div class="panel-heading">
			Membership Roster File Upload <span class="clrRed mbrRosterUpload"></span>
		</div>
		<div class="panel-body">
			<span class="updateError"></span>
			<springForm:form method="POST" id="mbrClaim" commandName="mbrClaim"
				action="fileProcessing.do" class="form-inline" role="form"
				enctype="multipart/form-data">
				<div class="col-sm-12">
					<div class="form-group insId col-sm-2">
						
							<label for="insurance" class="col-offset-12">Insurance</label>
							 <select id="insId" name="insId" class="form-control">
							 	<option value="">Select One</option>
						     <c:forEach items="${insList}" var="ins">
						        <option value="${ins.id}">${ins.name}</option>
						     </c:forEach>
						    </select>
					</div>	
					<div class="form-group fileType col-sm-3">
						
							<label for="fileType" class="col-offset-12">File Type</label>
							 <select id="fileType" name="fileType" class="form-control">
							 <option value="">Select One</option>
						     <c:forEach items="${fileTypeList}" var="fileType">
						        <option value="${fileType.code}">${fileType.description}</option>
						     </c:forEach>
						    </select>
					</div>	
				    <div class="form-group activityMonth col-sm-3">
						
							<label for="activityMonth" class="col-offset-12 text-center">Activity Month</label>
							<input type="text" id="activityMonth" class="activityMonth form-control datepicker" />
						
					</div>	
					<div class="form-group fileUpload col-sm-3">
						
							<label for="filesUpload" class="col-offset-2">Membership
							Claim File</label>
							<span class="btn btn-danger btn-file btn-sm"> Browse <input
								type="file" accept=".xls,.xlsx,.csv" class="file"
								name="fileUpload">
							</span>
					</div>
				</div>
				<div class="col-sm-12">	
					<div class="form-group col-sm-offset-10">	
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
		
		function fileUploadAndProcessing() {
			$(".mbrRosterUpload").html('');
			if(!$('#insId').val()){
				$(".mbrRosterUpload").html(
				" Choose a insurance");
				return false;
			}
			if(!$('#fileType').val()){
				
				$(".mbrRosterUpload").html(
				" Choose a fileType");
				return false;
			}
			if(!$('.datepicker').val()){
				
				$(".mbrRosterUpload").html(
				" Choose activity month");
				return false;
			}
			var fileName = $('input[type=file]').val();
			if (!fileName) {
				$(".mbrRosterUpload").html(
						" Choose a file and click upload button");
			} else {
				var validExtensions = /(\.csv|\.xls|\.xlsx)$/i;
				if (validExtensions.test(fileName)) {
					var url = getContextPath()
							+ 'membership/membershipRoster/fileProcessing.do?insId='+$('#insId').val()+'&fileTypeId='+$('#fileType').val();
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
										if (msg['message'] == 'Membership Roster ') {
											msg['message'] = msg['data']
													+ " Records added successfully";
										}
										$(".mbrRosterUpload").html(msg['message']);
									},
									cache : false,
									contentType : false,
									processData : false
								});
					} else {
						alert('fileupload error');
					}
				} else {
					$(".mbrRosterUpload").html(
							" Only csv, xls, xlsx files are allowed ");
				}
			}
		}
		$(".fileType").hide();
		$(".fileUpload").hide();
		 $(".activityMonth").hide();
		 $("#updateButton").hide();
		$( "#insId" ).change(function() {
			    $(".fileUpload").hide();
			  if($( "#insId" ).val())
			  {
				  $(".fileType").show();
				  $("#fileType").val('');
			  }
			  else{
				    $(".fileType").hide();
				    $(".activityMonth").hide();
				    $(".fileUpload").hide();
			   }
			});
		$( "#fileType" ).change(function() {
			  if($( "#fileType" ).val())
			  {
				  $(".fileUpload").show();
				  $(".activityMonth").show();
				  $("#updateButton").show();
			  }
			  else{
				    $(".fileUpload").hide();
				    $(".activityMonth").hide();
				    $("#updateButton").show();
			   }
			});
		
		$(".datepicker").datepicker({maxDate: '0',dateFormat: 'yymm' });
	</script>
	<style>
		#fileType{
		width:220px !important;
}
	</style>
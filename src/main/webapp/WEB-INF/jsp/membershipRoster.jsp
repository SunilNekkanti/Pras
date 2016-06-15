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
					<div class="form-group insId">
						<div class="col-sm-5">
							<label for="insurance">Insurance</label>
							 <select id="insId" name="insId">
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
							 <select id="fileType" name="fileType">
							 <option value="">Select One</option>
						     <c:forEach items="${fileTypeList}" var="fileType">
						        <option value="${fileType.code}">${fileType.description}</option>
						     </c:forEach>
						    </select>
						</div>
					</div>	
					<div class="form-group fileUpload">
						<div class="col-sm-5">
							<label for="filesUpload">Membership
							Claim File</label>
							<span class="btn btn-danger btn-file btn-sm"> Browse <input
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
			var fileName = $('input[type=file]').val();
			if (!fileName) {
				$(".mbrRosterUpload").html(
						" Choose a file and click upload button");
			} else {
				var validExtensions = /(\.csv|\.xls|\.xlsx)$/i;
				if (validExtensions.test(fileName)) {
					var url = getContextPath()
							+ 'membership/membershipRoster/fileProcessing.do';
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="springForm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="context"
	value="${pageContext.request.contextPath}/${userpath}" />
<div id="insuranceDetails">
	<div class="panel-group">
		<div class="panel panel-success">
			<div class="panel-heading">
				Insurance Profile <span class="clrRed">${Message}</span> <a
					class="btn btn-danger pull-right btn-xs white-text"
					href="${context}/insuranceList"> <span
					class="glyphicon glyphicon-plus-sign "></span>Insurance List
				</a>
			</div>
			<div class="panel-body">
				<springForm:form method="POST" commandName="insurance"
					action="${context}/insurance/${id}/save.do">
					<div class="form-group required col-sm-3">
						<div class="col-sm-4">
							<label for="name">Name</label>
						</div>
						<div class="col-sm-8">
							<springForm:hidden path="id" />
							<springForm:input path="name" class="form-control" id="name"
								placeholder="name" />
						</div>
						<div class="col-sm-12">
							<springForm:errors path="name" cssClass="error text-danger" />
						</div>


					</div>

					<div class="form-group required col-sm-3">
						<div class="col-sm-3">
							<label for="Plan">Plan</label>
						</div>
						<div class="col-sm-8">
							<springForm:select path="planTypeId" class="form-control"
								id="planTypeId" style="width:150px;">
								<springForm:options items="${planTypeList}" itemValue="id"
									itemLabel="description" />
							</springForm:select>
						</div>
						<div class="col-sm-12">
							<springForm:errors path="planTypeId.code"
								cssClass="error text-danger" />
						</div>

					</div>
					<div class="form-group col-sm-4">
						<c:choose>
							<c:when
								test="${insurance.id != null && insurance.activeInd == 89}">
								<div class="col-sm-3">
									<button type="button" class="btn btn-success btn-sm"
										id="updateButton" name="update"
										onclick="return modifyInsuranceDetails();">Update</button>
								</div>
								<div class="col-sm-3">
									<button type="button" class="btn btn-success btn-sm"
										id="deleteButton" name="delete"
										onclick="return deleteInsuranceDetails();">Delete</button>
								</div>
							</c:when>
							<c:when test="${insurance.id == null}">
								<div class="col-sm-3">
									<button type="submit" class="btn btn-success btn-sm"
										id="updateButton" name="add">Add</button>
								</div>
								<div class="col-sm-3">
									<button type="submit" class="btn btn-success btn-sm"
										id="resetButton">Reset</button>
								</div>
							</c:when>
						</c:choose>
					</div>
				</springForm:form>
			</div>
		</div>
	</div>
</div>
<div id="insuranceContractList"></div>
<div id="insuranceContactList"></div>


<script>
	function insuranceDetails() {
		var source = "${context}/"+ 'insurance/${id}/details';
		$.ajax({
			url : source,
			success : function(data, textStatus, jqXHR) {
				$('#insuranceDetails').html(data);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert("Insurance Details Error");
			}
		});
	}

	function modifyInsuranceDetails() {
		var url = "${context}/"+ 'insurance/${id}/save.do?update';
		var dataList = $("#insurance").serialize();
		$.ajax({
			type : "POST",
			url : url,
			data : dataList,
			success : function(data) {
				$('#insuranceDetails').html(data);
			},
			error : function(data) {
				alert('Insurance Modify Error ' + data);
			}
		});
	}
	function deleteInsuranceDetails() {
		if (confirm("Action cannot be undone.Click 'Ok' to delete.") == true) {
			var url = "${context}/"+ 'insurance/${id}/save.do?delete';
			var dataList = $("#insurance").serialize();
			$.ajax({
				type : "POST",
				url : url,
				data : dataList,
				success : function(data) {
					$('#insuranceDetails').html(data);
				},
				error : function(data) {
					alert('Insurance Delete Error ' + data);
				}
			});
		}
	}

	var source = "${context}/"+ 'insurance/${id}/contractList';
	$.ajax({
		url : source,
		success : function(data, textStatus, jqXHR) {
			$('#insuranceContractList').html(data);
			removeNewcontract();
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert("insurance Contract List Error");
		}
	});

	var source = "${context}/"+ 'insurance/${id}/contactList';
	$.ajax({
		url : source,
		success : function(data, textStatus, jqXHR) {
			$('#insuranceContactList').html(data);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert("insurance Contact List Error");
		}
	});

	function contract(insuranceId, contractId, pmpmRequired) {
		var source = "${context}/"+ 'insurance/' + insuranceId
				+ '/contract/' + contractId;
		$.ajax({
			url : source,
			success : function(data, textStatus, jqXHR) {
				$('#insuranceContractList').html(data);

			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert("insurance Contract Error");
			}
		});
		return false;
	}

	function contact(insuranceId, contactId) {
		var source = "${context}/"+ 'insurance/' + insuranceId
				+ '/contact/' + contactId;
		$.ajax({
			url : source,
			success : function(data, textStatus, jqXHR) {
				$('#insuranceContactList').html(data);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert("insurance Contact Error");
			}
		});
		return false;
	}

	function newContract(pmpmRequired) {
		var source = "${context}/"+ 'insurance/${id}/contract/new';
		$.ajax({
			url : source,
			success : function(data, textStatus, jqXHR) {
				$('#insuranceContractList').html(data);
				$('#contract').on('submit', function(event) {

				});
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert("insurance New Contract Error");
			}
		});
	}

	function newContact() {
		var source = "${context}/"+ 'insurance/${id}/contact/new';
		$.ajax({
			url : source,
			success : function(data, textStatus, jqXHR) {
				$('#insuranceContactList').html(data);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert("insurance New Contact Error");
			}
		});
	}

	function contractList(pmpmRequired) {
		var source = "${context}/"+ 'insurance/${id}/contractList';
		$.ajax({
			url : source,
			success : function(data, textStatus, jqXHR) {
				$('#insuranceContractList').html(data);
				removeNewcontract();
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert("Providr Contract List Error");
			}
		});
		$('#insuranceContactList').show();
	}

	function contactList() {

		var source = "${context}/"+ 'insurance/${id}/contactList';
		$.ajax({
			url : source,
			success : function(data, textStatus, jqXHR) {
				$('#insuranceContactList').html(data);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alert("insurance Contact List Error");
			}
		});
	}

	function addContract(pmpmRequired, avgServiceFundRequired) {
		if(avgServiceFundRequired){
			var avgServiceFund = $("#avgServiceFund").val();
			if(!avgServiceFund){
				$("#insuranceContractList .updateError").append(
						"<div class='row col-sm-12'>Enter Avg Service Fund</div>");
				return false;
			}
		}	
		if (pmpmRequired) {
			var url = "${context}/"+ 'insurance/${id}/contract/save.do?add';
			ajaxCallWithFileUpload(url, pmpmRequired, 'insuranceContractList');
			removeNewcontract();
		}
		return false;
	}

	function modifyContract(pmpmRequired,avgServiceFundRequired) {
		avgServiceFundRequired = avgServiceFundRequired || false;
		pmpmRequired = pmpmRequired || false;
		var mindates = [];
		var maxdates = [];
		var pmpmList = [];

		$("#insuranceContractList .updateError").html('');
		var pmpm = $("#PMPM").val();
		var startDate = $(".datepickerfrom").val();
		var endDate = $(".datepickerto").val();
		if(avgServiceFundRequired){
			var avgServiceFund = $("#avgServiceFund").val();
			if(!avgServiceFund){
				$("#insuranceContractList .updateError").append(
						"<div class='row col-sm-12'>Enter Avg Service Fund</div>");
				return false;
			}
		}
		
		
		var error = 0;
		var source = "${context}/"+ 'insurance/${id}/insuranceProviderContractJsonList';
		$.ajax({
			url : source,
			success : function(data, textStatus, jqXHR) {

				$.each(data.data, function(i, item) {
					mindates.push(new Date(data.data[i].startDate));
					maxdates.push(new Date(data.data[i].endDate));
					pmpmList.push(data.data[i].pmpm);
				});

				var maxDate = new Date(Math.max.apply(null, maxdates));
				var minDate = new Date(Math.min.apply(null, mindates));
				var maxpmpm = Math.max.apply(null, pmpmList);

				if (pmpm < maxpmpm) {
					error = 1;
					$("#insuranceContractList .updateError").append(
							"<div class='row col-sm-12'>PMPM must be greater than $"
									+ maxpmpm + "</div>");
				}
				if (Date.parse(startDate) > Date.parse(minDate)) {
					error = 1;
					var dMin = new Date(minDate);
					dMin = (dMin.getMonth() + 1) + "/" + dMin.getDate() + "/"
							+ dMin.getFullYear();
					$("#insuranceContractList .updateError").append(
							"<div class='row col-sm-12'>Start date must be less than "
									+ dMin + "</div>");
				}

				if (Date.parse(endDate) < Date.parse(maxDate)) {
					error = 1;
					var dMax = new Date(maxDate);
					dMax = (dMax.getMonth() + 1) + "/" + dMax.getDate() + "/"
							+ dMax.getFullYear();
					$("#insuranceContractList .updateError").append(
							"<div class='row col-sm-12'>End date must be greater than "
									+ dMax + "</div>");
				}
				if (error == 1) {
					return false;
				} else {
					if (pmpmRequired) {
						var url = "${context}/"
								+ 'insurance/${id}/contract/save.do?update';
						ajaxCallWithFileUpload(url, pmpmRequired,
								'insuranceContractList');
						removeNewcontract();
					}
					return false;
				}
			}
		});
	}

	function deleteContract(pmpmRequired) {
		if (confirm("Action cannot be undone.Click 'Ok' to delete.") == true) {
			var source = "${context}/"+ 'insurance/${id}/contractJsonList';
			$
					.ajax({
						url : source,
						success : function(data, textStatus, jqXHR) {
							if (data.data.length > 0) {
								
								$("#insuranceContractList .clrRed")
										.append(
												"Cannot delete because of depending Third Party Agreements");
							} else {
								var url = "${context}/"
										+ 'insurance/${id}/contract/save.do?delete';
								var dataList = $("#contract" + pmpmRequired)
										.serialize();
								$.ajax({
									type : "POST",
									url : url,
									data : dataList,
									success : function(data) {
										$('#insuranceContractList').html(data);
										removeNewcontract();
									},
									error : function(data) {
										alert('Insurance Delete Contact Error '
												+ data);
									}
								});
							}
						}
					});

		}
	}

	function addContact() {
		var url = "${context}/"+ 'insurance/${id}/contact/save.do?add';
		var dataList = $("#contact").serialize();
		$.ajax({
			type : "POST",
			url : url,
			data : dataList,
			success : function(data) {
				$('#insuranceContactList').html(data);
			},
			error : function(data) {
				alert('insurance Add Contact Error ' + data);
			}
		});
	}

	function modifyContact() {
		var url = "${context}/"+ 'insurance/${id}/contact/save.do?update';
		var dataList = $("#contact").serialize();

		$.ajax({
			type : "POST",
			url : url,
			data : dataList,
			success : function(data) {
				$('#insuranceContactList').html(data);
			},
			error : function(data) {
				alert('insurance Modify Contact Error ' + data);
			}
		});
	}
	function deleteContact() {
		if (confirm("Action cannot be undone.Click 'Ok' to delete.") == true) {
			var url = "${context}/"
					+ 'insurance/${id}/contact/save.do?delete';
			var dataList = $("#contact").serialize();
			$.ajax({
				type : "POST",
				url : url,
				data : dataList,
				success : function(data) {
					$('#insuranceContactList').html(data);
				},
				error : function(data) {
					alert('Insurance Delete Contact Error ' + data);
				}
			});
		}
	}

	function ajaxCallWithFileUpload(url, pmpmRequired, selector) {
		if (window.FormData !== undefined) // for HTML5 browsers
		{
			var formData = new FormData($('#contract' + pmpmRequired)[0]);
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

	function removeNewcontract() {

		var rowCount = $('#insuranceContractList tr').length;
		if (rowCount > 1) {
			$('#insuranceContractList .panel-heading button').hide();

		}
	}
</script>
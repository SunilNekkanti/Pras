<%@  page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%@  taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html  PUBLIC  "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<!-- meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"-->

<title>Spring3Example</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script
	src="//raw.github.com/botmonster/jquery-bootpag/master/lib/jquery.bootpag.min.js"></script>

<script src="/Pras/resources/js/prasweb.js"></script>


<script>

$(document).ready(function(){	
	
populateMembershipListData();
prasPagination();
openMembershipForm();
updatedMembershipForm();
deleteMembershipForm();
resetMembershipForm('form-ajax');
});
</script>

</head>

<body>

	<%-- <form:form action="membership.html" commandName="membership" > --%>
	<h2>Membership Details</h2>
	<div class="panel-group">
		<div class="panel panel-primary">
			<div class="panel-heading">Membership List</div>
			<div class="panel-body" id="tablediv">
				<div id="formdiv">
					<form id="form-ajax" class="form-horizontal" role="form" method="post" accept-charset="utf-8" action="membership"> 
						<div class="form-group">
							<label class="control-label col-sm-2" for="lastName">Last
								Name:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="lastName"
									name="lastName">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="pwd">First
								Name:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="firstName"
									name="firstName">
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-2" for="gender">Gender:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="genderId"
									placeholder="Gender">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="county">County:</label>
							<div class="col-sm-4">
								<input type="county" class="form-control" id="countyCode"
									placeholder="County">
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-2" for="ethnicity">Etnicity:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="ethinicCode"
									placeholder="Etnicity">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="status">Status:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="status"
									placeholder="Status">
							</div>
						</div>


						<div class="form-group">
							<label class="control-label col-sm-2" for="medicaidNo">Medicaid
								No:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="medicaidNo"
									placeholder="Medicaid No">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="medicareNo">MedicareNo:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="medicareNo"
									placeholder="MedicareNo">
							</div>
						</div>


						<div class="form-group">
							<label class="control-label col-sm-2" for="fileId">File
								Id:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="fileId"
									placeholder="File Id">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="active">Active:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="activeInd"
									placeholder="Active">
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-4">
								<button type="submit" class="btn btn-primary" id="updateButton">Update</button>
								<button type="submit" class="btn btn-primary" id="cancelButton">Cancel</button>
							</div>
						</div>
					</form>
				</div>
				<table id="tab" class="table table-striped table-hover">
					<thead>
						<tr>
							<th scope="col">First Name</th>
							<th scope="col">Last Name</th>
							<th scope="col">Date Of Birth</th>
							<th scope="col">Gender</th>
							<th scope="col">County</th>
							<th scope="col">File Number</th>
							<th scope="col">Status</th>
						</tr>
					</thead>

					<tbody id="content">
						<div id="show_per_page"></div>
						<div id="current_page"></div>
					</tbody>

					<tfoot>
						<div class="col-md-12 text-center" id="page_navigation"></div>
					</tfoot>

				</table>

			</div>
		</div>
	</div>

	<%-- </form:form> --%>

</body>
</html>
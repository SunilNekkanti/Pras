<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<div class="panel-group">
		<div class="panel panel-primary">
			<div class="panel-heading">Membership Updated Successfully</div>
				<div class="row">
					<div class="col-sm-12">
						<div class="well well-sm">
								<p><strong>First Name:${membership.firstName}</strong></p>
								<p><strong>Last Name:${membership.lastName}</strong></p>
								<p><strong>Date of Birth:${membership.dob}</strong></p>
								<p><strong>Membership Status:${membership.status.description}</strong></p>
								<p><strong>Gender:${membership.genderId.description}</strong></p>
								<p><strong>county:${membership.countyCode.description}</strong></p>
								 <p>Updated Successfully </p>
								<a href="http://localhost:8080/Pras/membershipTmpList">Click Here</a> Move to membership list
	 					</div>	
					</div>
				</div>
		</div>
	</div>	

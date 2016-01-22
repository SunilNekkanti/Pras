<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

 <h2> Contact Updated Successfully.</h2>
<div class="panel-group">
	<div class="panel panel-primary">
		<div class="panel-heading">Contact Update</div>
		<div class="row">
			<div class="col-sm-12">
				<div class="well well-sm">
  					<p> <strong>Name:${contact.refContact.mbr.firstName}  ${contact.refContact.mbr.lastName}</strong> </p>
  					<p><strong>Home Phone:${contact.homePhone}</strong>  </p>
  					<p><strong>Mobile Phone:${contact.mobilePhone}</strong>  </p>
  					<p> Updated Successfully  </p>
					<a href="http://localhost:8080/Pras/membership/${id}/contactList">Click Here</a> Move to contact list
				</div>	
			</div>
		</div>
	</div>
</div>	
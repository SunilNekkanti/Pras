<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="springForm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="context"
	value="${pageContext.request.contextPath}/${userpath}" />
<script>
  $(document).ready(function() {
	  	  $('.node').prop('readonly', true);
		  $("#addPrvdrContact").click(function (e)    {
		      		$('#emailTo').val($('input[name=prvdr]:checked').val());
		      		if($('#emailTo').val())
		      			 $('#emailTemplate').prop('readonly', false);
			});
        	
        	var datatable2Rest = function(sSource, aoData, fnCallback) {
        		//extract name/value pairs into a simpler map for use later
  			  var paramMap = {};
  			  for ( var i = 0; i < aoData.length; i++) {
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
  			   restParams.push({"name" : "pageSize", "value" : pageSize});
  			   restParams.push({"name" : "pageNo", "value" : pageNum });
  			   restParams.push({"name" : "sort", "value" : sortName });
  			   restParams.push({"name" : "sortdir", "value" : sortDir });
  			   restParams.push({"name" : "sSearch" , "value" : paramMap.sSearch  });

  			   
  			 $.ajax( {
	                dataType: 'json',
	                contentType: "application/json;charset=UTF-8",
	                type: 'GET',
	                url: sSource,
	                data: restParams,
	                success: function(res) {
	                    res.iTotalRecords = res.data.totalCount;
	                    res.iTotalDisplayRecords = res.data.totalCount;
	               		fnCallback(res);
	               		$("#prvdrContactListTable").css('width','100%');
	                },
	                error : function (e) {
	                }
	            } );
        	}
        	
        	$('#prvdrContactListTable').dataTable({
        	     "sAjaxSource" : getContextPath()+'/prvdrContact/list',
        	     "sAjaxDataProp" : 'data.list',
        	     "aoColumns": [
								{ "mDataProp": "email", "bSearchable" : false, "bVisible" : true, "asSorting" : [ "asc" ]  },
                               { "mDataProp": "refContact.prvdr", "bSearchable" : true, "bVisible" : true, "asSorting" : [ "asc" ]  },
                               { "mDataProp": "contactPerson","bSearchable" : true, "bSortable" : true,"sWidth" : "10%","sDefaultContent" : ""},
                               { "mDataProp": "email","bSearchable" : true, "bSortable": true,"sWidth" : "30%","sDefaultContent" : ""  },
                             
                           ],
                  "aoColumnDefs": [ 
                                   { "sName": "email", "aTargets": [ 0 ],
                                	   "render" : function(data, type, full, meta) {
                                		   if(data)
                                			   return '<input type="radio" name="prvdr"   id="'+data+'" value="'+data+'"/>';
                                		   else
                                			   return '';
										 }   
                                   },
                           		    { "sName": "id", "aTargets": [ 1 ] ,
                             		      "render": function ( data, type, full, meta ) {
                             		    		return data['name'];
                             		      }},
                           		    { "sName": "contactPerson", "aTargets": [ 2 ] },
                           		    { "sName": "email", "aTargets": [ 3 ] },
                           		   
                  ],          
        	     "bLengthChange": false,
        	     "iDisplayLength": 15,
        	     "sPaginationType": "full_numbers",
        	     "bProcessing": true,
        	     "bServerSide" : true,
        	     "fnServerData" : datatable2Rest
        	});

        	
   } );
</script>
<div class="panel-group">
	<div class="panel panel-success">
		<div class="panel-heading">
			Email Template Details <span class="clrRed">${Message}</span> <a
				class="btn btn-danger pull-right btn-xs white-text"
				href="${context}/emailList"> <span
				class="glyphicon glyphicon-plus-sign "></span>Email List
			</a>
		</div>
		<div class="panel-body" id="tablediv">
			<springForm:form method="POST" commandName="email" action="${context}/email/${id}/save.do"
				class="form-horizontal" role="form" enctype="multipart/form-data">

				<div class="form-group required">
					<label class="control-label col-sm-2" for="emailFrom">From</label>
					<div class="col-sm-8">
						<springForm:hidden path="id" />
						<springForm:input path="emailFrom" class="form-control node"
							maxlength="250" id="emailFrom" placeholder="Email From" />
						<springForm:errors path="emailFrom" cssClass="error text-danger" />
					</div>
				</div>

				<div class="form-group required">
					<label class="control-label col-sm-2" for="emailTo">To</label>
					<div class="col-sm-8">
						<springForm:input path="emailTo" class="form-control node"
							maxlength="250" id="emailTo" placeholder="Email To" />
						<springForm:errors path="emailTo" cssClass="error text-danger" />
					</div>
					<div class="col-sm-2">
						<a href="#" data-toggle="modal" data-target="#prvdrEmail"
							class="btn btn-success btn-sm white-text"> <span
							class="glyphicon glyphicon-plus-sign"></span>Contact Mail
						</a>
					</div>
				</div>

				<div class="form-group required">
					<label class="control-label col-sm-2" for="emailCc">CC</label>
					<div class="col-sm-8">
						<springForm:input path="emailCc" class="form-control node"
							maxlength="250" id="emailCc" placeholder="Email CC" />
						<springForm:errors path="emailCc" cssClass="error text-danger" />
					</div>
				</div>

				<div class="form-group required">
					<label class="control-label col-sm-2" for="emailTemplate">Email
						Template (Subject)</label>
					<div class="col-sm-8">
						<springForm:select path="emailTemplate" class="form-control node"
							id="emailTemplate">
							<springForm:option value="${null}" label="Select One" />
							<springForm:options items="${emailTemplateList}" itemValue="id"
								itemLabel="description" />
						</springForm:select>
						<springForm:errors path="emailTemplate"
							cssClass="error text-danger" />
					</div>
				</div>

				<div class="form-group required">
					<label class="control-label col-sm-2" for="body">Content</label>
					<div class="col-sm-8">
						<springForm:textarea path="body" rows="10" class="form-control"
							maxlength="250" id="content" placeholder="Content" />
						<springForm:errors path="body" cssClass="error text-danger" />
					</div>
				</div>

				<div class="form-group">
				       <label class="control-label col-sm-2" for="filesUpload">Attachment</label>
				       <div class="col-sm-8">
				        <span class="btn btn-danger btn-file btn-sm">  <input
				         type="file" class="file" name="fileUpload"  id="fileUpload">
				        </span>
				        
				        
				          <a href="#" onclick="fileDownload('C:\Softwares\test.csv')"><span
				           class="glyphicon glyphicon-paperclip"></span></a>
			       		</div>
			    </div>
      
				<div class="col-sm-12">
					<div class="col-sm-offset-2 col-sm-9">
						<c:choose>
							<c:when test="${email.id != null && email.activeInd == 89}">
								<button type="submit" class="btn btn-success btn-sm"
									id="updateButton" name="update">Update</button>
								<button type="submit" class="btn btn-success btn-sm"
									id="deleteButton" name="delete">Delete</button>
							</c:when>
							<c:otherwise>
								<button type="submit" class="btn btn-success btn-sm"
									id="updateButton" name="add">Add</button>
								<button type="submit" class="btn btn-success btn-sm"
									id="resetButton">Reset</button>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</springForm:form>
		</div>
	</div>
</div>

<div id="prvdrEmail" class="modal fade" role="dialog">
	<div class="modal-dialog modal-lg">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Provider Contact List</h4>
			</div>
			<div class="modal-body">
				<div class="panel-group">
					<div class="panel panel-success">

						<div class="panel-body">
							<div class="table-responsive">
								<table id="prvdrContactListTable"
									class="display table-responsive  table table-striped table-hover">
									<thead>
										<tr>
											<th scope="col">Select</th>
											<th scope="col">Provider</th>
											<th scope="col">Contact Person</th>
											<th scope="col">Email Id</th>
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
			<div class="modal-footer" style="text-align: left;">
				<button type="button" id="addPrvdrContact"
					class="btn btn-success btn-sm" data-dismiss="modal">ADD</button>
			</div>
		</div>
	</div>
</div>


<script>

function readSingleFile(e) {
	  var file = e.target.files[0];
	  if (!file) {
	    return;
	  }
	  var reader = new FileReader();
	  reader.onload = function(e) {
	    var contents = e.target.result;
	    displayContents(contents);
	  };
	  reader.readAsText(file);
	}

	function displayContents(contents) {
		var myWindow = window.open("", "File", "width=200,height=100");
		myWindow.document.write(contents);
		alert('contnet')

	}

	document.getElementById('fileUpload')
	  .addEventListener('change', readSingleFile, false);

function fileDownload(filename) 
{
	
	
	var w = 500;
	var h = 500;
	var left = (screen.width/2)-(w/2);
	var top = (screen.height/2)-(h/2);
	window.open (filename, "title", 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width='+w+', height='+h+', top='+top+', left='+left);
}
$(document).ready(function(){	
	$( "#emailTemplate" ).change(function() {
		var emailTemplateDesc = $( "#emailTemplate" ).val();
		if(emailTemplateDesc)
		{
			var source = getContextPath() + 'emailTemplate/'+emailTemplateDesc+'/details';
			$.ajax({
				url : source,
				success : function(data, textStatus, jqXHR) {
					$('#content').html(data.data);
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert("Email Template Error");
				}
			});
		}
		else{
			$('#content').html('');
		}
		
	});	
});	
</script>

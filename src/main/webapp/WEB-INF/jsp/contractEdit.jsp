<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
    prefix="springForm"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:set var="context" value="${pageContext.request.contextPath}" />
<div class="panel-group">
	<div class="panel panel-success">
		<div class="panel-heading">
			Contract Details
			<button class="btn btn-danger pull-right btn-xs" onclick= "return contractList(${pmpmRequired});">
          		<span class="glyphicon glyphicon-plus-sign "></span> contract List
           </button>
    	</div>
		<div class="panel-body">
			<springForm:form method="POST" id="contract${pmpmRequired}" commandName="contract" action="${id}/contract/save.do" class="form-horizontal" role="form" enctype="multipart/form-data">
				<div class="col-sm-12">
					<div class="col-sm-3">
						<div class="form-group required">
							<label class="control-label col-sm-5" for="contractNBR">Contract NBR</label>
							<div class="col-sm-7">
								<springForm:hidden path="id" />
								<springForm:hidden path="referenceContract.id" />
								
								 <c:choose>
								 	 <c:when test="${contract.referenceContract.prvdr != null}">
										<springForm:hidden path="referenceContract.prvdr.id" />
									</c:when>
									<c:when test="${contract.referenceContract.ins != null}">
										<springForm:hidden path="referenceContract.ins.id" />
									</c:when>
								</c:choose>
								
								<springForm:input path="contractNBR" class="form-control" id="contractNBR" placeholder="Contract NBR" />
								<springForm:errors path="contractNBR" cssClass="error text-danger" />
							</div>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="form-group required">
						 	<label class="control-label col-sm-5" for="startDate">Start Date</label>
							<div class="col-sm-7">
								<fmt:formatDate value="${contract.startDate}" var="dateString" pattern="MM/dd/yyyy" />
								<springForm:input  value="${dateString}" var="startDate" path="startDate"  class="form-control datepicker"  id="startDate" placeholder="Start Date" />
								<springForm:errors path="startDate" cssClass="error text-danger" />
							</div>
						</div>
					</div>
					<div class="col-sm-3">	
						<div class="form-group required">
						 	<label class="control-label col-sm-5" for="endDate">End Date</label>
							<div class="col-sm-7">
								<fmt:formatDate value="${contract.endDate}" var="dateString" pattern="MM/dd/yyyy" />
								<springForm:input value="${dateString}"  path="endDate" class="form-control datepicker" id="endDate" placeholder="End Date" />
								<springForm:errors path="endDate" cssClass="error text-danger" />
							</div>
						</div>
					</div>	
					<div class="col-sm-3">	
						<div class="form-group required">
						 	<label class="control-label col-sm-5" for="filesUpload">Contract file</label>
							<div class="col-sm-7">
	                  			<c:choose>
									<c:when test="${id != null && contract.activeInd == 89}"> 
										<a href="#" onclick="myFunction(${contract.id})"><span class="glyphicon glyphicon-open-file"></span></a>
										<button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#myModal"><span class="glyphicon glyphicon-file"></span> File </button>
									</c:when>
									<c:when test="${contract.id == null}"> 
										<input type="file" name="fileUpload" size="50" />
				                  	</c:when>
								</c:choose>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-12">	
						
				<c:choose>
					<c:when test="${pmpmRequired}">
					<div class="col-sm-3">	
						<div class="form-group required">
							<label class="control-label required col-sm-5" for="PMPM">PMPM</label>
							<div class="col-sm-7">
								<springForm:input path="pmpm" class="form-control" id="PMPM" placeholder="PMPM" />
								<springForm:errors path="pmpm" cssClass="error text-danger" />
							</div>
						</div>
					</div>	
					</c:when>
					</c:choose>

					<c:choose>
					<c:when test="${insuranceRequired}">
						<div class="col-sm-3">
									<div class="form-group required">
									<label class="control-label col-sm-5" for="status">Insurance</label>
									<div class="col-sm-7">
										<springForm:select path="insId" class="form-control" id="status">
											<springForm:option  value="${null}" label="Select One"/>
								    		<springForm:options items="${insuranceList}" itemValue="id" itemLabel="name"   />
										</springForm:select>
										<springForm:errors path="insId" cssClass="error text-danger" />
									  </div>
								</div>
						</div>		
					</c:when>
					</c:choose>	
				</div>
				<div id="filecontent"> </div>
				<div class="col-sm-offset-7 col-sm-5">
					<c:choose>
						<c:when test="${id != null && contract.activeInd == 89}"> 
						 	<button type="button" class="btn btn-success btn-sm" id="updateButton" name="update" onclick="return modifyContract(${pmpmRequired});" >Update</button>
						 	<button type="button" class="btn btn-success btn-sm" id="deleteButton" name="delete" onclick="return deleteContract(${pmpmRequired});">Delete</button>
						</c:when>
						<c:when test="${contract.id == null}">
							<button type="button" class="btn btn-success btn-sm" id="addButton"   name="add" onclick="return addContract(${pmpmRequired});" >Add</button>
							<button type="button" class="btn btn-success btn-sm" id="resetButton" >Reset</button>
						</c:when>
					</c:choose>
				</div>
			</springForm:form>
	 	</div>
	 </div>	
</div>


<script>
function myFunction(id) 
{
	var w = 500;
	var h = 500;
	var left = (screen.width/2)-(w/2);
	var top = (screen.height/2)-(h/2);
	window.open (getContextPath()+'contract/'+id+'/file', "title", 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width='+w+', height='+h+', top='+top+', left='+left);
}
</script>

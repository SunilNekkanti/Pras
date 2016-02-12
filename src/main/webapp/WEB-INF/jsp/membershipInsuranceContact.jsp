<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
    prefix="springForm"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="panel-group">
	<div class="panel panel-primary">
		<div class="panel-heading">Membership Insurance</div>
		<div class="panel-body" id="tablediv">
			<div class="col-sm-12 fdl">
				<springForm:form  commandName="membershipInsurance" action="save.do">
					<div class="panel panel-primary">
						<div class="panel-body" id="tablediv">      	
							<div class="form-group col-sm-12">
								<label class="control-label col-sm-4" for="name">Name</label>
								<div class="col-sm-8">
									<springForm:input path="insId.name" class="form-control" id="name" placeholder="${membershipInsurance.insId.name}" />
									<springForm:errors path="insId.name" cssClass="error text-danger" />
								</div>
							 </div>
						</div>
					</div>		 
					<c:forEach items="${membershipInsurance.insId.refInsContacts}" var="refCnt" varStatus = "status">
						<c:choose>
							<c:when test="${fn:contains(refCnt.cnt.activeInd, 'Y')}">
								<div class="panel panel-primary">
									<div class="panel-body" id="tablediv">   
										<div class="form-group col-sm-12">
											<label class="control-label col-sm-4" for="newBenifits">New Benifits</label>
											<div class="col-sm-8">
												<springForm:input path="newBenifits" class="form-control" id="newBenifits"  />
												<springForm:errors path="newBenifits" cssClass="error text-danger" />
											 </div>
										</div> 
										<div class="form-group col-sm-12">
											<label class="control-label col-sm-4" for="activityDate">Activity Date</label>
											<div class="col-sm-8">
												<springForm:input  path="activityDate" class="form-control" id="activityDate"  />
												<springForm:errors path="activityDate" cssClass="error text-danger" />
											</div>
										</div> 
										<div class="form-group col-sm-12">
											<label class="control-label col-sm-4" for="activityMonth">Activity Month</label>
											<div class="col-sm-8">
												<springForm:input path="activityMonth" class="form-control" id="activityMonth"  />
												<springForm:errors path="activityMonth" cssClass="error text-danger" />
											</div>
										</div> 
										<div class="form-group col-sm-12">
											<label class="control-label col-sm-4" for="effStartDate">Effective Start Date</label>
											<div class="col-sm-8">
											 	<springForm:input path="effStartDate" class="form-control" id="effStartDate"  />
												<springForm:errors path="effStartDate" cssClass="error text-danger" />
											</div>
										</div> 
										<div class="form-group col-sm-12">
											<label class="control-label col-sm-4" for="effEndDate">Effective End Date</label>
											<div class="col-sm-8">
											 	<springForm:input path="effEndDate" class="form-control" id="effEndDate"  />
												<springForm:errors path="effEndDate" cssClass="error text-danger" />
											</div>
										</div> 
										<div class="form-group col-sm-12">
											<label class="control-label col-sm-4" for="effEndDate">Effective End Date</label>
											<div class="col-sm-8">
											 	<springForm:input path="effEndDate" class="form-control" id="effEndDate"  />
												<springForm:errors path="effEndDate" cssClass="error text-danger" />
											</div>
										</div> 
										<div class="form-group col-sm-12">
											<label class="control-label col-sm-4" for="effEndDate">Effective End Date</label>
											<div class="col-sm-8">
											 	<springForm:input path="effEndDate" class="form-control" id="effEndDate"  />
												<springForm:errors path="effEndDate" cssClass="error text-danger" />
											</div>
										</div> 
										<div class="form-group col-sm-12">
											<label class="control-label col-sm-4" for="product">Product</label>
											<div class="col-sm-8">
											 	<springForm:input path="product" class="form-control" id="product"  />
												<springForm:errors path="product" cssClass="error text-danger" />
											</div>
										</div> 
										<div class="form-group col-sm-12">
											<label class="control-label col-sm-4" for="productLabel">Product Label</label>
											<div class="col-sm-8">
											 	<springForm:input path="productLabel" class="form-control" id="productLabel"  />
												<springForm:errors path="productLabel" cssClass="error text-danger" />
											</div>
										</div> 
										<div class="form-group col-sm-12">
											<label class="control-label col-sm-4" for="planId">Plan Id</label>
											<div class="col-sm-8">
											 	<springForm:input path="planId" class="form-control" id="productLabel"  />
												<springForm:errors path="planId" cssClass="error text-danger" />
											</div>
										</div> 
										<div class="form-group col-sm-12">
											<label class="control-label col-sm-4" for="srcSysMbrNbr">SrcSysMbrNbr</label>
											<div class="col-sm-8">
											 	<springForm:input path="srcSysMbrNbr" class="form-control" id="srcSysMbrNbr"  />
												<springForm:errors path="srcSysMbrNbr" cssClass="error text-danger" />
											</div>
										</div> 
										<div class="form-group col-sm-12">
											<label class="control-label col-sm-4" for="riskFlag">Risk Flag</label>
											<div class="col-sm-8">
											 	<springForm:input path="riskFlag" class="form-control" id="riskFlag"  />
												<springForm:errors path="riskFlag" cssClass="error text-danger" />
											</div>
										</div> 
										
									</div>
								</div>
							</c:when>
						</c:choose>
					</c:forEach>
				</springForm:form>
			</div>						
		</div>
	</div>
</div>
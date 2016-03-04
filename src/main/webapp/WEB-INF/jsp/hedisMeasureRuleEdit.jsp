<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
    prefix="springForm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <c:set var="context" value="${pageContext.request.contextPath}" />
<div class="panel with-nav-tabs panel-primary">
		<div class="panel-heading">
						<ul class="nav nav-tabs">
                            <li class="active"><a href="#" data-toggle="tab">Hedis Measure Rule</a></li>
                        </ul>
          </div>
		<div class="panel-body" id="tablediv">
			<springForm:form method="POST" commandName="hedisMeasureRule" action="${context}/hedisMeasureRule/${id}/save.do" class="form-horizontal" role="form">
				<springForm:hidden path="id" />
				<div class="form-group required">
					<label class="control-label col-sm-2" for="hedis">Hedis Code</label>
					<div class="col-sm-6">
						<springForm:select path="hedisMeasure" class="form-control" id="hedisCode" >
				    		<springForm:options items="${hedisMeasureList}" itemValue="id" itemLabel="code"   />
						</springForm:select>
						<springForm:errors path="hedisMeasure.code" cssClass="error text-danger" />
					</div>
				</div>
				
				<div class="form-group required">
					<label class="control-label col-sm-2" for="cpt">CPT Code</label>
					<div class="col-sm-6">
						<springForm:select multiple="true" path="cptCodes" class="form-control"  size="9" items="${cptMeasureList}" itemLabel="code" itemValue="id" />
						<springForm:errors path="cptCodes" cssClass="error text-danger" />
					</div>
				</div>
				 		 
				<div class="form-group required">
					<label class="control-label col-sm-2" for="cpt">ICD Code</label>
					<div class="col-sm-6">
						<springForm:select multiple="true" path="icdCodes" class="form-control"  size="9" items="${icdMeasureList}" itemLabel="code" itemValue="id" />
						<springForm:errors path="icdCodes" cssClass="error text-danger" />
					</div>
				</div>
				
				<div class="form-group required">
					<label class="control-label col-sm-2" for="year">Effective Year (YYYY)</label>
					<div class="col-sm-6">
						<springForm:input path="effectiveYear" class="form-control" id="effectiveYear" placeholder="Effective Year" />
						<springForm:errors path="effectiveYear" cssClass="error text-danger" />
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-2" for="gender">Gender</label>
					<div class="col-sm-6">
						<springForm:select path="genderId"  class="form-control" id="gender">
							<springForm:option  value="${null}" label="Select One" />
					   		<springForm:options items="${genderList}"  itemValue="id" itemLabel="description"    />
						</springForm:select>
						<springForm:errors path="genderId" cssClass="error text-danger" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label   col-sm-2" for="lowerAgeLimit">Lower Age Limit</label>
					<div class="col-sm-6">
						<springForm:input path="lowerAgeLimit" class="form-control" id="lowerAgeLimit" placeholder="lowerAgeLimit" />
						<springForm:errors path="lowerAgeLimit" cssClass="error text-danger" />
					  </div>
				</div>
				
				<div class="form-group">
					<label class="control-label  col-sm-2" for="upperAgeLimit">Upper Age Limit</label>
					<div class="col-sm-6">
						<springForm:input path="upperAgeLimit" class="form-control" id="upperAgeLimit" placeholder="upperAgeLimit" />
						<springForm:errors path="upperAgeLimit" cssClass="error text-danger" />
					  </div>
				</div>
				
				<div class="form-group">
					<label class="control-label  col-sm-2" for="doseCount">Dose Count</label>
					<div class="col-sm-6">
						<springForm:input path="doseCount" class="form-control" id="doseCount" placeholder="doseCount" />
						<springForm:errors path="doseCount" cssClass="error text-danger" />
					  </div>
				</div>
				
				<div class="form-group">
					<label class="control-label  col-sm-2" for="ageEffectiveFrom" >Age Effective From</label>
					<div class="col-sm-6">
						<fmt:formatDate value="${hedisMeasureRule.ageEffectiveFrom}" var="dateString" pattern="MM/dd/yyyy" />
						<springForm:input path="ageEffectiveFrom" value="${dateString}" class="form-control datepicker"  id="ageEffectiveFrom" placeholder="ageEffectiveFrom" />
						<springForm:errors path="ageEffectiveFrom" cssClass="error text-danger" />
					  </div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="ageEffectiveTo ">Age Effective To</label>
					<div class="col-sm-6">
						<fmt:formatDate value="${hedisMeasureRule.ageEffectiveTo}" var="dateString" pattern="MM/dd/yyyy" />
						<springForm:input path="ageEffectiveTo" value="${dateString}" class="form-control datepicker" id="ageEffectiveTo" placeholder="ageEffectiveTo" />
						<springForm:errors path="ageEffectiveTo" cssClass="error text-danger" />
					  </div>
				</div>
				<div class="col-sm-offset-6 col-sm-4">
					<c:choose>
						 <c:when test="${hedisMeasureRule.id != null}"> 
						 	<button type="submit" class="btn btn-primary" id="updateButton" name="update" >Update</button>
						 	<button type="submit" class="btn btn-primary" id="deleteButton" name="delete" >Delete</button>
						 </c:when>
						 <c:otherwise>
							<button type="submit" class="btn btn-primary" id="updateButton" name="add" >Add</button>
							<button type="submit" class="btn btn-primary" id="resetButton" >Reset</button>
						</c:otherwise>
						</c:choose>
					
					
				</div>
			</springForm:form>
 	</div>
</div>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
    prefix="springForm"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="panel with-nav-tabs panel-primary">
		<div class="panel-heading">
						<ul class="nav nav-tabs">
                            <li class="active"><a href="#" data-toggle="tab">Hedis Measure Rule</a></li>
                        </ul>
          </div>
		<div class="panel-body" id="tablediv">
			<springForm:form method="POST" commandName="hedisMeasureRule" action="save.do" class="form-horizontal" role="form">
				<springForm:hidden path="id" />
				<div class="form-group">
					<label class="control-label  required col-sm-2" for="hedis">Hedis Code:</label>
					<div class="col-sm-6">
						<springForm:select path="hedisMeasure" class="form-control" id="hedisCode" >
				    		<springForm:options items="${hedisMeasureList}" itemValue="id" itemLabel="code"   />
						</springForm:select>
						<springForm:errors path="hedisMeasure.code" cssClass="error text-danger" />
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label required col-sm-2" for="cpt">CPT Code:</label>
					<div class="col-sm-6">
						<springForm:select path="cptMeasure" class="form-control" id="cptCode" >
				    		<springForm:options items="${cptMeasureList}"  itemValue="id" itemLabel="code"  />
						</springForm:select>
						<springForm:errors path="cptMeasure.code" cssClass="error text-danger" />
					</div>
				</div>
				 		 
				<div class="form-group">
					<label class="control-label required col-sm-2" for="cpt">ICD Code:</label>
					<div class="col-sm-6">
						<springForm:select path="icdMeasure" class="form-control" id="icdCode" >
				    		<springForm:options items="${icdMeasureList}"  itemValue="id" itemLabel="code"  />
						</springForm:select>
						<springForm:errors path="icdMeasure.code" cssClass="error text-danger" />
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label required col-sm-2" for="year">Effective Year (YYYY):</label>
					<div class="col-sm-6">
						<springForm:input path="effectiveYear" class="form-control" id="effectiveYear" placeholder="Effective Year" />
						<springForm:errors path="effectiveYear" cssClass="error text-danger" />
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

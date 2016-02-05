<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
    prefix="springForm"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div class="panel with-nav-tabs panel-primary">
		<div class="panel-heading">
						<ul class="nav nav-tabs">
                            <li class="active"><a href="#" data-toggle="tab">Hedis Measure</a></li>
                        </ul>
          </div>
		<div class="panel-body" id="tablediv">
			<springForm:form method="POST" commandName="hedisMeasure" action="save.do" class="form-horizontal" role="form">
				<div class="form-group required">
					<label class="control-label col-sm-2" for="code">Code</label>
					<div class="col-sm-8">
						<springForm:hidden path="id" />
						<springForm:input path="code" class="form-control " id="code" placeholder="Code" />
						<springForm:errors path="code" cssClass="error text-danger" />
					</div>
				</div>
				 		 
				<div class="form-group required">
					<label class="control-label col-sm-2" for="description">Description</label>
					<div class="col-sm-8">
						<springForm:input path="description" class="form-control" id="description" placeholder="Description" />
						<springForm:errors path="description" cssClass="error text-danger" />
					</div>
				</div>
				
				<div class="form-group required">
					<label class="control-label col-sm-2" for="gender">Gender</label>
					<div class="col-sm-8">
						<springForm:select path="genderId"  class="form-control" id="gender">
					   		<springForm:options items="${genderList}"  itemValue="id" itemLabel="description"    />
						</springForm:select>
						<springForm:errors path="genderId" cssClass="error text-danger" />
					</div>
				</div>
				
				<div class="form-group required">
					<label class="control-label col-sm-2" for="hedisMsrGrp">Group Id</label>
					<div class="col-sm-8">
						<springForm:select path="hedisMsrGrp" class="form-control" id="hedisMsrGrp">
				    		<springForm:options items="${hedisMeasureGroupList}" itemValue="id" itemLabel="description"     />
						</springForm:select>
						<springForm:errors path="hedisMsrGrp.code" cssClass="error text-danger" />
					  </div>
				</div>
				
				<div class="form-group required">
					<label class="control-label   col-sm-2" for="lowerAgeLimit">Lower Age Limit</label>
					<div class="col-sm-8">
						<springForm:input path="lowerAgeLimit" class="form-control" id="lowerAgeLimit" placeholder="lowerAgeLimit" />
						<springForm:errors path="lowerAgeLimit" cssClass="error text-danger" />
					  </div>
				</div>
				
				<div class="form-group required">
					<label class="control-label  col-sm-2" for="upperAgeLimit">Upper Age Limit</label>
					<div class="col-sm-8">
						<springForm:input path="upperAgeLimit" class="form-control" id="upperAgeLimit" placeholder="upperAgeLimit" />
						<springForm:errors path="upperAgeLimit" cssClass="error text-danger" />
					  </div>
				</div>
				
				<div class="form-group">
					<label class="control-label  col-sm-2" for="ageEffectiveFrom" >Age Effective From</label>
					<div class="col-sm-8">
						<springForm:input path="ageEffectiveFrom" class="form-control" id="ageEffectiveFrom" placeholder="ageEffectiveFrom" />
						<springForm:errors path="ageEffectiveFrom" cssClass="error text-danger" />
					  </div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="ageEffectiveTo ">Age Effective To</label>
					<div class="col-sm-8">
						<springForm:input path="ageEffectiveTo" class="form-control" id="ageEffectiveTo" placeholder="ageEffectiveTo" />
						<springForm:errors path="ageEffectiveTo" cssClass="error text-danger" />
					  </div>
				</div>
			<div class="col-sm-12">	 
				<div class="col-sm-offset-2 col-sm-10">
					<c:choose>
						 <c:when test="${hedisMeasure.id != null}"> 
						 	<button type="submit" class="btn btn-primary" id="updateButton" name="update" >Update</button>
						 	<button type="submit" class="btn btn-primary" id="deleteButton" name="delete" >Delete</button>
						 </c:when>
						 <c:otherwise>
							<button type="submit" class="btn btn-primary" id="updateButton" name="add" >Add</button>
							<button type="submit" class="btn btn-primary" id="resetButton" >Reset</button>
						</c:otherwise>
						</c:choose>
			   </div>
			  </div> 
			</springForm:form>
 	</div>
</div>

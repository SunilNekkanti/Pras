<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<html>
  <head>  
    <title>AngularJS $http Example</title>  
    <style>
    <style>
form.ng-pristine {
    background-color: lightblue;
}
      .username.ng-valid {
          background-color: lightgreen;
      }
      .username.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .username.ng-dirty.ng-invalid-minlength {
          background-color: yellow;
      }
 
      .email.ng-valid {
          background-color: lightgreen;
      }
      .email.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .email.ng-dirty.ng-invalid-email {
          background-color: yellow;
      }
 
    </style>
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
     <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
     <script src="${context}/resources/js/app.js"></script>
     <script src="${context}/resources/js/service/leadMembership_service.js"></script>
     <script src="${context}/resources/js/controller/leadMembership_controller.js"></script>
     <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/themes/smoothness/jquery-ui.min.css" rel="stylesheet" type="text/css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>

  </head>
  <body ng-app="myApp" class="ng-cloak">
      <div class="generic-container" ng-controller="LeadMembershipController as ctrl">
          <div class="panel panel-default">
              <div class="panel-heading"><span class="lead">Lead Membership Details </span></div>
              <div class="formcontainer">
                  <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                      <input type="hidden" ng-model="ctrl.leadMembership.id" />
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-1 control-lable" for="firstname">First Name</label>
                              <div class="col-md-2">
                                  <input type="text" ng-model="ctrl.lead.firstname" id="firstname" class="username form-control input-sm" placeholder="Enter First name" required ng-minlength="5"/>
                                  
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.firstname.$error.required">This is a required field</span>
                                      <span ng-show="myForm.firstname.$error.minlength">Minimum length required is 5</span>
                                      <span ng-show="myForm.firstname.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                             
                              <label class="col-md-1 control-lable" for="lastname">Last Name</label> 
                              <div class="col-md-2">
                                  <input type="text" ng-model="ctrl.lead.lastname" id="lastname" class="username form-control input-sm" placeholder="Enter Last name" required ng-minlength="5"/>
                                  
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.lastname.$error.required">This is a required field</span>
                                      <span ng-show="myForm.lastname.$error.minlength">Minimum length required is 5</span>
                                      <span ng-show="myForm.lastname.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                              
                              <label class="col-md-1 control-lable" for="dob">Date Of Birth</label> 
                              <div class="col-md-2">
                                  <input type="text" ng-model="ctrl.lead.dob" id="dob" class="username form-control input-sm"  datepicker  placeholder="Enter Date Of Birth"  />
                                  
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.dob.$error.required">This is a required field</span>
                                      <span ng-show="myForm.dob.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                              
                              <label class="col-md-1 control-lable" for="dob">Gender</label> 
                              <div class="col-md-2">
                                  <select  ng-model="ctrl.lead.genderId.code" id="gender" class="username form-control input-sm" ng-options="g.description for g in ctrl.genders"   required >
                                  
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.gender.$error.required">This is a required field</span>
                                      <span ng-show="myForm.gender.$error.minlength">Minimum length required is 5</span>
                                      <span ng-show="myForm.gender.$invalid">This field is invalid </span>
                                  </div>
                                  </select>
                              </div>
                              
                          </div>
                      </div>
                         
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-1 control-lable" for="medicaidNo">Medicaid No</label> 
                              <div class="col-md-2">
                                  <input type="text" ng-model="ctrl.lead.medicaidNo" id="medicaidNo" class="username form-control input-sm" placeholder="Enter Medicaid No." required ng-minlength="9"/>
                                  
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.medicaidNo.$error.required">This is a required field</span>
                                      <span ng-show="myForm.medicaidNo.$error.minlength">Minimum length required is 5</span>
                                      <span ng-show="myForm.medicaidNo.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                              
                              <label class="col-md-1 control-lable" for="medicareNo">Medicare No</label> 
                              <div class="col-md-2">
                                  <input type="text" ng-model="ctrl.lead.medicareNo" id="medicareNo" class="username form-control input-sm" placeholder="Enter Medicare No." required ng-minlength="9"/>
                                  
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.medicareNo.$error.required">This is a required field</span>
                                      <span ng-show="myForm.medicareNo.$error.minlength">Minimum length required is 5</span>
                                      <span ng-show="myForm.medicareNo.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                              
                          </div>
                      </div>
 
                     
 
                      <div class="row">
                      {{myForm.$invalid}}
                          <div class="form-actions floatRight">
                              <input type="submit"  value="{{!ctrl.lead.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                              <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
                          </div>
                      </div>
                  </form>
              </div>
          </div>
       <div class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading"><span class="lead">List of Users </span></div>
              <div class="tablecontainer">
                  <table class="table table-hover">
                      <thead>
                          <tr>
                              <th>ID.</th>
                              <th>First Name</th>
                              <th>Last Name</th>
                              <th>Gender</th>
                               <th>Birth Date</th>
                                 <th>medicaid No</th>
                                  <th>medicare</th>
                              <th width="20%"></th>
                          </tr>
                      </thead>
                      <tbody>
                          <tr ng-repeat="l in ctrl.leads">
                              <td><span ng-bind="l.id"></span></td>
                              <td><span ng-bind="l.firstName"></span></td>
                              <td><span ng-bind="l.lastName"></span></td>
                              <td><span ng-bind="l."genderId.code"></span></td>
                              <td><span ng-bind="l.dob"></span></td>
                              <td><span ng-bind="l.medicaidNo"></span></td>
                              <td><span ng-bind="l.medicareNo"></span></td>
                              <td>
                              <button type="button" ng-click="ctrl.edit(u.id)" class="btn btn-success custom-width">Edit</button>  <button type="button" ng-click="ctrl.remove(u.id)" class="btn btn-danger custom-width">Remove</button>
                              </td>
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>
       
      </div>
       
 
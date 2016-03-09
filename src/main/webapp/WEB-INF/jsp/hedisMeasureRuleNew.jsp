<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
    prefix="springForm"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="panel with-nav-tabs panel-primary">
		<div class="panel-heading">
						<ul class="nav nav-tabs">
                            <li class="active"><a href="#" data-toggle="tab">Hedis Measure Rule</a></li>
                        </ul>
          </div>
		<div class="panel-body" id="tablediv">
			<springForm:form method="POST"  id = "hedisMeasureRule" commandName="hedisMeasureRule" action="save.do" class="form-horizontal" role="form">
				<springForm:hidden path="id" />
				<div class="form-group required">
					<label class="control-label col-sm-2" for="hedis">Hedis Code</label>
					<div class="col-sm-4">
						<springForm:select path="hedisMeasure" class="form-control" id="hedisCode" >
				    		<springForm:options items="${hedisMeasureList}" itemValue="id" itemLabel="code"   />
						</springForm:select>
						<springForm:errors path="hedisMeasure.code" cssClass="error text-danger" />
					</div>
				</div>
				
				<div class="form-group required">
					<label class="control-label col-sm-2" for="cpt">CPT Code</label>
					
					<div class="col-sm-4">
					    <a href="JavaScript:void(0);" id="btn-cpt">CPT &raquo;</a>
						<a href="JavaScript:void(0);" id="btn-addCPT">Add &raquo;</a>
						<a href="JavaScript:void(0);" id="btn-removeCPT">&laquo; Remove</a>
							<select  id="cptCodes" name="cptCodes"   multiple size="9"  class="form-control">
							</select>
							<springForm:errors path="cptCodes" cssClass="error text-danger" />
					</div>
					
					<div class="col-sm-4 cptCodes">
						
						
					</div>
					
					
				</div>
				 		 
				<div class="form-group required">
					<label class="control-label col-sm-2" for="ICD">ICD Code</label>
					<div class="col-sm-4">
					    <a href="JavaScript:void(0);" id="btn-icd">ICD &raquo;</a>
						<a href="JavaScript:void(0);" id="btn-addICD">Add &raquo;</a>
						<a href="JavaScript:void(0);" id="btn-removeICD">&laquo; Remove</a>
							<select  id="icdCodes" name="icdCodes" multiple size="9"  class="form-control">
							</select>
							<springForm:errors path="icdCodes" cssClass="error text-danger" />
					</div>
					<div class="col-sm-4 icdCodes">
						
					
					</div>
				</div>
				
				<div class="form-group required">
					<label class="control-label col-sm-2" for="year">Effective Year (YYYY)</label>
					<div class="col-sm-4">
						<springForm:input path="effectiveYear" class="form-control" id="effectiveYear" placeholder="Effective Year" />
						<springForm:errors path="effectiveYear" cssClass="error text-danger" />
					</div>
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-2" for="gender">Gender</label>
					<div class="col-sm-4">
						<springForm:select path="genderId"  class="form-control" id="gender">
						    <springForm:option  value="${null}" label="Select One" />
					   		<springForm:options items="${genderList}"  itemValue="id" itemLabel="description" />
						</springForm:select>
						<springForm:errors path="genderId" cssClass="error text-danger" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label   col-sm-2" for="lowerAgeLimit">Lower Age Limit</label>
					<div class="col-sm-4">
						<springForm:input path="lowerAgeLimit" class="form-control" id="lowerAgeLimit" placeholder="lowerAgeLimit" />
						<springForm:errors path="lowerAgeLimit" cssClass="error text-danger" />
					  </div>
				</div>
				
				<div class="form-group">
					<label class="control-label  col-sm-2" for="upperAgeLimit">Upper Age Limit</label>
					<div class="col-sm-4">
						<springForm:input path="upperAgeLimit" class="form-control" id="upperAgeLimit" placeholder="upperAgeLimit" />
						<springForm:errors path="upperAgeLimit" cssClass="error text-danger" />
					  </div>
				</div>
				
				<div class="form-group">
					<label class="control-label  col-sm-2" for="doseCount">Dose Count</label>
					<div class="col-sm-4">
						<springForm:input path="doseCount" class="form-control" id="doseCount" placeholder="doseCount" />
						<springForm:errors path="doseCount" cssClass="error text-danger" />
					  </div>
				</div>
				
				<div class="form-group">
					<label class="control-label  col-sm-2" for="ageEffectiveFrom" >Age Effective From</label>
					<div class="col-sm-4">
						<fmt:formatDate value="${hedisMeasureRule.ageEffectiveFrom}" var="dateString" pattern="MM/dd/yyyy" />
						<springForm:input path="ageEffectiveFrom" value="${dateString}" class="form-control datepicker"  id="ageEffectiveFrom" placeholder="ageEffectiveFrom" />
						<springForm:errors path="ageEffectiveFrom" cssClass="error text-danger" />
					  </div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="ageEffectiveTo ">Age Effective To</label>
					<div class="col-sm-4">
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


     

<script>
		$(document).ready(function() {
			
			
			
			$('#btn-cpt').click(function(){
					$(this).remove();
				
			     var $select = $('<select name ="\selectCPT\" id="\select-cpt\" class="\ form-control \" multiple ="\multiple\"  size="\9\"/>');
			   //request the JSON data and parse into the select element
				   $.getJSON('http://localhost:8080/Pras/hedisMeasureRule/cpt', function(data){
				    
				     //clear the current content of the select
				     $select.html('');
				    
				     //iterate over the data and append a select option
				     
				     $.each(data.data, function(key, val){
				    	
				      
				       $select.append('<option value="' + val.id + '">' + val.code +'</option>');
				     })
				   
				     $('.cptCodes').html($select);
				     alert("hi");
				   });

		    });
			
			$('#btn-icd').click(function(){
				$(this).remove();
			     var $select = $('<select name="\selectICD\" id="\select-icd\"  class="\ form-control \" multiple ="\multiple\"  size="\9\"/>');
			   //request the JSON data and parse into the select element
				   $.getJSON('http://localhost:8080/Pras/hedisMeasureRule/icd', function(data){
				    
				     //clear the current content of the select
				     $select.html('');
				    
				     //iterate over the data and append a select option
				      
				     $.each(data.data, function(key, val){
				      
				       $select.append('<option value="' + val.id + '">' + val.code +'</option>');
				     })
				   
				     $('.icdCodes').html($select);
				     
				   });

		    });
			
		    $('#btn-addCPT').click(function(){
		        $('#select-cpt option:selected').each( function() {
		                $('#cptCodes').append("<option value='"+$(this).val()+"' selected='selected'>"+$(this).text()+"</option>");
		            $(this).remove();
		        });
		    });
		    $('#btn-removeCPT').click(function(){
		        $('#cptCodes option:selected').each( function() {
		            $('#select-cpt').append("<option value='"+$(this).val()+"'>"+$(this).text()+"</option>");
		            $(this).remove();
		        });
		    });
		    
		    $('#btn-addICD').click(function(){
		        $('#select-icd option:selected').each( function() {
		                $('#icdCodes').append("<option value='"+$(this).val()+"' selected='selected'>"+$(this).text()+"</option>");
		            $(this).remove();
		        });
		    });
		    $('#btn-removeICD').click(function(){
		        $('#icdCodes option:selected').each( function() {
		            $('#select-icd').append("<option value='"+$(this).val()+"'>"+$(this).text()+"</option>");
		            $(this).remove();
		        });
		    });
		 
		});
		
		
</script>
<script>
$( "#hedisMeasureRule" ).submit(function( event ) {
	
	var error_count=0;
	var effectiveYear = $("input#effectiveYear").val().length;
		if (effectiveYear < 3 || effectiveYear > 20) {
			$("#effectiveYear").closest( "div" ).addClass( "has-error" );
			$('#effectiveYear').closest( "div" ).find('span').remove();
			$( "#effectiveYear" ).after("<span  class='text-danger'>Effective Year Must be equal to four digits.</span>" );
			error_count++;
		}
		else
		{
			$('#effectiveYear').closest( "div" ).find('span').remove();
			$("#effectiveYear").closest( "div" ).removeClass( "has-error" );
		}	
	    var icdCodes = $("#icdCodes").val();
	  	
		if (!icdCodes) {
			$("#icdCodes").closest( "div" ).addClass( "has-error" );
			$('#icdCodes').closest( "div" ).find('span').remove();
			$("#icdCodes" ).after("<span  class='text-danger'>Select ICD Codes.</span>" );
			error_count++;
		}
		else
		{
			$('#icdCodes').closest( "div" ).find('icdCodes').remove();
			$("#icdCodes").closest( "div" ).removeClass( "has-error" );
		}
		
		var cptCodes = $("#cptCodes").val();
	  	
		if (!cptCodes) {
			$("#cptCodes").closest( "div" ).addClass( "has-error" );
			$('#cptCodes').closest( "div" ).find('span').remove();
			$("#cptCodes" ).after("<span  class='text-danger'>Select CPT Codes.</span>" );
			error_count++;
		}
		else
		{
			$('#cptCodes').closest( "div" ).find('icdCodes').remove();
			$("#cptCodes").closest( "dov" ).removeClass( "has-error" );
		}
		
		
		
		
	    if(error_count >0){ event.preventDefault();}
	});

</script>




function membershipValidation(listname){
	
	$( "#firstName" ).focusout(function() {
	    var firstName = $("input#firstName").val().length;
		if (firstName < 3 || firstName > 20) {
			$("#firstName").closest( "div" ).addClass( "has-error" );
			$('#firstName').closest( "div" ).find('span').remove();
			$( "#firstName" ).after("<span  class='text-danger'>FirstName must be at least min 2 and max 20 characters.</span>" );
		}
		else
		{
			$('#firstName').closest( "div" ).find('span').remove();
			$("#firstName").closest( "div" ).removeClass( "has-error" );
		}	
  	});
  	$( "#lastName" ).focusout(function() {
  		var firstName = $("input#lastName").val().length;
		if (firstName < 3 || firstName > 20) {
			$("#lastName").closest( "div" ).addClass( "has-error" );
			$('#lastName').closest( "div" ).find('span').remove();
			$( "#lastName" ).after("<span  class='text-danger'>FirstName must be at least min 2 and max 20 characters.</span>" );
		}
		else
		{
			$('#lastName').closest( "div" ).find('span').remove();
			$("#lastName").closest( "div" ).removeClass( "has-error" );
		}	
  	});
  	$( "#gender" ).focusout(function() {
  		alert($("#gender").val());
  		var gender = $("#gender").val();
  	
		if (!gender) {
			$("#gender").closest( "div" ).addClass( "has-error" );
			$('#gender').closest( "div" ).find('span').remove();
			$("#gender" ).after("<span  class='text-danger'>Select Gender.</span>" );
		}
		else
		{
			$('#gender').closest( "div" ).find('span').remove();
			$("#gender").closest( "div" ).removeClass( "has-error" );
		}	
  	});
  	$( "#status" ).focusout(function() {
  		alert($("#status").val());
  		var status = $("#status").val();
  	
		if (!status) {
			$("#status").closest( "div" ).addClass( "has-error" );
			$('#status').closest( "div" ).find('span').remove();
			$("#status" ).after("<span  class='text-danger'>Select Status.</span>" );
		}
		else
		{
			$('#status').closest( "div" ).find('span').remove();
			$("#status").closest( "div" ).removeClass( "has-error" );
		}		
 });
$( "#county" ).focusout(function() {
		alert($("#county").val());
		var county = $("#county").val();
	
	if (!county) {
		$("#county").closest( "div" ).addClass( "has-error" );
		$('#county').closest( "div" ).find('span').remove();
		$("#county" ).after("<span  class='text-danger'>Select County.</span>" );
	}
	else
	{
		$('#county').closest( "div" ).find('span').remove();
		$("#county").closest( "div" ).removeClass( "has-error" );
	}		
	
});

$( "#dob" ).focusout(function() {
	
	var dob = $("#dob").val();
	$('#dob').closest( "div" ).find('span').remove();
	$("#dob").closest( "div" ).removeClass( "has-error" );
		if (dob=='') {
			$("#dob").closest( "div" ).addClass( "has-error" );
			$('#dob').closest( "div" ).find('span').remove();
			$("#dob" ).after("<span  class='text-danger'>Select Date.</span>" );
		}
		else
		{
			
			var rxDatePattern = /^(\d{1,2})(\/|-)(\d{1,2})(\/|-)(\d{4})$/; 
			var dtArray = dob.match(rxDatePattern); // is format OK?
		
			if (dtArray == null)
			{
				$("#dob").closest( "div" ).addClass( "has-error" );
				$('#dob').closest( "div" ).find('span').remove();
				$("#dob" ).after("<span  class='text-danger'>Select Valid Date.</span>" );
			} 
			else
			{
				dtMonth = dtArray[1];
				dtDay= dtArray[3];
				dtYear = dtArray[5];
				if (dtMonth < 1 || dtMonth > 12)
				{
					$("#dob").closest( "div" ).addClass( "has-error" );
					$('#dob').closest( "div" ).find('span').remove();
					$("#dob" ).after("<span  class='text-danger'>Select Valid Date.</span>" );
				}
					
				else if (dtDay < 1 || dtDay> 31)
				{
					$("#dob").closest( "div" ).addClass( "has-error" );
					$('#dob').closest( "div" ).find('span').remove();
					$("#dob" ).after("<span  class='text-danger'>Select Valid Date.</span>" );
					
				}
				    
				else if ((dtMonth==4 || dtMonth==6 || dtMonth==9 || dtMonth==11) && dtDay ==31)
					{
					$("#dob").closest( "div" ).addClass( "has-error" );
					$('#dob').closest( "div" ).find('span').remove();
						$("#dob" ).after("<span  class='text-danger'>Select Valid Date.</span>" );
					}
				   
				else if (dtMonth == 2)
				{
				   var isleap = (dtYear % 4 == 0 && (dtYear % 100 != 0 || dtYear % 400 == 0));
				   if (dtDay> 29 || (dtDay ==29 && !isleap))
				   {
					   $("#dob").closest( "div" ).addClass( "has-error" );
						$('#dob').closest( "div" ).find('span').remove();
						$("#dob" ).after("<span  class='text-danger'>Select Valid Date.</span>" );
					}
				}
				
			}	
			
			$('#dob').closest( "div" ).find('span').remove();
			$("#dob").closest( "div" ).removeClass( "has-error" );
		}		
		
	});
}







	
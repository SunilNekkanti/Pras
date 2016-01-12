<%@  page  language="java" contentType="text/html; charset=ISO-8859-1"
 
pageEncoding="ISO-8859-1"%>

<%@  taglib  prefix="form" uri="http://www.springframework.org/tags/form"%>
 
<!DOCTYPE html  PUBLIC  "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<!-- meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"-->

<title>Spring3Example</title>

  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

    <script src="//raw.github.com/botmonster/jquery-bootpag/master/lib/jquery.bootpag.min.js"></script>

    

<script>
$(document).ready(function(){	
$.get('membership/list',null,function(responseText) { 
	
	 for(var i=0;i<responseText.data.length;i++) {
		 $("#tab tbody").append(
		    "<tr>" +
		   "<td> <a href= membership/"+responseText.data[i].id + ">"+responseText.data[i].firstName +"</a></td> " +
        
        "<td>"+ responseText.data[i].lastName + "</td> " +
        
        "<td>"+ responseText.data[i].dob + "</td> " +
        
        "<td>"+ responseText.data[i].genderId.description + "</td>"+
        
        "<td>"+ responseText.data[i].countyCode.description + "</td> "+
        
        "<td>"+ responseText.data[i].fileId + "</td> "+
        
        "<td>"+ responseText.data[i].status.description + "</td></tr> "      );
		 
	 }
	 
   });

});
   

$(document).ready(function(){
	
	 alert('testing');
	 
	  //how much items per page to show
	  var show_per_page = 3; 
	  //getting the amount of elements inside content div
	  var number_of_items = $('#content').children().size();
	  //calculate the number of pages we are going to have
	  var number_of_pages = Math.ceil(number_of_items/show_per_page);
	  //set the value of our hidden input fields
	  $('#current_page').val(0);
	  $('#show_per_page').val(show_per_page);

	  var navigation_html = '<ul class="pagination">';

	  navigation_html += '<li class="previous_link">';
	  navigation_html += '<a href="javascript:previous();">&larr; Previous</a>';
	  navigation_html += '</li>';
	  var current_link = 0;
	  while(number_of_pages > current_link){
	    navigation_html += '<li class="page_link" id="id' + current_link +'">';
	    navigation_html += '<a href="javascript:go_to_page(' + current_link +')" longdesc="' + current_link +'">'+ (current_link + 1) +'</a>';
	    current_link++;
	    navigation_html += '</li>';
	  }
	  navigation_html += '<li>';
	  navigation_html += '<a class="next_link" href="javascript:next();">Next &rarr;</a>';
	  navigation_html += '</li>';
	  navigation_html += '</ul>';
	  $('#page_navigation').html(navigation_html);

	  //add active class to the first page link
	  $('#page_navigation .page_link:first').addClass('active');

	  //hide all the elements inside content div
	  $('#content').children().css('display', 'none');

	  //and show the first n (show_per_page) elements
	  $('#content').children().slice(0, show_per_page).css('display', 'table-row');

	});

	function previous(){

	  new_page = parseInt($('#current_page').val()) - 1;
	  //if there is an item before the current active link run the function
	  if($('.active').prev('.page_link').length==true){
	    go_to_page(new_page);
	  }
	}

	function next(){
	  new_page = parseInt($('#current_page').val()) + 1;
	  //if there is an item after the current active link run the function
	  if($('.active').next('.page_link').length==true){
	    go_to_page(new_page);
	  }

	}

	function go_to_page(page_num){
	  //get the number of items shown per page
	  var show_per_page = parseInt($('#show_per_page').val());

	  //get the element number where to start the slice from
	  start_from = page_num * show_per_page;

	  //get the element number where to end the slice
	  end_on = start_from + show_per_page;

	  activate_id = page_num;
	  var get_box = document.getElementById("id"+page_num);
	  //hide all children elements of content div, get specific items and show them
	  $('#content').children().css('display', 'none').slice(start_from, end_on).css('display', 'table-row');

	  /*get the page link that has longdesc attribute of the current page and add active class to it
	  and remove that class from previously active page link*/
	  $("#page_navigation").find('li.active').removeClass("active");
	  $(get_box).addClass("active");


	  //update the current page input field
	  $('#current_page').val(page_num);
	  
	}

	$(document).ready(function() {

	    $('#tab tr').click(function() {
	        var href = $(this).find("a").attr("href");
	        if(href) {
	        	alert(href)
	            window.location = href;
	        }
	    });

	});

</script>
 </head>

<body>

<form:form action="membership.html" commandName="membership">
  <h2>Membership Details</h2>
  <div class="panel-group">
    <div class="panel panel-primary">
      <div class="panel-heading">Membership Details</div>
      <div class="panel-body" id="tablediv">
         
         
		<table id="tab" class="table table-striped table-hover"> 
			<thead> 
				<tr>
	 				<th  scope="col">First Name</th>  
			        <th  scope="col">Last Name</th>  
			        <th  scope="col">Date Of Birth</th>  
			        <th  scope="col">Gender</th>  
			        <th  scope="col">County</th>
			        <th  scope="col">File Number</th>  
			        <th  scope="col">Status</th>  
				</tr>   
			</thead> 
			
			<tbody id="content"> 
				<div id="show_per_page"></div>
				<div id="current_page"></div>
			</tbody> 
			
			<tfoot > 
				<div class="col-md-12 text-center" id="page_navigation"> </div>
			</tfoot> 
			
		</table> 
 
    </div>
    </div>
  </div>

</form:form>
      	
</body>
</html>
function prasPagination(listname){
	
	
	  //how much items per page to show
	  var show_per_page = 100; 
	  //getting the amount of elements inside content div
	  var number_of_items = $('#content'+listname).children().size();
	  //calculate the number of pages we are going to have
	  var number_of_pages = Math.ceil(number_of_items/show_per_page);
	  //set the value of our hidden input fields
	  $('#current_page'+listname).val(0);
	  $('#show_per_page'+listname).val(show_per_page);
	  var navigation_html = '<ul class="pagination">';

	  navigation_html += '<li class="previous_link">';
	  navigation_html += '<a href="javascript:previous(\''+listname+'\');">&larr; Previous</a>';
	  navigation_html += '</li>';
	 
	  var current_link = 0;
	  while(number_of_pages > current_link){
	    navigation_html += '<li class="page_link" id="id' + current_link +listname+'">';
	    navigation_html += '<a href="javascript:go_to_page(' + current_link +',\''+listname+'\')" longdesc="' + current_link +'">'+ (current_link + 1) +'</a>';
	    current_link++;
	    navigation_html += '</li>';
	  }
	  navigation_html += '<li>';
	  navigation_html += '<a class="next_link" href="javascript:next(\''+listname+'\');">Next &rarr;</a>';
	  navigation_html += '</li>';
	  navigation_html += '</ul>';
	  
	  $('#page_navigation'+listname).html(navigation_html);

	  //add active class to the first page link
	  $('#page_navigation'+listname+' .page_link:first').addClass('active');

	  //hide all the elements inside content div
	  $('#content'+listname).children().css('display', 'none');

	  //and show the first n (show_per_page) elements
	  $('#content'+listname).children().slice(0, show_per_page).css('display', 'table-row');

  }

	function previous(listname){
	  new_page = parseInt($('#current_page'+listname).val()) - 1;
	  
	  //if there is an item before the current active link run the function
	  if($('.active').prev('.page_link').length==true){
	    go_to_page(new_page,listname);
	  }
	}

	function next(listname){
	  new_page = parseInt($('#current_page'+listname).val()) + 1;
	
	  //if there is an item after the current active link run the function
	  if($('.active').next('.page_link').length==true){
	    go_to_page(new_page, listname);
	  }
	}

	function go_to_page(page_num,listname){
	  //get the number of items shown per page
	  var show_per_page = parseInt($('#show_per_page'+listname).val());
	  //get the element number where to start the slice from
	  start_from = page_num * show_per_page;
	  //get the element number where to end the slice
	  end_on = start_from + show_per_page;
	  activate_id = page_num;
	 
	  var get_box = document.getElementById("id"+page_num+listname);
	  //hide all children elements of content div, get specific items and show them
	  $('#content'+listname).children().css('display', 'none').slice(start_from, end_on).css('display', 'table-row');

	  /*get the page link that has longdesc attribute of the current page and add active class to it
	  and remove that class from previously active page link*/
	  $("#page_navigation"+listname).find('li.active').removeClass("active");
	  $(get_box).addClass("active");

	  //update the current page input field
	  $('#current_page'+listname).val(page_num);
	  
	}

	function openMembershipForm() {
	$('#tab tr').click(
			function() {
				// get the link location that was clicked
				var href = $(this).find("a").attr("href");
				if (href) {
					$.ajax({
						type : "GET",
						url : "http://localhost:8080/Pras/" + href,
						data : '$format=json',
						dataType : 'json',
						success : function(response) {
							$.each(response.data, function(key, value) {
								alert("key is"+key);
								if (key == "status" || key == "genderId" || key == "countyCode") {
									$('#' + key).val(value.description);
								} else {
									$('#' + key).val(value);
								}
							})
						},

						error : function(xhr) {
							alert("AJAX request failed: " + xhr.status);
						}

					});

					// to change the browser URL to the given link location
					if (href != window.location) {
						 window.history.pushState({path:href},'',href);
					}

				}
		});

	}
	
	function populateMembershipListData (){
		$.get('membership/list',null,function(responseText) { 
		 for(var i=0;i<responseText.data.length;i++) {
			 $("#tab tbody").append(
			    "<tr>" +
			   "<td> <a href= membership/"+responseText.data[i].id + " rel='tab'] >"+responseText.data[i].firstName +"</a></td> " +
	        "<td>"+ responseText.data[i].lastName + "</td> " +
	        "<td>"+ responseText.data[i].dob + "</td> " +
	        "<td>"+ responseText.data[i].genderId.description + "</td>"+
	        "<td>"+ responseText.data[i].countyCode.description + "</td> "+
	        "<td>"+ responseText.data[i].fileId + "</td> "+
	        "<td>"+ responseText.data[i].status.description + "</td></tr> "      );
		 }
	   });
	}
	
	function updateMembershipForm (){
		$("#updateButton").click(function(){
			
			alert('clicked update button');
		    var bean = {"id":"158", "firstName": $("#firstName").val(),  "lastName":$("#lastName").val() };
		    $.ajax({
		        url: "http://localhost:8080/Pras/membership/176",
		        contentType : "application/json; charset=utf-8",
		        type: 'PUT',
		        dataType: 'json',
		        success: function (data) {alert(JSON.parse(data));},
		        data: JSON.stringify(bean),
		        error:function(){alert("ajax post call failed");}
		    });
		});
	}

	function deleteMembershipForm (){
		$("#deleteButton").click(function(){
			
			alert('clicked cancel button');
		    var bean = {"id":"175"};
		    $.ajax({
		        url: "http://localhost:8080/Pras/membership/175",
		        contentType : "application/json;charset=UTF-8",
		        type: 'DELETE',
		        dataType: 'json',
		        success: function (data) {alert(JSON.parse(data));},
		        data: JSON.stringify(bean),
		        error:function(){alert("ajax delete call failed");}
		    });
		});
	}
	
	function resetMembershipForm (formid){
		$("#cancelButton").click(function(){
			alert('clicked cancel button');
			$('#form-ajax').ajaxForm(function() {
			    this.reset();
			});
		});
	}

	
	function  removePlaceHolder(){
		$("input").removeAttr('placeholder');
	}
	

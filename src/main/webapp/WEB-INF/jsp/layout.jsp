<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="context" value="${pageContext.request.contextPath}" />
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<head>
<title>Physicians' First Choice</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="http://code.jquery.com/jquery-2.1.0.js"></script>
<script src="http://code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<script type="text/javascript" src="${context}/resources/js/cookie.js"></script>

<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<link
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<script type="text/javascript"
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="${context}/resources/css/common.css">

<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/buttons/1.1.2/css/buttons.dataTables.min.css">
<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="https://cdn.datatables.net/buttons/1.1.2/js/dataTables.buttons.min.js"></script>
<script type="text/javascript"
	src="http://cdn.datatables.net/buttons/1.1.2/js/buttons.html5.min.js"></script>
<script type="text/javascript"
	src="http://cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js"></script>
<script type="text/javascript"
	src="http://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/pdfmake.min.js"></script>
<script type="text/javascript"
	src="http://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/vfs_fonts.js"></script>
<script type="text/javascript"
	src="http://cdn.datatables.net/buttons/1.3.1/js/buttons.colVis.min.js"></script>

<c:set var="context"
	value="${pageContext.request.contextPath}/${userpath}" />
<script>

function stringToDate(_date,_format,_delimiter)
{
            var formatLowerCase=_format.toLowerCase();
            var formatItems=formatLowerCase.split(_delimiter);
            var dateItems=_date.split(_delimiter);
            var monthIndex=formatItems.indexOf("mm");
            var dayIndex=formatItems.indexOf("dd");
            var yearIndex=formatItems.indexOf("yyyy");
            var month=parseInt(dateItems[monthIndex]);
            month-=1;
            var formatedDate = new Date(dateItems[yearIndex],month,dateItems[dayIndex]);
            return formatedDate;
}


Number.prototype.formatMoney = function(c, d, t){
	 var n = this, 
	     c = isNaN(c = Math.abs(c)) ? 2 : c, 
	     d = d == undefined ? "." : d, 
	     t = t == undefined ? "," : t, 
	     s = n < 0 ? "-" : "", 
	     i = String(parseInt(n = Math.abs(Number(n) || 0).toFixed(c))), 
	     j = (j = i.length) > 3 ? j % 3 : 0;
	    return s + (j ? i.substr(0, j) + t : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + t) + (c ? d + Math.abs(n - i).toFixed(c).slice(2) : "");
	  };
	  
		$(document).ready(function() {
			
				  
			 $('.rowClick tbody').on('click', 'tr', function () {
				    	window.location = $(location).attr('protocol')+"//"+$(location).attr('host')+$(this).find('a').attr('href');
				    } );
		});
 </script>
<style>
.center {
	text-align: center;
}

/* Remove the navbar's default margin-bottom and rounded borders */
.navbar {
	margin-bottom: 0;
	border-radius: 0;
	padding: 15px;
}

/* Set height of the grid so .sidenav can be 100% (adjust as needed) */
.row.content {
	height: 850px;
}

/* Set gray background color and 100% height */
.sidenav {
	padding-top: 20px;
	background-color: #f1f1f1;
	height: 100%;
}

/* Set black background color, white text and some padding */
footer {
	background-color: #555;
	color: white;
	padding: 5px;
}

/* On small screens, set height to 'auto' for sidenav and grid */
@media screen and (max-width: 767px) {
	.sidenav {
		height: auto;
		padding: 15px;
	}
	.row.content {
		height: auto;
	}
}

.glyphicon.glyphicon-paperclip {
	font-size: 25px;
}
</style>

</head>

<body>
	<script>
	
	
	 function detectIE() {
		    var ua = window.navigator.userAgent;

		    var msie = ua.indexOf('MSIE ');
		    if (msie > 0) {
		        // IE 10 or older => return version number
		        return parseInt(ua.substring(msie + 5, ua.indexOf('.', msie)), 10);
		    }

		    var trident = ua.indexOf('Trident/');
		    if (trident > 0) {
		        // IE 11 => return version number
		        var rv = ua.indexOf('rv:');
		        return parseInt(ua.substring(rv + 3, ua.indexOf('.', rv)), 10);
		    }

		    var edge = ua.indexOf('Edge/');
		    if (edge > 0) {
		       // Edge (IE 12+) => return version number
		       return parseInt(ua.substring(edge + 5, ua.indexOf('.', edge)), 10);
		    }

		    // other browser
		    return false;
		}
		
		var ie = detectIE();
		var cookiePath;
		if(ie)
			cookiePath = "";
		else
		{	cookiePath = window.location.pathname;
			cookiePath = "";
		}
		 
		 function getDropDownCache(dropDown)
		 {
			 var value = Cookies.get(dropDown);
			 if(value){
				 var cacheList = new Array;
			 var cacheValue = $.parseJSON(value);
				$.each(cacheValue, function (i,v)
				{
					 if($.isNumeric(v))
						cacheList.push(parseInt(v)); 
					 else
						cacheList.push(v);
				});
			  	return cacheList
			 }
			 return "";
			
		 }
		 function dropDownCache(dropDown){
		  		 var arrayList = new Array;
		  		 arrayList = selectedList(dropDown);
			   	 $('#'+dropDown).multiselect('select', arrayList);
					
				 $('#'+dropDown).multiselect({numberDisplayed: 0, 
			    	 buttonWidth: '150px',
			    	 includeSelectAllOption: true,
			    	 templates: {
						 ul: '<ul class="multiselect-container dropdown-menu '+dropDown+'"></ul>'
					 },
			    });
				 
		}
		 function selectedList(dropDown)
		 {
			 var selectedList = new Array;
		   	  $('.'+dropDown+ ' '+'input[type=checkbox]').each(function () {
		   			 if(this.checked){
		   				selectedList.push($(this).val()); 
		   			 }
		   	 });
		   	  
		   	  return selectedList;
		 }
jQuery( document ).ready(function( $ ) {
	$(".datepicker").datepicker({
        dateFormat: 'mm/dd/yy',
        changeMonth: true,
        changeYear: true,
        
    });

	  $('body').on('focus',".datepicker", function(){
		    $(this).datepicker({
		    	onSelect:function(date){
		    		$id = $(this).attr('id');
		    		$dateValue = $("#"+$id).val();
		    		if($id.search( 'claimStartDate' ) != -1){
		    			$id = $id.replace("claimStartDate", "claimEndDate");
		    			$("[id='"+$id+"']").val(date);
		    		}
		    	}
		    });
		   });
	  
	  $("#deleteButton").click(function(e){
		   if (confirm("Action cannot be undone.Click 'Ok' to delete.") == false) 
		   {
		    e.preventDefault();
		   }
		    });
});

jQuery( document ).ready(function( $ ) {
	
	
	 $('body').on('keydown',".datepickerfrom, .datepickerto, .datepicker, .datepicker1, .datepicker3, .processfrom, .processto", function(event){
		 if(event.keyCode == 8 || event.keyCode == 46) {
		        $(this).val('');
		   }
		 else
		 	event.preventDefault();
    });

		

	  $('body').on('focus',".datepickerfrom", function(){
		 	 $(this).datepicker({
			          dateFormat: 'mm/dd/yy',
			          changeMonth: true,
			          changeYear: true,
			          minDate:'01/01/2015',
			          onClose: function( selectedDate ) {
			              $( ".datepickerto" ).datepicker( "option", "minDate", selectedDate );
			            }
			      });
			   });

	 $('body').on('focus',".datepickerto", function(){
  		 var date1 = new Date($('.datepickerfrom').val());
		  $(this).datepicker({
	          dateFormat: 'mm/dd/yy',
	          changeMonth: true,
	          changeYear: true,
	          minDate: date1
	         
	      });

	});
	 
	 $('body').on('focus',".datepicker1", function(){
	 	  
	 	 var date1 = new Date($('.startDateText').text());
	 	 var date2 = new Date($('.endDateText').text());
	 	$(this).datepicker( "destroy" );
	 	$(this).datepicker({
	          dateFormat: 'mm/dd/yy',
	          minDate: date1,
	          maxDate:date2,
	          changeMonth: true,
	          changeYear: true,
	          onClose: function( selectedDate ) {
	              $( ".datepicker3" ).datepicker( "option", "minDate", selectedDate );
	            }
	      });
	 	 
	 		
	   });
  
  $('body').on('focus',".datepicker3", function(){
	  var date1 = new Date($('.datepicker1').val());
	  var date2 = new Date($('.endDateText').text());
	  $(this).datepicker( "destroy" );
      $(this).datepicker({
          dateFormat: 'mm/dd/yy',
          minDate: date1,
          maxDate:date2,
          changeMonth: true,
          changeYear: true
      });
   });
  
  
});


$(document).ready(function(){	

	$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        localStorage.setItem('lastTab', $(this).attr('href'));
    });

    var lastTab = localStorage.getItem('lastTab');
    if (lastTab) {
        $('[href="' + lastTab + '"]').tab('show');
    }


$('[data-toggle="tab"]').click(function(e) {
    e.preventDefault();
    var loadurl = $(this).attr('href');
    var targ = $(this).attr('data-target');
    $.get(loadurl, function(data) {
        $(targ).html(data);

    });
    $(this).tab('show')
});


});
</script>

	<c:set var="context" value="${pageContext.request.contextPath}" />
	<div>
		<tiles:insertAttribute name="header" />
	</div>
	<div class="container-fluid text-center">
		<div class="row content" style="padding: 25px;">
			<div class="col-sm-12 text-left">
				<tiles:insertAttribute name="body" />
			</div>
		</div>
	</div>
	<div class="footer">
		<tiles:insertAttribute name="footer" />
	</div>

</body>
</html>
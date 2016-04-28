<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<c:set var="context" value="${pageContext.request.contextPath}" />
<tiles:insertAttribute name="body" />

<script>
$(document).ready(function() {  
 $( "form" ).each(function( index ) {
  var formid = $(this).attr("id");
  if($("#"+formid+' input:hidden[name=id]').val())
      $("#"+formid+" input").removeAttr("placeholder");
  }); 
});
</script>



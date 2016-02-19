<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<title>PF Choice</title>

<div id="content">
    <ul id="tabs" class="nav nav-pills" data-tabs="tabs">
        <li class="active"><a href="#membership" data-toggle="tab">Membership</a></li>
        <li><a href="#provider" data-toggle="tab">Provider</a></li>
        <li><a href="#insurance" data-toggle="tab">Insurance</a></li>
    </ul>
    <div class="row" style="height: 550px;overflow-y: scroll;">
	    <div class="col-sm-12" >
		    <div id="my-tab-content" class="tab-content">
		        <div class="tab-pane active" id="membership">
		           <jsp:include page="/WEB-INF/jsp/membershipList.jsp"/>	
		        </div>
		        <div class="tab-pane" id="provider">
		          <jsp:include page="/WEB-INF/jsp/providerList.jsp"/>	
		        </div>
		        <div class="tab-pane" id="insurance">
		            <jsp:include page="/WEB-INF/jsp/insuranceList.jsp"/>
		        </div>
		    </div>
	    </div>
	</div>
</div>

<script type="text/javascript">
    jQuery(document).ready(function ($) {
        $('#tabs').tab();
    });
</script>    

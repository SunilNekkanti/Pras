
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>

<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>PF Choice head page</title>
<style type="text/css">
.dropdown-menu>li {
	position: relative;
	-webkit-user-select: none; /* Chrome/Safari */
	-moz-user-select: none; /* Firefox */
	-ms-user-select: none; /* IE10+ */
	/* Rules below not implemented in browsers yet */
	-o-user-select: none;
	user-select: none;
	cursor: pointer;
}

.dropdown-menu .sub-menu {
	left: 100%;
	position: absolute;
	top: 0;
	display: none;
	margin-top: -1px;
	border-top-left-radius: 0;
	border-bottom-left-radius: 0;
	border-left-color: #fff;
	box-shadow: none;
}

.right-caret:after, .left-caret:after {
	content: "";
	border-bottom: 5px solid transparent;
	border-top: 5px solid transparent;
	display: inline-block;
	height: 0;
	vertical-align: middle;
	width: 0;
	margin-left: 5px;
}

.right-caret:after {
	border-left: 5px solid #ffaf46;
}

.left-caret:after {
	border-right: 5px solid #ffaf46;
}

.navigation {
	font-family: 'Gotham SSm', sans-serif;
	font-weight: 300;
	font-style: normal;
	font-size: 13px;
	line-height: 21px;
	color: #777;
}

.panel.with-nav-tabs .panel-heading {
	padding: 5px 5px 0 5px;
}

.panel.with-nav-tabs .nav-tabs {
	border-bottom: none;
}

.panel.with-nav-tabs .nav-justified {
	margin-bottom: -1px;
}
/********************************************************************/
/*** PANEL DEFAULT ***/
.with-nav-tabs.panel-default .nav-tabs>li>a, .with-nav-tabs.panel-default .nav-tabs>li>a:hover,
	.with-nav-tabs.panel-default .nav-tabs>li>a:focus {
	color: #777;
}

.with-nav-tabs.panel-default .nav-tabs>.open>a, .with-nav-tabs.panel-default .nav-tabs>.open>a:hover,
	.with-nav-tabs.panel-default .nav-tabs>.open>a:focus, .with-nav-tabs.panel-default .nav-tabs>li>a:hover,
	.with-nav-tabs.panel-default .nav-tabs>li>a:focus {
	color: #777;
	background-color: #ddd;
	border-color: transparent;
}

.with-nav-tabs.panel-default .nav-tabs>li.active>a, .with-nav-tabs.panel-default .nav-tabs>li.active>a:hover,
	.with-nav-tabs.panel-default .nav-tabs>li.active>a:focus {
	color: #555;
	background-color: #fff;
	border-color: #ddd;
	border-bottom-color: transparent;
}

.with-nav-tabs.panel-default .nav-tabs>li.dropdown .dropdown-menu {
	background-color: #f5f5f5;
	border-color: #ddd;
}

.with-nav-tabs.panel-default .nav-tabs>li.dropdown .dropdown-menu>li>a {
	color: #777;
}

.with-nav-tabs.panel-default .nav-tabs>li.dropdown .dropdown-menu>li>a:hover,
	.with-nav-tabs.panel-default .nav-tabs>li.dropdown .dropdown-menu>li>a:focus
	{
	background-color: #ddd;
}

.with-nav-tabs.panel-default .nav-tabs>li.dropdown .dropdown-menu>.active>a,
	.with-nav-tabs.panel-default .nav-tabs>li.dropdown .dropdown-menu>.active>a:hover,
	.with-nav-tabs.panel-default .nav-tabs>li.dropdown .dropdown-menu>.active>a:focus
	{
	color: #fff;
	background-color: #555;
}
/********************************************************************/
/*** PANEL PRIMARY ***/
.with-nav-tabs.panel-primary .nav-tabs>li>a, .with-nav-tabs.panel-primary .nav-tabs>li>a:hover,
	.with-nav-tabs.panel-primary .nav-tabs>li>a:focus {
	color: #fff;
}

.with-nav-tabs.panel-primary .nav-tabs>.open>a, .with-nav-tabs.panel-primary .nav-tabs>.open>a:hover,
	.with-nav-tabs.panel-primary .nav-tabs>.open>a:focus, .with-nav-tabs.panel-primary .nav-tabs>li>a:hover,
	.with-nav-tabs.panel-primary .nav-tabs>li>a:focus {
	color: #fff;
	background-color: #3071a9;
	border-color: transparent;
}

.with-nav-tabs.panel-primary .nav-tabs>li.active>a, .with-nav-tabs.panel-primary .nav-tabs>li.active>a:hover,
	.with-nav-tabs.panel-primary .nav-tabs>li.active>a:focus {
	color: #428bca;
	background-color: #fff;
	border-color: #428bca;
	border-bottom-color: transparent;
}

.with-nav-tabs.panel-primary .nav-tabs>li.dropdown .dropdown-menu {
	background-color: #428bca;
	border-color: #3071a9;
}

.with-nav-tabs.panel-primary .nav-tabs>li.dropdown .dropdown-menu>li>a {
	color: #fff;
}

.with-nav-tabs.panel-primary .nav-tabs>li.dropdown .dropdown-menu>li>a:hover,
	.with-nav-tabs.panel-primary .nav-tabs>li.dropdown .dropdown-menu>li>a:focus
	{
	background-color: #3071a9;
}

.with-nav-tabs.panel-primary .nav-tabs>li.dropdown .dropdown-menu>.active>a,
	.with-nav-tabs.panel-primary .nav-tabs>li.dropdown .dropdown-menu>.active>a:hover,
	.with-nav-tabs.panel-primary .nav-tabs>li.dropdown .dropdown-menu>.active>a:focus
	{
	background-color: #4a9fe9;
}
/********************************************************************/
/*** PANEL SUCCESS ***/
.with-nav-tabs.panel-success .nav-tabs>li>a, .with-nav-tabs.panel-success .nav-tabs>li>a:hover,
	.with-nav-tabs.panel-success .nav-tabs>li>a:focus {
	color: #3c763d;
}

.with-nav-tabs.panel-success .nav-tabs>.open>a, .with-nav-tabs.panel-success .nav-tabs>.open>a:hover,
	.with-nav-tabs.panel-success .nav-tabs>.open>a:focus, .with-nav-tabs.panel-success .nav-tabs>li>a:hover,
	.with-nav-tabs.panel-success .nav-tabs>li>a:focus {
	color: #3c763d;
	background-color: #d6e9c6;
	border-color: transparent;
}

.with-nav-tabs.panel-success .nav-tabs>li.active>a, .with-nav-tabs.panel-success .nav-tabs>li.active>a:hover,
	.with-nav-tabs.panel-success .nav-tabs>li.active>a:focus {
	color: #3c763d;
	background-color: #fff;
	border-color: #d6e9c6;
	border-bottom-color: transparent;
}

.with-nav-tabs.panel-success .nav-tabs>li.dropdown .dropdown-menu {
	background-color: #dff0d8;
	border-color: #d6e9c6;
}

.with-nav-tabs.panel-success .nav-tabs>li.dropdown .dropdown-menu>li>a {
	color: #3c763d;
}

.with-nav-tabs.panel-success .nav-tabs>li.dropdown .dropdown-menu>li>a:hover,
	.with-nav-tabs.panel-success .nav-tabs>li.dropdown .dropdown-menu>li>a:focus
	{
	background-color: #d6e9c6;
}

.with-nav-tabs.panel-success .nav-tabs>li.dropdown .dropdown-menu>.active>a,
	.with-nav-tabs.panel-success .nav-tabs>li.dropdown .dropdown-menu>.active>a:hover,
	.with-nav-tabs.panel-success .nav-tabs>li.dropdown .dropdown-menu>.active>a:focus
	{
	color: #fff;
	background-color: #3c763d;
}
/********************************************************************/
/*** PANEL INFO ***/
.with-nav-tabs.panel-info .nav-tabs>li>a, .with-nav-tabs.panel-info .nav-tabs>li>a:hover,
	.with-nav-tabs.panel-info .nav-tabs>li>a:focus {
	color: #31708f;
}

.with-nav-tabs.panel-info .nav-tabs>.open>a, .with-nav-tabs.panel-info .nav-tabs>.open>a:hover,
	.with-nav-tabs.panel-info .nav-tabs>.open>a:focus, .with-nav-tabs.panel-info .nav-tabs>li>a:hover,
	.with-nav-tabs.panel-info .nav-tabs>li>a:focus {
	color: #31708f;
	background-color: #bce8f1;
	border-color: transparent;
}

.with-nav-tabs.panel-info .nav-tabs>li.active>a, .with-nav-tabs.panel-info .nav-tabs>li.active>a:hover,
	.with-nav-tabs.panel-info .nav-tabs>li.active>a:focus {
	color: #31708f;
	background-color: #fff;
	border-color: #bce8f1;
	border-bottom-color: transparent;
}

.with-nav-tabs.panel-info .nav-tabs>li.dropdown .dropdown-menu {
	background-color: #d9edf7;
	border-color: #bce8f1;
}

.with-nav-tabs.panel-info .nav-tabs>li.dropdown .dropdown-menu>li>a {
	color: #31708f;
}

.with-nav-tabs.panel-info .nav-tabs>li.dropdown .dropdown-menu>li>a:hover,
	.with-nav-tabs.panel-info .nav-tabs>li.dropdown .dropdown-menu>li>a:focus
	{
	background-color: #bce8f1;
}

.with-nav-tabs.panel-info .nav-tabs>li.dropdown .dropdown-menu>.active>a,
	.with-nav-tabs.panel-info .nav-tabs>li.dropdown .dropdown-menu>.active>a:hover,
	.with-nav-tabs.panel-info .nav-tabs>li.dropdown .dropdown-menu>.active>a:focus
	{
	color: #fff;
	background-color: #31708f;
}
/********************************************************************/
/*** PANEL WARNING ***/
.with-nav-tabs.panel-warning .nav-tabs>li>a, .with-nav-tabs.panel-warning .nav-tabs>li>a:hover,
	.with-nav-tabs.panel-warning .nav-tabs>li>a:focus {
	color: #8a6d3b;
}

.with-nav-tabs.panel-warning .nav-tabs>.open>a, .with-nav-tabs.panel-warning .nav-tabs>.open>a:hover,
	.with-nav-tabs.panel-warning .nav-tabs>.open>a:focus, .with-nav-tabs.panel-warning .nav-tabs>li>a:hover,
	.with-nav-tabs.panel-warning .nav-tabs>li>a:focus {
	color: #8a6d3b;
	background-color: #faebcc;
	border-color: transparent;
}

.with-nav-tabs.panel-warning .nav-tabs>li.active>a, .with-nav-tabs.panel-warning .nav-tabs>li.active>a:hover,
	.with-nav-tabs.panel-warning .nav-tabs>li.active>a:focus {
	color: #8a6d3b;
	background-color: #fff;
	border-color: #faebcc;
	border-bottom-color: transparent;
}

.with-nav-tabs.panel-warning .nav-tabs>li.dropdown .dropdown-menu {
	background-color: #fcf8e3;
	border-color: #faebcc;
}

.with-nav-tabs.panel-warning .nav-tabs>li.dropdown .dropdown-menu>li>a {
	color: #8a6d3b;
}

.with-nav-tabs.panel-warning .nav-tabs>li.dropdown .dropdown-menu>li>a:hover,
	.with-nav-tabs.panel-warning .nav-tabs>li.dropdown .dropdown-menu>li>a:focus
	{
	background-color: #faebcc;
}

.with-nav-tabs.panel-warning .nav-tabs>li.dropdown .dropdown-menu>.active>a,
	.with-nav-tabs.panel-warning .nav-tabs>li.dropdown .dropdown-menu>.active>a:hover,
	.with-nav-tabs.panel-warning .nav-tabs>li.dropdown .dropdown-menu>.active>a:focus
	{
	color: #fff;
	background-color: #8a6d3b;
}
/********************************************************************/
/*** PANEL DANGER ***/
.with-nav-tabs.panel-danger .nav-tabs>li>a, .with-nav-tabs.panel-danger .nav-tabs>li>a:hover,
	.with-nav-tabs.panel-danger .nav-tabs>li>a:focus {
	color: #a94442;
}

.with-nav-tabs.panel-danger .nav-tabs>.open>a, .with-nav-tabs.panel-danger .nav-tabs>.open>a:hover,
	.with-nav-tabs.panel-danger .nav-tabs>.open>a:focus, .with-nav-tabs.panel-danger .nav-tabs>li>a:hover,
	.with-nav-tabs.panel-danger .nav-tabs>li>a:focus {
	color: #a94442;
	background-color: #ebccd1;
	border-color: transparent;
}

.with-nav-tabs.panel-danger .nav-tabs>li.active>a, .with-nav-tabs.panel-danger .nav-tabs>li.active>a:hover,
	.with-nav-tabs.panel-danger .nav-tabs>li.active>a:focus {
	color: #a94442;
	background-color: #fff;
	border-color: #ebccd1;
	border-bottom-color: transparent;
}

.with-nav-tabs.panel-danger .nav-tabs>li.dropdown .dropdown-menu {
	background-color: #f2dede; /* bg color */
	border-color: #ebccd1; /* border color */
}

.with-nav-tabs.panel-danger .nav-tabs>li.dropdown .dropdown-menu>li>a {
	color: #a94442; /* normal text color */
}

.with-nav-tabs.panel-danger .nav-tabs>li.dropdown .dropdown-menu>li>a:hover,
	.with-nav-tabs.panel-danger .nav-tabs>li.dropdown .dropdown-menu>li>a:focus
	{
	background-color: #ebccd1; /* hover bg color */
}

.with-nav-tabs.panel-danger .nav-tabs>li.dropdown .dropdown-menu>.active>a,
	.with-nav-tabs.panel-danger .nav-tabs>li.dropdown .dropdown-menu>.active>a:hover,
	.with-nav-tabs.panel-danger .nav-tabs>li.dropdown .dropdown-menu>.active>a:focus
	{
	color: #fff; /* active text color */
	background-color: #a94442; /* active bg color */
}

/* custom inclusion of right, left and below tabs */
.tabs-below>.nav-tabs, .tabs-right>.nav-tabs, .tabs-left>.nav-tabs {
	border-bottom: 0;
}

.tab-content>.tab-pane, .pill-content>.pill-pane {
	display: none;
}

.tab-content>.active, .pill-content>.active {
	display: block;
}

.tabs-below>.nav-tabs {
	border-top: 1px solid #ddd;
}

.tabs-below>.nav-tabs>li {
	margin-top: -1px;
	margin-bottom: 0;
}

.tabs-below>.nav-tabs>li>a {
	-webkit-border-radius: 0 0 4px 4px;
	-moz-border-radius: 0 0 4px 4px;
	border-radius: 0 0 4px 4px;
}

.tabs-below>.nav-tabs>li>a:hover, .tabs-below>.nav-tabs>li>a:focus {
	border-top-color: #ddd;
	border-bottom-color: transparent;
}

.tabs-below>.nav-tabs>.active>a, .tabs-below>.nav-tabs>.active>a:hover,
	.tabs-below>.nav-tabs>.active>a:focus {
	border-color: transparent #ddd #ddd #ddd;
}

.tabs-left>.nav-tabs>li, .tabs-right>.nav-tabs>li {
	float: none;
}

.tabs-left>.nav-tabs>li>a, .tabs-right>.nav-tabs>li>a {
	min-width: 74px;
	margin-right: 0;
	margin-bottom: 3px;
}

.tabs-left>.nav-tabs {
	float: left;
	margin-right: 19px;
	border-right: 1px solid #ddd;
}

.tabs-left>.nav-tabs>li>a {
	margin-right: -1px;
	-webkit-border-radius: 4px 0 0 4px;
	-moz-border-radius: 4px 0 0 4px;
	border-radius: 4px 0 0 4px;
}

.tabs-left>.nav-tabs>li>a:hover, .tabs-left>.nav-tabs>li>a:focus {
	border-color: #eeeeee #dddddd #eeeeee #eeeeee;
}

.tabs-left>.nav-tabs .active>a, .tabs-left>.nav-tabs .active>a:hover,
	.tabs-left>.nav-tabs .active>a:focus {
	border-color: #ddd transparent #ddd #ddd;
	*border-right-color: #ffffff;
}

.tabs-right>.nav-tabs {
	float: right;
	margin-left: 19px;
	border-left: 1px solid #ddd;
}

.tabs-right>.nav-tabs>li>a {
	margin-left: -1px;
	-webkit-border-radius: 0 4px 4px 0;
	-moz-border-radius: 0 4px 4px 0;
	border-radius: 0 4px 4px 0;
}

.tabs-right>.nav-tabs>li>a:hover, .tabs-right>.nav-tabs>li>a:focus {
	border-color: #eeeeee #eeeeee #eeeeee #dddddd;
}

.tabs-right>.nav-tabs .active>a, .tabs-right>.nav-tabs .active>a:hover,
	.tabs-right>.nav-tabs .active>a:focus {
	border-color: #ddd #ddd #ddd transparent;
	*border-left-color: #ffffff;
}
</style>
<script>
function getContextPath() {
    var ctx = window.location.pathname,
        path = '/' !== ctx ? ctx.substring(0, ctx.indexOf('/', 1) + 1) : ctx;
    path = path + (/\/$/.test(path) ? '' : '/')+"${userpath}";
    return  path = path + (/\/$/.test(path) ? '' : '/');
}

$(document).ready(function () {
	    $('.nav li a').click(function(e) {

	        $('.nav li').removeClass('active');

	        var $parent = $(this).parent();
	        if (!$parent.hasClass('active')) {
	            $parent.addClass('active');
	        }
	      //  e.preventDefault();
	    });
	    
	   $('.navbar').css('background','#556B2F')
	   
	   $(function(){
			$(".dropdown-menu > li > a.trigger").on("click",function(e){
				var current=$(this).next();
				var grandparent=$(this).parent().parent();
				if($(this).hasClass('left-caret')||$(this).hasClass('right-caret'))
					$(this).toggleClass('right-caret left-caret');
				grandparent.find('.left-caret').not(this).toggleClass('right-caret left-caret');
				grandparent.find(".sub-menu:visible").not(current).hide();
				current.toggle();
				e.stopPropagation();
			});
			$(".dropdown-menu > li > a:not(.trigger)").on("click",function(){
				var root=$(this).closest('.dropdown');
				root.find('.left-caret').toggleClass('right-caret left-caret');
				root.find('.sub-menu:visible').hide();
			});
		});
});
</script>
</head>
<body>

	<nav class="navbar navigation  navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar"></button>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li><a href="${context}/home">Home</a></li>
					<li><a href="${context}/${userpath}/insuranceList">Insurance</a></li>
					<li><a href="${context}/${userpath}/providerList">Provider</a></li>
					<li><a href="${context}/${userpath}/membershipList">Membership</a></li>
					<li class="dropdown"><a href="#" data-toggle="dropdown"
						role="button" aria-haspopup="true" aria-expanded="false">Admin
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu">

							<li class="dropdown"><a href="#"
								class="trigger  dropdown-toggle" data-toggle="dropdown"
								role="button" aria-haspopup="true" aria-expanded="false">Quality
									Measures <span class="right-caret"></span>
							</a>
								<ul class="dropdown-menu sub-menu">
									<li><a href="${context}/${userpath}/cpt/cptMeasureList">CPT</a></li>
									<li><a
										href="${context}/${userpath}/hedis/hedisMeasureList">Hedis</a></li>
									<li><a href="${context}/${userpath}/icd/icdMeasureList">ICD</a></li>
									<li role="presentation" class="divider"></li>
									<li><a href="${context}/${userpath}/problemList">Problem
											List</a></li>
									<li role="presentation" class="divider"></li>
									<li><a
										href="${context}/${userpath}/hedisMeasureRule/hedisMeasureRuleList">Hedis
											Rule</a></li>
								</ul></li>

							<li class="dropdown"><a href="#"
								class="trigger  dropdown-toggle" data-toggle="dropdown"
								role="button" aria-haspopup="true" aria-expanded="false">Lookups
									<span class="right-caret"></span>
							</a>
								<ul class="dropdown-menu sub-menu">
									<li><a href="${context}/${userpath}/hedisMeasureGroupList">Hedis
											Group</a></li>
									<li><a href="${context}/${userpath}/planTypeList">Plan
											Type</a></li>
									<li><a href="${context}/${userpath}/fileTypeList">File
											Type</a></li>
									<li><a href="${context}/${userpath}/roomTypeList">Room
											Type</a></li>
									<li><a href="${context}/${userpath}/frequencyTypeList">Frequency
											Type</a></li>
									<li><a href="${context}/${userpath}/billTypeList">Bill
											Type</a></li>
									<li><a href="${context}/${userpath}/hospitalList">Hospitals</a></li>
									<li><a href="${context}/${userpath}/attPhysicianList">ATT
											Physician</a></li>
								</ul></li>

							<li class="dropdown"><a href="#"
								class="trigger  dropdown-toggle" data-toggle="dropdown"
								role="button" aria-haspopup="true" aria-expanded="false">Emails
									<span class="right-caret"></span>
							</a>
								<ul class="dropdown-menu sub-menu">
									<li><a href="${context}/${userpath}/emailTemplateList">Email
											Templates</a></li>
									<li><a href="${context}/${userpath}/emailList">Emails</a></li>
								</ul></li>
							<li><a href="${context}/${userpath}/userList">User
									Accounts</a></li>
							<li><a href="${context}/${userpath}/fileUpload">File
									Uploader</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Reports <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="${context}/${userpath}/reports/generateReport?insId=2&reportFormat=pdf">Dashboard</a></li>
							<li><a href="${context}/${userpath}/reports/membershipList?insId=2&reportFormat=pdf">MembershipStatusCountPerProvider</a></li>
							<li><a href="${context}/${userpath}/reports/hedis">Hedis</a></li>
							<li><a href="${context}/${userpath}/reports/hospitalization">Hospitalization</a></li>
							<li>
							<li class="dropdown"><a href="#"
								class="trigger  dropdown-toggle" data-toggle="dropdown"
								role="button" aria-haspopup="true" aria-expanded="false">Claims
									<span class="right-caret"></span>
							</a>
								<ul class="dropdown-menu sub-menu">
									<li><a href="${context}/${userpath}/reports/claim">All
											Claims</a></li>
									<li><a href="${context}/${userpath}/reports/mamClaim">Wanted/Unwanted
											Claims</a></li>
								</ul></li>
							<li><a href="${context}/${userpath}/reports/problem">Problems</a></li>
							<li><a
								href="${context}/${userpath}/reports/membershipActivityMonthList">Activity
									Month</a></li>
									<li><a
								href="${context}/${userpath}/reports/medicalLossRatioList">MLR
									</a></li>
						</ul></li>

					<li><a href="#">Alerts <span class="badge"> 0</span></a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a onclick="return false;"> <span
							class="glyphicon glyphicon-user"></span> ${username}
					</a></li>
					<li><a href="${context}/logout"><span
							class="glyphicon glyphicon-log-out"></span> Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>
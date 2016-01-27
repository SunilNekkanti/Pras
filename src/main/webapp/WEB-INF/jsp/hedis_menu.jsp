
	<script type="text/javascript">
	$(function(){
		$('.nav a').filter(function(){return this.href==location.href}).parent().addClass('active').siblings().removeClass('active')
		$('.nav a').click(function(){
			$(this).parent().addClass('active').siblings().removeClass('active')	
		})
	})
	</script>


<link rel="stylesheet" href="/Pras/resources/css/side_menu.css">
<div class="row">
  <div class="col-sm-12 side-menu">
    <div class="sidebar-nav">
      <div class="navbar navbar-default" role="navigation">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <span class="visible-xs navbar-brand">Sidebar menu</span>
        </div>
        <div class="navbar-collapse collapse sidebar-navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="/Pras/hedis/${id}/display">Hedis Measure Details<span class="glyphicon glyphicon-play"></span></a></li>
            <li><a href="/Pras/hedis/${id}/detailsList">HCC Sub Group Details</a></li>
            <li><a href="/Pras/hedis/${id}/contactList">Contact Details</a></li>
             <li><a href="/Pras/hedis/${id}/problem">Problem Details</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>
 </div>
 </div> 
	<script type="text/javascript">
	$(function(){
		$('.nav a').filter(function(){return this.href==location.href}).parent().addClass('active').siblings().removeClass('active')
		$('.nav a').click(function(){
			$(this).parent().addClass('active').siblings().removeClass('active')	
		})
	})
	</script>
 <ul class="nav nav-tabs nav-stacked">
  <li role="presentation" class="active"><a href="/Pras/provider/${id}/display">Edit Provider Details</a></li>
  <li role="presentation"><a href="/Pras/provider/${id}/contactList">Provider Contact List</a></li>
  <li role="presentation"><a href="/Pras/provider/${id}/contractList">Provider Contract List</a></li>
</ul>

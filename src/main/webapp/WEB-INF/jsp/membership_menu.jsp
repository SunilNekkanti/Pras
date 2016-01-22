
	<script type="text/javascript">
	$(function(){
		$('.nav a').filter(function(){return this.href==location.href}).parent().addClass('active').siblings().removeClass('active')
		$('.nav a').click(function(){
			$(this).parent().addClass('active').siblings().removeClass('active')	
		})
	})
	</script>
 <ul class="nav nav-tabs nav-stacked">
  <li role="presentation" class="active"><a href="/Pras/membership/${id}/display">Profile</a></li>
  <li role="presentation"><a href="/Pras/membership/${id}/detailsList">Insurance</a></li>
  <li role="presentation"><a href="/Pras/membership/${id}/contact">Contact</a></li>
  <li role="presentation"><a href="/Pras/membership/${id}/problem">Problem</a></li>
</ul>
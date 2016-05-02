<%@  page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@  taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">


	<div
		class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
		<form:form action="loginform.do" commandName="loginForm">
			<fieldset>
				<h2>Please Sign In</h2>

				<c:if test="${not empty error}">
					<div class="error">
						<h3 class="text-danger">
							${error}
						</h3>
					</div>
				</c:if>
				<c:if test="${not empty msg}">
					<div class="msg">
						<h3 class="text-success">
							${msg}
						</h3>
					</div>
				</c:if>

				<hr class="colorgraph">
				<div class="form-group">
					<form:input path="username" class="form-control input-lg"
						placeholder="User Name" />
				</div>
				<div class="form-group">
					<form:password path="password" class="form-control input-lg"
						placeholder="Password" />
				</div>
				<span class="button-checkbox">
					<button type="button" class="btn" data-color="info">Remember
						Me</button> <input type="checkbox" name="remember_me" id="remember_me"
					checked="checked" class="hidden"> <a href=""
					class="btn btn-link pull-right">Forgot Password?</a>
				</span>
				<hr class="colorgraph">
				<div class="row">
					<div class="col-xs-6 col-sm-6 col-md-6">
						<input type="submit" class="btn btn-lg btn-success btn-block"
							value="Sign In">
					</div>
					<div class="col-xs-6 col-sm-6 col-md-6">
						<a href="" class="btn btn-lg btn-primary btn-block">Register</a>
					</div>
				</div>
			</fieldset>
		</form:form>
	</div>
</div>

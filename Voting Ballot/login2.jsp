<!DOCTYPE HTML>
<html>
<head>
<%@ include file="head.jsp"%>
</head>
<body>

	<!-- Header -->
	<div id="header">

		<!-- Logo -->
		<h1>
			<a href="index.jsp" id="logo">BBMP|ONLINE VOTING BALLOT</em></a>
		</h1>

		<!-- Nav -->
		<nav id="nav">
			<ul>
			</ul>
		</nav>

	</div>

	<!-- Main -->
	<section class="wrapper style1">
		<div class="container">


			<div>
				<%
					String res = request.getParameter("res");
					if (res != null && res.equals("fail")) {
				%>
				<h3>
					Invalid Credentials. Please <a href='login.jsp'>login again </a>
				</h3>
				<%
					} else {
				%>
				<header>
					<h2>One Time Password</h2>
					<p>An OTP has been generated and has been mailed to your
						registered email ID. Please enter the PIN below.</p>
				</header>
				<form action='login2' method=post id='reg'>
					<input type=text name='otp' placeholder='One Time Password'
						style='font-family: verdana; letter-spacing: 7px;' /><br> <br>
					<input type=submit value='LOGIN' /> <br> <br>
				</form>

				<%
					}
				%>
			</div>


			<!-- Gigantic Heading -->
			<section class="wrapper style2">
				<div class="container">
					<header class="major">
						<h2>Nandi Institute of Technology</h2>
						<p>Dept of CSE, Final year project work 2015</p>
					</header>
				</div>
			</section>




		</div>

	</section>
<%@include file='footer.jsp'%>
</body>
</html>

<script>
	$(document).ready(function() {
		$('#reg').validate({
			rules : {
				'otp' : 'required'
			},
			messages : {

				'otp' : 'Please enter the OTP you received in your e mail'

			},
			submitHandler : function(form) {
				return true;
			}
		});
	});
</script>
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
			<a href="index.jsp" id="logo">Voting Server - Election Commission
				Portal </a>
		</h1>
		<!-- Nav -->
		<nav id="nav">
			<ul>
				<li><a href="index.jsp">Home</a></li>

				<li class="current"><a href="login.jsp">Login</a></li>
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
					Invalid Credentials. Please <a href='login.jsp'>try again </a>
				</h3>
				<%
					} else if (res != null && res.equals("pending")) {
				%>
				<h3>EC Admin haven't approved your application yet. You will
					not be able to access this portal by this time.</h3>
				<%
					} else if (res != null && res.equals("rejected")) {
				%>
				<h3>Your application has been Rejected/ Disabled by EC Admin.
					You will not be able to access this portal anymore.</h3>
				<%
					} else if (res != null && res.equals("otpfail")) {
				%>
				<h3>
					OTP Verification failed. Please <a href='login.jsp'>login
						again.</a>
				</h3>

				<%
					} else {
				%>
				<header>
					<h2>Login</h2>
					<p>Please provide the details below.</p>
				</header>
				<form action='login' method=post id='reg'>
					<label> Enter your username: </label> <input type=text name='email' placeholder='EC username'/><br>
					<label>Enter password:</label> <input type=password name='pw' placeholder='EC password'/> <br>
					<input type=submit value='LOGIN' /> <br> <br>
				</form>

				<%
					}
				%>
			</div>



		</div>

	</section>

	<%@include file='footer.jsp'%>
</body>
</html>

<script>
	$(document).ready(function() {
		$('#reg').validate({
			rules : {
				'email' : 'required',
				'pw' : 'required'
			},
			messages : {

				'email' : 'Please enter your username',
				'pw' : 'Please enter your password'

			},
			submitHandler : function(form) {
				return true;
			}
		});
	});
</script>
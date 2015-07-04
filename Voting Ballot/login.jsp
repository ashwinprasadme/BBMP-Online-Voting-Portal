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
				<li><a href="index.jsp">Home</a></li>
				<li><a href="about.jsp">About</a></li>

				<li class="current"><a href="">Access</a>

					<ul>
						<li><a href="login.jsp">Login</a></li>
						<li><a href="register.jsp">Register</a></li>


						<!-- 						<li><a href="">Submenu</a> -->
						<!-- 							<ul> -->
						<!-- 								<li><a href="#">Lorem dolor</a></li> -->
						<!-- 								<li><a href="#">Phasellus magna</a></li> -->
						<!-- 								<li><a href="#">Magna phasellus</a></li> -->
						<!-- 								<li><a href="#">Etiam nisl</a></li> -->
						<!-- 								<li><a href="#">Veroeros feugiat</a></li> -->
						<!-- 							</ul></li> -->
					</ul></li>
				<li><a href="dev.jsp">Developers</a></li>
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
					<label> Enter your email: </label> <input type=text name='email' /><br>
					<label>Enter password:</label> <input type=password name='pw' /> <br>
					<input type=submit value='PROCEED >>' /> <br> <br>
				</form>

				<%
					}
				%>
			</div>


			<!-- Gigantic Heading -->
			<section class="wrapper style2">
				<div class="container">
					<header class="major">
						<h2>Nandi Insitute of Technology</h2>
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
				'email' : {
					'required' : true,
					'email' : true
				},
				'pw' : 'required'
			},
			messages : {

				'email' : {
					'required' : 'Please enter your email address',
					'email' : 'Invalid e-mail ID'
				},
				'pw' : 'Please enter your password'

			},
			submitHandler : function(form) {
				return true;
			}
		});
	});
</script>
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
				<h3>Something went wrong. Please try again later.</h3>
				<%
					} else if (res != null && res.equals("new")) {
				%>
				<h3>EC Admin haven't approved your application yet. You will
					not be able to access this portal by this time.</h3>
				<%
					} else if (res != null && res.equals("rejected")) {
				%>
				<h3>Your application has been disabled by EC Admin. You will
					not be able to access this portal anymore.</h3>
				<%
					} else if (res != null && res.equals("approved")) {
				%>
				<h3>
					You have already been registered and your application is approved.
					You can login <a href='login.jsp'>here</a>.
				</h3>
				<%
					}
				%>
				<header>
					<h2>Voter Registration Form</h2>
					<p>All the fields are mandatory. .</p>
				</header>
				<form action='register' method=post id='reg'>
					<label> Enter your email: </label> <input type=text name='email' /><br>

					<label>Enter password:</label> <input type=password name='pw' /> <br>

					<label>Enter First name: </label> <input type=text name='fn' /><br>

					<label>Enter Last name:</label> <input type=text name='ln' /> <br>
					<label>Select Gender:</label> <select name='gen'>
						<option value='Male'>Male</option>
						<option value='Female'>Female</option>
					</select> <br> <br> <label>Enter Date of Birth</label> <input
						type=date name='dob' /> <br> <br> <label>Enter
						Mobile number</label> <input type=text name='mobile' /> <br> <label>
						Chose your assembly </label> <select name='assembly'>
						<option value="North">North</option>
						<option value="South">South</option>
					</select> <br> <br> <input type=submit value='PROCEED >>' /> <br>
					<br>
				</form>
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
				'email' : {
					'required' : true,
					'email' : true
				},
				'fn' : 'required',
				'ln' : 'required',
				'pw' : 'required',
				'dob' : 'required',
				'mobile' : 'required',
				'assembly' : 'required',
				'gen' : 'required'
			},
			messages : {

				'email' : {
					'required' : 'Please provide your email address',
					'email' : 'Invalid e-mail ID'
				},
				'pw' : 'Please enter a password',
				'fn' : 'Please provide your first name',
				'ln' : 'Please provide your last name',
				'dob' : 'Please chose your date of birth',
				'mobile' : 'Please provide your mobile number',
				'assembly' : 'Please select your assembly',
				'gen' : 'Please select your gender'

			},
			submitHandler : function(form) {
				return true;
			}
		});
	});
</script>
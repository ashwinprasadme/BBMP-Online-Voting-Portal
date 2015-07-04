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
				<li class="current"><a href="welcome.jsp">Home</a></li>

				<li><a href="requests">Pending Requests</a></li>
				<li><a href="parties">Parties</a></li>
				<li><a href="electionList">Election</a></li>
				<li><a href="resultList">Analytics</a></li>

			</ul>
		</nav>

	</div>
	<div class='container'>
		<%
			String loggedin = (String) request.getSession().getAttribute(
					"loggedIn");
			if (loggedin != null) {
		%>
		<h3>
			Welcome EC Admin. -- <a href='logout'> Logout</a>
		</h3>
		<%
			} else {
				response.sendRedirect("login.jsp?res=fail");
			}
		%>
	</div>
	<!-- Main -->
	<section class="wrapper style1">
		<div class="container">


			<div></div>


			<section class="wrapper style2">
				<div class="container">
					<header class="major">
						<h2>Welcome to the Election Commission Portal</h2>
						<p>We provide you the power to manage everything.</p>
					</header>
				</div>
			</section>

			<section id="cta" class="wrapper style3">
				<div class="container">
					<header>
						<h2>Manage new requests</h2>
						<a href="requests" class="button">Proceed</a>
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
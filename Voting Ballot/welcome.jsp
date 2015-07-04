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
				<li class='current'><a href="welcome.jsp">Home</a></li>
				<li><a href="about.jsp">About</a></li>
				<li><a href='electionList'>Election</a></li>
				<li><a href='resultList'>Result</a></li>
				<li><a href="dev.jsp">Developers</a></li>
			</ul>
		</nav>

	</div>
	<div class='container'>
		<%
			String email = (String) request.getSession().getAttribute("email");
			if (email != null) {
		%>
		<h3>
			Welcome
			<%=email%>. -- <a href='logout'> Logout</a>
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




			<!-- Gigantic Heading -->
			<section class="wrapper style2">
				<div class="container">
					<header class="major">
						<h2>Nandi Institute of Technology</h2>
						<p>Final year project work, 2015</p>
					</header>
				</div>
			</section>




		</div>

	</section>

	<%@include file='footer.jsp'%>
</body>
</html>
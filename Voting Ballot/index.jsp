<!DOCTYPE HTML>
<html>
<head>
<%@include file='head.jsp'%></head>
<body>

	<!-- Header -->
	<div id="header">

		<!-- Logo -->
		<h1>
			<a href="index.jsp" id="logo">BBMP|ONLINE VOTING BALLOT</a>
		</h1>

		<!-- Nav -->
		<nav id="nav">
			<ul>
				<li class="current"><a href="index.jsp">Home</a></li>
				<li><a href="about.jsp">About</a></li>

				<li><a href="">Access</a>

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

	<!-- Banner -->
	<section id="banner">
		<header>
			<h2>
				An Online Voting System: <em>Providing a strong Internet
					Security</em>
			</h2>
			<a href="about.jsp" class="button">Learn More</a>
		</header>
	</section>

	<!-- Highlights -->
	<section class="wrapper style1">
		<div class="container">
			<div class="row 200%">
				<section class="4u 12u(narrower)">
					<div class="box highlight">
						<i class="icon major fa-paper-plane"></i>
						<h3>Faster Response</h3>
						<p>The voting server application is hosted on JBoss clustered
							environemnt which is capable of balancing the load among
							themselves thus providing the ballet with faster response</p>
					</div>
				</section>
				<section class="4u 12u(narrower)">
					<div class="box highlight">
						<i class="icon major fa-pencil"></i>
						<h3>Cast your Vote</h3>
						<p>Voting ballet is developed using responsive design
							framework and it lets you to vote from any device of your choice.
							A laptop, desktop, mobile or a tablet.</p>
					</div>
				</section>
				<section class="4u 12u(narrower)">
					<div class="box highlight">
						<i class="icon major fa-wrench"></i>
						<h3>Manage Profile</h3>
						<p>Full control on your profile is given to you so that you
							can update it whenever you wish.</p>
					</div>
				</section>
			</div>

			<!-- CTA -->
			<section id="cta" class="wrapper style3">
				<div class="container">
					<header>
						<h2>Are you ready to get Started?</h2>
						<a href="register.jsp" class="button">Register</a>
					</header>
				</div>
			</section>




		</div>
	</section>


	<%@include file='footer.jsp'%>
</body>
</html>
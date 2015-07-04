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

				<%
					String email = (String) request.getSession().getAttribute("email");
					if (email != null) {
				%>
				<li><a href="welcome.jsp">Home</a></li>
				<li class='current'><a href="about.jsp">About</a></li>
				<li><a href='electionList'>Election</a></li>
				<li><a href='resultList'>Result</a></li>

				<li><a href="dev.jsp">Developers</a></li>

				<%
					} else {
				%>
				<li><a href="index.jsp">Home</a></li>
				<li class="current"><a href="about.jsp">About</a></li>

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
				<%
					}
				%>
			</ul>
		</nav>

	</div>
	<div class='container'>
		<%
			if (email != null) {
		%>
		<h3>
			Welcome
			<%=email%>. -- <a href='logout'> Logout</a>
		</h3>
		<%
			}
		%>
	</div>

	<!-- Main -->
	<section class="wrapper style1">
		<div class="container">
			<div class="row 200%">
				<div class="4u 12u(narrower)">
					<div id="sidebar">

						<!-- Sidebar -->

						<section>
							<h3>Abstract</h3>
							<p>We provide a practical application of the cryptographic
								schemes and digital signature that ensures integrity of the vote
								cast by voter and authentication of voter. Also, an One Time
								Password (OTP) verification scheme to ensure that the voter is
								genuine. We also implement the load balancer in JBoss
								environment .</p>
						</section>

						<section>

							<footer>
								<a href="register.jsp" class="button">Get Started</a>
							</footer>
						</section>

					</div>
				</div>
				<div class="8u  12u(narrower) important(narrower)">
					<div id="content">

						<!-- Content -->

						<article>
							<header>
								<h2>Voting Ballet - Client side</h2>
								<p>High level design diagram.</p>
							</header>

							<span class="image featured"><img src="images/client.jpg"
								alt="" width=100 /></span>

							<p>Nowadays every things is becoming online, so human
								tendency has changed; they try to do every things from home
								using Internet. Election is also becoming online. But if we make
								voting system online the security is major concern. In this
								project, we propose a web based Internet voting system where we
								are providing security to vote when it is travelling from voting
								client to voting server. Our main tool is the concept of
								multiple encryption and decryption. .</p>

							<h3>Introduction</h3>
							<p>Election and voting are well known things in modern days
								of Democracy. Electronic online voting over the Internet would
								be much more profitable. Many voters would appreciate the
								possibility of voting from anywhere. A company having their
								offices in different locations, can use internet voting for
								their election, in their employees from alloffices will take
								part in election from their own offices. Electronic voting, as
								the name implies, is the voting process held over electronic
								media, i.e. computers. In general, such internet voting system
								should satisfy such requirements as follows:
							<ol>
								<li>Accuracy</li>
								<li>Simplicity</li>
								<li>Democracy</li>
								<li>Verifiability</li>
								<li>Privacy</li>
								<li>Security</li>

							</ol>
							For such an internet voting system, security and privacy are main
							concerns. From that point of view, an implementation of secure
							Internet voting system appears to be another application of
							cryptography and network security. Electronic voting has been
							intensively studied for over the last twenty years. Many e-voting
							system, therefore, have been proposed in the last several decades
							and both the security as well as the effectiveness has been
							improved. Nevertheless, to the best of our knowledge, no
							practical and complete solution has been found for large scale
							elections over a network, say Internet.
							</p>
						</article>

					</div>
				</div>
			</div>
		</div>
	</section>

	<%@include file='footer.jsp'%></body>
</html>
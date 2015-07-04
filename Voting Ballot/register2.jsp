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

				<li class="current"><a href="">Access</a></li>
				<li><a href="dev.jsp">Developers</a></li>
			</ul>
		</nav>

	</div>

	<!-- Main -->
	<section class="wrapper style1">
		<div class="container">


			<div>
				<header>
					<h2>Identity Proof Submission</h2>
					<p>Please upload the scanned copy. .</p>
				</header>


				<%
					String chk = request.getParameter("res");
					if (chk != null && chk.equals("done")) {
				%>
				<h3>Successfully uploaded the ID Proof. Your application is
					under review. You will be able to access this portal once the
					Election Commision approves your application.</h3>
				<%
					} else if (chk != null && chk.equals("fail")) {
				%>
				<h3>Error uploading the document. Please provide us the valid
					document.</h3>
				<%
					} else {
				%>

				<form action='register2' method=post enctype="multipart/form-data"
					id='reg'>


					<label> Upload the Identity proof document (Pan card,
						Passport, or Adhaar card) </label> <input type=file name='doc'
						class='form-control' /> <br> <br> <input type=submit
						value='UPLOAD' /> <br> <br>
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
				'doc' : 'required'
			},
			messages : {
				'doc' : 'Please chose a file'

			},
			submitHandler : function(form) {
				return true;
			}
		});
	});
</script>
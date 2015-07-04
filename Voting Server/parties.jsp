<!DOCTYPE HTML>
<%@page import="com.aklc.pojo.User"%>
<%@page import="java.util.List"%>
<html>
<head>
<%@ include file="head.jsp"%>

<link href="css/cdn.css" rel='stylesheet' type="text/css" />
<script type="text/javascript" src="js/cdn.js"></script>
</head>
<body>

	<%
		String c = (String) session.getAttribute("loggedIn");
			if (c==null || !c.equals("yes")) {
		response.sendRedirect("index.jsp");
			}
	%>
	<!-- Header -->
	<div id="header">

		<!-- Logo -->
		<h1>
			<a href="welcome.jsp" id="logo">Voting Server - Election
				Commission Portal </a>
		</h1>
		<!-- Nav -->
		<nav id="nav">
			<ul>
				<li><a href="welcome.jsp">Home</a></li>

				<li><a href="requests">Pending Requests</a></li>
				<li class="current"><a href="parties">Parties</a></li>


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

		<div class='container'>

			<div class='row'>
				<div class='five columns'>
					<h3>Add a New Party</h3>
					<form action='addParty' method=post enctype="multipart/form-data"
						id='reg'>
						<label> Enter the party name : </label> <input type=text
							name='name' placeholder='Party name' /> <br /> <label>
							Upload the party logo : </label> <input type=file name='logo' /> <br />
						<br /> <input type=submit value='ADD PARTY' /> <br /> <br />

					</form>
				</div>

				<div class='two columns'></div>

				<div class='five columns'>
					<%
						List<String> result = (List<String>)  request.getAttribute("parties");
						if (result == null) {
					%>
					<h3>Something went wrong. Unable to pull the parties at this
						time.</h3>
					<%
						} else if (result.size()==0) {
					%>
					<h3>No Parties have been registered at this time.</h3>
					<%
						} else {
					%>
					<h3>Registered Parties</h3>

					<table id='req'>
						<thead>
							<tr align="left">
								<th>Party name</th>
								<th>Logo</th>
								<th>Action</th>
							</tr>
						</thead>

						<tbody>
							<%
								for (String p : result) {
							%>
							<tr valign="top">
								<td><%=p%></td>
								<td><img width=100
									src='http://ashvtu-ashvtu.rhcloud.com/vS/getImage?partylogo=true&name=<%=p%>' />
								</td>
								<td><a class='button alt'
									href='deleteParty?name=<%=p%>'>Delete Party</a></td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
					<%
						}
					%>



				</div>
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

		$('#req').dataTable({
			"pagingType" : "full_numbers"
		});

	});
</script>
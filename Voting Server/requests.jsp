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
		String chk = (String)session.getAttribute("loggedIn");
		if (chk==null || !chk.equals("yes"))
			response.sendRedirect("index.jsp");
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

				<li class="current"><a href="requests">Pending Requests</a></li>
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
			<%
				List<User> result = (List<User>)  request.getAttribute("result");
												if (result == null) {
			%>
			<h3>Something went wrong. Unable to pull the requests at this
				time.</h3>
			<%
				} else if (result.size()==0) {
			%>
			<h3>No Requests at this time.</h3>
			<%
				} else {
			%>
			<table id='req'>
				<thead>
					<tr align="left">
						<th>Assembly</th>
						<th>Email</th>
						<th>First name</th>
						<th>Last name</th>
						<th>Details</th>
					</tr>
				</thead>

				<tbody>
					<%
						for (User u : result) {
					%>
					<tr>
						<td><%=u.getAssembly()%></td>
						<td><%=u.getEmail()%></td>
						<td><%=u.getFname()%></td>
						<td><%=u.getLname()%></td>
						<td><a href='reqDetails?email=<%=u.getEmail()%>' class='button'>View
								Details</a></td>
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
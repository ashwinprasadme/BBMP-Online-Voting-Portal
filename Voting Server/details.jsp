<!DOCTYPE HTML>
<%@page import="com.aklc.pojo.User"%>
<%@page import="java.util.List"%>
<html>
<head>
<%@ include file="head.jsp"%>
</head>
<body>

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
				<li><a href="resultList">Result</a></li>

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
				String chk = request.getParameter("res");
				if (chk != null && chk.equals("fail")) {
			%>
			<h3>Something went wrong. .</h3>
			<%
				}
				User user = (User) request.getAttribute("details");
				if (user == null) {
			%>
			<h3>Something went wrong. Unable to pull the details at this
				time.</h3>
			<%
				} else {
			%>

			<table>

				<tr>
					<td></td>
					<td>

						<table>
							<tr>
								<td><form action='approve' method='post'>
										<input type=hidden name='email' value='<%=user.getEmail()%>' />
										<input type=submit value='APPROVE' />
									</form></td>

								<td>
									<form action='reject' method='post'>
										<input type=hidden name='email' value='<%=user.getEmail()%>' />
										<input type=submit value='REJECT' class='button alt'/>
									</form>
								</td>
							</tr>
						</table> <br> <br>
					</td>
				</tr>

				<tr>
					<td>Assembly <br />
						<h3><%=user.getAssembly()%>
						</h3> Email ID <br />
						<h3><%=user.getEmail()%>
						</h3> First Name <br />
						<h3><%=user.getFname()%>
						</h3> Last Name <br />
						<h3><%=user.getLname()%>
						</h3> Gender <br />
						<h3><%=user.getGender()%></h3> Date of Birth <br />
						<h3><%=user.getDob()%>
						</h3> Mobile <br />
						<h3><%=user.getMobile()%>
						</h3></td>

					<td>ID Proof <br /> <br /> <img
						src='http://ashvtu-ashvtu.rhcloud.com/vS/getImage?email=<%=user.getEmail()%>'
						width=600 />

					</td>
				</tr>
			</table>


			<%
				}
			%>
		</div>

	</section>

	<%@include file='footer.jsp'%>
</body>
</html>


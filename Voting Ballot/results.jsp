\<%@page import="java.util.*,com.aklc.pojo.*,java.text.*"%>
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
				<li><a href="about.jsp">About</a></li>
				<li><a href='electionList'>Election</a></li>
				<li class='current'><a href='resultList'>Result</a></li>

				<li><a href="dev.jsp">Developers</a></li>

				<%
					} else {
				%>
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
			} else {
				response.sendRedirect("login.jsp?res=fail");
			}
		%>
	</div>

	<section class="wrapper style1">

		<div class='container'>

			<div class='row'>

				<div class='twelve columns'>
					<%
						List<Result> result = (List<Result>) request.getAttribute("res");
																														if (result == null) {
					%>
					<h3>Something went wrong. Unable to pull the results this
						time.</h3>
					<%
						} else if (result.size() == 0) {
					%>
					<h3>No Elections found.</h3>
					<%
						} else {
					%>
					<h3>
						Results -
						<%=request.getAttribute("assembly")%>
						-
						<%=request.getAttribute("desc")%>, Held on
						<%=request.getAttribute("cdate")%></h3>

					<table id='req'>
						<thead>
							<tr align="left">
								<th>Party</th>
								<th>Candidate name</th>
								<th>Symbol</th>
								<th>Vote count</th>
								<th></th>

							</tr>
						</thead>

						<tbody>
							<%
								int i=0;
																		for (Result	r : result) {
																		if (i==0) {
							%>

							<tr valign="top" style='background-color: lightgreen;'>
								<td style='border: solid black 1px;'><b><%=r.getPname()%></b></td>
								<td style='border: solid black 1px;'><b><%=r.getName()%></b></td>
								<td
									style='padding-top: 40px; background-color: white; border: solid black 1px;'
									valign="middle"><img width=50
									src='http://ashvtu-ashvtu.rhcloud.com/vS/getImage?partylogo=true&name=<%=r.getPname()%>' /></td>
								<td style='border: solid black 1px;'><b><%=r.getVotecount()%></b></td>
								<td style='border: solid black 1px;'><b>CONGRATULATIONS</b></td>
							</tr>

							<%
								i++;
								} else {
							%>
							<tr valign="top" style='background-color: lightgray;'>
								<td style='border: solid black 1px;'><%=r.getPname()%></td>
								<td style='border: solid black 1px;'><%=r.getName()%></td>
								<td
									style='padding-top: 40px; background-color: white; border: solid black 1px;'
									valign="middle"><img width=50
									src='http://ashvtu-ashvtu.rhcloud.com/vS/getImage?partylogo=true&name=<%=r.getPname()%>' /></td>

								<td style='border: solid black 1px;'><%=r.getVotecount()%></td>
								<td style='border: solid black 1px;'></td>
							</tr>

							<%
								}
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

		$('#req').dataTable({
			"pagingType" : "full_numbers"
		});

	});
</script>
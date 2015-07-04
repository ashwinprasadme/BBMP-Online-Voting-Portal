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
						List<Election> result = (List<Election>) request.getAttribute("res");
														List<Integer> history = (List<Integer>) request.getAttribute("history");
															if (result == null) {
					%>
					<h3>Something went wrong. Unable to pull the elections list at
						this time.</h3>
					<%
						} else if (result.size() == 0) {
					%>
					<h3>No Elections have been registered yet.</h3>
					<%
						} else {
					%>
					<h3>Results</h3>

					<table id='req'>
						<thead>
							<tr align="left">
								<th>ID</th>
								<th>Description</th>
								<th>Assembly</th>
								<th>Commence Date</th>
								<th>Start Time</th>
								<th>End Time</th>

								<th>Status</th>
								<th>Action</th>

							</tr>
						</thead>

						<tbody>
							<%
								for (Election e : result) {
							%>
							<tr valign="top">
								<td><%=e.getId()%></td>
								<td><%=e.getDescp()%></td>
								<td><%=e.getAssembly()%></td>
								<td>
									<%
										SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");
																	SimpleDateFormat sdf2 = new SimpleDateFormat("hh : mm");
									%> <%=sdf.format(e.getCommencedate())%></td>
								<td><%=sdf2.format(e.getBegintime())%></td>
								<td><%=sdf2.format(e.getEndtime())%></td>
								<td><%=e.getState()%></td>
								<td>
									<%
										if (e.getState().equals("NEW")) {
									%> ELECTION NOT STARTED YET <%
										} else if (e.getState().equals("LIVE")) {
									%> VOTING IN PROGRESS <%
										} else if (e.getState().trim().equals("END")) {
									%> <a href='results?eid=<%=e.getId()%>&desc=<%=e.getDescp()%>&assembly=<%=e.getAssembly()%>&cdate=<%=sdf.format(e.getCommencedate())%>' class='button'>VIEW RESULTS</a> <%
 	}
 %>

								</td>
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

		$('#req').dataTable({
			"pagingType" : "full_numbers"
		});

	});
</script>
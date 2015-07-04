<!DOCTYPE HTML>
<%@page import="com.aklc.pojo.Result"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.aklc.pojo.Election"%>
<%@page import="com.aklc.pojo.User"%>
<%@page import="java.util.List"%>
<html>
<head>
<%@ include file="head.jsp"%>

<link href="css/cdn.css" rel='stylesheet' type="text/css" />
<script type="text/javascript" src="js/cdn.js"></script>
<script src="js//jquery.datetimepicker.js"></script>
<link rel="stylesheet" type="text/css"
	href="css/jquery.datetimepicker.css" />
<style type="text/css">
.custom-date-style {
	background-color: red !important;
}
</style>

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
				<li><a href="parties">Parties</a></li>


				<li><a href="electionList">Election</a></li>
				<li class="current"><a href="resultList">Analytics</a></li>

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
		$('#reg').validate({
			rules : {
				'descp' : 'required',
				'assembly' : 'required',
				'commencedate' : 'required',
				'begintime' : 'required',
				'endtime' : 'required',
			},
			messages : {

				'descp' : 'This is a mandatory field',
				'assembly' : 'This is a mandatory field',
				'commencedate' : 'This is a mandatory field',
				'begintime' : 'This is a mandatory field',
				'endtime' : 'This is a mandatory field',

			},
			submitHandler : function(form) {
				return true;
			}
		});

		$('#commencedate').datetimepicker({
			lang : 'en',
			timepicker : false,
			format : 'd/m/Y',
			formatDate : 'Y/m/d',
		//			minDate : '-1970/01/02', // yesterday is minimum date
		//maxDate : '+1970/01/02' // and tommorow is maximum date calendar
		});
		$('#begintime').datetimepicker({
			datepicker : false,
			format : 'H:i',
			step : 5
		});

		$('#endtime').datetimepicker({
			datepicker : false,
			format : 'H:i',
			step : 5
		});

		$('#req').dataTable({
			"pagingType" : "full_numbers"
		});

	});
</script>
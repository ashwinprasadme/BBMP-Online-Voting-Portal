<!DOCTYPE HTML>
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


				<li class="current"><a href="electionList">Election</a></li>
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

				<div class='twelve columns'>
					<%
						List<Election> result = (List<Election>)  request.getAttribute("res");
															if (result == null) {
					%>
					<h3>Something went wrong. Unable to pull the elections list at
						this time.</h3>
					<%
						} else if (result.size()==0) {
					%>
					<h3>No Elections have been registered yet.</h3>
					<%
						} else {
					%>
					<h3>Elections List</h3>

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
									%> <a class='button alt'
									href='deleteElection?id=<%=e.getId()%>'>CANCEL</a> <a
									class='button' href='participants?eid=<%=e.getId()%>'>PARTICIPANTS</a>
									<%
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

			<div class='row'>
				<div class='five columns'>
					<h3>Schedule a new election</h3>
					<form action='addElection' method=post id='reg'>
						<label> Enter the Description : </label> <input type=text
							name='descp' placeholder='Description of the new election' /> <br />
						<label> Chose the assembly </label> <select name='assembly'>
							<option value="North">North</option>
						<option value="South">South</option>
						</select> <br /> <br /> <label> Chose the election commence date
						</label> <input type="text" name='commencedate' id='commencedate'
							placeholder='Election commencement date' /> <br /> <label>
							Chose the election start time </label> <input type=text name='begintime'
							id='begintime' placeholder='Election start time' /><br> <label>
							Chose the election end time </label> <input type=text name='endtime'
							placeholder='Election end time' id='endtime' /><br> <br />
						<input type=submit value='SCHEDULE ELECTION' /> <br /> <br />

					</form>
				</div>

				<div class='two columns'></div>
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
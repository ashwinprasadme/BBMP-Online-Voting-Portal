<!DOCTYPE HTML>
<%@page import="com.aklc.pojo.Participants"%>
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

				<div class='five columns'>
					<%
						List<Participants> result = (List<Participants>)  request.getAttribute("res");
					if (result == null) {
					%>
					<h3>Something went wrong. Unable to pull the participants list
						at this time.</h3>
					<%
						} else if (result.size()==0) {
					%>
					<h3>No Participants have been added to this election yet.</h3>
					<%
						} else {
					%>
					<%
						String eid = (String) request.getAttribute("eid"); 
					%>
					<h3>
						Election ID :
						<%=eid%></h3>

					<h3>Participants List</h3>

					<table id='req'>
						<thead>
							<tr align="left">
								<th>Sl No</th>
								<th>Candidate Name</th>
								<th>Party Name</th>
								<th>Party Logo</th>
								<th>Action</th>

							</tr>
						</thead>

						<tbody>
							<%
								for (Participants p : result) {
							%>
							<tr valign="top">
								<td><%=p.getSlno()%></td>
								<td><%=p.getName()%></td>
								<td><%=p.getPartyName()%></td>
								<td><img width=100
									src='http://ashvtu-ashvtu.rhcloud.com/vS/getImage?partylogo=true&name=<%=p.getPartyName()%>' /></td>
								<td><a href='removeParticipant?eid=<%=eid %>&name=<%=p.getName() %>&pname=<%=p.getPartyName() %>' class='button alt'> REMOVE</a></td>
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

				<div class='two columns'></div>
				<div class='five columns'>
					<h3>Add a Participant for this Election</h3>
					<form action='addParticipant' method=post id='reg'>
						<input type=hidden name='eid'
							value='<%=(String) request.getAttribute("eid")%>' /> <label>
							Sl No. : </label> <input type=text name='slno'
							placeholder='Serial Number' /> <br /> <label> Enter the
							candidate's name </label> <input type=text name='name'
							placeholder='Candidate Name' /> <br /> <label> Chose
							the Party for which the candidate belong to </label> <select name='pname'>

							<%
								List<String> pnames = (List<String>) request.getAttribute("pnames");
																		for (String pname : pnames) {
							%>
							<option value='<%=pname%>'><%=pname%>
							</option>
							<%
								}
							%>
						</select> <br /> <br /> <input type=submit value='ADD' /> <br /> <br />

					</form>
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
				'slno' : 'required',
				'name' : 'required',
				'pname' : 'required'
			},
			messages : {

				'slno' : 'This is a mandatory field',
				'name' : 'This is a mandatory field',
				'pname' : 'This is a mandatory field'

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
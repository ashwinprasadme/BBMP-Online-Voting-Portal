<%@page import="com.sun.mail.iap.Literal"%>
<%@page import="java.util.*,com.aklc.pojo.*,java.text.*"%>
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
			<a href="index.jsp" id="logo">Voting Ballot</em></a>
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
				<li class='current'><a href='electionList'>Election</a></li>
				<li><a href='resultList'>Result</a></li>

				<li><a href="dev.jsp">Developer</a></li>

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

				<div class='12u  20u(narrower) important(narrower)'>

					<%
						String res = (String) request.getAttribute("res");
						if (res!=null && res.equals("done")) {
					%>
					<section class="wrapper style2">
						<div class="container">
							<header class="major">
								<h2>CONGRATULATIONS. You have successfully casted your
									vote.</h2>
								<p>Thanks for voting. Results will be soon available. Watch
									out for RESULTS section.</p>
							</header>
						</div>
					</section>

					<%
						} else if (res!=null && res.equals("fail")) {
					%>
					<section class="wrapper style2">
						<div class="container">
							<header class="major">
								<h2>APOLOGIES. There was a problem while submitting your
									vote to the server.</h2>
								<p>
									<a href=electionList> Click here </a>to vote again.
								</p>
							</header>
						</div>
					</section>

					<%
						} else {
					%>

					<%
						List<Participants> participants = (List<Participants>) request.getAttribute("participants");
						if (participants==null) {
					%>
					<h3>Something went wrong. Unable to pull the elections list at
						this time.</h3>
					<%
						} else if (participants.size() == 0) {
					%>
					<h3>No participants available for this Election.</h3>
					<%
						} else {
					%>
					<h3>Vote Now</h3>
					<h4><%=request.getAttribute("assembly")%>-<%=request.getAttribute("desc")%></h4>
					<table style='width: 75%; border: 5px;'>
						<thead>
							<tr height=100
								style='font-weight: bold; background-color: #333; color: white;'
								valign="middle">
								<th style='padding-top: 40px;'>Sl. No</th>
								<th>Candidate Name</th>
								<th>Party</th>
								<th>Symbol</th>
								<th>Vote</th>
							</tr>
						</thead>
						<tbody>
							<%
								int i=0;
								for (Participants p : participants) {
								i++;
							%>
							<tr align="center" valign="middle" height=100 style=''>
								<td valign="middle"><%=p.getSlno()%></td>
								<td valign="middle"><%=p.getName()%></td>
								<td valign="middle"><%=p.getPartyName()%></td>
								<td style='padding-top: 40px;' valign="middle"><img
									width=100
									src='http://ashvtu-ashvtu.rhcloud.com/vS/getImage?partylogo=true&name=<%=p.getPartyName()%>' /></td>
								<td>
									<%
										if ( !((String)session.getAttribute("assembly")).equals( (String)request.getAttribute("assembly")))  {
									%> <%
 	} else {
 %>
									<form action='vote' method=post id='voteform'
										onsubmit='return reqKeys("<%=session.getAttribute("email")%>", <%=i%>, <%=participants.size()%>); '>
										<input type=hidden name='slno' value='<%=p.getSlno()%>' /> <input
											type=hidden name='eid' value=' <%=p.getEid()%>' /> <input
											type=hidden name='name' value='<%=p.getName()%>' /> <input
											type=hidden name='pname' value='<%=p.getPartyName()%>' /> <input
											type=hidden name='assembly'
											value='<%=request.getAttribute("assembly")%>' /> <input
											type=submit class='button' value='Vote' id='votebtn<%=i%>' />
										<img src='images/loading.gif' id='loading<%=i%>' width=100
											style='display: none;' /> <input type=hidden name='priv'
											id='priv<%=i %>' value='' />
									</form> <%
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
	
	function reqKeys(email, i, n) {
		
		var res;
		
		$('#votebtn'+i).hide();
		
		var req = $.ajax({
		
			'url': 'http://www.vtuproject2015.com/vS/reqKeys',
			'method': 'POST',
			'async' : false,
			'data' : {'email':email},
			'beforeSend' : function(data) {
				for (var k=1;k<=n;k++)
					$('#votebtn'+k).hide();
				$('#loading'+i).show();
				alert("You will be receiving an email to your registered ID with the signing key. You will be prompted to enter it here. Please make sure you do not copy the white spaces. ")
			},
			
			'success' : function(data) {
				var privKey = prompt("Please Enter the Signing key received in a mail.");
				$('#priv'+i).val(privKey);
				res=true;
			},
			
			'error' : function(xhr, textStatus, errorThrown) {
				alert("Something went wrong. Please try again later");
				res=false;
			}
			
		});
		
	
		
		return res;
	}
</script>
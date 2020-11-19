
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.parser.JSONParser" %>
<%@ page import="org.json.simple.parser.ParseException" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Ubay - User</title>

    <%@include file="parts/resources.jsp"%>
    <link rel="stylesheet" href="/css/user/user.css">
</head>
<body>
<%@include file="parts/header_bar.jsp"%>

<div class="main_container">
    <%
        JSONObject jsonUser = null;
        JSONParser jp = new JSONParser();
        try {
            jsonUser = (JSONObject) jp.parse(request.getAttribute("user").toString());
        } catch (ParseException ignored) {}
    %>
    <div class="card">
        <h3>User information</h3>
        <div class="main_info">
            <a id="avatar" href="<%=jsonUser.get("avatar").toString()%>" target="_blank">
                <img src="<%=jsonUser.get("avatar").toString()%>" alt="user avatar">
            </a>
            <span><%
                out.println(jsonUser.get("login").toString());
            %></span>
        </div>
        <div id="info-role" class="secondary_info">
            <div class="secondary_info-item">
                <span class="title">Role:</span>
                <span class="value">
                    <%
                        if (jsonUser.get("role").toString().equals("2")) {
                            out.println("bidder");
                        } else {
                            out.println("auctioneer");
                        }
                    %>
                </span>
            </div>
        </div>
        <button id="btn_auctions" class="btn">Look for auctions</button>
    </div>
</div>

<%@include file="parts/scripts.jsp"%>
</body>
</html>
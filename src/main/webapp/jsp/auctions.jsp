<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ubay - Auctions</title>

    <%@include file="parts/resources.jsp" %>
    <link rel="stylesheet" href="css/auctions/auctions.css">
</head>
<body>
<%@include file="parts/header_bar.jsp"%>

<div class="main_container">
    <h2 id="title-my_auctions" class="title_text">My auctions</h2>
    <div class="title_line"></div>

    <div id="auctions-container" class="auctions-items">
        <a href="http://localhost:8080/create_auction">
            <div class="auction-item card add_auction">
                + Create new auction
            </div>
        </a>
<%--        <div class="auction-item card">--%>
<%--            <img src="resources/test.png" alt="auction-1">--%>
<%--            <div class="info_block">--%>
<%--                <div class="title">Wolf coins collection - Limited edition (America 2005)</div>--%>
<%--                <div class="price">9.30</div>--%>
<%--            </div>--%>
<%--            <div class="control_block">--%>
<%--                <button class="btn">edit</button>--%>
<%--                <div class="bids_label">Bids:</div>--%>
<%--                <div class="bids_count">5</div>--%>
<%--            </div>--%>
<%--        </div>--%>
    </div>
</div>


<script defer src="js/auctions/getMyAuctions.js"></script>
<script defer src="js/auctions/generate_auctions.js"></script>
<%@include file="parts/scripts.jsp"%>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ubay - Auctions</title>

    <%@include file="parts/resources.jsp" %>
    <link rel="stylesheet" href="/css/auctions/auctions.css">
    <link rel="stylesheet" href="/css/parts/auction_list.css">
    <script src="/js/parts/ref_token.js"></script>
</head>
<body>
<%@include file="parts/header_bar.jsp"%>

<div class="main_container">
    <h2 id="title-my_auctions" class="title_text">My auctions</h2>
    <div class="title_line"></div>

    <div id="auctions-container" class="auctions-items">
        <a class="add_auction_a" href="http://localhost:8080/create_auction">
            <div class="card add_auction">
                + Create new auction
            </div>
        </a>
        <div class="toggle-container">
            <label id="label-all" class="checkbox_input" onclick="onAuctionAll()">
                <input type="checkbox" id="checkbox-all">
                All
            </label>
            <label id="label-notActive" class="checkbox_input" onclick="onStatus()">
                <input type="checkbox" id="checkbox-notActive">
                Not active
            </label>
            <label id="label-active" class="checkbox_input" onclick="onStatus()">
                <input type="checkbox" id="checkbox-active">
                Active
            </label>
            <label id="label-finished" class="checkbox_input" onclick="onStatus()">
                <input type="checkbox" id="checkbox-finished">
                Finished
            </label>
        </div>
    </div>
</div>


<script src="/js/parts/ref_token.js"></script>
<script defer src="/js/auctions/getMyAuctions.js"></script>
<script defer src="/js/auctions/generate_auctions.js"></script>
<%@include file="parts/scripts.jsp"%>
</body>
</html>

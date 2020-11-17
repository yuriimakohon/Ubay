<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ubay - Bids</title>

    <script defer src="/js/http_requests/bid.js"></script>
    <script defer src="/js/bids/get_my_bids.js"></script>
    <script defer src="/js/bids/generate_bids.js"></script>

    <%@include file="parts/resources.jsp" %>
    <link rel="stylesheet" href="/css/bids/bids.css">
</head>
<body>
<%@include file="parts/header_bar.jsp"%>

<div class="main_container">
    <h2 id="title-my_bids" class="title_text">My bids</h2>
    <div class="title_line"></div>

    <div id="bids-container">

<%--        <div class="bid-item card">--%>
<%--            <img src="/resources/test2.jpeg" alt="bid-" onclick="onAuction()">--%>
<%--            <div class="info_block">--%>
<%--                <div class="title" onclick="onAuction()">Wolsdaqewrqwerwerqrqoqwerwerqwerqo werqoqwerwerqwe erqowejrkefaldskjflka;sdfjlk</div>--%>
<%--                <div class="price">24</div>--%>
<%--            </div>--%>
<%--            <div class="control_block">--%>
<%--                <button class="btn" onclick="onRebid()">Rebid</button>--%>
<%--                <button class="btn btn-red" onclick="onBidDelete()">Delete</button>--%>
<%--            </div>--%>
<%--        </div>--%>

    </div>
</div>

<%@include file="parts/scripts.jsp"%>
</body>
</html>

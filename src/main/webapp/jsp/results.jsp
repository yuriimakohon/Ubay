<html>
<head>
    <meta charset="utf-8">
    <title>Ubay - Search results</title>

    <%@include file="parts/resources.jsp"%>
    <link rel="stylesheet" href="/css/results/results.css">
    <link rel="stylesheet" href="/css/parts/auction_list.css">
</head>
<body>
<%@include file="parts/header_bar.jsp"%>

<div class="main_container">
    <h2 id="title-search_results" class="title_text">Search results</h2>
    <div class="title_line"></div>
<%--    <div class="tag-container">--%>
<%--        <div class="tag">Collectibles</div>--%>
<%--        <div class="tag">Collectibles</div>--%>
<%--        <div class="tag">Collectibles</div>--%>
<%--    </div>--%>
    <div id="auctions-container" class="auctions-items">
    </div>
</div>

<%@include file="parts/scripts.jsp"%>
<script defer src="/js/results/getAuctions.js"></script>
<script defer src="/js/results/generate_auctions.js"></script>
</body>
</html>

<html>
<head>
    <meta charset="utf-8">
    <title>Ubay - Search results</title>

    <%@include file="parts/resources.jsp"%>
    <link rel="stylesheet" href="/css/results/results.css">
    <link rel="stylesheet" href="/css/parts/auction_list.css">
    <script src="/js/parts/checkbox_select.js"></script>
</head>
<body>
<%@include file="parts/header_bar.jsp"%>

<div class="main_container">
    <h2 id="title-search_results" class="title_text">Search results</h2>
    <div class="title_line"></div>
    <div id="checkbox-container" class="auctions-items">
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
    <div id="auctions-container" class="auctions-items">
    </div>
</div>

<%@include file="parts/scripts.jsp"%>
<script defer src="/js/results/getAuctions.js"></script>
<script defer src="/js/results/generate_auctions.js"></script>
</body>
</html>

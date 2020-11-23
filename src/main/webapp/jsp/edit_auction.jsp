<html>
<head>
    <meta charset="utf-8">
    <title>Ubay - Edit auction</title>

    <%@include file="parts/resources.jsp"%>
    <link rel="stylesheet" href="css/create_auction/create_auction.css">

    <script src="js/editAuction/loadAuction.js"></script>

    <script src="js/http_requests/auction.js"></script>
    <script defer src="js/create_auction/datetime.js"></script>
    <script defer src="js/create_auction/info_text.js"></script>
</head>
<body>
<%@include file="parts/header_bar.jsp"%>

<div class="main_container">
    <h2 id="title-create_auction" class="title_text">Edit auction</h2>
    <div class="title_line"></div>

    <div class="card">
        <h3>Edit auction lot</h3>
        <div id="info_text" class="hidden"></div>
        <div class="text_input-item">
            <p>Lot title</p>
            <input class="text_input" type="text" id="input-title" placeholder="Auction lot title" maxlength="120">
        </div>
        <div id="description-container" class="text_input-item">
            <p>Lot description</p>
            <textarea id="textarea-description" class="textarea" maxlength="500" content="description" placeholder="Describe in detail the item put up for auction"></textarea>
        </div>
        <div id="categories-container" class="text_input-item">
            <p>Categories</p>
            <div class="checkbox-container">
                <%@include file="parts/checkbox.jsp"%>
            </div>
        </div>
        <div id="start_price-container" class="text_input-item">
            <p>Start price</p>
            <input class="number_input" type="number" id="input-start_price" step="5" min="0" placeholder="0.00">
        </div>
        <div id="max_price-container" class="text_input-item">
            <p>Max price
                <span class="tooltip">Maximum bid that can be placed by bidders</span>
            </p>
            <input class="number_input" type="number" id="input-max_price" step="5" min="5" placeholder="0.00">
        </div>
<%--        <div id="start_time-container" class="text_input-item">--%>
<%--            <p>Start time--%>
<%--                <span class="tooltip">If the time is in the past - the lot is activated immediately</span>--%>
<%--            </p>--%>
<%--            <input id="input-start_time" class="datetime-local-input" type="datetime-local">--%>
<%--        </div>--%>
        <div id="duration-container" class="text_input-item">
            <p>Duration</p>
            <label>
                <input id="range-duration" class="range-input" type="range" min="1" max="10" value="2" onmousemove="onRangeDuration()">
                <p id="p-duration"></p>
            </label>
        </div>
        <div class="btn-container">
            <button id="btn-delete_auction" class="btn btn-red" onclick="onDeleteAuction()">Delete auction</button>
            <button id="btn-save_changes" class="btn" onclick="onSaveChanges()">Save changes</button>
        </div>
    </div>
</div>

<%@include file="parts/scripts.jsp"%>
</body>
</html>

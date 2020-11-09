<html>
<head>
    <meta charset="utf-8">
    <title>Ubay - Create auction</title>

    <%@include file="parts/resources.jsp"%>
    <link rel="stylesheet" href="css/create_auction/create_auction.css">
    <script defer src="js/create_auction/create_auction.js"></script>
    <script defer src="js/create_auction/datetime.js"></script>
</head>
<body>
    <%@include file="parts/header_bar.jsp"%>


    <div class="main_container">
        <h2 id="title-create_auction" class="title_text">Create new auction</h2>
        <div class="title_line"></div>

        <div class="card">
            <h3>Create auction lot</h3>
            <div id="info_text" class="hidden"></div>
            <div class="text_input-item">
                <p>Lot title</p>
                <input class="text_input" type="text" id="input-title" placeholder="Auction lot title" maxlength="120">
            </div>
            <div id="description-container" class="text_input-item">
                <p>Lot description</p>
                <textarea id="textarea-description" class="textarea" maxlength="500" content="description" placeholder="Describe in detail the item put up for auction"></textarea>
            </div>
            <div id="start_price-container" class="text_input-item">
                <p>Start price</p>
                <input class="number_input" type="number" step="5" min="0" placeholder="0.00">
            </div>
            <div id="max_price-container" class="text_input-item">
                <p>Max price</p>
                <input class="number_input" type="number" step="5" min="5" placeholder="0.00">
            </div>
            <div id="start_time-container" class="text_input-item">
                <p>Start time</p>
                <input id="input-start_time" class="datetime-local-input" type="datetime-local">
            </div>
            <div id="finish_time-container" class="text_input-item">
                <p>Finish time</p>
                <input class="datetime-local-input" disabled type="datetime-local">
            </div>
            <button id="btn-create_auction" class="btn">Create auction</button>
        </div>
    </div>

<%--            <input type="text" name="Title" id="title_auction" maxlength="25">--%>
<%--            <textarea id="description" name="Description" rows="5" cols="50" maxlength="200" content="description"></textarea>--%>
<%--            <input type="number" name="start_price" id="start_price" min="1">--%>
<%--            <input type="number" name="MaxPrice" id="max_price" min="2">--%>
<%--            <input type="datetime-local" name="StartTime" id="start_time">--%>
<%--            <input type="range" name="Duration" id="duration" min="1" max="6" value="2" step="1">--%>
<%--            <input type="file" name="Image" id="forImage" accept="image" multiple>--%>
<%--        <button type="submit" id="sub">submit</button>--%>

    <%@include file="parts/scripts.jsp"%>
</body>
</html>
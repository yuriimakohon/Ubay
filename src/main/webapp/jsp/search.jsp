<html>
<head>
    <meta charset="utf-8">
    <title>Ubay - Advanced search</title>

    <%@include file="parts/resources.jsp"%>
    <link rel="stylesheet" href="css/search/search.css">
    <script defer src="/js/search/rate.js"></script>
    <script src="js/parts/ref_token.js"></script>
</head>
<body>
<%@include file="parts/header_bar.jsp"%>

<div class="main_container">
    <h2 class="title_text">Advanced search</h2>
    <div class="title_line"></div>

    <div class="card search-card">
        <h3>By text</h3>
        <div class="text-search">
            <label for="input-title">Title</label>
            <input id="input-title" class="text_input" type="text" placeholder="Search auction with title">
        </div>
        <div id="description" class="text-search">
            <label for="input-login">User login</label>
            <input id="input-login" class="text_input" type="text" placeholder="Search auction by user login">
        </div>
    </div>

    <div class="card search-card">
        <h3>By properties</h3>
        <div class="prop-search">
            <h4>Category</h4>
            <div class="prop-search-container">
                <label id="category-collectibles" class="checkbox_input">
                    <input type="checkbox">
                    Collectibles
                </label>
                <label id="category-clothing" class="checkbox_input">
                    <input type="checkbox">
                    Clothing
                </label>
                <label id="category-technics" class="checkbox_input">
                    <input type="checkbox">
                    Technics
                </label>
                <label id="category-furniture" class="checkbox_input">
                    <input type="checkbox">
                    Furniture
                </label>
                <label id="category-household" class="checkbox_input">
                    <input type="checkbox">
                    Household
                </label>
                <label id="category-jewellery" class="checkbox_input">
                    <input type="checkbox">
                    Jewellery
                </label>
            </div>
        </div>
        <div class="prop-search">
            <h4>Auction state</h4>
            <div class="prop-search-container">
                <label id="state-active" class="checkbox_input">
                    <input type="checkbox">
                    Active
                </label>
                <label id="state-finished" class="checkbox_input">
                    <input type="checkbox">
                    Finished
                </label>
                <label id="state-not_started" class="checkbox_input">
                    <input type="checkbox">
                    Not started
                </label>
            </div>
        </div>
        <div class="prop-search">
            <h4>Start price</h4>
            <div class="prop-search-container">
                <label>
                    <input class="number_input" id="start-price" type="number" placeholder="0.00" min="0" step="1">
                </label>
            </div>
        </div>
        <div class="prop-search">
            <h4>Min Rate</h4>
            <div class="prop-search-container">
                <label>
                    <input id="range-rate" class="range-input" type="range" min="1" max="5" value="3" onmousemove="onRangeRate()">
                    <p id="p-rate"></p>
                </label>
            </div>
        </div>
    </div>
    <div class="btn-container">
        <button id="btn-adv_search" class="btn" onclick="search()">Search</button>
    </div>
</div>

<%@include file="parts/scripts.jsp"%>
</body>
</html>
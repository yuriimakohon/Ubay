<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ubay - Catalog</title>

    <link rel="shortcut icon" href="resources/favicon.png" type="image/x-icon">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@100;300;400;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/design/design.css">
    <link rel="stylesheet" href="css/catalog/catalog.css">
</head>
<body>
    <header class="header_bar">
        <div class="header_bar-container">
            <a href="http://localhost:8080/ubay/">
                <img id="header-logo" src="resources/ubay_logo.svg" alt="ubay logo">
            </a>
            <div id="search-container">
                <input class="text_input" id="text_input-search" type="text" placeholder="Let's find">
                <select class="select">
                    <option selected="selected">Category</option>
                </select>
                <button class="btn" id="btn-search">
                    Search
                </button>
                <button class="btn" id="btn-advanced_search" onmouseover="showBtnAdvanceSearch()" onmouseout="hideBtnAdvanceSearch()">
                    >
                </button>
            </div>
            <div class="header_bar-items">
                <div class="header_bar-item">
                    <label>Auctions</label>
                    <img id="img-auction" src="resources/auction.svg" alt="auction">
                </div>
                <div class="header_bar-item">
                    <label>Bids</label>
                    <img id="img-bids" src="resources/bid.svg" alt="bids">
                </div>
                <div class="header_bar-item">
                    <label>Cart</label>
                    <img id="img-cart" src="resources/cart.svg" alt="cart">
                </div>
                <div class="header_bar-item">
                    <label>Account</label>
                    <img id="img-user" src="resources/user.svg" alt="user">
                </div>
            </div>
        </div>
    </header>

    <script src="js/catalog/display.js" type="text/javascript"></script>
</body>
</html>

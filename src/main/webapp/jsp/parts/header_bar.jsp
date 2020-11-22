<header class="header_bar">
    <div class="header_bar-container main_container">
<%--        <a href="http://localhost:8080/">--%>
            <img id="header-logo" src="/resources/ubay_logo.svg" alt="ubay logo" onclick="onForward('/catalog')">
<%--        </a>--%>
        <div id="search-container">
            <input class="text_input" id="text_input-search" type="text" placeholder="Let's find">
            <button class="btn" id="btn-search" onclick="onSearch()">
                Search
            </button>
            <button class="btn" id="btn-advanced_search" onmouseover="showBtnAdvanceSearch()" onmouseout="hideBtnAdvanceSearch()" onclick="onForward('/search')">
                >
            </button>
        </div>
        <div class="header_bar-items">
            <div id="item-auction" class="header_bar-item hidden" onclick="onForward('/auctions')">
                <label>Auctions</label>
                <img id="img-auction" src="/resources/auction.svg" alt="auction">
            </div>
            <div id="item-bids" class="header_bar-item hidden" onclick="onForward('/bids')">
                <label>Bids</label>
                <img id="img-bids" src="/resources/bid.svg" alt="bids">
            </div>
            <div id="item-cart" class="header_bar-item hidden">
                <label>Cart</label>
                <img id="img-cart" src="/resources/cart.svg" alt="cart">
            </div>
            <div id="item-balance" class="header_bar-item hidden" onclick="onForward('/account')">
                <label id="counter-balance"></label>
                <img id="img-balance" src="/resources/dollar.svg" alt="balance">
            </div>
            <div id="item-account" class="header_bar-item hidden" onclick="onForward('/account')">
                <img id="img-account" src="/resources/test2.jpeg" alt="account">
            </div>
            <div id="item-sign_up" class="header_bar-item hidden" onclick="onForward('/authorization')">
                <label>Sign Up</label>
                <img id="img-signup" src="/resources/user.svg" alt="signup">
            </div>
            <script src="/js/parts/header.js"></script>
        </div>
    </div>
</header>

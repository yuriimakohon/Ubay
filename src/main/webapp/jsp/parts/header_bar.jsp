<header class="header_bar">
    <div class="header_bar-container main_container">
        <a href="http://localhost:8080/">
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
            <div class="header_bar-item hidden">
                <label>Auctions</label>
                <img id="img-auction" src="resources/auction.svg" alt="auction">
            </div>
            <div class="header_bar-item hidden">
                <label>Bids</label>
                <img id="img-bids" src="resources/bid.svg" alt="bids">
            </div>
            <div class="header_bar-item hidden">
                <label>Cart</label>
                <img id="img-cart" src="resources/cart.svg" alt="cart">
            </div>
            <div class="header_bar-item hidden">
                <label>19.23</label>
                <img id="img-balance" src="resources/dollar.svg" alt="balance">
            </div>
            <a href="http://localhost:8080/account">
                <div class="header_bar-item hidden">
                    <img id="img-account" src="resources/user.svg" alt="account">
                </div>
            </a>
            <a href="http://localhost:8080/ubay/authorization">
                <div class="header_bar-item">
                    <label>Sign Up</label>
                    <img id="img-signup" src="resources/user.svg" alt="signup">
                </div>
            </a>
        </div>
    </div>
</header>

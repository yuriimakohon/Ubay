<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ubay - Catalog</title>

    <%@include file="parts/resources.jsp"%>
    <link rel="stylesheet" href="css/catalog/catalog.css">
</head>
<body>
    <%@include file="parts/header_bar.jsp"%>

    <div class="categories">
        <h2 class="main_container">Explore categories</h2>
        <div class="categories-background">
            <div class="main_container">
                <div id="categories-container">
                    <div class="categories-item">
                        <img src="resources/category_collectibles.png" alt="category collectibles">
                        <p>Collectibles</p>
                    </div>
                    <div class="categories-item">
                        <img src="resources/category_clothing.png" alt="category clothing">
                        <p>Clothing</p>
                    </div>
                    <div class="categories-item">
                        <img src="resources/category_technics.png" alt="category clothing">
                        <p>Technics</p>
                    </div>
                    <div class="categories-item">
                        <img src="resources/category_furniture.png" alt="category furniture">
                        <p>Furniture</p>
                    </div>
                    <div class="categories-item">
                        <img src="resources/category_household.png" alt="category household">
                        <p>Household</p>
                    </div>
                    <div class="categories-item">
                        <img src="resources/category_jewellery.png" alt="category jewellery">
                        <p>Jewellery</p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="auctions">
        <h2 class="main_container">Popular auctions</h2>
        <div class="block-background ">
            <div class="main_container">
                <div id="auctions-container">
                    <div class="card auctions-item" onclick="onAuction()">
<%--                        <img src="resources/test.png">--%>
                        <div class="text">
                            <div class="title">
                                <p>Wolf coins collection - Limited edition (America 2005)</p>
                            </div>
                            <p class="price">$ 12.50</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="js/parts/parseCookie.js"></script>
    <script src="js/catalog/queries.js"></script>
    <%@include file="parts/scripts.jsp"%>
</body>
</html>

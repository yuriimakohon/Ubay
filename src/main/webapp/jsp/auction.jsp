<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ubay - Auction</title>

    <link rel="stylesheet" href="css/auction/auction.css">
    <%@include file="parts/resources.jsp" %>
</head>
<body>
<%@include file="parts/header_bar.jsp"%>

<div class="main_container">
    <div class="info_blocks-container">
        <div id="info_block-left" class="card">

        </div>

        <div id="info_block-right" class="card">
            <h2 class="lot_title">Wolf coins collection - Limited edition (America 2005)</h2>
            <div class="lot_description">
                Lorem ipsum dolor sit amet, consectetur adipiscing elit,
                sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
                Excepteur sint occaecat cupidatat non proident,
                sunt in culpa qui officia deserunt mollit anim id est laborum.
            </div>
            <div class="additional_info">
                <div class="additional_info-item">
                    <p class="additional_info-item-title">Auctioneer</p>
                    <a>auctioneer_login</a>
                </div>
                <div class="additional_info-item">
                    <p class="additional_info-item-title">Auction rate</p>
                    <div class="rate-container">
                        <img src="resources/star_light.svg" alt="rate">
                        <span>4.2</span>
                    </div>
                </div>
            </div>
            <div class="status status-time">
                <div class="status-container">
                    <p  class="status-title">Auction ends</p>
                    <div class="status_info-container">
                        <p class="status_info-top">12.11.2020</p>
                        <p class="status_info-bottom">00:00:00</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="parts/scripts.jsp"%>
</body>
</html>

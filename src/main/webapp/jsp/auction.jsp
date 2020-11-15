<%@ page import="org.json.simple.parser.JSONParser" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ubay - Auction</title>

    <link rel="stylesheet" href="/css/auction/auction.css">
    <script src="/js/parts/ref_token.js"></script>
    <%@include file="parts/resources.jsp" %>
</head>
<body>
<%@include file="parts/header_bar.jsp" %>

<div class="main_container">
    <div class="info_blocks-container">
        <div id="info_block-left" class="card">
            <div class="photos-view">
                <img class="current-photo-item" src="" alt="current photo">
                <div class="photos-control">
                    <%
                        String path = "/resources/" + request.getAttribute("sellerId") + "/" + request.getAttribute("lotId") + "/";
                        for (int i = 0; i <  Integer.parseInt(request.getAttribute("p_count").toString()); i++)
                            out.println("<img id=\"photo-" + i + "\" class=\"photo-item\" src=\"" + path + i + ".jpg" + "\" alt=\"photo " + i + "\" onclick=\"onPhoto(" + i + ")\">");
                    %>
                </div>
            </div>
            <div id="prices" style="text-align: right">
                <div class="info">
                    <span class="info-title">Bids count:</span>
                    <span id="info-title-bids_count" class="info-value">
                    <%= request.getAttribute("b_count") %>
                </span>
                </div>
                <div class="info">
                    <span class="info-title">Start price:</span>
                    <span id="info-title-start_price" class="info-value">
                    <%= request.getAttribute("price") %>
                </span>
                </div>
                <div class="info">
                    <span class="info-title">Current price:</span>
                    <span id="info-title-current_price" class="info-value">
                    <%= request.getAttribute("bid") %>
                </span>
                </div>
            </div>
            <div id="btn-bid-container">
                <button id="btn-bid" class="btn">Bid</button>
            </div>
        </div>

        <div id="info_block-right" class="card">
            <h2 class="lot_title">
                <%= request.getAttribute("title") %>
            </h2>
            <div class="lot_description">
                <%= request.getAttribute("desc") %>
            </div>
            <div class="additional_info">
                <div class="additional_info-item">
                    <p class="additional_info-item-title">Auctioneer</p>
                    <div id="auctioneer-container">
                        <img id="img-auctioneer" src="/resources/test2.jpeg" alt="auctioneer avatar">
                        <span>auctioneer_login</span>
                    </div>
                </div>
                <div class="additional_info-item">
                    <p class="additional_info-item-title">Auction rate</p>
                    <div class="rate-container">
                        <img src="/resources/star_light.svg" alt="rate">
                        <span>4.2</span>
                    </div>
                </div>
            </div>
            <div class="status status-time">
                <div class="status-container">
                    <p class="status-title">Auction ends</p>
                    <div class="status_info-container">
                        <p class="status_info-top">12.11.2020</p>
                        <p class="status_info-bottom">00:00:00</p>
                    </div>
                </div>
            </div>
            <div id="btn-feedbacks-container">
                <button class="btn">Feedbacks</button>
            </div>
        </div>
    </div>

    <div class="feedbacks">
        <h2 id="title-create_auction" class="title_text">Feedbacks</h2>
        <div class="title_line"></div>

        <div class="feedbacks-container">
            <div class="user-feedback card">
                <h3>Leave feedback</h3>
                <textarea class="textarea" placeholder="Leave your opinion about the auction"
                          maxlength="500"></textarea>
                <div class="user-feedback-control">
                    <div class="feedback-evaluation-container">
                        <img id="star-1" class="evaluation_start evaluation_start-active" src="/resources/start.svg"
                             alt="star" onclick="onStar(1)">
                        <img id="star-2" class="evaluation_start evaluation_start-active" src="/resources/start.svg"
                             alt="star" onclick="onStar(2)">
                        <img id="star-3" class="evaluation_start evaluation_start-active" src="/resources/start.svg"
                             alt="star" onclick="onStar(3)">
                        <img id="star-4" class="evaluation_start evaluation_start-active" src="/resources/start.svg"
                             alt="star" onclick="onStar(4)">
                        <img id="star-5" class="evaluation_start evaluation_start-active" src="/resources/start.svg"
                             alt="star" onclick="onStar(5)">
                        <p id="user-evaluation" class="evaluation">5</p>
                    </div>
                    <button class="btn">Send</button>
                </div>
            </div>
            <div class="feedback card">
                <div class="feedback-info-container">
                    <div class="feedback_author">
                        <img src="/resources/test2.jpeg">
                        <span>user_login</span>
                    </div>
                    <div class="feedback-evaluation-container">
                        <img class="evaluation_start evaluation_start-active" src="/resources/start.svg" alt="star">
                        <img class="evaluation_start evaluation_start-active" src="/resources/start.svg" alt="star">
                        <img class="evaluation_start evaluation_start-active" src="/resources/start.svg" alt="star">
                        <img class="evaluation_start evaluation_start-active" src="/resources/start.svg" alt="star">
                        <img class="evaluation_start evaluation_start-active" src="/resources/start.svg" alt="star">
                    </div>
                </div>
                <p class="feedback-text">
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit,
                    sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                    Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris
                    nisi ut aliquip ex ea commodo consequat.
                </p>
            </div>
        </div>
    </div>
</div>

<script src="/js/auction/switch_photo.js"></script>
<script defer src="/js/auction/evaluation.js"></script>
<%@include file="parts/scripts.jsp" %>
</body>
</html>

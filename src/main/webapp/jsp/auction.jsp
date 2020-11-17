<%@ page import="org.json.simple.parser.JSONParser" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="java.io.Reader" %>
<%@ page import="org.json.simple.parser.ParseException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ubay - Auction</title>

    <link rel="stylesheet" href="/css/auction/auction.css">
    <script src="/js/parts/ref_token.js"></script>
    <script src="/js/auction/request.js"></script>
    <%@include file="parts/resources.jsp" %>
</head>
<body>
<%@include file="parts/header_bar.jsp" %>

<div class="main_container">
     <%
         JSONObject jsonUser = null;
         JSONObject jsonLot = null;
         if (response.getStatus() < 400) {
            JSONParser jp = new JSONParser();
            try {
                jsonLot = (JSONObject) jp.parse(request.getAttribute("lot").toString());
                jsonUser = (JSONObject) jp.parse(request.getAttribute("user").toString());
            } catch (ParseException ignored) {}
         }
     %>
    <div class="info_blocks-container">
        <div id="info_block-left" class="card">
            <div class="photos-view">
                <a href="" target="_blank">
                    <img class="current-photo-item" src="" alt="current photo">
                </a>
                <div class="photos-control">
                    <%
                        assert jsonLot != null;
                        String path = "/resources/" + jsonLot.get("sellerId") + "/" + jsonLot.get("lotId").toString() + "/";
                        for (int i = 0; i < Integer.parseInt(jsonLot.get("photoNumber").toString()); i++)
                            out.println("<img id=\"photo-" + i + "\" class=\"photo-item\" src=\"" + path + i + ".jpg" + "\" alt=\"photo " + i + "\" onclick=\"onPhoto(" + i + ")\">");
                    %>
                </div>
            </div>
            <div id="prices" style="text-align: right">
                <div class="info">
                    <span class="info-title">Bids count:</span>
                    <span id="info-title-bids_count" class="info-value">
                        <%
                            out.println(jsonLot.get("bidNumber").toString());
                        %>
                </span>
                </div>
                <div class="info">
                    <span class="info-title">Start price:</span>
                    <span id="info-title-start_price" class="info-value">
                        <%
                            out.println(jsonLot.get("price").toString());
                        %>
                </span>
                </div>
                <div class="info">
                    <span class="info-title">Current price:</span>
                    <span id="info-title-current_price" class="info-value">
                        <%
                            out.println(jsonLot.get("highestBid").toString());
                        %>
                </span>
                </div>
            </div>
            <div id="btn-bid-container">
                <button id="btn-show_bid" class="btn" onclick="onShowBid()">Bid</button>
            </div>
            <div id="bid-container" class="hidden">
                <label id="your_bid">
                    Your bid:
                    <input id="input-bid" class="number_input" type="number" placeholder="0.00" step="1" min="<%out.print(Float.parseFloat(jsonLot.get("highestBid").toString()) + 1);%>">
                </label>
                <div class="btn_bids-container">
                    <button class="btn btn-red" onclick="onCancelBid()">
                        cancel
                    </button>
                    <button class="btn" onclick="onPlaceBid(<%out.print(Float.parseFloat(jsonLot.get("lotId").toString()) + ", " +  Float.parseFloat(jsonLot.get("highestBid").toString()));%>)">
                        place a bid
                    </button>
                </div>
            </div>
        </div>

        <div id="info_block-right" class="card">
            <h2 class="lot_title">
                <%
                    out.println(jsonLot.get("title").toString());
                %>
            </h2>
            <div class="lot_description">
                <%
                    if (response.getStatus() < 400) {
                        out.println(jsonLot.get("description").toString());
                    }
                    assert jsonUser != null;%>
            </div>
            <div class="additional_info">
                <div class="additional_info-item">
                    <p class="additional_info-item-title">Auctioneer</p>
                    <div id="auctioneer-container" onclick="onAuctioneerContainer(<%=jsonUser.get("id").toString()%>)">
                        <%
                            if (response.getStatus() < 400) {
                                out.println("<img id=\"img-auctioneer\" src=\"" + jsonUser.get("avatar").toString() + "\" alt=\"auctioneer avatar\"");
                            }
                        %>
                        <span>
                            <%
                                out.println(jsonUser.get("login").toString());
                            %>
                        </span>
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
                <%
                    out.println("<button class=\"btn\" onclick=\"onFeedbacks(" + jsonLot.get("lotId").toString() + ")\">Feedbacks</button>");
                %>
<%--                <button class="btn" onclick="onFeedbacks()">Feedbacks</button>--%>
            </div>
        </div>
    </div>

    <div class="feedbacks hidden">
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
<script src="/js/auction/bid.js"></script>
<script defer src="/js/auction/evaluation.js"></script>
<script defer src="/js/auction/feedbacks.js"></script>
<%@include file="parts/scripts.jsp"%>
</body>
</html>

<%@ page import="org.json.simple.parser.JSONParser" %>
<%@ page import="org.json.simple.JSONObject" %>
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
         JSONObject jsonBid = null;
         JSONObject jsonBidder = null;
         if (response.getStatus() < 400) {
            JSONParser jp = new JSONParser();
            try {
                jsonLot = (JSONObject) jp.parse(request.getAttribute("lot").toString());
                jsonUser = (JSONObject) jp.parse(request.getAttribute("userSeller").toString());
                jsonBid = (JSONObject) jp.parse(request.getAttribute("bid").toString());
                if (request.getAttribute("userBidder") != null) {

                    jsonBidder = (JSONObject) jp.parse(request.getAttribute("userBidder").toString());
                }
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
            <div id="info_text" class="hidden">Error</div>
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
                            if (jsonBid.get("ok").toString().equals("true")) {
                                out.println(jsonBid.get("price").toString());
                            } else {
                                out.println("0.0");
                            }
                        %>
                </span>
                </div>
            </div>
            <div id="btn-bid-container" class="hidden <%
                    if (jsonLot.get("status").toString().equals("3")) {
                        out.println("end");
                    }
                %>">
                <%
                    if (!jsonLot.get("status").toString().equals("1") && !jsonLot.get("status").toString().equals("3")) {
                        out.println("<button id=\"btn-show_bid\" class=\"btn\" onclick=\"onShowBid()\">Bid</button>");
                    }
                %>
            </div>
            <div id="bid-container" class="hidden">
                <label id="your_bid">
                    Your bid:
                    <input id="input-bid" class="number_input" type="number" placeholder="0.00" step="1" min="<%
                            if (jsonBid.get("ok").toString().equals("true")) {
                                out.print(Float.parseFloat(jsonBid.get("price").toString()) + 1);
                            } else if (jsonBid.get("ok").toString().equals("false")) {
                                out.print(Float.parseFloat(jsonLot.get("price").toString()) + 1);
                            } else {
                                out.print(1);
                            }%>">
                </label>
                <div class="btn_bids-container">
                    <button class="btn btn-red" onclick="onCancelBid()">
                        cancel
                    </button>
                    <button class="btn" onclick="onPlaceBid(<%
                            if (jsonBid.get("ok").equals("true")) {
                                out.print(Float.parseFloat(jsonLot.get("lotId").toString()) + ", " +  Float.parseFloat(jsonBid.get("price").toString()));
                            } else if (jsonBid.get("ok").equals("false")) {
                                out.print(Float.parseFloat(jsonLot.get("lotId").toString()) + ", " +  Float.parseFloat(jsonLot.get("price").toString()));
                            } else {
                                out.print(Float.parseFloat(jsonLot.get("lotId").toString()) + ", 0");}%>)">
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
                    <div id="auctioneer-container" onclick="onUser(<%=jsonUser.get("id").toString()%>)">
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
                        <span> <%out.println(String.format("%.1f", Float.parseFloat(jsonLot.get("rate").toString())));%></span>
                    </div>
                </div>
            </div>
            <%
                int status = Integer.parseInt(jsonLot.get("status").toString());

                // status color
                if (status != 3) {
                    out.println("<div class=\"status status-time\">");
                } else {
                    out.println("<div class=\"status status-winner\">");
                }

                // status title
                out.println("<div class=\"status-container\"><p class=\"status-title\">");
                if (status == 1)
                    out.println("Auction starts");
                else if (status == 2)
                    out.println("Auction ends");
                else
                    out.println("Auction ended");
                out.println("</p><div class=\"status_info-container\"><p class=\"status_info-top\">");

                //  status info Top
                if (status != 3)
                    out.println("In:");
                else if (!jsonLot.get("bidNumber").toString().equals("0")) {
                    out.println(jsonBidder.get("login"));
                } else {
                    out.println("Without bids");
                }

                // status info Bottom

                out.println("</p><p id=\"status_info-bottom\" class=\"status_info-bottom\"");
                if (status != 3) {
                    if (status == 1)
                        out.println("data=\"" + jsonLot.get("startTime").toString() + "\">");
                    else
                        out.println("data=\"" + jsonLot.get("duration").toString() + "\">");
                    %><script src="/js/auction/setTimer.js"></script><%
                } else if (!jsonLot.get("bidNumber").toString().equals("0")) {
                    out.print("\">" + jsonBid.get("price").toString() + " $");
                }
                out.println("</p>");
            %>
                    </div>
                </div>
            </div>
            <div id="btn-feedbacks-container">
                <%
                    out.println("<button class=\"btn\" onclick=\"onFeedbacks(" + jsonLot.get("lotId").toString() + ")\">Feedbacks</button>");
                %>
            </div>
        </div>
    </div>

    <div class="feedbacks hidden">
        <h2 id="title-create_auction" class="title_text">Feedbacks</h2>
        <div class="title_line"></div>

        <div class="all-feedbacks-container">
            <%
                if (request.getAttribute("canFeedback").toString().equals("true"))
                    out.println("<div class=\"user-feedback card\">");
                else
                    out.println("<div class=\"user-feedback hidden card\">");
            %>
                <h3>Leave feedback</h3>
                <textarea id="textarea_feedback" class="textarea" placeholder="Leave your opinion about the auction" maxlength="500"></textarea>
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
                    <button class="btn" onclick="onSendFeedback()">Send</button>
                </div>
            </div>
            <div id="feedbacks-container"></div>
        </div>
    </div>
</div>

<script src="/js/auction/switch_photo.js"></script>
<script src="/js/auction/bid.js"></script>
<script defer src="/js/auction/evaluation.js"></script>
<script defer src="/js/auction/feedbacks.js"></script>
<script defer src="/js/auction/info_text.js"></script>
<script defer src="/js/auction/onSendFeedback.js"></script>

<script defer src="/js/auction/onPageLoad.js"></script>

<%@include file="parts/scripts.jsp"%>
</body>
</html>

<html>
<head>
    <meta charset="utf-8">
    <title>Ubay - User</title>

    <link rel="stylesheet" href="/css/user/user.css">
    <%@include file="parts/resources.jsp"%>
</head>
<body>
<%@include file="parts/header_bar.jsp"%>

<div class="main_container">
    <div class="card">
        <h3>User information</h3>
        <div class="main_info">
            <a id="avatar" href="/resources/test2.jpeg" target="_blank">
                <img src="/resources/test2.jpeg" alt="user avatar">
            </a>
            <span>User_login</span>
        </div>
        <div class="secondary_info">
            <div class="secondary_info-item">
                <span class="title">Role:</span>
                <span class="value">auctioneer</span>
            </div>
            <div class="secondary_info-item">
                <span class="title">AVG rate:</span>
                <span class="value">
                    <div class="rate">
                        <img src="/resources/star_light.svg" alt="rate">
                        <span>4.2</span>
                    </div>
                </span>
            </div>
        </div>
        <button class="btn">Look for auctions</button>
    </div>
</div>

<%@include file="parts/scripts.jsp"%>
</body>
</html>
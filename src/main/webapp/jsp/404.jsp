<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ubay - page not found</title>

    <%@include file="parts/resources.jsp" %>
</head>
<body>
    <%@include file="parts/header_bar.jsp"%>

    <div class="main_container" style="margin-top: 20%; display: flex; flex-flow: column; align-items: center;">
        <a href="http://localhost:8080/">
            <img src="/resources/ubay_logo.svg" alt="logo" style="height: 200px; margin-bottom: 50px;">
        </a>
        <h1 style="color: #0087E9; font-weight: 500">
            <p style="font-size: 64px; font-weight: 400; text-align: center">
                <%=request.getAttribute("javax.servlet.error.status_code")%>
            </p>
            <%=request.getAttribute("javax.servlet.error.request_uri") + " - "%>
            <%="Page not found"%>
        </h1>
    </div>

    <%@include file="parts/scripts.jsp"%>
</body>
</html>

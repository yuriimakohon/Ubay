<html>
<head>
    <meta charset="utf-8">
    <title>Ubay - Advanced search</title>

    <%@include file="parts/resources.jsp"%>
    <link rel="stylesheet" href="css/search/search.css">
</head>
<body>
<%@include file="parts/header_bar.jsp"%>

<div class="main_container">
    <h2 class="title_text">Advanced search</h2>
    <div class="title_line"></div>

    <div class="card search-card">
        <h3>By text</h3>
        <div class="text-search">
            <label for="input-title">Title</label>
            <input id="input-title" class="text_input" type="text" placeholder="Search auction with title">
            <button id="btn-title_search" class="btn">search</button>
        </div>
        <div id="description" class="text-search">
            <label for="input-description">Description</label>
            <input id="input-description" class="text_input" type="text" placeholder="Search auction with description">
            <button id="btn-description_search" class="btn">search</button>
        </div>
    </div>

    <div class="card search-card">
        <h3>By properties</h3>

    </div>
</div>

<%@include file="parts/scripts.jsp"%>
</body>
</html>
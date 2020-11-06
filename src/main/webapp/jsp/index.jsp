<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ubay - Catalog</title>

    <link rel="shortcut icon" href="resources/favicon.png" type="image/x-icon">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@100;300;400;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/design/design.css">
    <link rel="stylesheet" href="css/catalog/catalog.css">
</head>
<body>
    <header class="header-bar">
        <img id="header-logo" src="resources/ubay_logo.svg" alt="ubay logo">
        <div id="search-container">
            <input class="text_input" id="text_input-search" type="text" placeholder="Let's find">
            <select class="select">
                <option selected="selected">Choose category</option>
            </select>
            <button class="btn" id="btn-search">
                Search
            </button>
            <button class="btn" id="btn-advanced_search" onmouseover="showBtnAdvanceSearch()" onmouseout="hideBtnAdvanceSearch()">
                >
            </button>
        </div>
    </header>
    <div id="main_container">

    </div>

    <script src="js/catalog/display.js" type="text/javascript"></script>
</body>
</html>

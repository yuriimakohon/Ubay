<html>
  <head>
      <meta charset="utf-8">
      <title>ubay - create auctions</title>
      <link rel="shortcut icon" href="resources/favicon.png" type="image/x-icon">
      <link rel="stylesheet" href="css/create_auctions/create_auction.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
      <script src="js/loadFiles/loadFiles.js"></script>
    </head>
    <body>
      <div id="master">
        <label for="title_auction">title auction:</label>
            <input type="text" name="Title" id="title_auction" maxlength="25">
                <span id="errorTitle" style="color: darkred">No valid title</span>
        <label for="description">description:</label>
            <textarea id="description" name="Description" rows="5" cols="50" maxlength="200" content="description"></textarea>
                <span id="errorDescription" style="color: darkred">No valid description</span>
        <label for="start_price">start price:</label>
            <input type="number" name="start_price" id="start_price" min="1">
                <span id="errorStartPrice" style="color: darkred">Enter one or more</span>
        <label for="max_price">max price:</label>
            <input type="number" name="MaxPrice" id="max_price" min="2">
                <span id="errorMaxPrice" style="color: darkred">Enter more than start price</span>
        <label for="start_time">start time:</label>
            <input type="datetime-local" name="StartTime" id="start_time">
                <span id="errorStartTime" style="color: red">Enter start time</span>
                <span id="errorStartTime2" style="color: red">Enter start time more than curr time + 60 sec</span>
        <label for="duration">duration(day):</label>
            <input type="range" name="Duration" id="duration" min="1" max="6" value="2" step="1">
        <label for="forImage">images of goods:</label>
            <input type="file" name="Image" id="forImage" accept="image" multiple>
        <button type="submit" id="sub">submit</button>
        <script src="js/create_auctions/create_auction.js"></script>
    </div>
  </body>
</html>

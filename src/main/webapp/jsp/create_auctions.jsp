<html>
  <head>
      <meta charset="utf-8">
      <title>ubay - create auctions</title>
      <link rel="shortcut icon" href="resources/favicon.png" type="image/x-icon">
      <link rel="stylesheet" href="css/create_auctions/create_auctions.css">
    </head>
    <body>
      <div id="master">
        <label for="title_auctions">title auction:</label>
        <input type="text" name="Title" id="title_auction" maxlength="25">
        <label for="description">description:</label>
        <input type="text" name="Description" id="description" maxlength="200">
        <label for="start_price">start price:</label>
        <input type="number" name="start_price" id="start_price" min="1">
        <label for="max_price">max price:</label>
        <input type="number" name="MaxPrice" id="max_price" min="2">
        <label for="start_time">start time:</label>
        <input type="datetime-local" name="StartTime" id="start_time">
        <label for="duration">duration(day):</label>
        <input type="range" name="Duration" id="duration" min="1" max="6" value="2" step="1">
        <label for="forImage">image of goods:</label>
        <input type="file" name="Image" id="forImage" accept="image" multiple>
        <button type="submit" id="sub">submit</button>
        <script src="js/create_auctions/create_auctions.js"></script>
    </div>
  </body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<title>订餐吧_不仅吃省点,而且吃好点</title> 
<!--CSS-->
<link rel="stylesheet" href="css/styles.css" />
<link rel="stylesheet" href="css/stroll.css">
<!--Javascript-->
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery.flexslider.js"></script>
<script type="text/javascript" src="js/jquery.easing.js"></script>
<script type="text/javascript" src="js/jquery.jcarousel.js"></script>
<script type="text/javascript" src="js/form_elements.js"></script>
<script type="text/javascript" src="js/custom.js"></script>
<!--[if lt IE 9]>
    <script src="js/html5.js"></script>
<![endif]-->
<!-- mobile setting -->
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
<body>
<div class="wrapper">
    <div class="header_container">
        <!--Header Starts-->
        <c:import url="/header.html" charEncoding="UTF-8"></c:import>
        <!--Header Ends-->
    </div>
    <div class="navigation_container">
        <!--Navigation Starts-->
		<c:import url="/navigation.html" charEncoding="UTF-8">
			<c:param name="type" value="stroll"></c:param>
		</c:import>
        <!--Navigation Ends-->
    </div>
    <div class="section_container">
        <!--Mid Section Starts-->
        <section>
            <!--MAIN CONTENT STARTS-->
            <div id="container">
    <div id="main" role="main">

      <ul id="tiles">
        <!-- These is where we place content loaded from the Wookmark API -->
      </ul>

      <div id="loader">
        <div id="loaderCircle"></div>
      </div>

    </div>
  <!-- Include the plug-in -->
  <script src="js/jquery.wookmark.min.js"></script>

  <!-- Once the page is loaded, initalize the plug-in. -->
  <script type="text/javascript">
    var handler = null;
    var page = 1;
    var isLoading = false;
    var apiURL = 'json_stroll.html'

    // Prepare layout options.
    var options = {
      autoResize: true, // This will auto-update the layout when the browser window is resized.
      container: $('#tiles'), // Optional, used for some extra CSS styling
      offset: 2, // Optional, the distance between grid items
      itemWidth: 210 // Optional, the width of a grid item
    };

    /**
     * When scrolled all the way to the bottom, add more tiles.
     */
    function onScroll(event) {
      // Only check when we're not still waiting for data.
      if(!isLoading) {
        // Check if we're within 100 pixels of the bottom edge of the broser window.
        var closeToBottom = ($(window).scrollTop() + $(window).height() > $(document).height() - 100);
        if(closeToBottom) {
          loadData();
        }
      }
    };

    /**
     * Refreshes the layout.
     */
    function applyLayout() {
      // Create a new layout handler.
      handler = $('#tiles li');
      handler.wookmark(options);
    };

    /**
     * Loads data from the API.
     */
    function loadData() {
      isLoading = true;
      $('#loaderCircle').show();

      $.ajax({
        url: apiURL,
        dataType: 'json',
        data: {p: page}, // Page parameter to make sure we load new data
        success: onLoadData
      });
    };

    /**
     * Receives data from the API, creates HTML for images and updates the layout
     */
    function onLoadData(data) {
      isLoading = false;
      $('#loaderCircle').hide();

      // Increment page index for future calls.
      page++;

      // Create HTML for the images.
      var html = '';
      var i=0, length=data.length, image;
      for(; i<length; i++) {
        image = data[i];
        html += '<li>';

        // Image tag (preview in Wookmark are 200px wide, so we calculate the height based on that).
        html += '<a href="product.html?id='+image.toId+'"><img src="'+image.pictureUrl+'" width="200" height="300" ></a>';

        // Image title.
        html += '<p>'+image.contentText+'</p>';

        html += '</li>';
      }

      // Add image HTML to the page.
      $('#tiles').append(html);

      // Apply layout.
      applyLayout();
    };

    $(document).ready(new function() {
      // Capture scroll event.
      $(document).bind('scroll', onScroll);

      // Load first data from the API.
      loadData();
    });
  </script>
             
            </div>
            <!--MAIN CONTENT ENDS-->
        </section>
        <!--Mid Section Ends-->
    </div>
    <div class="/footer_container">
        <!--Footer Starts-->
		<footer>
		<c:import url="/footer.html" charEncoding="UTF-8"></c:import>
		</footer>
        <!--Footer Ends-->
    </div>
</div>
</body>
</html>

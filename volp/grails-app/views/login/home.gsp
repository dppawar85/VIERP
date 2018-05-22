<!doctype html>
<%! import grails.converters.JSON %>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>Welcome to VOLP </title>

  <!-- Bootstrap core CSS -->

  <meta name="viewport" content="width=device-width, initial-scale=1">

  <link href='https://fonts.googleapis.com/css?family=Antic' rel='stylesheet'>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

 <g:javascript library="jquery"></g:javascript>
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.11.2/jquery-ui.js"></script>


  <asset:stylesheet src="bootstrap.min.css" />

  <asset:stylesheet src="font-awesome.min.css" />



  <asset:stylesheet src="owl.carousel.css"  rel="stylesheet"/>
  <asset:stylesheet src="typography.css" rel="stylesheet"  />

  <asset:stylesheet src="responsive.css"  rel="stylesheet"/>

  <asset:stylesheet src="home.css" rel="stylesheet" />
  <asset:stylesheet src="course_category_card.css" rel="stylesheet" />
  <asset:stylesheet src="footer.css" rel="stylesheet" />

  <style>
a:hover {
    background-color: #ddd;
    color: black;
}

.previous {
    background-color: #f1f1f1;
    color: black;

}

.next {
    background-color: #4CAF50;
    color: white;

}

.round {
    border-radius: 50%;
}
</style>
</head>

<body>
<!--<a href="/registration/verifyemail">Sign-Up</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="/login/login">Log-in</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="url">Log-in with Google</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-->
<!-- SIGN IN and SIGNUP MODEL -->



<!--VOLP top bar start-->
<div class="top-bar1">
 <div class="container">
  <div class="row">
   <div class="col-md-5">
    <div class="pull-left">
     <em class="contact_top_bar"><i class="fa fa-phone"></i> Call Us  on 9423456787</em>
   </div>
 </div>
 <div class="col-md-7">
  <div class="lng_wrap">
   <div class="dropdown">
     <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
       <i class="fa fa-globe"></i>Select Language
       <span class="caret"></span>
     </button>

   </div>
 </div>
 <ul class="login_wrap">
   <li><a href="/registration/verifyemail" ><i class="fa fa-user"></i>Register</a></li>
   <li><a href="/login/login" ><i class="fa fa-sign-in"></i>Sign In</a></li>
 </ul>
 <ul class="top_nav">
   <li><a href="blog-detail.html">Home</a></li>
   <li><a href="event-detail.html">About VOLP</a></li>
   <li class="dropdown test">
    <a class="dropdown-toggle" data-toggle="dropdown" href="#">Courses<span class="caret"></span></a>
    <ul class="dropdown-menu">
     <li ><a href="#">Data Structures</a></li>
     <li ><a href="">Operating Systems</a></li>
     <li ><a href="#">Web Technology</a></li>
   </ul>
 </li>
 <li><a href="">Partners</a></li>
 <li><a href="">Pricing</a></li>

 <li><a href="">contact us</a></li>
</ul>
</div>
</div>
</div>


</div>
<!--VOLP top bar end-->

<!--VOLP Navigation end--------------------------------- -->

<!-- Search box start-->
<div class="container">

  <div class="col-md-6 pull-right">
    <div id="custom-search-input ">
      <div class="input-group col-md-12" style="border: 1px solid #5bc0de; border-radius: 8px;">
        <input type="text" class="form-control input-lg" placeholder="search course" />
        <span class="input-group-btn">
          <button class="btn btn-info btn-lg " type="button">
            <i class="fa fa-search" aria-hidden="true"></i>
          </button>
        </span>
      </div>
    </div>
  </div>

</div>

<!-- Search box End-->


<!--VOLP Carousel Start-->
<div class="container">
  <div class="row">
    <div class="col-sm-12">
      <div id="myCarousel" class="carousel slide" data-ride="carousel">
       <div class="carousel-inner inner">
        <div class="item active">
          <asset:image src="homeImages/volp_img_1.jpg" alt="homeImages1"/>
        </div>

        <div class="item">
          <asset:image src="homeImages/volp_img_2.jpg" alt="homeImages2"/>
        </div>

        <div class="item">
          <asset:image src="homeImages/volp_img_3.jpg" alt="homeImages3"/>
        </div>
      </div>
      <!-- Left and right controls -->
      <a class="left carousel-control" href="#myCarousel" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left"></span>
        <span class="sr-only">Previous</span>
      </a>
      <a class="right carousel-control" href="#myCarousel" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right"></span>
        <span class="sr-only">Next</span>
      </a>
    </div>
  </div>
</div>
</div>
<!--VOLP Carousel END-->

<!-- VOLP Course-category-card-slider start-->

<div class="container " >



  <div class="row">
  <div class="card col-sm-2" style="margin-left: 10px" >
    <img class="card-img-top" src="img_avatar1.png" alt="Card image" style="width:100%">
    <div class="card-body" >
      <h4 class="card-title output" >John Doe</h4>
      <p class="card-text"><a href="#" onclick="call()"></p>
      <a href="#" class="btn btn-primary">See Profile</a>
    </div>
  </div>
<div class="card col-sm-2" style=" margin-left: 10px">
    <img class="card-img-top" src="img_avatar1.png" alt="Card image" style="width:100%">
    <div class="card-body" >
      <h4 class="card-title output" >John Doe</h4>
      <p class="card-text">Some example text some example text. John Doe is an architect and engineer</p>
      <a href="#" class="btn btn-primary">See Profile</a>
    </div>
  </div>

  <div class="card col-sm-2" style=" margin-left: 10px">
    <img class="card-img-top" src="img_avatar1.png" alt="Card image" style="width:100%">
    <div class="card-body" >
      <h4 class="card-title output">John Doe</h4>
      <p class="card-text">Some example text some example text. John Doe is an architect and engineer</p>
      <a href="#" class="btn btn-primary">See Profile</a>
    </div>
  </div>

  <div class="card col-sm-2" style=" margin-left: 10px">
    <img class="card-img-top" src="img_avatar1.png" alt="Card image" style="width:100%">
    <div class="card-body" >
      <h4 class="card-title output">John Doe</h4>
      <p class="card-text">Some example text some example text. John Doe is an architect and engineer</p>
      <a href="#" class="btn btn-primary">See Profile</a>
    </div>
  </div>

  <div class="card col-sm-2" style=" margin-left: 10px">
    <img class="card-img-top" src="img_avatar1.png" alt="Card image" style="width:100%">
    <div class="card-body" >
      <h4 class="card-title output">John Doe</h4>
      <p class="card-text">Some example text some example text. John Doe is an architect and engineer</p>
      <a href="#" class="btn btn-primary">See Profile</a>
    </div>
  </div>


</div>
<a href="#" class="previous" id="prev_button">&laquo; Previous</a>
<a  href="#" class="next" id="next_button">Next &raquo;</a>

</div>
<br>
<br>

<div id="output"></div>



<script>




//var arr = JSON.parse('${topCrs}');//['foo', 'bar', 'baz'];
var i = 0;
//alert(arr);
function nextItem() {
    i = i + 1; // increase i by one
    i = i % arr.length; // if we've gone too high, start from `0` again
    return arr[i]; // give us back the item of where we are now
}

function prevItem() {
    if (i === 0) { // i would become 0
        i = arr.length; // so put it at the other end of the array
    }
    i = i - 1; // decrease by one
    return arr[i]; // give us back the item of where we are now
}

window.addEventListener('load', function () {
    var t=document.getElementsByClassName('output'); // initial value
    t[0].textContent = arr[0];
    t[1].textContent = arr[0];
    t[2].textContent = arr[0];
    t[3].textContent = arr[0];
    t[4].textContent = arr[0];


    document.getElementById('prev_button').addEventListener(
        'click', // we want to listen for a click
        function (e) { // the e here is the event itself
          var x =document.getElementsByClassName('output');
            var set=prevItem();
            x[0].innerHTML =set;
            x[1].innerHTML =set;
            x[2].innerHTML =set;
            x[3].innerHTML =set;
            x[4].innerHTML =set;
            //document.getElementById('output').textContent = prevItem();
        }
    );

    document.getElementById('next_button').addEventListener(
        'click', // we want to listen for a click
        function (e) { // the e here is the event itself
          var z =document.getElementsByClassName('output');
            var set=nextItem();
            z[0].textContent =set;
            z[1].textContent =set;
            z[2].textContent =set;
            z[3].textContent =set;
            z[4].textContent =set;




            //document.getElementById('output').textContent = nextItem();
        }
    );
});

  </script>

<!-- VOLP Course-category-card-slider end-->
<!--Footer-->

<footer id="myFooter">
  <div class="container">
    <div class="row">
      <div class="col-sm-3 myCols">
        <h5>Policy</h5>
        <ul>
          <li><a href="#">Home</a></li>
          <li><a href="#">Sign up</a></li>
          <li><a href="#">Downloads</a></li>
        </ul>
      </div>
      <div class="col-sm-3 myCols">
        <h5>Support</h5>
        <ul>
          <li><a href="#">Company Information</a></li>
          <li><a href="#">Contact us</a></li>
          <li><a href="#">Reviews</a></li>
        </ul>
      </div>
      <div class="col-sm-3 myCols">
        <h5>Blog</h5>
        <ul>
          <li><a href="#">FAQ</a></li>
          <li><a href="#">Help desk</a></li>
          <li><a href="#">Forums</a></li>
        </ul>
      </div>
      <div class="col-sm-3 myCols">
        <h5>FAQ</h5>
        <ul>
          <li><a href="#">Terms of Service</a></li>
          <li><a href="#">Terms of Use</a></li>
          <li><a href="#">Privacy Policy</a></li>
        </ul>
      </div>
    </div>
  </div>
  <div class="social-networks">
    <a href="#" class="facebook"><i class="fa fa-facebook-official"></i></a>
    <a href="#" class="google"><i class="fa fa-google-plus"></i></a>
    <a href="#" class="linkedin"><i class="fa fa-linkedin"></i></a>
  </div>
  <div class="footer-copyright">
    <p>© 2018 Copyright @VOLP </p>
  </div>
</footer>

<!--/Footer-->

<asset:javascript src="jquery.js"/>
<asset:javascript src="jquery-2.2.0.min.js"/>

   <!-- <script language="JavaScript" type="text/javascript" src="/js/jquery-1.2.6.min.js"></script>
    <script language="JavaScript" type="text/javascript" src="/js/jquery-ui-personalized-1.5.2.packed.js"></script>
    <script language="JavaScript" type="text/javascript" src="/js/sprinkle.js"></script>  -->
<asset:javascript src="bootstrap.js"/>

</body>

</html>

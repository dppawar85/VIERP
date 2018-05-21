<!doctype html>
<html lang="en">
<head>
  <%@ page expressionCodec="none" %>
  <%! import grails.converters.JSON %>
<!--<a href="#" onclick="prev()">prev</a>
  <a href="#" onclick="next()">next</a>-->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>Welcome to VOLP </title>

  <!-- Bootstrap core CSS -->

  <meta name="viewport" content="width=device-width, initial-scale=1">

  <link href='https://fonts.googleapis.com/css?family=Antic' rel='stylesheet'>
   <asset:stylesheet href="bootstrap4.min.css" />
   <asset:stylesheet href="bootstrap.css" />

   <asset:stylesheet href="jquery-ui.css"/>
   <asset:stylesheet href="font-awesome.min.css" />
  <asset:stylesheet href="owl.carousel.css"  rel="stylesheet"/>
  <asset:stylesheet href="typography.css" rel="stylesheet"  />

  <asset:stylesheet href="responsive.css"  rel="stylesheet"/>

  <asset:stylesheet href="home.css" rel="stylesheet" />
  <asset:stylesheet href="course_category_card.css" rel="stylesheet" />
  <asset:stylesheet href="footer.css" rel="stylesheet" />
  <asset:stylesheet href="menus.css" rel="stylesheet" />


  <asset:javascript src="jquery-2.2.0.min.js"/>
  <asset:javascript src="jquery.js"/>
  <asset:javascript src="jquery-ui.js" />
  <asset:javascript src="bootstrap.js"/>
  <script async src="https://www.googletagmanager.com/gtag/js?id=UA-116861890-1"> </script>
  <asset:javascript src="gtag.js"/>
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
  .dropdown-submenu {
      position: relative;
  }

  .dropdown-submenu .dropdown-menu {
      top: 0;
      left: 100%;
      margin-top: -1px;
  }
  .fa{
  color: blue;
  }
  }
</style>


</head>
<body onload="slider()">
  <!--VOLP top bar start-->
  <div class="top-bar1">
   <div class="container">
    <div class="row">
     <div class="col-md-5">
      <div class="pull-left">
      <!-- <em class="contact_top_bar"><i class="fa fa-phone"></i> Call Us  on 9423456789</em> -->
     </div>
   </div>

   <div class="col-md-7">
    <div class="lng_wrap">
     <div class="dropdown">
      <!-- <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
         <i class="fa fa-globe"></i>Select Language
         <span class="caret"></span>
       </button> -->

     </div>
   </div>

   <ul class="login_wrap">
     <li><a href="${request.contextPath}/registration/verifyemail" ><i class="fa fa-user"></i>Register</a></li>
     <li><a href="${request.contextPath}/login/login" ><i class="fa fa-sign-in"></i>Sign In</a></li>
   </ul>



   <ul class="top_nav pull-left">

     <li><a href="#">Home</a></li>

     <li><a href="${request.contextPath}/login/aboutvolp" target="_blank">About VOLP</a></li>
     <li class="dropdown test"> <a class="dropdown-toggle" data-toggle="dropdown" href="#">  Course Category </a>
                  <ul class="dropdown-menu">
                     <nav class="navigation" >
                          <g:each var="obj" in="${menus}">
                          	<ul class="mainmenu navigation" >

                          		<li ><g:link  action="showCourses" controller="courseCategory" params="[ccid: obj[0].id]">
                                                    <span class="catMenu"> ${obj[0].name} </span>
                                 </g:link>
                          		<ul class="submenu">
                          			<g:each var="sobj" in="${obj[1]}">
                          			<li><g:link action="showCourses" controller="courseCategory" params="[ccid: sobj.id]">
                                        <span class="catMenu"> ${sobj.name} </span>
                                     </g:link></li>

                          			</g:each>
                          		</ul>
                          		</li>
                          	</ul>
                          </g:each>
                     </nav>
                   </ul>
          </li>

   <li><a href="${request.contextPath}/login/partener" target="_blank">Partners</a></li>
   <li><a href="${request.contextPath}/login/pricing" target="_blank">Pricing</a></li>

   <li><a href="${request.contextPath}/login/contactus" target="_blank">contact us</a></li>
</ul>


</div>
<div class=" pull-left container " style="margin-bottom:10px">
                   <g:form controller="courseCategory" action="findCourses">

                   <div class="input-group col-md-5">

                   	<input class="form-control" type="text" placeholder="search course by name" name="searchTxt" id="autocomplete" required="true"/>

                   	<span class="input-group-btn">
                   		<button class="btn btn-warning" type="submit" >
                   			<i class="fa fa-search" aria-hidden="true"></i>
                   		</button>
                   	</span>



                   </div>
                   </g:form>

            </div>
</div>

</div>


</div>
<!--VOLP top bar end-->

<!--VOLP Navigation end--------------------------------- -->

<!-- Search box start-->


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
<br>
<div class="container-fluid">
<div class="row" id="topcard">

  <!--crsSlider.gsp-->

</div>
</div><br>
<!--VOLP Carousel END-->
<div class="container-fluid">
<div class="row" id="topcat1">
  <!--catcrsSlider1.gsp-->
</div>
</div><br>
<div class="container-fluid">
<div class="row" id="topcat2">
  <!--catcrsSlider2.gsp-->
</div>
</div><br>
<div class="container-fluid">
<div class="row" id="topcat3">
  <!--catcrsSlider3.gsp-->
</div>
</div>
<br>

<div class="container-fluid">
<div id="topcat4">
  <!--catcrsSlider4.gsp-->
</div>
</div>
<br>
<!--Footer-->

<footer id="myFooter" >
  <div class="container" style="max-width: 100%">
    <div class="row">
      <div class="col-sm-4 myCols">
        <h5>Policy</h5>
        <ul>
          <li><a href="#">Home</a></li>

          <li><a href="${request.contextPath}/login/downloads" target="_blank">Downloads</a></li>
        </ul>
      </div>
      <div class="col-sm-4 myCols">
        <h5>About Us</h5>
        <ul>

          <li><a href="${request.contextPath}/login/contactus" target="_blank">Contact us</a></li>
          <li><a href="${request.contextPath}/login/reviews" target="_blank">Reviews</a></li>
        </ul>
      </div>
      <div class="col-sm-4 myCols">
        <h5>Blog</h5>
        <ul>
          <li><a href="${request.contextPath}/login/faq" target="_blank">FAQ</a></li>

          <li><a href="${request.contextPath}/login/forum" target="_blank">Forums</a></li>
        </ul>
      </div>
    </div>
  </div>
  <div class="social-networks">
    <a href="https://www.facebook.com/vishwakarma.platform.7" target="_blank" class="facebook"><i class="fa fa-facebook-official"></i></a>
    <a href="https://plus.google.com/u/3/108067749854890374995" target="_blank" class="google"><i class="fa fa-google-plus"></i></a>
    <a href="https://www.linkedin.com/in/vishwakarma-online-learning-platform-56658715a/" target="_blank" class="linkedin"><i class="fa fa-linkedin"></i></a>
  </div>
  <div class="footer-copyright">
    <p>© 2018 Copyright @VOLP </p>
  </div>
</footer>

<!--/Footer-->



<script>
    $(document).ready(function(){
        $('#autocomplete').autocomplete({
                         source: '<g:createLink controller='courseCategory' action='ajaxCourseFinder'/>'

                       });
    });

    </script>
</body>
</html>

<script>
  var cnt=0;
  var cntcat1=0;
  var cntcat2=0;
  var cntcat3=0;
  var cntcat4=0;
var crs = ${crs}; //top courses
var cat1 = ${cat1}
var cat2 = ${cat2}
var cat3 = ${cat3}
var cat4 = ${cat4}

function slider(){
  next();
  nextcat1();
  nextcat2();
  nextcat3();
  nextcat4();
}
function nextcat1(){
  var crsids =[]
  if(cntcat1>cat1.length)
    cntcat1=0
  if(cntcat1<=cat1.length)
  {
    for(i=0;i<4;i++,cntcat1++){
      crsids[i] = cat1[cntcat1];
     }
  }
  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("topcat1").innerHTML = this.responseText;
    }
  };
  xmlhttp.open("GET", "${request.contextPath}/login/catSlider1?crsids=" + crsids+"&cc="+${catname1}, true);
  xmlhttp.send();
}
function prevcat1(){
  var crsids =[]
  if(cntcat1==0)
    cntcat1=4
  if(cntcat1==4)
  {
    cntcat1 = cntcat1-4
    for(i=0;i<4;i++,cntcat1++)
      crsids[i] = cat1[cntcat1];
  }

  else
  {
    cntcat1 = cntcat1-8
    for(i=0;i<4;i++,cntcat1++)
      crsids[i] = cat1[cntcat1];
  }

  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("topcat1").innerHTML = this.responseText;
    }
  };
  xmlhttp.open("GET", "${request.contextPath}/login/catSlider1?crsids=" + crsids+"&cc="+${catname1}, true);
  xmlhttp.send();
}
function nextcat2(){
  var crsids =[]
  if(cntcat2>cat2.length)
    cntcat2=0
  if(cntcat2<=cat2.length)
  {
    for(i=0;i<4;i++,cntcat2++)
      crsids[i] = cat2[cntcat2];
  }

  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("topcat2").innerHTML = this.responseText;
    }
  };
  xmlhttp.open("GET", "${request.contextPath}/login/catSlider2?crsids=" + crsids+"&cc="+${catname2}, true);
  xmlhttp.send();
}
function prevcat2(){

  var crsids =[]
  if(cntcat2==0)
    cntcat2=4
  if(cntcat2==4)
  {
    cntcat2 = cntcat2-4
    for(i=0;i<4;i++,cntcat2++)
      crsids[i] = cat2[cntcat2];
  }

  else
  {
    cntcat2 = cntcat2-8
    for(i=0;i<4;i++,cntcat2++)
      crsids[i] = cat2[cntcat2];
  }

  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("topcat2").innerHTML = this.responseText;
    }
  };
  xmlhttp.open("GET", "${request.contextPath}/login/catSlider2?crsids=" + crsids+"&cc="+${catname2}, true);
  xmlhttp.send();
}
function nextcat3(){
  var crsids =[]
  if(cntcat3>cat3.length)
    cntcat3=0
  if(cntcat3<=cat3.length)
  {
    for(i=0;i<4;i++,cntcat3++)
      crsids[i] = cat3[cntcat3];
  }

  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("topcat3").innerHTML = this.responseText;
    }
  };
  xmlhttp.open("GET", "${request.contextPath}/login/catSlider3?crsids=" + crsids+"&cc="+${catname3}, true);
  xmlhttp.send();
}
function prevcat3(){

  var crsids =[]
  if(cntcat3==0)
    cntcat3=4
  if(cntcat3==4)
  {
    cntcat3 = cntcat3-4
    for(i=0;i<4;i++,cntcat3++)
      crsids[i] = cat3[cntcat3];
  }

  else
  {
    cntcat3 = cntcat3-8
    for(i=0;i<4;i++,cntcat3++)
      crsids[i] = cat3[cntcat3];
  }

  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("topcat3").innerHTML = this.responseText;
    }
  };
  xmlhttp.open("GET", "${request.contextPath}/login/catSlider3?crsids=" + crsids+"&cc="+${catname3}, true);
  xmlhttp.send();
}
function nextcat4(){
  var crsids =[]
  if(cntcat4>cat4.length)
    cntcat4=0
  if(cntcat4<=cat4.length)
  {
    for(i=0;i<4;i++,cntcat4++)
      crsids[i] = cat4[cntcat4];
  }

  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("topcat4").innerHTML = this.responseText;
    }
  };
  xmlhttp.open("GET", "${request.contextPath}/login/catSlider4?crsids=" + crsids+"&cc="+${catname4}, true);
  xmlhttp.send();
}
function prevcat4(){

  var crsids =[]
  if(cntcat4==0)
    cntcat4=4
  if(cntcat4==4)
  {
    cntcat4 = cntcat4-4
    for(i=0;i<4;i++,cntcat4++)
      crsids[i] = cat4[cntcat4];
  }

  else
  {
    cntcat4 = cntcat4-8
    for(i=0;i<4;i++,cntcat4++)
      crsids[i] = cat4[cntcat4];
  }

  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("topcat4").innerHTML = this.responseText;
    }
  };
  xmlhttp.open("GET", "${request.contextPath}/login/catSlider4?crsids=" + crsids+"&cc="+${catname4}, true);
  xmlhttp.send();
}
function next(){
  var crsids =[]
  if(cnt>crs.length)
    cnt=0
  if(cnt<=crs.length)
  {
    for(i=0;i<4;i++,cnt++)
      crsids[i] = crs[cnt];
  }

  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("topcard").innerHTML = this.responseText;
    }
  };
  xmlhttp.open("GET", "${request.contextPath}/login/crsSlider?crsids=" + crsids, true);
  xmlhttp.send();
}
function prev(){

  var crsids =[]
  if(cnt==0)
    cnt=4
  if(cnt==4)
  {
    cnt = cnt-4
    for(i=0;i<4;i++,cnt++)
      crsids[i] = crs[cnt];
  }

  else
  {
    cnt = cnt-8
    for(i=0;i<4;i++,cnt++)
      crsids[i] = crs[cnt];
  }

  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("topcard").innerHTML = this.responseText;
    }
  };
  xmlhttp.open("GET", "${request.contextPath}/login/crsSlider?crsids=" + crsids, true);
  xmlhttp.send();
}
function showSubCat(mcat){
alert(mcat.length)
    if(mcat!=null || mcat!=""){
        alert("callajax..")
    }
}
</script>

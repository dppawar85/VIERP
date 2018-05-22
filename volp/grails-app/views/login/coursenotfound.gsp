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

   <asset:stylesheet href="w3.css" />
   <asset:stylesheet href="bootstrap4.min.css" />
   <asset:stylesheet href="bootstrap.css" />

   <asset:stylesheet href="jquery-ui.css"/>
   <asset:stylesheet href="font-awesome.min.css" />

   <asset:stylesheet href="course_category_card.css" rel="stylesheet" />
   <asset:stylesheet href="home2.css" rel="stylesheet" />

  <asset:javascript src="jquery-2.2.0.min.js"/>
  <asset:javascript src="jquery.js"/>
  <asset:javascript src="jquery-ui.js" />
  <asset:javascript src="bootstrap.js"/>
  <script async src="https://www.googletagmanager.com/gtag/js?id=UA-116861890-1"> </script>
  <asset:javascript src="gtag.js"/>


 <style>
            .jumbotron{
             background-color: white;
            }
          p,i{

           font-size: 3em;
           text-align: center;
           color: white;

          }
          i{

            font-size: 5em;
          }
          .panel-body{
          //background: grey;
          color:white;
          }
          .btn{
              background-color: #f4511e;
              color: white;
          }

       </style>


</head>
  <body onload="slider()">

  <header>
    <nav class="line navbar header1" >
      <div class="container-fluid row">
      <div class="col-md-3">
         <span class="brand">VOLP<span style="font-size: 0.5em; color:#337ab7;"> <h6>Viswakarma Online Learning Platform </h6></span> </span>
      </div>
      <div class="col-md-3">

      </div>
      <div class="left1 col-md-3">

             <ul >
               <li> <a href="${request.contextPath}/login/login"> <i class="fa fa-sign-in"></i>&nbsp;&nbsp;Sign In </a></li>
               <li> <a href="${request.contextPath}/registration/verifyemail"> <i class="fa fa-user"></i>&nbsp;&nbsp;New User </a> </li>
             </ul>
       </div>

      <div class="left1 col-md-3">
           <i class="fa fa-phone"></i> <span style="font-size: 10px"> +91 077 09 601881</span>

           <ul class="social">
             <li><a href="https://www.facebook.com/vishwakarma.platform.7" target="_blank" ><i class="fa fa-facebook"></i></a>
               </li>
             <li>  <a href="https://plus.google.com/u/3/108067749854890374995" target="_blank" ><i class="fa fa-google-plus" ></i></a>
             </li>
             <li>
               <a href="https://www.linkedin.com/in/vishwakarma-online-learning-platform-56658715a/" target="_blank" ><i class="fa fa-linkedin"></i></a>
              </li>
           </ul>
      </div>

      </div>
    </nav>

  </header>
<center>
  <header id="menuHeader">

      <nav class="navbar">
       <button title="Menu" type="button" style="background: white;" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
               <span class="icon-bar" ></span>
               <span class="icon-bar" ></span>
               <span class="icon-bar" ></span>
        </button>

       <div class="collapse navbar-collapse" id="myNavbar">
        <ul>
           <li><a href="${request.contextPath}/login/home1">Home</a></li>
            <li><a href="${request.contextPath}/login/aboutvolp" target="_self">About VOLP</a></li>
          <!-- ------------Course Category ---------------------- -->
            <li> <a href="#">Course Category &nbsp;<span><i style ="color: white" class="fa fa-caret-down"></i> </span>&nbsp;</a>
              <ul class="">
                      <g:each var="obj" in="${menus}">
                         <li class=""><g:link action="showCourses" controller="courseCategory" params="[ccid: obj[0].id]">
                                     <span class="catMenu"> ${obj[0].name} &nbsp;<span><i style ="color: white" class="fa fa-caret-right"></i> </span>&nbsp;</span>
                              </g:link>
                            <ul class="">
                                 <g:each var="sobj" in="${obj[1]}">
                                   <li class=""><g:link  action="showCourses" controller="courseCategory" params="[ccid: sobj.id]">
                                          <span class="catMenu"> ${sobj.name} </span>
                                       </g:link></li>
                                 </g:each>
                            </ul>
                         </li>

                        </g:each>
                   </ul>
            </li>

            <!-- ------------End Course Category ---------------------- -->
          <li><a href="${request.contextPath}/login/partener" target="_self">Partners</a></li>
          <li><a href="${request.contextPath}/login/pricing" target="_self">Pricing</a></li>
          <li><a href="${request.contextPath}/login/contactus" target="_self">contact us</a></li>
        </ul>

        </div>
      <nav>
  </header> </center>
  <!-- ------------ Search Div ---------------- -->
           <div class="searchCourse col-md-3">
                   <g:form controller="courseCategory" action="findCourses">

                   <div class="input-group">

                   	<input style="border: 1px solid;transition: width 0.4s ease-in-out;" class="form-control" type="text" placeholder="search course by name" name="searchTxt" id="autocomplete" required="true"/>

                   	<span class="input-group-btn">
                   		<button class="btn btn-danger" type="submit" >
                   			<i class="fa fa-search" aria-hidden="true" style="color:white"></i>
                   		</button>
                   	</span>

                   </div>
                   </g:form>
            </div> <!-- ------------ End Search Div ---------------- -->
<div class="container">
<h3> Oops <img src="http://localhost/vit/oops.png"/> &nbsp;&nbsp; No Such a Course!!!! Try Something Different</h3>
</div>
<br><br>
<!-- ------------------------------ End Top Courses---------------- -->

  <footer class="container-fluid" style="background: #007bff; color: white; opacity: 0.9">
  		<div class="overlay">
         <i class="fa fa-chevron-circle-up" style="" onclick="topFunction()" id="topScroll" title="Go to top"></i>
  		</div>
  		<div class="container">
  			<div class="row">
  				<div class="col-md-3">
  					<h5 style="text-decoration: underline; font-weight: bold">Learning</h5>
  					<ul class="list-inline">
  						<li><a href="#">Course</a></li>
  						<li><a href="${request.contextPath}/login/faq">Faq</a></li>
  						<li><a href="${request.contextPath}/login/contactus">Contact</a></li>
  						<li><a href="#">Terms</a></li>
  					</ul>
  				</div>

  				<div class="col-md-3">
  					<h5 style="text-decoration: underline; font-weight: bold">Learn &amp; Grow</h5>
  					<ul class="list-inline">
  						<li><a href="#">Blog</a></li>
  						<li><a href="#">Privacy</a></li>
  						<li><a href="#">Testimonials</a></li>
  						<li><a href="#">Help Desk</a></li>
  					</ul>
  				</div>

  				<div class="col-md-3">
  					<h5 style="text-decoration: underline; font-weight: bold">Engage us</h5>
  					<ul class="list-inline">
  						<li><a href="#">Marketing</a></li>
  						<li><a href="#">Visual Assistant</a></li>
  						<li><a href="#">System Analysis</a></li>
  						<li><a href="#">Advertise</a></li>
  					</ul>
  				</div>

  				<div class="col-md-3">
  					<h5 style="text-decoration: underline; font-weight: bold">Legal</h5>
  					<ul class="list-inline">
  						<li><a href="#">Find Designers</a></li>
  						<li><a href="#">Find Developers</a></li>
  						<li><a href="#">Teams</a></li>
  						<li><a href="#">Advertise</a></li>
  						<li><a href="#">API</a></li>
  					</ul>
  				</div>
  			</div>

  			<div class="row copyright">
  				<div class="col-md-12 text-center">
  					<p>
  						<small class="block">© 2018 All Rights Reserved.</small>

  					</p>
  				</div>
  			</div>

  		</div>
  	</footer>

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
// -----------------------topScroll button -----------------------------
 // When the user scrolls down 20px from the top of the document, show the button
 window.onscroll = function() {scrollFunction()};

 function scrollFunction() {
     if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
         document.getElementById("topScroll").style.display = "block";
     } else {
         document.getElementById("topScroll").style.display = "none";
     }
 }

 // When the user clicks on the button, scroll to the top of the document
 function topFunction() {
     document.body.scrollTop = 0;
     document.documentElement.scrollTop = 0;
 }
 </script>
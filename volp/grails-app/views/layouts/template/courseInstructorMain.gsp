<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="VOLP"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>


    <asset:stylesheet src="bootstrap.min.css"/>
    <asset:stylesheet src="font-awesome.min.css"/>
    <asset:stylesheet src="sb-admin.css"/>
    <g:layoutHead/>
    <style>
       .nav-link-text{
       	color: #3F51B5;
       	font-weight: bold;
       }
       .nav-link span:hover{

       	color: blue;
       	border-bottom:2px #C51162 solid;
       }
       ul .sidenav-second-level li a{
       	background-color: white;
       	color: black;
       }
       .fa{
       	color: #000000;
       }
       .user-profile-cursor {
       	cursor: pointer;
       	color: blue;

       }
      .navbar-brand {color:white;font-weight: bold;font-style: italic }
    </style>
</head>
<body>
 <g:render template="/layouts/courseInstructor/courseInstructorNav" />
<div class="content-wrapper">
    <div class="container-fluid">
      <!-- container-fluid-->

          <g:layoutBody/>

     </div><!-- /.container-fluid-->

 </div> <!-- /.content-wrapper-->


  <g:render template="/layouts/courseInstructor/courseInstructorFooter" />


    <asset:javascript src="jquery.min.js"/>
    <asset:javascript src="bootstrap.bundle.min.js"/>
    <asset:javascript src="jquery.easing.min.js"/>
    <asset:javascript src="sb-admin.min.js"/>
</body>
</html>

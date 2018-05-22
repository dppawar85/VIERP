<!doctype html>
<html lang="en">
<head>
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
</head>
<body>
<div class="row">
 <g:if test="${!(instructorcourses.isEmpty())}">

                 <g:each var="courseList" in="${instructorcourses}">

                     <div class="col-sm-3">
                             <div class="card">
                                    <div class="card-header " > <g:link action="course" controller="login" params="[cid:courseList.id]" style="font-weight: bold; color: blue">${courseList.course_name}</g:link>


                                     </div>
                                      <div class="card-body">
                                       <g:if test="${courseList.imgpath==null}">
                                       	<img src="${createLinkTo(dir:'assets/images/course', file: 'default.jpg' )}" alt="Card image" style="width:100%; max-height:100px" />
                                       </g:if>
                                       <g:if test="${courseList.imgpath!=null}">
                                       	<img src="${createLinkTo(dir:courseList.imgpath, file: courseList.imgname )}" alt="Card image" style="width:100%; max-height:100px"/>
                                       </g:if>
                                      <hr>
                                      <span><g:include controller="course" action="getOffering" params="[courseid: courseList.id]" /></span>

                                    </div>

                                   </div>

                     </div>
                     <br>
       </g:each>
 </g:if>
 <g:else>
  <h4 class="container" style="color:red">Course Not Available</h4>
 </g:else>
 </div>
 </body>
 </html>
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>Welcome to VOLP </title>

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
       <asset:javascript src="gtag.js"/>
</head>
<body>
<h4 class="fluid-container well well-sm " >  Current offered courses </h4>
             <div class="row">

                 <g:if test="${!(coffrlnrlst.isEmpty())}">

                      <g:each var="coffrobj" in="${coffrlnrlst}">
                          <g:if test="${coffrobj[0].isActive}">
                          <div class="col-sm-3">
                                  <div class="card" >
                                         <div class="card-header card-title" style="color:blue;font-weight: bold;">
                                           ${coffrobj[0].course.course_name}
                                          </div>
                                           <div class="card-body" >
                                             <g:if test="${coffrobj[0].course.imgpath==null}">
                                                    <img src="${createLinkTo(dir:'assets/images/course', file: 'default.jpg' )}" alt="Card image" style="width:100%; max-height:100px" />
                                                </g:if>
                                                 <g:if test="${coffrobj[0].course.imgpath!=null}">
                                                      <img src="${createLinkTo(dir:coffrobj[0].course.imgpath, file: coffrobj[0].course.imgname )}" alt="Card image" style="width:100%; max-height:100px"/>
                                                </g:if>

                                               <small style="color:blue;line-height: 1.6">Starts On : ${coffrobj[0].start_date}</small><br>
                                               <small style="color:blue;line-height: 1.6">Ends On: ${coffrobj[0].start_date}</small> <br>

                                           <p style="float: left;color:blue;">${coffrobj[1]}<span>&nbsp;&nbsp;Student(s) Enrolled</span></p>

                                            <g:if test="${session.uid==null}">
                                           <g:link action="viewDetails" style="font-size:16px; float:right" class="btn btn-success" controller="enroll" params="[cid:c.id]">
                                               View Details
                                           </g:link>
                                           <g:link action="payFee" style="font-size:16px; float:right" class="btn btn-success" controller="enroll" params="[coffr:coffrobj[0].id]">
                                               Enroll Now
                                           </g:link>

                                            </g:if>
                                            <g:else>
                                                <g:link style="font-size:16px; float:right" action="viewDetails" class="btn btn-success"controller="enroll" params="[cid:c.id]">
                                                   View Details
                                                </g:link>
                                           <g:link action="payFee" style="font-size:16px; float:right" class="btn btn-success" controller="enroll" params="[coffr:coffrobj[0].id]">
                                               Enroll Now
                                           </g:link>
                                            </g:else>
                                         </div>

                                        </div>

                          </div>
                          </g:if>

                      </g:each>
                          </g:if>

                  </div> <!-- End of row-->
                  <br><br>
                <h4 class="fluid-container well well-sm"> List of archived courses </h4>
                <div class="row">

                 <g:if test="${!(coffrlnrlst.isEmpty())}">

                      <g:each var="coffrobj" in="${coffrlnrlst}">
                          <g:if test="${!coffrobj[0].isActive}">
                          <div class="col-sm-3">
                                  <div class="card">
                                         <div class="card-header  card-title" style="color:blue;font-weight: bold;">
                                             ${coffrobj[0].course.course_name}
                                          </div>
                                           <div class="card-body">

                                            <g:if test="${coffrobj[0].course.imgpath==null}">
                                            	<img src="${createLinkTo(dir:'assets/images/course', file: 'default.jpg' )}" alt="Card image" style="width:100%; max-height:100px" />
                                            </g:if>
                                            <g:if test="${coffrobj[0].course.imgpath!=null}">
                                            	<img src="${createLinkTo(dir:coffrobj[0].course.imgpath, file: coffrobj[0].course.imgname )}" alt="Card image" style="width:100%; max-height:100px"/>
                                            </g:if>
                                                 <small style="color:blue;line-height: 1.6">Started On : ${coffrobj[0].start_date}</small><br>
                                                 <small style="color:blue;line-height: 1.6">Ended On: ${coffrobj[0].start_date}</small><br>

                                           <p style="float: left;color:blue;">${coffrobj[1]}<span>&nbsp;&nbsp;Student(s) Enrolled</span></p>

                                         </div>

                                        </div>

                          </div>
                          </g:if>

                          <br>
                      </g:each>
                          </g:if>

                  </div> <!-- End of row-->

</body>
</html>

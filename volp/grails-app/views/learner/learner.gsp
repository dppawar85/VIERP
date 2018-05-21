<head>
<meta name="layout" content="learner/courseLearnerMain">
<style>
    h5{
     color: white;
     background-color: #424242;
     border:1px solid;
     padding: 5px;
    }
   </style>
</head>

  </script>

  <body>
  <div class="row">
    <g:if test="${!(regCrs.isEmpty())}">

                 <g:each var="crs" in="${regCrs}">

                     <div class="col-sm-3">
                             <div class="card">
                                       <div class="card-header bg-default"> <g:link action="leranerCourse" params="[crsLearner:crs.id]"><i style="font-size: 18px" class="fa fa-arrow-circle-o-right">click here</i> &nbsp;${crs.courseoffering.course.course_name}  </g:link></div>
                                   <!-- <div class="card-header bg-default">
                                 <g:form id="form1" url="[action:'leranerCourse',controller:'learner']">
                                        <g:hiddenField name="crsLearner" value="${crs.id}" />
                                        <button type="submit" class="btn btn-light" form="form1" value="Submit">${crs.courseoffering.course.course_name} &nbsp; <i style="font-size: 18px" class="fa fa-arrow-circle-o-right">click here</i></button>
                                        <g:submitButton name="Go" value="${crs.courseoffering.course.course_name} Click here" />
                                    </g:form>
                                    </div>-->

                                      <div class="card-body">

                                    <g:if test="${crs.courseoffering.course.imgpath==null}">
                                                                                    <img src="${createLinkTo(dir:'assets/images/course', file: 'default.jpg' )}" alt="Card image" style="width:100%; max-height:100px" />
                                                                                </g:if>
                                                    <g:if test="${crs.courseoffering.course.imgpath!=null}">
                                                                                   <img src="${createLinkTo(dir:crs.courseoffering.course.imgpath, file: crs.courseoffering.course.imgname )}" alt="Card image" style="width:100%; max-height:100px"/>
                                                    </g:if>
                                      <hr>
                                       <p style="float:right"><i class="fa fa-bookmark fa-1x"></i>&nbsp;${crs.courseoffering.batch}</p>
                                      <p><i class="fa fa-star fa-1x"></i>&nbsp;${crs.courseoffering.course.rating}</p>
                                      <center><a href="#" >${crs.courseoffering.course.courseowner.person.firstName}&nbsp;${crs.courseoffering.course.courseowner.person.lastName}</span></a></center>
                                     <!-- <a href="" style="float: right"> <i class="fa fa-folder" aria-hidden="true"></i></a> -->
                                    </div>

                                   </div>

                     </div>
                     <br>
                 </g:each>
                     </g:if>
                     </div>
                     <br><br><br>
    </body>

<g:if test="${!(listClone.isEmpty())}">

                 <g:each var="courseList" in="${listClone}">

                     <div class="col-sm-3">
                             <div class="card">
                                    <div class="card-header bg-default"> <g:link action="addNewCoursesDetails" params="[uname:courseList.courseowner.id,coursename: courseList.id]">${courseList.course_name}</g:link>

                                     </div>
                                      <div class="card-body">
                                        <img src="${createLinkTo(dir:'assets/images/course', file: 'default.jpg' )}" alt="Card image" style="width:100%; max-height:100px" />

                                      <hr>
<span><g:include controller="course" action="getOffering" params="[courseid: courseList.id]" /></span>
                        <a href="" style="float: right"> <i class="fa fa-folder" aria-hidden="true"></i></a>
                                    </div>

                                   </div>

                     </div>
                     <br>
                 </g:each>
          </g:if>
          <g:else>
          <h6> Ooops No Course Matches the Specified search!!!!!</h6>
          </g:else>
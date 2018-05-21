<head>
<meta name="layout" content="courseInstructor/courseInstructorMain">
<asset:stylesheet href="w3.css" />
<style>
    h5{
     color: white;
     background-color: #424242;
     border:1px solid;
     padding: 5px;
    }
    .btn-click-here{
    background: #007bff;;
    opacity: 0.9;
    color: white;
    padding: 1px;
    }


   </style>
</head>
<script>

function fetchmaterial()
{
   var coursecodecoursename = document.getElementById("coursecodecoursename").value;
   var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
                       if (this.readyState == 4 && this.status == 200) {
                           document.getElementById("material4").innerHTML = this.responseText;
                       }
                   };
                   xmlhttp.open("GET", "${request.contextPath}/CourseMaterial/addeditcoursematerial?coursecodecoursename=" + coursecodecoursename, true);
                   xmlhttp.send();
           return(false)
}
function callme()
{
        var coursecodecoursename = document.getElementById("coursecodecoursename").value;
        var uploadcategory = document.getElementById("uploadcategory").value;
        var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange = function() {
                    if (this.readyState == 4 && this.status == 200) {
                        document.getElementById("material1").innerHTML = this.responseText;
                    }
                };
                xmlhttp.open("GET", "${request.contextPath}/CourseMaterial/processcoursematerial?coursecodecoursename=" + coursecodecoursename +"&uploadcategory="+uploadcategory, true);
                xmlhttp.send();
        return(false)
  }
function callmetoo()
{
        var courseoutline = document.getElementById("courseoutline").value;
        var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange = function() {
                    if (this.readyState == 4 && this.status == 200) {
                        document.getElementById("material2").innerHTML = this.responseText;
                    }
                };
                xmlhttp.open("GET", "${request.contextPath}/CourseMaterial/furtherprocesscoursematerial?courseoutline=" + courseoutline, true);
                xmlhttp.send();
        return(false)
  }
  function callmetooagain()
  {
        var coursetopic = document.getElementById("coursetopic").value;
        var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange = function() {
                    if (this.readyState == 4 && this.status == 200) {
                        document.getElementById("material13").innerHTML = this.responseText;
                    }
                };
             //   xmlhttp.open("GET", "${request.contextPath}/CourseMaterial/savecoursematerial?coursetopic=" + coursetopic, true);
             //   xmlhttp.send();
        return(false)
  }

  </script>

  <body>

           <g:if test="${flash.message}">
                      <div class="alert alert-error" style="display: block;color:red">${flash.message}</div>
                  </g:if>
             <div class="row">

                <div id="mainContentDiv" class="col-sm-10">

                 </div>   <!-- End of mainContentDiv Ajax Call-->


                </div>



            <g:if test="${!(instructorcourses.isEmpty())}">
<h5> List of created courses </h5>
 <div class="row" id="showcrs">
                 <g:each var="courseList" in="${instructorcourses}">

                     <div class="col-sm-3">
                             <div class="card">
                              <div class="card-header bg-header">
                            <!--  <g:link action="addNewCoursesDetails" params="[uname:courseList.courseowner.id,coursename: courseList.id]">${courseList.course_name}&nbsp;<i style="font-size: 18px" class="fa fa-arrow-circle-o-right">
                              click here</i></g:link> -->
                                      <g:form url="[action:'addNewCoursesDetails',controller:'instructor']" >
                                        <g:hiddenField name="uname" value="${courseList.courseowner.id}" />
                                        <g:hiddenField name="coursename" value="${courseList.id}" />
                                        click here&nbsp;<i style="font-size: 12px" class="fa fa-arrow-right"></i><g:submitButton name="go" class="btn btn-sm btn-click-here" value="${courseList.course_name}" title="click here" ></g:submitButton>

                                      </g:form>
                                </div>
                                      <div class="card-body">
                                        <g:if test="${courseList.imgpath==null}">
                                                                                                         <img src="${createLinkTo(dir:'assets/images/course', file: 'default.jpg' )}" alt="Card image" style="width:100%; max-height:100px" />
                                                                                                     </g:if>
                                                                         <g:if test="${courseList.imgpath!=null}">
                                                                                                        <img src="${createLinkTo(dir:courseList.imgpath, file: courseList.imgname )}" alt="Card image" style="width:100%; max-height:100px"/>
                                                                         </g:if>
                                      <hr>
                                      <p><g:include controller="course" action="getOffering" params="[courseid: courseList.id]" /></p>
                                    </div>

                                   </div>

                     </div>
                     <br>
                 </g:each>
                   </div> <!-- End of row-->
                     </g:if>
                     <g:else>

                     <g:include controller="instructor" action="instrcutorHelp" />
                     </g:else>

               <br>

             <br><br>
    </body>
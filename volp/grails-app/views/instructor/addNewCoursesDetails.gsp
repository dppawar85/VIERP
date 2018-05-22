
<!doctype html>
<html lang="en" class="no-js">
<head>

  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <title>
    ${cid.course_name}
  </title>
  <asset:stylesheet src="bootstrap.min.css"/>
  <asset:stylesheet src="font-awesome.min.css"/>
  <asset:stylesheet src="sb-admin.css"/>

  <asset:stylesheet src="courseInstructor.css"/>

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
<body id="page-top">
  <!-- ---------------------------Start of Navigation------------------------------------------ -->
  <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top" id="mainNav">
    <a class="navbar-brand" href="${request.contextPath}/instructor/instructor"><i class="fa fa-fw fa-home"></i>&nbsp;VOLP :: Course Instructor / ${cid.course_code}::${cid.course_name} </a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">

      <ul class="navbar-nav navbar-sidenav sidenavPanel" id="exampleAccordion" >

        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Edit Course">
          <a class="nav-link" href="#" onclick="editCourse()">
            <i class="fa fa-fw fa-pencil-square-o"></i>
            <span class="nav-link-text">Edit Course</span>
          </a>
        </li>

<g:set var="crscodename" value="${cid.course_code}:${cid.course_name}" />
 <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Add/Edit Course Outcome">

         <g:link class="nav-link" action="addcourseoutcomes" controller="CourseOutcomes" params="[coursecodecoursename:crscodename]">
          <span class="fa-stack">

                      <span class="fa fa-circle-o fa-stack-2x"></span>
                      <strong class="fa-stack-1x">
                          1
                      </strong>
                  </span>
             <span class="nav-link-text">Add/Edit Course Outcome</span>
          </g:link>
  </li>

       <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Add/Edit Course Outline">
          <g:link class="nav-link" action="addcourseoutline" controller="CourseOutline" params="[coursecodecoursename:crscodename]">
           <span class="fa-stack">

                       <span class="fa fa-circle-o fa-stack-2x"></span>
                       <strong class="fa-stack-1x">
                           2
                       </strong>
                   </span>
                       <span class="nav-link-text">Add/Edit Course Unit</span>
          </g:link>
        </li>

    <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Add/Edit Course Topic">
          <g:link class="nav-link" action="listcourseoutlines" controller="CourseTopic" params="[coursecodecoursename:crscodename]">
          <span class="fa-stack">

                      <span class="fa fa-circle-o fa-stack-2x"></span>
                      <strong class="fa-stack-1x">
                          3
                      </strong>
                  </span>
                      <span class="nav-link-text">Add/Edit Course Topic</span>

          </g:link>
        </li>

        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Add/Edit Course Material">
        <g:link class="nav-link" action="selectcourseanduploadcategory" controller="CourseMaterial" >
        <span class="fa-stack">

                    <span class="fa fa-circle-o fa-stack-2x"></span>
                    <strong class="fa-stack-1x">
                        4
                    </strong>
                </span>
             <span class="nav-link-text">Add/Edit Course Material</span>
        </g:link>

        </li>
<li class="nav-item" data-toggle="tooltip" data-placement="right" title="Add/Edit Course Video">
        <g:link class="nav-link" action="selectcourseanduploadcategory" controller="CourseVideos" >
        <span class="fa-stack">

                    <span class="fa fa-circle-o fa-stack-2x"></span>
                    <strong class="fa-stack-1x">
                        5
                    </strong>
                </span>
             <span class="nav-link-text">Add/Edit Course Video</span>
        </g:link>

        </li>
    <!--    <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Add/Edit Course Video">
          <a class="nav-link" href="#" onclick="callCourseVideo()">
           <span class="fa-stack">

                       <span class="fa fa-circle-o fa-stack-2x"></span>
                       <strong class="fa-stack-1x">
                           5
                       </strong>
                   </span>
            <span class="nav-link-text">Add/Edit Course Video</span>
          </a>
        </li>
-->
 <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Summary">
          <a class="nav-link" href="#" onclick="summary(${cid.id})">
           <span class="fa-stack">

                       <span class="fa fa-circle-o fa-stack-2x"></span>
                       <strong class="fa-stack-1x">
                           6
                       </strong>
                   </span>
            <span class="nav-link-text">Course Summary</span>
          </a>
        </li>
     </ul>

    <ul class="navbar-nav sidenav-toggler" style="background-color:white">
      <li class="nav-item">
        <a class="nav-link text-center blink" id="sidenavToggler" style="padding: 8px;">
          <i class="fa fa-wa fa-exchange" style="color:blue"></i>
        </a>
      </li>
    </ul>

    <ul class="navbar-nav ml-auto" >
      <li class="nav-item dropdown">

        <a class="nav-link dropdown-toggle mr-lg-2"  href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Hello, ${session.fname} <i class="fa fa-bars" aria-hidden="true""></i>
          <span class="d-lg-none">Dashboard</span>

        </a>

        <div class="dropdown-menu user-profile-cursor">
          <a class="dropdown-item small" href="${request.contextPath}/instructor/profile"> <i class="fa fa-user"></i>&nbsp;User Profile</a><div class="dropdown-divider"></div>
          <a class="dropdown-item small" onclick="changepass()" data-toggle="modal" data-target="#changeUserPasswordModal"> <i class="fa fa-key"></i>&nbsp;Change Password</a> <div class="dropdown-divider"></div>
          <a class="dropdown-item small" href="${request.contextPath}/login/logout"><i class="fa fa-sign-out"></i>&nbsp;Sign Out</a>

        </div>

      </li>

    </ul>

  </div> &nbsp;&nbsp;&nbsp;&nbsp;
</nav>
<!-- End of Navigation --------- --------------------------------------------- -->
<br> <br>
<div class="content-wrapper" id="page">
  <div class="container-fluid">
   <div  style="border-radius:5px; position: absolute; top: 50px; right: 10px;" class="bg-warning">
      <a class="btn btn-sm" style="color: black" href="${request.contextPath}/instructor/instructor"> <i class="fa fa-home" aria-hidden="true"></i> HOME </a>
    </div> <br>
    <!-- container-fluid-->
    <div id="ajaxcall">
<!--  This will disappear -->
    </div>
    <div id="editgrade">

    </div>
<!-- begins progress chart -->
<!-- ends progress chart -->
<g:if test="${flash.message}">
   <p class="error"> <font size="3" color="red">${flash.message}</font> </p>
</g:if>
     <h5> List of current offered courses </h5>

                 <div class="row">

                 <g:if test="${!(coffr.isEmpty())}">

                      <g:each var="coffrobj" status="x" in="${coffr}">
                          <g:if test="${coffrobj.isActive}">
                          <div class="col-sm-3">
                                  <div class="card">
<div class="card-header bg-default"> click here&nbsp;<i style="font-size: 12px" class="fa fa-arrow-right"></i> <g:link controller="CourseOffering" class="btn btn-sm btn-click-here"  action="menuOfSelectedCourseOffered" params="[coffrid:coffrobj.id]" title="click here">${coffrobj.course.course_name} &nbsp; </g:link>                                          </div>

                                           <div class="card-body">
                                             <img src="${createLinkTo(dir:'assets/images/course', file: 'default.jpg' )}" alt="Card image" style="width:100%; max-height:100px" />

                                           <hr>

                                           <a href="#" style="float: left" onclick="studentInfo(${coffrobj.id})" class="btn-sm btn-warning"><span>Student Info</span></a>
                                           <p style="float:right"><i class="fa fa-bookmark fa-1x"></i>&nbsp;${coffrobj.batch}</p>
                                           <p style="clear: both;font-size: 14px;"><g:if test="${commoncode[x]!=null}">classscode::${commoncode[x].code}</g:if></p>
                                         </div>

                                        </div>

                          </div>
                          </g:if>
                          <br>
                      </g:each>
                          </g:if>
                  </div> <!-- End of row-->

                  <h5> List of archived courses </h5>

                                   <div class="row">

                                   <g:if test="${!(coffr.isEmpty())}">

                                        <g:each var="coffrobj" in="${coffr}">
                                            <g:if test="${!coffrobj.isActive}">
                                            <div class="col-sm-3">
                                                    <div class="card">
                                                          <div class="card-header bg-default"> <g:link controller="CourseOffering" action="menuOfSelectedCourseOffered" params="[coffrid:coffrobj.id]">${coffrobj.course.course_name}</g:link>

                                                            </div>
                                                             <div class="card-body">
                                                               <img src="${createLinkTo(dir:'assets/images/course', file: 'default.jpg' )}" alt="Card image" style="width:100%; max-height:100px" />

                                                             <hr>
                                                              <a href="#" style="float: left" onclick="studentInfo(${coffrobj.id})" class="btn-sm btn-warning"><span>Student Info</span></a>
                                                            <p style="float:right"><i class="fa fa-bookmark fa-1x"></i>&nbsp;${coffrobj.batch}</p>
                                                           </div>

                                                          </div>

                                            </div>
                                            </g:if>
                                            <br>
                                        </g:each>
                                            </g:if>
                                    </div> <!-- End of row-->
                    <br><br>


  </div><!-- End of container-fluid-->

</div> <!-- End of content-wrapper-->


<!-- Start of Footer ------------------------------------ -->
<footer class="sticky-footer">
  <div class="container">
    <div class="text-center">
      <small style="font-size:14px">Copyright © VOLP</small>
    </div>
  </div>
</footer>

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top" >
  <i class="fa fa-angle-up"></i>
</a>
<!-- End Of Footer --------------------------------------- -->

<script>
function studentInfo(coffrid){
   // alert("coffrid:"+coffrid)
    var xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function() {
           // alert("1"+this.status)
                if (this.readyState == 4 && this.status == 200) {
               // alert("resp"+this.responseText)
                    document.getElementById("page").innerHTML = this.responseText;

                }
            };
            xmlhttp.open("GET", "${request.contextPath}/courseOfferingLearner/studentInfo?coffrid=" + coffrid, true);
            xmlhttp.send();
}

function studentDelete(cofflearnerid)
{
//alert("cofflearnerid:"+cofflearnerid);
    if (confirm('Are you sure you want to delete?')) {
            var xmlhttp = new XMLHttpRequest();
                    xmlhttp.onreadystatechange = function() {
                   // alert("1"+this.status)
                        if (this.readyState == 4 && this.status == 200) {
                       // alert("resp"+this.responseText)
                            document.getElementById("page").innerHTML = this.responseText;

                        }

                    };
                    xmlhttp.open("GET", "${request.contextPath}/courseOfferingLearner/studentDelete?cofflearnerid="+cofflearnerid, true);
                    xmlhttp.send();
    } else {
        // Do nothing!
    }

}
  function add_feed()
  {
    var div1 = document.createElement('div');
          // Get template data
          div1.innerHTML = document.getElementById('newlinktpl').innerHTML;
          // append to our form, so that template data
          //become part of form
          document.getElementById('newlink').appendChild(div1);
        }
        function add_feeddes()
        {
          var div1 = document.createElement('div');
                  // Get template data
                  div1.innerHTML = document.getElementById('newlinktpldes').innerHTML;
                  // append to our form, so that template data
                  //become part of form
                  document.getElementById('newlinkdes').appendChild(div1);
                }
                function add_feedurl()
                {
                  var div1 = document.createElement('div');
              // Get template data
              div1.innerHTML = document.getElementById('newlinktplurl').innerHTML;
              // append to our form, so that template data
              //become part of form
              document.getElementById('newlinkurl').appendChild(div1);
            }
            function add_feedpre()
            {
              var div1 = document.createElement('div');
              // Get template data
              div1.innerHTML = document.getElementById('newlinktplpre').innerHTML;
              // append to our form, so that template data
              //become part of form
              document.getElementById('newlinkpre').appendChild(div1);
            }



            function editCourse(){
              var xmlhttp = new XMLHttpRequest();
              xmlhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
            //alert("1");
            document.getElementById("ajaxcall").innerHTML = this.responseText;
//alert("1"+this.responseText);
}
};
xmlhttp.open("GET", "${request.contextPath}/instructor/getCourseDetails?cid="+${cid.id}, true);
xmlhttp.send();
}

function callCourseOutcome(crscodename){
//("crscodename:"+crscodename)
  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
            //alert("1");
            document.getElementById("ajaxcall").innerHTML = this.responseText;
//alert("1"+this.responseText);
}
};
xmlhttp.open("GET", "${request.contextPath}/courseOutcomes/addcourseoutcomes?coursecodecoursename="+crscodename, true);
xmlhttp.send();
}
function callCourseOutline(){
  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
            //alert("1");
            document.getElementById("ajaxcall").innerHTML = this.responseText;
//alert("1"+this.responseText);
}
};
xmlhttp.open("GET", "${request.contextPath}/courseOutline/selectcourse", true);
xmlhttp.send();
}
function callCourseTopic(){
//alert("11");
var xmlhttp = new XMLHttpRequest();
xmlhttp.onreadystatechange = function() {
  if (this.readyState == 4 && this.status == 200) {
            //alert("1");
            document.getElementById("ajaxcall").innerHTML = this.responseText;
//alert("1"+this.responseText);
}
};
xmlhttp.open("GET", "${request.contextPath}/CourseTopic/selectcourse", true);
xmlhttp.send();
}

/*function callCourseMaterial(){
//alert("11");
var xmlhttp = new XMLHttpRequest();
xmlhttp.onreadystatechange = function() {
  if (this.readyState == 4 && this.status == 200) {
            //alert("1");
            document.getElementById("ajaxcall").innerHTML = this.responseText;
//alert("1"+this.responseText);
}
};
xmlhttp.open("GET", "${request.contextPath}/CourseMaterial/selectcourseanduploadcategory", true);
xmlhttp.send();
}*/
function callCourseVideo(){
//alert("11");
var xmlhttp = new XMLHttpRequest();
xmlhttp.onreadystatechange = function() {
  if (this.readyState == 4 && this.status == 200) {
            //alert("1");
            document.getElementById("ajaxcall").innerHTML = this.responseText;
//alert("1"+this.responseText);
}
};
xmlhttp.open("GET", "${request.contextPath}/CourseVideos/selectcourseanduploadcategory", true);
xmlhttp.send();
}

function summary(cid){
//alert("cid:"+cid);
var xmlhttp = new XMLHttpRequest();
xmlhttp.onreadystatechange = function() {
  if (this.readyState == 4 && this.status == 200) {
            //alert("1");
            document.getElementById("ajaxcall").innerHTML = this.responseText;
//alert("1"+this.responseText);
}
};
xmlhttp.open("GET", "${request.contextPath}/instructor/summary?cid="+cid, true);
xmlhttp.send();
}

function callCourseOffering(){

  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {

      document.getElementById("ajaxcall").innerHTML = this.responseText;

    }
  };
  xmlhttp.open("GET", "${request.contextPath}/CourseOffering/selectcourse", true);
  xmlhttp.send();
}
// fetch Video script code -----------------------------------------
function fetchvideo()
{
 var coursecodecoursename = document.getElementById("coursecodecoursename").value;
 var xmlhttp = new XMLHttpRequest();
 xmlhttp.onreadystatechange = function() {
   if (this.readyState == 4 && this.status == 200) {
     document.getElementById("material4").innerHTML = this.responseText;
   }
 };
 xmlhttp.open("GET", "${request.contextPath}/courseVideos/addeditcoursevideo?coursecodecoursename=" + coursecodecoursename, true);
 xmlhttp.send();
 return(false)
}
function deletevdo(id)
{

   // alert("delete");
    var xmlhttp = new XMLHttpRequest();
     xmlhttp.onreadystatechange = function() {
       if (this.readyState == 4 && this.status == 200) {
         document.getElementById("ajaxcall").innerHTML = this.responseText;
       }
     };
     xmlhttp.open("GET", "${request.contextPath}/courseVideos/deletecoursevideo?id=" + id, true);
     xmlhttp.send();
}
function callme()
{
 // var coursecodecoursename = document.getElementById("coursecodecoursename").value;
  var uploadcategory = document.getElementById("uploadcategory").value;
  //alert(uploadcategory)
  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("material1").innerHTML = this.responseText;
    }
  };
  //xmlhttp.open("GET", "${request.contextPath}/courseVideos/processcoursevideo?coursecodecoursename=" + coursecodecoursename +"&uploadcategory="+uploadcategory, true);
  xmlhttp.open("GET", "${request.contextPath}/courseVideos/processcoursevideo?uploadcategory=" + uploadcategory, true);
  xmlhttp.send();
  return(false)
}
function callmetoo()
{
  var courseoutline = document.getElementById("courseoutline").value;
  //alert(courseoutline)
  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("material2").innerHTML = this.responseText;
    }
  };
  xmlhttp.open("GET", "${request.contextPath}/courseVideos/furtherprocesscoursevideo?courseoutline=" + courseoutline, true);
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
             //   xmlhttp.open("GET", "${request.contextPath}/courseVideos/savecoursevideo?coursetopic=" + coursetopic, true);
             //   xmlhttp.send();
             return(false)
           }


// -------------End of Fetch Video Ajax Call-------------------------

 // -------------------------Assessment Grade Ajax Call---------------------------

 function getCourseOfferingForGrade(){
 ///alert("hi");

 var xmlhttp = new XMLHttpRequest();
 xmlhttp.onreadystatechange = function() {
  if (this.readyState == 4 && this.status == 200) {
                      //alert("1");
                      document.getElementById("ajaxcall").innerHTML = this.responseText;
                      var x = document.getElementById("editgrade");
                          // alert("x:"+x)
                          x.style.display = "none";
          //alert("1"+this.responseText);
        }
      };
      xmlhttp.open("GET", "${request.contextPath}/courseOffering/getCourseOfferingForGrade?cid="+${cid.id}, true);
      xmlhttp.send();
    }
  /*  function setGrade(coffr){
     var xmlhttp = new XMLHttpRequest();
     xmlhttp.onreadystatechange = function() {
       if (this.readyState == 4 && this.status == 200) {
                       //alert("1");
                       document.getElementById("ajaxcall").innerHTML = this.responseText;
           //alert("1"+this.responseText);
         }
       };
       xmlhttp.open("GET", "${request.contextPath}/assessmentGrade/setGrade?coffr="+coffr, true);
       xmlhttp.send();
     } */
     /*function editGrade(ag,coffr){
      var x = document.getElementById("editgrade");
                         //  alert("x:"+x)
                         x.style.display = "block";
                         var xmlhttp = new XMLHttpRequest();
                         xmlhttp.onreadystatechange = function() {
                           if (this.readyState == 4 && this.status == 200) {
                       //alert("1");
                       document.getElementById("editgrade").innerHTML = this.responseText;
           //alert("1"+this.responseText);
         }
       };
       xmlhttp.open("GET", "${request.contextPath}/assessmentGrade/editGrade?ag="+ag + "&coffr="+coffr, true);
       xmlhttp.send();
     }*/
// -------------------------End Assessment Grade Ajax Call---------------------------

// ------------------------- Week Ajax Call---------------------------
function callWeek(crscodename){
//alert("crscodename:"+crscodename);
                         var xmlhttp = new XMLHttpRequest();
                         xmlhttp.onreadystatechange = function() {
                           if (this.readyState == 4 && this.status == 200) {
                       //alert("1");
                       document.getElementById("ajaxcall").innerHTML = this.responseText;
           //alert("1"+this.responseText);
         }
       };
       xmlhttp.open("GET", "${request.contextPath}/Week/processweek?coursecodecoursename="+crscodename, true);
       xmlhttp.send();
     }
// -------------------------End Week Ajax Call---------------------------
// ------------------------- Session Ajax Call---------------------------
function callSession(crscodename){

                         var xmlhttp = new XMLHttpRequest();
                         xmlhttp.onreadystatechange = function() {
                           if (this.readyState == 4 && this.status == 200) {
                       //alert("1");
                       document.getElementById("ajaxcall").innerHTML = this.responseText;
           //alert("1"+this.responseText);
         }
       };
       xmlhttp.open("GET", "${request.contextPath}/Session/processession?coursecodecoursename="+crscodename, true);
       xmlhttp.send();
     }
// -------------------------End Session Ajax Call---------------------------
// ------------------------- Assignment Ajax Call---------------------------
function callAssigment(crscodename){

                         var xmlhttp = new XMLHttpRequest();
                         xmlhttp.onreadystatechange = function() {
                           if (this.readyState == 4 && this.status == 200) {
                       //alert("1");
                       document.getElementById("ajaxcall").innerHTML = this.responseText;
           //alert("1"+this.responseText);
         }
       };
       xmlhttp.open("GET", "${request.contextPath}/Assignment/processassignment?coursecodecoursename="+crscodename, true);
       xmlhttp.send();
     }
// -------------------------End Assignment Ajax Call---------------------------
function fetchcourseofferingweek()
{
   var coursecodecoursename = document.getElementById("coursecodecoursename").value;
   var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
                       if (this.readyState == 4 && this.status == 200) {
                           document.getElementById("material1").innerHTML = this.responseText;
                       }
                   };
                   xmlhttp.open("GET", "${request.contextPath}/Week/processweek?coursecodecoursename=" + coursecodecoursename, true);
                   xmlhttp.send();
           return(false)
}

function fetchcourseofferingsession()
{
   var coursecodecoursename = document.getElementById("coursecodecoursename").value;
   var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
                       if (this.readyState == 4 && this.status == 200) {
                           document.getElementById("material1").innerHTML = this.responseText;
                       }
                   };
                   xmlhttp.open("GET", "${request.contextPath}/Session/processession?coursecodecoursename=" + coursecodecoursename, true);
                   xmlhttp.send();
           return(false)
}

function fetchcourseofferingassign()
{
   var coursecodecoursename = document.getElementById("coursecodecoursename").value;
   var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
                       if (this.readyState == 4 && this.status == 200) {
                           document.getElementById("material1").innerHTML = this.responseText;
                       }
                   };
                   xmlhttp.open("GET", "${request.contextPath}/Assignment/processassignment?coursecodecoursename=" + coursecodecoursename, true);
                   xmlhttp.send();
           return(false)
}
function furtherprocessweek(id){
     var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
                           if (this.readyState == 4 && this.status == 200) {
                               document.getElementById("ajaxcall").innerHTML = this.responseText;
                           }
                       };
                       xmlhttp.open("GET", "${request.contextPath}/week/furtherprocessweek?id=" + id, true);
                       xmlhttp.send();
}
function updateweeks(id){
var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
                           if (this.readyState == 4 && this.status == 200) {
                               document.getElementById("ajaxcall").innerHTML = this.responseText;
                           }
                       };
                       xmlhttp.open("GET", "${request.contextPath}/week/updateweeks?id=" + id, true);
                       xmlhttp.send();
}
function furtherprocesssession(id){
//alert("Hi"+id);
var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
                           if (this.readyState == 4 && this.status == 200) {
                               document.getElementById("ajaxcall").innerHTML = this.responseText;
                           }
                       };
                       xmlhttp.open("GET", "${request.contextPath}/session/furtherprocesssession?id=" + id, true);
                       xmlhttp.send();
}
//-------------------Course Material Ajax Call ------------------------------
/* function fetchmaterial()
{
   var coursecodecoursename = document.getElementById("coursecodecoursename").value;
   var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
                       if (this.readyState == 4 && this.status == 200) {
                           document.getElementById("material4").innerHTML = this.responseText;
                       }
                   };
                   xmlhttp.open("GET", "${request.contextPath}/courseMaterial/addeditcoursematerial?coursecodecoursename=" + coursecodecoursename, true);
                   xmlhttp.send();
           return(false)
}

function callmeCourseMaterial()
{
document.getElementById("crs_material").innerHTML = "will change"
//alert("callmeCourseMaterial")
//document.getElementById("material1").innerHTML = "will change"
        var coursecodecoursename = document.getElementById("coursecodecoursename").value;
        var uploadcategory = document.getElementById("uploadcategory").value;
        var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange = function() {
                    if (this.readyState == 4 && this.status == 200) {

                        document.getElementById("crs_material").innerHTML = this.responseText;
                        //alert("from proc cm"+this.responseText)
                    }
                };
                xmlhttp.open("GET", "${request.contextPath}/courseMaterial/processcoursematerial?coursecodecoursename=" + coursecodecoursename +"&uploadcategory="+uploadcategory, true);
                xmlhttp.send();
        //return(false)
  }

function callmetooCourseMaterial()
{
        var courseoutline = document.getElementById("courseoutline").value;
        var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange = function() {
                    if (this.readyState == 4 && this.status == 200) {
                        document.getElementById("material2").innerHTML = this.responseText;
                    }
                };
                xmlhttp.open("GET", "${request.contextPath}/courseMaterial/furtherprocesscoursematerial?courseoutline=" + courseoutline, true);
                xmlhttp.send();
        return(false)
  }
  */
//--------------------------------------------------------------------------


function checkpwd(){

var pwd1 = document.getElementById("newpassword").value;
var pwd2 = document.getElementById("confirmpassword").value;
//alert(pwd1+" "+pwd2)
if(pwd1==pwd2)
    return true;
else{
alert("Oops password not matched!!!")
     return false;

     }
return false;
}
function changepass()
{
    var xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                  document.getElementById("page").innerHTML = this.responseText;
             }
             };
              xmlhttp.open("GET", "${request.contextPath}/Login/changepassword", true);
               xmlhttp.send();
}
function checkImageSize(file){
   //alert("Hello SmartPic"+file.value)
        var file1= file.value;
        var reg = /(.*?)\.(jpg|jpeg)$/;

       var FileSize = file.files[0].size / 1024 / 1024; // in MB

               if ((FileSize > 0.12) || (!file1.match(reg))) {
                   if(FileSize > 0.12){
                    alert('File size exceeds than 128KB use less than 128KB');
                    file.value="";
                   }
                   else if(!file1.match(reg)){
                    alert('File type is not jpg/jpeg use only jpg/jpeg');
                    file.value="";
                   }
                  // $(file).val(''); //for clearing with Jquery
               }
   }
   function checkVideoDuration(file){
      //alert("Hello SmartPic"+file.value)
      var duration=file.value;
      var splitString = duration.split(".");
      var videoSec = splitString[1];
      //alert("Hello "+videoSec)
        if(videoSec > 60){
           alert('Please enter video seconds less than 60');
           file.value="";
      }
   }
</script>
<asset:javascript src="jquery.min.js"/>

<asset:javascript src="bootstrap.bundle.min.js"/>
<asset:javascript src="jquery.easing.min.js"/>
<asset:javascript src="sb-admin.min.js"/>

<body id="empty"></div>
</body>
</html>

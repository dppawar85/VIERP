
    <!doctype html>
    <html lang="en" class="no-js">
    <head>

      <meta name="viewport" content="width=device-width, initial-scale=1"/>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
      <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
      <title>
        VOLP || Menu
      </title>

           <asset:stylesheet src="bootstrap.min.css"/>
           <asset:stylesheet src="font-awesome.min.css"/>
           <asset:stylesheet src="sb-admin.css"/>
           <asset:stylesheet src="courseInstructor.css"/>
           <asset:stylesheet src="menus.css" rel="stylesheet" />

          <asset:javascript src="jquery.min.js"/>
       <asset:javascript src="bootstrap.bundle.min.js"/>
       <asset:javascript src="jquery.easing.min.js"/>
       <asset:javascript src="sb-admin.min.js"/>


<style>
a{
text-decoration:none; !important
}
.courseIndexh6{
padding: 8px;
margin:0px;
text-decoration: underline;
text-align: center
}
.line{
 height: 1px;
 background-color: white;
 border:1px solid white;
}

</style>
    </head>
    <body id="page-top">
     <!-- ---------------------------Start of Navigation------------------------------------------ -->
<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top" id="mainNav" >
  <a class="navbar-brand" href="${request.contextPath}/instructor/instructor"><i class="fa fa-fw fa-home"></i>VOLP :: Course Instructor::${coffr.course.course_code}:${coffr.course.course_name}:${coffr.batch}</a>
  <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarResponsive">

    <ul class="navbar-nav navbar-sidenav nav-pills nav-stacked sidenavPanel" id="exampleAccordion" >
       <h6 class="courseIndexh6"> Course Index</h6>
    <nav class="navigation" >
         <g:each var="obj" in="${menu}">
         	<ul class="mainmenu" >

         		<li><a  style="color: blue; line-height: .9" class="nav-link" href="#" onclick="callOutline(${obj[0].id});">
                    ${obj[0].outline}&nbsp;<i style="color: black" class="fa fa-sort-desc"></i>
                     </a>
         		<ul class="submenu">
         			<g:each var="sobj" in="${obj[1]}">
         			<li><a style="color: red; line-height: .9" style="color: red" class="nav-link" href="#"  onclick="callTopic(${sobj.id});">
                        <i style="color: black; font-size: 10px" class="fa fa-arrow-right"></i>&nbsp;${sobj.topic}
                    </a></li>

         			</g:each>
         		</ul>
         		</li>
         	</ul>
         </g:each>
    </nav>

    <p class="line"> </p>
    <h6 class="courseIndexh6"> Course Offering Dashboard</h6>
    <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Set Course Fees">
            <a class="nav-link" href="#" onclick="setFees(${coffr.id})" >
              <span class="fa-stack">

                          <span class="fa fa-circle-o fa-stack-2x"></span>
                          <strong class="fa-stack-1x">
                             1
                          </strong>
                      </span>
              <span class="nav-link-text">Set Course Fees</span>
            </a>
          </li>
    <li class="nav-item"  data-toggle="tooltip" data-placement="right" title="Assesment Grade">

                      <a class="nav-link" href="#" onclick="setGrade(${coffr.id})">
                      <span class="fa-stack">

                                  <span class="fa fa-circle-o fa-stack-2x"></span>
                                  <strong class="fa-stack-1x">
                                      2
                                  </strong>
                              </span>
                      <span class="nav-link-text">Define Grading Scheme</span>
                      </a>

                    </li>
<li class="nav-item" data-toggle="tooltip" data-placement="right" title="Add/Edit Week">

               <a class="nav-link" onclick="week(${coffr.id})">
               <span class="fa-stack">

                           <span class="fa fa-circle-o fa-stack-2x"></span>
                           <strong class="fa-stack-1x">
                               3
                           </strong>
                       </span>
               <span class="nav-link-text">Add/Edit Week</span>
               </a>

             </li>
     <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Add/Edit Schedule">

                    <a class="nav-link" onclick="furtherprocesssession(${coffr.id})">
                    <span class="fa-stack">

                                <span class="fa fa-circle-o fa-stack-2x"></span>
                                <strong class="fa-stack-1x">
                                    4
                                </strong>
                            </span>
                    <span class="nav-link-text">Add/Edit Schedule</span>
                    </a>

                  </li>
    <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Course Announcement">
    <!-- on click /courseAnnouncement/setCourseAnnouncement?coffrid=" +coffrid -->
                <a class="nav-link" href="#" onclick="courseAnnounce(${coffr.id})">
                <span class="fa-stack">

                            <span class="fa fa-circle-o fa-stack-2x"></span>
                            <strong class="fa-stack-1x">
                                5
                            </strong>
                        </span>
                <span class="nav-link-text">Create Announcement</span>
                </a>

              </li>


<li class="nav-item" data-toggle="tooltip" data-placement="right" title="Create Assignment">
               <a class="nav-link" href="${request.contextPath}/assignment/addassignment?coffrid=${coffr.id}" >
               <span class="fa-stack">

                           <span class="fa fa-circle-o fa-stack-2x"></span>
                           <strong class="fa-stack-1x">
                              6
                           </strong>
                       </span>
               <span class="nav-link-text">Create Assignment</span>
               </a>

             </li>
             <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Edit Assignment">
            <a class="nav-link" href="${request.contextPath}/assignment/selectassignmenttype?coffrid=${coffr.id}" >
             <span class="fa-stack">

             <span class="fa fa-circle-o fa-stack-2x"></span>
             <strong class="fa-stack-1x">
             7
             </strong>
             </span>
             <span class="nav-link-text">Edit Assignment</span>
             </a>
             </li>
             <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Evaluate Assignment">
             				<g:form  url="[action:'listallassignments',controller:'assignmentSubmission']">
             				<g:hiddenField name="coffrid" value="${coffr.id}"/>
             				<button class="btn btn-link" type="submit"><span class="fa-stack">

                                <span class="fa fa-circle-o fa-stack-2x"></span>
                                <strong class="fa-stack-1x">
                                8
                                </strong>
                                </span>
                                <span class="nav-link-text">Evaluate Assignment</span></button>
             				</g:form>


             </li>
             <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Request To Delete Course">
                     <g:form  url="[action:'requestToDeleteCourseOffering',controller:'Instructor']">
                       <g:hiddenField name="coffrid" value="${coffr.id}"/>
                       <button class="btn btn-link" type="submit"><span class="fa-stack">
                         <span class="fa fa-circle-o fa-stack-2x"></span>
                           <strong class="fa-stack-1x">
                             9
                           </strong>
                         </span>
                         <span class="nav-link-text">Request To Delete Course</span>
                       </button>
                     </g:form>
                   </li>

<p class="line"> </p>
<li class="nav-item" data-toggle="tooltip" data-placement="right" title="Start Discussion">

<g:link controller="forumTopic" action="discussionForum" params="[coffrid: coffr.id,type:'Instructor']" target="_blank" method="post">
<!-- action=redirect -->
 <i class="fa fa-fw fa-book"></i>
                            <span class="nav-link-text">Start Discussion</span>

</g:link>
                          </li>

      </ul>

      <ul class="navbar-nav sidenav-toggler" style="background-color: white;">
        <li class="nav-item">
          <a class="nav-link text-center" id="sidenavToggler" >
            <i class="fa fa-fw fa fa-exchange"></i>
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
            <a class="dropdown-item small" onclick="invitetolearner(${coffr.id});"data-toggle="modal" data-target="#userProfileModal"> <i class="fa fa-share-alt"></i>&nbsp;Send Invitation</a><div class="dropdown-divider"></div>
            <a class="dropdown-item small" onclick="changepass()" data-toggle="modal" data-target="#changeUserPasswordModal"> <i class="fa fa-key"></i>&nbsp;Change Password</a> <div class="dropdown-divider"></div>
            <a class="dropdown-item small" href="${request.contextPath}/login/logout"><i class="fa fa-sign-out"></i>&nbsp;Sign Out</a>

          </div>

        </li>



      </ul>

    </div>
  </nav>
  <!-- End of Navigation --------- --------------------------------------------- -->
     <br> <br>
     <div class="content-wrapper" id="page">
      <div class="container-fluid">
      <div  style="border-radius:5px; position: absolute; top: 50px; right: 10px;" class="bg-warning">
            <a class="btn btn-sm" style="color: black" href="${request.contextPath}/instructor/instructor"> <i class="fa fa-home" aria-hidden="true"></i> HOME </a>
       </div> <hr />
        <!-- container-fluid-->
<g:if test="${flash.message}">
   <p class="error"> <font size="3" color="red">${flash.message}</font> </p>
</g:if>
<div id="material">


</div>
<div id="edit">
</div>
<div id="updateMe">
</div>
<div id="editGrade"></div>



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
      <i class="fa fa-angle-up" ></i>
    </a>
<!-- End Of Footer --------------------------------------- -->
<script>
<!-- Topic -->
    function callTopic(topicid){
       // alert("T:"+topicid);
       document.getElementById("edit").style.display = "none"
       document.getElementById("editGrade").style.display = "none"
       document.getElementById("updateMe").style.display = "none"
        var x = document.getElementById("material");
                x.style.display = "block";
        var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange = function() {
                    if (this.readyState == 4 && this.status == 200) {
                        document.getElementById("material").innerHTML = this.responseText;
                    }
                };
                xmlhttp.open("GET", "${request.contextPath}/course/showOutLineMaterial?topicid=" +topicid +"&ot=t", true);
                xmlhttp.send();
    }
    function editMaterialTopic(mid){
      //  alert("TM:"+mid)
               document.getElementById("edit").style.display = "block"
               document.getElementById("editGrade").style.display = "none"
               document.getElementById("updateMe").style.display = "none"
                var x = document.getElementById("material");
                        x.style.display = "none";
                var xmlhttp = new XMLHttpRequest();
                                            xmlhttp.onreadystatechange = function() {
                                                if (this.readyState == 4 && this.status == 200) {
                                                    document.getElementById("edit").innerHTML = this.responseText;
                                                }
                                            };
                                            xmlhttp.open("GET", "${request.contextPath}/course/editCourseMaterial?mid=" +mid, true);
                                            xmlhttp.send();
    }
    function editVdoTopic(mid){
                //alert("mid:"+mid);

                  document.getElementById("edit").style.display = "block"
                  document.getElementById("editGrade").style.display = "none"
                  document.getElementById("updateMe").style.display = "none"
                   var x = document.getElementById("material");
                           x.style.display = "none";
                var xmlhttp = new XMLHttpRequest();
                                            xmlhttp.onreadystatechange = function() {
                                                if (this.readyState == 4 && this.status == 200) {
                                                    document.getElementById("edit").innerHTML = this.responseText;
                                                }
                                            };
                                            xmlhttp.open("GET", "${request.contextPath}/course/editCourseVdo?mid=" +mid, true);
                                            xmlhttp.send();
    }
<!-- OutLine -->
    function callOutline(outlineid){

           document.getElementById("edit").style.display = "none"
           document.getElementById("editGrade").style.display = "none"
           document.getElementById("updateMe").style.display = "none"
            var x = document.getElementById("material");
                    x.style.display = "block";
            //alert("O:"+outlineid);
            var xmlhttp = new XMLHttpRequest();
                            xmlhttp.onreadystatechange = function() {
                                if (this.readyState == 4 && this.status == 200) {
                                    document.getElementById("material").innerHTML = this.responseText;
                                }
                            };
                            xmlhttp.open("GET", "${request.contextPath}/course/showOutLineMaterial?olid=" +outlineid +"&ot=o", true);
                            xmlhttp.send();
        }
    function editMaterial(mid){
        //alert("mid:"+mid);

          document.getElementById("edit").style.display = "block"
          document.getElementById("editGrade").style.display = "none"
          document.getElementById("updateMe").style.display = "none"
           var x = document.getElementById("material");
                   x.style.display = "none";
        var xmlhttp = new XMLHttpRequest();
                                    xmlhttp.onreadystatechange = function() {
                                        if (this.readyState == 4 && this.status == 200) {
                                            document.getElementById("edit").innerHTML = this.responseText;
                                        }
                                    };
                                    xmlhttp.open("GET", "${request.contextPath}/course/editCourseMaterial?mid=" +mid, true);
                                    xmlhttp.send();
    }
     function editVdo(mid){
            //alert("mid:"+mid);

              document.getElementById("edit").style.display = "block"
              document.getElementById("editGrade").style.display = "none"
              document.getElementById("updateMe").style.display = "none"
               var x = document.getElementById("material");
                       x.style.display = "none";
            var xmlhttp = new XMLHttpRequest();
                                        xmlhttp.onreadystatechange = function() {
                                            if (this.readyState == 4 && this.status == 200) {
                                                document.getElementById("edit").innerHTML = this.responseText;
                                            }
                                        };
                                        xmlhttp.open("GET", "${request.contextPath}/course/editCourseVdo?mid=" +mid, true);
                                        xmlhttp.send();
        }
        function courseAnnounce(coffrid){
                var params="coffrid="+coffrid
               document.getElementById("edit").style.display = "none"
               document.getElementById("editGrade").style.display = "none"
               document.getElementById("updateMe").style.display = "none"
                var x = document.getElementById("material");
                        x.style.display = "block";
                 //alert("coffrid:"+coffrid);
                        var xmlhttp = new XMLHttpRequest();
                                                                            xmlhttp.open("POST", "${request.contextPath}/courseAnnouncement/setCourseAnnouncement", true);
                                                    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

                                                    xmlhttp.onreadystatechange = function() {
                                                        if (this.readyState == 4 && this.status == 200) {
                                                            document.getElementById("material").innerHTML = this.responseText;
                                                        }
                                                    };
                                                  //  xmlhttp.open("GET", "${request.contextPath}/courseAnnouncement/setCourseAnnouncement?coffrid=" +coffrid, true);
                                                   xmlhttp.send(params);
                                                   // xmlhttp.send();
            }
            function editAnnouncement(caid,coffrid){

                  document.getElementById("edit").style.display = "none"
                  document.getElementById("editGrade").style.display = "none"
                  document.getElementById("updateMe").style.display = "none"
                   var x = document.getElementById("material");
                           x.style.display = "block";
                var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                     document.getElementById("material").innerHTML = this.responseText;
                }
                };
                xmlhttp.open("GET", "${request.contextPath}/courseAnnouncement/editAnnouncement?caid=" +caid+"&coffrid="+coffrid, true);
                xmlhttp.send();
            }
function setGrade(coffr){

       document.getElementById("edit").style.display = "none"
       document.getElementById("editGrade").style.display = "none"
       document.getElementById("updateMe").style.display = "none"
        var x = document.getElementById("material");
                x.style.display = "block";     var xmlhttp = new XMLHttpRequest();
     xmlhttp.onreadystatechange = function() {
       if (this.readyState == 4 && this.status == 200) {
                       //alert("1");
                       document.getElementById("material").innerHTML = this.responseText;
           //alert("1"+this.responseText);
         }
       };
       xmlhttp.open("GET", "${request.contextPath}/assessmentGrade/setGrade?coffr="+coffr, true);
       xmlhttp.send();
     }
function editGrade(ag,coffr){

       document.getElementById("edit").style.display = "block"
       document.getElementById("editGrade").style.display = "none"
       document.getElementById("updateMe").style.display = "none"
        var x = document.getElementById("material");
                x.style.display = "none";
                         var xmlhttp = new XMLHttpRequest();
                         xmlhttp.onreadystatechange = function() {
                           if (this.readyState == 4 && this.status == 200) {
                       //alert("1");
                       document.getElementById("edit").innerHTML = this.responseText;
           //alert("1"+this.responseText);
         }
       };
       xmlhttp.open("GET", "${request.contextPath}/assessmentGrade/editGrade?ag="+ag + "&coffr="+coffr, true);
       xmlhttp.send();
     }
     function deleteGrade(ag,coffr){

                 document.getElementById("edit").style.display = "block"
                 document.getElementById("editGrade").style.display = "none"
                 document.getElementById("updateMe").style.display = "none"
                  var x = document.getElementById("material");
                          x.style.display = "none";
                                   var xmlhttp = new XMLHttpRequest();
                                   xmlhttp.onreadystatechange = function() {
                                     if (this.readyState == 4 && this.status == 200) {
                                 //alert("1");
                                 document.getElementById("edit").innerHTML = this.responseText;
                     //alert("1"+this.responseText);
                   }
                 };
                 xmlhttp.open("GET", "${request.contextPath}/assessmentGrade/deleteGrade?ag="+ag + "&coffr="+coffr, true);
                 xmlhttp.send();
               }
function week(coffrid){

               document.getElementById("edit").style.display = "block"
               document.getElementById("editGrade").style.display = "none"
               document.getElementById("updateMe").style.display = "none"
                var x = document.getElementById("material");
                        x.style.display = "none";
            var xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                  document.getElementById("edit").innerHTML = this.responseText;
             }
             };
              xmlhttp.open("GET", "${request.contextPath}/week/furtherprocessweek?coffrid=" +coffrid, true);
               xmlhttp.send();
        }
function updateweeks(id){
 document.getElementById("edit").style.display = "block"
               document.getElementById("editGrade").style.display = "none"
               document.getElementById("updateMe").style.display = "none"
                var x = document.getElementById("material");
                        x.style.display = "none";
 var xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                  document.getElementById("edit").innerHTML = this.responseText;
             }
             };
              xmlhttp.open("GET", "${request.contextPath}/week/updateweeks?id=" +id, true);
               xmlhttp.send();
}
function invitetolearner(coffrid)
        {
         document.getElementById("edit").style.display = "none"
                       document.getElementById("editGrade").style.display = "none"
                       document.getElementById("updateMe").style.display = "none"
                        var x = document.getElementById("material");
                                x.style.display = "block";
            //alert("Hi::"+coffrid);
            var xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                  document.getElementById("material").innerHTML = this.responseText;
             }
             };
              xmlhttp.open("GET", "${request.contextPath}/sendInvitationToLearner/aceeptemails?coffrid=" +coffrid, true);
               xmlhttp.send();
        }
function furtherprocesssession(coffrid){
//alert("furtherprocesssession");
 document.getElementById("edit").style.display = "block"
               document.getElementById("editGrade").style.display = "none"
               document.getElementById("updateMe").style.display = "none"
                var x = document.getElementById("material");
                        x.style.display = "none";
    var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                      document.getElementById("edit").innerHTML = this.responseText;
                 }
                 };
                  xmlhttp.open("GET", "${request.contextPath}/session/furtherprocesssession?coffrid=" +coffrid, true);
                   xmlhttp.send();
}
function processassignment(coffrid){
//alert("processassignment")
document.getElementById("edit").style.display = "block"
               document.getElementById("editGrade").style.display = "none"
               document.getElementById("updateMe").style.display = "none"
                var x = document.getElementById("material");
                        x.style.display = "none";
    var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                      document.getElementById("edit").innerHTML = this.responseText;
                 }
                 };
                  //xmlhttp.open("GET", "${request.contextPath}/assignment/processassignment?coffrid=" +coffrid, true);
                  xmlhttp.open("GET", "${request.contextPath}/assignment/addassignment?coffrid=" +coffrid, true);

                   xmlhttp.send();
}
</script>
<script>
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
function changeFee(val)
{
  var xmlhttp = new XMLHttpRequest();
         xmlhttp.onreadystatechange = function() {
             if (this.readyState == 4 && this.status == 200) {
                 document.getElementById("test").innerHTML = this.responseText;
             }
         };
         xmlhttp.open("GET", "${request.contextPath}/courseFees/changeFees?Doller="+val, true);
         xmlhttp.send();
}
function setFees(coffr){
       //document.getElementById("edit").style.display = "none"
       //document.getElementById("editGrade").style.display = "none"
       //document.getElementById("updateMe").style.display = "none"
        var x = document.getElementById("material");
                x.style.display = "block";     var xmlhttp = new XMLHttpRequest();
     xmlhttp.onreadystatechange = function() {
       if (this.readyState == 4 && this.status == 200) {
                       //alert("1");
                       document.getElementById("material").innerHTML = this.responseText;
           //alert("1"+this.responseText);
         }
       };
       xmlhttp.open("GET", "${request.contextPath}/courseFees/setCourseFee?coffr="+coffr, true);
       xmlhttp.send();
     }

          function discussionForum(coffrid)
     {
                    document.getElementById("edit").style.display = "block"
                    document.getElementById("editGrade").style.display = "none"
                    document.getElementById("updateMe").style.display = "none"
                     var x = document.getElementById("material");
                             x.style.display = "none";
                     var xmlhttp = new XMLHttpRequest();
                     xmlhttp.onreadystatechange = function() {
                     if (this.readyState == 4 && this.status == 200) {
                           document.getElementById("edit").innerHTML = this.responseText;
                      }
                      };
                       xmlhttp.open("GET", "${request.contextPath}/forumTopic/discussionForum?coffrid=" +coffrid+"&type=Instructor", true);
                        xmlhttp.send();
     }
</script>

<script>
function validateGradeForm1() {

  var form = document.getElementById('notice').value;
  if(form=="" || form==null)
  {alert("Please Enter Notice")
  return false;}
  else
  {//alert("else")
  return true;}
       /* if(form.validate()) {
          return true;
        }else {
          alert('Enter Mandatory Data!!!');
          return false;
        }*/
          return true;
  }
  function validateGradeForm() {

    var form = document.getElementById('myform');

         /* if(form.validate()) {
            return true;
          }else {
            alert('Enter Mandatory Data!!!');
            return false;
          }*/
            return true;
    }
</script>

  </body>
  </html>

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
                  document.getElementById("mainContentDiv").innerHTML = this.responseText;
             }
             };
              xmlhttp.open("GET", "${request.contextPath}/Login/changepassword", true);
               xmlhttp.send();
}

</script>
<!-- ---------------------------Start of Navigation------------------------------------------ -->
<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top" id="mainNav">
  <a class="navbar-brand" href="#">VOLP :: Course Instructor</a>
  <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse " id="navbarResponsive" >

    <ul class="navbar-nav navbar-sidenav sidenavPanel" id="exampleAccordion">
    <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Course Help">
                  <a class="nav-link" onclick="callInstructorHelp()">

                    <span class="nav-link-text">Instructor Steps</span> <i class="fa fa-info-circle"></i>
                  <a>
            </li>

      <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Create Course">

        <a class="nav-link" onclick="showMenu()">

        <span class="fa-stack">

            <span class="fa fa-circle-o fa-stack-2x"></span>
            <strong class="fa-stack-1x">
                1
            </strong>
        </span>
             <span class="nav-link-text">Create New Course</span>
        </a>

      </li>

      <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Publish Course">
                <a class="nav-link" href="#" onclick="callOfferCourse()">
                  <span class="fa-stack">

                              <span class="fa fa-circle-o fa-stack-2x"></span>
                              <strong class="fa-stack-1x">
                                 2
                              </strong>
                          </span>
                  <span class="nav-link-text">Publish Course</span>
                </a>
        </li>

     <!-- <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Set Course Fees">
        <a class="nav-link" href="#" onclick="setCourseFees()">
          <span class="fa-stack">

                      <span class="fa fa-circle-o fa-stack-2x"></span>
                      <strong class="fa-stack-1x">
                         3
                      </strong>
                  </span>
          <span class="nav-link-text">Set Course Fees</span>
        </a>
      </li> -->
      <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Test Course Video">
        <a class="nav-link" href="#" onclick="givenPlatformFeedback('Instructor')">
          <i class="fa fa-fw fa-video-camera"></i>
          <span class="nav-link-text">Give Platform Feedback</span>
        </a>
      </li>
    <!--  <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Request for Course Review">
        <a class="nav-link" href="">
          <i class="fa fa-fw fa-comments-o"></i>
          <span class="nav-link-text">Request for Course Review</span>
        </a>
      </li>
      <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Course Exam">
        <a class="nav-link" href="" >
          <i class="fa fa-fw fa-check"></i>
          <span class="nav-link-text">Course Exam</span>
        </a>
      </li>

      <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Course Assessment">
        <a class="nav-link" href="#" >
          <i class="fa fa-fw fa-check"></i>
          <span class="nav-link-text">Course Assessment Scheme</span>
        </a>
      </li>
    -->



       <!--   <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Menu">
          <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseMulti" data-parent="#exampleAccordion">
            <i class="fa fa-fw fa-sitemap"></i>
            <span class="nav-link-text">Menu6</span>
          </a>
          <ul class="sidenav-second-level collapse" id="collapseMulti">
            <li>
              <a href="#">Menu6.1</a>
            </li>
            <li>
              <a href="#">Menu6.2</a>
            </li>
            <li>
              <a href="#">Menu6.3</a>
            </li>

          <li>
              <a class="nav-link-collapse collapsed" data-toggle="collapse" href="#collapseMulti2">Menu 6.3.1</a>
              <ul class="sidenav-third-level collapse" id="collapseMulti2">
                <li>
                  <a href="#">Menu 6.3.1</a>
                </li>
                <li>
                  <a href="#">Menu 6.3.1</a>
                </li>
                <li>
                  <a href="#">Menu 6.3.1</a>
                </li>
              </ul>
            </li>
          </ul>
        </li> -->

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

        <li>
          <!-- <form class="form-inline"> -->
            <div class="input-group">
              <input class="form-control" type="text" id="searchcrs1" placeholder="Search for Course" title="Search for Course Category " onchange="searchcrs(${instructorcourses.id})">
            <!--  <span class="input-group-btn">
                <button class="btn btn-warning" type="button">
                  <i class="fa fa-search"></i>
                </button>
              </span>  -->
            </div>
         <!-- </form>  -->
        </li>

      </ul>

    </div> &nbsp;&nbsp;&nbsp;&nbsp;
  </nav>
  <!-- End of Navigation --------- --------------------------------------------- -->
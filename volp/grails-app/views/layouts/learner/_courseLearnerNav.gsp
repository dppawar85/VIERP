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
function joinclass()
{

         //   alert("Hi::");
            var xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                  document.getElementById("mainContentDiv").innerHTML = this.responseText;
             }
             };
              xmlhttp.open("GET", "${request.contextPath}/SendInvitationToLearner/joinclassbycode", true);
               xmlhttp.send();

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
  <a class="navbar-brand" href="#">VOLP :: Course Learner </a>
  <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarResponsive">

    <ul class="navbar-nav navbar-sidenav sidenavPanel" id="exampleAccordion" >


      <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Test Course Video">
              <a class="nav-link" href="#" onclick="givenPlatformFeedback('Learner')">
                <i class="fa fa-fw fa-video-camera"></i>
                <span class="nav-link-text">Give Platform Feedback</span>
              </a>
            </li>


      <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Assignments">
        <a class="nav-link" href="" >
          <i class="fa fa-fw fa-check"></i>
          <span class="nav-link-text">Assignments</span>
        </a>
      </li>
      <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Examination">
        <a class="nav-link" href="" >
          <i class="fa fa-fw fa-check"></i>
          <span class="nav-link-text">Examination</span>
        </a>
      </li>
<li class="nav-item" data-toggle="tooltip" data-placement="right" title="Rate Course">
        <a class="nav-link" href="#" onclick="rateCourse()">
          <i class="fa fa-fw fa-shield"></i>
          <span class="nav-link-text">Rate Course</span>
        </a>
      </li>


      <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Result">
        <a class="nav-link" href="#">
          <i class="fa fa-fw fa-shield"></i>
          <span class="nav-link-text">Result</span>
        </a>
      </li>

      <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Certificate">
        <a class="nav-link" href="#">
          <i class="fa fa-fw fa-trophy"></i>
          <span class="nav-link-text">Certificate</span>
        </a>
      </li>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Payment History">
        <a class="nav-link" href="">
          <i class="fa fa-fw fa-money"></i>
          <span class="nav-link-text">Payment History</span>
        </a>
      </li>

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

      <ul class="navbar-nav sidenav-toggler " style="background-color: white">
        <li class="nav-item blink">
          <a class="nav-link text-center " id="sidenavToggler">
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
            <a class="dropdown-item small" onclick="joinclass();" data-toggle="modal" data-target="#userProfileModal"> <i class="fa fa-user"></i>&nbsp;Join Class</a><div class="dropdown-divider"></div>
           <a class="dropdown-item small" onclick="changepass()" data-toggle="modal" data-target="#changeUserPasswordModal"> <i class="fa fa-key"></i>&nbsp;Change Password</a> <div class="dropdown-divider"></div>
         <!--   <a class="dropdown-item small" data-toggle="modal" data-target="#logoutModal"><i class="fa fa-sign-out"></i>&nbsp;Sign Out</a> -->
 <a class="dropdown-item small" href="${request.contextPath}/login/logout"><i class="fa fa-sign-out"></i>&nbsp;Sign Out</a>
          </div>

        </li>

        <li>
          <form class="form-inline">
            <div class="input-group">
              <input class="form-control" type="text" placeholder="Search for Course" title="Search for Course">
              <span class="input-group-btn">
                <button class="btn btn-danger" type="button">
                  <i class="fa fa-search" style="color: white"></i>
                </button>
              </span>
            </div>
          </form>
        </li>

      </ul>

    </div> &nbsp;&nbsp;&nbsp;&nbsp;
  </nav>
  <!-- End of Navigation --------- --------------------------------------------- -->
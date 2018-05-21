
    <!doctype html>
    <html lang="en" class="no-js">
    <head>

      <meta name="viewport" content="width=device-width, initial-scale=1"/>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
      <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
      <title>
        VOLP || Dashboard
      </title>

           <asset:stylesheet src="bootstrap.min.css"/>
           <asset:stylesheet src="font-awesome.min.css"/>
           <asset:stylesheet src="sb-admin.css"/>
           <asset:stylesheet src="courseInstructor.css"/>


    </head>
    <body id="page-top">
     <!-- ---------------------------Start of Navigation------------------------------------------ -->
<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top" id="mainNav">
  <a class="navbar-brand" href="">VOLP :: Organization</a>
  <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarResponsive">

    <ul class="navbar-nav navbar-sidenav sidenavPanel" id="exampleAccordion">
          <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Add/Edit Course Category">

            <a class="nav-link" onclick="addcategory()">
            <i class="fa fa-fw fa-book"></i>
            <span class="nav-link-text">Add/Edit Course Category</span>
            </a>

          </li>

      <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Add Department">

        <a class="nav-link" onclick="adddepartment()">
        <i class="fa fa-fw fa-book"></i>
        <span class="nav-link-text">Add/Edit Department</span>
        </a>

      </li>

      <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Add Program">

        <a class="nav-link" onclick="addprogram()">
        <i class="fa fa-fw fa-book"></i>
        <span class="nav-link-text">Add/Edit Program</span>
        </a>

      </li>
      <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Add Division">

        <a class="nav-link" onclick="adddivision()">
        <i class="fa fa-fw fa-book"></i>
        <span class="nav-link-text">Add/Edit Division</span>
        </a>

      </li>
      <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Authenticate Instructors">

        <a class="nav-link" onclick="authenticateinstructor()">
        <i class="fa fa-fw fa-book"></i>
        <span class="nav-link-text">Authenticate Instructors</span>
        </a>

      </li>
      <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Authenticate Learners">

        <a class="nav-link" onclick="authenticatelearner()">
        <i class="fa fa-fw fa-book"></i>
        <span class="nav-link-text">Authenticate Learners</span>
        </a>

      </li>
      <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Block Instructors">

        <a class="nav-link" onclick="blockinstructor()">
        <i class="fa fa-fw fa-book"></i>
        <span class="nav-link-text">Block Instructors</span>
        </a>

      </li>
      <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Block Learners">

        <a class="nav-link" onclick="blocklearner()">
        <i class="fa fa-fw fa-book"></i>
        <span class="nav-link-text">Block Learners</span>
        </a>
      </li>
      <li class="nav-item" data-toggle="tooltip" data-placement="right" title="View Instructors">

        <a class="nav-link" onclick="viewinstructors()">
        <i class="fa fa-fw fa-book"></i>
        <span class="nav-link-text">View Instructors</span>
        </a>
      </li>
      <li class="nav-item" data-toggle="tooltip" data-placement="right" title="View Learners">

        <a class="nav-link" onclick="viewlearners()">
        <i class="fa fa-fw fa-book"></i>
        <span class="nav-link-text">View Learners</span>
        </a>
      </li>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Delete Offered Course">
                      <a class="nav-link" onclick="deleteofferedcourse()">
                      <i class="fa fa-fw fa-book"></i>
                      <span class="nav-link-text">Delete Offered Course</span>
                      </a>
                    </li>

      </ul>

      <ul class="navbar-nav sidenav-toggler" style="background-color: white">
        <li class="nav-item">
          <a class="nav-link text-center" id="sidenavToggler">
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
                        <a class="dropdown-item small" href="${request.contextPath}/instructor/profile"> <i class="fa fa-user"></i>&nbsp;Organization Profile</a><div class="dropdown-divider"></div>

            <a class="dropdown-item small" data-toggle="modal" data-target="#changeUserPasswordModal"> <i class="fa fa-key"></i>&nbsp;Change Password</a> <div class="dropdown-divider"></div>
            <a class="dropdown-item small" href="${request.contextPath}/login/logout"><i class="fa fa-sign-out"></i>&nbsp;Sign Out</a>

          </div>

        </li>
        <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>


      </ul>

    </div> &nbsp;&nbsp;&nbsp;&nbsp;
  </nav>
  <!-- End of Navigation --------- --------------------------------------------- -->
     <br> <br>
     <div class="content-wrapper">
      <div class="container-fluid">
      <g:if test="${flash.message}">
            <div class="message" role="status" style="color: red">${flash.message}</div>
        </g:if>
        <!-- container-fluid-->
            <div id="mainorgranizationajax">

            </div>


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

   <asset:javascript src="jquery.min.js"/>
       <asset:javascript src="bootstrap.bundle.min.js"/>
       <asset:javascript src="jquery.easing.min.js"/>
       <asset:javascript src="sb-admin.min.js"/>
<script>
function editmaincategory(id,name)
{
  //  alert(id+"   "+name);
    var categoryid=id
    var categoryname = prompt("Please Enter New Name",name);
    if(!categoryname)
    {
        categoryname=name;
        return;
    }
        var xmlhttp = new XMLHttpRequest();
                  xmlhttp.onreadystatechange = function() {
                      if (this.readyState == 4 && this.status == 200) {
                         document.getElementById("mainorgranizationajax").innerHTML = this.responseText;
                         //alert("done");
                      }
                  };
                  xmlhttp.open("GET", "${request.contextPath}/CourseCategory/editmaincategory?categoryid="+categoryid+"&categoryname="+categoryname, true);
                  xmlhttp.send();
}
function changesubcategorylinking()
{
        var xmlhttp = new XMLHttpRequest();
                  xmlhttp.onreadystatechange = function() {
                      if (this.readyState == 4 && this.status == 200) {
                         document.getElementById("mainorgranizationajax").innerHTML = this.responseText;
                         //alert("done");
                      }
                  };
                  xmlhttp.open("GET", "${request.contextPath}/CourseCategory/changesubcategorylinking", true);
                  xmlhttp.send();
}
function fetchsubcategory(val)
{
    var maincategory=val.value
        var xmlhttp = new XMLHttpRequest();
                  xmlhttp.onreadystatechange = function() {
                      if (this.readyState == 4 && this.status == 200) {
                         document.getElementById("mainorgranizationajax").innerHTML = this.responseText;
                         //alert("done");
                      }
                  };
                  xmlhttp.open("GET", "${request.contextPath}/CourseCategory/addfurthersubcategory?maincategory="+maincategory, true);
                  xmlhttp.send();

}
function addsubcategory()
{
    var xmlhttp = new XMLHttpRequest();
              xmlhttp.onreadystatechange = function() {
                  if (this.readyState == 4 && this.status == 200) {
                     document.getElementById("mainorgranizationajax").innerHTML = this.responseText;
                     //alert("done");
                  }
              };
              xmlhttp.open("GET", "${request.contextPath}/CourseCategory/addsubcategory", true);
              xmlhttp.send();
}
function addmaincategory()
{
    var xmlhttp = new XMLHttpRequest();
              xmlhttp.onreadystatechange = function() {
                  if (this.readyState == 4 && this.status == 200) {
                     document.getElementById("mainorgranizationajax").innerHTML = this.responseText;
                     //alert("done");
                  }
              };
              xmlhttp.open("GET", "${request.contextPath}/CourseCategory/addmaincategory", true);
              xmlhttp.send();
}
function addcategory()
{
    //var dd=document.getElementById('mainorgranizationajax').innerHTML="Hello";
    var xmlhttp = new XMLHttpRequest();
              xmlhttp.onreadystatechange = function() {
                  if (this.readyState == 4 && this.status == 200) {
                     document.getElementById("mainorgranizationajax").innerHTML = this.responseText;
                     //alert("done");
                  }
              };
              xmlhttp.open("GET", "${request.contextPath}/CourseCategory/addcategory", true);
              xmlhttp.send();
}
function adddepartment()
{
    //var dd=document.getElementById('mainorgranizationajax').innerHTML="Hello";
    var xmlhttp = new XMLHttpRequest();
              xmlhttp.onreadystatechange = function() {
                  if (this.readyState == 4 && this.status == 200) {
                     document.getElementById("mainorgranizationajax").innerHTML = this.responseText;
                     //alert("done");
                  }
              };
              xmlhttp.open("GET", "${request.contextPath}/Department/adddepartment", true);
              xmlhttp.send();
}
function editdepartment(id)
{
   // alert("Hello"+id);
    var xmlhttp = new XMLHttpRequest();
              xmlhttp.onreadystatechange = function() {
                  if (this.readyState == 4 && this.status == 200) {
                     document.getElementById("mainorgranizationajax").innerHTML = this.responseText;
                     //alert("done");
                  }
              };
              xmlhttp.open("GET", "${request.contextPath}/Department/updatedepartment?id="+id, true);
              xmlhttp.send();
}
function addprogram()
{
  //  println("Hi");
    var xmlhttp = new XMLHttpRequest();
              xmlhttp.onreadystatechange = function() {
                  if (this.readyState == 4 && this.status == 200) {
                     document.getElementById("mainorgranizationajax").innerHTML = this.responseText;
                     //alert("done");
                  }
              };
              xmlhttp.open("GET", "${request.contextPath}/Program/addprogram", true);
              xmlhttp.send();
}
function editprogram(id)
{
    var xmlhttp = new XMLHttpRequest();
              xmlhttp.onreadystatechange = function() {
                  if (this.readyState == 4 && this.status == 200) {
                     document.getElementById("mainorgranizationajax").innerHTML = this.responseText;
                     //alert("done");
                  }
              };
              xmlhttp.open("GET", "${request.contextPath}/Program/updateprogram?id="+id, true);
              xmlhttp.send();
}
function adddivision()
{
    var xmlhttp = new XMLHttpRequest();
              xmlhttp.onreadystatechange = function() {
                  if (this.readyState == 4 && this.status == 200) {
                     document.getElementById("mainorgranizationajax").innerHTML = this.responseText;
                     //alert("done");
                  }
              };
              xmlhttp.open("GET", "${request.contextPath}/Division/adddivision", true);
              xmlhttp.send();

}
function editdivision(id)
{
    var xmlhttp = new XMLHttpRequest();
              xmlhttp.onreadystatechange = function() {
                  if (this.readyState == 4 && this.status == 200) {
                     document.getElementById("mainorgranizationajax").innerHTML = this.responseText;
                     //alert("done");
                  }
              };
              xmlhttp.open("GET", "${request.contextPath}/Division/updatedivision?id="+id, true);
              xmlhttp.send();
}
function authenticateinstructor()
{
    var xmlhttp = new XMLHttpRequest();
              xmlhttp.onreadystatechange = function() {
                  if (this.readyState == 4 && this.status == 200) {
                     document.getElementById("mainorgranizationajax").innerHTML = this.responseText;
                     //alert("done");
                  }
              };
              xmlhttp.open("GET", "${request.contextPath}/Organization/addinstructor", true);
              xmlhttp.send();
}
function authenticatelearner()
{
    var xmlhttp = new XMLHttpRequest();
              xmlhttp.onreadystatechange = function() {
                  if (this.readyState == 4 && this.status == 200) {
                     document.getElementById("mainorgranizationajax").innerHTML = this.responseText;
                     //alert("done");
                  }
              };
              xmlhttp.open("GET", "${request.contextPath}/Organization/addlearner", true);
              xmlhttp.send();
}
function blockinstructor()
{
    var xmlhttp = new XMLHttpRequest();
              xmlhttp.onreadystatechange = function() {
                  if (this.readyState == 4 && this.status == 200) {
                     document.getElementById("mainorgranizationajax").innerHTML = this.responseText;
                     //alert("done");
                  }
              };
              xmlhttp.open("GET", "${request.contextPath}/Organization/blockinstructor", true);
              xmlhttp.send();
}
function blocklearner()
{
    var xmlhttp = new XMLHttpRequest();
              xmlhttp.onreadystatechange = function() {
                  if (this.readyState == 4 && this.status == 200) {
                     document.getElementById("mainorgranizationajax").innerHTML = this.responseText;
                     //alert("done");
                  }
              };
              xmlhttp.open("GET", "${request.contextPath}/Organization/blocklearner", true);
              xmlhttp.send();
}
function viewinstructors()
{
    var xmlhttp = new XMLHttpRequest();
              xmlhttp.onreadystatechange = function() {
                  if (this.readyState == 4 && this.status == 200) {
                     document.getElementById("mainorgranizationajax").innerHTML = this.responseText;
                     //alert("done");
                  }
              };
              xmlhttp.open("GET", "${request.contextPath}/Organization/viewinstructors", true);
              xmlhttp.send();
}
function viewlearners()
{
    var xmlhttp = new XMLHttpRequest();
              xmlhttp.onreadystatechange = function() {
                  if (this.readyState == 4 && this.status == 200) {
                     document.getElementById("mainorgranizationajax").innerHTML = this.responseText;
                     //alert("done");
                  }
              };
              xmlhttp.open("GET", "${request.contextPath}/Organization/viewlearners", true);
              xmlhttp.send();
}
function deleteofferedcourse()
{
    var xmlhttp = new XMLHttpRequest();
              xmlhttp.onreadystatechange = function() {
                  if (this.readyState == 4 && this.status == 200) {
                     document.getElementById("mainorgranizationajax").innerHTML = this.responseText;
                     //alert("done");
                  }
              };
              xmlhttp.open("GET", "${request.contextPath}/deleteCourseOffering/viewrequesttodeletecourse", true);
              xmlhttp.send();
}
function deleteCourseOffering(coffrid)
{
    var xmlhttp = new XMLHttpRequest();
              xmlhttp.onreadystatechange = function() {
                  if (this.readyState == 4 && this.status == 200) {
                     document.getElementById("mainorgranizationajax").innerHTML = this.responseText;
                     //alert("done");
                  }
              };
              xmlhttp.open("GET", "${request.contextPath}/deleteCourseOffering/deleteCourseOffering?coffrid="+coffrid, true);
              xmlhttp.send();
}
</script>
  </body>
  </html>

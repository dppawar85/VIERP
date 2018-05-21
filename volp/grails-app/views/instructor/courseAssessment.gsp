
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
  <a class="navbar-brand" href="${request.contextPath}/instructor/instructor">VOLP :: Course Instructor</a>
  <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarResponsive">

    <ul class="navbar-nav navbar-sidenav" id="exampleAccordion" style="background: linear-gradient(to right, rgba(0,128,128,0), rgba(0,128,128,1));">

      <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Create Course">

        <a class="nav-link" ">
        <i class="fa fa-fw fa-book"></i>
        <span class="nav-link-text">Menu</span>
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
            <a class="dropdown-item small" data-toggle="modal" data-target="#userProfileModal"> <i class="fa fa-user"></i>&nbsp;User Profile</a><div class="dropdown-divider"></div>
            <a class="dropdown-item small" data-toggle="modal" data-target="#changeUserPasswordModal"> <i class="fa fa-key"></i>&nbsp;Change Password</a> <div class="dropdown-divider"></div>
            <a class="dropdown-item small" href="${request.contextPath}/login/logout"><i class="fa fa-sign-out"></i>&nbsp;Sign Out</a>

          </div>

        </li>



      </ul>

    </div> &nbsp;&nbsp;&nbsp;&nbsp;
  </nav>
  <!-- End of Navigation --------- --------------------------------------------- -->
     <br> <br>
     <div class="content-wrapper">
      <div class="container-fluid">
        <!-- container-fluid-->



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

  </body>
  </html>


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
          <asset:stylesheet src="jquery-ui.css" />
                 <asset:javascript src="jquery.min.js"/>
                  <asset:javascript src="bootstrap.bundle.min.js"/>
                  <asset:javascript src="jquery.easing.min.js"/>
                  <asset:javascript src="sb-admin.min.js"/>
                 <asset:javascript src="jquery-ui.js" />
    <g:layoutHead />
    </head>
    <body id="page-top">
     <!-- ---------------------------Start of Navigation------------------------------------------ -->
<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top" id="mainNav">
  <a class="navbar-brand" href="#">VOLP :: Course Instructor</a>
  <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav ml-auto" >
        <li class="nav-item dropdown">

          <a class="nav-link dropdown-toggle mr-lg-2"  href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            Hello, ${session.fname} <i class="fa fa-bars" aria-hidden="true""></i>
            <span class="d-lg-none">Dashboard</span>

          </a>

          <div class="dropdown-menu user-profile-cursor">
            <a class="dropdown-item small" href="${request.contextPath}/instructor/profile" > <i class="fa fa-user"></i>&nbsp;User Profile</a><div class="dropdown-divider"></div>
            <a class="dropdown-item small" data-toggle="modal" data-target="#changeUserPasswordModal"> <i class="fa fa-key"></i>&nbsp;Change Password</a> <div class="dropdown-divider"></div>
            <a class="dropdown-item small" href="${request.contextPath}/login/logout"><i class="fa fa-sign-out"></i>&nbsp;Sign Out</a>

          </div>

        </li>



      </ul>

    </div> &nbsp;&nbsp;&nbsp;&nbsp;
  </nav>
  <!-- End of Navigation --------- --------------------------------------------- -->
     <br> <br>
     <div class="">
      <div class="container-fluid">
        <!-- container-fluid-->

              <g:layoutBody />

             <br><br>


      </div><!-- End of container-fluid-->

    </div> <!-- End of content-wrapper-->


    <!-- Start of Footer ------------------------------------ -->
<footer class="container col-md-12 sticky-footer" style="width: 100%">
      <!--<div class="container">-->
        <div class="text-center">
          <small style="font-size:14px">Copyright © VOLP</small>
        </div>
      <!--</div>-->
    </footer>

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top" >
      <i class="fa fa-angle-up"></i>
    </a>
<!-- End Of Footer --------------------------------------- -->


  </body>
  </html>
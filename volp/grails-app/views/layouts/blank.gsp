
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
    <style>

    </style>
    <script>
    function goBack() {
        window.history.back();
    }
    </script>

    </head>
    <body id="page-top">
     <!-- ---------------------------Start of Navigation------------------------------------------ -->
<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top" id="mainNav">
  <a class="navbar-brand" href="#">VOLP :: Course Instructor  ::  ${session.uid}</a>
  <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  </nav>
  <button  class="backBtn btn pull-right" onclick="goBack()"><i class="fa fa-angle-left"></i>Go Back</button>
  <!-- End of Navigation --------- --------------------------------------------- -->
     <br> <br>

      <div class="container-fluid">

        <!-- container-fluid-->

              <g:layoutBody />

             <br><br>


      </div><!-- End of container-fluid-->




    <!-- Start of Footer ------------------------------------ -->
<footer class="sticky-footer" style="width: 100%">
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



  </body>
  </html>

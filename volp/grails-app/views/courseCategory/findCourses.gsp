
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

    <style>
      .backBtn{
       position: sticky;
       padding: 1px;
       top: 30px;
       right: 0px;
      }
      @media only screen and (max-width: 600px)  {
        .backBtn{
               position: relative;
               top: 50px;
               right: 0px;
              }
      }
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
  <a class="navbar-brand" href="#"></a>
  <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  </nav>
  <button  class="backBtn btn btn-secondary pull-right" onclick="goBack()"><i class="fa fa-angle-left"></i>Go Back<i class="fa fa-angle-right"></i></button>
  <!-- End of Navigation --------- --------------------------------------------- -->
     <br> <br>

      <div class="container-fluid">

        <!-- container-fluid-->

              <g:form >
                  <g:if test="${flash.message}">
                      <g:message code="${flash.message}" />
                  </g:if>
                  <table class="table table-striped table-bordered">
                  <tbody>

                  <g:each in="${mainCourse}">
                      <tr>
                      <td> Course Name :: ${it.course_name}</td>
                      </tr>

                      <tr>
                      <td> <g:if test ="${it.prerequisite.prerequisite}">
                          prerequisite:: <g:each var="pre" in="${it.prerequisite.prerequisite}">
                               ${pre}
                              </g:each>
                          </g:if>
                          </td>
                       </tr>

                       <tr>
                       <td>
                      <g:if test ="${it.course_code}">
                          Course Code :: ${it.course_code}
                      </g:if>
                      </td>
                      </tr>
                      <tr>
                      <g:if test ="${it.department!=null}">
                          <td> Department ::  ${it.department.name} </td>
                      </g:if>
                      <g:else>
                            <td>Department :: Open </td>
                      </g:else>
                      </tr>

                      <tr>
                       <g:if test ="${it.program}">
                              <td>  Program::${it.program.name} </td>
                       </g:if>
                       </tr>

                         <tr> <td>courseowner :: ${it.courseinstructor.person.firstName} &nbsp;&nbsp;&nbsp;<i class="fa fa-star fa-1x"></i>&nbsp; ${it.rating}</td></tr>
                     <tr>
                      <g:each in="${fee}" var="fees" status="i">
                          <g:if test ="${i==0}">
                              <td>With Certificate :: ${fees.fees[i]}</td>
                          </g:if>
                          <g:else>
                          <g:if test="${fees}">
                                <td> Without Certificate:: ${fees}</td>
                          </g:if>
                          </g:else>
                      </g:each>
                    </tr>
                    <tr><td>
<g:link class="btn btn-warning" action="course" controller="login" params="[cid:it.id]">Enroll Now</g:link>
                   </td></tr>
                  </g:each>
            </table>
              </g:form>

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




<body>

</body>

<!-- ---------------------------Start of Navigation------------------------------------------ -->
  <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top" id="mainNav">
    <a class="navbar-brand" href="index.html">VOLP :: Admin</a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">

<ul class="navbar-nav navbar-sidenav bgimg" id="exampleAccordion" >


     <!-- <ul class="navbar-nav navbar-sidenav" id="exampleAccordion" style="background: linear-gradient(to right, rgba(74,189,172,0), rgba(74,189,172,1));"> -->

             <li class="nav-item" data-toggle="tooltip" data-placement="right" title="View Feedback">
             <a href="#" onclick="viewFeedback()" >View Feedback</a>
            </li>

        <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
            <li class="nav-item" data-toggle="tooltip" data-placement="right" title="${c.name}">
               <g:link class="btn" controller="${c.logicalPropertyName}">${c.name}</g:link>
            </li>
        </g:each>
     </ul>

          <ul class="navbar-nav ml-auto" >
            <li class="nav-item dropdown">

              <a class="nav-link dropdown-toggle mr-lg-2"  href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Hello, Admin <i class="fa fa-bars" aria-hidden="true""></i>
                <span class="d-lg-none">Dashboard</span>

              </a>

              <div class="dropdown-menu user-profile-cursor">
                <a class="dropdown-item small" data-toggle="modal" data-target="#userProfileModal"> <i class="fa fa-user"></i>&nbsp;User Profile</a><div class="dropdown-divider"></div>
                <a class="dropdown-item small" data-toggle="modal" data-target="#changeUserPasswordModal"> <i class="fa fa-key"></i>&nbsp;Change Password</a> <div class="dropdown-divider"></div>
                <a class="dropdown-item small" data-toggle="modal" data-target="#logoutModal"><i class="fa fa-sign-out"></i>&nbsp;Sign Out</a>

              </div>

            </li>

            <li>
              <form class="form-inline">
                <div class="input-group">
                  <input class="form-control" type="text" placeholder="Search for Course Category" title="Search for Course Category">
                  <span class="input-group-btn">
                    <button class="btn btn-warning" type="button">
                      <i class="fa fa-search"></i>
                    </button>
                  </span>
                </div>
              </form>
            </li>

          </ul>


    </div> &nbsp;&nbsp;&nbsp;&nbsp;
    </nav>
    <!-- End of Navigation --------- --------------------------------------------- -->
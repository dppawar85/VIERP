<!-- ---------------------------Start of Navigation------------------------------------------ -->
  <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top" id="mainNav">
    <a class="navbar-brand" href="index.html">VOLP :: Name of Landing Page User</a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">

      <ul class="navbar-nav navbar-sidenav" id="exampleAccordion" style="background: linear-gradient(to right, rgba(0,128,128,0), rgba(0,128,128,1));">

        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Menu">
          <a class="nav-link" href="" >
            <i class="fa fa-fw fa-dashboard"></i>
            <span class="nav-link-text">Menu1</span>
          </a>
        </li>

        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Menu">
          <a class="nav-link" href="charts.html">
            <i class="fa fa-fw fa-area-chart"></i>
            <span class="nav-link-text">Menu2</span>
          </a>
        </li>

        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Menu">
          <a class="nav-link" href="#">
            <i class="fa fa-fw fa-table"></i>
            <span class="nav-link-text">Menu3</span>
          </a>
        </li>

        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Menu">
          <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseComponents" data-parent="#exampleAccordion">
            <i class="fa fa-fw fa-wrench"></i>
            <span class="nav-link-text">Menu4</span>
          </a>

          <ul class="sidenav-second-level collapse" id="collapseComponents">
            <li>
              <a href="#">Menu4.1</a>
            </li>
            <li>
              <a href="#">Menu4.2</a>
            </li>
          </ul>

        </li>

        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Menu">
          <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseExamplePages" data-parent="#exampleAccordion">
            <i class="fa fa-fw fa-file"></i>
            <span class="nav-link-text">Menu5</span>
          </a>

          <ul class="sidenav-second-level collapse" id="collapseExamplePages">
            <li>
              <a href="#">Menu5.1</a>
            </li>

            <li>
              <a href="#">Menu5.2</a>
            </li>

            <li>
              <a href="#">Menu5.3</a>
            </li>

            <li>
              <a href="#">Menu5.3</a>
            </li>

          </ul>

        </li>

        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Menu">
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
        </li>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Link">
          <a class="nav-link" href="#">
            <i class="fa fa-fw fa-link"></i>
            <span class="nav-link-text">Menu 7</span>
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
                <a class="dropdown-item small" data-toggle="modal" data-target="#userProfileModal"> <i class="fa fa-user-plus"></i>&nbsp;Add/Edit Profile</a><div class="dropdown-divider"></div>
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
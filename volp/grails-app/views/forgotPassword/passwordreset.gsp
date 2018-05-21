<!DOCTYPE html>
<!DOCTYPE html>
<html>
<head>
  <meta charset="ISO-8859-1">
  <asset:stylesheet src="bootstrap.min.css"/>
   <asset:stylesheet src="font-awesome.min.css"/>
   <asset:stylesheet src="sb-admin.css"/>
  <title>password reset</title>

  </head>

<body class="bg-light">
  <div class="container">
    <div class="card card-login mx-auto mt-5">
      <div class="card-header bg-warning">Reset Password</div>
      <g:if test="${flash.message}">
           <div class="alert alert-error" style="display: block;color:red">${flash.message}</div>
       </g:if>
      <div class="card-body">

    <g:form url="[action:'savepasswordreset']">

          <div class="form-group">
            <label for="exampleInputEmail1">Email address</label>
            <input class="form-control" id="email" name="email" type="text" value="${email}" readonly="true" aria-describedby="emailHelp" required>
          </div>

          <div class="form-group">
            <label for="exampleInputPassword1">New Password</label>
             <input class="form-control" name="password" type="password" placeholder="Create Your Password" required>
          </div>
           <div class="form-group">
            <label for="exampleInputPassword1">Confirm Password</label>
             <input class="form-control" name="confirmpassword" type="password" placeholder="Create Your Password" required>
          </div>

          <g:submitButton class="btn btn-primary btn-block" name="Change Password" value="Change Password"></g:submitButton>

        </g:form>


        </div>
      </div>
    </div>
  </div>
  </body>
</html>


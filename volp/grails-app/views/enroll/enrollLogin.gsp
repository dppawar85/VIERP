<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <title>Welcome to VOLP </title>

  <asset:stylesheet src="bootstrap.min.css"/>
     <asset:stylesheet src="font-awesome.min.css"/>
     <asset:stylesheet src="sb-admin.css"/>
</head>
<body class="bg-light">
  <div class="container">
    <div class="card card-login mx-auto mt-5">
      <div class="card-header bg-warning">Sign In</div>
      <g:if test="${flash.message}">
      <div class="alert alert-error" style="display: block;color:red">${flash.message}</div>
    </g:if>
    <div class="card-body">

    <g:form name="myForm" url="[action:'processLogin',controller:'enroll']">

    <hr>
    <fieldset id="enable_disable_form">

     <div class="form-group">
      <label for="exampleInputEmail1">Email address</label>
      <input class="form-control" id="email" type="email" name="email" aria-describedby="emailHelp" placeholder="Enter email" required>
    </div>

    <div class="form-group">
      <label for="exampleInputPassword1">Password</label>
      <input class="form-control" name="pwd" type="password" placeholder="Password" required>
    </div>

    <div class="form-group">
      <div class="form-check">
        <label class="form-check-label">
          <input class="form-check-input" type="checkbox"> Remember Password</label>
        </div>
      </div>
    <g:hiddenField name="coffr" value="${coffr}" />
      <input class="btn btn-primary btn-block" type="submit" value="Login" name="submit">
      <fieldset>

      </g:form>

      <div class="text-center">
        <a class="d-block small mt-3" href="${request.contextPath}/registration/verifyemail">Register an Account</a>
        <g:link class="d-block small" controller="ForgotPassword" action="verifyemail">
        Forgot Password?
      </g:link>

    </div>
  </div>
</div>
</div>
</body>
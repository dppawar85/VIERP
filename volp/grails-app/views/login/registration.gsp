<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Registration</title>
</head>
<link href="social-buttons.css" rel="stylesheet"/>
<link href="bootstrap.min.css" rel="stylesheet">
<style>
.demoDiv {
    margin:auto;
    text-align:center;
}
</style>
<body>
<br/>
<g:if test="${flash.message}">
  <div class="alert alert-error" style="display: block">${flash.message}</div>
  </g:if>
  <br>
<div class="demoDiv">

    <a	href="https://accounts.google.com/o/oauth2/auth?scope=https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/plus.login&redirect_uri=http://localhost:8080/volp/login/processreg&response_type=code&client_id=1044538822480-rp49uql6hntc6vk2eessltv99gecpfqp.apps.googleusercontent.com&approval_prompt=force"
              class="btn btn-lg btn-social btn-google"> <i
                class="fa fa-twitter"></i> Sign up with Google
    </a>

</div>
</body>
</html>
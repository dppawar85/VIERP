<!DOCTYPE html>
<html>
<head>
  <meta charset="ISO-8859-1">
  <asset:stylesheet src="bootstrap.min.css"/>
   <asset:stylesheet src="font-awesome.min.css"/>
   <asset:stylesheet src="sb-admin.css"/>
  <title>ForgotPassword</title>

  </head>

<body class="bg-light">
  <div class="container">
    <div class="card card-login mx-auto mt-5">
      <div class="card-header bg-warning">Reset Password</div>
      <g:if test="${flash.message}">
           <div class="alert alert-error" style="display: block;color:red">${flash.message}</div>
       </g:if>
      <div class="card-body" id="disableForm">

          <div class="form-group">
            <label for="exampleInputEmail1">Email address</label>
            <input class="form-control" id="email" name="email" type="text" placeholder="Enter Your Email" aria-describedby="emailHelp" required>
          </div>

          <button class="btn btn-primary btn-block" name="Verify Email" value="Verify Email" onclick="callPasswordReset()">Verify Email</button>

       </div>


     <div class="card-body" id="responseForgotPassword">

      </div>
     <div id="myProgress">
           <div id="myBar">

           </div>
      </div>

    </div>
  </div>

  <script>
function callProgressBar() {
  var elem = document.getElementById("myBar");

  var width = 1;
  var id = setInterval(frame, 20);
  function frame() {
    if (width >= 100) {
      clearInterval(id);
    } else {
      width++;
      elem.style.width = width + '%';
      //elem.innerHTML = width * 1  + '%';
      elem.style.color="#FFFFFF";
      elem.style.opacity="0.3";
    }
  }
}
                function callPasswordReset() {
                var email=document.getElementById('email').value;
                  var emailReg=/^([\w-\.]+@([\w]+\.)+[\w-]{2,4})?$/;
               if(email==="" || !(email).match(emailReg))
                   {
                    alert("please enter valid email address");
                   }
                   else{
                  var xhttp = new XMLHttpRequest();
                  xhttp.onreadystatechange = function() {
                  callProgressBar();
                    if (this.readyState == 4 && this.status == 200) {
                     document.getElementById("responseForgotPassword").innerHTML = this.responseText;
                     document.getElementById('disableForm').style.display="none";
                    }
                    if (this.status == 404){
                     alert("Something went wrong!")
                    }
                  };
                  xhttp.open("GET", "${request.contextPath}/forgotPassword/verifyemailprocess?email="+email, true);
                  xhttp.send();
                }
                }
           </script>
  </body>
</html>

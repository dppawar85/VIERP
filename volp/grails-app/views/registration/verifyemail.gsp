<!DOCTYPE html>
<html>
<head>
 <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <meta charset="ISO-8859-1">
  <asset:stylesheet src="bootstrap.min.css"/>
  <asset:stylesheet src="font-awesome.min.css"/>
  <asset:stylesheet src="sb-admin.css"/>
  <title>Registration</title>
   <style>
   #myProgress {
     width: 100%;

   }

   #myBar {
     width: 0%;
     height: 24px;
     background-color: #FF5252;
     border-radius: 10px;
   }
   </style>
</head>
<body class="bg-light">
  <div class="container">
    <div class="card card-login mx-auto mt-5">
      <div class="card-header bg-warning">Registration</div>
      <g:if test="${flash.message}">
      <div class="alert alert-error" style="display: block;color:red">${flash.message}</div>
    </g:if>
    <div class="card-body">
      <div class="form-group" id="disableForm">
        <label for="exampleInputEmail1">Email address</label>
        <input class="form-control" id="email" name="email" type="text" placeholder="Enter Your Email" aria-describedby="emailHelp" required="true">
        </br>

      <button id="verifyEmailbtn" class="btn btn-primary btn-block" name="Verify Email" value="Verify Email" onclick="callRegistration()" >Verify Email</button>
       </div>


    <div id="responseResgistration">

    </div>
    <div id="myProgress">
      <div id="myBar">

      </div>
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

function verifyConfirmPassword() {

    if (document.getElementById('password').value === document.getElementById('confirmpassword').value) {
    document.getElementById('message').style.color = 'green';
    document.getElementById('message').innerHTML = 'matching';
    return true;
  } else {
    document.getElementById('message').style.color = 'red';
    document.getElementById('message').innerHTML = 'not matching';
    document.getElementById('confirmpassword').focus();
    return false;
  }
  }
  function callRegistration() {
  var emailReg=/^([\w-\.]+@([\w]+\.)+[\w-]{2,4})?$/;
    var email=document.getElementById('email').value;
    var verifyEmailbtn=document.getElementById('verifyEmailbtn');

    if(email==="" || !(email).match(emailReg))
    {
     alert("please enter valid email address");
    }
    else{
         callProgressBar();
        var xhttp = new XMLHttpRequest();
         verifyEmailbtn.style.visibility="hidden";
        xhttp.onreadystatechange = function() {

        if (this.readyState == 4 && this.status == 200) {

       document.getElementById("responseResgistration").innerHTML = this.responseText;
       document.getElementById('disableForm').style.display="none";
       document.getElementById("myBar").style.display="none";
     }
     if (this.status == 404){
       alert("Something went wrong!")
     }
     if (this.readyState == 4 && this.status == 500){
              alert("User already exist or Email address does not exist")
                 }
   };
   xhttp.open("GET", "${request.contextPath}/registration/verifyemailprocess?email="+email, true);
   xhttp.send();
   }
 }

 function confirmOTP() {
     var email=document.getElementById('email').value;
     var otp=document.getElementById('otp').value;
     var xhttp = new XMLHttpRequest();

     xhttp.onreadystatechange = function() {
       if (this.readyState == 4 && this.status == 200) {
        document.getElementById("responseResgistration").innerHTML = this.responseText;
        document.getElementById('disableForm').style.display="none";
      }
      if (this.status == 404){
        alert("Something went wrong!")
      }

    };
    xhttp.open("GET", "${request.contextPath}/registration/verifyotp?email="+email+"&otp="+otp, true);
    xhttp.send();
  }

</script>
</body>
</html>

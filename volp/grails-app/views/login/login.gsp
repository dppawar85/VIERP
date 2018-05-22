<!DOCTYPE html>
<html>
<head>
  <meta charset="ISO-8859-1">
   <meta name="viewport" content="width=device-width, initial-scale=1">
  <asset:stylesheet src="bootstrap.min.css"/>
   <asset:stylesheet src="font-awesome.min.css"/>
   <asset:stylesheet src="sb-admin.css"/>

  <title>Login</title>
  <!-- FB -->
  <script>
        // initialize and setup facebook js sdk //volp
        window.fbAsyncInit = function() {
          FB.init({
            appId      : '${fbkey}',
            xfbml      : true,
            version    : 'v2.5'
          });
          FB.getLoginStatus(function(response) {
           if (response.status === 'connected') {
            //document.getElementById('show1').innerHTML = 'We are connected.';
            document.getElementById('login').style.visibility = 'hidden';
          } else if (response.status === 'not_authorized') {
            //document.getElementById('show1').innerHTML = 'We are not logged in.'
          } else {
            //document.getElementById('show1').innerHTML = 'You are not logged into Facebook.';
          }
        });
        };
        (function(d, s, id){
          var js, fjs = d.getElementsByTagName(s)[0];
          if (d.getElementById(id)) {return;}
          js = d.createElement(s); js.id = id;
          js.src = "//connect.facebook.net/en_US/sdk.js";
          fjs.parentNode.insertBefore(js, fjs);
        }(document, 'script', 'facebook-jssdk'));

        // login with facebook with extra permissions
        function login() {
          FB.login(function(response) {
            if (response.status === 'connected') {
              //document.getElementById('show1').innerHTML = 'We are connected.';
                //document.getElementById('login').style.visibility = 'hidden';
                //alert("3");
                // getting bas ic user info
                FB.api('/me', 'GET', {fields: 'first_name,last_name,id,picture.width(150).height(150)'}, function(response) {
                  //alert("4");
                  if (response.id.length == 0) {
                   document.getElementById("show1").innerHTML = "Email not found:"+user.firstName+":"+user.headline;
                   return;
                 } else {
                  alert("FB");
                   var xmlhttp = new XMLHttpRequest();
                   xmlhttp.onreadystatechange = function() {
                     if (this.readyState == 4 && this.status == 200) {
                       //alert("1");
               document.getElementById("show1").innerHTML = this.responseText;
                  window.location = "${request.contextPath}/login/redirect";
                     }
                   };
               //alert("2");
               xmlhttp.open("GET", "${request.contextPath}/login/processfblogin?id=" + response.id + "&fname="+response.first_name + "&lname="+response.last_name, true);
               xmlhttp.send();

             }
           });
              } else if (response.status === 'not_authorized') {
                document.getElementById('show1').innerHTML = 'We are not logged in.'
              } else {
                document.getElementById('show1').innerHTML = 'You are not logged into Facebook.';
              }
           }, {scope: 'email'});

        }
        function enable_disable_form(){


        }
      </script>
      <!-- End FB -->
      <!-- Linkedin -->
      <script type="text/javascript" src="//platform.linkedin.com/in.js">

        api_key: 81vqng3xkc3uiw
        authorize: false
        onLoad: onLinkedInLoad
        scope: r_basicprofile r_emailaddress
      </script>
      <script type="text/javascript">
        // Setup an event listener to make an API call once auth is complete  onLinkedInLoad
        function onLinkedInLoad() {

          IN.Event.on(IN, "auth", getProfileData);
        }

        // Use the API call wrapper to request the member's profile data
        function getProfileData() {

          IN.API.Profile("me").fields("id", "first-name", "last-name", "headline", "location", "picture-url", "public-profile-url", "email-address").result(displayProfileData).error(onError);
        }

        // Handle the successful return from the API call
        function displayProfileData(data){

          var user = data.values[0];
            //alert("data:"+user);
           // document.getElementById("picture").innerHTML = '<img src="'+user.pictureUrl+'" />';
          //document.getElementById("name").innerHTML = user.firstName+' '+user.lastName;
         //  document.getElementById("intro").innerHTML = user.headline;

           // document.getElementById("email").innerHTML = '<input type="text" value="'+user.emailAddress+'" name="email" disabled>';
          //  document.getElementById("location").innerHTML = user.location.name;
         //   document.getElementById("link").innerHTML = '<a href="'+user.publicProfileUrl+'" target="_blank">Visit profile</a>';
         //   document.getElementById('profileData').style.display = 'block';
            
            //document.getElementById("show1").innerHTML = "Email not found:"+user.firstName+":"+user.id;
            if (user.id.length == 0) {
             document.getElementById("show1").innerHTML = "Email not found:"+user.firstName+":"+user.headline;
             return;
           } else {
            alert("LD");
             var xmlhttp = new XMLHttpRequest();
             xmlhttp.onreadystatechange = function() {
               if (this.readyState == 4 && this.status == 200) {
                   //alert("1");
                   document.getElementById("show1").innerHTML = this.responseText;
                   window.location = "${request.contextPath}/login/redirect";
                 }
               };
           alert("uerid:"+user.id);
           xmlhttp.open("GET", "${request.contextPath}/login/processsignlingdin?id=" + user.id+"&fname="+user.firstName +"&lname="+user.lastName, true);
           xmlhttp.send();

         }
       }

        // Handle an error response from the API call
        function onError(error) {
          console.log(error);
        }

        // Destroy the session of linkedin
        function logout(){
       // alert("hi");
       IN.User.logout(removeProfileData);
     }

        // Remove profile data from page
        function removeProfileData(){
          document.getElementById('profileData').remove();
        }


        function liAuth(){
        document.getElementById("show1").innerHTML ="";
         IN.User.authorize(function(){
       //callback();
     });
       }
     </script>
     <!-- end of Linkedin -->

     <script>

      function init(){

        //window.location ="https://accounts.google.com/o/oauth2/auth?scope=https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/plus.login&redirect_uri=http://localhost:8080/volp/login/processlogin&response_type=code&client_id=1044538822480-rp49uql6hntc6vk2eessltv99gecpfqp.apps.googleusercontent.com&approval_prompt=force";
        window.location ="https://accounts.google.com/o/oauth2/auth?scope=https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/plus.login&redirect_uri=http://www.volp.in/login/processlogin&response_type=code&client_id=1044538822480-rp49uql6hntc6vk2eessltv99gecpfqp.apps.googleusercontent.com&approval_prompt=force";
            //return false;
          }

          function getUserType(utypid){
        //alert("valuue:"+typ)
         document.getElementById("enable_disable_form").style.display = 'none';
         if(utypid=="null"){
                      document.getElementById("enable_disable_form").style.display = 'block';
                     return
                 }
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
         if (this.readyState == 4 && this.status == 200) {
           //alert(utypid);
           document.getElementById("show1").innerHTML = this.responseText;
          // alert(this.responseText);
        }
      };
      xmlhttp.open("GET", "${request.contextPath}/login/setusertype?utid=" + utypid, true);
      xmlhttp.send();

    }
  </script>


  <style>
  input[type="submit"] {
   width :100%;
   margin-top: 2px;
  }
</style>
 </head>

<body class="bg-light">
  <div class="container">
    <div class="card card-login mx-auto mt-5">
      <div class="card-header bg-warning">Sign In</div>
      <g:if test="${flash.message}">
           <div class="alert alert-error" style="display: block;color:red">${flash.message}</div>
       </g:if>
      <div class="card-body">
      <fieldset>
               <legend class="small"><b>Sign-In with Google+ / LinkedIn / Facebook</b></legend>
                <div class="form-group">
                <label for="selectUserType">Select User Type</label>
                <g:select class="form-control" optionKey="id" from="${usertype}" optionValue="${{it.type}}" noSelection="${['null':'Select Type']}" name="usertype" onchange="getUserType(this.value);" />
                   <div id="show1" >

                   </div>
                      <!-- <script type="in/Login"></script> -->
                </div>
       </fieldset>
     <g:form name="myForm" url="[action:'processsignin',controller:'login']">

        <hr>
         <fieldset id="enable_disable_form">
                 <legend class="small text-center"><b>------------------OR-------------------</b></legend>
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
</html>

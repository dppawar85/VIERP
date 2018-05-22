<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title></title>
	<link rel="stylesheet" href="">
</head>
<body>
	<script>
		// initialize and setup facebook js sdk //volp
		window.fbAsyncInit = function() {
		    FB.init({
		      appId      : '177004436234883',
		      xfbml      : true,
		      version    : 'v2.5'
		    });
		    FB.getLoginStatus(function(response) {
		    	if (response.status === 'connected') {
		    		document.getElementById('show1').innerHTML = 'We are connected.';
		    		document.getElementById('login').style.visibility = 'hidden';
		    	} else if (response.status === 'not_authorized') {
		    		document.getElementById('show1').innerHTML = 'We are not logged in.'
		    	} else {
		    		document.getElementById('show1').innerHTML = 'You are not logged into Facebook.';
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
		    		document.getElementById('show1').innerHTML = 'We are connected.';
		    		document.getElementById('login').style.visibility = 'hidden';
		    		// getting basic user info
		    		FB.api('/me', 'GET', {fields: 'first_name,last_name,id,picture.width(150).height(150),email'}, function(response) {
                                				if (user.id.length == 0) {
                                                                                   document.getElementById("show1").innerHTML = "Email not found:"+user.firstName+":"+user.headline;
                                                                                   return;
                                                                               } else {
                                                                                   var xmlhttp = new XMLHttpRequest();
                                                                                   xmlhttp.onreadystatechange = function() {
                                                                                       if (this.readyState == 4 && this.status == 200) {
                                                                                           //alert("1");
                                                                                           document.getElementById("show1").innerHTML = this.responseText;
                                                                                           window.location = "http://localhost:8080/login/redirect";
                                                                                       }
                                                                                   };
                                                                                   //alert("2");
                                                                                   xmlhttp.open("GET", "processfblogin?id=" + user.id, true);
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
	</script>

	<div id="show1"></div>

	<button onclick="login()" id="login">Login</button>
</body>
</html>
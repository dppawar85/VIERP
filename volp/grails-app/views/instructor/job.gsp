job


<script type="IN/JYMBII" data-format="inline"></script>
<a href="https://www.linkedin.com/profile/add?startTask=CERTIFICATION_NAME" target="_blank">ADD CERTIFICATION</a>
<a href="https://www.linkedin.com/profile/add?startTask=SCHOOL_NAME" target="_blank">Add education</a>

<a href="https://api.linkedin.com/v2/recommendedJobs?q=byMember">user</a>

<!-- Linkedin -->
      <script type="text/javascript" src="//platform.linkedin.com/in.js">

        api_key: 81vqng3xkc3uiw
        authorize: false
        onLoad: onLinkedInLoad
        scope: r_basicprofile r_emailaddress r_JYMBII
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

            if (user.id.length == 0) {
             document.getElementById("show1").innerHTML = "Email not found:"+user.firstName+":"+user.headline;
             return;
           } else {
            alert("LD");
             var xmlhttp = new XMLHttpRequest();
             xmlhttp.onreadystatechange = function() {
               if (this.readyState == 4 && this.status == 200) {
                   alert("1");
                   document.getElementById("show").innerHTML = this.responseText;
                   window.location = "${request.contextPath}/login/redirect";
                 }
               };
           alert("uerid:"+user.id);
         //  window.location.href ="https://api.linkedin.com/v2/recommendedJobs?q=vikas-chauhan-6aa65461";
        var url = "https://api.linkedin.com/v2/recommendedJobs?q=" + user.id;
        var xhr = createCORSRequest('GET',url);
           //xmlhttp.open("GET", "https://api.linkedin.com/v2/recommendedJobs?q=" + user.id, true);
           xhr.setRequestHeader('X-Custom-Header','value');
           xhr.send();
            alert("done"+user.publicProfileUrl);

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
        alert("1");
       // document.getElementById("show1").innerHTML ="";
         IN.User.authorize(function(){
       //callback();
     });
       }
     </script>
     <!-- end of Linkedin -->

     <a href="#" onclick="liAuth()">job</a>

     <div id="show"></div>
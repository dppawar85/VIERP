    <!doctype html>
    <html lang="en" class="no-js">
    <head>

      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
      <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
      <title>
        <g:layoutTitle default="VOLP || Dashboard"/>
      </title>
      <meta name="viewport" content="width=device-width, initial-scale=1"/>


      <asset:stylesheet src="bootstrap.min.css"/>
      <asset:stylesheet src="font-awesome.min.css"/>
      <asset:stylesheet src="sb-admin.css"/>

      <asset:stylesheet src="courseInstructor.css"/>


    </head>
    <body class="bgimg">
     <g:render template="/layouts/admin/adminNav" />
     <br> <br>
     <div class="content-wrapper ">
      <div class="container-fluid" id="mainContentDiv">
        <!-- container-fluid-->

        <g:layoutBody/>

      </div><!-- End of container-fluid-->

    </div> <!-- End of content-wrapper-->


    <g:render template="/layouts/admin/adminFooter" />
<script>
function viewFeedback()
{

alert('Hello');
var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                document.getElementById("mainContentDiv").innerHTML = this.responseText;
            }
        };
        xmlhttp.open("GET", "${request.contextPath}/admin/viewFeedback", true);
        xmlhttp.send();
}
</script>

    <asset:javascript src="jquery.min.js"/>
    <asset:javascript src="bootstrap.bundle.min.js"/>
    <asset:javascript src="jquery.easing.min.js"/>
    <asset:javascript src="sb-admin.min.js"/>


  </body>
  </html>

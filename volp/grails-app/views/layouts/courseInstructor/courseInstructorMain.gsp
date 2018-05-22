    <!doctype html>
    <html lang="en" class="no-js">
    <head>

      <meta name="viewport" content="width=device-width, initial-scale=1"/>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
      <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
      <title>
        <g:layoutTitle default="VOLP || Dashboard"/>
      </title>

      <asset:stylesheet src="bootstrap.min.css"/>
      <asset:stylesheet src="font-awesome.min.css"/>
      <asset:stylesheet src="sb-admin.css"/>


      <asset:stylesheet src="courseInstructor.css"/>
      <style>

      </style>

      <g:layoutHead/>

    </head>
    <body id="page-top">
     <g:render template="/layouts/courseInstructor/courseInstructorNav" />
     <br> <br>
     <div class="content-wrapper">
      <div class="container-fluid">
        <!-- container-fluid-->
        <g:layoutBody/>

      </div><!-- End of container-fluid-->

    </div> <!-- End of content-wrapper-->


    <g:render template="/layouts/courseInstructor/courseInstructorFooter" />


<script>
function givenPlatformFeedback(type)
{
//alert(type)
 var xmlhttp = new XMLHttpRequest();
          xmlhttp.onreadystatechange = function() {
              if (this.readyState == 4 && this.status == 200) {
                 document.getElementById("mainContentDiv").innerHTML = this.responseText;
                 //alert("done");
              }
          };
          xmlhttp.open("GET", "${request.contextPath}/platformFeedback/givenPlatformFeedback?type="+type, true);
          xmlhttp.send();
}
function changeFee(val)
{
  var xmlhttp = new XMLHttpRequest();
         xmlhttp.onreadystatechange = function() {
             if (this.readyState == 4 && this.status == 200) {
                 document.getElementById("test").innerHTML = this.responseText;
             }
         };
         xmlhttp.open("GET", "${request.contextPath}/courseFees/changeFees?Doller="+val, true);
         xmlhttp.send();
}
function setCourseFees()
{
  var xmlhttp = new XMLHttpRequest();
         xmlhttp.onreadystatechange = function() {
             if (this.readyState == 4 && this.status == 200) {
                 document.getElementById("mainContentDiv").innerHTML = this.responseText;
             }
         };
         xmlhttp.open("GET", "${request.contextPath}/courseFees/setCourseFee", true);
         xmlhttp.send();
}
function callOfferCourse()
{
  var xmlhttp = new XMLHttpRequest();
         xmlhttp.onreadystatechange = function() {
             if (this.readyState == 4 && this.status == 200) {
                 document.getElementById("mainContentDiv").innerHTML = this.responseText;
             }
         };
         xmlhttp.open("GET", "${request.contextPath}/CourseOffering/selectcourse", true);
         xmlhttp.send();
}
function showMenu() {

        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                document.getElementById("mainContentDiv").innerHTML = this.responseText;
            }
        };
        xmlhttp.open("GET", "${request.contextPath}/Instructor/addCourse", true);
        xmlhttp.send();

}
function callInstructorHelp(){
 var xmlhttp = new XMLHttpRequest();
     xmlhttp.onreadystatechange = function() {
                        if (this.readyState == 4 && this.status == 200) {
                            document.getElementById("mainContentDiv").innerHTML = this.responseText;
                        }
                    };
                    xmlhttp.open("GET", "${request.contextPath}/instructor/instrcutorHelp", true);
                    xmlhttp.send();
}

 function addCat(cat) {
 //alert("cat:"+cat);
    document.getElementById('submitCrs').disabled = false;
             var msgflg=0
             if (cat == "null") {
            // alert("hi");
                 //document.getElementById("txtHint").innerHTML = "";
                 document.getElementById('submitCrs').disabled = true;
                 return;
             } else {
                 var xmlhttp = new XMLHttpRequest();
                 xmlhttp.onreadystatechange = function() {
                     if (this.readyState == 4 && this.status == 200) {
                 //alert(cat)
                 document.getElementById("scat").innerHTML = this.responseText;
             }
         };
         xmlhttp.open("GET", "${request.contextPath}/instructor/subCat?cat=" + cat+"&msgflg="+msgflg, true);
         xmlhttp.send();
             }
         }
         function addCats(cat) {
             //alert("second")

             if (cat.length == 0) {
                 document.getElementById("txtHint").innerHTML = "";
                 return;
             } else {
                 var xmlhttp = new XMLHttpRequest();
                 xmlhttp.onreadystatechange = function() {
                     if (this.readyState == 4 && this.status == 200) {
                             //alert(cat)
                             document.getElementById("scat").innerHTML = this.responseText;
                         }
                     };
                     xmlhttp.open("GET", "${request.contextPath}/subCat?cat=" + cat, true);
                     xmlhttp.send();
                 }
             }
             function add_feed()
             {
                 var div1 = document.createElement('div');
         // Get template data
         div1.innerHTML = document.getElementById('newlinktpl').innerHTML;

         // append to our form, so that template data
         //become part of form

         elm=document.getElementById('newlink').appendChild(div1);

           }
     function add_feeddes()
     {
         var div1 = document.createElement('div');
             // Get template data
             div1.innerHTML = document.getElementById('newlinktpldes').innerHTML;
             // append to our form, so that template data
             //become part of form
             document.getElementById('newlinkdes').appendChild(div1);
         }
         function add_feedpre()
         {
             var div1 = document.createElement('div');
             // Get template data
             div1.innerHTML = document.getElementById('newlinktplpre').innerHTML;
             // append to our form, so that template data
             //become part of form
             document.getElementById('newlinkpre').appendChild(div1);
         }
         function add_feedurl()
         {
             var div1 = document.createElement('div');
             // Get template data
             div1.innerHTML = document.getElementById('newlinktplurl').innerHTML;
             // append to our form, so that template data
             //become part of form
             document.getElementById('newlinkurl').appendChild(div1);
         }

         function callMe()
         {
            alert("hello");

         }

 function searchcrs(crslst){
    //alert(crslst);
    var crs= document.getElementById("searchcrs1").value;
    //alert(crs)
    var xmlhttp = new XMLHttpRequest();
                     xmlhttp.onreadystatechange = function() {
                         if (this.readyState == 4 && this.status == 200) {
                                 //alert(cat)
                                 document.getElementById("showcrs").innerHTML = this.responseText;
                             }
                         };
                         xmlhttp.open("GET", "${request.contextPath}/instructor/getCourse?crs=" + crs+"&crslst="+crslst, true);
                         xmlhttp.send();

 }
function hidecolValue(val){

      if(val.value==='yes')
      {
        document.getElementById('collaborativeInstructor').style.display="block";
      }
      if(val.value==='no')
      {
        document.getElementById('collaborativeInstructor').style.display="none";
      }
 }

    </script>
            <asset:javascript src="jquery.min.js"/>
             <asset:javascript src="bootstrap.bundle.min.js"/>
             <asset:javascript src="jquery.easing.min.js"/>
             <asset:javascript src="sb-admin.min.js"/>
  <script>
    function pop(){

      $('[data-toggle="popover"]').popover();
   }

  </script>

  </body>
  </html>

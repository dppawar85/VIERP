<head>

<style>
body, html {
    height: 100%;
    width:100%;
}
.bg {
    /* The image used */
    background-image: url("http://localhost/vit/certificate_background.png");

    /* Full height */

      height:100%;
          width:80%;

    /* Center and scale the image nicely */
    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
}
</style>

<script>

function printElem(divId) {
         var content = document.getElementById(divId).innerHTML;
         var mywindow = document.body.innerHTML;

         document.body.innerHTML=content ;
         window.print();
         document.body.innerHTML=mywindow;
                 return true;
     }

</script>
</head>



<body >

   <center>

<div id="print" >
<div class="bg">
<br>
<center>
   <!--<asset:image src="homeImages/vit_logo.png" alt="VI Logo" /> -->
<br>
<br>
<br><br>
<br>
<br>

    <div style="text-align:right;margin-right:12%;">Certificate No:${cc.certificate_number}</div>

    <br>
    <img src="http://localhost/vit/vit_logo.png"/>
     <h2>Course Completion Certificate</h2>
    This is to certify that
    <br><br>
    <g:if test="${cc.courseofferinglearner.learner.person.firstName!=""}">
        <b>${cc.courseofferinglearner.learner.person.firstName}&nbsp;&nbsp;</b>
    </g:if>
    <g:if test="${cc.courseofferinglearner.learner.person.middleName!=""}">
        <b>${cc.courseofferinglearner.learner.person.middleName}&nbsp;&nbsp;</b>
    </g:if>
    <g:if test="${cc.courseofferinglearner.learner.person.lastName!=""}">
        <b>${cc.courseofferinglearner.learner.person.lastName}&nbsp;&nbsp;</b>
    </g:if>
    <br><br>
        Has successfully Completed
     <br><br>
    Course On <b>${cc.courseofferinglearner.courseoffering.course.course_name}</b>
    <br><br>
    Duration:
    <g:if test="${coursetype=="Weekwise"}">
        ${cc.period}<br>
        Weeks(${cc.number_of_weeks})
    </g:if>
    <g:if test="${coursetype=="Self-Pace"}">
        ${cc.number_of_hours} Hours
    </g:if>
    <br><br>
    With Profociency
    <br><br><br>
    Instructor:${cc.courseofferinglearner.courseoffering.course.courseowner.person.firstName}&nbsp;${cc.courseofferinglearner.courseoffering.course.courseowner.person.lastName}
   <br> <div align="center">Date:<g:formatDate format="dd-MM-yyyy" date="${cc.certificate_date}"/></div>
    </center>
</div>
</div>
<br>
<button onclick="printElem('print')">PRINT&nbsp; <i class="fa fa-2x fa-print" aria-hidden="true"></i></button>
<br><br>
</body>
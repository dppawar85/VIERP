<html>
<head>
<meta name="layout" content="blank">
<style>
.error{
color:red;
}
</style>

<script>
function fetchaddoptions(myval)
{
if(myval=="7")
{
    alert("Max Options reached.");
    var x=document.getElementById("bt"+myval);
      x.style.display='none';
    return;
}
//alert("test1"+myval)
  var x=document.getElementById("bt"+myval);
  x.style.display='none';
   var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
                       if (this.readyState == 4 && this.status == 200) {
                                //document.getElementById("test"+myval).innerHTML = this.responseText;
                                document.getElementById("test1"+myval).innerHTML = this.responseText;
                       }
                   };
                   xmlhttp.open("GET", "${request.contextPath}/assignment/furtherprocessaddassignment", true);
                   xmlhttp.send();
         //  return(false)
}
function fetchcourseoutline(coffrid)
{
    //alert("hi::"+coffrid);
        var courseoutline = document.getElementById("courseoutline").value;
        var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange = function() {
                    if (this.readyState == 4 && this.status == 200) {
                        document.getElementById("material2").innerHTML = this.responseText;
                    }
                };
                xmlhttp.open("GET", "${request.contextPath}/assignment/addcourseoutlines?courseoutline=" + courseoutline+"&coffrid=" + coffrid, true);
                xmlhttp.send();
       // return(false)
  }

</script>
</head>
<body>
<div style="position: fixed; top: 50px; right: 80px" class="bg-warning">
    <a class="btn btn-sm" style="color: black" href="/instructor/instructor"> <i class="fa fa-home" aria-hidden="true"></i> HOME </a>
</div>
 <form action="saveassignment"> <br>
 <center><h5>Add Assignment</h5></center>
<table class="table table-bordered" style="width: 80%; position: relative; margin: auto auto">
<tbody>
<tr><td><label>Course</label></td><td><input class="form-control" name="coursecodecoursename" type="text" value="${coff.course.course_code}&nbsp;${coff.course.course_name}" readonly></td></tr>
<tr><td><label>Description</label></td><td><input class="form-control" name="batch" type="text" value="${coff.batch}" readonly></td></tr>
<g:if test="${coff.courseofferingtype.name!='Self-Pace'}">
    <tr><td><label>Start Date&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Commencement Date of the course </span> </i></label></td> <td> <g:formatDate date="${coff.start_date}" name="start_date" format="dd-MM-yyyy"/></td></tr>
    <tr><td><label>End Date&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Last date of Course Schedule </span> </i></label></td> <td> <g:formatDate date="${coff.end_date}" name="end_date" format="dd-MM-yyyy"/></td></tr>
</g:if>
<tr><td><label>Assignment Type</label></td><td><input class="form-control" name="assignmenttype" type="text" value="${assignmenttype}" readonly></td></tr>
</tbody>
</table>
<div style="overflow:scroll">
<table class="table table-bordered" style="width: 80%; position: relative; margin: auto auto">
<tbody>
<g:if test="${session.uploadcategory=='Unit' || session.uploadcategory=='Topic'}">
    <g:if test="${onlyTopic}">
        <tr><td><span class="error">*</span>&nbsp;Course Topic</td><td><g:select class="form-control" name="coursetopic" id="coursetopic" from="${ctop}" optionValue="topic" optionKey="id" required="true"/></td></tr>
        <tr><td><lable><span class="error">*</span>&nbsp; Assignment Title</lable></td><td><input class="form-control" name="assignment_title" id="assignment_title" type="text" size="60" required></td></tr>
        <tr><td><lable><span class="error">*</span>&nbsp; Assignment Weightage&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Maximum Marks for Assignment </span> </i></lable></td><td><input class="form-control" name="assignment_weightage" id="assignment_weightage" type="text" required></td></tr>
        <tr><td><lable><span class="error">*</span>&nbsp; Problem Statement&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Descriptive problem statement </span> </i></lable></td><td><textarea class="form-control" name="assignment_text" id="assignment_text" row="30" cols="50"  type="text" size="60" required></textarea></td></tr>
        <tr><td><lable><span class="error">*</span>&nbsp; Difficulty Level&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext"> Easy or Difficult </span> </i></lable></td><td><g:select class="form-control" name="difficultylevel" id="difficultylevel" from="${difficultylevellist}" required="true"/></td></tr>
        <g:if test="${coff.courseofferingtype.name!='Self-Pace'}">
            <tr><td><lable><span class="error">*</span>&nbsp;Scheduled Date &nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Date from which Assignment will be visible to the students  </span> </i></lable></td><td><g:datePicker class="form-control" name="schedule_date"   precision="day" value="${new Date()}"/></td></tr>
            <tr><td><lable><span class="error">*</span>&nbsp;Due Date&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Last Date upto which students can submit the answers for the assignment </span> </i></lable></td><td><g:datePicker name="due_date"   precision="day" value="${new Date()}"/></td></tr>
        </g:if>
        <tr><td><lable>Assignment Link&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">If assignment is to be given in softcopy format plz upload it on google drive and paste a link in the text field </span> </i></lable></td><td><input class="form-control" name="assignment_link" id="assignment_link" type="text" size="60" ></td></tr>
        <g:if test="${session.assignmenttype=='MCQ'}">
            <tr><th colspan="2"><center>Enter Options</center></th></tr>
               <!-- <tr><td><lable><span class="error">*</span>&nbsp;Option Name</lable></td><td><g:select class="form-control" name="option_name" id="option_name" from="${mcqoptionlist}" /></td></tr>  -->
                <tr><td><lable><span class="error">*</span>&nbsp;Option Text&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">MCQ option answer text </span> </i></lable></td><td><input class="form-control" name="option_value" id="option_value" type="text" size="60" ></td></tr>
                <tr><td><span class="error">*</span>&nbsp;Is Correct Option?&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">If this option is correct then select Yes else select No </span> </i></td><td><g:select class="form-control" name="isCorrect" id="isCorrect" from="${correctoptionlist}"/></td></tr>
                <tr><td><lable>Option File Link&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">If option is in the form of Image or Pdf plz provide link </span> </i></lable></td><td><input class="form-control" name="mcq_option_file_link" id="mcq_option_file_link" type="text" size="60" ></td></tr>
               <!-- <tr><td><lable>Option File Name</lable></td><td><input class="form-control" name="mcq_option_file_name" id="mcq_option_file_name" type="text" size="60" ></td></tr>  -->
                <tr><td></td>
                <td><input class="btn btn-primary" type="button" name="Add Another Option" id="bt${session.trackoptions}" value="Add Another Option" onclick="return fetchaddoptions(${session.trackoptions});"/>

        <br>
        <br>
        <br>
                </td></tr>

                <tr ><td colspan="2">
        <div id="test11"></div>
                        <div id="test12"></div>
                        <div id="test13"></div>
                        <div id="test14"></div>
                        <div id="test15"></div>
                        <div id="test16"></div>
                </td></tr>
            </g:if>
    </g:if>
    <g:else>
<tr><td colspan="2"><span class="error">*</span>&nbsp;Course Unit <g:select class="form-control" name="courseoutline" id="courseoutline" from="${courseoutlinelist.outline}" onChange="fetchcourseoutline(${coffrid});"/></td></tr>
    </g:else>
</g:if>
<g:else>
<!-- <tr><td><lable><span class="error">*</span>&nbsp; Assignment No.</lable></td><td><input class="form-control" name="assignment_no" id="assignment_no" type="text" required pattern="[0-9]{1,4}"></td></tr>
<tr><td><lable><span class="error">*</span>&nbsp; Assignment Title</lable></td><td><input class="form-control" name="assignment_title" id="assignment_title" type="text" size="60" required></td></tr>
<tr><td><lable><span class="error">*</span>&nbsp; Assignment Description</lable></td><td><input class="form-control" name="assignment_description" id="assignment_description" type="text" size="60" required></td></tr>  -->
<tr><td><lable><span class="error">*</span>&nbsp; Assignment Weightage&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Maximum Marks for Assignment </span> </i></lable></td><td><input class="form-control" name="assignment_weightage" id="assignment_weightage" type="text" required></td></tr>
<tr><td><lable><span class="error">*</span>&nbsp; Problem Statement&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Descriptive problem statement </span> </i></lable></td><td><textarea class="form-control" name="assignment_text" id="assignment_text" row="30" cols="50"  type="text" size="60" required></textarea></td></tr>
<tr><td><lable><span class="error">*</span>&nbsp; Difficulty Level&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext"> Easy or Difficult </span> </i></lable></td><td><g:select class="form-control" name="difficultylevel" id="difficultylevel" from="${difficultylevellist}" required="true"/></td></tr>
<g:if test="${coff.courseofferingtype.name!='Self-Pace'}">
    <tr><td><lable><span class="error">*</span>&nbsp;Scheduled Date &nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Date from which Assignment will be visible to the students  </span> </i></lable></td><td><g:datePicker class="form-control" name="schedule_date"   precision="day" value="${new Date()}"/></td></tr>
    <tr><td><lable><span class="error">*</span>&nbsp;Due Date&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Last Date upto which students can submit the answers for the assignment </span> </i></lable></td><td><g:datePicker name="due_date"   precision="day" value="${new Date()}"/></td></tr>
</g:if>
<tr><td><lable>Assignment Link&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">If assignment is to be given in softcopy format plz upload it on google drive and paste a link in the text field </span> </i></lable></td><td><input class="form-control" name="assignment_link" id="assignment_link" type="text" size="60" ></td></tr>
<!-- <tr><td><lable>Assignment File Name</lable></td><td><input class="form-control" name="assignment_name" id="assignment_name" type="text" size="60" ></td></tr> -->
    <g:if test="${session.assignmenttype=='MCQ'}">
    <tr><th colspan="2"><center>Enter Options</center></th></tr>
       <!-- <tr><td><lable><span class="error">*</span>&nbsp;Option Name</lable></td><td><g:select class="form-control" name="option_name" id="option_name" from="${mcqoptionlist}" /></td></tr>  -->
        <tr><td><lable><span class="error">*</span>&nbsp;Option Text&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">MCQ option answer text </span> </i></lable></td><td><input class="form-control" name="option_value" id="option_value" type="text" size="60" ></td></tr>
        <tr><td><span class="error">*</span>&nbsp;Is Correct Option?&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">If this option is correct then select Yes else select No </span> </i></td><td><g:select class="form-control" name="isCorrect" id="isCorrect" from="${correctoptionlist}"/></td></tr>
        <tr><td><lable>Option File Link&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">If option is in the form of Image or Pdf plz provide link </span> </i></lable></td><td><input class="form-control" name="mcq_option_file_link" id="mcq_option_file_link" type="text" size="60" ></td></tr>
       <!-- <tr><td><lable>Option File Name</lable></td><td><input class="form-control" name="mcq_option_file_name" id="mcq_option_file_name" type="text" size="60" ></td></tr>  -->
        <tr><td></td>
        <td><input class="btn btn-primary" type="button" name="Add Another Option" id="bt${session.trackoptions}" value="Add Another Option" onclick="return fetchaddoptions(${session.trackoptions});"/>

<br>
<br>
<br>
        </td></tr>

        <tr ><td colspan="2">
<div id="test11"></div>
                <div id="test12"></div>
                <div id="test13"></div>
                <div id="test14"></div>
                <div id="test15"></div>
                <div id="test16"></div>
        </td></tr>
    </g:if>
    <g:else>
     <!--    <tr><td><lable>Model Answer Text</lable></td><td><input class="form-control" name="model_answer_text" id="model_answer_text" type="text" size="60" ></td></tr>
       <tr><td><lable>Model Answer File Link</lable></td><td><input class="form-control" name="model_answer_link" id="model_answer_link" type="text" size="60" ></td></tr>
        <tr><td><lable>Model Answer File Name</lable></td><td><input class="form-control" name="model_answer_name" id="model_answer_name" type="text" size="60" ></td></tr>  -->
    </g:else>
</g:else>
 <td><div id="material2"></div></td>
<!-- <td><div id="test1"></div></td>
 <td><div id="test2"></div></td>
 <td><div id="test3"></div></td>
 <td><div id="test4"></div></td>
 <td><div id="test5"></div></td>
 <td><div id="test6"></div></td>
 <td><div id="test7"></div></td>
 <td><div id="test8"></div></td> -->

</tbody>
</table>
<center><input class="btn btn-primary" type="submit" value="Save Assignment" > </center>
</div>
</form>
<br><br>
</body>
</html>
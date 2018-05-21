<style>
.error{
color:red;
}
</style>

<table class="table table-bordered">
<tbody>
 <g:if test="${session.uploadcategory=='Topic'}">
<tr><td><span class="error">*</span>&nbsp;Course Topic</td><td><g:select class="form-control" name="coursetopic" id="coursetopic" from="${coursetopiclist.topic}" required="true"/></td></tr>
</g:if>
<!-- <tr><td><lable><span class="error">*</span>&nbsp;Assignment No.</lable></td><td><input class="form-control" name="assignment_no" id="assignment_no" type="text" required pattern="[0-9]{1,4}"></td></tr>
<tr><td><lable><span class="error">*</span>&nbsp;Assignment Title</lable></td><td><input class="form-control" name="assignment_title" id="assignment_title" type="text" size="60" required></td></tr>
<tr><td><lable><span class="error">*</span>&nbsp;Assignment Description</lable></td><td><input class="form-control" name="assignment_description" id="assignment_description" type="text" size="60" required></td></tr> -->
<tr><td><lable><span class="error">*</span>&nbsp;Assignment Weightage&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Maximum Marks for Assignment </span> </i></lable></td><td><input class="form-control" name="assignment_weightage" id="assignment_weightage" type="text" required></td></tr>
<tr><td><lable><span class="error">*</span>&nbsp;Problem Statement&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Discriptive problem statement </span> </i></lable></td><td><textarea class="form-control" name="assignment_text" id="assignment_text" row="40" cols="50" type="text" size="60" required></textarea   ></td></tr>
<tr><td><lable><span class="error">*</span>&nbsp;Difficulty Level&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Easy or Diffcult </span> </i></lable></td><td><g:select class="form-control" name="difficultylevel" id="difficultylevel" from="${difficultylevellist}" required="true"/></td></tr>
<g:if test="${coff.courseofferingtype.name!='Self-Pace'}">
    <tr><td><lable><span class="error">*</span>&nbsp;Scheduled Date&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Date from which Assignment will be visible to the students  </span> </i></lable></td><td><g:datePicker name="schedule_date"   precision="day" value="${new Date()}"/></td></tr>
    <tr><td><lable><span class="error">*</span>&nbsp;Due Date&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Last Date upto which students can submit the answers for the assignment </span> </i></lable></td><td><g:datePicker name="due_date"   precision="day" value="${new Date()}"/></td></tr>
</g:if>
<tr><td><lable>Assignment Link</lable></td><td><input class="form-control" name="assignment_link" id="assignment_link" type="text" size="60" ></td></tr>
<!--  <tr><td><lable>Assignment File Name</lable></td><td><input class="form-control" name="assignment_name" id="assignment_name" type="text" size="60" ></td></tr> -->
    <g:if test="${session.assignmenttype=='MCQ'}">
    <tr><th colspan="2"><center>Enter Options</center></th></tr>
      <!--  <tr><td><lable><span class="error">*</span>&nbsp;Option Name</lable></td><td><g:select class="form-control" name="option_name" id="option_name" from="${mcqoptionlist}"/></td></tr>  -->
        <tr><td><lable><span class="error">*</span>&nbsp;Option Text&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">MCQ option answer text </span> </i></lable></td><td><input class="form-control" name="option_value" id="option_value" type="text" size="60" ></td></tr>
        <tr><td><span class="error">*</span>&nbsp;Is Correct Option?&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">If this option is correct then select Yes else select No </span> </i></td><td><g:select class="form-control" name="isCorrect" id="isCorrect" from="${correctoptionlist}"/></td></tr>
        <tr><td><lable>Option File Link&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">If option is in the form of Image or Pdf plz provide link </span> </i></lable></td><td><input class="form-control" name="mcq_option_file_link" id="mcq_option_file_link" type="text" size="60" ></td></tr>
      <!--  <tr><td><lable>Option File Name</lable></td><td><input class="form-control" name="mcq_option_file_name" id="mcq_option_file_name" type="text" size="60" ></td></tr> -->
        <tr><td></td><td><input type="button" class="btn btn-warning" name="Add Another Option" id="bt${session.trackoptions}" value="Add Another Option" onclick="return fetchaddoptions(${session.trackoptions});"/></td></tr>
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
     <!--   <tr><td><lable>Model Answer Text</lable></td><td><input class="form-control" name="model_answer_text" id="model_answer_text" type="text" size="60" ></td></tr>
        <tr><td><lable>Model Answer File Link&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Provide google drive link for model answer or answer key in the case of MCQ </span> </i></lable></td><td><input class="form-control" name="model_answer_link" id="model_answer_link" type="text" size="60" ></td></tr>
        <tr><td><lable>Model Answer File Name&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Name of the answer file </span> </i></lable></td><td><input class="form-control" name="model_answer_name" id="model_answer_name" type="text" size="60" ></td></tr>  -->
    </g:else>
</tbody>
</table>
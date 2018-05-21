<style>
 .error{
 color:red;
 }
 </style>

  <g:if test="${flash.message}">
  <div class="alert alert-error" style="display: block;color:red">${flash.message}</div>
</g:if>


<table class="table table-bordered" >
<tbody>
<g:if test="${session.uploadcategory=='Topic'}">
<tr><td><span class="error"> * </span>&nbsp; Course Topic</td><td><g:select name="coursetopic" id="coursetopic" from="${coursetopiclist.topic}" required="true"/></td></tr>
</g:if>

<tr><td><span class="error"> * </span>&nbsp; Video Title</td><td><input class="form-control" name="course_video_name" id="course_video_name" type="text" size="60" required></td></tr>
<tr><td><span class="error"> * </span>&nbsp; Video Link</td><td><input class="form-control" name="course_video_link" id="course_video_link" type="text" size="60" required>
<span><a class="btn btn-link" href="https://www.youtube.com/" target="_blank">Go To Youtube</a><span>
</td></tr>
<tr><td><span class="error"> * </span>&nbsp; Video Duration in Minutes</td><td><input class="form-control" name="course_video_duration" type="text" id="course_video_duration" size="60" onchange="checkVideoDuration(this)" required></td></tr>
<!-- <tr><td><span class="error"> * </span>&nbsp; Video Extension</td><td><g:select class="form-control" name="course_video_extension" id="course_video_extension" from="${videoextensionlist.extension}" required="true"/></td></tr>
<tr><td><span class="error"> * </span>&nbsp; Video Key words</td><td><input class="form-control" name="course_video_keywords" id="course_video_keywords" type="text" size="60" required></td></tr>
<tr><td>Video Description</td><td><input class="form-control" name="course_video_description" id="course_video_description" type="text" size="60"></td></tr>
-->
<tr>
  <!-- <td colspan="2"><center><g:submitToRemote class="btn btn-primary " url="[action: 'savecoursevideo']" update="ajaxcall" value="Save"/></center></td> -->
  <td colspan="2"><center><g:submitButton class="btn btn-sm btn-primary" name="save" value="Save" /> </center></td>
    </tr>
</tbody>
</table>

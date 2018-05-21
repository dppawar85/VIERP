<html>
<head>
<meta name="layout" content="blank">


</head>
<body>
<br>

<g:form url="[action:'saveupdatecourseoffering']">
<table>
<tbody>
<tr><td><label>Course</label></td><td><input class="form-control" name="course" type="text" value="${session.course.course_name}" readonly> </td></tr>
<tr><td><label>Start Date:&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Course commencement date </span> </i></label></td><td><g:datePicker name="startdate"  precision="day" value="${coff.start_date}" /></td></tr>
<tr><td><label>End Date:&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Last schedule date of the course </span> </i></label></td><td><g:datePicker name="enddate"  precision="day" value="${coff.end_date}" /></td></tr>
<tr><td>Batch Description:&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Specify batch name E.g.Summer-2018/19 or Winter-2018/19 </span> </i></td><td><input class="form-control" name="batch" type="text" value="${coff.batch}"></td></tr>
<g:if test="${session.isuserbelongstoorganization==true}">
    <tr><td>Year:&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">First Year/Second Year/Third Year etc.. </span> </i></td><td><g:select class="form-control" name="year" from="${yearlist.year}"" value="${coff.year.year}"/></td></tr>
    <tr><td>Academic Year:&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Academic year in which you want to offer course  </span> </i></td><td><g:select class="form-control" name="ay" from="${aylist.ay}" value="${coff.academicyear.ay}"/></td></tr>
    <tr><td>Semester:&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Semester for academic year in which you want to offer course </span> </i></td><td><g:select class="form-control" name="sem" from="${semlist.sem}"" value="${coff.semester.sem}"/></td></tr>
    <tr><td>Course Type:&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Course Type indicates course with Laboratory only or Theory only or Theory and Lab or Theory and Project or tutorial</span> </i></td><td><g:select class="form-control" name="coursetype" from="${coursetypelist.type}"" value="${coff.coursetype.type}"/></td></tr>
    <tr><td>Course Pattern:&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Pattern indicates whether student has to complete the course within the Schedule or self spaced Mode</span> </i></td><td><g:select class="form-control" name="courseofferingtype" from="${courseofferingtypelist.name}" value="${coff.courseofferingtype.name}"/></td></tr>
    <tr><td>Want to Archive?:&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">if you choose Yes , Students will not be able to opt the course</span> </i></td><td><g:select class="form-control" name="archive" from="${archivelist}" value="${archive}"/></td></tr>
</g:if>
<tr></tr>
<tr><td colspan="2" align="right"><g:submitButton class="btn btn-primary" name="update course offering" value="update"></g:submitButton></td></tr>
</tbody>
</table>
</g:form>
</body>
</html>
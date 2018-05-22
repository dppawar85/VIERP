<div class="container">
<b>Updateweeks Weeks</b>
<table class=" table table-striped  table-bordered" >
<tr>
<td>
<label>Course</label>
</td>
<td><input class="form-control"  name="coursecodecoursename" type="text" value="${coff.course.course_code}&nbsp;${coff.course.course_name}" readonly>
</td>
</tr>
<tr>
<td><label>Description</label></td>
<td><input class="form-control"  name="batch" type="text" value="${coff.batch}" readonly> </td>
</tr>
<tr>
<td><label>Start Date</label></td>
 <td> <g:formatDate date="${coff.start_date}" name="start_date" format="dd-MM-yyyy"/>  </td>
 </tr>
<tr>
<td><label>End Date</label></td>
<td> <g:formatDate date="${coff.end_date}" name="end_date" format="dd-MM-yyyy"/> </td>
</tr>

</table>

<g:form url="[action:'updatesaveweeks']">
<table class="table table-striped  table-bordered" >
<tr>
<td><lable>Week Number:</lable></td><td><g:select class="form-control" name="week_number" from="${week_number_list}" value="${week.week_number}"/>
</td>
</tr>
<tr>
<td><label>Start Date:</label></td>
<td><g:datePicker name="start_date"  precision="day" value="${new Date()}" value="${week.start_date}" /></td>
</tr>
<tr>
<td><label>End Date:</label></td>
<td><g:datePicker name="end_date"  precision="day" value="${new Date()}" value="${week.end_date}" /></td>
</tr>
<tr>
<td colspan="2"><!-- <g:submitButton name="Edit" value="Edit"></g:submitButton> -->
<center><g:submitToRemote class="btn btn-primary " url="[action: 'updatesaveweeks']" update="edit" value="edit"/></center>
</td>
</tr>

</table>
</g:form>
</div>
<br>
<br>

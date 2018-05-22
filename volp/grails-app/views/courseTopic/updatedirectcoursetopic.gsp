<html>
<body>
<center><b>Edit/Update Course Topic</b></center><br>
<g:form url="[action:'saveupdatedirectcoursetopic']" id="editoutcome">
<table class=" col-sm-12 table table-striped table-bordered">

<tbody>
<tr>
<td><label>Course</label></td>
<td><input class="form-control"  name="coursecodecoursename" type="text" value="${ct.course.course_code}:${ct.course.course_name}" readonly> </td>
</tr>
<tr>
<td><label>Course Topic Number</label></td>
<td><input class="form-control"   name="topic_number" type="number"  value="${ct.topic_number}" min="0"></td>
</tr>
<tr>
<td><label>Course Topic Statement</label></td>
<td><input class="form-control"  name="topic_statement" type="text" value="${ct.topic}" ></td>
</tr>
<tr >
<td colspan="2"><center><g:submitButton class="btn btn-primary"name="Update" value="Update"></g:submitButton></center></td>
</tr>
</tbody>
</table>
</g:form>
</body>
</html>
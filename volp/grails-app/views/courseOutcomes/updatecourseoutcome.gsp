<html>
<body>
<g:form url="[action:'saveupdatecourseoutcome']" id="editoutcome">
<table class=" col-sm-12 table table-striped table-bordered">

<tbody>
<tr>
<td><label>Course</label></td>
<td><input class="form-control"  name="coursecodecoursename" type="text" value="${coursecodecoursename}" readonly> </td>
</tr>
<tr>
<td><label>Course Outcome Number</label></td>
<td><input class="form-control"   name="outcome_number" type="number"  value="${outcome_number}" min="0"></td>
</tr>
<tr>
<td><label>Course Outcome Statement</label></td>
<td><input class="form-control"  name="outcome_statement" type="text" value="${outcome_statement}" ></td>
</tr>
<tr>
<td><label>Revision Year:</label></td>
<td><g:select name="ay" from="${aylist}" value="${ay}"/></td>
</tr>
<td><label>Is Live?:</label></td>
<td><g:select name="isCurrent" from="${isLive}" value="${isLiveflagstr}"/></td>
</tr>
<tr >

<!-- <td><input class="form-control"  name="email" type="hidden" value="${email}" ></td>
<td><input class="form-control"  name="coursecode" type="hidden" value="${coursecode}" ></td>
<td><input class="form-control"  name="coursename" type="hidden" value="${coursename}" ></td> -->

<td colspan="2"><center><g:submitButton class="btn btn-primary"name="Update" value="Update"></g:submitButton></center></td>
</tr>
</tbody>
</table>
</g:form>
</body>
</html>
<html>
<body>
<g:form url="[action:'addcourseoutline']">
<table>
<tbody>
<tr>
<td><label>Select Course</label></td>
<td><g:select class="flow-control" name="coursecodecoursename" from="${courselist}"/></td>
</tr>
<tr>
<td><td>
<td></td><td><g:submitButton class="btn btn-primary" name="Proceed" value="Proceed"></g:submitButton></td>
</tr>
</tbody>
</table>
</g:form>
</body>
</html>
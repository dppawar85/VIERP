<html>
<script>

</script>
<body>
<b>Add/Edit Assignment</b>
<g:form url="[action:'processassignment']">
<table>
<tbody>
<tr>
<td><label>Select Course</label></td>
<td><g:select name="coursecodecoursename" from="${courselist}" onChange="return fetchcourseofferingassign();"/></td>
</tr>
<tr>
<td><td><div id="material1"></div></td><td>
</tr>
</tbody>
</table>
</g:form>
</body>
</html>
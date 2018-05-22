<html>
<script>

</script>
<body>
<b>Add/Edit Sessions</b>
<g:form url="[action:'processession']">
<table>
<tbody>
<tr>
<td><label>Select Course</label></td>
<td><g:select name="coursecodecoursename" from="${courselist}" onChange="return fetchcourseofferingsession();"/></td>
</tr>
<tr>
<td><td><div id="material1"></div></td><td>
</tr>
</tbody>
</table>
</g:form>
</body>
</html>
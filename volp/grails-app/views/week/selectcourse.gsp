<html>
<title>selectcourse</title>
<script>
</script>
<body>
<b>Select Course</b>
<g:form url="[action:'processweek']">
<table>
<tbody>
<tr>
<td><label>Select Course</label></td>
<td><g:select name="coursecodecoursename" from="${courselist}" onChange="return fetchcourseofferingweek();"/></td>
</tr>
<tr>
<td><td><div id="material1"></div></td><td>
</tr>
</tbody>
</table>
</g:form>
</body>
</html>
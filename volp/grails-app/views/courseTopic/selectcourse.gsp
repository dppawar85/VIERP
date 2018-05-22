<html>
<head>
<title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <asset:stylesheet src="bootstrap.min.css"/>
        <asset:stylesheet src="font-awesome.min.css"/>
        <asset:stylesheet src="sb-admin.css"/>


</head>
<body>
<g:form url="[action:'listcourseoutlines']">
<table class="table  table-bordered col-sm-12 table-strip">
<tbody>
<tr>
<th>Select Course</th>
<td><g:select class="form-control" name="coursecodecoursename" from="${courselist}"/></td>
</tr>
<tr>

<td colspan="2"><center><g:submitButton class="btn btn-primary " name="Proceed" value="Proceed"></g:submitButton></center></td>
</tr>
</tbody>
</table>
</g:form>
<asset:javascript src="jquery.min.js"/>
    <asset:javascript src="bootstrap.bundle.min.js"/>
    <asset:javascript src="jquery.easing.min.js"/>
    <asset:javascript src="sb-admin.min.js"/>
</body>

</html>
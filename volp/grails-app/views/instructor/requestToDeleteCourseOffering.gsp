<html>
<head>
<meta name="layout" content="blank">
</head>
<body>
<br>
<h5>Select Course to Delete</h5>

<g:form action="processDeleteRequest">
    <g:hiddenField name="CoffrId" value="${dcu.id}" name="coffrid"/>
    <table class="table table-bordered">
    <tr>
        <td>Course Name: </td><td><input class="form-control" type="text" value="${dcu.course.course_name}" name="coursename" readonly/></td>
    </tr>
    <tr>
        <td>Mention the reason to delete Offering :</td><td><input class="form-control" type="text" value="" name="reason"/></td>
    </tr>
    </table>
    <g:submitButton class="btn btn-primary" name="delBtn" update="updateMe" value="Delete Request"/>
</g:form>

<br>
</body>
</html>
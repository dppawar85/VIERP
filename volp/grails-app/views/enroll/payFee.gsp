<center>Course Registration</center>
<g:form name="myForm" url="[action:'savepayfee',controller:'enroll']">
<table>
<tr><td><lable>Course</lable></td><td><g:textField name="coursename" value="${coffr.course.course_name}" readonly="true"/></td></tr>
<tr><td><lable>Fees</lable></td><td><g:textField name="coursefees" value="${cf.fees}" readonly="true"/></td></tr>
</table>
<input class="btn btn-primary btn-block" type="submit" value="Pay Fees" name="submit">
</g:form>


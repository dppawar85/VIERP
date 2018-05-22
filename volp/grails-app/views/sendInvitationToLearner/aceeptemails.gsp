<g:form url="[controller:'SendInvitationToLearner',action:'sendcodetoemails']">
<b>Send Invitation to Learners</b>
<table>
<tbody>
<tr><td><label>Course Code</label></td><td><input name="coursecode" type="text" value="${coff.course.course_code}" readonly> </td></tr>
<tr><td><label>Course Name</label></td><td><input name="coursename" type="text" value="${coff.course.course_name}" readonly> </td></tr>
<tr><td><lable>Emails:</lable></td><td><input name="emails" type="text" size="100"></td></tr>
<tr><td></td><td><g:submitButton name="Send Invitation" value="Send Invitation"  ></g:submitButton></td></tr>
</tbody>
</table>
</g:form>
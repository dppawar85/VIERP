<g:form url="[controller:'SendInvitationToLearner',action:'comparecodejoinclass']">
<h5>Join class</h5>
<table class="form-control table table-bordered">
<tbody>
<tr><th><label>Secret code</label></th><th><input class="form-control" name="secretcode" type="text" required> </th></tr>
 <g:if test="${isbelongstoorganization == true}">
    <tr><td><lable>Program:</lable></td><td><g:select class="form-control" name="program" from="${programlist}" /></td></tr>
    <tr><td><lable>Year:</lable></td><td><g:select class="form-control" name="year" from="${yearlist}" /></td></tr>
    <tr><td><lable>Division:</lable></td><td><g:select class="form-control" name="division" from="${divisionlist}" /></td></tr>
    <tr><td><label>Roll Number</label></td><td><input class="form-control"name="rollno" type="text" > </td></tr>
 </g:if>
<tr><td colspan="2" align="center"><g:submitButton class="btn btn-primary" name="Join class" value="Join class"  ></g:submitButton></td></tr>
</tbody>
</table>
</g:form>
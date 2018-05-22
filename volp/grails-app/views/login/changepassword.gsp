
<g:form url="[controller:'Login',action:'savechangepassword']" onsubmit="return checkpwd();">
<b>Change Password</b>
<table>
<tbody>
<tr><td><label>New Password:</label></td><td><input name="newpassword" id="newpassword" type="password" > </td></tr>
<tr><td><lable>Confirm Password:</lable></td><td><input name="confirmpassword" id="confirmpassword" type="password" > </td></tr>
<tr><td></td><td><g:submitButton class="btn btn-sm btn-danger" name="Change Password" value="Change Password"  ></g:submitButton></td></tr>
</tbody>
</table>
</g:form>
<br>


      <g:if test="${flash.message}">
      <div class="alert alert-error" style="display: block;color:red">${flash.message}</div>
    </g:if>
    <div class="card-body">
      <g:form url="[action:'saveregistration']">
      <div class="form-group" >
        <label >Email address</label>
        <input class="form-control" id="email" name="email" type="text" value="${email}" readonly="true" >

        <label >Password:</label>
        <input class="form-control" id="password" name="password" type="password" placeholder="Create Your Password" onchange="verifyConfirmPassword()" required>

        <label >Confirm Password:</label>
        <input class="form-control" id="confirmpassword" name="confirmpassword" type="password" placeholder="Confirm Your Password" onchange="verifyConfirmPassword()" required> <span id='message'></span>
        <label>First Name:</label>
        <input class="form-control" name="firstname" type="text" placeholder="Enter Your First Name" required>

         <label>Last Name:</label>
        <input class="form-control" name="lastname" type="text" placeholder="Enter Your Last Name" required>

        <label>User Type:</label>
          <g:select class="form-control" name="usertype" from="${usertypelist}" value=""/>
          <br>
        <g:submitButton class="btn btn-primary btn-block" name="Register" value="Register"></g:submitButton></td>

      </div>
    </g:form>

<script>
 /* verifyConfirmPassword() should in verifyemail */

</script>
</body>
</html>

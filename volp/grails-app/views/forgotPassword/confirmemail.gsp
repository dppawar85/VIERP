
<g:form url="[action:'verifyotp']">
<div class="form-group">
   <label for="exampleInputEmail1">Email address</label>
  <input class="form-control" id="email" name="email" type="text" value="${email}" aria-describedby="emailHelp" readonly="true">
  <label for="otp">OTP:</label>
    <input class="form-control" id="otp" name="otp" type="text">
    <br>
    <g:submitButton class="btn btn-primary btn-block" name="Verify OTP" value="Verify OTP"></g:submitButton>

 </div>
</g:form>



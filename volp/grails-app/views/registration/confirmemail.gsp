
<div class="form-group">
   <label for="exampleInputEmail1">Email address</label>
  <input class="form-control" id="email" name="email" type="text" value="${email}" aria-describedby="emailHelp" readonly="true">
  <label for="otp">OTP:</label>
    <input class="form-control" id="otp" name="otp" type="text">
    <br>
    <button class="btn btn-primary btn-block" name="Verify OTP" value="Verify Email" onclick="confirmOTP()">Confirm OTP</button>
 </div>
 <style>
 .error{
 color:red;
 }
 </style>
 <g:form action="storePlatformFeedback">
  <div class="form-group">
   <span class="error"> * </span>&nbsp;
 <label for="description">Give Feedback::</label>
    <g:textArea name="description" class="form-control"  placeholder="Write your valuable feedback here....." rows="2" cols="40" required="true"/>
    <g:hiddenField name="type" value="${type}" />
  </div>


  <button type="submit" class="btn btn-sm btn-primary">Submit Feedback</button>
</g:form>
<br><br><br>
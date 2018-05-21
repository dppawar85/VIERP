
<div class="card card-login mx-auto mt-5">

  <g:if test="${flash.message}">
  <div class="alert alert-error" style="display: block;color:red">${flash.message}</div>
</g:if>
<div class="card-body">
<g:form url="[action:'addcourseoutcomes']">
Select Course
<g:select class="form-control" name="coursecodecoursename" from="${courselist}"/> <br/>
<g:submitButton class="btn btn-primary btn-block" name="Proceed" value="Proceed"></g:submitButton>
</g:form>
</div>
</div>

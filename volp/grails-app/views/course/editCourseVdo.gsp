<div class="container">
<g:if test="${flash.message}">
  <div class="alert alert-error error" style="display: block">${flash.message}
  </div>
  </g:if>
<g:form >
<table class="table  table-bordered col-sm-12 table-strip" >
<tr>
<th>Description</th>
<th>Meta-Data</th>
<th>Link</th>
<th>Duration</th>
<th>Edit Video</th>


</tr>
<tr>
<td><input class="form-control" type="text" name="desp" value="${cv.description}"></td>
<td><input  class="form-control" type="text" name="md" value="${cv.meta_data}"></td>
<td><input  class="form-control" type="text" name="vl" value="${cv.video_link}"></td>
<td><input class="form-control" type="text" name="vd" value="${cv.duration_in_minutes}"></td>
<g:hiddenField name="cvid" value="${cv.id}" />
<td><g:submitToRemote class="btn btn-primary" url="[action: 'processEditVdo']" update="material" value="Edit vdo"/></td>
</tr>
</table>
<br>
<br>
</g:form>
</div>
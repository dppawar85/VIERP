<div class="container">
<g:form >
<table class="table  table-bordered col-sm-12 table-strip">
<tr>
<th>Description</th>
<th>Meta-Data</th>
<th>Link</th>
<th>Edit Material</th>


</tr>
<tr>
<td><input class="form-control" type="text" name="desp" value="${cm.description}"></td>
<td><input class="form-control" type="text" name="md" value="${cm.meta_data}"></td>
<td><input class="form-control" type="text" name="ml" value="${cm.material_link}"></td>
<g:hiddenField name="cmid" value="${cm.id}" />
<td><g:submitToRemote class="btn btn-primary" url="[action: 'processEditMaterial']" update="material" value="Edit material"/></td>

</tr>
</table>
<br>
<br>
</g:form>
</div>
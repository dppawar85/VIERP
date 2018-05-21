
<div class="container">
<table>
<th>Sr.No</th>
<th>Assignment Description</th>
<th>Assignment Type</th>
<th>Edit Assignment </th>
<th>Delete Assignment </th>
<g:each var="ass" in="${al}" status="i">
<tr>
<td>${++i}</td>
<td>${ass.assignment.assignment_text}</td>
<td>${ass.assignment.assignmenttype.type}</td>
<td>
<i class="fa fa-2x fa-pencil-square-o" onclick="editAssignent(${ass.assignment.id},${ass.id})" /></td>
<td> <g:link action="deleteass" id="${ass.id}"><i class="fa fa-2x fa-trash" aria-hidden="true" data-toggle="tooltip" title="delete"></i></g:link></td>
<tr>
</g:each>
</table>
<div id="updateMe" >

</div>
<br><br>
</div>

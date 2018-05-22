
  <g:form url="[action:'saveupdatecoursetopic']" onsubmit="return saveudateddata();">
  <h6>Edit Course Topic</h6>
<table class="table  table-bordered col-sm-12 table-strip">
<tbody>
<tr>
<th>Outline Number</th>
<td><input class="form-control" name="outline_number" id="update_outline_number" type="text" value="${coursetopic.courseoutline.outline_number}" readonly></td>
</tr>
<tr>
<th>Topic Number</th>
<td><input class="form-control" name="topic_number" id="update_topic_number" type="text" value="${coursetopic.topic_number}" ></td>
</tr>
<tr>
<th>Topic Statement</th>
<td><input class="form-control" name="topic" type="text" id="update_topic" size="50" value="${coursetopic.topic}"></td>
</tr>
<tr>
<td colspan="2"><center><input class="btn btn-primary " type="submit" value="Edit Topic" /></center></td>
</tr>
</tbody>
</table>
 </g:form>


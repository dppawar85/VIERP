<g:if test="${flash.message}">
  <div class="alert alert-error error" style="display: block">${flash.message}
  </div>
  </g:if>
<!-- OutLine Material -->
<g:if test="${ot=='o'}">
<!--OutLine:${co.course.course_name}::${co.outline_number}:${co.outline}-->
<h6> Edit Course Video and Course Material</h6>
<table class="table  table-bordered col-sm-12 table-strip">

<tr><th>Description</th><th>Path</th><th>Meta-data</th><th>Edit</th></tr>
<g:each var="c" in= "${cm}">
    <tr><td>${c.description}</td>
    <td>${c.material_link}</td>
    <td>${c.meta_data}</td>
    <td><a href="#" onclick="editMaterial(${c.id})"><i class="fa fa-pencil-square-o fa-1x" aria-hidden="true" ></i></a></td></tr>
</g:each>
</table>

<!-- Vdo --->
<table class="table  table-bordered col-sm-12 table-strip">

<tr><th>Description</th><th>Path</th><th>Meta-data</th><th>Duration(mins)</th><th>Edit</th></tr>
<g:each var="c" in= "${cv}">
    <tr><td>${c.description}</td>
    <td>${c.video_link}</td>
    <td>${c.meta_data}</td>
    <td>${c.duration_in_minutes}</td>
    <td><a href="#" onclick="editVdo(${c.id})"><i class="fa fa-pencil-square-o fa-1x" aria-hidden="true" ></i> </a></td></tr>
</g:each>
</table>
</g:if>



<g:elseif test="${ot=='t'}"><!-- Topic Material -->
Topic:${co.courseoutline.course.course_name}::${co.courseoutline.outline}::${co.topic}
<table class="table  table-bordered col-sm-12 table-strip">
<tr><th>Description</th><th>Path</th><th>Meta-data</th><th>Edit</th></tr>
<g:each var="c" in= "${cm}">
    <tr><td>${c.description}</td>
    <td>${c.material_link}</td>
    <td>${c.meta_data}</td>
    <td><a href="#" onclick="editMaterialTopic(${c.id})"><i class="fa fa-pencil-square-o fa-1x" aria-hidden="true" ></i></a></td></tr>
</g:each>
</table>

<!-- Vdo --->
<table class="table  table-bordered col-sm-12 table-strip">
<tr><th>Description</th><th>Path</th><th>Meta-data</th><th>Duration(mins)</th><th>Edit</th></tr>
<g:each var="c" in= "${cv}">
    <tr><td>${c.description}</td>
    <td>${c.video_link}</td>
    <td>${c.meta_data}</td>
    <td>${c.duration_in_minutes}</td>
    <td><a href="#" onclick="editVdoTopic(${c.id})"><i class="fa fa-pencil-square-o fa-1x" aria-hidden="true" ></i></a></td></tr>
</g:each>
</table>

</g:elseif>
<div class="container">

<!-- OutLine Material -->
<g:if test="${ot=='o'}">
<b>OutLine:${co.course.course_name}::${co.outline_number}:${co.outline}</b>
<h6><b>Edit Course Video and Course Material</b></h6>
<b>Course Material(s):</b>
<table class="table table-strip table-bordered col-sm-12 ">
<tr><th>Description</th><th>Path</th><th>Meta-data</th><th>Edit</th></tr>
<g:if test="${cm.size()!=0}">
<g:each var="c" in= "${cm}">
    <tr><td>${c.description}</td>
    <td><a href="${c.material_link}" target="_blank">${c.material_link}</a> </td>
    <td>${c.meta_data}</td>
    <td><a href="#" onclick="editMaterial(${c.id})"><i class="fa fa-pencil-square-o fa-2x" aria-hidden="true" ></i></a></td></tr>
</g:each>
</g:if>
<g:else>
<tr><td>No Study Material Available</td></tr>
</g:else>
</table>

<!-- Vdo --->
<b>Course Video(s):</b>
<table class="table  table-bordered col-sm-12 table-strip">

<tr><th>Description</th><th>Path</th><th>Meta-data</th><th>Duration(mins)</th><th>Edit</th></tr>
<g:if test="${cv.size()!=0}">
<g:each var="c" in= "${cv}">
    <tr><td>${c.description}</td>
   <!-- <td>${c.video_link}</td> -->
	<td><iframe class="embed-responsive-item" width="500" height="300" src="https://www.youtube.com/embed/${c.video_link}" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe></td>
    <td>${c.meta_data}</td>
    <td>${c.duration_in_minutes}</td>
    <td><a href="#" onclick="editVdo(${c.id})"><i class="fa fa-pencil-square-o fa-2x" aria-hidden="true"></i></a></td></tr>
</g:each>
</g:if>
<g:else>
<tr><td>No Videos Available</td></tr>
</g:else>
</table>
</g:if>



<g:elseif test="${ot=='t'}"><!-- Topic Material -->
<b>Topic:${co.courseoutline.course.course_name}::${co.courseoutline.outline}::${co.topic}</b>
<table class="table  table-bordered col-sm-12 table-strip">
<tr><th>Description</th><th>Path</th><th>Meta-data</th><th>Edit</th></tr>
<g:if test="${cm.size()!=0}">
<g:each var="c" in= "${cm}">
    <tr><td>${c.description}</td>
    <td><a href="${c.material_link}" target="_blank">${c.material_link}</a> </td>
    <td>${c.meta_data}</td>
    <td><a href="#" onclick="editMaterialTopic(${c.id})"><i class="fa fa-pencil-square-o fa-2x" aria-hidden="true" ></i></a></td></tr>
</g:each>
</g:if>
<g:else>
    <tr><td>No Study Material Available</td></tr>
</g:else>
</table>

<!-- Vdo --->
<table class="table  table-bordered col-sm-12 table-strip">
<tr><th>Description</th><th>Path</th><th>Meta-data</th><th>Duration(mins)</th><th>Edit</th></tr>
<g:if test="${cv.size()!=0}">
<g:each var="c" in= "${cv}">
    <tr><td>${c.description}</td>
    <td><iframe class="embed-responsive-item" width="500" height="300" src="https://www.youtube.com/embed/${c.video_link}" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe></td>
	<!-- <td><iframe class="embed-responsive-item" width="${vp.width}" height="${vp.height}" src="https://www.youtube.com/embed/${c.video_link}" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe></td> -->
    <td>${c.meta_data}</td>
    <td>${c.duration_in_minutes}</td>
    <td><a href="#" onclick="editVdoTopic(${c.id})"><i class="fa fa-pencil-square-o fa-2x" aria-hidden="true" ></i></a></td></tr>
</g:each>
</g:if>
<g:else>
    <tr><td>No Videos Available</td></tr>
</g:else>
</table>

</g:elseif>
</br>
</div>
</br>
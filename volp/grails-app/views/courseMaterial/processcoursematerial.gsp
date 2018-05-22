<style>
 .error{
 color:red;
 }
 </style>

<table class="table  table-bordered col-sm-12 table-strip">
<tbody>
<g:if test="${session.uploadcategory=='Unit' || (session.uploadcategory=='Topic' && courseoutlinelist.size()>1)}">
<tr><td colspan="2"><span class="error"> * </span>&nbsp; Select Course Unit <g:select class="form-control"  name="courseoutline" id="courseoutline" from="${courseoutlinelist.outline}" onChange="return callmetoo(); " required="true" placeholder="select course unit"/></td></tr>
</g:if>
<g:elseif test="${session.uploadcategory=='Unit' || (session.uploadcategory=='Topic' && courseoutlinelist.size()==1)}">
<tr><td><span class="error"> * </span>&nbsp; Course Topic</td><td><g:select class="form-control"  name="coursetopic" id="coursetopic" from="${coursetopiclist.topic}" required="true"/></td></tr>
<tr><td><span class="error"> * </span>&nbsp;Material Title</td><td><input class="form-control"  name="course_material_name" id="course_material_name" type="text"  required></td></tr>
<tr><td><span class="error"> * </span>&nbsp;Material URL Link</td><td><input class="form-control"  name="course_material_link" id="course_material_link" type="text"  required> <span><a class="btn btn-link" href="https://www.google.com/drive/" target="_blank">Go To Google Drive</a><span></td></tr>
</g:elseif>
<g:else>
<tr><td><span class="error"> * </span>&nbsp;Material Title&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Assign name for Material similar to Topic name </span> </i></td><td><input class="form-control"  name="course_material_name" id="course_material_name" type="text"  required></td></tr>
<tr><td><span class="error"> * </span>&nbsp;Material URL Link&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Google drive link for Material </span> </i></td><td><input class="form-control"  name="course_material_link" id="course_material_link" type="text"  required> <span><a class="btn btn-link" href="https://www.google.com/drive/" target="_blank">Go To Google Drive</a><span></td></tr>
<!-- <tr><td><span class="error">  </span>&nbsp;File Extension</td><td><g:select class="form-control"  name="material_extension" id="material_extension" from="${materialextensionlist.extension}" required="true"/></td></tr>
<tr><td><span class="error">  </span>&nbsp;Material Key words&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Specify the Key words related with topic so that users can easily search that material  </span> </i></td><td><input class="form-control"  name="material_keywords" id="material_keywords" type="text" size="60" required></td></tr>
<tr><td>Material Description&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Short Description of material </span> </i></td><td><input class="form-control"  name="material_description" id="material_description" type="text" size="60"></td></tr> -->
</g:else>
</tbody>
</table>
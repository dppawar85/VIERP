<style>
 table, tr{
  height: 10px;
 }
 table {
     border-collapse: collapse;
 }

 table, th, td {
     border: 1px solid black;
 }
</style>
<div class="pull-right">
<a class="btn btn-sm btn-warning" onclick="addmaincategory()">
        <span class="nav-link-text">Add Main Category</span>
</a>

<a class="btn btn-sm btn-warning" onclick="addsubcategory()">
        <span class="nav-link-text">Add Sub Category</span>
</a>
<a class="btn btn-sm btn-warning" onclick="changesubcategorylinking()">
        <span class="nav-link-text">Change Linking of Sub Category</span>
</a>


</div>
<br><br>
<div class="container" style="position : relative;font-size: 15px; margin-left: 20%">
<g:set var="x" value="${0}"/>
<table class="" style=" width: 60%">
<tr class="bg-secondary">
<td><b>Course Category</b></td><td><b>SubCategory</b></td>
</tr>
<g:each var="m" in="${menu}">
<g:if test="${x%2==0}">
    <tr><th colspan="2">${m.name} &nbsp;&nbsp;&nbsp;<span> <a onclick="editmaincategory(${m.id},'${m.name}')"> <i class="fa fa-edit"></i></a> </span></th></tr>
</g:if>
<g:else>
    <g:each var="s" in="${m}">
         <tr><td></td><td>${s.name}&nbsp;&nbsp;&nbsp; <span> <a onclick="editmaincategory(${s.id},'${s.name}')"><i class="fa fa-edit"></i></a> </span></td></tr>
    </g:each>
</g:else>
<g:set var="x" value="${x+1}"/>
</g:each>
</table>
<br>
</div>


<head>
<meta name="layout" content="blank">
</head>
<br><br>
<g:form name="saveCM" url="[action:'saveEditCourseMaterial',controller:'courseMaterial']">
<g:hiddenField name="cm" value="${cm.id}" />
Name:&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Topic for which you want to upload Material </span> </i><input class="form-control" type="text" value=${cm.material_name} name="name"><br>
Link:&nbsp;&nbsp;<i class="fa fa-info-circle info"><span class="infotext">Google drive link for the Material </span> </i><input class="form-control" type="text" value=${cm.material_link} name="link"><br>
<g:submitButton name="update" value="Update" class="btn btn-sm btn-warning"/>
</g:form>
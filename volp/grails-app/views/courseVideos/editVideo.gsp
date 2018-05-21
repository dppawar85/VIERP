<head>
<meta name="layout" content="blank">
</head>
<br><br>
<g:form name="saveCV" url="[action:'saveEditCourseVideo',controller:'courseVideos']">
<g:hiddenField name="cv" value="${cv.id}" />
Name:<input class="form-control" type="text" value="${cv.video_name}" name="name"><br>
Link:<input class="form-control" type="text" value="${cv.video_link}" name="link"><br>
<g:submitButton name="update" value="Update" class="btn btn-sm btn-warning"/>
</g:form>
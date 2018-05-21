Course Logo:<img width="120" height="100" src="${createLinkTo(dir:crs.imgpath, file: crs.imgname )}" /></div>
<g:hiddenField name="cid" value="${cid}" />
Category:${crs.coursecategory.name}<br>
Code:${crs.course_code}<br>
Name:${crs.course_name}<br>
Instructor :
<ul>
	<g:each var="ins" in = "${crs.courseinstructor}">
        <li>${ins.person.firstName} ${ins.person.lastName}</li>
    </g:each>
</ul>
features:
<ul>
    <g:each var="frs" in = "${features}">
        <li>${frs.feature}</li>
    </g:each>
</ul>
Prerequisite:
<ul>
    <g:each var="preq" in = "${pre}">
        <li>${preq.prerequisite}</li>
    </g:each>
</ul>
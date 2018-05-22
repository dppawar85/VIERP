<div id="rating">
<g:each var="crs" in="${lcrs}">

    Course:${crs.course_name} || Rating:${crs.rating} <g:include controller="learner" action="stars" params="[rating1:crs.rating,cid:crs.id]" /><br>
    Rate: <input type="number" name="rating" id="rating${crs.id}" value="" min="0" max="5" onchange="changeRating(${crs.id})"/><br>
</g:each>
</div>
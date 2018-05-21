<g:set var="x" value="${0}"/>
<g:each var="obj" in="${catCrs}">

<g:if test="${x%2==0}">
Category:${obj.name}

</g:if>
<g:else>
Crs:${obj}

</g:else>
<g:set var="x" value="${x+1}"/>
<br>
</g:each>
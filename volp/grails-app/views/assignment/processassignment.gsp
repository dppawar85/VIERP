<html>

<body>
<g:set var="x" value="${row-1}"/>
<table class="table table-bordered table-striped">
<tbody>
<g:each in="${(0..x).toList()}" var="i" status="d">
    <tr >
    <g:each in="${listOfLists[i]}" var="e" status="j">
         <g:if test="${i==0}">
            <th>${listOfLists[i][j]}</th>
        </g:if>
        <g:else>
            <td >${listOfLists[i][j]}</td>
        </g:else>
    </g:each>
    <g:if test="${i!=0}">
       <td colspan="2" align="center"><g:link class="btn btn-primary" action="addassignment" id="${i}">Add Assignment</g:link></td>
    </g:if>
    </tr>
</g:each>
</tbody>
</table>
</body>
</html>
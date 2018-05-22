
<g:set var="x" value="${row-1}"/>
<table>
<tbody>
<g:each in="${(0..x).toList()}" var="i" status="d">
    <tr>
    <g:each in="${listOfLists[i]}" var="e" status="j">
         <g:if test="${i==0}">
            <td><b>${listOfLists[i][j]}</b></td>
        </g:if>
        <g:else>
            <td>${listOfLists[i][j]}</td>
        </g:else>
    </g:each>
    <g:if test="${i!=0}">
        <td></td><td>
   <!--     <g:link action="furtherprocesssession" id="${i}">Add Sessions</g:link> -->
        <a href="#" onclick="furtherprocesssession(${i})">Add Session...     </a>
        </td>
    </g:if>
    </tr>
</g:each>
</tbody>
</table>
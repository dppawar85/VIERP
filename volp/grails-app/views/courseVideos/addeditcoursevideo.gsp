
<g:set var="x" value="${row-1}"/>
<table class="table table-bordered" >
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
     <!--   <td> <g:link action="deletecoursevideo" id="${i}"><i class="fa fa-trash-o" aria-hidden="true" data-toggle="tooltip" title="delete"></i></g:link></td>  -->
     <td colspan="2"><g:submitButton name="save" value="Save" /> </center></td>
    </g:if>
    </tr>
</g:each>
</tbody>
</table>

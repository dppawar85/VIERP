<title>processweek</title>
<g:set var="x" value="${row-1}"/>
<table class=" table table-striped table-bordered"  id="data1" >
<b>processweek</b>
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
    </tr>
    <tr>
    <g:if test="${i!=0}">
        <center> <td colspan="5"> <a "btn btn-primary" href="#" onclick="furtherprocessweek(${i})" >Go </a>
       <!-- <g:link class  action="furtherprocessweek" id="${i}">Add Weeks</g:link> -->
        </td> </center>
    </g:if>
    </tr>
</g:each>
</tbody>
</table>
<script>

</script>
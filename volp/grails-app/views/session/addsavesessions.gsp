<html>
<body>
<table>
<tbody>
<tr><td><label><b>Course</b></label></td><td><input name="coursecodecoursename" type="text" value="${coff.course.course_code}&nbsp;${coff.course.course_name}" readonly></td>
<td><label><b>Description</b></label></td><td><input name="batch" type="text" value="${coff.batch}" readonly></td>
<td><label><b>Start Date</b></label></td> <td> <g:formatDate date="${coff.start_date}" name="start_date" format="dd-MM-yyyy"/></td>
<td><label><b>End Date<b></label></td> <td> <g:formatDate date="${coff.end_date}" name="end_date" format="dd-MM-yyyy"/></td></tr>
</tbody>
</table>
<br>
<b>Add Sessions</b>
<g:form url="[action:'addsavesessions']">
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
        <td><g:select name="session_number" from="${session_number_list}" value="${previus_session_number_list[i]}" /></td>
        <g:if test="${session.courseofferingtype=='Weekwise'}">
            <td><g:select name="week_number" id="${i},${j}" from="${week_number_list}" value="${previus_week_number_list[i]}" /></td>
        </g:if>
        <g:if test="${session.courseofferingtype=='Daywise'}">
            <td><g:datePicker name="start_date"  id="${i},${j}" precision="day" value="${previus_date_number_list[i]}"/></td>
        </g:if>
    </g:if>
    </tr>
</g:each>
<tr><td><g:submitToRemote url="[action: 'addsavesessions']" update="ajaxcall" value="Update Schedule"/>
<!-- <g:submitButton name="Add Schedule" value="Add Schedule"></g:submitButton> -->
</td></tr>
</tbody>
</table>
</g:form>
</br></br>
br
</body>
</html>